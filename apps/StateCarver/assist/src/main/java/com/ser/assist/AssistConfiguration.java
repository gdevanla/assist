package com.ser.assist;


import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FilenameUtils;

/**
 * Created with IntelliJ IDEA.
 * User: gdevanla
 * Date: 7/22/13
 * Time: 1:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class AssistConfiguration {

    private final String traceFolder = "trace";
    private final String METHOD_TRACE_FILE_NAME = "MethodTrace.log";

    //public final static String config_properties_fname = "hellochicago.assist.properties";
    //public final static String config_properties_fname = "jtopas.assist.properties";
    public final static String config_properties_fname = "texplorer.assist.properties";

    protected Configuration config;

    public AssistConfiguration(){
    try {
        //config = new PropertiesConfiguration("assist.properties");
        config = new PropertiesConfiguration(config_properties_fname);
    } catch (ConfigurationException e) {
        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        System.out.println("Unable to load config file assist.properties. Please make sure it is in the classpath.");
    }
    }



    public String getMethodTraceFileName(){
        return FilenameUtils.concat(getTraceDestination(), METHOD_TRACE_FILE_NAME);
    }

    public String getTraceDestination(){
        return FilenameUtils.concat(config.getString("assist.trace_destination"), traceFolder);
    }

    public String getAppSourceFolder() {
        return config.getString("aut.source_folder");
    }

    public String getAppTestsSourceFolder() {
        return config.getString("aut.tests_source_folder");
    }

    public String getAppClasspath() {
        return config.getString("aut.app_classpath");
    }

    public String getAcceptanceTestCommand(){
        return config.getString("acceptancetests.run.command");
    }

    public String getCompileIntegrationTestCommand() {
        return config.getString("compile.integrationtest.command");
    }


    public String getIntegrationTestsRunCommand(){
     return config.getString("integration_tests.run.command");
    }






}
