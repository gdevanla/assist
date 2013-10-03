package com.google.test.metric.report.issues;


import com.google.test.metric.example.NonMockableCollaborator.FinalMethodCantBeOverridden;
import com.google.test.metric.report.issues.ClassIssues.TotalCostComparator;
import com.google.test.metric.example.NonMockableCollaborator.StaticMethodCalled;
import com.google.test.metric.CostModel;
import com.google.test.metric.report.issues.Issue;
import com.google.test.metric.example.ExpensiveConstructor.StaticWorkInTheConstructor;
import com.google.test.metric.example.ExpensiveConstructor.Cost2ToConstruct;
import com.google.test.metric.ClassInfo;
import com.google.test.metric.Cost;
import junit.framework.TestCase;
import com.google.test.metric.MethodCost;
import com.google.test.metric.testing.MetricComputerJavaDecorator;
import com.google.test.metric.Reason;
import java.util.LinkedList;
import com.google.test.metric.report.issues.TriageIssuesQueue;
import com.google.test.metric.CyclomaticCost;
import java.util.List;
import com.google.test.metric.testing.MetricComputerBuilder;
import com.google.test.metric.JavaClassRepository;
import com.google.test.metric.MetricComputer;
import java.lang.String;
import com.google.test.metric.ViolationCost;
import com.google.test.metric.report.issues.ClassIssues;
import com.google.test.metric.MethodInvocationCost;
import com.google.test.metric.example.Lessons.Primeness;
import com.google.test.metric.SourceLocation;
import java.util.Arrays;
import com.google.test.metric.report.issues.HypotheticalCostModel;
import com.google.test.metric.ClassRepository;
import com.google.test.metric.ClassCost;
import static com.google.test.metric.report.issues.IssueSubType.COMPLEXITY;
import static com.google.test.metric.report.issues.IssueSubType.NON_MOCKABLE;
import static com.google.test.metric.report.issues.IssueSubType.SETTER;
import static com.google.test.metric.report.issues.IssueSubType.STATIC_INIT;
import static com.google.test.metric.report.issues.IssueSubType.STATIC_METHOD;
import static com.google.test.metric.report.issues.IssueType.CONSTRUCTION;
import static java.util.Arrays.asList;
import static java.util.ResourceBundle.getBundle;
import static com.google.test.metric.Reason.NON_OVERRIDABLE_METHOD_CALL;
import static com.google.test.metric.report.issues.IssueSubType.FINAL_METHOD;
import static com.google.test.metric.report.issues.IssueSubType.SINGLETON;

public class IssuesReporterGenTest extends TestCase {
	public HypotheticalCostModel instantiateHypotheticalCostModel549() {
		return new HypotheticalCostModel(new CostModel());
	}

	public TriageIssuesQueue<ClassIssues> instantiateTriageIssuesQueue550() {
		int var3285 = 50;
		int var3286 = 10;
		return new TriageIssuesQueue<ClassIssues>(var3285, var3286,
				new ClassIssues.TotalCostComparator());
	}

	/**
	 * Test method for the class com.google.test.metric.report.issues.IssuesReporter
	 */
	public void testIssuesReporter1015() throws Exception {
		TriageIssuesQueue<ClassIssues> var3287 = instantiateTriageIssuesQueue550();
		HypotheticalCostModel var3288 = instantiateHypotheticalCostModel549();
		var3289 = new HypotheticalCostModel(new CostModel());
		IssuesReporter var3290 = new IssuesReporter(
				new LinkedList<ClassIssues>(), var3288);
		ClassIssues var3291 = new ClassIssues("BadClass", 500);
		Issue var3292;
		var3292 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var3291.add(var3292);
		var3287.offer(var3291);
		ClassIssues var3294 = new ClassIssues("PrettyGoodClass", 101);
		var3292 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var3294.add(var3292);
		var3287.offer(var3294);
		var3287.asList();
		Issue var3295 = new Issue(new SourceLocation(null, 1), null, 1f, null,
				null);
		var3287.offer(var3295);
		var3287.isEmpty();
		var3287.asList();
		ClassRepository var3297 = new JavaClassRepository();
		MetricComputer var3298 = new MetricComputerBuilder()
				.withClassRepository(var3297).build();
		ClassInfo var3299 = var3297.getClass(Example.class.getCanonicalName());
		ClassCost var3300 = var3298.compute(var3299);
		MethodCost var3301 = var3300.getMethodCost("Example()");
		MethodInvocationCost var3302 = (MethodInvocationCost) var3301
				.getViolationCosts().get(0);
		var3288.computeContributionFromIssue(var3300, var3301, var3302);
		MethodCost var3304;
		var3304 = new MethodCost("Foo", "doThing()", 1, false, false, false);
		var3304.addCostSource(new CyclomaticCost(new SourceLocation(null, 3),
				Cost.cyclomatic(100)));
		ClassCost var3305 = new ClassCost("com.google.Foo",
				Arrays.asList(var3304));
		MethodCost var3306 = var3304;
		var3288.computeContributionFromMethod(var3305, var3306);
		MetricComputerJavaDecorator var3307;
		ClassRepository var3308 = new JavaClassRepository();
		MetricComputer var3309 = new MetricComputerBuilder()
				.withClassRepository(var3308).build();
		var3307 = new MetricComputerJavaDecorator(var3309, var3308);
		var3290.determineIssues(var3307
				.compute(StaticWorkInTheConstructor.class));
		MetricComputerJavaDecorator var3311;
		ClassRepository var3312 = new JavaClassRepository();
		MetricComputer var3313 = new MetricComputerBuilder()
				.withClassRepository(var3312).build();
		var3311 = new MetricComputerJavaDecorator(var3313, var3312);
		var3290.determineIssues(var3311.compute(SubclassOfSetterCost.class));
	}

	public HypotheticalCostModel instantiateHypotheticalCostModel551() {
		CostModel var3315 = new CostModel();
		return new HypotheticalCostModel(var3315);
	}

	public TriageIssuesQueue<ClassIssues> instantiateTriageIssuesQueue552() {
		return new TriageIssuesQueue<ClassIssues>(100, 2,
				new ClassIssues.TotalCostComparator());
	}

	/**
	 * Test method for the class com.google.test.metric.report.issues.IssuesReporter
	 */
	public void testIssuesReporter1016() throws Exception {
		TriageIssuesQueue<ClassIssues> var3316 = instantiateTriageIssuesQueue552();
		HypotheticalCostModel var3317 = instantiateHypotheticalCostModel551();
		IssuesReporter var3318 = new IssuesReporter(
				new LinkedList<ClassIssues>(), var3317);
		ClassIssues var3319 = new ClassIssues("BadClass", 500);
		Issue var3292;
		var3292 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var3319.add(var3292);
		var3316.offer(var3319);
		ClassIssues var3321 = new ClassIssues("PrettyGoodClass", 200);
		var3292 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var3321.add(var3292);
		var3316.offer(var3321);
		ClassIssues var3322 = new ClassIssues("NotGreatClass", 300);
		var3292 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var3322.add(var3292);
		var3316.offer(var3322);
		var3316.asList();
		var3316.asList();
		Issue var3295 = new Issue(new SourceLocation(null, 1), null, 1f, null,
				null);
		var3316.offer(var3295);
		var3316.isEmpty();
		var3316.asList();
		MethodCost var3304;
		var3304 = new MethodCost("Foo", "doThing()", 1, false, false, false);
		var3304.addCostSource(new CyclomaticCost(new SourceLocation(null, 3),
				Cost.cyclomatic(100)));
		MethodCost var3323;
		var3323 = new MethodCost("Foo", "hasIndirect()", 2, false, false, false);
		var3323.addCostSource(new CyclomaticCost(new SourceLocation(null, 4),
				Cost.cyclomatic(50)));
		var3323.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				1), var3304, NON_OVERRIDABLE_METHOD_CALL, Cost.cyclomatic(33)));
		ClassCost var3324 = new ClassCost("com.google.Foo", Arrays.asList(
				var3304, var3323));
		MethodCost var3325 = var3304;
		var3317.computeContributionFromMethod(var3324, var3325);
		ClassRepository var3297 = new JavaClassRepository();
		MetricComputer var3298 = new MetricComputerBuilder()
				.withClassRepository(var3297).build();
		ClassInfo var3299 = var3297.getClass(Example.class.getCanonicalName());
		ClassCost var3300 = var3298.compute(var3299);
		MethodCost var3301 = var3300.getMethodCost("Example()");
		MethodInvocationCost var3302 = (MethodInvocationCost) var3301
				.getViolationCosts().get(0);
		var3317.computeContributionFromIssue(var3300, var3301, var3302);
		MetricComputerJavaDecorator var3307;
		ClassRepository var3308 = new JavaClassRepository();
		MetricComputer var3309 = new MetricComputerBuilder()
				.withClassRepository(var3308).build();
		var3307 = new MetricComputerJavaDecorator(var3309, var3308);
		var3318.determineIssues(var3307
				.compute(FinalMethodCantBeOverridden.class));
		MetricComputerJavaDecorator var3311;
		ClassRepository var3312 = new JavaClassRepository();
		MetricComputer var3313 = new MetricComputerBuilder()
				.withClassRepository(var3312).build();
		var3311 = new MetricComputerJavaDecorator(var3313, var3312);
		var3318.determineIssues(var3311
				.compute(SeveralConstructionIssues.class));
	}

	public HypotheticalCostModel instantiateHypotheticalCostModel553() {
		CostModel var3315 = new CostModel();
		return new HypotheticalCostModel(var3315);
	}

	public TriageIssuesQueue<ClassIssues> instantiateTriageIssuesQueue554() {
		return new TriageIssuesQueue<ClassIssues>(1, 10,
				new ClassIssues.TotalCostComparator());
	}

	/**
	 * Test method for the class com.google.test.metric.report.issues.IssuesReporter
	 */
	public void testIssuesReporter1017() throws Exception {
		TriageIssuesQueue<ClassIssues> var3326 = instantiateTriageIssuesQueue554();
		HypotheticalCostModel var3327 = instantiateHypotheticalCostModel553();
		IssuesReporter var3328 = new IssuesReporter(
				new LinkedList<ClassIssues>(), var3327);
		ClassIssues var3291 = new ClassIssues("BadClass", 500);
		Issue var3292;
		var3292 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var3291.add(var3292);
		var3326.offer(var3291);
		ClassIssues var3294 = new ClassIssues("PrettyGoodClass", 101);
		var3292 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var3294.add(var3292);
		var3326.offer(var3294);
		var3326.asList();
		var3292 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var3291.add(var3292);
		var3326.offer(var3291);
		var3292 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var3294.add(var3292);
		var3326.offer(var3294);
		var3326.asList();
		MethodCost var3304;
		var3304 = new MethodCost("Foo", "doThing()", 1, false, false, false);
		var3304.addCostSource(new CyclomaticCost(new SourceLocation(null, 3),
				Cost.cyclomatic(100)));
		ClassCost var3305 = new ClassCost("com.google.Foo",
				Arrays.asList(var3304));
		MethodCost var3306 = var3304;
		var3327.computeContributionFromMethod(var3305, var3306);
		ClassRepository var3297 = new JavaClassRepository();
		MetricComputer var3298 = new MetricComputerBuilder()
				.withClassRepository(var3297).build();
		ClassInfo var3329 = var3297.getClass(Example.class.getCanonicalName());
		ClassCost var3330 = var3298.compute(var3329);
		var3327.computeClass(var3330);
		MetricComputerJavaDecorator var3307;
		ClassRepository var3308 = new JavaClassRepository();
		MetricComputer var3309 = new MetricComputerBuilder()
				.withClassRepository(var3308).build();
		var3307 = new MetricComputerJavaDecorator(var3309, var3308);
		var3328.determineIssues(var3307.compute(StaticMethodCalled.class));
		var3307 = new MetricComputerJavaDecorator(var3309, var3308);
		var3328.determineIssues(var3307.compute(Cost2ToConstruct.class));
	}

	public HypotheticalCostModel instantiateHypotheticalCostModel555() {
		return new HypotheticalCostModel(new CostModel());
	}

	public TriageIssuesQueue<ClassIssues> instantiateTriageIssuesQueue556() {
		return new TriageIssuesQueue<ClassIssues>(1, 10,
				new ClassIssues.TotalCostComparator());
	}

	/**
	 * Test method for the class com.google.test.metric.report.issues.IssuesReporter
	 */
	public void testIssuesReporter1018() throws Exception {
		TriageIssuesQueue<ClassIssues> var3331 = instantiateTriageIssuesQueue556();
		HypotheticalCostModel var3332 = instantiateHypotheticalCostModel555();
		var3289 = new HypotheticalCostModel(new CostModel());
		IssuesReporter var3333 = new IssuesReporter(
				new LinkedList<ClassIssues>(), var3332);
		int var3285 = 50;
		ClassIssues var3334 = new ClassIssues("FooClass", var3285 - 1);
		Issue var3292;
		var3292 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var3334.add(var3292);
		var3331.offer(var3334);
		var3331.isEmpty();
		ClassIssues var3336 = new ClassIssues("FooClass", 100);
		var3292 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var3336.add(var3292);
		var3331.offer(var3336);
		var3331.peek();
		MethodCost var3304;
		var3304 = new MethodCost("Foo", "doThing()", 1, false, false, false);
		var3304.addCostSource(new CyclomaticCost(new SourceLocation(null, 3),
				Cost.cyclomatic(100)));
		ClassCost var3305 = new ClassCost("com.google.Foo",
				Arrays.asList(var3304));
		MethodCost var3306 = var3304;
		var3332.computeContributionFromMethod(var3305, var3306);
		ClassRepository var3297 = new JavaClassRepository();
		MetricComputer var3298 = new MetricComputerBuilder()
				.withClassRepository(var3297).build();
		ClassInfo var3329 = var3297.getClass(Example.class.getCanonicalName());
		ClassCost var3330 = var3298.compute(var3329);
		var3332.computeClass(var3330);
		MetricComputerJavaDecorator var3307;
		ClassRepository var3308 = new JavaClassRepository();
		MetricComputer var3309 = new MetricComputerBuilder()
				.withClassRepository(var3308).build();
		var3307 = new MetricComputerJavaDecorator(var3309, var3308);
		var3333.determineIssues(var3307
				.compute(StaticWorkInTheConstructor.class));
		MetricComputerJavaDecorator var3311;
		ClassRepository var3312 = new JavaClassRepository();
		MetricComputer var3313 = new MetricComputerBuilder()
				.withClassRepository(var3312).build();
		var3311 = new MetricComputerJavaDecorator(var3313, var3312);
		var3333.determineIssues(var3311.compute(SubclassOfSetterCost.class));
	}

	public HypotheticalCostModel instantiateHypotheticalCostModel557() {
		return new HypotheticalCostModel(new CostModel());
	}

	public TriageIssuesQueue<ClassIssues> instantiateTriageIssuesQueue558() {
		return new TriageIssuesQueue<ClassIssues>(100, 2,
				new ClassIssues.TotalCostComparator());
	}

	/**
	 * Test method for the class com.google.test.metric.report.issues.IssuesReporter
	 */
	public void testIssuesReporter1019() throws Exception {
		TriageIssuesQueue<ClassIssues> var3337 = instantiateTriageIssuesQueue558();
		HypotheticalCostModel var3338 = instantiateHypotheticalCostModel557();
		IssuesReporter var3339 = new IssuesReporter(
				new LinkedList<ClassIssues>(), var3338);
		ClassIssues var3291 = new ClassIssues("BadClass", 500);
		Issue var3292;
		var3292 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var3291.add(var3292);
		var3337.offer(var3291);
		ClassIssues var3294 = new ClassIssues("PrettyGoodClass", 101);
		var3292 = new Issue(new SourceLocation(null, 1), null, 1f, null, null);
		var3294.add(var3292);
		var3337.offer(var3294);
		var3337.asList();
		Issue var3295 = new Issue(new SourceLocation(null, 1), null, 1f, null,
				null);
		var3337.offer(var3295);
		var3337.isEmpty();
		var3337.asList();
		ClassRepository var3297 = new JavaClassRepository();
		MetricComputer var3298 = new MetricComputerBuilder()
				.withClassRepository(var3297).build();
		ClassInfo var3329 = var3297.getClass(Example.class.getCanonicalName());
		ClassCost var3330 = var3298.compute(var3329);
		var3338.computeClass(var3330);
		MethodCost var3304;
		var3304 = new MethodCost("Foo", "doThing()", 1, false, false, false);
		var3304.addCostSource(new CyclomaticCost(new SourceLocation(null, 3),
				Cost.cyclomatic(100)));
		MethodCost var3323;
		var3323 = new MethodCost("Foo", "hasIndirect()", 2, false, false, false);
		var3323.addCostSource(new CyclomaticCost(new SourceLocation(null, 4),
				Cost.cyclomatic(50)));
		var3323.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				1), var3304, NON_OVERRIDABLE_METHOD_CALL, Cost.cyclomatic(33)));
		ClassCost var3324 = new ClassCost("com.google.Foo", Arrays.asList(
				var3304, var3323));
		MethodCost var3325 = var3304;
		var3338.computeContributionFromMethod(var3324, var3325);
		MetricComputerJavaDecorator var3307;
		ClassRepository var3308 = new JavaClassRepository();
		MetricComputer var3309 = new MetricComputerBuilder()
				.withClassRepository(var3308).build();
		var3307 = new MetricComputerJavaDecorator(var3309, var3308);
		var3339.determineIssues(var3307.compute(Primeness.class));
		var3307 = new MetricComputerJavaDecorator(var3309, var3308);
		var3339.determineIssues(var3307
				.compute(FinalMethodCantBeOverridden.class));
	}

	private static class SeveralConstructionIssues {
		public SeveralConstructionIssues() {
			CostUtil.staticCost3();
			int a = 0;
			@SuppressWarnings("unused")
			int b = a > 5 ? 3 : 5;
			b = a < 4 ? 4 : 3;
			new CostUtil().instanceCost4();
		}
	}

	static class SubclassOfSetterCost extends HasSetterCost {
		public void doThing() {
		}
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