package com.ser.assist.testgenerator;


import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.ser.assist.oraclefinder.Oracle;
import org.apache.commons.lang.StringUtils;
import soot.options.Options;

import javax.sound.midi.SysexMessage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class ClassDefinition{
    public final String testClassNum;

    public final String TEST_CLASS_NUM = "$TEST_CLASS_NUM$";

    public ClassDefinition(String testClassNum){
        this.testClassNum = testClassNum;
    }

    public String generateClassDefintion(String template){
         return template.replace(TEST_CLASS_NUM, this.testClassNum);
    }
}

class MethodDefinition{
    public final String mutName;
    public final int testmethodNum;
    public final int sequenceNumber;
    public final String clazzName;
    public final String basePathofStateFiles;
    public final boolean mutReturns;
    public final String mutSignature;

    public final List<String> assertStatements;

    public final String MUT = "$MUT$";
    public final String TEST_METHOD_NUM = "$TEST_METHOD_NUM$";
    public final String MUT_SIGNATURE = "$MUT_SIGNATURE$";
    public final String CLASS_DECLARING_MUT = "$CLASS_DECLARING_MUT$";
    public final String METHOD_TRACE_SEQUENCE_NUMBER = "$METHOD_TRACE_SEQUENCE_NUMBER$";
    public final String BASE_PATH = "$BASE_PATH$";
    public final String METHOD_RETURNS = "$METHOD_RETURNS$";
    public final String ASSERT_STATEMENTS_GOES_HERE = "$ASSERT_STATEMENTS_GOES_HERE$";
    public final String METHOD_RETURN_TYPE = "$METHOD_RETURN_TYPE$";


    public MethodDefinition(String mutSignature, String mutName, String clazzName, String basePathofStateFiles,
                            int testmethodNum, int sequenceNumber, boolean mutReturns, List<String> assertStatements){

        this.mutName = mutName;
        this.testmethodNum = testmethodNum;
        this.sequenceNumber = sequenceNumber;
        this.clazzName = clazzName;
        this.basePathofStateFiles = basePathofStateFiles;
        this.mutReturns = mutReturns;
        this.mutSignature = mutSignature;

        this.assertStatements = assertStatements;
    }

    public final String generateMethodDefinition(String template) throws ClassNotFoundException {

       System.out.println("Joining the asserts" + Joiner.on(";\n").join(assertStatements));

       return template.replace(MUT, this.mutName).replace(TEST_METHOD_NUM, Integer.valueOf(testmethodNum).toString())
               .replace(MUT_SIGNATURE, this.mutSignature)
               .replace(CLASS_DECLARING_MUT, this.clazzName)
               .replace(METHOD_TRACE_SEQUENCE_NUMBER, Integer.valueOf(sequenceNumber).toString())
               .replace(BASE_PATH, this.basePathofStateFiles)
               .replace(METHOD_RETURNS, Boolean.valueOf(mutReturns).toString())
               .replace(ASSERT_STATEMENTS_GOES_HERE, Joiner.on(";\n").join(assertStatements))
               .replace(METHOD_RETURN_TYPE, getMUTReturnTypeAsString());
    }

    private String convertMUTSignatureToOneUsedByJavaReflection(){
        // classnamew : returnType methodname(type arg, type arg...)

        Iterator<String> s = Splitter.on(":").trimResults().split(mutSignature).iterator();
        String className = s.next();
        String subSignature = s.next();

        String returnTypeString = subSignature.substring(0, subSignature.indexOf(" "));
        String restOfMethodSignature = subSignature.substring(subSignature.indexOf(" ")).trim();

        return String.format("%s %s.%s", returnTypeString, className, restOfMethodSignature);
    }

    public Method getMUTMethod() throws ClassNotFoundException {
        ClassLoader contextCL = Thread.currentThread().getContextClassLoader();
        Class<?> c = contextCL.loadClass(clazzName);
        Method[] methods = c.getDeclaredMethods();
        for (Method m:methods){
            System.out.println(m.toString());
            System.out.println(mutSignature);
            System.out.println(convertMUTSignatureToOneUsedByJavaReflection());
            if (m.toString().contains(convertMUTSignatureToOneUsedByJavaReflection())){
                return m;
            }
        }
        return null;
    }

    public String getMUTReturnTypeAsString() throws ClassNotFoundException {
        Class<?> returnType = getMUTMethod().getReturnType();
        String strReturnType = returnType.toString();
        if (returnType.isArray()){
            strReturnType = returnType.getComponentType().toString() + "[]";
        }

        return strReturnType.split(" ")[1]; //get rid of "class"
    }
}


public class TestClassGenerator {

    public final String BEGIN_PACKAGE_SECTION = "$BEGIN_PACKAGE_SECTION";
    public final String END_PACKAGE_SECTION = "$END_PACKAGE_SECTION";

    public final String BEGIN_CLASS_DEFINITION = "$BEGIN_CLASS_DEFINITION";
    public final String END_CLASS_DEFINITION = "$END_CLASS_DEFINITION";

    public final String BEGIN_METHOD_DEFINITION = "$BEGIN_METHOD_DEFINITION";
    public final String END_METHOD_DEFINITION = "$END_METHOD_DEFINITION";

    public final String CLASS_DEFINITION_GOES_HERE = "$CLASS_DEFINITION_GOES_HERE$";
    public final String PACKAGE_NAMES_GOES_HERE = "$PACKAGE_NAMES_GOES_HERE$";
    public final String METHOD_DEFINITION_GOES_HERE = "$METHOD_DEFINITION_GOES_HERE$" ;

    public final ClassDefinition classDefinition;
    public final List<String> packageImports;
    public final MethodDefinition methodDefinition;

    public TestClassGenerator(List<String> packageImports,
                              ClassDefinition classDefinition,
                              MethodDefinition methodDefinition) throws MalformedURLException {
        this.classDefinition = classDefinition;
        this.packageImports = packageImports;
        this.methodDefinition = methodDefinition;

        initClassPath();
    }

    private void initClassPath() throws MalformedURLException {
        ClassLoader contextCL = Thread.currentThread().getContextClassLoader();

        String[] appClassPaths = TestGeneratorConfiguration.v().getAppClassPath().split(":");
        List<URL> urls = new ArrayList<URL>();
        for(String cp:appClassPaths){
            urls.add(new URL("file://" + cp));
        }

        URLClassLoader urlCL = URLClassLoader.newInstance(urls.toArray(new URL[]{}), contextCL);
        Thread.currentThread().setContextClassLoader(urlCL);
    }


    public String generateTest() throws IOException, ClassNotFoundException {

        URL url = this.getClass().getClassLoader().getResource("TestTemplate.template");
        String template = com.google.common.io.Files.toString(new File(url.getFile()), Charset.defaultCharset());

        template = template.replace(PACKAGE_NAMES_GOES_HERE, Joiner.on("\n").join(packageImports));
        template = classDefinition.generateClassDefintion(template);
        template = methodDefinition.generateMethodDefinition(template);

        return template;
    }

    private void getOracles(){
        com.ser.assist.oraclefinder.Core c = new
                com.ser.assist.oraclefinder.Core(this.methodDefinition.clazzName,
                this.methodDefinition.mutSignature);
        //"<com.ser.oraclefinder.testartifacts.Apples: int add(int)>")
        c.runAnalysis(Options.output_format_J, true);
    }

    private String getClassDefinitionTemplate(String template){
       return StringUtils.substringBetween(template,BEGIN_CLASS_DEFINITION, END_CLASS_DEFINITION);
    }

    public static boolean run(String clazzName, String mutName, String mutSignature,
                              int sequenceNumber) throws IOException, ClassNotFoundException {
        ArrayList<String> assertStatements =  new ArrayList<String>();
        assertStatements.add("dummy assert statement");

        MethodDefinition methodDefinition = new MethodDefinition(mutSignature, "MUTEE", clazzName, "/tmp",1,sequenceNumber, true, assertStatements);
        ClassDefinition classDefinition = new ClassDefinition("1");

        TestClassGenerator generator = new TestClassGenerator(new ArrayList<String>(), classDefinition, methodDefinition);
        String generatedTest = generator.generateTest();
        System.out.println(generatedTest);
        return true;

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
/*
        String clazzName =  "com.ser.oraclefinder.testartifacts.Apples"; //"com.ser.statecarver.testartifacts.TestPojo";
        String mutSignature =  "com.ser.oraclefinder.testartifacts.Apples: int add(int)"; //"int[] com.ser.statecarver.testartifacts.TestPojo.newadd2(int)";
        int sequenceNumber = 1;
        int testMethodNumber = 999;
*/

        String clazzName =  "com.ser.oraclefinder.testartifacts.Kiwi2"; //"com.ser.statecarver.testartifacts.TestPojo";
        String mutSignature =  "com.ser.oraclefinder.testartifacts.Kiwi2: com.ser.oraclefinder.testartifacts.Kiwi2 anotherInstance()"; //"int[] com.ser.statecarver.testartifacts.TestPojo.newadd2(int)";
        int sequenceNumber = 1;
        int testMethodNumber = 999;


        ArrayList<String> assertStatements =  new ArrayList<String>();
        com.ser.assist.oraclefinder.Core c = new  com.ser.assist.oraclefinder.Core(clazzName, mutSignature);
        c.runAnalysis(Options.output_format_J, false);
        for(Oracle o:c.oraclesFound){
            assertStatements.add(o.generateAssertStatement("expectedReturnValue", "returnValue"));
        }


        MethodDefinition methodDefinition = new MethodDefinition(mutSignature, "MUTEE", clazzName,
                TestGeneratorConfiguration.v().getTraceSource(),testMethodNumber,sequenceNumber, true, assertStatements);
        ClassDefinition classDefinition = new ClassDefinition("1");

        TestClassGenerator generator = new TestClassGenerator(new ArrayList<String>(), classDefinition, methodDefinition);
        String generatedTest = generator.generateTest();
        System.out.println(generatedTest);

    }





}
