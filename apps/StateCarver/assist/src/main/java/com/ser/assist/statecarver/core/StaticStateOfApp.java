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

  /*  private void collectStaticFields(){
        Chain<SootClass> applicationClasses = Scene.v().getApplicationClasses();
        for (SootClass sootClass : applicationClasses) {
            if (visitedClasses.contains(sootClass)){
                continue;
            }
            collectStaticFields(sootClass);
        }
    }*/

   /* private void collectStaticFields(SootClass sootClazz){

        System.out.println("Fetching static fields for" + sootClazz.getName());
        Chain<SootField> fields = sootClazz.getFields();

        for (SootField field : fields) {
            //if(field.isPublic() && field.isStatic() && !field.isFinal())
            if (field.isStatic() && !field.isFinal())
                staticFields.add(field);
            // if(Utils.isPrimitive(field.getType())) //no need to drill deeper
            //     continue;

            *//*
            RefType type;
            soot.Type fieldType = field.getType();
            if(fieldType instanceof soot.ArrayType){
                soot.ArrayType arrayType = (soot.ArrayType) field.getType();
                if(Utils.isPrimitive(arrayType.baseType))
                    continue;
                fieldType = (RefType) arrayType.baseType;
            }
            type = (RefType) fieldType;
            SootClass clazz = type.getSootClass();
            visitedClasses.add(sootClazz);
            if(!visitedClasses.contains(clazz))
                collectStaticFields(clazz);
                *//*
        }
    }*/
}
