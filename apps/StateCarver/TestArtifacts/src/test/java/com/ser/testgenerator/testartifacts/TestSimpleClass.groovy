package com.ser.testgenerator.testartifacts



class TestSimpleClass extends GroovyTestCase {

    void testExample1Test1() {
        def s1 = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state.this.mut.xml");
        def s2 = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state.return.mut.xml");

        def param1 = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state.param1.mut.xml");
        def param2 = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state.param2.mut.xml");
        def r1 = s1.invokeMethod(mut, param1, param2);

        assertEquals(r1, s2);
    }

    void testExample1Test2() {
        def s1 = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state.this.mut.xml");
        def s2 = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state.return.mut.xml");

        def param1 = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state.param1.mut.xml");
        def param2 = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state.param2.mut.xml");

        def r1 = s1.invokeMethod(mut, param1, param2);

    }




    void testSomething() {














        def s1 = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("simpleclass.xml");
        def x = s1.invokeMethod("sayHello", null)
        def x1 = Eval.me("def s1=com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState(\"simpleclass.xml\"); " +
                "s1.invokeMethod(\"${t}\", null)");
        //def y = ((String)x).substring(5);

        //def s2 = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("simpleclass.xml");

        assertEquals(x, x1)

    }
}
