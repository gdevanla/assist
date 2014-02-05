package com.ser.assist.tests.xstreamstatecarver;

import com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver;
import com.ser.assist.tests.oraclefinder.TestBase;
import groovyjarjarasm.asm.Type;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

@RunWith(JUnit4.class)
public class TestXStreamStateCarver extends TestBase {

    @Test
    public void testSimpleClass() {
        SimpleClass s = new SimpleClass();
        XStreamStateCarver.saveStateToFile(s, "simpleclass.xml");

        SimpleClass c = (SimpleClass) XStreamStateCarver.loadState("simpleclass.xml");
        //assertEquals(c.i, 10);
        //assertEquals(c.getS(), "private variable");
    }

    @Test
    public void testPrimitiveInt(){
        XStreamStateCarver.saveStateToFile(Integer.valueOf(10), "int.xml");
        Object o = XStreamStateCarver.loadState("int.xml");
        assertEquals(o, 10);
    }

    @Test
    public void testStaticVariants(){
        XStreamStateCarver.saveStateToFile(ClassHavingStaticMember.s, "staticstring1.xml");
        Object o = XStreamStateCarver.loadState("staticstring1.xml");
        ClassHavingStaticMember.s = (String)o;
        assertEquals(ClassHavingStaticMember.s , "static");

        XStreamStateCarver.saveStateToFile(new ClassReferringToStaticMemberOfAnotherClass(), "staticstring2.xml");
        Object o1 = XStreamStateCarver.loadState("staticstring2.xml");
        ClassReferringToStaticMemberOfAnotherClass c = (ClassReferringToStaticMemberOfAnotherClass) o1;
        assertEquals(ClassHavingStaticMember.s , "static");
    }


    @Test
    public void testContainedClasses(){
        TestObjectForXStream to = new TestObjectForXStream(10, "testobject");
        XStreamStateCarver.saveStateToFile(to, "testobjectforxstream.xml");
        Object o = XStreamStateCarver.loadState("testobjectforxstream.xml");
        TestObjectForXStream t = (TestObjectForXStream)o;
        assertEquals(o.hashCode(), t.hashCode());
    }

}


/* Test Fixtures */



class SimpleClass  {
    public int i = 10;
    private String s = "private variable";
    public static int j = 100;
    public String getS() { return s;}
}


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
    public  static String s = "static";
    int instance_i;

    public ClassHavingStaticMember(int y){
        instance_i = y;
    }
}

class ClassReferringToStaticMemberOfAnotherClass {
    public String s = ClassHavingStaticMember.s;
}

