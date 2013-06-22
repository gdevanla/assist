package com.ser.statecarver.testartifacts;

/**
 * Created with IntelliJ IDEA.
 * User: gdevanla
 * Date: 6/13/13
 * Time: 5:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestClassReferringToStaticClass {

    public static DummyStaticClass x = new DummyStaticClass();

    public void referToStaticPropertyOfAnotherClass(int i){
        //int j = DummyStaticClass.x;
    }
}
