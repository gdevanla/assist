package com.ser.statecarver.testartifacts;

public class TestClassReferringToStaticClass {

    public static DummyStaticClass x = new DummyStaticClass();

    public void referToStaticPropertyOfAnotherClass(int i){
        //int j = DummyStaticClass.x;
    }
}
