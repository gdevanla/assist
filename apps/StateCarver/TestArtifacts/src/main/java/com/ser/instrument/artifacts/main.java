package com.ser.instrument.artifacts;

public class Main {

    public void testInstrumentedClass1(){
        InstrumentedClass1 c = new InstrumentedClass1();
        c.methodWithPrimitiveInt(10);
    }

    public static void main(String[] args){
        Main m = new Main();
        m.testInstrumentedClass1();
    }

}
