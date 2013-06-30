package com.ser.assist.statecarver.core;

import soot.*;
import soot.jimple.*;
import soot.jimple.internal.JimpleLocal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class MethodInstrumenter {

    SootClass methodTracerClass = Scene.v().forceResolve("MethodTracer", SootClass.SIGNATURES);
    SootMethod methodTracerWrite = methodTracerClass.getMethodByName("writeMethodTrace");
    SootClass xStreamStateCarverClass = Scene.v().forceResolve(
            "XStreamStateCarver",
            SootClass.SIGNATURES);
    SootMethod xStreamSaveMethod = xStreamStateCarverClass.getMethodByName("saveState");

    public void instrumentMethod(Body body, String s, Map map) {

        if (filter(body))
            return;

        String currentClassName = body.getMethod().getDeclaringClass().getName();
        Stmt stmtToInsertBefore = getStatementToInsertBefore(body.getUnits());
        Stmt methodTraceStmt = getMethodTracerStmt(body, currentClassName);
        List<Unit> parameterSavingStmts = getParameterSavingStatements(body);
        List<Unit> staticStateSavingStmts = getStaticStateSavingStatements(body);

        //Stmt thisObjectSavingStmt = getParameterSavingStatements(body);
        //Stmt staticFieldsSavingStatement = getParameterSavingStatements(body);

        body.getUnits().insertBefore(methodTraceStmt, stmtToInsertBefore);
        body.getUnits().insertBefore(parameterSavingStmts, stmtToInsertBefore);

        if ( staticStateSavingStmts.size()>0){
            System.out.println("has static fields " + body);
            body.getUnits().insertBefore(staticStateSavingStmts, stmtToInsertBefore);
        }
        else
        {
           // System.out.println(body + " has no static fields");
        }

    }

    private String getTempFieldName(SootField f){
        return "fieldRef" + "_" + f.getDeclaringClass() + "_" + f.getName();
    }

    private List<Unit> getStaticStateSavingStatements(Body body) {

        List<Unit> savingStmts = new ArrayList<Unit>();
        int i=0;
        for ( SootField field : StaticStateOfApp.instance.getStaticFieldList()){
            Type t = field.getType();
            //add a local variable for the static field
            Local local = Jimple.v().newLocal(getTempFieldName(field), field.getType());
            body.getLocals().addLast(local);

            AssignStmt stmt = Jimple.v().newAssignStmt(local , Jimple.v().newStaticFieldRef(field.makeRef()));
            savingStmts.add(stmt);

            if(Utils.isPrimitive(t)){
                savingStmts.addAll(getXStreamSaveStateMethodInvokerForPrimitives(body, local));
            }
            else{
                List<Object> args = new ArrayList<Object>();
                args.add(local);
                args.add(StringConstant.v(local.getName()));
                savingStmts.add(Jimple.v().newInvokeStmt(Jimple.v().newStaticInvokeExpr(xStreamSaveMethod.makeRef(),
                        args)));
            }
        }
        return savingStmts;

    }

    private List<Unit> getParameterSavingStatements(Body body) {

        List<Unit> localArgSavingStmts = new ArrayList<Unit>();
        for (Unit unit : body.getUnits()) {
            if(unit instanceof IdentityStmt){
                localArgSavingStmts.addAll(getXStreamSaveStateMethodInvoker(body, (IdentityStmt) unit));
            }
            else{
                //once we stop seeing identity statements, we don't care.
                break;
            }
        }

        return localArgSavingStmts;
    }

    private List<Unit> getXStreamSaveStateMethodInvoker(Body body,IdentityStmt unit) {
        List<Unit> savingStmts = new ArrayList<Unit>();
        Local arg  = (JimpleLocal) ((IdentityStmt)unit).getLeftOp();
        if (Utils.isPrimitive(arg.getType())){
               savingStmts.addAll(getXStreamSaveStateMethodInvokerForPrimitives(body, arg));
        }
        else
        {
            List<Object> args = new ArrayList<Object>();
            args.add(arg);
            args.add(StringConstant.v(arg.getName()));
            savingStmts.add(Jimple.v().newInvokeStmt(Jimple.v().newStaticInvokeExpr(xStreamSaveMethod.makeRef(),
                    args)));
        }

        return savingStmts;
    }

    private List<Unit> getXStreamSaveStateMethodInvokerForPrimitives(Body body, Local arg) {
        List<Unit> primitiveSavingStmts = new ArrayList<Unit>(2);
        Type t = arg.getType();
        SootMethod  boxingMethod = Utils.getSootMethodsForPrimitiveTypes(t);
        Local nonPrimitiveLocal = Utils.getLocalForType("nonPrimitiveArgLocal"+ "_" + arg.getName(), t);
        body.getLocals().addLast(nonPrimitiveLocal);

        Stmt nonPrimAssStmt = Jimple.v().newAssignStmt(nonPrimitiveLocal, Jimple.v().newStaticInvokeExpr(boxingMethod.makeRef(), arg));
        primitiveSavingStmts.add(nonPrimAssStmt);

        List<Object> args = new ArrayList<Object>();
        args.add(nonPrimitiveLocal);
        args.add(StringConstant.v(nonPrimitiveLocal.getName()));

        primitiveSavingStmts.add(Jimple.v().newInvokeStmt(Jimple.v().newStaticInvokeExpr(xStreamSaveMethod.makeRef(),
                args)));

        return primitiveSavingStmts;
    }


    private Stmt getMethodTracerStmt(Body body, String currentClassName) {
        List<Object> args = new ArrayList<Object>();
        args.add(body.getMethod().getName());
        args.add(currentClassName);
        return Jimple.v().newInvokeStmt(Jimple.v().newStaticInvokeExpr(methodTracerWrite.makeRef(),
                StringConstant.v(body.getMethod().getName()), StringConstant.v(currentClassName)));
    }

    public boolean filter(Body body){
        //TODO: Revisit this
        if(body.getMethod().isPrivate()) return true;
        if(body.getMethod().getName().contains("init")) return true;
        if(body.getMethod().getName().contains("main")) return true;
        return false;
    }

    private Stmt getStatementToInsertBefore(PatchingChain<Unit> units) {
        Stmt s = null;
        Iterator<Unit> stmtIt = units.snapshotIterator();
        while (stmtIt.hasNext()) {
            s = (Stmt) stmtIt.next();
            if(s instanceof IdentityStmt)
                continue;
            break;
        }
        return s;
    }



}
