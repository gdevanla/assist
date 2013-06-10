package com.ser.statecarver.core;

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

    private static StateInstrumenter instance = new StateInstrumenter();
    private StateInstrumenter() {}

    @Override
    protected void internalTransform(Body body, String s, Map map) {
        SootMethod method = body.getMethod();
        System.out.println(method.getName());
        if ( method.getName().equals("add")){
        Chain<Local> locals = body.getLocals();
        System.out.println("Here=" + method);
        for(Local l:locals){
          //  System.out.println(l.toString());
        }

        List<ValueBox> defBoxes = body.getDefBoxes();
        System.out.println("DefBoxes:" + defBoxes);
/*
        System.out.println("Inside Def boxes");
        for (ValueBox v:defBoxes){
           System.out.println(v.toString() + "," + v.getValue() + "," + v.getTags());

        }
*/

       List<ValueBox> useDefBoxes = body.getUseAndDefBoxes();
       System.out.println("UseDefBoxes:" + useDefBoxes);
       for (ValueBox v:useDefBoxes){
            //System.out.println("Inside Use-Def boxes");
            //System.out.println(v.toString() + "," + v.getValue() + "," + v.getTags());

        }
     }

        List<ValueBox> useBoxes = body.getUseBoxes();
        System.out.println("UseBoxes:" + useBoxes);
        for (ValueBox v:useBoxes){
            //System.out.println("Inside Use-Def boxes");
            System.out.println(v.toString() + "," + v.getValue() + "," + v.getTags());
        }
    }

    public static StateInstrumenter v() { return instance;}

    public static void main(String[] args){
        PackManager.v().getPack("jtp").add(new Transform("jtp.myTransformer", StateInstrumenter.v()));
                Options.v().set_verbose(true);
        Options.v().set_output_format(Options.output_format_J);

        String[] sootArguments = new String[]{"-process-dir", sourceFolder,
                "-cp", sourceFolder + ":/System/Library/Frameworks/JavaVM.framework/Classes/classes.jar"};

        soot.Main.main(sootArguments);


    }
}
