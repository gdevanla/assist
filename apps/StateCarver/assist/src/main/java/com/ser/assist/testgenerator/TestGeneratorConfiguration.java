package com.ser.assist.testgenerator;

import com.google.common.base.Joiner;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.util.Iterator;

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

public class TestGeneratorConfiguration {

    private static class Loader {
        public static TestGeneratorConfiguration testGeneratorConfiguration = new TestGeneratorConfiguration();
    }

    private Configuration config;
    private static TestGeneratorConfiguration testGeneratorConfiguration=null;

    public static TestGeneratorConfiguration v(){
        return Loader.testGeneratorConfiguration;
    }

    private TestGeneratorConfiguration(){
        try {
            config = new PropertiesConfiguration("assist.properties");
        } catch (ConfigurationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            System.out.println("Unable to load config file assist.properties. Please make sure it is in the classpath.");
        }
    }

    public String getAppClassPath(){
        return config.getString("testgenerator.app_classpath");
    }
}
