package com.ser.statecarver.xstreamcarver;

import com.ser.statecarver.core.Configuration;
import com.ser.statecarver.core.MethodTracer;
import com.sun.corba.se.impl.orb.ParserTable;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.reflection.Sun14ReflectionProvider;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

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

    public TestObjectForXStream(int x, String s){
        this.x = x;
        this.y = s;
        innerObject = new InnerTestObjectForXStream(x*10, s+":"+s);
        circularReference = new ObjectWithCircularReference(this);
        this.treeSample = new Child(circularReference);
    }
}

public class XStreamStateCarver {

    private XStream xstream;
    private String fileName;

   private static XStreamStateCarver instance = new XStreamStateCarver();

    public XStreamStateCarver(){
        this.fileName = MethodTracer.getStateFileForMethod();
        xstream = new XStream(new Sun14ReflectionProvider(),
                new DomDriver());
        //TODO: Do I need to set a Marshalling Strategy?

        xstream.registerConverter(new StaticAttributeConverter(
                xstream.getMapper(),
                new StaticReflectionProvider()),
                XStream.PRIORITY_LOW);
    }

    private void save(Object o){
        try{
            String fileName = getNameofStateFile();
            System.out.println("Writing to file =" + fileName);
            ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter(fileName, true));
            out.writeObject(o);
            out.close();
        }
        catch(IOException e){
            //I need a better logger here
            System.out.println("Error while saving state " + o.toString() + o.getClass() + e);
        }
    }

    public static void saveState(Object o) {
       System.out.println("Over here - this time");
       instance.save(o);
       System.out.println("Done saving file");
    }


    private static String getNameofStateFile(){
        //TODO: use proper path concatenation style
        return new Configuration().getBaseFolder() + "/" + MethodTracer.getCurrentMethodCounter() + ".xml";
    }

    public static void main(String[] args){
        TestObjectForXStream o = new TestObjectForXStream(10, "OuterObject");
        XStreamStateCarver x = new XStreamStateCarver();
        x.saveState(o);
        x.saveState(TestObjectForXStream.class);
    }
}
