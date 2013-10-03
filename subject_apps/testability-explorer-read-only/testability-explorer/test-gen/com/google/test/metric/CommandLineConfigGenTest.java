package com.google.test.metric;


import java.io.PrintStream;
import junit.framework.TestCase;
import java.io.ByteArrayOutputStream;

public class CommandLineConfigGenTest extends TestCase {
	/**
	 * Test method for the class com.google.test.metric.CommandLineConfig
	 */
	public void testCommandLineConfig920() throws Exception {
		ByteArrayOutputStream var2875 = new ByteArrayOutputStream();
		ByteArrayOutputStream var2876 = new ByteArrayOutputStream();
		CommandLineConfig var2877 = new CommandLineConfig(new PrintStream(
				var2875), new PrintStream(var2876));
		var2877.validate();
		var2877.validate();
	}

	/**
	 * Test method for the class com.google.test.metric.CommandLineConfig
	 */
	public void testCommandLineConfig921() throws Exception {
		PrintStream var2879 = new PrintStream(new ByteArrayOutputStream());
		CommandLineConfig var2880 = new CommandLineConfig(null, var2879);
		var2880.validate();
		var2880.validate();
	}

	/**
	 * Test method for the class com.google.test.metric.CommandLineConfig
	 */
	public void testCommandLineConfig922() throws Exception {
		PrintStream var2879 = new PrintStream(new ByteArrayOutputStream());
		CommandLineConfig var2880 = new CommandLineConfig(null, var2879);
		var2880.validate();
		var2880.validate();
	}

	/**
	 * Test method for the class com.google.test.metric.CommandLineConfig
	 */
	public void testCommandLineConfig923() throws Exception {
		ByteArrayOutputStream var2875 = new ByteArrayOutputStream();
		ByteArrayOutputStream var2876 = new ByteArrayOutputStream();
		CommandLineConfig var2877 = new CommandLineConfig(new PrintStream(
				var2875), new PrintStream(var2876));
		var2877.validate();
		var2877.validate();
	}

	/**
	 * Test method for the class com.google.test.metric.CommandLineConfig
	 */
	public void testCommandLineConfig924() throws Exception {
		ByteArrayOutputStream var2875 = new ByteArrayOutputStream();
		ByteArrayOutputStream var2876 = new ByteArrayOutputStream();
		CommandLineConfig var2877 = new CommandLineConfig(new PrintStream(
				var2875), new PrintStream(var2876));
		var2877.validate();
		var2877.validate();
	}
}