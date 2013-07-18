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

public class SimpleReturnPatternFinder extends AbstractOracleFinder{

    public SimpleReturnPatternFinder(Body body, String mutSignature){
       super(body, mutSignature);
    }

    public List<Oracle> getAllOccurences(){
        UnitGraph cfg = new EnhancedUnitGraph(body);
        SimpleLocalDefs simpleLocalDefs = new SimpleLocalDefs(cfg);

        LinkedList<Oracle> oraclesFound = new LinkedList<Oracle>();

        //SimpleLocalUses localUses = new SimpleLocalUses(cfg, localVarDefs);

        for (Unit unit: body.getUnits()){

            Stmt stmt = (Stmt)unit;
            if (!stmt.containsInvokeExpr()) continue;

            InvokeExpr expr = stmt.getInvokeExpr();
            if (!expr.getMethod().getName().startsWith("assert")) continue;

            System.out.println(unit);
            System.out.println(unit.getUseBoxes());
            List<Local> locals = getLocalUsedInAssertEquals(unit);
//           System.out.println(simpleLocalDefs.getDefsOfAt(local, unit));

            for(Local local:locals){
                if (hasReachingDefinitionToMUT(simpleLocalDefs,unit,local)){
                    oraclesFound.add(new Oracle(
                            body.getMethod().getDeclaringClass().getName(),
                            body.getMethod().getName(),
                            Oracle.OraclePattern.ASSERT_EXPLICIT_RETURN_VALUE,
                            new Assertion(unit), this.mutSignature, null));
                }
            }
        }

        return oraclesFound;

    }

    private boolean hasReachingDefinitionToMUT(SimpleLocalDefs simpleLocalDefs, Unit unit, Local local) {
        LinkedList<Unit> reachingLocals = new LinkedList<Unit>();
        System.out.println(local);
        if (simpleLocalDefs.hasDefsAt(local, unit)){
            reachingLocals.addAll(simpleLocalDefs.getDefsOfAt(local, unit));
        }

        while (!reachingLocals.isEmpty()){
            Unit u = reachingLocals.remove();
            if (haveWeReachedTheMUTYet(u)){
                System.out.println("Before adding to oracle" + unit);
               return true;
            }

            if (!isSimpleAssignment(u)) continue;
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







}
