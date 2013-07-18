package com.ser.oraclefinder.testartifacts;

import junit.framework.TestCase;

class Kiwi2{
    public Kiwi2(){

    }

    public int add(int x){
        return x*2;
    }

    public Kiwi2 anotherInstance(){
        return new Kiwi2();
    }

    public int addNoArgs(){
        return 100;
    }
}

public class TestsMethodHavingMultipleAsserts extends  TestCase {

    public void testTest1(){
        /* Here MUT is add*/
        Kiwi2 x = new Kiwi2();
        Kiwi2 y = x.anotherInstance();
        int z = y.addNoArgs();
        assertEquals(20, z);
        assertEquals(25, y);
    }


    public void testTest2(){
        // Here MUT is add
        Kiwi2 x = new Kiwi2();
        Kiwi2 y = x.anotherInstance();
        Kiwi2 a = x.anotherInstance();
        int z = y.addNoArgs();
        assertEquals(20, z);
        assertEquals(25, a);
    }

}
