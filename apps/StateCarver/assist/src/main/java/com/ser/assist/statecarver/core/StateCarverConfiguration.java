package com.ser.assist.statecarver.core;

import com.google.common.base.Joiner;
import com.google.inject.Singleton;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FilenameUtils;

import javax.swing.plaf.metal.MetalTheme;
import java.util.Iterator;
import java.util.concurrent.locks.Lock;

/**
 * Created with IntelliJ IDEA.
 * User: gdevanla
 * Date: 6/11/13
 * Time: 6:33 AM
 * To change this template use File | Settings | File Templates.
 */

public class StateCarverConfiguration {



    private static class Loader {
        public static StateCarverConfiguration stateCarverConfiguration = new StateCarverConfiguration();
    }

    private Configuration config;
    //private static StateCarverConfiguration stateCarverConfiguration=null;

    public static StateCarverConfiguration v(){
        return Loader.stateCarverConfiguration;
    }

    private StateCarverConfiguration(){
        try {
            config = new PropertiesConfiguration("assist.properties");
        } catch (ConfigurationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            System.out.println("Unable to load config file assist.properties. Please make sure it is in the classpath.");
        }
    }

    private String METHOD_TRACE_FILE_NAME = "MethodTrace.log";

    public String getMethodTraceFileName(){
        System.out.println(config.getString("statecarver.trace_destination"));
        return FilenameUtils.concat(getTraceDestination(), METHOD_TRACE_FILE_NAME);
    }

    public String getTraceDestination(){
        return config.getString("statecarver.trace_destination");
    }

    // The following properties are used during static analysis

    public String getAppClassesPrefix(){
        return config.getString("statecarver.app_classes_prefix");
    }

    public String getProcessDir(){
        return config.getString("statecarver.soot.processdir");
    }

    public String getSootClassPath(){

        Iterator i = config.getKeys();
       // while (i.hasNext()){
          //  Object s = i.next();
          //  System.out.println(s);
          //  System.out.println(config.getString((String)s));
       // }

        return Joiner.on(":").join(config.getString("global.assist_classpath"),
                config.getString("global.xstreamjar"),
                config.getString("global.jcejar"),
                config.getString("global.classesjar"),
                config.getString("statecarver.soot.processdir"));

    }

    public String getSootOutputFolder() {
        return config.getString("statecarver.soot.output_folder");
    }

}



