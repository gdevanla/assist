package com.ser.hellochicago;

public class HelloChicago {

    public String s;
    public int count;
    public static int counter = 0;

    public HelloChicago(String s, int count){
        this.s = s;
        this.count = count;
    }

    public String appendString(String name){
        return s  + name;
    }

    public int increaseCount(int i){
        return count + i;
    }

    public void incrementCount(){
        this.count = this.count + 1;
    }

    public int getCount(){
        return count;
    }

    public String getString(){
        return s;
    }


}
