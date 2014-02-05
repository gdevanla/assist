package com.ser.oraclefinder.testartifacts;

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



}

