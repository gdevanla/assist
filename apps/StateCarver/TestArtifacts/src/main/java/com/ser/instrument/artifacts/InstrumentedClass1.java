package com.ser.instrument.artifacts;


public class InstrumentedClass1 {

    private int private_member = 100;
    public int public_member = 200;
    public StaticContainer sc = new StaticContainer(); //static variant referred in class

    private String s = "private string in Instrumented Class1";

    public int methodWithPrimitiveInt(int x){
        System.out.println(SingletonClass.s);  //static variant referred in method
        int y = x + (int)Math.random();
        System.out.println("Called methodWithPrimitiveInt");
        methodCallAtSecondLevel();
        System.out.println("Completed calling methodCallAtSecondLevel");
        return y;
    }

    public int methodCallAtSecondLevel() {
        System.out.println("This method called from first level");
        return (int)Math.random();
    }

}
