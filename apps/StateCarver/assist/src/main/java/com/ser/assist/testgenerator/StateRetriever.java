package com.ser.assist.testgenerator;


import com.ser.assist.statecarver.core.Utils;
import com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver;
import org.apache.commons.io.FilenameUtils;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class StateRetriever {
    public final String clazzName;
    public final String mutSignature;
    public final int sequenceNumber ;
    public final String basePath;

    public StateRetriever(String clazzName, String mutSignature, int sequenceNumber, String basePath){
        this.clazzName = clazzName;
        this.mutSignature = mutSignature;
         this.sequenceNumber = sequenceNumber;
        this.basePath = basePath;
    }

    public Method getMUTMethod() throws ClassNotFoundException {
        ClassLoader contextCL = Thread.currentThread().getContextClassLoader();
        System.out.println("Looking for class="+clazzName);
        Class<?> c = contextCL.loadClass(this.clazzName);
        Method[] methods = c.getDeclaredMethods();
        for (Method m:methods){
            System.out.println(m.toString());

            if (m.toString().contains(Utils.convertMUTSignatureToOneUsedByJavaReflection(mutSignature))){
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

        if ( strReturnType.contains("class"))
            return strReturnType.split(" ")[1]; //get rid of "class"
        else
            return strReturnType;

    }

    public Object getReturnValue() throws ClassNotFoundException {
        String fileName = XStreamStateCarver.getParamStateFileName(this.sequenceNumber, "return", getMUTReturnTypeAsString());
        Object o = XStreamStateCarver.loadState(FilenameUtils.concat(basePath, fileName));
        System.out.println(o.getClass());
        System.out.println(o);
        return o;
    }

    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException {

        URL url = new URL("file:///Users/gdevanla/Dropbox/private/se_research/myprojects/assist/apps/StateCarver/TestArtifacts/target/classes/");
        ClassLoader contextCL = Thread.currentThread().getContextClassLoader();

        //Thread.currentThread().getContextClassLoader().getResource()
        URLClassLoader urlCL = URLClassLoader.newInstance(new URL[]{url}, contextCL);

        for ( URL u:urlCL.getURLs()){
            System.out.println(u.toString());
        }

        Thread.currentThread().setContextClassLoader(urlCL);
        StateRetriever t = new StateRetriever("com.ser.statecarver.testartifacts.TestPojo",
                "int[] com.ser.statecarver.testartifacts.TestPojo.newadd2(int)", 1, "/tmp");
        t.getReturnValue();
        // t.invokeMethod();
    }


}
