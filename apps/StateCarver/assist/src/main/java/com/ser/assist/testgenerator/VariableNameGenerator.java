package com.ser.assist.testgenerator;

import java.util.concurrent.atomic.AtomicLong;

public class VariableNameGenerator {
    public static final String var = "var_%s_%s";
    public static final AtomicLong variableCounter = new AtomicLong();

    public static long getNextIndex(){
        return variableCounter.incrementAndGet();
    }

    public static String getNextName(String suffix){
       return String.format(var, suffix, getNextIndex());
    }

}
