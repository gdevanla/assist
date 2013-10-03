package com.google.test.metric;


import com.google.test.metric.Cost;
import com.google.test.metric.SourceLocation;
import junit.framework.TestCase;
import static com.google.test.metric.Reason.NON_OVERRIDABLE_METHOD_CALL;
import static com.google.test.metric.report.DrillDownReportGenerator.NEW_LINE;
import static java.lang.Integer.MAX_VALUE;
import static com.google.test.metric.report.issues.IssueSubType.COMPLEXITY;
import static com.google.test.metric.report.issues.IssueSubType.STATIC_METHOD;
import static com.google.test.metric.report.issues.IssueType.COLLABORATOR;
import static com.google.test.metric.report.issues.IssueType.DIRECT_COST;
import static com.google.test.metric.report.FreemarkerReportGenerator.HTML_REPORT_TEMPLATE;
import static java.text.MessageFormat.format;
import static java.util.ResourceBundle.getBundle;
import static com.google.test.metric.Reason.IMPLICIT_STATIC_INIT;
import static java.util.Arrays.asList;

public class CyclomaticCostGenTest extends TestCase {
	public SourceLocation instantiateSourceLocation589() {
		return new SourceLocation("com/google/Foo.java", 12);
	}

	public Cost instantiateCost590() {
		return new Cost();
	}

	/**
	 * Test method for the class com.google.test.metric.CyclomaticCost
	 */
	public void testCyclomaticCost1040() throws Exception {
		Cost var3469 = instantiateCost590();
		SourceLocation var3470 = instantiateSourceLocation589();
		CyclomaticCost var3471 = new CyclomaticCost(var3470, var3469);
		var3469.add(new Cost());
		var3469.add(new Cost());
	}

	public SourceLocation instantiateSourceLocation591() {
		return new SourceLocation("foo", 1);
	}

	/**
	 * Test method for the class com.google.test.metric.CyclomaticCost
	 */
	public void testCyclomaticCost1041() throws Exception {
		SourceLocation var3473 = instantiateSourceLocation591();
		CyclomaticCost var3474 = new CyclomaticCost(var3473, Cost.cyclomatic(1));
	}

	public SourceLocation instantiateSourceLocation592() {
		return new SourceLocation("foo", 1);
	}

	/**
	 * Test method for the class com.google.test.metric.CyclomaticCost
	 */
	public void testCyclomaticCost1042() throws Exception {
		SourceLocation var3475 = instantiateSourceLocation592();
		CyclomaticCost var3476 = new CyclomaticCost(var3475, Cost.cyclomatic(1));
	}

	public SourceLocation instantiateSourceLocation593() {
		return new SourceLocation("foo", 1);
	}

	/**
	 * Test method for the class com.google.test.metric.CyclomaticCost
	 */
	public void testCyclomaticCost1043() throws Exception {
		SourceLocation var3477 = instantiateSourceLocation593();
		CyclomaticCost var3478 = new CyclomaticCost(var3477, Cost.cyclomatic(1));
	}

	public SourceLocation instantiateSourceLocation594() {
		return new SourceLocation("com/google/FooClass.java", 1);
	}

	public Cost instantiateCost595() {
		return new Cost();
	}

	/**
	 * Test method for the class com.google.test.metric.CyclomaticCost
	 */
	public void testCyclomaticCost1044() throws Exception {
		Cost var3479 = instantiateCost595();
		SourceLocation var3480 = instantiateSourceLocation594();
		CyclomaticCost var3481 = new CyclomaticCost(var3480, var3479);
		var3479.add(new Cost());
		var3479.add(new Cost());
	}
}