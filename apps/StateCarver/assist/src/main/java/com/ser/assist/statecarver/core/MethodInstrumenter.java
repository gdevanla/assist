package com.ser.assist.statecarver.core;

import soot.*;
import soot.jimple.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MethodInstrumenter {

    SootClass methodTracerClass = Scene.v().forceResolve("com.ser.assist.statecarver.core.MethodTracer", SootClass.SIGNATURES);
    SootMethod methodTracerWrite = methodTracerClass.getMethodByName("writeMethodTrace");
    SootClass xStreamStateCarverClass = Scene.v().forceResolve(
            "com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver",
            SootClass.SIGNATURES);
    SootMethod xStreamSaveMethod = xStreamStateCarverClass.getMethodByName("saveState");
    SootMethod xStreamSaveMethodPrimitiveInt = xStreamStateCarverClass.getMethodByName("savePrimitiveInt");
    SootMethod xStreamStaticSaveMethod = xStreamStateCarverClass.getMethodByName("saveStaticState");


    Local methodCounter;

    public void instrumentMethod(Body body, String s, Map map) throws Exception {

        if (filter(body))
            return;

        String currentClassName = body.getMethod().getDeclaringClass().getName();
        Stmt stmtToInsertBefore = getStatementToInsertBefore(body.getUnits());
        List<Unit> methodCounterStmts = getMethodCounterStatements(body);
        Stmt methodTraceStmt = getMethodTracerStmt(body, currentClassName);
        List<Unit> parameterSavingStmts = getParameterSavingStatements(body);
       // List<Unit> staticStateSavingStmts = getStaticStateSavingStatements(body);


        //Stmt thisObjectSavingStmt = getParameterSavingStatements(body);
        //Stmt staticFieldsSavingStatement = getParameterSavingStatements(body);

        body.getUnits().insertBefore(methodCounterStmts, stmtToInsertBefore);
        body.getUnits().insertBefore(methodTraceStmt, stmtToInsertBefore);

        if (parameterSavingStmts.size()>0){
            body.getUnits().insertBefore(parameterSavingStmts, stmtToInsertBefore);
        }

       /* if ( staticStateSavingStmts.size()>0){
            body.getUnits().insertBefore(staticStateSavingStmts, stmtToInsertBefore);
        }*/

        if (!(body.getMethod().getReturnType() instanceof VoidType)){
            List<Unit> allReturnStatements = collectReturnStatements(body);
            for (Unit u:allReturnStatements){
                if (!(u instanceof ReturnStmt)) continue;
                ReturnStmt r = (ReturnStmt)u;
                List<ValueBox> retValues = r.getUseBoxes();
                if (retValues.size()>1)
                    throw new Exception("Do not know what do with multiple uses in return statement");

                List<Unit> returnValueSaveStatement = returnValueSaveStatement(body, retValues.get(0));
                body.getUnits().insertBefore(returnValueSaveStatement, u);
            }

        }

    }

    private List<Unit> collectReturnStatements(Body body){
        List<Unit> returnStatements = new ArrayList<Unit>();
        for (Unit u: body.getUnits()){
            if (!(u instanceof ReturnStmt)) continue;
            returnStatements.add(u);
        }
        return returnStatements;
    }

    private List<Unit> returnValueSaveStatement(Body body, ValueBox retValueBox){

        List<Unit> stmts = new ArrayList<Unit>();
        if (Utils.isPrimitive(retValueBox.getValue().getType())){
            return getSaveStmtForPrimitives(body, retValueBox.getValue().toString(), retValueBox.getValue(), "return");
        }
        else
        {

        List<Value> args = new ArrayList<Value>(2);
        args.add(retValueBox.getValue());
        args.add(methodCounter);
        args.add(StringConstant.v("return"));
        args.add(StringConstant.v(retValueBox.getValue().getType().toString()));


        SootMethodRef saveMethodByType = getSaveMethodByType(retValueBox.getValue().getType());
        stmts.add(Jimple.v().newInvokeStmt(Jimple.v().newStaticInvokeExpr(saveMethodByType,
                args)));
        return stmts;
        }
    }

    private SootMethodRef getSaveMethodByType(Type type) {

        if (type.toString().equals("java.lang.int")){
            return xStreamSaveMethodPrimitiveInt.makeRef();
        }
        else
        {
            return xStreamSaveMethod.makeRef();
        }
    }

    private List<Unit> getMethodCounterStatements(Body body){
        Local localMethodCounter = Jimple.v().newLocal("assist_method_counter", LongType.v());
        Local atomicMethodCounterRef = Jimple.v().newLocal("atomic_method_counter_ref",
                RefType.v("java.util.concurrent.atomic.AtomicLong"));
        body.getLocals().add(localMethodCounter);
        body.getLocals().add(atomicMethodCounterRef);

        //TODO: temp, clean this shit up
        methodCounter = localMethodCounter;

        SootClass atomicCounterClass = Scene.v().forceResolve("com.ser.assist.statecarver.core.AtomicCounter", SootClass.SIGNATURES);
        SootField methodCounterField = atomicCounterClass.getFieldByName("methodCounter");
        Unit u1 = Jimple.v().newAssignStmt( atomicMethodCounterRef , Jimple.v().newStaticFieldRef(methodCounterField.makeRef()));

        SootClass atomicLongClass = Scene.v().forceResolve("java.util.concurrent.atomic.AtomicLong", SootClass.SIGNATURES);
        SootMethod getAndIncrementMethod = atomicLongClass.getMethod("long getAndIncrement()");

        Unit u2 = Jimple.v().newAssignStmt(localMethodCounter,
                Jimple.v().newVirtualInvokeExpr(atomicMethodCounterRef, getAndIncrementMethod.makeRef()));

        List<Unit> units = new ArrayList<Unit>();
        units.add(u1);
        units.add(u2);

        return units;

    }

    private String getTempFieldName(SootField f){
        return "fieldRef" + "_" + f.getDeclaringClass() + "_" + f.getName();
    }


    private List<Unit> getParameterSavingStatements(Body body) throws Exception {

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

    private List<Unit> getXStreamSaveStateMethodInvoker(Body body, IdentityStmt unit) throws Exception {
        List<Unit> savingStmts = new ArrayList<Unit>();
        Value arg = ((IdentityStmt)unit).getLeftOp();
        Value rightArg = ((IdentityStmt)unit).getRightOp();

        //TODO: Primitive types
        if (rightArg instanceof ParameterRef){
            if (Utils.isPrimitive(arg.getType())){
                System.out.println(arg);
                savingStmts.addAll(getSaveStmtsForPrimitiveParams(body, unit));
            }
            else{
                List<Value> args = new ArrayList<Value>(2);
                args.add(arg);
                args.add(methodCounter);
                args.add(StringConstant.v(Integer.valueOf(((ParameterRef)rightArg).getIndex()).toString()));
                args.add(StringConstant.v(arg.getType().toString()));
                savingStmts.add(Jimple.v().newInvokeStmt(Jimple.v().newStaticInvokeExpr(xStreamSaveMethod.makeRef(),
                        args)));
            }
        }
        else if (rightArg instanceof ThisRef){
            Value thisArg = ((IdentityStmt)unit).getLeftOp();
            List<Value> args = new ArrayList<Value>(2);
            args.add(thisArg);
            args.add(methodCounter);
            args.add(StringConstant.v("this"));
            args.add(StringConstant.v(thisArg.getType().toString()));
            savingStmts.add(Jimple.v().newInvokeStmt(Jimple.v().newStaticInvokeExpr(xStreamSaveMethod.makeRef(),
                    args)));
        }
        else{
            throw new Exception("Unexpected typ in identity stmt="+ unit.toString());
        }

        return savingStmts;

    }

    private List<Unit> getSaveStmtForPrimitives(Body body, String nonPrimitiveLocalName, Value arg, String paramOrThisOrReturn) {
        List<Unit> primitiveSavingStmts = new ArrayList<Unit>(2);

        Local nonPrimitiveLocal = Utils.getLocalForType( "nonPrimitiveArgLocal_" + nonPrimitiveLocalName, arg.getType());
        body.getLocals().addLast(nonPrimitiveLocal);

        SootMethod  boxingMethod = Utils.getSootMethodsForPrimitiveTypes(arg.getType());
        Stmt nonPrimAssStmt = Jimple.v().newAssignStmt(nonPrimitiveLocal, Jimple.v().newStaticInvokeExpr(boxingMethod.makeRef(), arg));
        primitiveSavingStmts.add(nonPrimAssStmt);

        List<Value> args = new ArrayList<Value>();
        args.add(nonPrimitiveLocal);
        args.add(methodCounter);
        args.add(StringConstant.v(paramOrThisOrReturn));
        args.add(StringConstant.v(arg.getType().toString()));

        primitiveSavingStmts.add(Jimple.v().newInvokeStmt(Jimple.v().newStaticInvokeExpr(xStreamSaveMethod.makeRef(),
                args)));

        return primitiveSavingStmts;
    }

    private List<Unit> getSaveStmtsForPrimitiveParams(Body body, Unit u){
        List<Unit> savingStmts = new ArrayList<Unit>();
        Value arg = ((IdentityStmt)u).getLeftOp();
        ParameterRef rightArg = (ParameterRef)((IdentityStmt)u).getRightOp();
        return getSaveStmtForPrimitives(body, Integer.valueOf(rightArg.getIndex()).toString(), arg, Integer.valueOf(rightArg.getIndex()).toString());
    }

    private String buildMethodStringToSave(Body body, String currentClassName){
        String returnType = body.getMethod().getReturnType().toString();
        System.out.println("Return Type="+returnType );
        returnType = returnType.replace("[]", "\\[\\]");


        System.out.print("SubSignature="+body.getMethod().getSubSignature());
        String subSignatureWithoutReturnType = body.getMethod().getSubSignature().replaceFirst(returnType, "");
        return returnType + " " + subSignatureWithoutReturnType.trim();
    }

    private Stmt getMethodTracerStmt(Body body, String currentClassName) {
        List<Value> args = new ArrayList<Value>();
        args.add(StringConstant.v(buildMethodStringToSave(body, currentClassName)));
        args.add(StringConstant.v(currentClassName));
        args.add(methodCounter);
        return Jimple.v().newInvokeStmt(Jimple.v().newStaticInvokeExpr(methodTracerWrite.makeRef(), args));

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

    private List<Unit> getStaticStateSavingStatements(Body body) {

        List<Unit> savingStmts = new ArrayList<Unit>();
        int i=0;
        for (SootField field : StaticStateOfApp.instance.getStaticFieldList()){
            Type t = field.getType();
            //add a local variable for the static field
            Local local = Jimple.v().newLocal(getTempFieldName(field), field.getType());
            body.getLocals().addLast(local);

            AssignStmt stmt = Jimple.v().newAssignStmt(local , Jimple.v().newStaticFieldRef(field.makeRef()));
            savingStmts.add(stmt);

            if(Utils.isPrimitive(t)){
                savingStmts.addAll(getXStreamStaticSaveStateMethodInvokerForPrimitives(body, field, local));
            }
            else{
                List<Object> args = new ArrayList<Object>();
                args.add(local);
                args.add(methodCounter);
                args.add(StringConstant.v(field.getDeclaringClass().getName()));
                args.add(StringConstant.v(local.getType().toString()));
                args.add(StringConstant.v(field.getName()));
                savingStmts.add(Jimple.v().newInvokeStmt(Jimple.v().newStaticInvokeExpr(xStreamStaticSaveMethod.makeRef(),
                        args)));
            }
        }
        return savingStmts;

    }

    private List<Unit> getXStreamStaticSaveStateMethodInvokerForPrimitives(Body body, SootField field, Local larg) {
        List<Unit> primitiveSavingStmts = new ArrayList<Unit>(2);
        Type t = field.getType();

        Local nonPrimitiveLocal = Utils.getLocalForType("nonPrimitiveStaticVar"+ "_" + field.getName(), t);
        body.getLocals().addLast(nonPrimitiveLocal);

        SootMethod  boxingMethod = Utils.getSootMethodsForPrimitiveTypes(t);
        Stmt nonPrimAssStmt = Jimple.v().newAssignStmt(nonPrimitiveLocal, Jimple.v().newStaticInvokeExpr(boxingMethod.makeRef(), larg));
        primitiveSavingStmts.add(nonPrimAssStmt);

        List<Value> args = new ArrayList<Value>();
        args.add(nonPrimitiveLocal);
        args.add(methodCounter);
        args.add(StringConstant.v(field.getDeclaringClass().getName()));
        args.add(StringConstant.v(nonPrimitiveLocal.getType().toString()));
        args.add(StringConstant.v(larg.getName()));


        for ( Value v:args){
            System.out.println(v);
        }
        primitiveSavingStmts.add(Jimple.v().newInvokeStmt(Jimple.v().newStaticInvokeExpr(xStreamStaticSaveMethod.makeRef(),
                args)));

        return primitiveSavingStmts;
    }




}
