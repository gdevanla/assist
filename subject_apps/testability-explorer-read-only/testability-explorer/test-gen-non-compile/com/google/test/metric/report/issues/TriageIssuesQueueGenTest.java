package com.google.test.metric.report.issues;


import com.google.test.metric.report.issues.ClassIssues.TotalCostComparator;
import com.google.test.metric.CostModel;
import com.google.test.metric.report.issues.Issue;
import com.google.test.metric.report.issues.ClassIssues;
import java.util.List;
import com.google.test.metric.SourceLocation;
import com.google.test.metric.ClassCost.CostComparator;
import junit.framework.TestCase;
import com.google.test.metric.report.issues.Issue.TotalCostComparator;
import com.google.test.metric.ClassCost;
import static com.google.test.metric.report.issues.IssueSubType.COMPLEXITY;
import static com.google.test.metric.report.issues.IssueSubType.NON_MOCKABLE;
import static com.google.test.metric.report.issues.IssueSubType.SETTER;
import static com.google.test.metric.report.issues.IssueSubType.STATIC_INIT;
import static com.google.test.metric.report.issues.IssueSubType.STATIC_METHOD;
import static com.google.test.metric.report.issues.IssueType.CONSTRUCTION;
import static java.util.Arrays.asList;

public class TriageIssuesQueueGenTest extends TestCase {
	public ClassCost.CostComparator instantiateCostComparator457() {
		return new ClassCost.CostComparator(new CostModel());
	}

	/**
	 * Test method for the class com.google.test.metric.report.issues.TriageIssuesQueue
	 */
	public void testTriageIssuesQueue935() throws Exception {
		ClassCost.CostComparator var2892 = instantiateCostComparator457();
		int var2893 = 50;
		int var2894 = 10;
		TriageIssuesQueue<ClassIssues> var2895 = new TriageIssuesQueue<ClassIssues>(
				var2893, var2894, new ClassIssues.TotalCostComparator());
		var2895.size();
		ClassIssues var2897 = new ClassIssues("BadClass", 500);
		Issue var2898;
		var2898 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var2897.add(var2898);
		var2895.offer(var2897);
		ClassIssues var2900 = new ClassIssues("PrettyGoodClass", 101);
		var2898 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var2900.add(var2898);
		var2895.offer(var2900);
		var2895.asList();
	}

	public ClassCost.CostComparator instantiateCostComparator458() {
		return new ClassCost.CostComparator(new CostModel());
	}

	/**
	 * Test method for the class com.google.test.metric.report.issues.TriageIssuesQueue
	 */
	public void testTriageIssuesQueue936() throws Exception {
		ClassCost.CostComparator var2901 = instantiateCostComparator458();
		TriageIssuesQueue<Issue> var2902 = new TriageIssuesQueue<Issue>(.5f,
				20, new Issue.TotalCostComparator());
		ClassIssues var2903 = new ClassIssues("FooClass", 100);
		Issue var2898;
		var2898 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var2903.add(var2898);
		var2902.offer(var2903);
		var2902.size();
		ClassIssues var2905 = new ClassIssues("FooClass", 100);
		var2898 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var2905.add(var2898);
		var2902.offer(var2905);
		ClassIssues var2906 = new ClassIssues("FooClass", 300);
		var2898 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var2906.add(var2898);
		var2902.offer(var2906);
		ClassIssues var2907 = new ClassIssues("FooClass", 200);
		var2898 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var2907.add(var2898);
		var2902.offer(var2907);
		var2902.asList();
	}

	public ClassCost.CostComparator instantiateCostComparator459() {
		return new ClassCost.CostComparator(new CostModel());
	}

	/**
	 * Test method for the class com.google.test.metric.report.issues.TriageIssuesQueue
	 */
	public void testTriageIssuesQueue937() throws Exception {
		ClassCost.CostComparator var2908 = instantiateCostComparator459();
		TriageIssuesQueue<ClassIssues> var2909 = new TriageIssuesQueue<ClassIssues>(
				100, 1, new ClassIssues.TotalCostComparator());
		ClassIssues var2910 = new ClassIssues("BadClass", 500);
		Issue var2898;
		var2898 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var2910.add(var2898);
		var2909.offer(var2910);
		ClassIssues var2912 = new ClassIssues("PrettyGoodClass", 200);
		var2898 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var2912.add(var2898);
		var2909.offer(var2912);
		ClassIssues var2913 = new ClassIssues("NotGreatClass", 300);
		var2898 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var2913.add(var2898);
		var2909.offer(var2913);
		var2909.asList();
		var2909.asList();
		var2909.size();
	}

	public ClassCost.CostComparator instantiateCostComparator460() {
		return new ClassCost.CostComparator(new CostModel());
	}

	/**
	 * Test method for the class com.google.test.metric.report.issues.TriageIssuesQueue
	 */
	public void testTriageIssuesQueue938() throws Exception {
		ClassCost.CostComparator var2914 = instantiateCostComparator460();
		TriageIssuesQueue<ClassIssues> var2909 = new TriageIssuesQueue<ClassIssues>(
				100, 1, new ClassIssues.TotalCostComparator());
		ClassIssues var2915 = new ClassIssues("FooClass", 100);
		Issue var2898;
		var2898 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var2915.add(var2898);
		var2909.offer(var2915);
		var2909.peek();
		int var2893 = 50;
		ClassIssues var2916 = new ClassIssues("FooClass", var2893 - 1);
		var2898 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var2916.add(var2898);
		var2909.offer(var2916);
		var2909.isEmpty();
	}

	public ClassCost.CostComparator instantiateCostComparator461() {
		return new ClassCost.CostComparator(new CostModel());
	}

	/**
	 * Test method for the class com.google.test.metric.report.issues.TriageIssuesQueue
	 */
	public void testTriageIssuesQueue939() throws Exception {
		ClassCost.CostComparator var2917 = instantiateCostComparator461();
		TriageIssuesQueue<ClassIssues> var2918 = new TriageIssuesQueue<ClassIssues>(
				100, 2, new ClassIssues.TotalCostComparator());
		ClassIssues var2915 = new ClassIssues("FooClass", 100);
		Issue var2898;
		var2898 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var2915.add(var2898);
		var2918.offer(var2915);
		var2918.peek();
		var2918.size();
	}
}