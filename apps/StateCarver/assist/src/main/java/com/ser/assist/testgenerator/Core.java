package com.ser.assist.testgenerator;


import soot.*;
import soot.jimple.JasminClass;
import soot.jimple.Jimple;
import soot.jimple.StringConstant;
import soot.jimple.ThisRef;
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
       addPublicConstructor(testClazz);

       SootMethod testMethod = createTestMethod(testMethodName, testClazz);
       testMethod.setActiveBody(Jimple.v().newBody(testMethod));
       testClazz.addMethod(testMethod);

       addThisReferenceToBody(testMethod.getActiveBody());
       generateSimpleReturnTest(testMethod.getActiveBody(), "com.ser.testgenerator.testartifacts.SimpleClass",
               "int sayHello()", "/tmp/0.xml");

       System.out.println(testMethod.getActiveBody().getUnits());
       writeAsJimple(testClazz);
       writeAsClass(testClazz);

   }

    private void addPublicConstructor(SootClass testClazz) {

        SootClass superTestClazz = Scene.v().forceResolve("junit.framework.TestCase", SootClass.SIGNATURES);
        SootMethod superTestClazzInit = superTestClazz.getMethod("void <init>()");

        SootMethod testMethod = new SootMethod("<init>", new ArrayList(), VoidType.v(), Modifier.PUBLIC);
        testMethod.setActiveBody(Jimple.v().newBody(testMethod));
        testClazz.addMethod(testMethod);

        Body body = testMethod.getActiveBody();
        addThisReferenceToBody(body);
        Local thisLocal = body.getThisLocal();
        body.getUnits().addLast(Jimple.v().newInvokeStmt(
                Jimple.v().newSpecialInvokeExpr(thisLocal, superTestClazzInit.makeRef())));
        body.getUnits().addLast(Jimple.v().newReturnVoidStmt());

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

    public void addThisReferenceToBody(Body body){
       //com.ser.oraclefinder.testartifacts.Apples this;
       //this := @this: com.ser.oraclefinder.testartifacts.Apples;
       SootClass clazz = body.getMethod().getDeclaringClass();
       Local l = Jimple.v().newLocal("this", RefType.v(clazz));
       body.getLocals().addFirst(l);

       //Jimple.v().new

       ThisRef thisref = Jimple.v().newThisRef(RefType.v(clazz));
       //((Value)l), (Value)Jimple.v().newIdentityRefBox((JimpleLocal)l));
       Unit u = Jimple.v().newIdentityStmt(l, thisref);
       body.getUnits().addLast(u);
   }


   public void generateSimpleReturnTest(Body body, String mutDeclaringClazzName, String subSignature, String stateFileName){

       SootClass xStreamStateCarverClass = Scene.v().forceResolve(
               "com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver",
               SootClass.SIGNATURES);
       SootMethod xStreamLoadMethod = xStreamStateCarverClass.getMethodByName("loadState");

       SootClass clazz = Scene.v().forceResolve(mutDeclaringClazzName, SootClass.SIGNATURES);
       SootMethod mut = clazz.getMethod(subSignature);

       Local localReceiverObject = Jimple.v().newLocal("receiverObject1", RefType.v(mutDeclaringClazzName));
       body.getLocals().add(localReceiverObject);

       List<Value> args = new ArrayList<Value>();
       args.add(StringConstant.v(stateFileName));

       Local localObject = Jimple.v().newLocal("localObject", RefType.v("java.lang.Object"));
       body.getLocals().add(localObject);
       body.getUnits().addLast(Jimple.v().newAssignStmt(localObject,
               Jimple.v().newStaticInvokeExpr(xStreamLoadMethod.makeRef(), args)));
               //StringConstant.v(stateFileName))));

       body.getUnits().addLast(Jimple.v().newAssignStmt(localReceiverObject,
               Jimple.v().newCastExpr(localObject, clazz.getType())));

       body.getUnits().addLast(Jimple.v().newInvokeStmt(Jimple.v().newVirtualInvokeExpr(localReceiverObject, mut.makeRef())));
       body.getUnits().addLast(Jimple.v().newReturnVoidStmt());

   }

    public static boolean run() throws IOException {
        Core core = new Core();
        String sootClassPath = "/System/Library/Frameworks/JavaVM.framework/Classes/classes.jar"
                + ":" + "/System/Library/Frameworks/JavaVM.framework/Classes/jce.jar"
                + ":" + "/Users/gdevanla/.m2/repository/com/thoughtworks/xstream/xstream/1.4.4/xstream-1.4.4.jar"
                + ":" + "/Users/gdevanla/.m2/repository/junit/junit/3.8.1/junit-3.8.1.jar"
                + ":" + "/Users/gdevanla/Dropbox/private/se_research/myprojects/assist/apps/StateCarver/TestArtifacts/src/main/java"
                + ":" + "/Users/gdevanla/Dropbox/private/se_research/myprojects/assist/apps/StateCarver/assist/src/main/java";

        core.initSoot(sootClassPath);
        core.builder();

        return true;
    }

    public static void main(String[] args) throws IOException {

        Core core = new Core();
        String sootClassPath = "/System/Library/Frameworks/JavaVM.framework/Classes/classes.jar"
                + ":" + "/System/Library/Frameworks/JavaVM.framework/Classes/jce.jar"
                + ":" + "/Users/gdevanla/.m2/repository/com/thoughtworks/xstream/xstream/1.4.4/xstream-1.4.4.jar"
                + ":" + "/Users/gdevanla/.m2/repository/junit/junit/3.8.1/junit-3.8.1.jar"
                + ":" + "/Users/gdevanla/Dropbox/private/se_research/myprojects/assist/apps/StateCarver/TestArtifacts/src/main/java"
                + ":" + "/Users/gdevanla/Dropbox/private/se_research/myprojects/assist/apps/StateCarver/assist/src/main/java";

        core.initSoot(sootClassPath);
        core.builder();

    }



}
