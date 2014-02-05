package com.ser.assist.tests.statecarver;


import com.ser.assist.AssistConfiguration;
import com.ser.assist.statecarver.core.StateInstrumenter;
import com.ser.assist.tests.oraclefinder.TestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;


@RunWith(JUnit4.class)
public class TestStateCarver extends TestBase {

    @Test
    public void testStateCarver() throws IOException {

        AssistConfiguration.config_properties_fname = "assist.properties";
        StateInstrumenter.run();

    }

}

