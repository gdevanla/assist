package com.ser.oraclefinder.testartifacts;


import junit.framework.TestCase;


class ColorFullApples{

    int color;

    public ColorFullApples(){
        color = 1;
    }

    public int getColor(){
        return color;
    }


    public int add(int x){
        return x*2;
    }
}

class ColorChanger{
    public void changeColor(ColorFullApples a){
        a.color = 2;
    }
}



public class SimpleParameterMutationPatternTest extends TestCase {


    public void testPatternWithAttributeCall() {
        /* Here MUT is changeColor*/
        ColorFullApples x = new ColorFullApples();
        new ColorChanger().changeColor(x);
        assertEquals(2, x.color);
    }


    public void testPatternWithMethodCall() {
        /* Here MUT is changeColor*/
        ColorFullApples x = new ColorFullApples();
        new ColorChanger().changeColor(x);
        assertEquals(2, x.getColor());
    }
}