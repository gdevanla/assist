package com.ser.oraclefinder.testartifacts;

import junit.framework.TestCase;


class Apples1 {
    public Apples1(){
    }

    public int add(int x){
        return x*2;
    }
}

public class SimpleReturnPatternTest2 extends TestCase {

    public void testSimpleReturnPattern2() {
        /* Here MUT is add*/
        Apples1 x = new Apples1();
        int z = (int)Math.random();
        if (z > 100){
            z = x.add(10); //+ Math.abs(10);
        }
        else
        {
            z = x.add(20);
        }

        int y = z;
        assertEquals(20, y);
    }

}
