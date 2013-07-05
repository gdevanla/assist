package com.ser.oraclefinder.testartifacts;


import junit.framework.TestCase;


class Y{
    public int x;
    public String s;

    public Y(int x, String s){
        this.x = x;
        this.s = s;
    }
}

class X{
    public static Object loadState(String fileName){
        return new Y(10, "aa");
    }
}


public class EmptyTest extends TestCase {

    public void testEmptyTest() {

    }

    public void testSaveAndRetrieve(){
        //SimpleClassWithState s = new SimpleClassWithState(10, "hey");
        Y s = (Y)X.loadState("/tmp/0.xml");
        System.out.println(s.x + ":" + s.s);
    }

}
