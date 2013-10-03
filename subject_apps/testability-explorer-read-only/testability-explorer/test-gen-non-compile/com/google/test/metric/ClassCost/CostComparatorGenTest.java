package com.google.test.metric.ClassCost;


import com.google.test.metric.Reason;
import com.google.test.metric.CostModel;
import com.google.test.metric.CyclomaticCost;
import com.google.test.metric.testing.MetricComputerBuilder;
import com.google.test.metric.ClassInfo;
import com.google.test.metric.JavaClassRepository;
import com.google.test.metric.MetricComputer;
import com.google.test.metric.Cost;
import java.lang.String;
import junit.framework.TestCase;
import com.google.test.metric.MethodCost;
import com.google.test.metric.MethodInvocationCost;
import com.google.test.metric.SourceLocation;
import com.google.test.metric.ClassRepository;
import com.google.test.metric.GlobalCost;
import com.google.test.metric.ClassCost;
import static com.google.test.metric.Reason.NON_OVERRIDABLE_METHOD_CALL;
import static com.google.test.metric.Reason.IMPLICIT_STATIC_INIT;
import static java.util.Arrays.asList;

public class CostComparatorGenTest extends TestCase {
	public CostModel instantiateCostModel462() {
		return new CostModel();
	}

	/**
	 * Test method for the class com.google.test.metric.ClassCost.CostComparator
	 */
	public void testCostComparator940() throws Exception {
		CostModel var2919 = instantiateCostModel462();
		ClassCost.CostComparator var2920 = new ClassCost.CostComparator(var2919);
		ClassRepository var2921 = new JavaClassRepository();
		MetricComputer var2922 = new MetricComputerBuilder()
				.withClassRepository(var2921).build();
		ClassInfo var2923 = var2921.getClass(Example.class.getCanonicalName());
		ClassCost var2924 = var2922.compute(var2923);
		var2919.computeClass(var2924);
		var2919.computeClass(var2924);
	}

	public CostModel instantiateCostModel463() {
		return new CostModel();
	}

	/**
	 * Test method for the class com.google.test.metric.ClassCost.CostComparator
	 */
	public void testCostComparator941() throws Exception {
		CostModel var2926 = instantiateCostModel463();
		ClassCost.CostComparator var2927 = new ClassCost.CostComparator(var2926);
		ClassRepository var2921 = new JavaClassRepository();
		MetricComputer var2922 = new MetricComputerBuilder()
				.withClassRepository(var2921).build();
		ClassInfo var2923 = var2921.getClass(Example.class.getCanonicalName());
		ClassCost var2924 = var2922.compute(var2923);
		var2926.computeClass(var2924);
		var2926.computeClass(var2924);
	}

	public CostModel instantiateCostModel464() {
		return new CostModel(2, 10, 1);
	}

	/**
	 * Test method for the class com.google.test.metric.ClassCost.CostComparator
	 */
	public void testCostComparator942() throws Exception {
		CostModel var2928 = instantiateCostModel464();
		ClassCost.CostComparator var2929 = new ClassCost.CostComparator(var2928);
		MethodCost var2930 = new MethodCost("", "a", 0, false, false, false);
		var2930.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2930.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		MethodCost var2931 = new MethodCost("", "b", 0, false, false, false);
		var2931.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2931.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2931.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2930.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var2931, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var2930.link();
		var2930.getTotalCost();
		var2928.computeOverall(var2930.getTotalCost());
		var2930.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2930.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		var2931.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2931.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2931.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2930.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var2931, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var2930.link();
		var2930.getTotalCost();
		var2928.computeOverall(var2930.getTotalCost());
	}

	public CostModel instantiateCostModel465() {
		return new CostModel();
	}

	/**
	 * Test method for the class com.google.test.metric.ClassCost.CostComparator
	 */
	public void testCostComparator943() throws Exception {
		CostModel var2933 = instantiateCostModel465();
		ClassCost.CostComparator var2934 = new ClassCost.CostComparator(var2933);
		ClassRepository var2921 = new JavaClassRepository();
		MetricComputer var2922 = new MetricComputerBuilder()
				.withClassRepository(var2921).build();
		ClassInfo var2923 = var2921.getClass(Example.class.getCanonicalName());
		ClassCost var2924 = var2922.compute(var2923);
		var2933.computeClass(var2924);
		var2933.computeClass(var2924);
	}

	public CostModel instantiateCostModel466() {
		return new CostModel(1, 1, 10);
	}

	/**
	 * Test method for the class com.google.test.metric.ClassCost.CostComparator
	 */
	public void testCostComparator944() throws Exception {
		CostModel var2935 = instantiateCostModel466();
		ClassCost.CostComparator var2936 = new ClassCost.CostComparator(var2935);
		MethodCost var2930 = new MethodCost("", "a", 0, false, false, false);
		var2930.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2930.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		MethodCost var2931 = new MethodCost("", "b", 0, false, false, false);
		var2931.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2931.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2931.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2930.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var2931, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var2930.link();
		var2930.getTotalCost();
		var2935.computeOverall(var2930.getTotalCost());
		var2930.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2930.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		var2931.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2931.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2931.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2930.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var2931, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var2930.link();
		var2930.getTotalCost();
		var2935.computeOverall(var2930.getTotalCost());
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