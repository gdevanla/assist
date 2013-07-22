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

    static AssistConfiguration config = new AssistConfiguration();

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        runStateInstrumentor();

        //run acceptance test
        runAcceptanceTests();
        //runSequenceMiner();
        runIntegrationTestGenerator();
        compileIntegrationTests();
        runIntegrationTests();
    }

    private static void runIntegrationTestGenerator() throws IOException, ClassNotFoundException {
        //run integration test generator
        String clazzName = "com.ser.hellochicago.HelloChicago";
        String mutSignature = "com.ser.hellochicago.HelloChicago: java.lang.String appendString(java.lang.String)";
        int sequenceNumber = 0;

        if (!TestClassGenerator.run(clazzName, "mutee", mutSignature,
                sequenceNumber)){
                System.out.println("****Failed at Test generation");
            System.exit(-1);
        }
    }

    private static void runStateInstrumentor() {
        if (!StateInstrumenter.run()){
            System.out.println("****Failed at State instrumenter");
            System.exit(-1);
        }
    }

    public static void runAcceptanceTests() throws IOException {
        System.out.println("Running Acceptance Test:");
        ProcessBuilder pb = new ProcessBuilder(config.getAcceptanceTestCommand());
        pb.start();
        System.out.println("Complete running acceptance tests");
    }

    public static void compileIntegrationTests() throws IOException {
        System.out.println("Running Acceptance Test");
        ProcessBuilder pb = new ProcessBuilder(config.getCompileIntegrationTestCommand());
        pb.start();
        System.out.println("Complete compiling new integration tests");
    }

    public static void runIntegrationTests() throws IOException {
        System.out.println("Running Integration Tests Test");
        ProcessBuilder pb = new ProcessBuilder(config.getIntegrationTestsRunCommand());
        pb.start();
        System.out.println("Complete running integration tests");
    }

}
