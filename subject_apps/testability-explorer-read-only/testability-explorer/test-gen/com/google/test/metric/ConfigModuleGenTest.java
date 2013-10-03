package com.google.test.metric;


import java.lang.String;
import java.io.PrintStream;
import junit.framework.TestCase;
import java.io.ByteArrayOutputStream;

public class ConfigModuleGenTest extends TestCase {
	/**
	 * Test method for the class com.google.test.metric.ConfigModule
	 */
	public void testConfigModule925() throws Exception {
		ByteArrayOutputStream var2881 = new ByteArrayOutputStream();
		PrintStream var2882 = new PrintStream(var2881);
		ByteArrayOutputStream var2883 = new ByteArrayOutputStream();
		PrintStream var2884 = new PrintStream(var2883);
		ConfigModule var2885 = new ConfigModule(new String[] { "junit.runner",
				"-cp" }, var2882, var2884);
	}

	/**
	 * Test method for the class com.google.test.metric.ConfigModule
	 */
	public void testConfigModule926() throws Exception {
		ByteArrayOutputStream var2881 = new ByteArrayOutputStream();
		PrintStream var2882 = new PrintStream(var2881);
		ByteArrayOutputStream var2883 = new ByteArrayOutputStream();
		PrintStream var2884 = new PrintStream(var2883);
		ConfigModule var2886 = new ConfigModule(new String[] { "-cp",
				"not/default/path", "com.google.FirstClass",
				"com.google.second.package", "com.google.third.package" },
				var2882, var2884);
	}

	/**
	 * Test method for the class com.google.test.metric.ConfigModule
	 */
	public void testConfigModule927() throws Exception {
		ByteArrayOutputStream var2881 = new ByteArrayOutputStream();
		PrintStream var2882 = new PrintStream(var2881);
		ByteArrayOutputStream var2883 = new ByteArrayOutputStream();
		PrintStream var2884 = new PrintStream(var2883);
		ConfigModule var2887 = new ConfigModule(new String[] { "-cp",
				"not/default/path", "com.google.TestClass" }, var2882, var2884);
	}

	/**
	 * Test method for the class com.google.test.metric.ConfigModule
	 */
	public void testConfigModule928() throws Exception {
		ByteArrayOutputStream var2881 = new ByteArrayOutputStream();
		PrintStream var2882 = new PrintStream(var2881);
		ByteArrayOutputStream var2883 = new ByteArrayOutputStream();
		PrintStream var2884 = new PrintStream(var2883);
		ConfigModule var2888 = new ConfigModule(new String[0], var2882, var2884);
	}

	/**
	 * Test method for the class com.google.test.metric.ConfigModule
	 */
	public void testConfigModule929() throws Exception {
		ByteArrayOutputStream var2881 = new ByteArrayOutputStream();
		PrintStream var2882 = new PrintStream(var2881);
		ByteArrayOutputStream var2883 = new ByteArrayOutputStream();
		PrintStream var2884 = new PrintStream(var2883);
		ConfigModule var2887 = new ConfigModule(new String[] { "-cp",
				"not/default/path", "com.google.TestClass" }, var2882, var2884);
	}
}