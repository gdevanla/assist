package com.ser.oraclefinder.testartifacts;

/**
 * Created by gdevanla on 2/5/14.
 */
class Kiwi2{
    public Kiwi2(){

    }

    public int add(int x){
        return x*2;
    }

    public Kiwi2 anotherInstance(){
        return new Kiwi2();
    }

    public int addNoArgs(){
        return 100;
    }
}
