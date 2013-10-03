package com.google.test.metric.report;


import com.google.test.metric.report.Diff;
import java.util.List;
import java.util.Arrays;
import junit.framework.TestCase;
import com.google.test.metric.report.Diff.ClassDiff;
import com.google.test.metric.report.Diff.MethodDiff;

public class DiffGenTest extends TestCase {
	/**
	 * Test method for the class com.google.test.metric.report.Diff
	 */
	public void testDiff1185() throws Exception {
		Diff var3827 = new Diff(Arrays.asList(new Diff.ClassDiff("com.Foo",
				123, 456)));
	}

	/**
	 * Test method for the class com.google.test.metric.report.Diff
	 */
	public void testDiff1186() throws Exception {
		Diff.MethodDiff var3828 = new Diff.MethodDiff("doThing", 123, 456);
		Diff.ClassDiff var3829 = new Diff.ClassDiff("Foo", 1, 1,
				Arrays.asList(var3828));
		Diff var3830 = new Diff(Arrays.asList(var3829));
	}

	/**
	 * Test method for the class com.google.test.metric.report.Diff
	 */
	public void testDiff1187() throws Exception {
		Diff var3827 = new Diff(Arrays.asList(new Diff.ClassDiff("com.Foo",
				123, 456)));
	}

	/**
	 * Test method for the class com.google.test.metric.report.Diff
	 */
	public void testDiff1188() throws Exception {
		Diff var3831 = new Diff(Arrays.asList(new Diff.ClassDiff("Foo", 456,
				null)));
	}

	/**
	 * Test method for the class com.google.test.metric.report.Diff
	 */
	public void testDiff1189() throws Exception {
		Diff var3832 = new Diff(Arrays.asList(new Diff.ClassDiff("Foo", 456,
				123)));
	}
}