package com.ser.assist.statecarver.core;

import java.io.*;

public class MethodTracer {

    public static int counter=0;

    public static void writeMethodTrace(String methodName, String className, long methodCounter){
       PrintWriter pw = null;
        try {
            System.out.println("writeMethodTrace called" + methodName + ":" + className + ":" + methodCounter);
            pw = new PrintWriter(new BufferedWriter(
                    new FileWriter(
                            StateCarverConfiguration.v().getMethodTraceFileName(), true)));
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        pw.write("counter: " + methodCounter + ":" + className + ": " + methodName + "\n");
        pw.close();
    }
}
