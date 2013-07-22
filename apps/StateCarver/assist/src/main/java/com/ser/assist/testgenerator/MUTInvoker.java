package com.ser.assist.testgenerator;

import com.ser.assist.statecarver.core.Utils;
import com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;


// load state of this
// load state of params
// load all static state
// invoke method ( static, private , public )
//    - handle multiple parameters
// return the value from invoked method

public class MUTInvoker {

    public final String clazzName;
    public final String mutSignature;
    public final int sequenceNumber ;
    public final String basePath;

    public MUTInvoker(String clazzName, String mutSignature, int sequenceNumber, String basePath){
        this.clazzName = clazzName;
        this.mutSignature = mutSignature;
        this.sequenceNumber = sequenceNumber;
        this.basePath = basePath;
    }

    public void loadStaticState() throws Exception {
        File dir = new File(basePath);
        FileFilter fileFilter = new WildcardFileFilter("static."+sequenceNumber+".*");
       // FileFilter fileFilter = new WildcardFileFilter("static.0.com.ser.statecarver.testartifacts.DummyStaticClass:java.lang.Integer:fieldRef_com.ser.statecarver.testartifacts.DummyStaticClass_x.xml");
        File[] files = dir.listFiles(fileFilter);
        for(File f:files){
            String temp = f.getName().substring("static.0.".length());
            temp = temp.substring(0, temp.length()-".xml".length());
            String[] var = temp.split(":");
            if (var[2].contains("fieldRef_")){
                var[2] = var[2].split("_")[2];
            }
            initStaticField(var, f);
            checkStaticField(var);
        }

    }

    private void initStaticField(String[] var, File fileName) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        ClassLoader contextCL = Thread.currentThread().getContextClassLoader();
        Class<?> c = contextCL.loadClass(var[0]);
        Field field = c.getDeclaredField(var[2]);
        field.setAccessible(true);
        Object o = XStreamStateCarver.loadState(fileName.toString());
        field.set(c, o);
    }

    private void checkStaticField(String[] var) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        ClassLoader contextCL = Thread.currentThread().getContextClassLoader();
        Class<?> c = contextCL.loadClass(var[0]);
        Field f = c.getDeclaredField(var[2]);
        f.setAccessible(true);
        System.out.println(f.get(c));
    }

    public ArrayList<Object> loadParams() throws Exception {
        ArrayList<Object>  params = new ArrayList<Object>();
        File dir = new File(basePath);
        for (int i=0; i < 256; i++){   //well, how else will I know how my params are there?
            FileFilter fileFilter = new WildcardFileFilter("state."+sequenceNumber+"."+i+"*");
            File[] files = dir.listFiles(fileFilter);
            if (files.length == 0) break;
            if (files.length > 1) throw new Exception("Found more than one file for a given param" + i + "in folder" + basePath);

            File f = files[0];

            Object o = XStreamStateCarver.loadState(f.toString());
            System.out.println(o.getClass());
            params.add(o);
        }
        return params;
    }

    public Object loadThis() throws ClassNotFoundException {
        Class<?> c = Thread.currentThread().getContextClassLoader().loadClass(this.clazzName);
        String fileName = XStreamStateCarver.getParamStateFileName(this.sequenceNumber, "this", this.clazzName);
        Object o = XStreamStateCarver.loadState(FilenameUtils.concat(basePath, fileName));

        return o;
    }

    public Method getMutMethod() throws ClassNotFoundException {
        ClassLoader contextCL = Thread.currentThread().getContextClassLoader();
        Class<?> c = contextCL.loadClass(this.clazzName);
        Method[] methods = c.getDeclaredMethods();
        for (Method m:methods){
            System.out.println(m.toString());
            System.out.println("Looking for ="+mutSignature);
            if (m.toString().contains(Utils.convertMUTSignatureToOneUsedByJavaReflection(mutSignature))){
                return m;
            }
        }
        return null;
    }

    public Object invokeMethod() throws Exception {

        ClassLoader contextCL = Thread.currentThread().getContextClassLoader();

        Class<?> c = contextCL.loadClass(this.clazzName);
        Constructor<?>[] x = c.getDeclaredConstructors();
        XStreamStateCarver stateCarver = new XStreamStateCarver();
        Object i = loadThis();
        ArrayList<Object> params = loadParams();
        ArrayList<Class> argTypes = new ArrayList<Class>();
        for (Object o:params){
            System.out.println("params="+o);
            argTypes.add(o.getClass());
        }
        Method m = getMutMethod();
        System.out.println("method being invoked="+m);
        Object returnValue = m.invoke(i, params.toArray());
        System.out.println(returnValue);
        return returnValue;
    }

    public static void main(String[] args) throws Exception {
        URL url = new URL("file:///Users/gdevanla/Dropbox/private/se_research/myprojects/assist/apps/StateCarver/TestArtifacts/target/classes/");
        ClassLoader contextCL = Thread.currentThread().getContextClassLoader();

        //Thread.currentThread().getContextClassLoader().getResource()
        URLClassLoader urlCL = URLClassLoader.newInstance(new URL[]{url}, contextCL);

        for ( URL u:urlCL.getURLs()){
            System.out.println(u.toString());
        }


        Thread.currentThread().setContextClassLoader(urlCL);
        MUTInvoker t = new MUTInvoker("com.ser.statecarver.testartifacts.TestPojo",
                "void com.ser.statecarver.testartifacts.TestPojo.newadd(int)", 0, "/tmp");
       // t.invokeMethod();

        t.loadStaticState();

        //ClassLoader contextCL = Thread.currentThread().getContextClassLoader();
        Class<?> c = urlCL.loadClass("com.ser.statecarver.testartifacts.DummyStaticClass");
        Field f = c.getDeclaredField("x");
        System.out.println(f.get(c));
        f = c.getDeclaredField("Integer_array_y");
        Integer[] is = (Integer[])f.get(c);
        for (Integer i:is){
            System.out.println(i);
        }




    }

}
