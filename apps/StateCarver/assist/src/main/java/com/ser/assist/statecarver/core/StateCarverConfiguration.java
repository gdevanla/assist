package com.ser.assist.statecarver.core;

import com.google.common.base.Joiner;
import com.google.inject.Singleton;
import com.ser.assist.Assist;
import com.ser.assist.AssistConfiguration;
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

public class StateCarverConfiguration extends AssistConfiguration {

    private static class Loader {
        public static StateCarverConfiguration stateCarverConfiguration = new StateCarverConfiguration();
    }


    //private static StateCarverConfiguration stateCarverConfiguration=null;

    public static StateCarverConfiguration v(){
        return Loader.stateCarverConfiguration;
    }

    private StateCarverConfiguration(){
        try {
            //config = new PropertiesConfiguration("assist.properties");
            config = new PropertiesConfiguration(AssistConfiguration.config_properties_fname);
        } catch (ConfigurationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            System.out.println("Unable to load config file assist.properties. Please make sure it is in the classpath.");
        }
    }



    // The following properties are used during static analysis

    public String getAppClassesPrefix(){
        return config.getString("aut.app_classes_prefix");
    }

    public String getProcessDir(){
       return getAppSourceFolder();
    }

    public String getSootClassPath(){

        return Joiner.on(":").join(config.getString("global.assist_classpath"),
                config.getString("global.xstreamjar"),
                config.getString("global.jcejar"),
                config.getString("global.classesjar"),
                getProcessDir());
    }

    public String getSootOutputFolder() {
        return config.getString("assist.soot.output_folder");
    }

}



