package com.ser.statecarver.testartifacts;

/**
 * Created with IntelliJ IDEA.
 * User: gdevanla
 * Date: 6/7/13
 * Time: 5:19 AM
 * To change this template use File | Settings | File Templates.
 */

public class TestPojo {
    public String public_string_s;
    public int public_int_x;
    private int private_int_x;

    static int static_int_x;

    public TestPojo(int public_int_x, int private_int_x, int static_int_x, String public_string_s){
        this.public_int_x = public_int_x;
        this.private_int_x = private_int_x;
        this.public_string_s = public_string_s;
        static_int_x = this.static_int_x;
    }

    public void newadd(int x){
        System.out.println("new add");
    }

    public int newadd1(int x){
       return x*2;
    }

    public int[] newadd2(int x){
        return new int[]{1,2,3,4};
    }

    public int checkifandreturn(int x){
        if (x>100){
            return 200;
        }
        else if (x> 200)
        {
            return 50;
        }
        else
        {
            return -10;
        }



    }

    public int add(Integer x){
        int y = 10;
        private_int_x += x;
        public_int_x *= 2;
        System.out.println("Reached this statement1.");

        static_int_x = 100;
        static_int_x = x + 10;
        static_int_x = 20 + 10;

        return static_int_x + 50;
    }

    public static void main(String[] args){
       TestPojo pojo =  new TestPojo(1,1,1,"test");
        pojo.newadd1(1);
        pojo.newadd2(2);
        //pojo.add(new Integer(10));
        //pojo.public_int_x *=5;
        //pojo.add(20);

        //TestClassReferringToStaticClass t = new TestClassReferringToStaticClass();
        //t.referToStaticPropertyOfAnotherClass(10);
    }

}
