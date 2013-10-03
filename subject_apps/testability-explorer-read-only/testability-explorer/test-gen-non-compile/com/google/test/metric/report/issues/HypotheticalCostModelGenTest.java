package com.google.test.metric.report.issues;


import com.google.test.metric.Reason;
import com.google.test.metric.CostModel;
import com.google.test.metric.CyclomaticCost;
import java.util.List;
import com.google.test.metric.ClassInfo;
import com.google.test.metric.testing.MetricComputerBuilder;
import com.google.test.metric.MetricComputer;
import com.google.test.metric.JavaClassRepository;
import java.lang.String;
import com.google.test.metric.Cost;
import junit.framework.TestCase;
import com.google.test.metric.ViolationCost;
import com.google.test.metric.MethodCost;
import com.google.test.metric.MethodInvocationCost;
import com.google.test.metric.SourceLocation;
import java.util.Arrays;
import com.google.test.metric.ClassRepository;
import com.google.test.metric.ClassCost;
import com.google.test.metric.GlobalCost;
import static com.google.test.metric.Reason.NON_OVERRIDABLE_METHOD_CALL;
import static com.google.test.metric.Reason.IMPLICIT_STATIC_INIT;

public class HypotheticalCostModelGenTest extends TestCase {
	public CostModel instantiateCostModel559() {
		return new CostModel(1, 1, 10);
	}

	/**
	 * Test method for the class com.google.test.metric.report.issues.HypotheticalCostModel
	 */
	public void testHypotheticalCostModel1020() throws Exception {
		CostModel var3340 = instantiateCostModel559();
		HypotheticalCostModel var3341 = new HypotheticalCostModel(var3340);
		MethodCost var3342 = new MethodCost("", "a", 0, false, false, false);
		var3342.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3342.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		MethodCost var3343 = new MethodCost("", "b", 0, false, false, false);
		var3343.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3343.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3343.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3342.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var3343, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var3342.link();
		var3342.getTotalCost();
		var3340.computeOverall(var3342.getTotalCost());
		var3342.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3342.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		var3343.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3343.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3343.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3342.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var3343, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var3342.link();
		var3342.getTotalCost();
		var3340.computeOverall(var3342.getTotalCost());
		ClassRepository var3345 = new JavaClassRepository();
		MetricComputer var3346 = new MetricComputerBuilder()
				.withClassRepository(var3345).build();
		ClassInfo var3347 = var3345.getClass(Example.class.getCanonicalName());
		ClassCost var3348 = var3346.compute(var3347);
		var3341.computeClass(var3348);
		MethodCost var3350;
		var3350 = new MethodCost("Foo", "doThing()", 1, false, false, false);
		var3350.addCostSource(new CyclomaticCost(new SourceLocation(null, 3),
				Cost.cyclomatic(100)));
		ClassCost var3351 = new ClassCost("com.google.Foo",
				Arrays.asList(var3350));
		MethodCost var3352 = var3350;
		var3341.computeContributionFromMethod(var3351, var3352);
	}

	public CostModel instantiateCostModel560() {
		return new CostModel(1, 1, 1);
	}

	/**
	 * Test method for the class com.google.test.metric.report.issues.HypotheticalCostModel
	 */
	public void testHypotheticalCostModel1021() throws Exception {
		CostModel var3353 = instantiateCostModel560();
		HypotheticalCostModel var3354 = new HypotheticalCostModel(var3353);
		MethodCost var3342 = new MethodCost("", "a", 0, false, false, false);
		var3342.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3342.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		MethodCost var3343 = new MethodCost("", "b", 0, false, false, false);
		var3343.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3343.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3343.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3342.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var3343, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var3342.link();
		var3342.getTotalCost();
		var3353.computeOverall(var3342.getTotalCost());
		var3342.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3342.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		var3343.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3343.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3343.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3342.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var3343, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var3342.link();
		var3342.getTotalCost();
		var3353.computeOverall(var3342.getTotalCost());
		ClassRepository var3345 = new JavaClassRepository();
		MetricComputer var3346 = new MetricComputerBuilder()
				.withClassRepository(var3345).build();
		ClassInfo var3347 = var3345.getClass(Example.class.getCanonicalName());
		ClassCost var3348 = var3346.compute(var3347);
		var3354.computeClass(var3348);
		MethodCost var3350;
		var3350 = new MethodCost("Foo", "doThing()", 1, false, false, false);
		var3350.addCostSource(new CyclomaticCost(new SourceLocation(null, 3),
				Cost.cyclomatic(100)));
		MethodCost var3355;
		var3355 = new MethodCost("Foo", "hasIndirect()", 2, false, false, false);
		var3355.addCostSource(new CyclomaticCost(new SourceLocation(null, 4),
				Cost.cyclomatic(50)));
		var3355.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				1), var3350, NON_OVERRIDABLE_METHOD_CALL, Cost.cyclomatic(33)));
		ClassCost var3356 = new ClassCost("com.google.Foo", Arrays.asList(
				var3350, var3355));
		MethodCost var3357 = var3350;
		var3354.computeContributionFromMethod(var3356, var3357);
	}

	public CostModel instantiateCostModel561() {
		return new CostModel(2, 10, 1);
	}

	/**
	 * Test method for the class com.google.test.metric.report.issues.HypotheticalCostModel
	 */
	public void testHypotheticalCostModel1022() throws Exception {
		CostModel var3358 = instantiateCostModel561();
		HypotheticalCostModel var3359 = new HypotheticalCostModel(var3358);
		MethodCost var3342 = new MethodCost("", "a", 0, false, false, false);
		var3342.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3342.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		MethodCost var3343 = new MethodCost("", "b", 0, false, false, false);
		var3343.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3343.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3343.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3342.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var3343, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var3342.link();
		var3342.getTotalCost();
		var3358.computeOverall(var3342.getTotalCost());
		var3342.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3342.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		var3343.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3343.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3343.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3342.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var3343, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var3342.link();
		var3342.getTotalCost();
		var3358.computeOverall(var3342.getTotalCost());
		MethodCost var3350;
		var3350 = new MethodCost("Foo", "doThing()", 1, false, false, false);
		var3350.addCostSource(new CyclomaticCost(new SourceLocation(null, 3),
				Cost.cyclomatic(100)));
		MethodCost var3355;
		var3355 = new MethodCost("Foo", "hasIndirect()", 2, false, false, false);
		var3355.addCostSource(new CyclomaticCost(new SourceLocation(null, 4),
				Cost.cyclomatic(50)));
		var3355.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				1), var3350, NON_OVERRIDABLE_METHOD_CALL, Cost.cyclomatic(33)));
		ClassCost var3356 = new ClassCost("com.google.Foo", Arrays.asList(
				var3350, var3355));
		MethodCost var3357 = var3350;
		var3359.computeContributionFromMethod(var3356, var3357);
		var3350 = new MethodCost("Foo", "doThing()", 1, false, false, false);
		var3350.addCostSource(new CyclomaticCost(new SourceLocation(null, 3),
				Cost.cyclomatic(100)));
		var3355 = new MethodCost("Foo", "hasIndirect()", 2, false, false, false);
		var3355.addCostSource(new CyclomaticCost(new SourceLocation(null, 4),
				Cost.cyclomatic(50)));
		var3355.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				1), var3350, NON_OVERRIDABLE_METHOD_CALL, Cost.cyclomatic(33)));
		var3359.computeContributionFromMethod(var3356, var3357);
	}

	public CostModel instantiateCostModel562() {
		return new CostModel();
	}

	/**
	 * Test method for the class com.google.test.metric.report.issues.HypotheticalCostModel
	 */
	public void testHypotheticalCostModel1023() throws Exception {
		CostModel var3360 = instantiateCostModel562();
		HypotheticalCostModel var3361 = new HypotheticalCostModel(var3360);
		ClassRepository var3345 = new JavaClassRepository();
		MetricComputer var3346 = new MetricComputerBuilder()
				.withClassRepository(var3345).build();
		ClassInfo var3347 = var3345.getClass(Example.class.getCanonicalName());
		ClassCost var3348 = var3346.compute(var3347);
		var3360.computeClass(var3348);
		var3360.computeClass(var3348);
		ClassInfo var3363 = var3345.getClass(Example.class.getCanonicalName());
		ClassCost var3364 = var3346.compute(var3363);
		MethodCost var3365 = var3364.getMethodCost("Example()");
		MethodInvocationCost var3366 = (MethodInvocationCost) var3365
				.getViolationCosts().get(0);
		var3361.computeContributionFromIssue(var3364, var3365, var3366);
		var3361.computeContributionFromIssue(var3364, var3365, var3366);
	}

	public CostModel instantiateCostModel563() {
		return new CostModel();
	}

	/**
	 * Test method for the class com.google.test.metric.report.issues.HypotheticalCostModel
	 */
	public void testHypotheticalCostModel1024() throws Exception {
		CostModel var3367 = instantiateCostModel563();
		HypotheticalCostModel var3368 = new HypotheticalCostModel(var3367);
		ClassRepository var3345 = new JavaClassRepository();
		MetricComputer var3346 = new MetricComputerBuilder()
				.withClassRepository(var3345).build();
		ClassInfo var3347 = var3345.getClass(Example.class.getCanonicalName());
		ClassCost var3348 = var3346.compute(var3347);
		var3367.computeClass(var3348);
		var3367.computeClass(var3348);
		ClassInfo var3363 = var3345.getClass(Example.class.getCanonicalName());
		ClassCost var3364 = var3346.compute(var3363);
		MethodCost var3365 = var3364.getMethodCost("Example()");
		MethodInvocationCost var3366 = (MethodInvocationCost) var3365
				.getViolationCosts().get(0);
		var3368.computeContributionFromIssue(var3364, var3365, var3366);
		var3368.computeContributionFromIssue(var3364, var3365, var3366);
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