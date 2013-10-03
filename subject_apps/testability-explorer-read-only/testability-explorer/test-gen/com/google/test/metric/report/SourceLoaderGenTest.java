package com.google.test.metric.report;


import com.google.classpath.ClassPathFactory;
import com.google.test.metric.report.Source;
import java.lang.Class;
import java.lang.String;
import com.google.classpath.ClassPath;
import junit.framework.TestCase;

public class SourceLoaderGenTest extends TestCase {
	/**
	 * Test method for the class com.google.test.metric.report.SourceLoader
	 */
	public void testSourceLoader995() throws Exception {
		ClassPath var3180 = new ClassPathFactory().createFromPaths(
				"src/test/java", "core/src/test/java");
		SourceLoader var3181 = new SourceLoader(var3180);
		var3181.load("X-I don't exist-X");
		var3181.load(InnerClass.class.getCanonicalName());
	}

	/**
	 * Test method for the class com.google.test.metric.report.SourceLoader
	 */
	public void testSourceLoader996() throws Exception {
		ClassPath var3180 = new ClassPathFactory().createFromPaths(
				"src/test/java", "core/src/test/java");
		SourceLoader var3181 = new SourceLoader(var3180);
		var3181.load("X-I don't exist-X");
		var3181.load(getClass().getName());
	}

	/**
	 * Test method for the class com.google.test.metric.report.SourceLoader
	 */
	public void testSourceLoader997() throws Exception {
		ClassPath var3185 = new ClassPathFactory().createFromPaths(
				"src/test/java", "core/src/test/java");
		SourceLoader var3186 = new SourceLoader(var3185);
		var3186.load(getClass().getName());
		var3186.load(InnerClass.class.getCanonicalName());
	}

	/**
	 * Test method for the class com.google.test.metric.report.SourceLoader
	 */
	public void testSourceLoader998() throws Exception {
		ClassPath var3180 = new ClassPathFactory().createFromPaths(
				"src/test/java", "core/src/test/java");
		SourceLoader var3181 = new SourceLoader(var3180);
		var3181.load(getClass().getName());
		var3181.load("X-I don't exist-X");
	}

	/**
	 * Test method for the class com.google.test.metric.report.SourceLoader
	 */
	public void testSourceLoader999() throws Exception {
		ClassPath var3185 = new ClassPathFactory().createFromPaths(
				"src/test/java", "core/src/test/java");
		SourceLoader var3186 = new SourceLoader(var3185);
		var3186.load("X-I don't exist-X");
		var3186.load(getClass().getName());
	}

	private static class InnerClass {
	}
}