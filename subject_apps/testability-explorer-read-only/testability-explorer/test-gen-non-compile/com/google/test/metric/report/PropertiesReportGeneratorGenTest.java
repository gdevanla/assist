package com.google.test.metric.report;


import com.google.test.metric.Reason;
import com.google.test.metric.CostModel;
import com.google.test.metric.CyclomaticCost;
import com.google.test.metric.ClassInfo;
import com.google.test.metric.testing.MetricComputerBuilder;
import java.util.List;
import com.google.test.metric.MetricComputer;
import com.google.test.metric.JavaClassRepository;
import com.google.test.metric.Cost;
import java.lang.String;
import junit.framework.TestCase;
import com.google.test.metric.MethodCost;
import com.google.test.metric.MethodInvocationCost;
import com.google.test.metric.SourceLocation;
import java.util.Arrays;
import com.google.test.metric.ClassRepository;
import java.io.ByteArrayOutputStream;
import com.google.test.metric.GlobalCost;
import com.google.test.metric.ClassCost;
import static com.google.test.metric.Reason.NON_OVERRIDABLE_METHOD_CALL;
import static com.google.test.metric.Reason.IMPLICIT_STATIC_INIT;

public class PropertiesReportGeneratorGenTest extends TestCase {
	public CostModel instantiateCostModel510() {
		return new CostModel(1, 1, 10);
	}

	/**
	 * Test method for the class com.google.test.metric.report.PropertiesReportGenerator
	 */
	public void testPropertiesReportGenerator980() throws Exception {
		CostModel var3105 = instantiateCostModel510();
		ByteArrayOutputStream var3106 = new ByteArrayOutputStream();
		PropertiesReportGenerator var3107 = new PropertiesReportGenerator(
				var3106, var3105);
		MethodCost var3108 = new MethodCost("", "a", 0, false, false, false);
		var3108.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3108.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		MethodCost var3109 = new MethodCost("", "b", 0, false, false, false);
		var3109.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3109.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3109.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3108.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var3109, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var3108.link();
		var3108.getTotalCost();
		var3105.computeOverall(var3108.getTotalCost());
		var3108.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3108.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		var3109.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3109.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3109.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3108.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var3109, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var3108.link();
		var3108.getTotalCost();
		var3105.computeOverall(var3108.getTotalCost());
		String var3111 = "com.google.foo.Bar";
		MethodCost var3112 = new MethodCost("", "doThing", 3, false, false,
				false);
		var3112.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3112.link();
		ClassCost var3113 = new ClassCost(var3111, Arrays.asList(var3112));
		var3107.addClassCost(var3113);
		var3107.printFooter();
		var3112.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3112.link();
		var3107.addClassCost(var3113);
		var3107.printFooter();
	}

	public CostModel instantiateCostModel511() {
		return new CostModel();
	}

	/**
	 * Test method for the class com.google.test.metric.report.PropertiesReportGenerator
	 */
	public void testPropertiesReportGenerator981() throws Exception {
		CostModel var3115 = instantiateCostModel511();
		ByteArrayOutputStream var3106 = new ByteArrayOutputStream();
		PropertiesReportGenerator var3116 = new PropertiesReportGenerator(
				var3106, var3115);
		ClassRepository var3117 = new JavaClassRepository();
		MetricComputer var3118 = new MetricComputerBuilder()
				.withClassRepository(var3117).build();
		ClassInfo var3119 = var3117.getClass(Example.class.getCanonicalName());
		ClassCost var3120 = var3118.compute(var3119);
		var3115.computeClass(var3120);
		var3115.computeClass(var3120);
		String var3111 = "com.google.foo.Bar";
		MethodCost var3112 = new MethodCost("", "doThing", 3, false, false,
				false);
		var3112.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3112.link();
		ClassCost var3113 = new ClassCost(var3111, Arrays.asList(var3112));
		var3116.addClassCost(var3113);
		var3116.printFooter();
		var3112.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3112.link();
		var3116.addClassCost(var3113);
		var3116.printFooter();
	}

	public CostModel instantiateCostModel512() {
		return new CostModel(1, 1, 1);
	}

	/**
	 * Test method for the class com.google.test.metric.report.PropertiesReportGenerator
	 */
	public void testPropertiesReportGenerator982() throws Exception {
		CostModel var3122 = instantiateCostModel512();
		ByteArrayOutputStream var3106 = new ByteArrayOutputStream();
		PropertiesReportGenerator var3123 = new PropertiesReportGenerator(
				var3106, var3122);
		MethodCost var3108 = new MethodCost("", "a", 0, false, false, false);
		var3108.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3108.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		MethodCost var3109 = new MethodCost("", "b", 0, false, false, false);
		var3109.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3109.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3109.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3108.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var3109, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var3108.link();
		var3108.getTotalCost();
		var3122.computeOverall(var3108.getTotalCost());
		var3108.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3108.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		var3109.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3109.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3109.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3108.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var3109, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var3108.link();
		var3108.getTotalCost();
		var3122.computeOverall(var3108.getTotalCost());
		String var3111 = "com.google.foo.Bar";
		MethodCost var3112 = new MethodCost("", "doThing", 3, false, false,
				false);
		var3112.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3112.link();
		ClassCost var3113 = new ClassCost(var3111, Arrays.asList(var3112));
		var3123.addClassCost(var3113);
		var3123.printFooter();
		var3112.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3112.link();
		var3123.addClassCost(var3113);
		var3123.printFooter();
	}

	public CostModel instantiateCostModel513() {
		return new CostModel();
	}

	/**
	 * Test method for the class com.google.test.metric.report.PropertiesReportGenerator
	 */
	public void testPropertiesReportGenerator983() throws Exception {
		CostModel var3124 = instantiateCostModel513();
		ByteArrayOutputStream var3106 = new ByteArrayOutputStream();
		PropertiesReportGenerator var3125 = new PropertiesReportGenerator(
				var3106, var3124);
		ClassRepository var3117 = new JavaClassRepository();
		MetricComputer var3118 = new MetricComputerBuilder()
				.withClassRepository(var3117).build();
		ClassInfo var3119 = var3117.getClass(Example.class.getCanonicalName());
		ClassCost var3120 = var3118.compute(var3119);
		var3124.computeClass(var3120);
		var3124.computeClass(var3120);
		String var3111 = "com.google.foo.Bar";
		MethodCost var3112 = new MethodCost("", "doThing", 3, false, false,
				false);
		var3112.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3112.link();
		ClassCost var3113 = new ClassCost(var3111, Arrays.asList(var3112));
		var3125.addClassCost(var3113);
		var3125.printFooter();
		var3112.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3112.link();
		var3125.addClassCost(var3113);
		var3125.printFooter();
	}

	public CostModel instantiateCostModel514() {
		return new CostModel(1, 1, 1);
	}

	/**
	 * Test method for the class com.google.test.metric.report.PropertiesReportGenerator
	 */
	public void testPropertiesReportGenerator984() throws Exception {
		CostModel var3126 = instantiateCostModel514();
		ByteArrayOutputStream var3106 = new ByteArrayOutputStream();
		PropertiesReportGenerator var3127 = new PropertiesReportGenerator(
				var3106, var3126);
		MethodCost var3108 = new MethodCost("", "a", 0, false, false, false);
		var3108.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3108.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		MethodCost var3109 = new MethodCost("", "b", 0, false, false, false);
		var3109.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3109.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3109.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3108.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var3109, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var3108.link();
		var3108.getTotalCost();
		var3126.computeOverall(var3108.getTotalCost());
		var3108.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3108.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		var3109.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3109.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3109.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3108.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var3109, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var3108.link();
		var3108.getTotalCost();
		var3126.computeOverall(var3108.getTotalCost());
		String var3111 = "com.google.foo.Bar";
		MethodCost var3112 = new MethodCost("", "doThing", 3, false, false,
				false);
		var3112.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3112.link();
		ClassCost var3113 = new ClassCost(var3111, Arrays.asList(var3112));
		var3127.addClassCost(var3113);
		var3127.printFooter();
		var3112.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3112.link();
		var3127.addClassCost(var3113);
		var3127.printFooter();
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