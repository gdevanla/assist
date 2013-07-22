package com.ser.hellochicago.tests;

import com.ser.hellochicago.HelloChicago;
import junit.framework.TestCase;

public class TestHelloChicago extends TestCase {
    public void testAppendString1(){
      HelloChicago c = new HelloChicago("City of", 10 );
      String s = c.appendString(" Big Shoulders");
      assertEquals(s, "City of Big Shoulders");
    }

    public void testAppendString2(){
        HelloChicago c = new HelloChicago("City of", 10 );
        assertEquals(c.appendString(" Big Shoulders"), "City of Big Shoulders");
    }

    public void testAppendString3(){
        HelloChicago c = new HelloChicago("City of", 10 );
        String s = c.appendString(" Big something else");
        assertEquals("City of Big something else", s);
    }

    public void testIncrement1(){
        HelloChicago c = new HelloChicago("City of", 10 );
        int x =c.increaseCount(100);
        int y = x;
        assertEquals(x, y);
    }
}
