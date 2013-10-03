package com.google.test.metric.report;


import com.google.test.metric.Reason;
import com.google.test.metric.CyclomaticCost;
import com.google.test.metric.CostModel;
import com.google.test.metric.testing.MetricComputerBuilder;
import com.google.test.metric.ClassInfo;
import com.google.test.metric.JavaClassRepository;
import com.google.test.metric.MetricComputer;
import com.google.test.metric.Cost;
import java.lang.String;
import java.io.PrintStream;
import junit.framework.TestCase;
import com.google.test.metric.MethodCost;
import com.google.test.metric.MethodInvocationCost;
import com.google.test.metric.SourceLocation;
import com.google.test.metric.ClassRepository;
import com.google.test.metric.GlobalCost;
import java.io.ByteArrayOutputStream;
import com.google.test.metric.ClassCost;
import static com.google.test.metric.Reason.NON_OVERRIDABLE_METHOD_CALL;
import static com.google.test.metric.report.DrillDownReportGenerator.NEW_LINE;
import static java.lang.Integer.MAX_VALUE;
import static com.google.test.metric.report.FreemarkerReportGenerator.HTML_REPORT_TEMPLATE;
import static java.text.MessageFormat.format;
import static java.util.ResourceBundle.getBundle;
import static com.google.test.metric.Reason.IMPLICIT_STATIC_INIT;
import static java.util.Arrays.asList;

public class TextReportGeneratorGenTest extends TestCase {
	public CostModel instantiateCostModel534() {
		return new CostModel();
	}

	/**
	 * Test method for the class com.google.test.metric.report.TextReportGenerator
	 */
	public void testTextReportGenerator1005() throws Exception {
		CostModel var3229 = instantiateCostModel534();
		ByteArrayOutputStream var3230 = new ByteArrayOutputStream();
		TextReportGenerator var3231 = new TextReportGenerator(new PrintStream(
				var3230), var3229, 0, 0, 0);
		ClassRepository var3232 = new JavaClassRepository();
		MetricComputer var3233 = new MetricComputerBuilder()
				.withClassRepository(var3232).build();
		ClassInfo var3234 = var3232.getClass(Example.class.getCanonicalName());
		ClassCost var3235 = var3233.compute(var3234);
		var3229.computeClass(var3235);
		var3229.computeClass(var3235);
		var3231.addClassCost(classCost("c.g.t.A", 1));
		var3231.addClassCost(classCost("c.g.t.B", 70));
		var3231.addClassCost(classCost("c.g.t.C", 70));
		var3231.addClassCost(classCost("c.g.t.D", 101));
		var3231.addClassCost(classCost("c.g.t.E", 101));
		var3231.addClassCost(classCost("c.g.t.F", 101));
		var3231.printSummary();
		var3231.addClassCost(classCost("c.g.t.A", 1));
		var3231.addClassCost(classCost("c.g.t.B", 70));
		var3231.addClassCost(classCost("c.g.t.C", 70));
		var3231.addClassCost(classCost("c.g.t.D", 101));
		var3231.addClassCost(classCost("c.g.t.E", 101));
		var3231.addClassCost(classCost("c.g.t.F", 101));
		var3231.printSummary();
	}

	public CostModel instantiateCostModel535() {
		return new CostModel();
	}

	/**
	 * Test method for the class com.google.test.metric.report.TextReportGenerator
	 */
	public void testTextReportGenerator1006() throws Exception {
		CostModel var3238 = instantiateCostModel535();
		ByteArrayOutputStream var3239 = new ByteArrayOutputStream();
		TextReportGenerator var3240 = new TextReportGenerator(new PrintStream(
				var3239), var3238, 50, 100, 0);
		ClassRepository var3232 = new JavaClassRepository();
		MetricComputer var3233 = new MetricComputerBuilder()
				.withClassRepository(var3232).build();
		ClassInfo var3234 = var3232.getClass(Example.class.getCanonicalName());
		ClassCost var3235 = var3233.compute(var3234);
		var3238.computeClass(var3235);
		var3238.computeClass(var3235);
		var3240.addClassCost(classCost("c.g.t.A", 1));
		var3240.addClassCost(classCost("c.g.t.B", 10));
		var3240.addClassCost(classCost("c.g.t.C", 15));
		var3240.addClassCost(classCost("c.g.t.D", 30));
		var3240.addClassCost(classCost("c.g.t.E", 31));
		var3240.addClassCost(classCost("c.g.t.F", 32));
		var3240.printDistribution(3, 50);
		var3240.addClassCost(classCost("c.g.t.A", 1));
		var3240.addClassCost(classCost("c.g.t.B", 10));
		var3240.addClassCost(classCost("c.g.t.C", 15));
		var3240.addClassCost(classCost("c.g.t.D", 30));
		var3240.addClassCost(classCost("c.g.t.E", 31));
		var3240.addClassCost(classCost("c.g.t.F", 32));
		var3240.printDistribution(3, 50);
	}

	public CostModel instantiateCostModel536() {
		return new CostModel();
	}

	/**
	 * Test method for the class com.google.test.metric.report.TextReportGenerator
	 */
	public void testTextReportGenerator1007() throws Exception {
		CostModel var3241 = instantiateCostModel536();
		ByteArrayOutputStream var3230 = new ByteArrayOutputStream();
		TextReportGenerator var3242 = new TextReportGenerator(new PrintStream(
				var3230), var3241, 0, 0, 0);
		ClassRepository var3232 = new JavaClassRepository();
		MetricComputer var3233 = new MetricComputerBuilder()
				.withClassRepository(var3232).build();
		ClassInfo var3234 = var3232.getClass(Example.class.getCanonicalName());
		ClassCost var3235 = var3233.compute(var3234);
		var3241.computeClass(var3235);
		var3241.computeClass(var3235);
		var3242.addClassCost(classCost("c.g.t.A", 1));
		var3242.addClassCost(classCost("c.g.t.B", 10));
		var3242.addClassCost(classCost("c.g.t.C", 15));
		var3242.addClassCost(classCost("c.g.t.D", 30));
		var3242.addClassCost(classCost("c.g.t.E", 31));
		var3242.addClassCost(classCost("c.g.t.F", 32));
		var3242.printDistribution(3, 50);
		var3242.addClassCost(classCost("c.g.t.A", 1));
		var3242.addClassCost(classCost("c.g.t.B", 10));
		var3242.addClassCost(classCost("c.g.t.C", 15));
		var3242.addClassCost(classCost("c.g.t.D", 30));
		var3242.addClassCost(classCost("c.g.t.E", 31));
		var3242.addClassCost(classCost("c.g.t.F", 32));
		var3242.printDistribution(3, 50);
	}

	public CostModel instantiateCostModel537() {
		return new CostModel(1, 1, 1);
	}

	/**
	 * Test method for the class com.google.test.metric.report.TextReportGenerator
	 */
	public void testTextReportGenerator1008() throws Exception {
		CostModel var3243 = instantiateCostModel537();
		ByteArrayOutputStream var3239 = new ByteArrayOutputStream();
		TextReportGenerator var3244 = new TextReportGenerator(new PrintStream(
				var3239), var3243, 50, 100, 0);
		MethodCost var3245 = new MethodCost("", "a", 0, false, false, false);
		var3245.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3245.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		MethodCost var3246 = new MethodCost("", "b", 0, false, false, false);
		var3246.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3246.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3246.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3245.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var3246, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var3245.link();
		var3245.getTotalCost();
		var3243.computeOverall(var3245.getTotalCost());
		var3245.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3245.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		var3246.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3246.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3246.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3245.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var3246, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var3245.link();
		var3245.getTotalCost();
		var3243.computeOverall(var3245.getTotalCost());
		var3244.addClassCost(classCost("c.g.t.A", 1));
		var3244.addClassCost(classCost("c.g.t.B", 70));
		var3244.addClassCost(classCost("c.g.t.C", 70));
		var3244.addClassCost(classCost("c.g.t.D", 101));
		var3244.addClassCost(classCost("c.g.t.E", 101));
		var3244.addClassCost(classCost("c.g.t.F", 101));
		var3244.printSummary();
		var3244.addClassCost(classCost("c.g.t.A", 1));
		var3244.addClassCost(classCost("c.g.t.B", 70));
		var3244.addClassCost(classCost("c.g.t.C", 70));
		var3244.addClassCost(classCost("c.g.t.D", 101));
		var3244.addClassCost(classCost("c.g.t.E", 101));
		var3244.addClassCost(classCost("c.g.t.F", 101));
		var3244.printSummary();
	}

	public CostModel instantiateCostModel538() {
		return new CostModel();
	}

	/**
	 * Test method for the class com.google.test.metric.report.TextReportGenerator
	 */
	public void testTextReportGenerator1009() throws Exception {
		CostModel var3248 = instantiateCostModel538();
		ByteArrayOutputStream var3239 = new ByteArrayOutputStream();
		TextReportGenerator var3249 = new TextReportGenerator(new PrintStream(
				var3239), var3248, 50, 100, 0);
		ClassRepository var3232 = new JavaClassRepository();
		MetricComputer var3233 = new MetricComputerBuilder()
				.withClassRepository(var3232).build();
		ClassInfo var3234 = var3232.getClass(Example.class.getCanonicalName());
		ClassCost var3235 = var3233.compute(var3234);
		var3248.computeClass(var3235);
		var3248.computeClass(var3235);
		var3249.addClassCost(classCost("c.g.t.A", 1));
		var3249.addClassCost(classCost("c.g.t.B", 70));
		var3249.addClassCost(classCost("c.g.t.C", 70));
		var3249.addClassCost(classCost("c.g.t.D", 101));
		var3249.addClassCost(classCost("c.g.t.E", 101));
		var3249.addClassCost(classCost("c.g.t.F", 101));
		var3249.printSummary();
		var3249.addClassCost(classCost("c.g.t.A", 1));
		var3249.addClassCost(classCost("c.g.t.B", 70));
		var3249.addClassCost(classCost("c.g.t.C", 70));
		var3249.addClassCost(classCost("c.g.t.D", 101));
		var3249.addClassCost(classCost("c.g.t.E", 101));
		var3249.addClassCost(classCost("c.g.t.F", 101));
		var3249.printSummary();
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