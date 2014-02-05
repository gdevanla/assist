package com.ser.assist;

import soot.*;
import soot.jimple.*;
import soot.jimple.internal.JimpleLocal;
import soot.options.Options;
import soot.toolkits.graph.ExceptionalUnitGraph;
import soot.toolkits.graph.PseudoTopologicalOrderer;
import soot.toolkits.scalar.LocalDefs;
import soot.toolkits.scalar.SimpleLiveLocals;
import soot.toolkits.scalar.SimpleLocalDefs;
import soot.toolkits.scalar.SmartLocalDefs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gdevanla
 * Date: 6/26/13
 * Time: 6:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class SootHelper {

    private final Body body;
    public SootHelper(Body body){
        this.body = body;
    }

    public static boolean isMethodInvokedInBody(Body body, SootMethod method){
        for(Unit unit:body.getUnits()){
            if (((Stmt)unit).containsInvokeExpr()){
                //Dicey when it comes to static invoke expr.
                InvokeExpr expr = ((Stmt) unit).getInvokeExpr();
                if (expr.getMethod().equals(method))
                    return true;
                }
            }
        return false;
    }

    public static boolean doIKnowEverythingAboutThisAssert(Unit u) {
        return true;
    }

    public static boolean isAssert(Unit u) {
       if (u instanceof InvokeStmt) {
            return ((InvokeStmt) u).getInvokeExpr().getMethod().getName().startsWith("assert");
       }
       return false;
    }

    public static List<Value> getParamsFromAssert(Unit u){

        List params = new ArrayList<Value>();
        StaticInvokeExpr expr = (StaticInvokeExpr)((InvokeStmt)u).getInvokeExpr();

        if (expr.getMethod().getName().contains("assertEquals")){
            if (expr.getArgCount() == 2) {
                params.add(expr.getArg(0));
                params.add(expr.getArg(1));
            }
            else
            {
                params.add(expr.getArg(1));
                params.add(expr.getArg(1));
            }
        }

        return params;

    }

    public Unit getReachingDefStatement(Unit beforeUnit){

        for(Unit unit:body.getUnits()){
            List<ValueBox> valueBoxes = unit.getDefBoxes();
            System.out.println(valueBoxes);
            if ( beforeUnit.equals(unit)){
                System.out.println(beforeUnit);
                System.out.println("equals");
                System.out.println(unit);
            }
        }

        return null;
    }

    public static SootClass getResolvedClass(String className) {
        //Assume Soot scene is currently active
        SootClass klass = Scene.v().forceResolve(className,
                SootClass.BODIES | SootClass.SIGNATURES);
        return klass;
    }

    public static SootMethod getResolvedMethod(String className, String methodSubSignature){
        return getResolvedClass(className).getMethod(methodSubSignature);
    }


    public static boolean doesUnitInvokeMethod(Unit unit, SootMethod m){
        return ((Stmt)unit).containsInvokeExpr() &&
                ((Stmt)unit).getInvokeExpr().getMethod().equals(m);
    }

    public static SootMethod getInvokedMethod(Unit unit, SootMethod m){
        if (((Stmt)unit).containsInvokeExpr() &&
                ((Stmt)unit).getInvokeExpr().getMethod().equals(m)){
            return ((Stmt)unit).getInvokeExpr().getMethod();
        }

        return null;
    }

    public static List<Local> getUseLocalsInUnit(Unit u){
        List<Local> locals = new ArrayList<Local>();
        List<Immediate> imm = getAllImmediates(u);
        for (Immediate i:imm){
            if (i instanceof Local){
                locals.add((Local) i);
            }
        } 
        return locals;
    }

    public static List<Unit> getDefsOfLocal(SimpleLocalDefs defs, Unit u, Local l)
    {
        if (defs.hasDefsAt(l, u) ){
            return defs.getDefsOfAt(l, u);
        }
        else
        {
            assert( u instanceof AssignStmt);
            List<Unit> defUnits = new ArrayList<Unit>();
            defUnits.add(u);
            return defUnits;
        }
    }

    public static List<Unit> getDefsOfAllLocals(SimpleLocalDefs simpleLocalDefs, Unit u){
        List<Unit> defs = new ArrayList<Unit>();
        for (Local l: getUseLocalsInUnit(u))
        {
            defs.addAll(getDefsOfLocal(simpleLocalDefs, u, l ));
        }

        return defs;
    }

    public static boolean allLocals(List l){
        for (Object o:l){
            if (!(o instanceof Local)) return false;
        }
        return true;
    }


    public static List<Local> getAllLocals(Unit u){
        List<Immediate> imm = getAllImmediates(u);
        List<Local> locals = new ArrayList<Local>();
        for (Immediate im:imm){
            if ( im instanceof Local){
                locals.add((Local)im);
            }
        }

        if ( u instanceof AssignStmt){
            locals.add((Local)((AssignStmt)u).getLeftOp());
        }

        return locals;

    }

    // Gets only locals on rightOp
    public static List<Immediate> getAllImmediates(Unit u){
        List<Immediate> imm = new ArrayList<Immediate>();
        for (ValueBox vb:u.getUseBoxes()){
            getAllImmediates(vb, imm);
        }

        return imm;
    }

    public static void getAllImmediates(ValueBox vb, List<Immediate> imm){
        if (vb.getValue() instanceof Immediate){
            imm.add((Immediate)vb.getValue());
            return;
        }

        for (ValueBox v:vb.getValue().getUseBoxes()){
            getAllImmediates(v, imm);
        }



    }

    /*
     Abstracted from
     http://opensourcejavaphp.net/java/soot/soot/jimple/toolkits/scalar/ConstantPropagatorAndFolder.java.html
     */
    public static void propogateConstants(Body b){
        StmtBody stmtBody = (StmtBody)b;
        int numPropagated = 0;

        if (Options.v().verbose())
            G.v().out.println("[" + stmtBody.getMethod().getName() +
                    "] Propagating and folding constants...");

        ExceptionalUnitGraph unitGraph = new ExceptionalUnitGraph(stmtBody);
        LocalDefs localDefs;

        localDefs = new SmartLocalDefs(unitGraph, new SimpleLiveLocals(unitGraph));

        // Perform a constant/local propagation pass.
        Iterator stmtIt = (new PseudoTopologicalOrderer()).newList(unitGraph,false).iterator();

        // go through each use box in each statement
        while (stmtIt.hasNext()) {
            Stmt stmt = (Stmt) stmtIt.next();

            // propagation pass
            Iterator useBoxIt = stmt.getUseBoxes().iterator();
            ValueBox useBox;

            while (useBoxIt.hasNext()) {
                useBox = (ValueBox) useBoxIt.next();
                if (useBox.getValue() instanceof Local) {
                    Local local = (Local) useBox.getValue();
                    List<Unit> defsOfUse = localDefs.getDefsOfAt(local, stmt);
                    if (defsOfUse.size() == 1) {
                        DefinitionStmt defStmt =
                                (DefinitionStmt) defsOfUse.get(0);
                        if (defStmt.getRightOp() instanceof NumericConstant) {
                            if (useBox.canContainValue(defStmt.getRightOp())) {
                                useBox.setValue(defStmt.getRightOp());
                                numPropagated++;
                            }
                        }
                    }
                }
            }

        }
    }

}
