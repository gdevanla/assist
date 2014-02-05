package com.ser.assist.statecarver.xstreamcarver;

import com.ser.assist.statecarver.core.StateCarverConfiguration;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.reflection.Sun14ReflectionProvider;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.io.*;

public class XStreamStateCarver {

    private XStream xstream;
  //  private String fileName;

   private static XStreamStateCarver instance = new XStreamStateCarver();

    public XStreamStateCarver(){
       // this.fileName = MethodTracer.getStateFileForMethod();
        xstream = new XStream(new Sun14ReflectionProvider(),
                new DomDriver());

        //xstream.setMode();

        //TODO: Do I need to set a Marshalling Strategy?
       /* xstream.registerConverter(new StaticAttributeConverter(
                xstream.getMapper(),
                new StaticReflectionProvider()),
                XStream.PRIORITY_LOW);
                */
    }

    private void save(Object o, String fileName){
        try{
            String filePath = getFilePath(fileName);
            System.out.println("Writing to file now.. =" + filePath);
            ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter(filePath, false));
            out.writeObject(o);

            out.close();
        }
        catch(IOException e){
            //I need a better logger here
            System.err.println("Error while saving state " + o.toString() + o.getClass() + e);
            System.err.println("Error in write Method Trace");
            System.exit(-1);
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

    // Helper methods
    public static String getParamStateFileName(long methodCounter, String paramNumberOrThis, String type){
        return "state" + "." + methodCounter + "." + paramNumberOrThis + ".xml";
    }

    public static String getParamStateFileName(long methodCounter, String paramNumberOrThis){
        return "state" + "." + methodCounter + "." + paramNumberOrThis + ".xml";
    }

    public static String getStateFileName(long methodCounter)
    {
        return getParamStateFileName(methodCounter, "this");
    }

    public static String getParamFileName(long methodCounter, int index)
    {
        return getParamStateFileName(methodCounter, Integer.toString(index));
    }

    public static String getReturnFileName(long methodCounter)
    {
        return getParamStateFileName(methodCounter, "return");
    }


    public static void saveState(Object o, long methodCounter, String paramNumberOrThisOrRet, String type){
        System.out.println("Saving state for methodCounter " + methodCounter + "type=" + type);
        String fileName = getParamStateFileName(methodCounter, paramNumberOrThisOrRet, type);
        saveStateToFile(o, fileName);
    }

    public static void saveStateToFile(Object o, String fileName){
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

    public static Object loadState(String fileName) {
        return instance.load(getFilePath(fileName));
    }


    private static String getFilePath(String fileName){
        //TODO: use proper path concatenation style
        return StateCarverConfiguration.v().getTraceDestination() + "/" + fileName;
    }

    public static void main(String[] args){

        //TestObjectForXStream o = new TestObjectForXStream(10, "OuterObject");
        //ClassHavingStaticMember o = new ClassHavingStaticMember(1000);
        //XStreamStateCarver.saveState(o, 1, "1", o.getClass().getCanonicalName());
        //x.saveState(TestObjectForXStream.class, TestObjectForXStream.class.toString());
        //XStreamStateCarver.saveState(10);
        //Object o = XStreamStateCarver.loadState("/tmp/trace/saveint.xml");
        //System.out.println(o.getClass());

        // ClassHavingStaticMember o = (ClassHavingStaticMember)XStreamStateCarver.loadState("/tmp/state.0.0.int.xml");
        // System.out.println(o.s);

    }
}
