package com.google.test.metric;


import com.google.test.metric.Reason;
import com.google.test.metric.CyclomaticCost;
import com.google.test.metric.MethodCost;
import com.google.test.metric.MethodInvocationCost;
import java.util.List;
import java.lang.String;
import com.google.test.metric.Cost;
import com.google.test.metric.SourceLocation;
import junit.framework.TestCase;
import com.google.test.metric.ViolationCost;
import com.google.test.metric.GlobalCost;
import static com.google.test.metric.Reason.NON_OVERRIDABLE_METHOD_CALL;
import static java.util.Arrays.asList;
import static com.google.test.metric.report.issues.IssueSubType.COMPLEXITY;
import static com.google.test.metric.report.issues.IssueSubType.STATIC_METHOD;
import static com.google.test.metric.report.issues.IssueType.COLLABORATOR;
import static com.google.test.metric.report.issues.IssueType.DIRECT_COST;
import static com.google.test.metric.report.DrillDownReportGenerator.NEW_LINE;
import static java.lang.Integer.MAX_VALUE;
import static com.google.test.metric.report.FreemarkerReportGenerator.HTML_REPORT_TEMPLATE;
import static java.text.MessageFormat.format;
import static java.util.ResourceBundle.getBundle;
import static com.google.test.metric.Reason.IMPLICIT_STATIC_INIT;

public class MethodInvocationCostGenTest extends TestCase {
	public MethodCost instantiateMethodCost596() {
		return new MethodCost("", "M1", -1, false, false, false);
	}

	public SourceLocation instantiateSourceLocation597() {
		return new SourceLocation("com/google/Foo.java", 12);
	}

	public Cost instantiateCost598() {
		return new Cost();
	}

	/**
	 * Test method for the class com.google.test.metric.MethodInvocationCost
	 */
	public void testMethodInvocationCost1060() throws Exception {
		MethodCost var3499 = instantiateMethodCost596();
		Cost var3500 = instantiateCost598();
		SourceLocation var3501 = instantiateSourceLocation597();
		var3502.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		MethodInvocationCost var3503 = new MethodInvocationCost(var3501,
				var3499, NON_OVERRIDABLE_METHOD_CALL, var3500);
		var3499.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3499.link();
		var3499.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3499.link();
		var3500.add(new Cost());
		var3500.add(new Cost());
	}

	public MethodCost instantiateMethodCost599() {
		return new MethodCost("", "a", 0, false, false, false);
	}

	public SourceLocation instantiateSourceLocation600() {
		return new SourceLocation("com/google/FooClass.java", 1);
	}

	/**
	 * Test method for the class com.google.test.metric.MethodInvocationCost
	 */
	public void testMethodInvocationCost1061() throws Exception {
		MethodCost var3506 = instantiateMethodCost599();
		SourceLocation var3507 = instantiateSourceLocation600();
		var3508.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3508.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3508.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		MethodInvocationCost var3509 = new MethodInvocationCost(var3507,
				var3506, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3));
		var3506.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		MethodCost var3510 = new MethodCost("", "c.g.t.A.method0()V", 0, false,
				false, false);
		var3506.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				8), var3510, NON_OVERRIDABLE_METHOD_CALL, new Cost()));
		MethodCost var3511 = new MethodCost("", "c.g.t.A.method3()V", 0, false,
				false, false);
		var3511.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3511.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3511.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3506.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				13), var3511, NON_OVERRIDABLE_METHOD_CALL, Cost.cyclomatic(3)));
		var3506.link();
		var3506.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3506.link();
	}

	public MethodCost instantiateMethodCost601() {
		return new MethodCost("Foo", "hasIndirect()", 2, false, false, false);
	}

	public SourceLocation instantiateSourceLocation602() {
		return new SourceLocation("com/google/Foo.java", 12);
	}

	public Cost instantiateCost603() {
		return new Cost();
	}

	/**
	 * Test method for the class com.google.test.metric.MethodInvocationCost
	 */
	public void testMethodInvocationCost1062() throws Exception {
		MethodCost var3512 = instantiateMethodCost601();
		Cost var3513 = instantiateCost603();
		SourceLocation var3514 = instantiateSourceLocation602();
		var3515.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3515.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		MethodInvocationCost var3516 = new MethodInvocationCost(var3514,
				var3512, NON_OVERRIDABLE_METHOD_CALL, var3513);
		var3512.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3512.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		MethodCost var3502 = new MethodCost("", "c.g.t.A.method1()V", 0, false,
				false, false);
		var3502.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3512.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				8), var3502, NON_OVERRIDABLE_METHOD_CALL, Cost.cyclomatic(1)));
		var3512.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3512.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		MethodCost var3508 = new MethodCost("", "b", 0, false, false, false);
		var3508.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3508.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3508.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3512.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var3508, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var3512.link();
		var3512.getTotalCost();
		var3512.getExplicitViolationCosts();
		var3512.getImplicitViolationCosts();
		var3513.add(new Cost());
		var3513.add(new Cost());
	}

	public MethodCost instantiateMethodCost604() {
		return new MethodCost("", "c.g.t.A.method0()V", 0, false, false, false);
	}

	public SourceLocation instantiateSourceLocation605() {
		return new SourceLocation("foo", 1);
	}

	/**
	 * Test method for the class com.google.test.metric.MethodInvocationCost
	 */
	public void testMethodInvocationCost1063() throws Exception {
		MethodCost var3518 = instantiateMethodCost604();
		SourceLocation var3519 = instantiateSourceLocation605();
		var3511.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3511.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3511.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		MethodInvocationCost var3520 = new MethodInvocationCost(var3519,
				var3518, NON_OVERRIDABLE_METHOD_CALL, Cost.cyclomatic(3));
		var3518.getDescription();
		var3518.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3518.link();
	}

	public MethodCost instantiateMethodCost606() {
		return new MethodCost("", "c.g.t.A.method3()V", 0, false, false, false);
	}

	public SourceLocation instantiateSourceLocation607() {
		return new SourceLocation("com/google/FooClass.java", 1);
	}

	public Cost instantiateCost608() {
		return new Cost();
	}

	/**
	 * Test method for the class com.google.test.metric.MethodInvocationCost
	 */
	public void testMethodInvocationCost1064() throws Exception {
		MethodCost var3523 = instantiateMethodCost606();
		Cost var3524 = instantiateCost608();
		SourceLocation var3525 = instantiateSourceLocation607();
		var3502.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		MethodInvocationCost var3526 = new MethodInvocationCost(var3525,
				var3523, NON_OVERRIDABLE_METHOD_CALL, var3524);
		var3523.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3523.link();
		var3523.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3523.link();
		var3524.add(new Cost());
		var3524.add(new Cost());
	}
}