package com.ser.instrument.testartifacts;
import java.io.IOException;

import com.ser.instrument.artifacts.InstrumentedClass1;
import junit.framework.TestCase;

//@RunWith(JUnit4.class)
public class TestInstrumentedClasses extends TestCase{

   // @Test
    public void testInstrumentClass1() throws IOException {
        InstrumentedClass1 c = new InstrumentedClass1();
        c.methodWithPrimitiveInt(10);

    }


}

