package com.ser.instrument.testartifacts

import com.ser.assist.statecarver.xstreamcarver.StaticStateLoader
import com.ser.statecarver.testartifacts.TestPojo

class GroovyTestModel extends GroovyTestCase {

    public void testInitStaticData()
    {
        StaticStateLoader.loadStaticState(9)
    }



    public void testTest1() {
        def temp$1
        def var_state_1
        def y_clone
        def z_clone
        def var_state_1_clone
        def temp$1_clone
        def x_clone
        def z
        def var_param_0_2
        def y
        def x

        initStaticData()
        var_state_1 = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state:13:this.xml")
        var_state_1_clone = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state:13:this.xml")
        var_param_0_2 = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state:13:0.xml")
        temp$1 = var_state_1.invokeMethod("add", var_param_0_2)
        temp$1_clone = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state:13:return.xml")
        z = temp$1
        z_clone = temp$1_clone
        y = z
        y_clone = z_clone
        assertEquals(y_clone,y)
    }

    public void testTest2() {

        def temp$1
        def y_clone
        def z_clone
        def var_state_3
        def var_state_3_clone
        def temp$1_clone
        def x_clone
        def var_param_0_4
        def z
        def y
        def x
        var_state_3 = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state.13.this.xml")
        var_state_3_clone = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state.13.this.xml")
        var_param_0_4 = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state.13.0.xml")
        temp$1 = var_state_3.invokeMethod("add", var_param_0_4)
        temp$1_clone = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state.13.return.xml")
        z = temp$1
        z_clone = temp$1_clone
        y = z
        y_clone = z_clone
        assertEquals(y_clone,y)
    }

    public void testTest3() {
        def var_state_5_clone
        def temp$4_clone
        def temp$3_clone
        def var_state_5
        def x_clone
        def temp$1_clone
        def temp$2_clone
        def xx_clone
        def xx
        def temp$1
        def temp$3
        def temp$2
        def z_clone
        def temp$4
        def var_param_0_6
        def aa
        def aa_clone
        def z
        def x
        var_state_5 = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state.13.this.xml")
        var_state_5_clone = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state.13.this.xml")
        var_param_0_6 = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state.13.0.xml")
        temp$1 = var_state_5.invokeMethod("add", var_param_0_6)
        temp$1_clone = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state.13.return.xml")
        z = temp$1
        z_clone = temp$1_clone
        //temp$2 = new com.ser.oraclefinder.testartifacts.Apples
        temp$2 =  new com.ser.oraclefinder.testartifacts.Apples()
        temp$2_clone =  new com.ser.oraclefinder.testartifacts.Apples()
        xx = temp$2
        xx_clone = temp$2_clone
        temp$3 = z / 2
        temp$3_clone = z_clone / 2
        temp$4 = xx.add(temp$3)
        temp$4_clone = xx_clone.add(temp$3)
        aa = temp$4
        aa_clone = temp$4_clone
       /* y = z
        y_clone = z_clone
        a = aa
        a_clone = aa_clone */
        assertEquals(z,aa)
        assertEquals(z_clone,aa_clone)
        assertEquals(z,aa_clone)
        assertEquals(z_clone,aa)
    }

    public void testTest4() {

        def temp$3_clone
        def temp$4_clone
        def var_state_1
        def a_clone
        def a
        def temp$1_clone
        def x_clone
        def temp$2_clone
        def temp$1
        def temp$3
        def temp$2
        def temp$4
        def var_state_1_clone
        def x
        var_state_1 = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state.13.this.xml")
        var_state_1_clone = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state.13.this.xml")
        temp$1 = var_state_1.invokeMethod("getListOfApples", null)
        temp$1_clone = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state.13.return.xml")
        x = temp$1
        x_clone = temp$1_clone
        temp$2 = x.get(2)
        temp$2_clone = x_clone.get(2)
        temp$3 = (java.lang.Integer) temp$2
        temp$3_clone = (java.lang.Integer) temp$2_clone
        temp$4 = temp$3.intValue()
        temp$4_clone = temp$3_clone.intValue()
        assertEquals(temp$4,temp$4_clone)
    }

    public void testTest5() {
        def temp$3_clone
        def temp$4_clone
        def var_state_1
        def a_clone
        def a
        def temp$1_clone
        def x_clone
        def temp$2_clone
        def temp$1
        def temp$3
        def temp$2
        def temp$4
        def var_state_1_clone
        def x
        var_state_1 = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state.13.this.xml")
        var_state_1_clone = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state.13.this.xml")
        temp$1 = var_state_1.invokeMethod("getArrayOfApples", null)
        temp$1_clone = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state.13.return.xml")
        x = temp$1
        x_clone = temp$1_clone
        temp$2 = x
        temp$2_clone = x_clone
        temp$3 = 2
        temp$3_clone = 2
        temp$4 = temp$2[temp$3]
        temp$4_clone = temp$2_clone[temp$3_clone]
        assertEquals(temp$4,temp$4_clone)
    }

    public void testTest6() {
        def $i0_clone
        def $r1
        def $r1_clone
        def var_param_1_1_clone
        def $i0
        def l0
        def var_param_1_1
        def l0_clone
        $r1 = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state:3:this.xml")
        $r1_clone = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state:3:this.xml")
        l0 = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state:3:0.xml")
        var_param_1_1 = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state:3:1.xml")
        l0_clone = l0
        var_param_1_1_clone = var_param_1_1
        $i0 = l0.getCount()
        $i0_clone = l0_clone.getCount()
        //return

        println l0
        println var_param_1_1

    }

    public void testTest7() {
        def $i0_clone
        def $r1
        def $r1_clone
        def var_param_1_1_clone
        def $i0
        def l0
        def var_param_1_1
        def l0_clone
        $r1 = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state:3:this.xml")
        $r1_clone = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state:3:this.xml")
        l0 = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state:3:0.xml")
        var_param_1_1 = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state:3:1.xml")
        l0_clone = l0
        var_param_1_1_clone = var_param_1_1
        $i0 = l0.getCount()
        $i0_clone = l0_clone.getCount()
        assertEquals($i0_clone,$i0)
        //return
    }


    public void testTest8() {
        def $i0_clone
        def var_param_1_2_clone
        def $r1
        def $r1_clone
        def $i0
        def l0
        def var_param_1_2
        def l0_clone
        $r1 = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state:3:this.xml")
        $r1_clone = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state:3:this.xml")
        l0 = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state:3:0.xml")
        var_param_1_2 = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state:3:1.xml")
        l0_clone = l0
        var_param_1_2_clone = var_param_1_2
        $i0 = l0.count
        $i0_clone = l0_clone.count
        assertEquals($i0_clone,$i0)
    }


    public void testTest9() {
        def temp$1
        def temp$2
        def c
        def x_clone
        def temp$1_clone
        def temp$2_clone
        def var_param_1_3
        def var_param_1_3_clone
        def x
        def c_clone
        temp$1 = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state:3:this.xml")
        temp$1_clone = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state:3:this.xml")
        c = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state:3:0.xml")
        var_param_1_3 = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state:3:1.xml")
        c_clone = c
        var_param_1_3_clone = var_param_1_3
        x = com.ser.instrument.artifacts.StaticContainer.y_static
        x_clone = com.ser.instrument.artifacts.StaticContainer.y_static
        temp$2 = c.count
        temp$2_clone = c_clone.count
        assertEquals(x,temp$2)
        assertEquals(x_clone,temp$2_clone)
        assertEquals(x,temp$2_clone)
        assertEquals(x_clone,temp$2)
    }

    public void testTest10() {
        def temp$1
        def temp$3
        def temp$3_clone
        def temp$2
        def c
        def var_param_1_4
        def var_param_1_4_clone
        def x_clone
        def temp$1_clone
        def temp$2_clone
        def x
        def c_clone
        temp$1 = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state:3:this.xml")
        temp$1_clone = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state:3:this.xml")
        c = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state:3:0.xml")
        var_param_1_4 = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state:3:1.xml")
        c_clone = c
        var_param_1_4_clone = var_param_1_4
        temp$2 = com.ser.statecarver.testartifacts.TestPojo.getPrintStaticValues()
        temp$2_clone = com.ser.statecarver.testartifacts.TestPojo.getPrintStaticValues()
        x = temp$2
        x_clone = temp$2_clone
        temp$3 = c.count
        temp$3_clone = c_clone.count
        assertEquals(x,temp$3)
        assertEquals(x_clone,temp$3_clone)
        assertEquals(x,temp$3_clone)
        assertEquals(x_clone,temp$3)
//return
    }


}
