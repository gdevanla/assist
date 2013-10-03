package com.google.test.metric.report;


import java.util.ArrayList;
import com.google.test.metric.Reason;
import com.google.test.metric.CyclomaticCost;
import com.google.test.metric.CostModel;
import com.google.test.metric.testing.MetricComputerBuilder;
import com.google.test.metric.ClassInfo;
import com.google.test.metric.JavaClassRepository;
import com.google.test.metric.MetricComputer;
import com.google.test.metric.Cost;
import java.lang.Integer;
import java.lang.String;
import java.io.PrintStream;
import junit.framework.TestCase;
import com.google.test.metric.MethodCost;
import com.google.test.metric.MethodInvocationCost;
import com.google.test.metric.SourceLocation;
import com.google.test.metric.ClassRepository;
import com.google.test.metric.GlobalCost;
import com.google.test.metric.ClassCost;
import java.io.ByteArrayOutputStream;
import static com.google.test.metric.Reason.NON_OVERRIDABLE_METHOD_CALL;
import static com.google.test.metric.report.DrillDownReportGenerator.NEW_LINE;
import static java.lang.Integer.MAX_VALUE;
import static com.google.test.metric.report.FreemarkerReportGenerator.HTML_REPORT_TEMPLATE;
import static java.text.MessageFormat.format;
import static java.util.ResourceBundle.getBundle;
import static com.google.test.metric.Reason.IMPLICIT_STATIC_INIT;

public class DrillDownReportGeneratorGenTest extends TestCase {
	public CostModel instantiateCostModel574() {
		return new CostModel();
	}

	/**
	 * Test method for the class com.google.test.metric.report.DrillDownReportGenerator
	 */
	public void testDrillDownReportGenerator1030() throws Exception {
		CostModel var3404 = instantiateCostModel574();
		DrillDownReportGenerator var3405 = new DrillDownReportGenerator(
				new PrintStream(new ByteArrayOutputStream()), var3404, null,
				Integer.MAX_VALUE, 0);
		ClassRepository var3406 = new JavaClassRepository();
		MetricComputer var3407 = new MetricComputerBuilder()
				.withClassRepository(var3406).build();
		ClassInfo var3408 = var3406.getClass(Example.class.getCanonicalName());
		ClassCost var3409 = var3407.compute(var3408);
		var3404.computeClass(var3409);
		var3404.computeClass(var3409);
		MethodCost var3411 = new MethodCost("", "c.g.t.A.method1()V", 0, false,
				false, false);
		var3411.addCostSource(new CyclomaticCost(new SourceLocation(null, 1),
				Cost.cyclomatic(1)));
		var3411.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		var3411.link();
		var3405.print("", var3411, Integer.MAX_VALUE);
		MethodCost var3413 = new MethodCost("", "c.g.t.A.method2()V", 0, false,
				false, false);
		var3413.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3413.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3405.print("", var3413, MAX_VALUE);
	}

	public CostModel instantiateCostModel575() {
		return new CostModel();
	}

	/**
	 * Test method for the class com.google.test.metric.report.DrillDownReportGenerator
	 */
	public void testDrillDownReportGenerator1031() throws Exception {
		CostModel var3415 = instantiateCostModel575();
		ByteArrayOutputStream var3416 = new ByteArrayOutputStream();
		DrillDownReportGenerator var3417 = new DrillDownReportGenerator(
				new PrintStream(var3416), var3415, null, MAX_VALUE, 4);
		ClassRepository var3406 = new JavaClassRepository();
		MetricComputer var3407 = new MetricComputerBuilder()
				.withClassRepository(var3406).build();
		ClassInfo var3408 = var3406.getClass(Example.class.getCanonicalName());
		ClassCost var3409 = var3407.compute(var3408);
		var3415.computeClass(var3409);
		var3415.computeClass(var3409);
		MethodCost var3411 = new MethodCost("", "c.g.t.A.method1()V", 0, false,
				false, false);
		var3411.addCostSource(new CyclomaticCost(new SourceLocation(null, 1),
				Cost.cyclomatic(1)));
		var3411.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		var3411.link();
		var3417.print("", var3411, Integer.MAX_VALUE);
		MethodCost var3418 = new MethodCost("", "c.g.t.A.method1()V", 0, false,
				false, false);
		var3418.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3417.print("", var3418, MAX_VALUE);
	}

	public CostModel instantiateCostModel576() {
		return new CostModel(1, 1, 1);
	}

	/**
	 * Test method for the class com.google.test.metric.report.DrillDownReportGenerator
	 */
	public void testDrillDownReportGenerator1032() throws Exception {
		CostModel var3420 = instantiateCostModel576();
		ByteArrayOutputStream var3416 = new ByteArrayOutputStream();
		DrillDownReportGenerator var3421 = new DrillDownReportGenerator(
				new PrintStream(var3416), var3420, null, MAX_VALUE, 2);
		MethodCost var3422 = new MethodCost("", "a", 0, false, false, false);
		var3422.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3422.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		MethodCost var3423 = new MethodCost("", "b", 0, false, false, false);
		var3423.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3423.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3423.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3422.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var3423, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var3422.link();
		var3422.getTotalCost();
		var3420.computeOverall(var3422.getTotalCost());
		var3422.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3422.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		var3423.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3423.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3423.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3422.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var3423, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var3422.link();
		var3422.getTotalCost();
		var3420.computeOverall(var3422.getTotalCost());
		MethodCost var3411 = new MethodCost("", "c.g.t.A.method1()V", 0, false,
				false, false);
		var3411.addCostSource(new CyclomaticCost(new SourceLocation(null, 1),
				Cost.cyclomatic(1)));
		var3411.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		var3411.link();
		var3421.print("", var3411, Integer.MAX_VALUE);
		ClassCost var3425 = new ClassCost("FAKE_classInfo0",
				new ArrayList<MethodCost>());
		var3421.addClassCost(var3425);
		var3421.printFooter();
	}

	public CostModel instantiateCostModel577() {
		return new CostModel(1, 1, 10);
	}

	/**
	 * Test method for the class com.google.test.metric.report.DrillDownReportGenerator
	 */
	public void testDrillDownReportGenerator1033() throws Exception {
		CostModel var3427 = instantiateCostModel577();
		ByteArrayOutputStream var3416 = new ByteArrayOutputStream();
		DrillDownReportGenerator var3428 = new DrillDownReportGenerator(
				new PrintStream(var3416), var3427, null, MAX_VALUE, 0);
		MethodCost var3422 = new MethodCost("", "a", 0, false, false, false);
		var3422.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3422.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		MethodCost var3423 = new MethodCost("", "b", 0, false, false, false);
		var3423.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3423.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3423.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3422.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var3423, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var3422.link();
		var3422.getTotalCost();
		var3427.computeOverall(var3422.getTotalCost());
		var3422.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3422.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		var3423.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3423.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3423.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3422.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var3423, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var3422.link();
		var3422.getTotalCost();
		var3427.computeOverall(var3422.getTotalCost());
		MethodCost var3429 = new MethodCost("", "c.g.t.A.method3()V", 0, false,
				false, false);
		var3429.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3429.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3429.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3428.print("", var3429, 2);
		ClassCost var3431 = new ClassCost("FAKE_classInfo0",
				new ArrayList<MethodCost>());
		var3428.addClassCost(var3431);
		ClassCost var3433 = new ClassCost("FAKE_classInfo1",
				new ArrayList<MethodCost>());
		var3428.addClassCost(var3433);
		ClassCost var3434 = new ClassCost("FAKE_classInfo2",
				new ArrayList<MethodCost>());
		var3428.addClassCost(var3434);
		var3428.printFooter();
	}

	public CostModel instantiateCostModel578() {
		return new CostModel();
	}

	/**
	 * Test method for the class com.google.test.metric.report.DrillDownReportGenerator
	 */
	public void testDrillDownReportGenerator1034() throws Exception {
		CostModel var3435 = instantiateCostModel578();
		ByteArrayOutputStream var3416 = new ByteArrayOutputStream();
		DrillDownReportGenerator var3436 = new DrillDownReportGenerator(
				new PrintStream(var3416), var3435, null, MAX_VALUE, 0);
		ClassRepository var3406 = new JavaClassRepository();
		MetricComputer var3407 = new MetricComputerBuilder()
				.withClassRepository(var3406).build();
		ClassInfo var3408 = var3406.getClass(Example.class.getCanonicalName());
		ClassCost var3409 = var3407.compute(var3408);
		var3435.computeClass(var3409);
		var3435.computeClass(var3409);
		MethodCost var3429 = new MethodCost("", "c.g.t.A.method3()V", 0, false,
				false, false);
		var3429.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3429.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3429.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3436.print("", var3429, MAX_VALUE);
		MethodCost var3418 = new MethodCost("", "c.g.t.A.method1()V", 0, false,
				false, false);
		var3418.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3436.print("", var3418, MAX_VALUE);
	}

	private static class Example {
		public Example() {
			new CostUtil().instanceCost4();
		}

		public int doThing() {
			new CostUtil().instanceCost3();
			return 1;
		}
	}
}