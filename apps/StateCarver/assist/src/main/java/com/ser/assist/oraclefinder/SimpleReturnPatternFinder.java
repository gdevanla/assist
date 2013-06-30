package com.ser.assist.oraclefinder;

import soot.Body;
import soot.Local;
import soot.Unit;
import soot.ValueBox;
import soot.jimple.InvokeExpr;
import soot.jimple.Stmt;
import soot.jimple.internal.JimpleLocal;
import soot.toolkits.graph.UnitGraph;
import soot.toolkits.graph.pdg.EnhancedUnitGraph;
import soot.toolkits.scalar.SimpleLocalDefs;

import java.util.LinkedList;
import java.util.List;

public class SimpleReturnPatternFinder{

    public final Body body;
    public final String mutSignature;

    public SimpleReturnPatternFinder(Body body, String mutSignature){
        this.body =  body;
        this.mutSignature = mutSignature;
    }

    public List<Oracle> getAllOccurences(){
        UnitGraph cfg = new EnhancedUnitGraph(body);
        SimpleLocalDefs simpleLocalDefs = new SimpleLocalDefs(cfg);

        LinkedList<Oracle> oraclesFound = new LinkedList<Oracle>();

        //SimpleLocalUses localUses = new SimpleLocalUses(cfg, localVarDefs);

        for (Unit unit: body.getUnits()){

            Stmt stmt = (Stmt)unit;
            if (!stmt.containsInvokeExpr()) continue;

            InvokeExpr expr = (InvokeExpr)stmt.getInvokeExpr();
            if (!expr.getMethod().getName().startsWith("assert")) continue;

            Local local = getLocalUsedInAssert(unit);
            System.out.println(simpleLocalDefs.getDefsOfAt(local, unit));


            if (hasReachingDefinitionToMUT(simpleLocalDefs,unit,local)){
                oraclesFound.add(new Oracle(Oracle.OraclePattern.ASSERT_EXPLICIT_RETURN_VALUE,
                        new Assertion(unit), this.mutSignature));
            }
        }

        return oraclesFound;

    }

    private boolean hasReachingDefinitionToMUT(SimpleLocalDefs simpleLocalDefs, Unit unit, Local local) {
        LinkedList<Unit> reachingLocals = new LinkedList<Unit>();
        List<Unit> reachingUnits = simpleLocalDefs.getDefsOfAt(local, unit);
        reachingLocals.addAll(reachingUnits);

        while (!reachingLocals.isEmpty()){
            Unit u = reachingLocals.remove();
            if (!isSimpleAssignment(u)) continue;

            if (haveWeReachedTheMUTYet(u)){
                System.out.println("Before adding to oracle" + unit);

               return true;
            }

            List<ValueBox> useBoxes = u.getUseBoxes();
            for (ValueBox vb:useBoxes){
                if ( vb.getValue() instanceof JimpleLocal){
                    if (simpleLocalDefs.hasDefsAt((Local)vb.getValue(), u)){
                        reachingLocals.addAll(simpleLocalDefs.getDefsOfAt((Local) vb.getValue(), u));
                    }
                }
            }
        }

        return false;
    }

    private Local getLocalUsedInAssert(Unit unit) {
        Stmt s = (Stmt)unit;
        InvokeExpr expr = ((Stmt) unit).getInvokeExpr();
        if (expr.getMethod().getSignature().equals("<junit.framework.Assert: void assertEquals(int,int)>")){
            List<ValueBox> useBoxes = unit.getUseBoxes();
            for (ValueBox b:useBoxes){
                if (b.getValue().getClass().toString().equals("class soot.jimple.internal.JimpleLocal")){
                    return (Local)b.getValue();
                }
            }
        }

        return null;

    }

    private boolean isSimpleAssignment(Unit u){
        /* here we just look for one expressions that don't involve more
        than one variable.
         */
        List<ValueBox> useBoxes = u.getUseBoxes();

        int count = 0;
        for(ValueBox box:useBoxes){
            System.out.println(box + " .getValue()=" + box.getValue());
            if (box.getValue() instanceof JimpleLocal){
                // System.out.println(box + "is instance of " + (box.getValue() instanceof JimpleLocal));
                count++;
            }
        }

        if (count==1) return true;

        return false;

    }


    private boolean haveWeReachedTheMUTYet(Unit u) {
        Stmt stmt = (Stmt) u;
        if (!stmt.containsInvokeExpr()) {
            return false;
        }

        InvokeExpr expr = stmt.getInvokeExpr();
        if (expr.getMethod().getSignature().equals(mutSignature)){
            return true;
        }
        return false;
    }
}
