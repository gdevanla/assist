package com.ser.assist.testgenerator

import com.ser.assist.SootHelper
import com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TestGenerator
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
import soot.coffi.element_value
import soot.jimple.AssignStmt
import soot.jimple.Constant
import soot.jimple.InstanceFieldRef
import soot.jimple.InstanceInvokeExpr
import soot.jimple.InvokeExpr
import soot.jimple.NewExpr
import soot.jimple.ReturnStmt
import soot.jimple.ReturnVoidStmt
import soot.jimple.SpecialInvokeExpr
import soot.jimple.StaticFieldRef
import soot.jimple.StaticInvokeExpr
import soot.jimple.Stmt
import soot.options.Options
import soot.toolkits.graph.BriefUnitGraph
import soot.toolkits.graph.UnitGraph
import soot.toolkits.graph.pdg.EnhancedUnitGraph
import soot.toolkits.scalar.SimpleLocalDefs

import java.util.regex.Pattern

class Core {

    def muts
    def sm_mt
    def sm_oinfos
    def init = false

    public Core(List<MethodTrace> muts){
        this.muts = muts;
        sm_mt = [:].withDefault {[]}
        sm_oinfos = [:].withDefault {[]}

    }

    public constructMaps(){
        muts.each() { MethodTrace mt ->
            sm_mt[SootHelper.getResolvedMethod(mt.className, mt.methodName)].add(mt)
        }

        init = true
    }

    //entry point for Soot
    public void processMethod(Body body, SimpleLocalDefs simpleLocalDefs){
        if (!init) {constructMaps()}
        //Collect all oracles
        sm_mt.keySet().each {
            def oinfos = processMethod(body, simpleLocalDefs, it);
            sm_oinfos[it].addAll(oinfos)


            oinfos.each() {OracleReplayInfo r -> println "New sequence" ; println r.method; println r.mut;
                r.replayUnits.each() { println it }
            }
        }

       // sm_mt.entrySet().collect() { sm_mt ->
          //  sm_oinfos.get(sm_mt.key).collect { [sm_mt.value, it.value] }
        //}

       // oinfos.each() {replay(it)};


    }

    public processMethod(Body body, SimpleLocalDefs simpleLocalDefs, SootMethod mut){

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

        return oinfos


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

            //check if we know the defs of each local in the unit
            if (allDefsKnown(imm, simpleLocalDefs, current_unit, defUnitsInEnv)){
                if (current_unit instanceof AssignStmt){
                    defUnitsInEnv.add(current_unit) //add the known definition to assignment
                }

                oInfo.replayUnits.add(current_unit);
            }
        }

        return oInfo;

    }


    def hasMultiplePaths(UnitGraph g, Unit u) {

        if (g.getSuccsOf(u).size() > 1)
        {
            return true;
        }
        else if (g.getSuccsOf(u).size() == 0) return false;
        else { hasMultiplePaths(g, g.getSuccsOf(u)[0])}

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

    //sequences of form [[[oinfo:mt1] [oinfo:mt2]...], //first sequence
    //                   [mt1
    public generateTests(sequences) {

        def codeSnippets = []
        sequences.each() { seq ->
            def objectId = -1
            HashSet<String> env = new HashSet<String>()
            List<String> instructions = new ArrayList<String>()
            seq.each { e ->
                if (objectId == e.value.objectId) {
                    //TODO: based on results we will try this.
                    //def (vars, instr) = generateTests(mt)
                }
                else
                {
                    def (vars, instr) = generateTestSnippet(e.value, e.key)
                    env.addAll(vars)
                    instructions.addAll(instr)
                }
            }
            codeSnippets << [env, instructions]
        }

        return codeSnippets;
    }



    def generateTestSnippet(MethodTrace mt, OracleReplayInfo oInfo)
    {
        //Create defs
        HashSet<String> vars = new HashSet<>();
        Unit mutUnit = oInfo.replayUnits[0];

        oInfo.replayUnits.drop(0).each(){
            def alllocals = SootHelper.getAllImmediates(it).findAll() { it instanceof Local };
            alllocals.each() { vars.add(it.getName()); vars.add(((Local)it).getName() + "_clone")}

            if ( it instanceof AssignStmt){
                String varname = ((Local)it.getDefBoxes().get(0).value).toString()
                vars.add(varname)
                vars.add(varname + "_clone")
            }
        }

        //AssignStmt, InvokeStmt, RetStmt, ReturnStmt,
        //AssignStmt
        List<String> statements = []

        def mutUnits = generateStatementForMut(mt, oInfo);

        //consolidate all vars
        vars.addAll(mutUnits[0])
        //vars.each() { statements.add("def " + it)}

        //mut statement
        statements.addAll(mutUnits[1])
        //remaining statements
        oInfo.replayUnits.drop(1).each() {
            statements.addAll(generateStatement(it))
        }

        println "Output Test for Test Method =" + oInfo.method;
        vars.each() { println "def " + it }
        statements.each() { println it}

        return [vars, statements]
    }


    def generateStatement(Unit u){

        List<String> statements = []

        switch (u) {
            case {((Stmt)u).containsInvokeExpr() }:

                InvokeExpr expr = ((Stmt)u).getInvokeExpr();
                switch(expr) {
                    case {expr instanceof SpecialInvokeExpr}:
                        String specialInvokeFormat = "%s = new %s(%s)"
                        String type =  ((Local)((SpecialInvokeExpr)expr).getBase()).getType();
                        List<Value> argLocals = ((SpecialInvokeExpr)expr).getArgs()
                        String args = argLocals.join(",")
                        List<String> argLocals_clone = argLocals.collect() {
                            if (it instanceof Local) { ((Local)it).getName() + "_clone"} else { ((Constant)it).toString() }
                        }
                        String argsClone = argLocals_clone.join(",")

                        String u1 = String.format(specialInvokeFormat, ((Local)((SpecialInvokeExpr)expr).getBase()).getName(), type, args)
                        String u2 = String.format(specialInvokeFormat, ((Local)((SpecialInvokeExpr)expr).getBase()).getName() + "_clone", type, argsClone)


                        statements.add(u1)
                        statements.add(u2)
                        break

                    case { expr instanceof InstanceInvokeExpr }:  //handles interface and virtual
                        String base  = ((Local)((InstanceInvokeExpr)expr).getBase()).getName()
                        SootMethod method = ((InstanceInvokeExpr)expr).getMethod()
                        List<Value> argLocals = ((InstanceInvokeExpr)expr).getArgs()
                        String args = argLocals.join(",")
                        //(argLocals.inject() { "," + it.getName() }).getAt(0..1)
                        List<String> argLocals_clone = argLocals.collect() {
                            if (it instanceof Local) { ((Local)it).getName() + "_clone"} else { ((Constant)it).toString() }
                        }
                        String argsClone = argLocals_clone.join(",")

                        String u1 = String.format("%s.%s(%s)", base, method.getName(), args)
                        String u2 = String.format("%s.%s(%s)", base + "_clone", method.getName(), argsClone)

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
                            def params = SootHelper.getParamsFromAssert(u);
                            def param1 = params.get(0)
                            def param2 = params.get(1)
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
                        else {
                            String clazz  = ((StaticInvokeExpr)expr).getMethod().getDeclaringClass().toString();
                            SootMethod staticMethod = ((StaticInvokeExpr)expr).getMethod()
                            List<Value> argLocals = ((StaticInvokeExpr)expr).getArgs()
                            String args = argLocals.join(",")

                            List<String> argLocals_clone = argLocals.collect() {
                                if (it instanceof Local) { ((Local)it).getName() + "_clone"} else { ((Constant)it).toString() }
                            }
                            String argsClone = argLocals_clone.join(",")

                            //(argLocals.inject() { "," + it.getName() }).getAt(0..1)

                            String u1 = String.format("%s.%s(%s)", clazz, method.getName(), args)
                            String u2 = String.format("%s.%s(%s)", clazz, method.getName(), argsClone)

                            if ( u instanceof AssignStmt){
                                u1 = ((Local)((AssignStmt)u).getLeftOp()).getName() + " = " + u1
                                u2 =  ((Local)((AssignStmt)u).getLeftOp()).getName() + "_clone" + " = " + u2
                            }

                            statements.add(u1)
                            statements.add(u2)
                            break
                        }

                        break
                }
                break;
            case {u instanceof ReturnStmt || u instanceof ReturnVoidStmt ||
                    u instanceof AssignStmt && ((AssignStmt)u).getRightOp() instanceof NewExpr}:
                statements.add("//" + u.toString());
                break;
            case { ((Stmt)u).containsFieldRef() && u instanceof AssignStmt}:
                def processOp = { f , suffix ->
                    switch (f) {
                        case { f instanceof InstanceFieldRef}:
                            ((Local)f.getUseBoxes().get(0).getValue()).getName() + suffix + "." + f.getField().getName()
                            break
                        case {f instanceof StaticFieldRef}:
                            ((StaticFieldRef)f).getFieldRef().declaringClass().getName() + "." +
                                    ((StaticFieldRef)f).getFieldRef().name();
                            break;
                        default:
                            ((Local)f).getName() + suffix
                    }
                }

                String u1 =   processOp(((AssignStmt)u).getLeftOp(), "") + " = " + processOp(((AssignStmt)u).getRightOp(), "")
                String u2 =  processOp(((AssignStmt)u).getLeftOp(), "_clone") + " = " + processOp(((AssignStmt)u).getRightOp(), "_clone")

                statements.add(u1)
                statements.add(u2)
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

    def generateStatementForMut(MethodTrace mt, OracleReplayInfo oInfo){

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
        def state1_var = ((Local)base).getName();
        vars.add(state1_var)
        statements.add(String.format(loadStatementTemplate, state1_var, XStreamStateCarver.getStateFileName(mt.counter)))

        //clone
        def state2_var = state1_var + "_clone"
        vars.add(state2_var)
        statements.add(String.format(loadStatementTemplate, state2_var, XStreamStateCarver.getStateFileName(mt.counter)))

        List<String> paramVar = (0..<method.getParameterCount()).collect() {
            Immediate im = mutUnitExpr.getArg(it)
            if (im instanceof Local){
                ((Local)im).getName();
            }
            else {
                VariableNameGenerator.getNextName("param_" + it)
            }
        }

        paramVar.each() { vars.add(it); vars.add(it + "_clone") }
        paramVar.eachWithIndex { String entry, int i ->
            statements.add(String.format(loadStatementTemplate, entry, XStreamStateCarver.getParamFileName(mt.counter, i )))
            //statements.add(String.format("%s = %s", entry, entry + "_clone")
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
                    XStreamStateCarver.getReturnFileName(mt.counter) )
            statements.addAll([u1, u2])
        }

        //all set the cloned params, since they may have changed when passed by ref
        paramVar.eachWithIndex { String entry, int i ->
            statements.add(String.format("%s = %s", entry + "_clone", entry))
        }

        return [vars, statements]

    }

    def static product(l){
        def p = { l1, l2 ->
            l1.inject([]) { acc, i ->
                acc + l2.inject([]) { acc1, j ->
                    if ( i instanceof List) { acc1 << i + j}
                    else { acc1 << [i, j]}}}}

        l.inject() { acc, it -> p(acc, it) }
    }

    public createTests() {
        def mt_oinfos = muts.collect([]) { mut ->
            def oinfos = sm_oinfos[SootHelper.getResolvedMethod(mut.className, mut.methodName)]
            oinfos.inject([:]) { acc, it -> acc[it] = mut; acc }
        }

        // println "mt_oinfos = "
        //println mt_oinfos

        //println "Product"
        def sequences = product(mt_oinfos)
        //println sequences

        def codeSnippets = generateTests(sequences)

        def allTests = [:]
        codeSnippets.eachWithIndex { e, i ->
            def testCode = []
            def (var, instr) = e
            def (testName, testMethodName) = [ "Generated${i}Test", "testGenerated${i}"]
            testCode << "package com.ser.instrument.testartifacts"
            testCode << "public class ${testName} extends GroovyTestCase {"
            testCode << "public void ${testMethodName}() {"
            var.each { testCode << "def " + it}
            instr.each { testCode <<  it }
            testCode << "} //end of test"
            testCode << "} //end of class"
            allTests[testName] = testCode
        }

        return allTests
    }

    public writeTests(allTests) {
        String fileLocation = "/Users/gdevanla/Dropbox/private/se_research/myprojects/assist/apps/StateCarver/TestArtifacts/src/test/java/com/ser/instrument/testartifacts/"
        allTests.entrySet().each { e ->
            new File("/${fileLocation}/${e.key}.groovy").withWriter { out ->
                    e.value.each() {
                        out.writeLine(it)
                    }
                }
            }
        }

    public static void main(String[] args){

        def muts = [new MethodTrace(28,24,"com.ser.oraclefinder.testartifacts.Apples","int add(int)", 100),
                new MethodTrace(28,23,"com.ser.oraclefinder.testartifacts.Apples","java.util.List getListOfApples()", 100),
                 new MethodTrace(28,22,"com.ser.oraclefinder.testartifacts.Apples","int[] getArrayOfApples()", 100),
                new MethodTrace(28,13,"com.ser.oraclefinder.testartifacts.Cantaloupe","int getCount()", 100),
                new MethodTrace(28,2,"com.ser.oraclefinder.testartifacts.OrangeCountIncrementer",
                        "void addCantaloupe(com.ser.oraclefinder.testartifacts.Cantaloupe,int)", 100),
                new MethodTrace(28,3,"com.ser.oraclefinder.testartifacts.OrangeCountIncrementer",
                       "void addCantaloupe(com.ser.oraclefinder.testartifacts.Cantaloupe,int)", 100),
        ]

        final Core c = new Core(muts)

        //Collect the oracles
        setSootOptions(c);
        String[] sootArguments = ["-process-dir",
                TestGeneratorConfiguration.v().getAppTestsSourceFolder(),
                "-cp", TestGeneratorConfiguration.v().getSootClassPath()];
        soot.Main.main(sootArguments)
        def allTests = c.createTests()
        c.writeTests(allTests)

    }



    def static setSootOptions(Core c){
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
                    UnitGraph cfg = new EnhancedUnitGraph(body);
                    SimpleLocalDefs simpleLocalDefs = new SimpleLocalDefs(cfg);
                    c.processMethod(body, simpleLocalDefs);
            }
        }));
    }











}
