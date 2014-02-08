package com.ser.assist.tests.oraclefinder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import soot.options.Options;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class AssertOnReturnValue extends TestBase {
/*
    @Test
    public void testAssertOnReturnValue1(){
       Core c = new Core("Apples", "com.ser.oraclefinder.testartifacts.Apples: int add(int)");
       c.runAnalysis(Options.output_format_J, true, "com.ser.oraclefinder.testartifacts.SimpleReturnPatternTest1");
       assertEquals(4, c.oraclesFound.size());
       //for (Oracle o:c.oraclesFound){
       //    assertEquals(o.getMethodCallTreeInTest(), "");
       //}
    }

    @Test
    public void testAssertOnReturnValue2(){
        Core c = new Core("Apples1", "<com.ser.oraclefinder.testartifacts.Apples1: int add(int)>");
        c.runAnalysis(Options.output_format_J, true, "com.ser.oraclefinder.testartifacts.SimpleReturnPatternTest2");
        assertEquals(2, c.oraclesFound.size());
        assertEquals(c.oraclesFound.get(0).testMethodName, "testSimpleReturnPattern2");
        assertEquals(c.oraclesFound.get(0).getMethodCallTreeInTest(), "");
    }

    @Test
    public void testAssertOnReturnValue3(){
        Core c = new Core("Apples1", "<com.ser.oraclefinder.testartifacts.Apples1: int add(int)>");
        c.runAnalysis(Options.output_format_J, true, "com.ser.oraclefinder.testartifacts.EmptyTest");
        assertEquals(0, c.oraclesFound.size());
    }

    @Test
    public void testAssertOnReturnObjectsMethod1(){
        Core c = new Core("Kiwi", "<com.ser.oraclefinder.testartifacts.Kiwi: com.ser.oraclefinder.testartifacts.Kiwi anotherInstance()>");
        c.runAnalysis(Options.output_format_J, true, "com.ser.oraclefinder.testartifacts.AssertOnReturnObjectMethod1Test");
        assertEquals(1, c.oraclesFound.size());
        assertEquals(c.oraclesFound.get(0).testMethodName, "testTest1");
        assertEquals(c.oraclesFound.get(0).getMethodCallTreeInTest(), "addNoArgs()");
    }


    @Test
    public void testAssertOnReturnObjectsMethod2(){
        Core c = new Core("Kiwi", "<com.ser.oraclefinder.testartifacts.Kiwi: int add(int)>");
        c.runAnalysis(Options.output_format_J, true, "com.ser.oraclefinder.testartifacts.AssertOnReturnObjectMethod1Test");
        assertEquals(2, c.oraclesFound.size());
        assertEquals(c.oraclesFound.get(0).testMethodName, "testTest2");
        assertEquals(c.oraclesFound.get(1).testMethodName, "testTest5");
    }

    @Test
    public void testMethodsWithMultipleAsserts(){
        Core c = new Core("Kiwi2", "<com.ser.oraclefinder.testartifacts.Kiwi2: com.ser.oraclefinder.testartifacts.Kiwi2 anotherInstance()>");
        c.runAnalysis(Options.output_format_J, true, "com.ser.oraclefinder.testartifacts.TestsMethodHavingMultipleAsserts");
     //   assertEquals(4, c.oraclesFound.size());
     //   assertEquals(c.oraclesFound.get(0).testMethodName, "testTest1");
     //   assertEquals(c.oraclesFound.get(1).testMethodName, "testTest1");
     //   assertEquals(c.oraclesFound.get(2).testMethodName, "testTest2");
     //   assertEquals(c.oraclesFound.get(3).testMethodName, "testTest2");

     //   assertEquals(c.oraclesFound.get(0).getMethodCallTreeInTest(), "addNoArgs()");
    }*/
}
