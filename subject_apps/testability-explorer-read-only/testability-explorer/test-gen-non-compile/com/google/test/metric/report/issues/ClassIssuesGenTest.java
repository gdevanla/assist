package com.google.test.metric.report.issues;


import java.util.LinkedList;
import com.google.test.metric.report.issues.ClassIssues.TotalCostComparator;
import com.google.test.metric.report.issues.TriageIssuesQueue;
import com.google.test.metric.report.issues.Issue;
import com.google.test.metric.report.issues.ClassIssues;
import com.google.common.collect.Lists;
import java.util.Map;
import java.util.List;
import java.lang.String;
import com.google.test.metric.SourceLocation;
import junit.framework.TestCase;
import java.util.Queue;
import static com.google.test.metric.report.issues.IssueSubType.COMPLEXITY;
import static com.google.test.metric.report.issues.IssueSubType.NON_MOCKABLE;
import static com.google.test.metric.report.issues.IssueSubType.SETTER;
import static com.google.test.metric.report.issues.IssueSubType.STATIC_INIT;
import static com.google.test.metric.report.issues.IssueSubType.STATIC_METHOD;
import static com.google.test.metric.report.issues.IssueType.CONSTRUCTION;

public class ClassIssuesGenTest extends TestCase {
	public TriageIssuesQueue<ClassIssues> instantiateTriageIssuesQueue703() {
		return new TriageIssuesQueue<ClassIssues>(100, 2,
				new ClassIssues.TotalCostComparator());
	}

	/**
	 * Test method for the class com.google.test.metric.report.issues.ClassIssues
	 */
	public void testClassIssues1245() throws Exception {
		TriageIssuesQueue<ClassIssues> var3918 = instantiateTriageIssuesQueue703();
		Queue<Issue> var3919;
		var3919 = Lists.newLinkedList();
		ClassIssues var3920 = new ClassIssues("Foo", 100, var3919);
		ClassIssues var3921 = new ClassIssues("BadClass", 500);
		Issue var3922;
		var3922 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var3921.add(var3922);
		var3918.offer(var3921);
		ClassIssues var3924 = new ClassIssues("PrettyGoodClass", 101);
		var3922 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var3924.add(var3922);
		var3918.offer(var3924);
		var3918.asList();
		var3918.offer(new ClassIssues("foo", 0));
		var3918.isEmpty();
		var3920.getConstructionIssues();
		var3920.getConstructionIssues();
	}

	public TriageIssuesQueue<ClassIssues> instantiateTriageIssuesQueue704() {
		return new TriageIssuesQueue<ClassIssues>(100, 2,
				new ClassIssues.TotalCostComparator());
	}

	/**
	 * Test method for the class com.google.test.metric.report.issues.ClassIssues
	 */
	public void testClassIssues1246() throws Exception {
		TriageIssuesQueue<ClassIssues> var3927 = instantiateTriageIssuesQueue704();
		Queue<Issue> var3919;
		var3919 = Lists.newLinkedList();
		ClassIssues var3920 = new ClassIssues("Foo", 100, var3919);
		var3927.offer(new ClassIssues("foo", 0));
		var3927.isEmpty();
		int var3928 = 50;
		ClassIssues var3929 = new ClassIssues("FooClass", var3928 - 1);
		Issue var3922;
		var3922 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var3929.add(var3922);
		var3927.offer(var3929);
		var3927.isEmpty();
		var3920.getConstructionIssues();
		var3920.getConstructionIssues();
	}

	public TriageIssuesQueue<ClassIssues> instantiateTriageIssuesQueue705() {
		int var3928 = 50;
		int var3932 = 10;
		return new TriageIssuesQueue<ClassIssues>(var3928, var3932,
				new ClassIssues.TotalCostComparator());
	}

	/**
	 * Test method for the class com.google.test.metric.report.issues.ClassIssues
	 */
	public void testClassIssues1247() throws Exception {
		TriageIssuesQueue<ClassIssues> var3933 = instantiateTriageIssuesQueue705();
		Queue<Issue> var3919;
		var3919 = Lists.newLinkedList();
		ClassIssues var3920 = new ClassIssues("Foo", 100, var3919);
		ClassIssues var3934 = new ClassIssues("FooClass", 100);
		Issue var3922;
		var3922 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var3934.add(var3922);
		var3933.offer(var3934);
		ClassIssues var3935 = new ClassIssues("FooClass", 300);
		var3922 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var3935.add(var3922);
		var3933.offer(var3935);
		ClassIssues var3936 = new ClassIssues("FooClass", 200);
		var3922 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var3936.add(var3922);
		var3933.offer(var3936);
		var3933.asList();
		var3933.size();
		var3920.getConstructionIssues();
		var3920.getConstructionIssues();
	}

	public TriageIssuesQueue<ClassIssues> instantiateTriageIssuesQueue706() {
		return new TriageIssuesQueue<ClassIssues>(100, 2,
				new ClassIssues.TotalCostComparator());
	}

	/**
	 * Test method for the class com.google.test.metric.report.issues.ClassIssues
	 */
	public void testClassIssues1248() throws Exception {
		TriageIssuesQueue<ClassIssues> var3939 = instantiateTriageIssuesQueue706();
		Queue<Issue> var3919;
		var3919 = Lists.newLinkedList();
		ClassIssues var3920 = new ClassIssues("Foo", 100, var3919);
		Issue var3940 = new Issue(new SourceLocation(null, 1), null, 1f, null,
				null);
		var3939.offer(var3940);
		var3939.isEmpty();
		var3939.asList();
		ClassIssues var3942 = new ClassIssues("BadClass", 500);
		Issue var3922;
		var3922 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var3942.add(var3922);
		var3939.offer(var3942);
		ClassIssues var3944 = new ClassIssues("PrettyGoodClass", 200);
		var3922 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var3944.add(var3922);
		var3939.offer(var3944);
		ClassIssues var3945 = new ClassIssues("NotGreatClass", 300);
		var3922 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var3945.add(var3922);
		var3939.offer(var3945);
		var3939.asList();
		var3939.asList();
		var3920.getConstructionIssues();
		var3920.getConstructionIssues();
	}

	public TriageIssuesQueue<ClassIssues> instantiateTriageIssuesQueue707() {
		int var3928 = 50;
		int var3932 = 10;
		return new TriageIssuesQueue<ClassIssues>(var3928, var3932,
				new ClassIssues.TotalCostComparator());
	}

	/**
	 * Test method for the class com.google.test.metric.report.issues.ClassIssues
	 */
	public void testClassIssues1249() throws Exception {
		TriageIssuesQueue<ClassIssues> var3947 = instantiateTriageIssuesQueue707();
		Queue<Issue> var3919;
		var3919 = Lists.newLinkedList();
		ClassIssues var3920 = new ClassIssues("Foo", 100, var3919);
		ClassIssues var3934 = new ClassIssues("FooClass", 100);
		Issue var3922;
		var3922 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var3934.add(var3922);
		var3947.offer(var3934);
		ClassIssues var3935 = new ClassIssues("FooClass", 300);
		var3922 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var3935.add(var3922);
		var3947.offer(var3935);
		ClassIssues var3936 = new ClassIssues("FooClass", 200);
		var3922 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var3936.add(var3922);
		var3947.offer(var3936);
		var3947.asList();
		var3947.size();
		var3920.getConstructionIssues();
		var3920.getConstructionIssues();
	}

	/**
	 * Test method for the class com.google.test.metric.report.issues.ClassIssues
	 */
	public void testClassIssues1250() throws Exception {
		ClassIssues var3949 = new ClassIssues("BadClass", 500);
		Issue var3950;
		var3950 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var3949.add(var3950);
		var3950 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var3949.add(var3950);
	}

	/**
	 * Test method for the class com.google.test.metric.report.issues.ClassIssues
	 */
	public void testClassIssues1251() throws Exception {
		ClassIssues var3949 = new ClassIssues("BadClass", 500);
		Issue var3950;
		var3950 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var3949.add(var3950);
		var3950 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var3949.add(var3950);
	}

	/**
	 * Test method for the class com.google.test.metric.report.issues.ClassIssues
	 */
	public void testClassIssues1252() throws Exception {
		ClassIssues var3955 = new ClassIssues("FooClass", 200);
		Issue var3950;
		var3950 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var3955.add(var3950);
		var3950 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var3955.add(var3950);
	}

	/**
	 * Test method for the class com.google.test.metric.report.issues.ClassIssues
	 */
	public void testClassIssues1253() throws Exception {
		ClassIssues var3957 = new ClassIssues("NotGreatClass", 300);
		Issue var3950;
		var3950 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var3957.add(var3950);
		var3950 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var3957.add(var3950);
	}

	/**
	 * Test method for the class com.google.test.metric.report.issues.ClassIssues
	 */
	public void testClassIssues1254() throws Exception {
		ClassIssues var3959 = new ClassIssues("FooClass", 100);
		Issue var3950;
		var3950 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var3959.add(var3950);
		var3950 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var3959.add(var3950);
	}
}