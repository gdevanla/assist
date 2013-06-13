package com.ser.statecarver.core;

/**
 * Created with IntelliJ IDEA.
 * User: gdevanla
 * Date: 6/11/13
 * Time: 6:33 AM
 * To change this template use File | Settings | File Templates.
 */

public class Configuration {

    //This needs to be guiceified
    private String baseFolder = "/tmp";
    private String testName = "";
    private String methodTracerFileName = "/tmp/MethodTracer1.log";
    private String appClassesPrefix = "com.ser.statecarver.test.testartifacts";

    public String getMethodTraceFileName(){
        return methodTracerFileName;
    }

    public String getBaseFolder(){
        return baseFolder;
    }

    public String getAppClassesPrefix(){
        return appClassesPrefix;
    }

}



