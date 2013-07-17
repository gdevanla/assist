package com.ser.assist.oraclefinder;

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

public class OracleFinderConfiguration {



    private static class Loader {
        public static OracleFinderConfiguration oracleFinderConfiguration = new OracleFinderConfiguration();
    }

    private Configuration config;
    private static OracleFinderConfiguration oracleFinderConfiguration=null;

    public static OracleFinderConfiguration v(){
        return Loader.oracleFinderConfiguration;
    }

    private OracleFinderConfiguration(){
        try {
            config = new PropertiesConfiguration("assist.properties");
        } catch (ConfigurationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            System.out.println("Unable to load config file assist.properties. Please make sure it is in the classpath.");
        }
    }

    public String getTestsSourceFolder(){
        return config.getString("oraclefinder.tests_folder");
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
                getTestsSourceFolder(),
                config.getString("global.junitjar"));

    }
}
