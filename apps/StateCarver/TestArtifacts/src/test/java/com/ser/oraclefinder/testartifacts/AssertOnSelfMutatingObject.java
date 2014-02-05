package com.ser.oraclefinder.testartifacts;


import junit.framework.TestCase;

public class AssertOnSelfMutatingObject  extends TestCase {
    public void testPatternWithMethodCall() {
        /* Here MUT is add*/
        Oranges o = new Oranges();
        o.add(10);
        assertEquals(200, o.getDouble());
    }

    public void testPatternWithAttributeCall() {
        /* Here MUT is add*/
        Oranges o = new Oranges();
        o.add(10);
        assertEquals(20, o.count);
    }
}

