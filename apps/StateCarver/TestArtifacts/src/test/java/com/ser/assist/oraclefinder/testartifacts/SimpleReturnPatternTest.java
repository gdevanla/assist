package com.ser.assist.oraclefinder.testartifacts;


import junit.framework.TestCase;


class Apples{
    public Apples(){

    }

    public int add(int x){
        return x*2;
    }
}

public class SimpleReturnPatternTest extends TestCase {

    public void testSimpleReturnPattern() {
        /* Here MUT is add*/
        Apples x = new Apples();
        int y = x.add(10);
        assertEquals(20, y);
    }
}
