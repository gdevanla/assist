package com.ser.assist.statecarver.xstreamcarver;

import com.ser.assist.statecarver.core.Configuration;
import com.ser.assist.statecarver.core.MethodTracer;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.reflection.Sun14ReflectionProvider;
import com.thoughtworks.xstream.io.xml.DomDriver;

import javax.sound.midi.SysexMessage;
import java.io.*;

class InnerTestObjectForXStream{
    public int a;
    private String b;

    public InnerTestObjectForXStream(int x, String s){
        this.a = x;
        this.b = s;
    }
}

class ObjectWithCircularReference{
    public TestObjectForXStream x;

    public ObjectWithCircularReference(TestObjectForXStream x){
        this.x =x;
    }
}

class TestStaticClass{
    private String label;
    public static TestStaticClass instance = new TestStaticClass();
    private TestStaticClass(){
        this.label = "I am the label inside the static class";
    }
}

class Parent {
    String s = "I am from parent";
    ObjectWithCircularReference c;
    static TestStaticClass staticClass;
    public Parent(ObjectWithCircularReference c){
        this.c = c;
        staticClass = TestStaticClass.instance;
    }
}

class Child extends Parent{
    private static String childsStaticField;
    public Child(ObjectWithCircularReference o){
        super(o);
        childsStaticField = "I am a static field in the Child";
    }
}

class TestObjectForXStream{
    private int x;
    public String y;
    private InnerTestObjectForXStream innerObject;
    private ObjectWithCircularReference circularReference;
    private Child treeSample;

    int[] x_int_array = new int[10];
    Parent[] clazz_array = new Parent[10];

    public TestObjectForXStream(int x, String s){
        this.x = x;
        this.y = s;
        innerObject = new InnerTestObjectForXStream(x*10, s+":"+s);
        circularReference = new ObjectWithCircularReference(this);
        this.treeSample = new Child(circularReference);

        for ( int i=0; i<10; i++){
            x_int_array[i] = i;
            clazz_array[i] = new Parent(circularReference);
        }
    }
}


class ClassHavingStaticMember{
    public  Integer I = new Integer(10);
    public  int i;
    public  String s = "static";
    int instance_i;

    public ClassHavingStaticMember(int y){
        instance_i = y;
    }
}

public class XStreamStateCarver {

    private XStream xstream;
  //  private String fileName;

   private static XStreamStateCarver instance = new XStreamStateCarver();

    public XStreamStateCarver(){
       // this.fileName = MethodTracer.getStateFileForMethod();
        xstream = new XStream(new Sun14ReflectionProvider(),
                new DomDriver());
        //TODO: Do I need to set a Marshalling Strategy?

       /* xstream.registerConverter(new StaticAttributeConverter(
                xstream.getMapper(),
                new StaticReflectionProvider()),
                XStream.PRIORITY_LOW);*/
    }

    private void save(Object o, String fileName){
        try{
            String filePath = getFilePath(fileName);
            System.out.println("Writing to file =" + filePath);
            ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter(filePath, false));
            out.writeObject(o);
            out.close();
        }
        catch(IOException e){
            //I need a better logger here
            System.out.println("Error while saving state " + o.toString() + o.getClass() + e);
        }
    }

    private Object load(String filePath){
        try{
            ObjectInputStream in = xstream.createObjectInputStream(new FileReader(filePath));
            Object o = in.readObject();
            return o;
        }
        catch(IOException e){
            System.out.println("Error while loading state from " + filePath);
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Error while loading state from " + filePath);
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return null;
    }

    public static void saveState1(Object o, String fileName) {
       System.out.println("Over here - this time");
       instance.save(o, fileName);
       System.out.println("Done saving file");
    }

    public static String getParamStateFileName(long methodCounter, String paramNumberOrThis, String type){
        return "state" + "." + methodCounter + "." + paramNumberOrThis + "." + type + ".xml";
    }

    public static void saveState(Object o, long methodCounter, String paramNumberOrThisOrRet, String type){
        String fileName = getParamStateFileName(methodCounter, paramNumberOrThisOrRet, type);
        instance.save(o, fileName);
    }

    public static void savePrimitiveInt(int o, long methodCounter, String paramNumberOrThisOrRet, String type){
        String fileName = getParamStateFileName(methodCounter, paramNumberOrThisOrRet, type);
        instance.save(o, fileName);
    }

    public static String getStaticStateFileName(long methodCounter, String enclosingClass,
                                           String type, String variableName){
        return "static" + "." + methodCounter + "." + enclosingClass +
                ":" + type + ":" + variableName + ".xml";
    }

    public static void saveStaticState(Object o, long methodCounter, String enclosingClass,
                                       String type, String variableName){
        String fileName = getStaticStateFileName(methodCounter, enclosingClass, type, variableName);
        instance.save(o, fileName);

    }

    public static Object loadState(String filePath) {
        return instance.load(filePath);
    }


    private static String getFilePath(String fileName){
        //TODO: use proper path concatenation style
        return new Configuration().getBaseFolder() + "/" + fileName;
    }

    public static void main(String[] args){
       // TestObjectForXStream o = new TestObjectForXStream(10, "OuterObject");
        //ClassHavingStaticMember o = new ClassHavingStaticMember(1000);
        //XStreamStateCarver.saveState(o, o.toString());
        //x.saveState(TestObjectForXStream.class, TestObjectForXStream.class.toString());

        Object o = XStreamStateCarver.loadState("/tmp/state.0.0.int.xml");
        System.out.println(o.getClass());

        // ClassHavingStaticMember o = (ClassHavingStaticMember)XStreamStateCarver.loadState("/tmp/state.0.0.int.xml");
        // System.out.println(o.s);

    }
}
