package com.ser.assist.testgenerator;

import com.ser.assist.AssistConfiguration;

/**
 * Created with IntelliJ IDEA.
 * User: gdevanla
 * Date: 6/11/13
 * Time: 6:33 AM
 * To change this template use File | Settings | File Templates.
 */

public class TestGeneratorConfiguration extends AssistConfiguration {

    public String getIntegrationTestDest() {
        return config.getString("assist.integration_test_destination");
    }

    private static class Loader {
        public static TestGeneratorConfiguration testGeneratorConfiguration = new TestGeneratorConfiguration();
    }

    private static TestGeneratorConfiguration testGeneratorConfiguration=null;

    public static TestGeneratorConfiguration v(){
        return Loader.testGeneratorConfiguration;
    }

    private TestGeneratorConfiguration(){
      super();
    }

}
