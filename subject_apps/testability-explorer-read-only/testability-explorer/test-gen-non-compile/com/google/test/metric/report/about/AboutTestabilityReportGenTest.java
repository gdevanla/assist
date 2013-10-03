package com.google.test.metric.report.about;


import com.google.test.metric.example.NonMockableCollaborator.FinalMethodCantBeOverridden;
import com.google.test.metric.report.issues.ClassIssues.TotalCostComparator;
import com.google.test.metric.CostModel;
import com.google.classpath.ClassPathFactory;
import com.google.test.metric.ClassInfo;
import freemarker.template.DefaultObjectWrapper;
import com.google.test.metric.report.issues.IssuesReporter;
import junit.framework.TestCase;
import freemarker.ext.beans.ResourceBundleModel;
import com.google.test.metric.testing.MetricComputerJavaDecorator;
import com.google.test.metric.report.Source.Line;
import java.lang.Class;
import java.util.ResourceBundle;
import com.google.classpath.ClassPath;
import freemarker.ext.beans.BeansWrapper;
import java.util.LinkedList;
import com.google.test.metric.report.issues.TriageIssuesQueue;
import com.google.test.metric.example.ExpensiveConstructor.ObjectInstantiationWorkInTheConstructor;
import java.lang.Override;
import java.util.List;
import com.google.test.metric.testing.MetricComputerBuilder;
import com.google.test.metric.report.SourceLoader;
import com.google.test.metric.JavaClassRepository;
import com.google.test.metric.MetricComputer;
import java.lang.String;
import com.google.test.metric.example.MutableGlobalState.FinalGlobalExample;
import com.google.test.metric.report.issues.ClassIssues;
import com.google.test.metric.report.Source;
import com.google.test.metric.example.Lessons.Primeness;
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
import static com.google.test.metric.report.issues.IssueSubType.FINAL_METHOD;
import static com.google.test.metric.report.issues.IssueSubType.SINGLETON;

public class AboutTestabilityReportGenTest extends TestCase {
	public SourceLoader instantiateSourceLoader564() {
		ClassPath var3369 = new ClassPathFactory().createFromPaths(
				"src/test/java", "core/src/test/java");
		return new SourceLoader(var3369);
	}

	public IssuesReporter instantiateIssuesReporter565() {
		HypotheticalCostModel var3370 = new HypotheticalCostModel(
				new CostModel());
		return new IssuesReporter(new LinkedList<ClassIssues>(), var3370);
	}

	/**
	 * Test method for the class com.google.test.metric.report.about.AboutTestabilityReport
	 */
	public void testAboutTestabilityReport1025() throws Exception {
		SourceLoader var3371 = instantiateSourceLoader564();
		IssuesReporter var3372 = instantiateIssuesReporter565();
		HypotheticalCostModel var3370 = new HypotheticalCostModel(
				new CostModel());
		AboutTestabilityReport var3374 = new AboutTestabilityReport(var3372,
				new SourceLoader(null) {
					@Override
					public Source load(String var3373) {
						return new Source(asList(new Line(1,
								"Copyright garbage!"), new Line(2,
								"package com.google.test.metric.example;"),
								new Line(3, "import java.util.List;"),
								new Line(4, "  "), new Line(5,
										"class SumOfPrimes {"), new Line(6,
										"  public void sum() {}"), new Line(7,
										"}")));
					}
				});
		var3371.load(getClass().getName());
		var3371.load(getClass().getName());
		MetricComputerJavaDecorator var3376;
		ClassRepository var3377 = new JavaClassRepository();
		MetricComputer var3378 = new MetricComputerBuilder()
				.withClassRepository(var3377).build();
		var3376 = new MetricComputerJavaDecorator(var3378, var3377);
		var3372.determineIssues(var3376
				.compute(ObjectInstantiationWorkInTheConstructor.class));
		MetricComputerJavaDecorator var3380;
		ClassRepository var3381 = new JavaClassRepository();
		MetricComputer var3382 = new MetricComputerBuilder()
				.withClassRepository(var3381).build();
		var3380 = new MetricComputerJavaDecorator(var3382, var3381);
		var3372.determineIssues(var3380.compute(ClassInfo.class));
		BeansWrapper var3384 = new DefaultObjectWrapper();
		ResourceBundleModel var3385 = new ResourceBundleModel(
				getBundle("messages"), var3384);
		var3374.setMessageBundle(var3385);
		var3374.setMessageBundle(var3385);
	}

	public SourceLoader instantiateSourceLoader566() {
		ClassPath var3369 = new ClassPathFactory().createFromPaths(
				"src/test/java", "core/src/test/java");
		return new SourceLoader(var3369);
	}

	public IssuesReporter instantiateIssuesReporter567() {
		HypotheticalCostModel var3387;
		var3387 = new HypotheticalCostModel(new CostModel());
		return new IssuesReporter(new LinkedList<ClassIssues>(), this.var3387);
	}

	/**
	 * Test method for the class com.google.test.metric.report.about.AboutTestabilityReport
	 */
	public void testAboutTestabilityReport1026() throws Exception {
		SourceLoader var3388 = instantiateSourceLoader566();
		IssuesReporter var3389 = instantiateIssuesReporter567();
		HypotheticalCostModel var3370 = new HypotheticalCostModel(
				new CostModel());
		AboutTestabilityReport var3390 = new AboutTestabilityReport(var3389,
				new SourceLoader(null) {
					@Override
					public Source load(String var3373) {
						return new Source(asList(new Line(1,
								"Copyright garbage!"), new Line(2,
								"package com.google.test.metric.example;"),
								new Line(3, "import java.util.List;"),
								new Line(4, "  "), new Line(5,
										"class SumOfPrimes {"), new Line(6,
										"  public void sum() {}"), new Line(7,
										"}")));
					}
				});
		var3388.load(InnerClass.class.getCanonicalName());
		var3388.load(InnerClass.class.getCanonicalName());
		MetricComputerJavaDecorator var3376;
		ClassRepository var3377 = new JavaClassRepository();
		MetricComputer var3378 = new MetricComputerBuilder()
				.withClassRepository(var3377).build();
		var3376 = new MetricComputerJavaDecorator(var3378, var3377);
		var3389.determineIssues(var3376.compute(FinalGlobalExample.class));
		var3376 = new MetricComputerJavaDecorator(var3378, var3377);
		var3389.determineIssues(var3376.compute(Primeness.class));
		BeansWrapper var3384 = new DefaultObjectWrapper();
		ResourceBundleModel var3385 = new ResourceBundleModel(
				getBundle("messages"), var3384);
		var3390.setMessageBundle(var3385);
		var3390.setMessageBundle(var3385);
	}

	public SourceLoader instantiateSourceLoader568() {
		ClassPath var3369 = new ClassPathFactory().createFromPaths(
				"src/test/java", "core/src/test/java");
		return new SourceLoader(var3369);
	}

	public IssuesReporter instantiateIssuesReporter569() {
		TriageIssuesQueue<ClassIssues> var3392 = new TriageIssuesQueue<ClassIssues>(
				1, 10, new ClassIssues.TotalCostComparator());
		HypotheticalCostModel var3387;
		var3387 = new HypotheticalCostModel(new CostModel());
		return new IssuesReporter(var3392, var3387);
	}

	/**
	 * Test method for the class com.google.test.metric.report.about.AboutTestabilityReport
	 */
	public void testAboutTestabilityReport1027() throws Exception {
		SourceLoader var3393 = instantiateSourceLoader568();
		IssuesReporter var3394 = instantiateIssuesReporter569();
		HypotheticalCostModel var3370 = new HypotheticalCostModel(
				new CostModel());
		AboutTestabilityReport var3395 = new AboutTestabilityReport(var3394,
				new SourceLoader(null) {
					@Override
					public Source load(String var3373) {
						return new Source(asList(new Line(1,
								"Copyright garbage!"), new Line(2,
								"package com.google.test.metric.example;"),
								new Line(3, "import java.util.List;"),
								new Line(4, "  "), new Line(5,
										"class SumOfPrimes {"), new Line(6,
										"  public void sum() {}"), new Line(7,
										"}")));
					}
				});
		var3393.load(InnerClass.class.getCanonicalName());
		var3393.load(InnerClass.class.getCanonicalName());
		MetricComputerJavaDecorator var3376;
		ClassRepository var3377 = new JavaClassRepository();
		MetricComputer var3378 = new MetricComputerBuilder()
				.withClassRepository(var3377).build();
		var3376 = new MetricComputerJavaDecorator(var3378, var3377);
		var3394.determineIssues(var3376
				.compute(FinalMethodCantBeOverridden.class));
		MetricComputerJavaDecorator var3380;
		ClassRepository var3381 = new JavaClassRepository();
		MetricComputer var3382 = new MetricComputerBuilder()
				.withClassRepository(var3381).build();
		var3380 = new MetricComputerJavaDecorator(var3382, var3381);
		var3394.determineIssues(var3380
				.compute(SeveralConstructionIssues.class));
		BeansWrapper var3384 = new DefaultObjectWrapper();
		ResourceBundleModel var3385 = new ResourceBundleModel(
				getBundle("messages"), var3384);
		var3395.setMessageBundle(var3385);
		var3395.setMessageBundle(var3385);
	}

	public SourceLoader instantiateSourceLoader570() {
		ClassPath var3396 = new ClassPathFactory().createFromPaths(
				"src/test/java", "core/src/test/java");
		return new SourceLoader(var3396);
	}

	public IssuesReporter instantiateIssuesReporter571() {
		HypotheticalCostModel var3370 = new HypotheticalCostModel(
				new CostModel());
		return new IssuesReporter(new LinkedList<ClassIssues>(), var3370);
	}

	/**
	 * Test method for the class com.google.test.metric.report.about.AboutTestabilityReport
	 */
	public void testAboutTestabilityReport1028() throws Exception {
		SourceLoader var3397 = instantiateSourceLoader570();
		IssuesReporter var3398 = instantiateIssuesReporter571();
		HypotheticalCostModel var3370 = new HypotheticalCostModel(
				new CostModel());
		AboutTestabilityReport var3399 = new AboutTestabilityReport(var3398,
				new SourceLoader(null) {
					@Override
					public Source load(String var3373) {
						return new Source(asList(new Line(1,
								"Copyright garbage!"), new Line(2,
								"package com.google.test.metric.example;"),
								new Line(3, "import java.util.List;"),
								new Line(4, "  "), new Line(5,
										"class SumOfPrimes {"), new Line(6,
										"  public void sum() {}"), new Line(7,
										"}")));
					}
				});
		var3397.load(getClass().getName());
		var3397.load("X-I don't exist-X");
		MetricComputerJavaDecorator var3380;
		ClassRepository var3381 = new JavaClassRepository();
		MetricComputer var3382 = new MetricComputerBuilder()
				.withClassRepository(var3381).build();
		var3380 = new MetricComputerJavaDecorator(var3382, var3381);
		var3398.determineIssues(var3380.compute(SubclassOfSetterCost.class));
		var3380 = new MetricComputerJavaDecorator(var3382, var3381);
		var3398.determineIssues(var3380.compute(StaticInit.class));
		BeansWrapper var3384 = new DefaultObjectWrapper();
		ResourceBundleModel var3385 = new ResourceBundleModel(
				getBundle("messages"), var3384);
		var3399.setMessageBundle(var3385);
		var3399.setMessageBundle(var3385);
	}

	public SourceLoader instantiateSourceLoader572() {
		ClassPath var3369 = new ClassPathFactory().createFromPaths(
				"src/test/java", "core/src/test/java");
		return new SourceLoader(var3369);
	}

	public IssuesReporter instantiateIssuesReporter573() {
		HypotheticalCostModel var3387;
		var3387 = new HypotheticalCostModel(new CostModel());
		return new IssuesReporter(new LinkedList<ClassIssues>(), this.var3387);
	}

	/**
	 * Test method for the class com.google.test.metric.report.about.AboutTestabilityReport
	 */
	public void testAboutTestabilityReport1029() throws Exception {
		SourceLoader var3401 = instantiateSourceLoader572();
		IssuesReporter var3402 = instantiateIssuesReporter573();
		HypotheticalCostModel var3370 = new HypotheticalCostModel(
				new CostModel());
		AboutTestabilityReport var3403 = new AboutTestabilityReport(var3402,
				new SourceLoader(null) {
					@Override
					public Source load(String var3373) {
						return new Source(asList(new Line(1,
								"Copyright garbage!"), new Line(2,
								"package com.google.test.metric.example;"),
								new Line(3, "import java.util.List;"),
								new Line(4, "  "), new Line(5,
										"class SumOfPrimes {"), new Line(6,
										"  public void sum() {}"), new Line(7,
										"}")));
					}
				});
		var3401.load("X-I don't exist-X");
		var3401.load(InnerClass.class.getCanonicalName());
		MetricComputerJavaDecorator var3376;
		ClassRepository var3377 = new JavaClassRepository();
		MetricComputer var3378 = new MetricComputerBuilder()
				.withClassRepository(var3377).build();
		var3376 = new MetricComputerJavaDecorator(var3378, var3377);
		var3402.determineIssues(var3376.compute(Primeness.class));
		MetricComputerJavaDecorator var3380;
		ClassRepository var3381 = new JavaClassRepository();
		MetricComputer var3382 = new MetricComputerBuilder()
				.withClassRepository(var3381).build();
		var3380 = new MetricComputerJavaDecorator(var3382, var3381);
		var3402.determineIssues(var3380.compute(StaticInit.class));
		BeansWrapper var3384 = new DefaultObjectWrapper();
		ResourceBundleModel var3385 = new ResourceBundleModel(
				getBundle("messages"), var3384);
		var3403.setMessageBundle(var3385);
		var3403.setMessageBundle(var3385);
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

	private static class StaticInit {
		private static int a = 1;
		private static String b = "b";

		public void otherCost() {
			new CostUtil().instanceCost4();
		}
	}

	static class SubclassOfSetterCost extends HasSetterCost {
		public void doThing() {
		}
	}

	private static class InnerClass {
	}
}