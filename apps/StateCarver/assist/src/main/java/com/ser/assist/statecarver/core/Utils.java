package com.ser.assist.statecarver.core;

import com.google.common.base.Splitter;
import soot.*;
import soot.jimple.Jimple;

import java.util.Iterator;

public class Utils {

    public static boolean isPrimitive(soot.Type t) {
        return t instanceof IntType ||t instanceof FloatType
                || t instanceof FloatType ||  t instanceof DoubleType
                || t instanceof LongType || t instanceof soot.BooleanType
                || t instanceof soot.CharType || t instanceof soot.ByteType
                || t instanceof soot.ShortType;
    }

    public static Local getLocalForType(String name, Type type) {
        Local l;
        SootClass clazz = null;
        if(type instanceof IntType){
            clazz = Scene.v().forceResolve("java.lang.Integer", SootClass.SIGNATURES);
        }
        else if(type instanceof FloatType){
            clazz = Scene.v().forceResolve("java.lang.Float", SootClass.SIGNATURES);
        }
        else if(type instanceof LongType){
            clazz = Scene.v().forceResolve("java.lang.Long", SootClass.SIGNATURES);
        }
        else if(type instanceof DoubleType){
            clazz = Scene.v().forceResolve("java.lang.Double", SootClass.SIGNATURES);
        }
        else if(type instanceof soot.ShortType){
            clazz = Scene.v().forceResolve("java.lang.Short", SootClass.SIGNATURES);
        }
        else if(type instanceof CharType){
            clazz = Scene.v().forceResolve("java.lang.Character", SootClass.SIGNATURES);;
        }
        else if(type instanceof ByteType){
            clazz = Scene.v().forceResolve("java.lang.Byte", SootClass.SIGNATURES);
        }
        else if(type instanceof BooleanType){
            clazz = Scene.v().forceResolve("java.lang.Boolean", SootClass.SIGNATURES);
        }
        else{
            try {
                throw new Exception("Not Implemented for Type : " + type.toString());
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        l = Jimple.v().newLocal(name, RefType.v(clazz));
        return l;
    }

    public static SootMethod getSootMethodsForPrimitiveTypes(soot.Type type){
        SootClass clazz;
        SootMethod method = null;
        if(type instanceof IntType){
            clazz = Scene.v().forceResolve("java.lang.Integer", SootClass.SIGNATURES);
            method = clazz.getMethod("java.lang.Integer valueOf(int)");
        }
        else if(type instanceof FloatType){
            clazz = Scene.v().forceResolve("java.lang.Float", SootClass.SIGNATURES);
            method = clazz.getMethod("java.lang.Float valueOf(float)");
        }
        else if(type instanceof LongType){
            clazz = Scene.v().forceResolve("java.lang.Long", SootClass.SIGNATURES);
            method = clazz.getMethod("java.lang.Long valueOf(long)");
        }
        else if(type instanceof DoubleType){
            clazz = Scene.v().forceResolve("java.lang.Double", SootClass.SIGNATURES);
            method = clazz.getMethod("java.lang.Double valueOf(double)");
        }
        else if(type instanceof soot.ShortType){
            clazz = Scene.v().forceResolve("java.lang.Short", SootClass.SIGNATURES);
            method = clazz.getMethod("java.lang.Short valueOf(short)");
        }
        else if(type instanceof CharType){
            clazz = Scene.v().forceResolve("java.lang.Character", SootClass.SIGNATURES);
            method = clazz.getMethod("java.lang.Character valueOf(char)");
        }
        else if(type instanceof ByteType){
            clazz = Scene.v().forceResolve("java.lang.Byte", SootClass.SIGNATURES);
            method = clazz.getMethod("java.lang.Byte valueOf(byte)");
        }
        else if(type instanceof BooleanType){
            clazz = Scene.v().forceResolve("java.lang.Boolean", SootClass.SIGNATURES);
            method = clazz.getMethod("java.lang.Boolean valueOf(boolean)");
        }
        else{
            try {
                throw new Exception("Not Implemented for Type : " + type.toString());
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return method;
    }

    public static String convertMUTSignatureToOneUsedByJavaReflection(String mutSignature){
        // classnamew : returnType methodname(type arg, type arg...)

        Iterator<String> s = Splitter.on(":").trimResults().split(mutSignature).iterator();
        String className = s.next();
        String subSignature = s.next();

        String returnTypeString = subSignature.substring(0, subSignature.indexOf(" "));
        String restOfMethodSignature = subSignature.substring(subSignature.indexOf(" ")).trim();

        return String.format("%s %s.%s", returnTypeString, className, restOfMethodSignature);
    }

}
