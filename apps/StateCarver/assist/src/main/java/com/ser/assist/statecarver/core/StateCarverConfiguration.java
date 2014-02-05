package com.ser.assist.statecarver.core;

import com.google.common.base.Joiner;
import com.ser.assist.AssistConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class StateCarverConfiguration extends AssistConfiguration {

    private static class Loader {
        public static StateCarverConfiguration stateCarverConfiguration = new StateCarverConfiguration();
    }

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

    public String getSootOutputFolder() {
        return config.getString("assist.soot.output_folder");
    }

}



