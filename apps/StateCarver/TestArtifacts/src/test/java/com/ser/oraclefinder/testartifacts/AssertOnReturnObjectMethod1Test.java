package com.ser.oraclefinder.testartifacts;

import junit.framework.TestCase;

class Kiwi{
    public Kiwi(){

    }

    public int add(int x){
        return x*2;
    }

    public Kiwi anotherInstance(){
        return new Kiwi();
    }

    public int addNoArgs(){
        return 100;
    }
}

public class AssertOnReturnObjectMethod1Test extends TestCase {

    public void testTest1(){
        /* Here MUT is add*/
        Kiwi x = new Kiwi();
        Kiwi y = x.anotherInstance();
        int z = y.addNoArgs();
        assertEquals(20, z);
    }

    public void testTest2(){
        /* Here MUT is add*/
        Kiwi x = new Kiwi();
        int y = x.add(12121);
        int z = y;
        assertEquals(20, z);
    }

    public void testTest3(){
        /* Here MUT is add*/
        Kiwi x = new Kiwi();
        int y = x.add(12121);
        Integer z = y;
        assertEquals(20, z.intValue());
    }

    public void testTest4(){
        /* Here MUT is add*/
        Kiwi x = new Kiwi();
        int y = x.add(12);
        Integer z = Integer.valueOf(y);
        assertEquals(20, z.intValue());
    }

    public void testTest5(){
        /* Here MUT is add*/
        Kiwi x = new Kiwi();
        assertEquals(20, x.add(12));
    }


}
