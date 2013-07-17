package com.ser.assist;

import com.ser.assist.statecarver.core.StateCarverConfiguration;
import com.ser.assist.statecarver.core.StateInstrumenter;
import com.ser.assist.testgenerator.TestClassGenerator;
import com.ser.assist.testgenerator.TestGeneratorConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import soot.PackManager;
import soot.Transform;
import soot.options.Options;

import java.io.IOException;

public class Assist {

    public static Configuration config;
    static{
        try {
            config = new PropertiesConfiguration("assist.properties");
        } catch (ConfigurationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


    public static void runAcceptanceTests() throws IOException {
        ProcessBuilder pb = new ProcessBuilder(config.getString("acceptancetest.command"));
        pb.start();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        if (!StateInstrumenter.run()){
            System.out.println("****Faile at State instrumenter");
            System.exit(-1);
        }
        //run acceptance test

         runAcceptanceTests();

        //run integration test generator
        String clazzName = "com.ser.statecarver.testartifacts.TestPojo";
        String mutSignature = "int[] com.ser.statecarver.testartifacts.TestPojo.newadd2(int)";
        int sequenceNumber = 1;
        if (!com.ser.assist.testgenerator.TestClassGenerator.run(clazzName, "mutee", mutSignature,
                sequenceNumber)){
                System.out.println("****Failed at Test generation");
            System.exit(-1);
        }
    }
}
