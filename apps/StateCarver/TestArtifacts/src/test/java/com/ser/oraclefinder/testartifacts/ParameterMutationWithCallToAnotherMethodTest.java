package com.ser.oraclefinder.testartifacts;

import junit.framework.TestCase;


class ShappyApples{

    String shape;

    public ShappyApples(){
        shape = "square";
    }

    public String getMorphedShape(String update){
        return shape+"_"+update;
    }
}

class ShapeChanger{
    public void changeShape(ShappyApples a){
        a.shape = "circle";
    }
}

public class ParameterMutationWithCallToAnotherMethodTest extends TestCase {
    public void testPatternWithMethodCall() {
        /* MUT = changeShape */
        ShappyApples s = new ShappyApples();
        new ShapeChanger().changeShape(s);
        assertEquals("circle_rectangle", s.getMorphedShape("rectangle"));

    }
}