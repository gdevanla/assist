package com.google.test.metric;


import com.google.test.metric.Reason;
import com.google.test.metric.CyclomaticCost;
import com.google.test.metric.MethodCost;
import com.google.test.metric.MethodInvocationCost;
import com.google.test.metric.Cost;
import com.google.test.metric.SourceLocation;
import junit.framework.TestCase;
import com.google.test.metric.GlobalCost;
import static com.google.test.metric.Reason.IMPLICIT_STATIC_INIT;
import com.google.test.metric.ClassInfo;
import com.google.test.metric.testing.MetricComputerBuilder;
import com.google.test.metric.MetricComputer;
import com.google.test.metric.JavaClassRepository;
import java.lang.String;
import com.google.test.metric.ClassRepository;
import com.google.test.metric.ClassCost;
import static com.google.test.metric.Reason.NON_OVERRIDABLE_METHOD_CALL;

public class CostModelGenTest extends TestCase {
	/**
	 * Test method for the class com.google.test.metric.CostModel
	 */
	public void testCostModel945() throws Exception {
		CostModel var2937 = new CostModel(1, 1, 10);
		MethodCost var2938 = new MethodCost("", "a", 0, false, false, false);
		var2938.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2938.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		MethodCost var2939 = new MethodCost("", "b", 0, false, false, false);
		var2939.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2939.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2939.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2938.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var2939, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var2938.link();
		var2938.getTotalCost();
		var2937.computeOverall(var2938.getTotalCost());
		var2938.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2938.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		var2939.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2939.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2939.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2938.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var2939, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var2938.link();
		var2938.getTotalCost();
		var2937.computeOverall(var2938.getTotalCost());
	}

	/**
	 * Test method for the class com.google.test.metric.CostModel
	 */
	public void testCostModel946() throws Exception {
		CostModel var2941 = new CostModel(1, 1, 1);
		MethodCost var2938 = new MethodCost("", "a", 0, false, false, false);
		var2938.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2938.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		MethodCost var2939 = new MethodCost("", "b", 0, false, false, false);
		var2939.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2939.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2939.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2938.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var2939, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var2938.link();
		var2938.getTotalCost();
		var2941.computeOverall(var2938.getTotalCost());
		var2938.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2938.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		var2939.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2939.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2939.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2938.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var2939, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var2938.link();
		var2938.getTotalCost();
		var2941.computeOverall(var2938.getTotalCost());
	}

	/**
	 * Test method for the class com.google.test.metric.CostModel
	 */
	public void testCostModel947() throws Exception {
		CostModel var2942 = new CostModel(2, 10, 1);
		MethodCost var2938 = new MethodCost("", "a", 0, false, false, false);
		var2938.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2938.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		MethodCost var2939 = new MethodCost("", "b", 0, false, false, false);
		var2939.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2939.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2939.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2938.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var2939, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var2938.link();
		var2938.getTotalCost();
		var2942.computeOverall(var2938.getTotalCost());
		var2938.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2938.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		var2939.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2939.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2939.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2938.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var2939, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var2938.link();
		var2938.getTotalCost();
		var2942.computeOverall(var2938.getTotalCost());
	}

	/**
	 * Test method for the class com.google.test.metric.CostModel
	 */
	public void testCostModel948() throws Exception {
		CostModel var2941 = new CostModel(1, 1, 1);
		MethodCost var2938 = new MethodCost("", "a", 0, false, false, false);
		var2938.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2938.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		MethodCost var2939 = new MethodCost("", "b", 0, false, false, false);
		var2939.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2939.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2939.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2938.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var2939, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var2938.link();
		var2938.getTotalCost();
		var2941.computeOverall(var2938.getTotalCost());
		var2938.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2938.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		var2939.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2939.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2939.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2938.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var2939, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var2938.link();
		var2938.getTotalCost();
		var2941.computeOverall(var2938.getTotalCost());
	}

	/**
	 * Test method for the class com.google.test.metric.CostModel
	 */
	public void testCostModel949() throws Exception {
		CostModel var2942 = new CostModel(2, 10, 1);
		MethodCost var2938 = new MethodCost("", "a", 0, false, false, false);
		var2938.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2938.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		MethodCost var2939 = new MethodCost("", "b", 0, false, false, false);
		var2939.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2939.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2939.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2938.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var2939, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var2938.link();
		var2938.getTotalCost();
		var2942.computeOverall(var2938.getTotalCost());
		var2938.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2938.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		var2939.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2939.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2939.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var2938.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var2939, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var2938.link();
		var2938.getTotalCost();
		var2942.computeOverall(var2938.getTotalCost());
	}

	/**
	 * Test method for the class com.google.test.metric.CostModel
	 */
	public void testCostModel950() throws Exception {
		CostModel var2943 = new CostModel();
		ClassRepository var2944 = new JavaClassRepository();
		MetricComputer var2945 = new MetricComputerBuilder()
				.withClassRepository(var2944).build();
		ClassInfo var2946 = var2944.getClass(Example.class.getCanonicalName());
		ClassCost var2947 = var2945.compute(var2946);
		var2943.computeClass(var2947);
		var2943.computeClass(var2947);
	}

	/**
	 * Test method for the class com.google.test.metric.CostModel
	 */
	public void testCostModel951() throws Exception {
		CostModel var2943 = new CostModel();
		ClassRepository var2944 = new JavaClassRepository();
		MetricComputer var2945 = new MetricComputerBuilder()
				.withClassRepository(var2944).build();
		ClassInfo var2946 = var2944.getClass(Example.class.getCanonicalName());
		ClassCost var2947 = var2945.compute(var2946);
		var2943.computeClass(var2947);
		var2943.computeClass(var2947);
	}

	/**
	 * Test method for the class com.google.test.metric.CostModel
	 */
	public void testCostModel952() throws Exception {
		CostModel var2943 = new CostModel();
		ClassRepository var2944 = new JavaClassRepository();
		MetricComputer var2945 = new MetricComputerBuilder()
				.withClassRepository(var2944).build();
		ClassInfo var2946 = var2944.getClass(Example.class.getCanonicalName());
		ClassCost var2947 = var2945.compute(var2946);
		var2943.computeClass(var2947);
		var2943.computeClass(var2947);
	}

	/**
	 * Test method for the class com.google.test.metric.CostModel
	 */
	public void testCostModel953() throws Exception {
		CostModel var2943 = new CostModel();
		ClassRepository var2944 = new JavaClassRepository();
		MetricComputer var2945 = new MetricComputerBuilder()
				.withClassRepository(var2944).build();
		ClassInfo var2946 = var2944.getClass(Example.class.getCanonicalName());
		ClassCost var2947 = var2945.compute(var2946);
		var2943.computeClass(var2947);
		var2943.computeClass(var2947);
	}

	/**
	 * Test method for the class com.google.test.metric.CostModel
	 */
	public void testCostModel954() throws Exception {
		CostModel var2943 = new CostModel();
		ClassRepository var2944 = new JavaClassRepository();
		MetricComputer var2945 = new MetricComputerBuilder()
				.withClassRepository(var2944).build();
		ClassInfo var2946 = var2944.getClass(Example.class.getCanonicalName());
		ClassCost var2947 = var2945.compute(var2946);
		var2943.computeClass(var2947);
		var2943.computeClass(var2947);
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