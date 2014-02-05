package com.ser.oraclefinder.testartifacts;

import junit.framework.TestCase;


public class SimpleReturnPattern2Test extends TestCase {

    public void testSimpleReturnPattern2() {
        /* Here MUT is add*/
        Apples1 x = new Apples1();
        int z = (int)Math.random();
        if (z > 100){
            z = x.add(10); //+ Math.abs(10);
        }
        else
        {
            z = x.add(100);
        }

        int y = z;
        int a = 200;
        assertEquals(a, y);
    }

}
