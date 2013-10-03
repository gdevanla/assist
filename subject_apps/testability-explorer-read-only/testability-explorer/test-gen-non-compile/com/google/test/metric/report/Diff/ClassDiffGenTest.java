package com.google.test.metric.report.Diff;


import com.google.test.metric.report.Diff;
import java.util.List;
import java.util.Arrays;
import junit.framework.TestCase;
import com.google.test.metric.report.Diff.MethodDiff;

public class ClassDiffGenTest extends TestCase {
	/**
	 * Test method for the class com.google.test.metric.report.Diff.ClassDiff
	 */
	public void testClassDiff1230() throws Exception {
		Diff.MethodDiff var3890 = new Diff.MethodDiff("doThing", 456, 123);
		Diff.ClassDiff var3891 = new Diff.ClassDiff("Foo", 1, 1,
				Arrays.asList(var3890));
	}

	/**
	 * Test method for the class com.google.test.metric.report.Diff.ClassDiff
	 */
	public void testClassDiff1231() throws Exception {
		Diff.MethodDiff var3892 = new Diff.MethodDiff("doThing", null, 123);
		Diff.ClassDiff var3893 = new Diff.ClassDiff("Foo", 1, 1,
				Arrays.asList(var3892));
	}

	/**
	 * Test method for the class com.google.test.metric.report.Diff.ClassDiff
	 */
	public void testClassDiff1232() throws Exception {
		Diff.MethodDiff var3892 = new Diff.MethodDiff("doThing", null, 123);
		Diff.ClassDiff var3893 = new Diff.ClassDiff("Foo", 1, 1,
				Arrays.asList(var3892));
	}

	/**
	 * Test method for the class com.google.test.metric.report.Diff.ClassDiff
	 */
	public void testClassDiff1233() throws Exception {
		Diff.MethodDiff var3894 = new Diff.MethodDiff("doThing", 123, null);
		Diff.ClassDiff var3895 = new Diff.ClassDiff("Foo", 1, 1,
				Arrays.asList(var3894));
	}

	/**
	 * Test method for the class com.google.test.metric.report.Diff.ClassDiff
	 */
	public void testClassDiff1234() throws Exception {
		Diff.MethodDiff var3896 = new Diff.MethodDiff("doThing", 123, 456);
		Diff.ClassDiff var3897 = new Diff.ClassDiff("Foo", 1, 1,
				Arrays.asList(var3896));
	}
}