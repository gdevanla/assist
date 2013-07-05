package com.ser.statecarver.testartifacts;

/**
 * Created with IntelliJ IDEA.
 * User: gdevanla
 * Date: 7/4/13
 * Time: 7:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestInheritedClasses {

    public void test(){
        SubClass s = new SubClass();

        s.subclass_method();
        s.subclassinterface_method();

        s.parentclass_method();
        s.parentclass_method_final();
        s.parentinterface_method();


    }
}
