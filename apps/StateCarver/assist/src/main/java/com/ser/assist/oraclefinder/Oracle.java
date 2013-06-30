package com.ser.assist.oraclefinder;

import soot.SootField;
import soot.SootMethod;

public class Oracle {

    OraclePattern pattern;
    Assertion assertion;
    String mutSignature;


    public Oracle(OraclePattern pattern, Assertion assertion, String mutSignature){
        this.pattern = pattern;
        this.assertion = assertion;
        this.mutSignature = mutSignature;
    }

    //String mutSignature;
    //SootField observedField;

    public enum OraclePattern {
        // r = x.MUT(), assert(r)
        ASSERT_EXPLICIT_RETURN_VALUE,

        // assert(r.MUT)
        ASSERT_IMPLICIT_RETURN_VALUE,

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

}
