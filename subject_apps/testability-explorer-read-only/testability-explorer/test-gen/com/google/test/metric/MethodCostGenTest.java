package com.google.test.metric;


import com.google.test.metric.Reason;
import com.google.test.metric.MethodCost;
import com.google.test.metric.CyclomaticCost;
import com.google.test.metric.MethodInvocationCost;
import java.util.List;
import java.lang.String;
import com.google.test.metric.Cost;
import com.google.test.metric.SourceLocation;
import junit.framework.TestCase;
import com.google.test.metric.ViolationCost;
import com.google.test.metric.GlobalCost;
import static com.google.test.metric.Reason.NON_OVERRIDABLE_METHOD_CALL;
import static com.google.test.metric.report.DrillDownReportGenerator.NEW_LINE;
import static java.lang.Integer.MAX_VALUE;
import static com.google.test.metric.report.FreemarkerReportGenerator.HTML_REPORT_TEMPLATE;
import static java.text.MessageFormat.format;
import static java.util.ResourceBundle.getBundle;
import static com.google.test.metric.Reason.IMPLICIT_STATIC_INIT;
import static java.util.Arrays.asList;

public class MethodCostGenTest extends TestCase {
	/**
	 * Test method for the class com.google.test.metric.MethodCost
	 */
	public void testMethodCost1055() throws Exception {
		MethodCost var3485 = new MethodCost("", "methodName", 1, false, false,
				false);
		var3485.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3485.link();
		var3485.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3485.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
	}

	/**
	 * Test method for the class com.google.test.metric.MethodCost
	 */
	public void testMethodCost1056() throws Exception {
		MethodCost var3488 = new MethodCost("", "void translation_unit()", 1,
				false, false, false);
		var3488.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3488.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		MethodCost var3490 = new MethodCost("", "b", 0, false, false, false);
		var3490.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3490.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3490.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3488.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var3490, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var3488.link();
		var3488.getTotalCost();
		var3488.getExplicitViolationCosts();
		var3488.getImplicitViolationCosts();
		var3488.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3488.link();
	}

	/**
	 * Test method for the class com.google.test.metric.MethodCost
	 */
	public void testMethodCost1057() throws Exception {
		MethodCost var3492 = new MethodCost("", "c.g.t.A.method1()V", 0, false,
				false, false);
		var3492.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3492.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3492.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		SourceLocation var3493 = new SourceLocation("com/google/FooClass.java",
				1);
		MethodCost var3494 = new MethodCost("", "void setUp()", 1, false,
				false, false);
		var3494.addCostSource(new MethodInvocationCost(var3493, var3494,
				Reason.IMPLICIT_SETTER, new Cost(100, 1, new int[0])));
		var3492.addCostSource(new MethodInvocationCost(var3493, var3494,
				Reason.IMPLICIT_SETTER, new Cost(100, 1, new int[0])));
	}

	/**
	 * Test method for the class com.google.test.metric.MethodCost
	 */
	public void testMethodCost1058() throws Exception {
		MethodCost var3495 = new MethodCost("", "c.g.t.A.method3()V", 0, false,
				false, false);
		var3495.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3495.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3495.getDescription();
	}

	/**
	 * Test method for the class com.google.test.metric.MethodCost
	 */
	public void testMethodCost1059() throws Exception {
		MethodCost var3497 = new MethodCost("Foo", "doThing()", 1, false,
				false, false);
		var3497.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3497.link();
		var3497.getDescription();
	}
}