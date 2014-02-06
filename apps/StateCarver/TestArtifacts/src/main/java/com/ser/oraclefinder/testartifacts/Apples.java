package com.ser.oraclefinder.testartifacts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gdevanla on 2/5/14.
 */
public class Apples{
    public Apples(){

    }

    public int add(int x){
        return x*2;
    }

    public List<Integer> getListOfApples() {
        List<Integer> l = new ArrayList<Integer>();
        l.add(10);
        l.add(20);
        l.add(30);

        return l;
    }

    public int[] getArrayOfApples() {
        int[] x = {10, 20, 30};
        return x;
    }
}
