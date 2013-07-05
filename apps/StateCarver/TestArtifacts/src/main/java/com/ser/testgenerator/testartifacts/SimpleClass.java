package com.ser.testgenerator.testartifacts;

public class SimpleClass {

    public int sayHello(){
        System.out.println("ClassName:" + this.getClass() + ":" + "MethodName" + this.getClass().getEnclosingMethod());
        return 10;
    }

    public String sayHello(String parameter1){
        System.out.println("ClassName:" + this.getClass() + ":" + "MethodName" + this.getClass().getEnclosingMethod());
        return "Return value:" + parameter1;
    }

    public String sayHello(String parameter1, int parameter2){
        System.out.println("ClassName:" + this.getClass() + ":" + "MethodName" + this.getClass().getEnclosingMethod());
        return "Return value:" + parameter1 + ", " + parameter2;
    }


}
