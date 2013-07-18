package com.ser.assist.oraclefinder;

import org.apache.commons.lang.StringUtils;
import soot.jimple.Stmt;

public class Oracle {

    public final String testMethodClass;
    public final String testMethodName;
    public final OraclePattern pattern;
    public final Assertion assertion;
    public final String mutSignature;
    public final UnitWrapper uw;

    public Oracle(String testMethodClass, String testMethodName, OraclePattern pattern, Assertion assertion, String mutSignature, UnitWrapper uw){
        this.testMethodName = testMethodName;
        this.testMethodClass = testMethodClass;
        this.pattern = pattern;
        this.assertion = assertion;
        this.mutSignature = mutSignature;
        this.uw = uw;
    }

    public String getAssertionMethod(){
        return ((Stmt)this.assertion).getInvokeExpr().getMethod().getName();
    }

    public String getMutSignature(){
        return mutSignature;
    }

    public String getMethodCallTreeInTest(){
        if (uw==null){
            return "";
        }
        else
        {
            return uw.getCallTree();
        }
    }

    public String generateAssertStatement(String expectedValueVariableName, String actualValueVariableName){
        String methodCallTree = getMethodCallTreeInTest();
        if (StringUtils.isNotEmpty(methodCallTree)){
            expectedValueVariableName = expectedValueVariableName + "." + methodCallTree;
        }

        return String.format("assertEquals(%s, %s)", actualValueVariableName, expectedValueVariableName);

    }

    //String mutSignature;
    //SootField observedField;

    public enum OraclePattern {
        // r = x.MUT(), assert(r)
        ASSERT_EXPLICIT_RETURN_VALUE,

        // assert(r.MUT)
        ASSERT_ON_RETURN_OBJECTS_METHOD,

        //r.MUT(x), assert(x)
        ASSERT_MODIFIED_OBJECTS_VALUE,

        //r.MUT(x), assert(x.someMethod)
        ASSERT_MODIFIED_OBJECTS_METHOD

    };


   /* public String toString(){
        if(observerMethodSignature != null)
            return pattern.toString() + " " + assertion.toString() + " " + observerMethodSignature;
        else if(observedField != null)
            return pattern.toString() + " " + assertion.toString() + " " + observedField.toString();
        else
            return pattern.toString() + " " + assertion.toString();
    }*/

    public static boolean containsOracle(java.util.List<Oracle> oracleList, String oracleString){
        for (Oracle oracle : oracleList) {
            if(oracle.toString().equals(oracleString))
                return true;
        }
        return false;
    }

    public String getAssertMethodUsed(){
        return "";
    }

    public String getAssertArgsUser(){
        return "";
    }





}
