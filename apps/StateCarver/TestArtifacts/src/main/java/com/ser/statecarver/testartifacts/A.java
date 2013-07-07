package com.ser.statecarver.testartifacts;

import java.util.concurrent.atomic.AtomicLong;

class AtomicCounter {

    public static final AtomicLong methodCounter = new AtomicLong();

}

public class A {

    public void T1(){
        int a = 0;
        a = a + 1;
    }

    public void testAtomicLong()
    {
        long x = AtomicCounter.methodCounter.getAndIncrement();
    }


}
