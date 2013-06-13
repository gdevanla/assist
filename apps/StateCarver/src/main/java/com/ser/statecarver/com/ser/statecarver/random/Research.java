package com.ser.statecarver.com.ser.statecarver.random;

import soot.Body;
import soot.Local;
import soot.SootMethod;
import soot.ValueBox;
import soot.util.Chain;

import javax.inject.Singleton;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: gdevanla
 * Date: 6/11/13
 * Time: 7:01 AM
 * To change this template use File | Settings | File Templates.
 */

@Singleton
public class Research {

    public void reviewUseDefBoxes(Body body, String s, Map map) {
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


}
