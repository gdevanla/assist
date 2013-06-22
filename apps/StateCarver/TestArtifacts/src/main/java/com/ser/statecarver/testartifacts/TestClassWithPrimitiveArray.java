package com.ser.statecarver.testartifacts;

/**
 * Created with IntelliJ IDEA.
 * User: gdevanla
 * Date: 6/13/13
 * Time: 4:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestClassWithPrimitiveArray {

    int[] int_array;

    public TestClassWithPrimitiveArray(){
        int_array = new int[100];
    }

    public void change_array(int y)
    {
        int_array[y]=-1;
    }

    public void print_element(int y){
        System.out.println(int_array[y]);
    }
}
