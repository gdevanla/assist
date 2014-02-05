package com.ser.oraclefinder.testartifacts;

import com.ser.statecarver.testartifacts.Kiwi;
import junit.framework.TestCase;

public class AssertOnReturnObjectMethod1Test extends TestCase {

    public void testTest1(){
        /* Here MUT is anotherInstance*/
        Kiwi x = new Kiwi();
        Kiwi y = x.anotherInstance();
        int z = y.addNoArgs();
        assertEquals(100, z);

        /* This can only be compared with this before function call
        and this after function call.
         */


    }

    public void testTest2(){
        /* Here MUT is add*/
        Kiwi x = new Kiwi();
        int y = x.add(10);
        int z = y;
        assertEquals(20, z);

        /* compare with stored return value */



    }

    public void testTest3(){
        /* Here MUT is add*/
        Kiwi x = new Kiwi();
        int y = x.add(10);
        Integer z = y;
        assertEquals(20, z.intValue());

        /* static invoke. not needed right now */


    }

    public void testTest4(){
        /* Here MUT is add*/
        Kiwi x = new Kiwi();
        int y = x.add(12);
  //      Integer z = Integer.valueOf(y);
  //      assertEquals(20, z.intValue());
    }

    public void testTest5(){
        /* Here MUT is add*/
        Kiwi x = new Kiwi();
        assertEquals(20, x.add(10));
    }

}
