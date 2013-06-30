package com.ser.assist.statecarver.core;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: gdevanla
 * Date: 6/11/13
 * Time: 6:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class MethodTracer {

    public static int counter=0;

    public static void writeMethodTrace(String methodName, String className){
       PrintWriter pw = null;
        try {
            System.out.println("writeMethodTrace called" + methodName + ":" + className);
            pw = new PrintWriter(new BufferedWriter(
                    new FileWriter(
                            new Configuration().getMethodTraceFileName(), true)));
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        counter++;
        pw.write("counter: " + counter + ":" + className + ": " + methodName + "\n");
        pw.close();
    }

    public static int getCurrentMethodCounter(){
        return counter;
    }

    public static String getStateFileForMethod(){
        return String.valueOf(counter);
    }

}
