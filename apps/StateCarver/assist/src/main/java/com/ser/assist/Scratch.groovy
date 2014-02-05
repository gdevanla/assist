package com.ser.assist

import com.ser.assist.oraclefinder.OracleFinderConfiguration
import com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver
import com.ser.assist.testgenerator.VariableNameGenerator
import soot.Body
import soot.BodyTransformer
import soot.G
import soot.Immediate
import soot.Local
import soot.PackManager
import soot.SootMethod
import soot.Transform
import soot.Unit
import soot.Value
import soot.coffi.array_element_value
import soot.grimp.NewInvokeExpr
import soot.jimple.AssignStmt
import soot.jimple.Constant
import soot.jimple.InstanceInvokeExpr
import soot.jimple.InterfaceInvokeExpr
import soot.jimple.InvokeExpr
import soot.jimple.NewExpr
import soot.jimple.RetStmt
import soot.jimple.ReturnStmt
import soot.jimple.ReturnVoidStmt
import soot.jimple.SpecialInvokeExpr
import soot.jimple.StaticInvokeExpr
import soot.jimple.Stmt
import soot.options.Options
import soot.toolkits.graph.BriefUnitGraph
import soot.toolkits.graph.UnitGraph
import soot.toolkits.graph.pdg.EnhancedUnitGraph
import soot.toolkits.scalar.SimpleLocalDefs
import sun.rmi.rmic.iiop.SpecialClassType

import java.util.regex.Pattern

class OracleReplayInfo {
    int counter = 13;
    Body testBody = null;
    SootMethod method;
    Unit mut;
    List<Unit> replayUnits = new ArrayList<>();
}

//set up soot options

def setSootOptions(String className, String subSignature){
    G.reset();
//Options.v().set_verbose(true);
    Options.v().set_no_bodies_for_excluded(true);
    ArrayList<String> exclude_list = new ArrayList<String>();
    exclude_list.add("java.");
    Options.v().set_exclude(exclude_list);
    Options.v().set_whole_program(true);
    Options.v().set_output_format(Options.output_format_jimple);
    Options.v().set_keep_line_number(true);
    Options.v().setPhaseOption("jb", "use-original-names");
//Options.v().set_verbose(verbose);
//Options.v().set_output_format(outputFormat);


    PackManager.v().getPack("jtp").add(new Transform("jtp.myTransformer", new BodyTransformer() {
        @Override
        protected void internalTransform(Body body, String s, Map map) {
            //com.ser.oraclefinder.testartifacts.Apples: int add(int)
            if ( SootHelper.isMethodInvokedInBody(body, SootHelper.getResolvedMethod(className, subSignature)))
            {
                UnitGraph cfg = new EnhancedUnitGraph(body);
                SimpleLocalDefs simpleLocalDefs = new SimpleLocalDefs(cfg);
                processMethod(body, simpleLocalDefs, className, subSignature);
            }

        }
    }));
}


def processMethod(Body body, SimpleLocalDefs simpleLocalDefs, String className, String subSignature)
{
    //this call update the body by performing constant propogation
    SootHelper.propogateConstants(body)

    SootMethod mut = SootHelper.getResolvedMethod(className, subSignature)
    List<Unit> unitsWithMut = body.getUnits().findAll() { Unit it ->
       SootHelper.doesUnitInvokeMethod(it, mut)
    }

    //filter units that do not lead to multiple paths (no if/loop constructs)
    def g = new BriefUnitGraph(body)
    unitsWithMut = unitsWithMut.findAll() {u -> !hasMultiplePaths(g, u)}

    //Special treatment for these folks, we don't look up the hashset.
    List oinfos = unitsWithMut.collect() {
        generateReplayInfo(body, it)
    }

    oinfos.each() {OracleReplayInfo r -> println "New sequence" ; println r.method; println r.mut;
          r.replayUnits.each() { println it }
    }

    /*for (u in unitsWithMut){
      List paths = []
      //all_paths(g, u, [],paths)
      println "path in " + body.getMethod() + "==>" + paths.each() {println it}
    }*/

    oinfos.each() {replay(it)};

}

def replay(OracleReplayInfo oInfo)
{
    //Create defs
    HashSet<String> vars = new HashSet<>();

    //Create local for mut statement if it has an assignment
    Unit mutUnit = oInfo.replayUnits[0];
    if (mutUnit instanceof AssignStmt){
        //println "Mut Unit = " + mutUnit
       //vars.add(((Local)mutUnit.getDefBoxes().get(0).value).toString());
    }

    oInfo.replayUnits.drop(0).each(){
        def alllocals = SootHelper.getAllImmediates(it).findAll() { it instanceof Local };
        alllocals.each() { vars.add(it.getName()); vars.add(((Local)it).getName() + "_clone")}

        if ( it instanceof AssignStmt){
            String varname = ((Local)mutUnit.getDefBoxes().get(0).value).toString()
            vars.add(varname)
            vars.add(varname + "_clone")
        }
    }

    //AssignStmt, InvokeStmt, RetStmt, ReturnStmt,
    //AssignStmt
    List<String> statements = []



    mutUnits = generateStatementForMut(oInfo);

    //consolidate all vars
    vars.addAll(mutUnits[0])
    vars.each() { statements.add("def " + it)}

    //mut statement
    statements.addAll(mutUnits[1])
    //remaining statements
    oInfo.replayUnits.drop(1).each() {
        statements.addAll(generateStatement(it))
    }



    println "Output Test for Test Method =" + oInfo.method;
    statements.each() { println it}
}


def generateStatement(Unit u){

    List<String> statements = []

    switch (u) {
        case {((Stmt)u).containsInvokeExpr() }:

            InvokeExpr expr = ((Stmt)u).getInvokeExpr();
            switch(expr) {
                case {expr instanceof SpecialInvokeExpr}:
                    String type =  ((Local)((SpecialInvokeExpr)expr).getBase()).getType();
                    String u1 = ((Local)((SpecialInvokeExpr)expr).getBase()).getName() + " =  new " + type + "()"
                    String u2 = ((Local)((SpecialInvokeExpr)expr).getBase()).getName() + "_clone =  new " + type + "()"
                    statements.add(u1);
                    statements.add(u2);
                    break;
                case { expr instanceof InstanceInvokeExpr }:  //handles interface and virtual
                    String base  = ((Local)((InstanceInvokeExpr)expr).getBase()).getName()
                    SootMethod method = ((InstanceInvokeExpr)expr).getMethod()
                    List<Value> argLocals = ((InstanceInvokeExpr)expr).getArgs()
                    String args = argLocals.join(",")
                    //(argLocals.inject() { "," + it.getName() }).getAt(0..1)

                    String u1 = String.format("%s.%s(%s)", base, method.getName(), args)
                    String u2 = String.format("%s.%s(%s)", base + "_clone", method.getName(), args)

                    if ( u instanceof AssignStmt){
                        u1 = ((Local)((AssignStmt)u).getLeftOp()).getName() + " = " + u1
                        u2 =  ((Local)((AssignStmt)u).getLeftOp()).getName() + "_clone" + " = " + u2
                    }

                    statements.add(u1)
                    statements.add(u2)
                    break
                case { expr instanceof StaticInvokeExpr}:
                    // for now ignore any statements that are not assert calls.
                    //also handle assert statement
                    //TODO: check for assert calls
                    String base = ((StaticInvokeExpr)expr).getType().toString();
                    SootMethod method = ((StaticInvokeExpr)expr).getMethod();

                    if (SootHelper.isAssert(u)){
                        params = SootHelper.getParamsFromAssert(u);
                        param1 = params.get(0)
                        param2 = params.get(1)
                        switch([param1, param2]){
                            case {param1 instanceof Local && param2 instanceof Local}:
                                String u1 = String.format("%s(%s,%s)", method.getName(), param1, param2)
                                String u2 = String.format("%s(%s_clone,%s_clone)", method.getName(), param1, param2)
                                String u3 = String.format("%s(%s,%s_clone)", method.getName(), param1, param2)
                                String u4 = String.format("%s(%s_clone,%s)", method.getName(), param1, param2)
                                statements.addAll([u1, u2, u3, u4])
                                break
                            case {param1 instanceof Local && param2 instanceof Constant}:
                                String u1 = String.format("%s(%s,%s_clone)", method.getName(), param1, param1)
                                statements.add(u1)
                                break
                            case {param1 instanceof Constant && param2 instanceof Local}:
                                String u1 = String.format("%s(%s_clone,%s)", method.getName(), param2, param2 )
                                statements.add(u1)
                                break
                            case {param1 instanceof Constant && param2 instanceof Constant}:
                                break

                        }
                    }

                    break
            }
            break;
        case {u instanceof ReturnStmt || u instanceof ReturnVoidStmt ||
                u instanceof AssignStmt && ((AssignStmt)u).getRightOp() instanceof NewExpr}:
            statements.add("//" + u.toString());
            break;
        default:
            String u1 = u.toString();
            String u2 = u.toString();
            List<Local> locals = SootHelper.getAllLocals(u);
            for (Local l:locals){
               Pattern p = Pattern.compile(String.format("\\b%s\\b", l.getName().replace("\$", "\\\$")))
               u2 = u2.replaceAll(p) { l.getName() + "_clone" }
            }

            statements.add(u1)
            statements.add(u2)
    }

    return statements;

}

def generateStatementForMut(OracleReplayInfo oInfo){

    Stmt mutUnit = (Stmt) oInfo.replayUnits[0]

    String loadStatementTemplate = "%s = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState(\"%s\")"

    assert ((Stmt)oInfo.replayUnits[0]).containsInvokeExpr()
    assert ((Stmt)oInfo.replayUnits[0]).getInvokeExpr() instanceof InstanceInvokeExpr
    InstanceInvokeExpr mutUnitExpr = mutUnit.getInvokeExpr();

    Set<String> vars = new ArrayList<>();
    List<String> statements = new ArrayList<>();

    Value base = mutUnitExpr.getBase();
    SootMethod method =  mutUnitExpr.getMethod();

    //Load all states
    def state1_var = VariableNameGenerator.getNextName("state")
    vars.add(state1_var)
    statements.add(String.format(loadStatementTemplate, state1_var, XStreamStateCarver.getStateFileName(oInfo.counter)))

    //clone
    def state2_var = state1_var + "_clone"
    vars.add(state2_var)
    statements.add(String.format(loadStatementTemplate, state2_var, XStreamStateCarver.getStateFileName(oInfo.counter)))

    List<String> paramVar = (0..<method.getParameterCount()).collect() { VariableNameGenerator.getNextName("param_" + it)}
    paramVar.each() { vars.add(it)}
    paramVar.eachWithIndex { String entry, int i ->
     statements.add(String.format(loadStatementTemplate, entry, XStreamStateCarver.getParamFileName(oInfo.counter, i )))
    }

    //Invoke mut
    String u1 = ""
    String u2 = ""

    String params = paramVar.join(",")
    u1 = String.format("%s.invokeMethod(\"%s\", %s)", state1_var, method.getName(), params?params:"null")

    if ( mutUnit instanceof AssignStmt){
        vars.addAll([((Local)mutUnit.leftOp).getName(),
                ((Local)mutUnit.leftOp).getName() + "_clone"])

        u1 = ((Local)mutUnit.leftOp).getName() + " = " + u1
        u2 = String.format(loadStatementTemplate,
                ((Local)mutUnit.leftOp).getName() + "_clone" ,
                XStreamStateCarver.getReturnFileName(oInfo.counter) )
        statements.addAll([u1, u2])
    }

    return [vars, statements]

}


def generateReplayInfo(Body body, Unit u){

    OracleReplayInfo oInfo = new OracleReplayInfo(method:body.getMethod(), mut:u, testBody:body);
    UnitGraph cfg = new EnhancedUnitGraph(body);
    SimpleLocalDefs simpleLocalDefs = new SimpleLocalDefs(cfg);

    Set<Unit> defUnitsInEnv = new HashSet<Unit>();
    defUnitsInEnv.addAll(SootHelper.getDefsOfAllLocals(simpleLocalDefs, u))
    if (u instanceof AssignStmt){
        defUnitsInEnv.add(u); //mut used in assigned.
    }

    def g = new BriefUnitGraph(body)
    oInfo.replayUnits.add(u); //adding mut.
    Unit current_unit = u
    while (g.getSuccsOf(current_unit)){
         current_unit = g.getSuccsOf(current_unit).get(0);
        List<Immediate> imm = SootHelper.getAllImmediates(current_unit);

       // if ( !SootHelper.isAssert(current_unit))
        //{
        //    if (!SootHelper.allLocals(imm)) continue;
        //}
        //else
        //{
            //we will just add the Assert know and deal with
            //creating with all locals later.
        //    oInfo.replayUnits.add(current_unit);
        //    continue;
        //}

        //check if we know the defs of each local in the unit
        if (allDefsKnown(imm, simpleLocalDefs, current_unit, defUnitsInEnv)){
            if (u instanceof AssignStmt){
                defUnitsInEnv.add(current_unit) //add the known definition to assignment
            }

            oInfo.replayUnits.add(current_unit);
        }
    }

    return oInfo;

}

boolean allDefsKnown(List<Immediate> imm, SimpleLocalDefs simpleLocalDefs, Unit current_unit, HashSet<Unit> defUnitsInEnv) {
    for (Immediate l : imm) {
        if (!(l instanceof Local)) continue; //special case for asserts)
        List<Unit> localDefs = SootHelper.getDefsOfLocal(simpleLocalDefs, current_unit, (Local) l);
        //we only care about one, since we don't support branches
        assert (localDefs.size() == 1); //we only support one

        //returning we don't have all defs of locals in this unit
        for (Unit localDef : localDefs) {
            if (!defUnitsInEnv.contains(localDef)) {
                return false; //we continue in search of asserts
            }
        }
    }

    return true;
}


def hasMultiplePaths(UnitGraph g, Unit u) {

    if (g.getSuccsOf(u).size() > 1)
    {
        return true;
    }
    else if (g.getSuccsOf(u).size() == 0) return false;
    else { hasMultiplePaths(g, g.getSuccsOf(u)[0])}

}


def all_paths(UnitGraph g, Unit u, List path, List paths)
{
    for(c in g.getSuccsOf(u)){
       List newPath = path.collect() {it}
       all_paths(g, c, newPath << u, paths);
    }

    if ( g.getSuccsOf(u).size() == 0){
        paths << path;
    }


}



setSootOptions("com.ser.oraclefinder.testartifacts.Apples", "int add(int)")
String[] sootArguments = ["-process-dir",
        OracleFinderConfiguration.v().getAppTestsSourceFolder(),
        "-cp", OracleFinderConfiguration.v().getSootClassPath()];
soot.Main.main(sootArguments)


