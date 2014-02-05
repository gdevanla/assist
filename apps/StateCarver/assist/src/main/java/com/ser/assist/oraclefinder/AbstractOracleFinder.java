package com.ser.assist.oraclefinder;

import soot.Body;
import soot.Local;
import soot.Unit;
import soot.ValueBox;
import soot.jimple.InvokeExpr;
import soot.jimple.Stmt;
import soot.jimple.internal.JimpleLocal;

import java.util.ArrayList;
import java.util.List;

public class AbstractOracleFinder {

    public final Body body;
    public final String mutSignature;

    public AbstractOracleFinder(Body body, String mutSignature){
        this.body =  body;
        this.mutSignature = mutSignature;
    }

    protected List<Local> getLocalUsedInAssertEquals(Unit unit) {
        List<Local> localsUsed = new ArrayList<Local>();
        Stmt s = (Stmt)unit;
        InvokeExpr expr = ((Stmt) unit).getInvokeExpr();
        if (expr.getMethod().getSignature().contains("junit.framework.Assert: void assertEquals")){
            System.out.println("Found assertEquals=" + expr.getUseBoxes());
            List<ValueBox> useBoxes = unit.getUseBoxes();
            for (ValueBox b:useBoxes){
                System.out.println(b + "," + b.getValue() + "," + b.getValue().getClass());
                if (b.getValue().getClass().toString().equals("class soot.jimple.internal.JimpleLocal")){
                    localsUsed.add((Local)b.getValue());
                }
            }
        }

        return localsUsed;
    }

    protected boolean hasInvokeExpr(Unit u) {
        if (((Stmt)u).containsInvokeExpr()) return true;

        return false;

    }

    protected boolean hasInvokeExprWithNoArgs(Unit u) {
        if (!((Stmt)u).containsInvokeExpr()) return false;

        InvokeExpr invokeExpr = ((Stmt) u).getInvokeExpr();
        if (invokeExpr.getMethod().getParameterCount() == 0){
            return true;
        }
        return false;
    }

    protected boolean hasInvokeExprsWithArgs(Unit u) {
        if (!((Stmt)u).containsInvokeExpr()) return false;

        InvokeExpr invokeExpr = ((Stmt) u).getInvokeExpr();
        if (invokeExpr.getMethod().getParameterCount() > 0){
            return true;
        }
        return false;
    }

    protected boolean haveWeReachedTheMUTYet(Unit u) {
        Stmt stmt = (Stmt) u;
        if (!stmt.containsInvokeExpr()) {
            return false;
        }

        InvokeExpr expr = stmt.getInvokeExpr();

        //Wrong: we should just do method equals check
        if (expr.getMethod().getSignature().contains(mutSignature)){
            return true;
        }
        return false;
    }

    protected boolean isSimpleAssignment(Unit u){
        /* here we just look for one expressions that don't involve more
        than one variable.
         */

        if (hasInvokeExpr(u)) return false;

        List<ValueBox> useBoxes = u.getUseBoxes();

        int count = 0;
        for(ValueBox box:useBoxes){
            System.out.println(box + " .getValue()=" + box.getValue());
            if (box.getValue() instanceof JimpleLocal){
                count++;
            }
        }

        if (count==1) return true;

        return false;

    }


}
