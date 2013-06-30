package com.ser.oraclefinder.testartifacts;


import junit.framework.TestCase;


class Apples{
    public Apples(){

    }

    public int add(int x){
        return x*2;
    }
}

public class SimpleReturnPatternTest1 extends TestCase {

    public void testSimpleReturnPattern1() {
        /* Here MUT is add*/
        Apples x = new Apples();
        int z = x.add(10); //+ Math.abs(10);
        int y = z;
        assertEquals(20, y);
    }
}
