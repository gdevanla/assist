package com.google.test.metric.report;


import com.google.test.metric.CostModel;
import com.google.test.metric.RegExpWhiteList;
import com.google.test.metric.ClassInfo;
import com.google.classpath.ClassPathFactory;
import com.google.test.metric.Cost;
import com.google.test.metric.report.chart.HistogramChartUrl;
import junit.framework.TestCase;
import com.google.test.metric.report.GradeCategories;
import com.google.test.metric.MethodCost;
import java.lang.Class;
import com.google.test.metric.report.chart.PieChartUrl;
import com.google.classpath.ClassPath;
import com.google.test.metric.GlobalCost;
import com.google.test.metric.Testability;
import com.google.test.metric.Reason;
import com.google.test.metric.CyclomaticCost;
import com.google.test.metric.report.ProjectReport;
import freemarker.template.Configuration;
import com.google.test.metric.testing.MetricComputerBuilder;
import java.util.List;
import java.lang.Package;
import com.google.test.metric.report.PackageReport;
import com.google.classpath.DirectoryClassPath;
import com.google.test.metric.report.ClassReport;
import com.google.test.metric.JavaClassRepository;
import com.google.test.metric.report.SourceLoader;
import com.google.test.metric.MetricComputer;
import java.lang.String;
import java.lang.Integer;
import java.util.Date;
import com.google.test.metric.WeightedAverage;
import com.google.test.metric.MethodInvocationCost;
import com.google.test.metric.report.ClassSourceReportTestExample;
import com.google.test.metric.report.Source;
import com.google.test.metric.report.SourceReportGenerator;
import java.io.File;
import com.google.test.metric.SourceLocation;
import com.google.test.metric.report.ProjectSummaryReport;
import com.google.test.metric.ClassRepository;
import com.google.test.metric.ClassCost;
import static com.google.test.metric.Reason.NON_OVERRIDABLE_METHOD_CALL;
import static java.util.Arrays.asList;
import static com.google.test.metric.Reason.IMPLICIT_STATIC_INIT;

public class SourceReportGeneratorGenTest extends TestCase {
	public CostModel instantiateCostModel515() {
		return new CostModel(1, 1, 1);
	}

	public SourceLoader instantiateSourceLoader516() {
		ClassPath var3128 = new ClassPathFactory().createFromPaths(
				"src/test/java", "core/src/test/java");
		return new SourceLoader(var3128);
	}

	public GradeCategories instantiateGradeCategories517() {
		return new GradeCategories(1, 2);
	}

	/**
	 * Test method for the class com.google.test.metric.report.SourceReportGenerator
	 */
	public void testSourceReportGenerator985() throws Exception {
		CostModel var3129 = instantiateCostModel515();
		SourceLoader var3130 = instantiateSourceLoader516();
		GradeCategories var3131 = instantiateGradeCategories517();
		ClassPath var3128 = new ClassPathFactory().createFromPaths(
				"src/test/java", "core/src/test/java");
		SourceReportGenerator var3132 = new SourceReportGenerator(var3131,
				var3130, null, var3129, new Date(), 10, new Configuration());
		MethodCost var3133 = new MethodCost("", "a", 0, false, false, false);
		var3133.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3133.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		MethodCost var3134 = new MethodCost("", "b", 0, false, false, false);
		var3134.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3134.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3134.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3133.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var3134, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var3133.link();
		var3133.getTotalCost();
		var3129.computeOverall(var3133.getTotalCost());
		var3133.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3133.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		var3134.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3134.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3134.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3133.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var3134, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var3133.link();
		var3133.getTotalCost();
		var3129.computeOverall(var3133.getTotalCost());
		var3130.load(getClass().getName());
		var3130.load(InnerClass.class.getCanonicalName());
		var3131.createDistributionChart(asList(1, 2, 2, 3, 3, 3));
		var3131.createHistogram(0, 0, asList(1, 2, 2, 3, 3, 3));
		MetricComputer var3139;
		var3139 = new MetricComputer(new JavaClassRepository(), null,
				new RegExpWhiteList(), 1);
		ClassCost var3140 = var3139.compute(HasImplicitCostFromOtherClass.class
				.getCanonicalName());
		var3132.createClassReport(var3140);
		var3132.printHeader();
		ClassRepository var3143 = new JavaClassRepository();
		MetricComputer var3144 = new MetricComputer(var3143, null,
				new RegExpWhiteList("!com.google"), 1);
		ClassCost var3145 = var3144.compute(var3143
				.getClass(ClassSourceReportTestExample.class.getName()));
		var3132.createClassReport(var3145);
		GradeCategories var3146 = new GradeCategories(50, 100);
		ClassPath var3147 = new DirectoryClassPath(new File("src-test"));
		File var3148 = new File("test-out");
		SourceReportGenerator var3142 = new SourceReportGenerator(var3146,
				new SourceLoader(var3147), var3148, new CostModel(),
				new Date(), 10, new Configuration());
		var3142.printHeader();
		ClassReport var3149 = var3142.createClassReport(var3145);
		var3132.write("Class.html", var3149, new File(var3148, "Class.html"));
	}

	public CostModel instantiateCostModel518() {
		return new CostModel();
	}

	public SourceLoader instantiateSourceLoader519() {
		ClassPath var3150 = new ClassPathFactory().createFromPaths(
				"src/test/java", "core/src/test/java");
		return new SourceLoader(var3150);
	}

	public GradeCategories instantiateGradeCategories520() {
		return new GradeCategories(50, 100);
	}

	/**
	 * Test method for the class com.google.test.metric.report.SourceReportGenerator
	 */
	public void testSourceReportGenerator986() throws Exception {
		CostModel var3151 = instantiateCostModel518();
		SourceLoader var3152 = instantiateSourceLoader519();
		GradeCategories var3153 = instantiateGradeCategories520();
		ClassPath var3147 = new DirectoryClassPath(new File("src-test"));
		File var3148 = new File("test-out");
		SourceReportGenerator var3154 = new SourceReportGenerator(var3153,
				var3152, var3148, var3151, new Date(), 10, new Configuration());
		ClassRepository var3155 = new JavaClassRepository();
		MetricComputer var3156 = new MetricComputerBuilder()
				.withClassRepository(var3155).build();
		ClassInfo var3157 = var3155.getClass(Example.class.getCanonicalName());
		ClassCost var3158 = var3156.compute(var3157);
		var3151.computeClass(var3158);
		var3151.computeClass(var3158);
		var3152.load("X-I don't exist-X");
		var3152.load("X-I don't exist-X");
		var3153.getGoodCount(asList(1, 2, 2, 3));
		var3153.getNeedsWorkCount(asList(1, 2, 3, 3));
		MetricComputer var3139;
		var3139 = new MetricComputer(new JavaClassRepository(), null,
				new RegExpWhiteList(), 1);
		ClassCost var3161 = var3139.compute(TestClass.class.getCanonicalName());
		var3154.createClassReport(var3161);
		var3154.printHeader();
		ClassRepository var3143 = new JavaClassRepository();
		MetricComputer var3144 = new MetricComputer(var3143, null,
				new RegExpWhiteList("!com.google"), 1);
		ClassCost var3145 = var3144.compute(var3143
				.getClass(ClassSourceReportTestExample.class.getName()));
		var3154.createClassReport(var3145);
		GradeCategories var3146 = new GradeCategories(50, 100);
		SourceReportGenerator var3142 = new SourceReportGenerator(var3146,
				new SourceLoader(var3147), var3148, new CostModel(),
				new Date(), 10, new Configuration());
		var3142.printHeader();
		ClassReport var3149 = var3142.createClassReport(var3145);
		var3154.write("Class.html", var3149, new File(var3148, "Class.html"));
	}

	public CostModel instantiateCostModel521() {
		return new CostModel(1, 1, 1);
	}

	public SourceLoader instantiateSourceLoader522() {
		ClassPath var3150 = new ClassPathFactory().createFromPaths(
				"src/test/java", "core/src/test/java");
		return new SourceLoader(var3150);
	}

	public GradeCategories instantiateGradeCategories523() {
		return new GradeCategories(50, 100);
	}

	/**
	 * Test method for the class com.google.test.metric.report.SourceReportGenerator
	 */
	public void testSourceReportGenerator987() throws Exception {
		CostModel var3162 = instantiateCostModel521();
		SourceLoader var3163 = instantiateSourceLoader522();
		GradeCategories var3164 = instantiateGradeCategories523();
		ClassPath var3147 = new DirectoryClassPath(new File("src-test"));
		File var3148 = new File("test-out");
		SourceReportGenerator var3165 = new SourceReportGenerator(var3164,
				var3163, var3148, var3162, new Date(), 10, new Configuration());
		MethodCost var3133 = new MethodCost("", "a", 0, false, false, false);
		var3133.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3133.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		MethodCost var3134 = new MethodCost("", "b", 0, false, false, false);
		var3134.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3134.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3134.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3133.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var3134, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var3133.link();
		var3133.getTotalCost();
		var3162.computeOverall(var3133.getTotalCost());
		var3133.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3133.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		var3134.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3134.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3134.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3133.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var3134, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var3133.link();
		var3133.getTotalCost();
		var3162.computeOverall(var3133.getTotalCost());
		var3163.load(getClass().getName());
		var3163.load("X-I don't exist-X");
		var3164.createDistributionChart(asList(1, 2, 2, 3, 3, 3));
		var3164.getNeedsWorkCount(asList(1, 2, 3, 3));
		var3165.printHeader();
		GradeCategories var3146 = new GradeCategories(50, 100);
		PackageReport var3166 = new PackageReport(Testability.class
				.getPackage().getName(), var3146, new WeightedAverage());
		var3166.addClass("a.b.C", 30);
		var3166.addClass("a.b.D", 80);
		var3166.addClass("a.b.E", 130);
		var3165.write("Package.html", var3166,
				new File(var3148, "Package.html"));
		var3165.printHeader();
		var3166.addClass("a.b.C", 30);
		var3166.addClass("a.b.D", 80);
		var3166.addClass("a.b.E", 130);
		var3165.write("Package.html", var3166,
				new File(var3148, "Package.html"));
	}

	public CostModel instantiateCostModel524() {
		return new CostModel(1, 1, 10);
	}

	public SourceLoader instantiateSourceLoader525() {
		ClassPath var3150 = new ClassPathFactory().createFromPaths(
				"src/test/java", "core/src/test/java");
		return new SourceLoader(var3150);
	}

	public GradeCategories instantiateGradeCategories526() {
		return new GradeCategories(1, 2);
	}

	/**
	 * Test method for the class com.google.test.metric.report.SourceReportGenerator
	 */
	public void testSourceReportGenerator988() throws Exception {
		CostModel var3167 = instantiateCostModel524();
		SourceLoader var3168 = instantiateSourceLoader525();
		GradeCategories var3169 = instantiateGradeCategories526();
		ClassPath var3128 = new ClassPathFactory().createFromPaths(
				"src/test/java", "core/src/test/java");
		SourceReportGenerator var3170 = new SourceReportGenerator(var3169,
				var3168, null, var3167, new Date(), 10, new Configuration());
		MethodCost var3133 = new MethodCost("", "a", 0, false, false, false);
		var3133.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3133.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		MethodCost var3134 = new MethodCost("", "b", 0, false, false, false);
		var3134.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3134.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3134.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3133.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var3134, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var3133.link();
		var3133.getTotalCost();
		var3167.computeOverall(var3133.getTotalCost());
		var3133.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3133.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		var3134.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3134.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3134.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3133.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var3134, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var3133.link();
		var3133.getTotalCost();
		var3167.computeOverall(var3133.getTotalCost());
		var3168.load(getClass().getName());
		var3168.load("X-I don't exist-X");
		var3169.createHistogram(0, 0, asList(4, 90));
		var3169.getExcellentCount(asList(1, 1, 2, 3));
		var3170.printHeader();
		GradeCategories var3146 = new GradeCategories(50, 100);
		PackageReport var3166 = new PackageReport(Testability.class
				.getPackage().getName(), var3146, new WeightedAverage());
		var3166.addClass("a.b.C", 30);
		var3166.addClass("a.b.D", 80);
		var3166.addClass("a.b.E", 130);
		File var3148 = new File("test-out");
		var3170.write("Package.html", var3166,
				new File(var3148, "Package.html"));
		var3170.printHeader();
		ClassRepository var3143 = new JavaClassRepository();
		MetricComputer var3144 = new MetricComputer(var3143, null,
				new RegExpWhiteList("!com.google"), 1);
		ClassCost var3145 = var3144.compute(var3143
				.getClass(ClassSourceReportTestExample.class.getName()));
		var3170.createClassReport(var3145);
		ClassPath var3147 = new DirectoryClassPath(new File("src-test"));
		SourceReportGenerator var3142 = new SourceReportGenerator(var3146,
				new SourceLoader(var3147), var3148, new CostModel(),
				new Date(), 10, new Configuration());
		var3142.printHeader();
		ClassReport var3149 = var3142.createClassReport(var3145);
		var3170.write("Class.html", var3149, new File(var3148, "Class.html"));
	}

	public CostModel instantiateCostModel527() {
		return new CostModel();
	}

	public SourceLoader instantiateSourceLoader528() {
		ClassPath var3128 = new ClassPathFactory().createFromPaths(
				"src/test/java", "core/src/test/java");
		return new SourceLoader(var3128);
	}

	public GradeCategories instantiateGradeCategories529() {
		return new GradeCategories(1, 2);
	}

	/**
	 * Test method for the class com.google.test.metric.report.SourceReportGenerator
	 */
	public void testSourceReportGenerator989() throws Exception {
		CostModel var3171 = instantiateCostModel527();
		SourceLoader var3172 = instantiateSourceLoader528();
		GradeCategories var3173 = instantiateGradeCategories529();
		ClassPath var3147 = new DirectoryClassPath(new File("src-test"));
		File var3148 = new File("test-out");
		SourceReportGenerator var3174 = new SourceReportGenerator(var3173,
				var3172, var3148, var3171, new Date(), 10, new Configuration());
		ClassRepository var3155 = new JavaClassRepository();
		MetricComputer var3156 = new MetricComputerBuilder()
				.withClassRepository(var3155).build();
		ClassInfo var3157 = var3155.getClass(Example.class.getCanonicalName());
		ClassCost var3158 = var3156.compute(var3157);
		var3171.computeClass(var3158);
		var3171.computeClass(var3158);
		var3172.load(getClass().getName());
		var3172.load("X-I don't exist-X");
		var3173.getExcellentCount(asList(1, 1, 2, 3));
		var3173.createHistogram(0, 0, asList(4, 90));
		var3174.printHeader();
		GradeCategories var3146 = new GradeCategories(50, 100);
		ProjectReport var3175 = new ProjectReport(Testability.class
				.getPackage().getName(), var3146, new WeightedAverage());
		var3175.addClass("a.b.C", 30);
		var3175.addClass("a.b.D", 80);
		var3175.addClass("a.b.E", 130);
		var3175.addClass("a.b.F", 13);
		var3175.addClass("a.b.G", 10);
		var3175.addClass("a.b.H", 3);
		ProjectReport var3176 = new ProjectReport("", var3146,
				new WeightedAverage());
		var3176.addPackage("a.b.c", 1);
		var3176.addPackage("a.b.d", 51);
		var3176.addPackage("a.b.e", 101);
		var3174.write("Project.html",
				new ProjectSummaryReport(var3175, var3176), new File(var3148,
						"Project.html"));
		MetricComputer var3139;
		var3139 = new MetricComputer(new JavaClassRepository(), null,
				new RegExpWhiteList(), 1);
		ClassCost var3161 = var3139.compute(TestClass.class.getCanonicalName());
		var3174.createClassReport(var3161);
	}

	private static class InnerClass {
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

	static class TestClass {
		void m1() {
			CostUtil.staticCost4();
		}

		void m2() {
			CostUtil.staticCost3();
		}
	}

	static class HasImplicitCostFromOtherClass extends HasSetterCost {
		public void doThing() {
		}
	}
}