package com.google.test.metric.report.issues;


import com.google.test.metric.report.issues.IssueType;
import java.lang.String;
import com.google.test.metric.SourceLocation;
import junit.framework.TestCase;
import com.google.test.metric.report.issues.IssueSubType;
import static com.google.test.metric.report.issues.IssueSubType.COMPLEXITY;
import static com.google.test.metric.report.issues.IssueSubType.STATIC_METHOD;
import static com.google.test.metric.report.issues.IssueType.COLLABORATOR;
import static com.google.test.metric.report.issues.IssueType.DIRECT_COST;
import static com.google.test.metric.report.FreemarkerReportGenerator.HTML_REPORT_TEMPLATE;
import static java.text.MessageFormat.format;
import static java.util.ResourceBundle.getBundle;

public class IssueGenTest extends TestCase {
	public SourceLocation instantiateSourceLocation718() {
		return new SourceLocation("com/google/FooClass.java", 1);
	}

	/**
	 * Test method for the class com.google.test.metric.report.issues.Issue
	 */
	public void testIssue1280() throws Exception {
		SourceLocation var3990 = instantiateSourceLocation718();
		String var3991 = "foo()";
		Issue var3992 = new Issue(var3990, var3991, 0.9f, DIRECT_COST,
				COMPLEXITY);
	}

	public SourceLocation instantiateSourceLocation719() {
		return new SourceLocation("foo", 1);
	}

	/**
	 * Test method for the class com.google.test.metric.report.issues.Issue
	 */
	public void testIssue1281() throws Exception {
		SourceLocation var3993 = instantiateSourceLocation719();
		Issue var3994 = new Issue(var3993, null, 1f, null, null);
	}

	public SourceLocation instantiateSourceLocation720() {
		return new SourceLocation("foo", 1);
	}

	/**
	 * Test method for the class com.google.test.metric.report.issues.Issue
	 */
	public void testIssue1282() throws Exception {
		SourceLocation var3995 = instantiateSourceLocation720();
		String var3996 = "foo()";
		Issue var3997 = new Issue(var3995, var3996, 1.0f,
				IssueType.CONSTRUCTION, IssueSubType.COMPLEXITY);
	}

	public SourceLocation instantiateSourceLocation721() {
		return new SourceLocation("com/google/FooClass.java", 1);
	}

	/**
	 * Test method for the class com.google.test.metric.report.issues.Issue
	 */
	public void testIssue1283() throws Exception {
		SourceLocation var3998 = instantiateSourceLocation721();
		String var3991 = "foo()";
		Issue var3999 = new Issue(var3998, var3991, 0.5f, DIRECT_COST,
				STATIC_METHOD);
	}

	public SourceLocation instantiateSourceLocation722() {
		return new SourceLocation("com/google/FooClass.java", 1);
	}

	/**
	 * Test method for the class com.google.test.metric.report.issues.Issue
	 */
	public void testIssue1284() throws Exception {
		SourceLocation var4000 = instantiateSourceLocation722();
		String var3991 = "foo()";
		Issue var4001 = new Issue(var4000, var3991, 0.9f, DIRECT_COST,
				COMPLEXITY);
	}
}