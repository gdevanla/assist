package com.ser.oraclefinder.testartifacts;

import junit.framework.TestCase;


public class ParameterMutationWithCallToAnotherMethodTest extends TestCase {
    public void testPatternWithMethodCall() {
        /* MUT = changeShape */
        ShappyApples s = new ShappyApples();
        new ShapeChanger().changeShape(s);
        assertEquals("circle_rectangle", s.getMorphedShape("rectangle"));

    }
}