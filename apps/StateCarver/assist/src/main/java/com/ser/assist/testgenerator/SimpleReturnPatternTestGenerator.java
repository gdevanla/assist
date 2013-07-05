package com.ser.assist.testgenerator;

import com.ser.assist.oraclefinder.Oracle;
import soot.Local;
import soot.RefType;
import soot.jimple.Jimple;
import soot.jimple.internal.JimpleLocal;

/**
 * Created with IntelliJ IDEA.
 * User: gdevanla
 * Date: 6/30/13
 * Time: 7:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleReturnPatternTestGenerator {

    public final String mutSignature;
    public final String clazzName;
    public final Oracle oracle;


    public SimpleReturnPatternTestGenerator(String mutSignature, String clazz, Oracle oracle){
        this.mutSignature = mutSignature;
        this.clazzName = clazz;
        this.oracle = oracle;
    }

    public void generateCodeSnippet(int index, Local xStreamLocal, String fileName){

    }
}
