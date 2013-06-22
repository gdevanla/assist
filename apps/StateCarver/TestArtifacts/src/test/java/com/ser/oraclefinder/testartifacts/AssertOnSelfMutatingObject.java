package com.ser.oraclefinder.testartifacts;


import junit.framework.TestCase;

class Oranges{
    int count = 10;
    public void add(int x){
        count = count + 10;
    }

    public int getDouble(){
        return count * 10;
    }
}

public class AssertOnSelfMutatingObject  extends TestCase {
    public void testPatternWithMethodCall() {
        /* Here MUT is add*/
        Oranges o = new Oranges();
        o.add(10);
        assertEquals(200, o.getDouble());
    }

    public void testPatternWithAttributeCall() {
        /* Here MUT is add*/
        Oranges o = new Oranges();
        o.add(10);
        assertEquals(20, o.count);
    }
}

