package com.ser.assist.statecarver.core;

import com.google.inject.internal.util.$AsynchronousComputationException;
import org.apache.commons.io.FileUtils;
import sun.org.mozilla.javascript.internal.EcmaError;

import java.io.*;

public class MethodTracer {

    //create the trace folders
    {
    try {
        //dirty way to create a new file
        new FileWriter(
                StateCarverConfiguration.v().getMethodTraceFileName(), false);
        FileUtils.cleanDirectory(new File(StateCarverConfiguration.v().getTraceDestination()));
    }
    catch (IOException e) {
        System.out.println("Unable to create directory to write trace files\n");
        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    }
    }

    private static final MethodTracer methodTracer = new MethodTracer();
    private PrintWriter pw;

    private MethodTracer(){

        try
        {
        pw = new PrintWriter(new BufferedWriter(
                new FileWriter(
                        StateCarverConfiguration.v().getMethodTraceFileName(), true)));
        pw.write("Write something here\n");
        //pw.flush();
        }
        catch (IOException ex){
            System.out.println("Unable to create file to trace method calls\n");
            ex.printStackTrace();
        }

        //Register shutdown hook to close this file handle.
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Closing Method Tracer handle from inside shutdown hook\n");
                if (pw == null) {
                    //TODO: Add logger here.
                    System.out.println("pw is null");
                }
                else {
                    pw.write("Completed tracing all methods. Closing handle now");
                    pw.close();
                }
            }
        }));

    }

    public static void writeMethodTrace(String methodName, String className, long methodCounter){
        int level = (new Throwable()).getStackTrace().length;
        //int level = 10;
        String s =  "counter: " + methodCounter + ":" + className + ": " + methodName + ": level = " + level + "\n";
        System.out.println("In writeMethodTrace " + s);
        methodTracer.pw.write(s);
        //methodTracer.pw.flush();
    }

}
