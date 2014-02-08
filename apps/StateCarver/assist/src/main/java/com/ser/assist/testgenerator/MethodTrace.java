package com.ser.assist.testgenerator;



public class MethodTrace {

    public final int level;
    public final int counter;
    public final String className;
    public final String methodName;
    public int methodIndex;
    public final int objectId; //hashcode

    public MethodTrace(int level, int counter, String className,
                       String methodName, int objectId){
        this.level = level;
        this.counter =  counter;
        this.className = className;
        this.methodIndex = -1;
        this.methodName = methodName;
        this.objectId = objectId;
    }

    public MethodTrace(int level, int counter, String className,
                       String methodName, int objectId, int methodIndex){
        this(level, counter, className, methodName, objectId);

        this.methodIndex = methodIndex;
    }



}
