package com.ser.statecarver.core;

import com.ser.statecarver.com.ser.statecarver.random.Research;
import soot.*;
import soot.options.Options;
import soot.util.Chain;
import soot.util.Switch;

import java.util.List;
import java.util.Map;


public class StateInstrumenter extends BodyTransformer {

    /*move to configuration*/
    static final String appBaseFolder = "/Users/gdevanla/Dropbox/private/se_research/myprojects/assist/apps/StateCarver";
    static final String sourceFolder = appBaseFolder + "/src/test/java";

    static final String sootClassPath = sourceFolder + ":" + appBaseFolder + "/" + "target/classes";

    private static StateInstrumenter instance = new StateInstrumenter();
    private StateInstrumenter() {}

    @Override
    protected void internalTransform(Body body, String s, Map map) {
      //new Research().reviewUseDefBoxes(body, s, map);
      new MethodInstrumenter().instrumentMethod(body, s, map);
    }

    public static StateInstrumenter v() { return instance;}

    public static void main(String[] args){

        //Create settings in Configuration
        //TODO: Static fields need to be captured across the application

        PackManager.v().getPack("jtp").add(new Transform("jtp.myTransformer", StateInstrumenter.v()));
                Options.v().set_verbose(true);
        //Options.v().set_output_format(Options.output_format_J);


        //move this whole thing to mvn, can that be done?
        String[] sootArguments = new String[]{"-process-dir", sourceFolder,
                "-cp", sootClassPath + ":/System/Library/Frameworks/JavaVM.framework/Classes/classes.jar"
                + ":" + "/System/Library/Frameworks/JavaVM.framework/Classes/jce.jar"
                + ":" + "/Users/gdevanla/.m2/repository/com/thoughtworks/xstream/xstream/1.4.4/xstream-1.4.4.jar"};


        soot.Main.main(sootArguments);


    }
}
