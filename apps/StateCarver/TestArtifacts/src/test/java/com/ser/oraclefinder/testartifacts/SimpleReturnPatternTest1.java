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

    public void testSimpleReturnPattern2() {
        /* Here MUT is add*/
        Apples x = new Apples();
        int z = x.add(10); //+ Math.abs(10);
        int y = z;
        int a = 20;
        assertEquals(a, y);
    }

    public void testSimpleReturnPattern3() {
        /* Here MUT is add*/
        Apples x = new Apples();
        int z = x.add(10); //+ Math.abs(10);
        int aa = x.add(20);
        int y = z;
        int a = 20;
        assertEquals(z, aa);
    }



}
