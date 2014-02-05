package com.ser.instrument.testartifacts

class GroovyTestModel extends GroovyTestCase {

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
        var_state_1 = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state.13.this.xml")
        var_state_1_clone = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state.13.this.xml")
        var_param_0_2 = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state.13.0.xml")
        temp$1 = var_state_1.invokeMethod("add", var_param_0_2)
        temp$1_clone = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state.13.return.xml")
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



}
