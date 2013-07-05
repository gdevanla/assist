package com.ser.assist.testgenerator;


import soot.*;
import soot.jimple.*;
import soot.jimple.internal.IdentityRefBox;
import soot.jimple.internal.JimpleLocal;
import soot.options.Options;
import soot.util.JasminOutputStream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Core {

   public void initSoot(String classpath){

       Options.v().set_soot_classpath(classpath);
       Options.v().set_output_dir("/tmp");
       //soot.Main.main(new String[]{});

   }

   public SootClass createTestClazz(String testClazzName){

       SootClass superTestClazz = Scene.v().forceResolve("junit.framework.TestCase", SootClass.SIGNATURES);
       SootClass testClazz = new SootClass(testClazzName, Modifier.PUBLIC);
       testClazz.setSuperclass(superTestClazz);
       return testClazz;

   }

   public SootMethod createTestMethod(String methodName, SootClass testClazz){
       return new SootMethod(methodName, new ArrayList(), VoidType.v(),Modifier.PUBLIC);
   }

   public void builder() throws IOException {

       String testClazzName = "TestSimpleReturn101";
       String testMethodName = "testSimpleReturn101";

       SootClass testClazz = createTestClazz(testClazzName);
       SootMethod testMethod = createTestMethod(testMethodName, testClazz);
       testMethod.setActiveBody(Jimple.v().newBody(testMethod));
       testClazz.addMethod(testMethod);

       generateMethodDefaultParameterStack(testClazz, testMethod.getActiveBody());
       generateSimpleReturnTest(testMethod.getActiveBody(), "com.ser.testgenerator.testartifacts.SimpleClass",
               "int sayHello()");

       System.out.println(testMethod.getActiveBody().getUnits());
       writeAsJimple(testClazz);
       writeAsClass(testClazz);

   }

   public void writeAsJimple(SootClass clazz) throws IOException {

       String fileName = SourceLocator.v().getFileNameFor(clazz, Options.output_format_jimple);
       OutputStream streamOut = new FileOutputStream(fileName);
       PrintWriter writerOut = new PrintWriter(
               new OutputStreamWriter(streamOut));
       Printer.v().printTo(clazz, writerOut);
       writerOut.flush();
       streamOut.close();

   }

   public void writeAsClass(SootClass clazz) throws IOException {
       String fileName = SourceLocator.v().getFileNameFor(clazz, Options.output_format_class);
       OutputStream streamOut = new JasminOutputStream(
               new FileOutputStream(fileName));
       PrintWriter writerOut = new PrintWriter(
               new OutputStreamWriter(streamOut));
       JasminClass jasminClass = new soot.jimple.JasminClass(clazz);
       jasminClass.print(writerOut);
       writerOut.flush();
       streamOut.close();
   }

    public void generateMethodDefaultParameterStack(SootClass clazz, Body body){
       //com.ser.oraclefinder.testartifacts.Apples this;
       //this := @this: com.ser.oraclefinder.testartifacts.Apples;

       Local l = Jimple.v().newLocal("this", RefType.v(clazz));
       body.getLocals().addFirst(l);

       //Jimple.v().new

       ThisRef thisref = Jimple.v().newThisRef(RefType.v(clazz));
       //((Value)l), (Value)Jimple.v().newIdentityRefBox((JimpleLocal)l));
       Unit u = Jimple.v().newIdentityStmt(l, thisref);
       body.getUnits().addLast(u);
   }


   public void generateSimpleReturnTest(Body body, String clazzName, String subSignature){

       SootClass clazz = Scene.v().forceResolve(clazzName, SootClass.SIGNATURES);
       SootMethod mut = clazz.getMethod(subSignature);



       Local l1 = Jimple.v().newLocal("v1", RefType.v(clazzName));
       body.getLocals().add(l1);

       Stmt newStmt = Jimple.v().newAssignStmt(l1, Jimple.v().newNewExpr(RefType.v(clazzName)));
       body.getUnits().addLast(newStmt);
       body.getUnits().addLast(Jimple.v().newInvokeStmt(Jimple.v().newVirtualInvokeExpr(l1, mut.makeRef())));

   }

    public static void main(String[] args) throws IOException {

        Core core = new Core();
        String sootClassPath = "/System/Library/Frameworks/JavaVM.framework/Classes/classes.jar"
                + ":" + "/System/Library/Frameworks/JavaVM.framework/Classes/jce.jar"
                + ":" + "/Users/gdevanla/.m2/repository/com/thoughtworks/xstream/xstream/1.4.4/xstream-1.4.4.jar"
                + ":" + "/Users/gdevanla/.m2/repository/junit/junit/3.8.1/junit-3.8.1.jar"
                + ":" + "/Users/gdevanla/Dropbox/private/se_research/myprojects/assist/apps/StateCarver/TestArtifacts/src/main/java";

        core.initSoot(sootClassPath);
        core.builder();

    }



}
