package com.ser.oraclefinder.testartifacts;

public class ShappyApples{

    String shape;

    public ShappyApples(){
        shape = "square";
    }

    public String getMorphedShape(String update){
        return shape+"_"+update;
    }
}
