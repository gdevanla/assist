package com.ser.assist.oraclefinder;

import com.google.common.base.Joiner;
import com.ser.assist.AssistConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class OracleFinderConfiguration extends AssistConfiguration {

    private static class Loader {
        public static OracleFinderConfiguration oracleFinderConfiguration = new OracleFinderConfiguration();
    }

    private static OracleFinderConfiguration oracleFinderConfiguration=null;

    public static OracleFinderConfiguration v(){
        return Loader.oracleFinderConfiguration;
    }

    private OracleFinderConfiguration(){
        try {
            //config = new PropertiesConfiguration("assist.properties");
            config = new PropertiesConfiguration(AssistConfiguration.config_properties_fname);

        } catch (ConfigurationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            System.out.println("Unable to load config file assist.properties. Please make sure it is in the classpath.");
        }
    }

    public String getSootClassPath(){

        return Joiner.on(":").join(config.getString("global.assist_classpath"),
                config.getString("global.xstreamjar"),
                config.getString("global.jcejar"),
                config.getString("global.classesjar"),
                getAppTestsSourceFolder(),
                getAppClasspath(),
                config.getString("global.junitjar"));
    }
}
