package com.ser.assist.oraclefinder.testartifacts;

import junit.framework.TestCase;


class Cantaloupe{
    int count = 10;

    public int getCount(){
        return count;
    }
}

class OrangeCountIncrementer{
    public void addCantaloupe(Cantaloupe c, int x){
        c.count += x;
    }
}

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

