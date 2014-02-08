package com.ser.assist;

import com.ser.assist.statecarver.core.StateInstrumenter;
//import com.ser.assist.testgenerator.TestClassGenerator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class MutId{
    final String clazzName;
    final String mutSignature;
    final int sequenceNumber;

    public MutId(String clazzName, String mutSignature, int sequenceNumber){
        this.clazzName = clazzName;
        this.mutSignature = mutSignature;
        this.sequenceNumber = sequenceNumber;
    }
}

public class Assist {

    static AssistConfiguration config = new AssistConfiguration();

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        runStateInstrumentor();

        //run acceptance test
        //runAcceptanceTests();
        //runSequenceMiner();

       // List<MutId> methodTuples = runSequenceMiner();
       // runIntegrationTestGenerator(methodTuples);

        //compileIntegrationTests();
        //runIntegrationTests();
    }

    private static List<MutId> runSequenceMiner() throws IOException {

        //counter: 3:com.ser.hellochicago.HelloChicago: int com.ser.hellochicago.HelloChicago.increaseCount(int)

        List<MutId> methodTuples = new ArrayList<MutId>();
        String[] methods = com.google.common.io.Files.toString(new File(config.getMethodTraceFileName()), Charset.defaultCharset()).split("\n");
        for (String m:methods){
            String[] data = m.split(":");
            int counter = Integer.valueOf(data[1].trim());
            String clazzName = data[2].trim();
            String mutSignature = data[2].trim() + ": " + data[3].trim();
            methodTuples.add(new MutId(clazzName, mutSignature, counter));
        }

       return methodTuples;

    }

    private static void runIntegrationTestGenerator(List<MutId> methodTuples) throws IOException, ClassNotFoundException {
        //run integration test generator
        String clazzName = "com.ser.hellochicago.HelloChicago";
        String mutSignature = "com.ser.hellochicago.HelloChicago: java.lang.String appendString(java.lang.String)";
        int sequenceNumber = 0;

        for (MutId methodTuple:methodTuples){
       /* if (!TestClassGenerator.run(methodTuple.clazzName, "mutee", methodTuple.mutSignature,
                methodTuple.sequenceNumber)){
                System.out.println("****Failed at Test generation");
            System.exit(-1);
        }*/
        }
    }

    private static void runStateInstrumentor() throws IOException {
        if (!StateInstrumenter.run()){
            System.out.println("****Failed at State instrumenter");
            System.exit(-1);
        }
    }

    private static void runProcess(ProcessBuilder pb) throws IOException {
        Process p = pb.start();
        InputStream ip = p.getInputStream();

        Scanner sc = new Scanner(ip);
        while (sc.hasNextLine()) {
            System.out.println(sc.nextLine());
        }

    }

    public static void runAcceptanceTests() throws IOException {
        System.out.println("Running Acceptance Test:");
        runProcess(new ProcessBuilder(config.getAcceptanceTestCommand()).redirectErrorStream(true));
        System.out.println("Complete running acceptance tests");
    }

    public static void compileIntegrationTests() throws IOException {
        System.out.println("Running Acceptance Test");
        runProcess(new ProcessBuilder(config.getCompileIntegrationTestCommand()).redirectErrorStream(true));
        System.out.println("Complete compiling new integration tests");
    }

    public static void runIntegrationTests() throws IOException {
        System.out.println("Running Integration Tests");
        runProcess(new ProcessBuilder(config.getIntegrationTestsRunCommand()).redirectErrorStream(true));
        System.out.println("Complete running integration tests");
    }

}
