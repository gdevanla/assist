package com.ser.assist.tests.oraclefinder;

import com.ser.assist.oraclefinder.OracleFinderConfiguration;

/**
 * Created with IntelliJ IDEA.
 * User: gdevanla
 * Date: 6/29/13
 * Time: 10:17 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestBase {

    String appBaseFolder = "/Users/gdevanla/Dropbox/private/se_research/myprojects/assist/apps/StateCarver/assist";
    String inputSourceFolder =  "/Users/gdevanla/Dropbox/private/se_research/myprojects/assist/apps/StateCarver/TestArtifacts/src/test/java";
    String sootClassPath = appBaseFolder + "/" + "target/classes" + ":" + inputSourceFolder +
            ":/System/Library/Frameworks/JavaVM.framework/Classes/classes.jar"
            + ":" + "/System/Library/Frameworks/JavaVM.framework/Classes/jce.jar"
            + ":" + "/Users/gdevanla/.m2/repository/com/thoughtworks/xstream/xstream/1.4.4/xstream-1.4.4.jar"
            + ":" + "/Users/gdevanla/.m2/repository/junit/junit/3.8.1/junit-3.8.1.jar";

   // OracleFinderConfiguration config;

    public TestBase(){
        //config = new OracleFinderConfiguration(appBaseFolder, inputSourceFolder, sootClassPath);
    }

}
