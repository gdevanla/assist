package com.google.test.metric;


import com.google.test.metric.Reason;
import com.google.test.metric.CyclomaticCost;
import com.google.test.metric.MethodCost;
import com.google.test.metric.MethodInvocationCost;
import java.util.List;
import java.lang.Class;
import java.lang.String;
import com.google.test.metric.Cost;
import com.google.test.metric.SourceLocation;
import java.util.Arrays;
import junit.framework.TestCase;
import static com.google.test.metric.Reason.NON_OVERRIDABLE_METHOD_CALL;
import static java.util.Arrays.asList;
import static com.google.test.metric.report.FreemarkerReportGenerator.HTML_REPORT_TEMPLATE;
import static java.text.MessageFormat.format;
import static java.util.ResourceBundle.getBundle;

public class ClassCostGenTest extends TestCase {
	/**
	 * Test method for the class com.google.test.metric.ClassCost
	 */
	public void testClassCost1080() throws Exception {
		MethodCost var3555;
		var3555 = new MethodCost("Foo", "doThing()", 1, false, false, false);
		var3555.addCostSource(new CyclomaticCost(new SourceLocation(null, 3),
				Cost.cyclomatic(100)));
		MethodCost var3556;
		var3556 = new MethodCost("Foo", "hasIndirect()", 2, false, false, false);
		var3556.addCostSource(new CyclomaticCost(new SourceLocation(null, 4),
				Cost.cyclomatic(50)));
		var3556.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				1), var3555, NON_OVERRIDABLE_METHOD_CALL, Cost.cyclomatic(33)));
		ClassCost var3557 = new ClassCost("com.google.Foo", Arrays.asList(
				var3555, var3556));
		var3557.getPackageName();
		var3557.getTotalComplexityCost();
	}

	/**
	 * Test method for the class com.google.test.metric.ClassCost
	 */
	public void testClassCost1081() throws Exception {
		MethodCost var3555;
		var3555 = new MethodCost("Foo", "doThing()", 1, false, false, false);
		var3555.addCostSource(new CyclomaticCost(new SourceLocation(null, 3),
				Cost.cyclomatic(100)));
		ClassCost var3560 = new ClassCost("com.google.Foo",
				Arrays.asList(var3555));
		var3560.getTotalComplexityCost();
		var3560.getTotalComplexityCost();
	}

	/**
	 * Test method for the class com.google.test.metric.ClassCost
	 */
	public void testClassCost1082() throws Exception {
		MethodCost var3563 = new MethodCost("", "c.g.t.A.method0()V", 0, false,
				false, false);
		var3563.link();
		ClassCost var3564 = new ClassCost("FAKE_classInfo0", asList(var3563));
		var3564.getTotalComplexityCost();
		var3564.getTotalComplexityCost();
	}

	/**
	 * Test method for the class com.google.test.metric.ClassCost
	 */
	public void testClassCost1083() throws Exception {
		MethodCost var3565 = new MethodCost("", "void setUp()", 1, false,
				false, false);
		SourceLocation var3566 = new SourceLocation("com/google/FooClass.java",
				1);
		var3565.addCostSource(new MethodInvocationCost(var3566, var3565,
				Reason.IMPLICIT_SETTER, new Cost(100, 1, new int[0])));
		ClassCost var3567 = new ClassCost(getClass().getName(),
				Arrays.asList(var3565));
		var3567.getTotalComplexityCost();
		var3567.getTotalComplexityCost();
	}

	/**
	 * Test method for the class com.google.test.metric.ClassCost
	 */
	public void testClassCost1084() throws Exception {
		MethodCost var3563 = new MethodCost("", "c.g.t.A.method0()V", 0, false,
				false, false);
		var3563.link();
		ClassCost var3568 = new ClassCost("Dee", asList(var3563));
		var3568.getTotalComplexityCost();
		var3568.getTotalComplexityCost();
	}
}