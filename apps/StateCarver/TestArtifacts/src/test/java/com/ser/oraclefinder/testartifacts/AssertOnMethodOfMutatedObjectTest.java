package com.ser.oraclefinder.testartifacts;

import com.ser.instrument.artifacts.StaticContainer;
import com.ser.statecarver.testartifacts.TestPojo;
import junit.framework.TestCase;

public class AssertOnMethodOfMutatedObjectTest extends TestCase {


    public void testPatternWithMethodCall() {
        /* Here MUT is add*/
      Cantaloupe c = new Cantaloupe();

      new OrangeCountIncrementer().addCantaloupe(c, 10);
      assertEquals(20, c.getCount());
    }


    public void testPatternWithAttributeCall() {
        /* Here MUT is add*/
        Cantaloupe c = new Cantaloupe();

        new OrangeCountIncrementer().addCantaloupe(c, 10);
        assertEquals(20, c.count);
    }

    public void testPatternWithAttributeCallAndStaticAttr() {
        /* Here MUT is add*/
        Cantaloupe c = new Cantaloupe();

        new OrangeCountIncrementer().addCantaloupe(c, 20);
        int x = StaticContainer.y_static;
        assertEquals(x, c.count);


    }

    public void testPatternWithAttributeCallAndStaticMethodCall() {
        /* Here MUT is add*/
        Cantaloupe c = new Cantaloupe();

        new OrangeCountIncrementer().addCantaloupe(c, 30);
        int x = TestPojo.getPrintStaticValues();
        int y = TestPojo.getStaticValues(x, 20);
        assertEquals(y, c.count);


    }

    public void testPatternWithAttributeCallAndInstanceCallWithParams() {
        /* Here MUT is add*/
        Cantaloupe c = new Cantaloupe();

        new OrangeCountIncrementer().addCantaloupe(c, 30);
        new OrangeCountIncrementer(TestPojo.getPrintStaticValues(), 10).addCantaloupe(c, 30);
        int x = TestPojo.getPrintStaticValues();
        int y = TestPojo.getStaticValues(x, 20);
        boolean tt = c instanceof Cantaloupe;
        assertEquals(y, c.count);



    }
}

