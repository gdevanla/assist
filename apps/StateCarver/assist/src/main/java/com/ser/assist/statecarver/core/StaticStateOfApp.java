package com.ser.assist.statecarver.core;

import com.sun.tools.doclets.formats.html.resources.standard;
import soot.RefType;
import soot.Scene;
import soot.SootClass;
import soot.SootField;
import soot.util.Chain;

import java.util.ArrayList;
import java.util.HashSet;

public class StaticStateOfApp {

   // HashSet<SootClass> visitedClasses;
    ArrayList<SootField> staticFields;

    public static StaticStateOfApp instance = new StaticStateOfApp();

    public static void init(){
        instance.collectStaticFields();

        System.out.println("Following static fields were collected");
        for(SootField f: instance.staticFields){
            System.out.println(f + " in " + f.getDeclaringClass());
        }
    }

    private StaticStateOfApp(){
        staticFields = new ArrayList<SootField>();
       // visitedClasses = new HashSet<SootClass>();
    }

    private void collectStaticFields(){
        //TODO: Report to soot-list. getAppClasses does not return the classes defined inside
        //another class file.
        Chain<SootClass> applicationClasses = Scene.v().getClasses();
        for (SootClass sootClass : applicationClasses) {
            if (!sootClass.getJavaPackageName()
                    .startsWith(StateCarverConfiguration.v().getAppClassesPrefix())){
                //System.out.println("Ignoring class = " + sootClass + " in package " + sootClass.getPackageName());
                continue;
            }

            collectStaticFields(sootClass);
        }
    }

    private void collectStaticFields(SootClass sootClazz){
        System.out.println("Fetching static fields for" + sootClazz.getName());
        for (SootField field :  sootClazz.getFields()) {
            //TODO: can I ignore isPrivate check?
            if (field.isStatic() && !field.isFinal())
                staticFields.add(field);
        }
    }

    public ArrayList<SootField> getStaticFieldList(){
        return staticFields;
    }

}
