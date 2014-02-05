package com.ser.oraclefinder.testartifacts;


import junit.framework.TestCase;


public class SimpleReturnPattern1Test extends TestCase {

    public void testSimpleReturnPattern1() {
        /* Here MUT is add*/
        Apples x = new Apples();
        int z = x.add(10); //+ Math.abs(10);
        int y = z;
        assertEquals(20, y);
    }

    public void testSimpleReturnPattern2() {
        /* Here MUT is add*/
        Apples x = new Apples();
        int z = x.add(10); //+ Math.abs(10);
        int y = z;
        int a = 20;  //need constant propogation for this.
        assertEquals(a, y);
    }

    public void testSimpleReturnPattern3() {
        /* Here MUT is add*/
        Apples x = new Apples();
        int z = x.add(10); //+ Math.abs(10);
        Apples xx = new Apples();
        int aa = xx.add(z / 2);  //need to handle BigDecimal crap
        int y = z;
        int a = aa;
        assertEquals(z, aa);
    }


    public void testSimpleReturnPattern4() {
        /* Here MUT is add*/
        Apples x = new Apples();
        int z = x.add(50); //+ Math.abs(10);
        int y = 100;
        if ( 1 <= (int)Math.random() )  {
            y = 100;
        }
        else
        {
            y = 100;
        }

        assertEquals(z, y);
    }

    public void testSimpleReturnPattern5() {
        /* Here MUT is add*/
        Apples x = new Apples();
        int z = x.add(10); //+ Math.abs(10);
        int y = 20;
        for (int i=0; i < 5; i++){
           // y = i;
            assertEquals(z, y);
        }

        //assertEquals(z, y);
    }







}
