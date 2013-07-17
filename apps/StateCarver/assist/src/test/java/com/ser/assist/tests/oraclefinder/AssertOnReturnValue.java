package com.ser.assist.tests.oraclefinder;

import static org.junit.Assert.assertEquals;

import com.ser.assist.oraclefinder.Core;
import com.ser.assist.oraclefinder.OracleFinderConfiguration;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import soot.options.Options;

@RunWith(JUnit4.class)
public class AssertOnReturnValue extends TestBase {

  /* @Test
    public void testAssertOnReturnValue1(){
        Core c = new Core(config, "Apples", "<com.ser.oraclefinder.testartifacts.Apples: int add(int)>");
        c.runAnalysis(Options.output_format_J, true, "com.ser.oraclefinder.testartifacts.SimpleReturnPatternTest1");
        assertEquals(1, c.oraclesFound.size());
    }

    @Test
    public void testAssertOnReturnValue2(){
        Core c = new Core(config, "Apples1", "<com.ser.oraclefinder.testartifacts.Apples1: int add(int)>");
        c.runAnalysis(Options.output_format_J, true, "com.ser.oraclefinder.testartifacts.SimpleReturnPatternTest2");
        assertEquals(1, c.oraclesFound.size());
    }

    @Test
    public void testAssertOnReturnValue3(){
        Core c = new Core(config, "Apples1", "<com.ser.oraclefinder.testartifacts.Apples1: int add(int)>");
        c.runAnalysis(Options.output_format_J, true, "com.ser.oraclefinder.testartifacts.EmptyTest");
        //assertEquals(1, c.oraclesFound.size());
    }*/

    @Test
    public void testAssertOnReturnObjectsMethod1(){
         Core c = new Core("Kiwi", "<com.ser.oraclefinder.testartifacts.Kiwi: com.ser.oraclefinder.testartifacts.Kiwi anotherInstance()>");
         c.runAnalysis(Options.output_format_J, true, "com.ser.oraclefinder.testartifacts.AssertOnReturnObjectMethod1Test");
       // assertEquals(1, c.oraclesFound.size());
    }

    @Test
    public void testAssertOnReturnObjectsMethod2(){
        Core c = new Core("Kiwi", "<com.ser.oraclefinder.testartifacts.Kiwi: int add(int)>");
        c.runAnalysis(Options.output_format_J, true, "com.ser.oraclefinder.testartifacts.AssertOnReturnObjectMethod1Test");
        // assertEquals(1, c.oraclesFound.size());
    }
}
