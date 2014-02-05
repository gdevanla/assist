package com.ser.statecarver.testartifacts;

/**
 * Created by gdevanla on 2/5/14.
 */
public class Kiwi{
    public Kiwi(){

    }

    public int add(int x){
        return x*2;
    }

    public Kiwi anotherInstance(){
        return new Kiwi();
    }

    public int addNoArgs(){
        return 100;
    }
}
