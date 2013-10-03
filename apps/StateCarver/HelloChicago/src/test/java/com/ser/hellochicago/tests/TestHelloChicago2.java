package com.ser.hellochicago.tests;

import com.ser.hellochicago.HelloChicago;
import junit.framework.TestCase;

public class TestHelloChicago2 extends TestCase {
    public void testAppendString1(){
        HelloChicago c = new HelloChicago("City of", 10 );
        String s = c.appendString(" Big Shoulders");
        // assertEquals(s, "City of Big Shoulders");

        System.out.println(HelloChicago.counter++);
    }

    public void testAppendString2(){
        HelloChicago c = new HelloChicago("City of", 10 );
        //   assertEquals(c.appendString(" Big Shoulders"), "City of Big Shoulders");
        System.out.println(c.counter++);
    }

    public void testAppendString3(){
        HelloChicago c = new HelloChicago("City of", 10 );
        String s = c.appendString(" Big something else");
        // assertEquals("City of Big something else", s);
        System.out.println(c.counter++);
    }

    public void testIncrement1(){
        HelloChicago c = new HelloChicago("City of", 10 );
        int x =c.increaseCount(100);
        int y = x;
        //  assertEquals(x, y);
        System.out.println(c.counter++);
    }
}
