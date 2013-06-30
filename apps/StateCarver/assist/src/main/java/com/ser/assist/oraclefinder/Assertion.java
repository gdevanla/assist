package com.ser.assist.oraclefinder;


import soot.Unit;

public class Assertion {
    public enum AssertionType{
        AssertTrue,
        AssertFalse,
        AssertEquals,
        AssertNull,
        AssertNotNull,
        AssertArrayEquals,
        AssertSame,
        AssertNotSame,
        AssertThat,
        Fail
    }

    public final Unit unit;
    public Assertion(Unit unit){
        this.unit = unit;
    }



}