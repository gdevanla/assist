package com.ser.assist.oraclefinder;

/**
 * Created with IntelliJ IDEA.
 * User: gdevanla
 * Date: 6/23/13
 * Time: 5:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class OracleFinderConfiguration {

    public final String appBaseFolder; /*base of this class*/
    public final String inputSourceFolder;  /*base of this inputSourceFolder*/
    public final String sootClasspath;

    public OracleFinderConfiguration(String appBaseFolder,
                                     String inputSourceFolder,
                                     String sootClasspath){

        this.appBaseFolder = appBaseFolder;
        this.inputSourceFolder = inputSourceFolder;
        this.sootClasspath = sootClasspath;


    }



}
