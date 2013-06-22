package com.ser.statecarver.testartifacts;

/**
 * Created with IntelliJ IDEA.
 * User: gdevanla
 * Date: 6/13/13
 * Time: 4:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestClassWithObjectArray {

    TestPojo[] pojos;

    public TestClassWithObjectArray(){
        pojos = new TestPojo[10];
        for (int i=0; i<10;i++){
            pojos[i] = new TestPojo(i,i,i*2, String.valueOf(i));
        }

    }

    public void change_array(int y)
    {
        pojos[y] = new TestPojo(y,y,y*2, String.valueOf("changed"));
    }

    public void print_element(int y){
        System.out.println(pojos[y].public_string_s);
    }
}
