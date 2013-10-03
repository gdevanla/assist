package com.google.test.metric;


import com.google.classpath.ClassPathFactory;
import com.google.test.metric.ClassInfo;
import com.google.test.metric.Cost;
import com.google.test.metric.report.issues.IssuesReporter;
import org.apache.xml.serialize.OutputFormat;
import freemarker.ext.beans.ResourceBundleModel;
import com.google.test.metric.report.about.AboutTestabilityReport;
import com.google.test.metric.report.Source.Line;
import java.lang.Class;
import com.google.test.metric.report.ClassPathTemplateLoader;
import java.util.ResourceBundle;
import freemarker.ext.beans.BeansWrapper;
import com.google.test.metric.Reason;
import java.util.LinkedList;
import com.google.test.metric.CyclomaticCost;
import freemarker.template.Configuration;
import java.lang.Object;
import java.util.List;
import com.google.classpath.DirectoryClassPath;
import com.google.test.metric.report.SourceLoader;
import com.google.test.metric.MetricComputer;
import com.google.test.metric.report.ClassReport;
import com.google.test.metric.javasrc.JavaSrcRepository;
import java.io.StringWriter;
import com.google.test.metric.report.issues.ClassIssues;
import com.google.test.metric.MethodInvocationCost;
import com.google.test.metric.report.ReportModel;
import java.io.File;
import com.google.test.metric.ReportGeneratorProvider;
import org.apache.xml.serialize.XMLSerializer;
import java.util.Arrays;
import com.google.test.metric.report.issues.HypotheticalCostModel;
import com.google.test.metric.ClassRepository;
import com.google.test.metric.ClassCost;
import com.google.test.metric.CostModel;
import com.google.test.metric.RegExpWhiteList;
import freemarker.template.DefaultObjectWrapper;
import com.google.test.metric.report.XMLReportGenerator;
import java.io.PrintStream;
import junit.framework.TestCase;
import com.google.test.metric.report.GradeCategories;
import com.google.test.metric.MethodCost;
import com.google.classpath.ClassPath;
import java.io.ByteArrayOutputStream;
import java.lang.Override;
import com.google.test.metric.report.ReportGenerator;
import com.google.test.metric.JavaClassRepository;
import java.lang.String;
import java.util.Date;
import com.google.test.metric.ViolationCost;
import com.google.test.metric.report.Source;
import com.google.test.metric.report.SourceReportGenerator;
import com.google.test.metric.SourceLocation;
import com.google.test.metric.report.TextReportGenerator;
import com.google.test.metric.report.FreemarkerReportGenerator;
import static java.util.Arrays.asList;
import static java.util.ResourceBundle.getBundle;
import static com.google.test.metric.report.FreemarkerReportGenerator.HTML_REPORT_TEMPLATE;
import static java.text.MessageFormat.format;
import static com.google.test.metric.Reason.IMPLICIT_STATIC_INIT;
import static com.google.test.metric.Reason.NON_OVERRIDABLE_METHOD_CALL;

public class JavaTestabilityRunnerGenTest extends TestCase {
	public SourceReportGenerator instantiateSourceReportGenerator487() {
		GradeCategories var3006 = new GradeCategories(50, 100);
		ClassPath var3007 = new DirectoryClassPath(new File("src-test"));
		File var3008 = new File("test-out");
		return new SourceReportGenerator(var3006, new SourceLoader(var3007),
				var3008, new CostModel(), new Date(), 10, new Configuration());
	}

	public MetricComputer instantiateMetricComputer488() {
		return new MetricComputer(new JavaClassRepository(), null,
				new RegExpWhiteList(), 1);
	}

	public RegExpWhiteList instantiateRegExpWhiteList489() {
		return new RegExpWhiteList("java.");
	}

	public JavaClassRepository instantiateJavaClassRepository490() {
		ClassPath var3009 = new ClassPathFactory().createFromJVM();
		return new JavaClassRepository(var3009);
	}

	/**
	 * Test method for the class com.google.test.metric.JavaTestabilityRunner
	 */
	public void testJavaTestabilityRunner965() throws Exception {
		RegExpWhiteList var3010 = instantiateRegExpWhiteList489();
		JavaClassRepository var3011 = instantiateJavaClassRepository490();
		MetricComputer var3012 = instantiateMetricComputer488();
		SourceReportGenerator var3013 = instantiateSourceReportGenerator487();
		ByteArrayOutputStream var3014 = new ByteArrayOutputStream();
		ReportGenerator var3015 = new TextReportGenerator(new PrintStream(
				var3014), new CostModel(), 0, 0, 0);
		ClassPath var3016 = new ClassPathFactory().createFromPaths(var3017,
				"core/" + var3017);
		ClassRepository var3018 = new JavaClassRepository(var3016);
		ByteArrayOutputStream var3019 = new ByteArrayOutputStream();
		PrintStream var3020 = new PrintStream(var3019);
		List<String> var3021 = Arrays.asList("");
		JavaTestabilityRunner var3022 = new JavaTestabilityRunner(var3015,
				var3016, var3018, var3012, var3021, var3010, new PrintStream(
						var3019));
		var3010.isClassWhiteListed("java.lang.String");
		var3010.isClassWhiteListed("com.company.String");
		var3010.addPackage("javax.");
		var3012.compute(Setters.class.getCanonicalName());
		var3012.compute(TestClass.class.getCanonicalName());
		var3011.getClass(DeeplyNestedIfStatements.class.getCanonicalName());
		var3011.getClass(DeeplyNestedIfStatements.class.getCanonicalName());
		MetricComputer var3026;
		var3026 = new MetricComputer(new JavaClassRepository(), null,
				new RegExpWhiteList(), 1);
		ClassCost var3028 = var3026.compute(HasImplicitCostFromOtherClass.class
				.getCanonicalName());
		var3013.createClassReport(var3028);
		var3026 = new MetricComputer(new JavaClassRepository(), null,
				new RegExpWhiteList(), 1);
		var3013.createClassReport(var3028);
	}

	public FreemarkerReportGenerator instantiateFreemarkerReportGenerator491() {
		HypotheticalCostModel var3030 = new HypotheticalCostModel(
				new CostModel());
		IssuesReporter var3031 = new IssuesReporter(
				new LinkedList<ClassIssues>(), var3030);
		ReportModel var3032 = new AboutTestabilityReport(var3031,
				new SourceLoader(null) {
					@Override
					public Source load(String var3033) {
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
		BeansWrapper var3034 = new DefaultObjectWrapper();
		ResourceBundleModel var3035 = new ResourceBundleModel(
				getBundle("messages"), var3034);
		var3032.setMessageBundle(var3035);
		ByteArrayOutputStream var3036 = new ByteArrayOutputStream();
		Configuration var3037 = new Configuration();
		var3037.setTemplateLoader(new ClassPathTemplateLoader(
				ReportGeneratorProvider.PREFIX));
		var3037.setObjectWrapper(var3034);
		return new FreemarkerReportGenerator(var3032, new PrintStream(var3036),
				"about/Report.html", var3037);
	}

	public MetricComputer instantiateMetricComputer492() {
		return new MetricComputer(new JavaClassRepository(), null,
				new RegExpWhiteList(), 1);
	}

	public RegExpWhiteList instantiateRegExpWhiteList493() {
		return new RegExpWhiteList("java.");
	}

	public JavaClassRepository instantiateJavaClassRepository494() {
		return new JavaClassRepository();
	}

	/**
	 * Test method for the class com.google.test.metric.JavaTestabilityRunner
	 */
	public void testJavaTestabilityRunner966() throws Exception {
		RegExpWhiteList var3038 = instantiateRegExpWhiteList493();
		JavaClassRepository var3039 = instantiateJavaClassRepository494();
		MetricComputer var3040 = instantiateMetricComputer492();
		FreemarkerReportGenerator var3041 = instantiateFreemarkerReportGenerator491();
		ByteArrayOutputStream var3014 = new ByteArrayOutputStream();
		ReportGenerator var3015 = new TextReportGenerator(new PrintStream(
				var3014), new CostModel(), 0, 0, 0);
		ClassPath var3016 = new ClassPathFactory().createFromPaths(var3017,
				"core/" + var3017);
		ClassRepository var3018 = new JavaClassRepository(var3016);
		ByteArrayOutputStream var3019 = new ByteArrayOutputStream();
		PrintStream var3020 = new PrintStream(var3019);
		List<String> var3021 = Arrays.asList("");
		JavaTestabilityRunner var3042 = new JavaTestabilityRunner(var3015,
				var3016, var3018, var3040, var3021, var3038, new PrintStream(
						var3019));
		var3038.isClassWhiteListed("java.lang.String");
		var3038.isClassWhiteListed("java.x.Z");
		var3038.isClassWhiteListed("java.lang.String");
		var3038.isClassWhiteListed("com.company.String");
		var3040.compute(HasImplicitCostFromOtherClass.class.getCanonicalName());
		var3040.compute(Setters.class.getCanonicalName());
		var3039.getClass(Object.class.getCanonicalName());
		var3039.getClass(String.class.getCanonicalName());
		var3041.printHeader();
		var3041.addClassCost(new ClassCost(
				"com.google.test.metric.example.Lessons.SumOfPrimes1",
				asList(new MethodCost("", "foo()", 1, false, false, false))));
		var3041.printFooter();
		var3041.printHeader();
		ClassCost var3047;
		MethodCost var3048 = new MethodCost("", "void setUp()", 1, false,
				false, false);
		SourceLocation var3049 = new SourceLocation("com/google/FooClass.java",
				1);
		var3048.addCostSource(new MethodInvocationCost(var3049, var3048,
				Reason.IMPLICIT_SETTER, new Cost(100, 1, new int[0])));
		var3047 = new ClassCost(getClass().getName(), Arrays.asList(var3048));
		var3041.addClassCost(var3047);
		var3041.printFooter();
	}

	public MetricComputer instantiateMetricComputer495() {
		JavaClassRepository var3050 = new JavaClassRepository();
		return new MetricComputer(var3050, null, new RegExpWhiteList(), 1);
	}

	public RegExpWhiteList instantiateRegExpWhiteList496() {
		return new RegExpWhiteList("String");
	}

	public JavaClassRepository instantiateJavaClassRepository497() {
		return new JavaClassRepository();
	}

	/**
	 * Test method for the class com.google.test.metric.JavaTestabilityRunner
	 */
	public void testJavaTestabilityRunner967() throws Exception {
		RegExpWhiteList var3051 = instantiateRegExpWhiteList496();
		JavaClassRepository var3052 = instantiateJavaClassRepository497();
		MetricComputer var3053 = instantiateMetricComputer495();
		ByteArrayOutputStream var3014 = new ByteArrayOutputStream();
		ReportGenerator var3015 = new TextReportGenerator(new PrintStream(
				var3014), new CostModel(), 0, 0, 0);
		ClassPath var3016 = new ClassPathFactory().createFromPaths(var3017,
				"core/" + var3017);
		ClassRepository var3018 = new JavaClassRepository(var3016);
		ByteArrayOutputStream var3019 = new ByteArrayOutputStream();
		PrintStream var3020 = new PrintStream(var3019);
		List<String> var3021 = Arrays.asList("");
		JavaTestabilityRunner var3054 = new JavaTestabilityRunner(var3015,
				var3016, var3018, var3053, var3021, var3051, new PrintStream(
						var3019));
		var3051.isClassWhiteListed("java.lang.String");
		var3051.isClassWhiteListed("java.x.Z");
		var3051.isClassWhiteListed("java.lang.String");
		var3051.isClassWhiteListed("java.x.Z");
		var3053.compute(HasImplicitCostFromOtherClass.class.getCanonicalName());
		var3053.compute(HasImplicitCostFromOtherClass.class.getCanonicalName());
		var3052.getClass(Monitor.class.getCanonicalName());
		var3052.getClass(Medium.class.getCanonicalName());
	}

	public MetricComputer instantiateMetricComputer498() {
		JavaClassRepository var3050 = new JavaClassRepository();
		return new MetricComputer(var3050, null, new RegExpWhiteList(), 1);
	}

	public RegExpWhiteList instantiateRegExpWhiteList499() {
		return new RegExpWhiteList(".*String");
	}

	public JavaSrcRepository instantiateJavaSrcRepository500() {
		ClassRepository var3056 = new JavaClassRepository();
		return new JavaSrcRepository(var3056,
				new ClassPathFactory().createFromPaths("core/src/test/java",
						"src/test/java"));
	}

	/**
	 * Test method for the class com.google.test.metric.JavaTestabilityRunner
	 */
	public void testJavaTestabilityRunner968() throws Exception {
		RegExpWhiteList var3057 = instantiateRegExpWhiteList499();
		MetricComputer var3058 = instantiateMetricComputer498();
		JavaSrcRepository var3059 = instantiateJavaSrcRepository500();
		ByteArrayOutputStream var3014 = new ByteArrayOutputStream();
		ReportGenerator var3015 = new TextReportGenerator(new PrintStream(
				var3014), new CostModel(), 0, 0, 0);
		ClassPath var3016 = new ClassPathFactory().createFromPaths(var3017,
				"core/" + var3017);
		ClassRepository var3018 = new JavaClassRepository(var3016);
		ByteArrayOutputStream var3019 = new ByteArrayOutputStream();
		PrintStream var3020 = new PrintStream(var3019);
		List<String> var3021 = Arrays.asList("");
		JavaTestabilityRunner var3060 = new JavaTestabilityRunner(var3015,
				var3016, var3018, var3058, var3021, var3057, new PrintStream(
						var3019));
		var3057.isClassWhiteListed("java.lang.String");
		var3057.isClassWhiteListed("java.x.Z");
		var3057.isClassWhiteListed("java.lang.String");
		var3057.isClassWhiteListed("java.x.Z");
		var3058.compute(Setters.class.getCanonicalName());
		var3058.compute(HasImplicitCostFromOtherClass.class.getCanonicalName());
		var3059.getClass("IDontExist");
		var3059.getClass("pkg.A");
	}

	public XMLReportGenerator instantiateXMLReportGenerator501() {
		XMLSerializer var3063 = new XMLSerializer();
		StringWriter var3064 = new StringWriter();
		var3063.setOutputCharStream(var3064);
		var3063.startDocument();
		OutputFormat var3065 = new OutputFormat();
		var3065.setIndenting(true);
		var3063.setOutputFormat(var3065);
		CostModel var3066 = new CostModel();
		return new XMLReportGenerator(var3063, var3066, 0, 0, 0);
	}

	public MetricComputer instantiateMetricComputer502() {
		ClassPath var3016 = new ClassPathFactory().createFromPaths(var3017,
				"core/" + var3017);
		ClassRepository var3018 = new JavaClassRepository(var3016);
		ByteArrayOutputStream var3019 = new ByteArrayOutputStream();
		PrintStream var3020 = new PrintStream(var3019);
		RegExpWhiteList var3067 = new RegExpWhiteList("java.");
		return new MetricComputer(var3018, var3020, var3067, 0);
	}

	public RegExpWhiteList instantiateRegExpWhiteList503() {
		return new RegExpWhiteList(".*String");
	}

	public JavaClassRepository instantiateJavaClassRepository504() {
		return new JavaClassRepository();
	}

	/**
	 * Test method for the class com.google.test.metric.JavaTestabilityRunner
	 */
	public void testJavaTestabilityRunner969() throws Exception {
		RegExpWhiteList var3068 = instantiateRegExpWhiteList503();
		JavaClassRepository var3069 = instantiateJavaClassRepository504();
		MetricComputer var3070 = instantiateMetricComputer502();
		XMLReportGenerator var3071 = instantiateXMLReportGenerator501();
		ByteArrayOutputStream var3014 = new ByteArrayOutputStream();
		ReportGenerator var3015 = new TextReportGenerator(new PrintStream(
				var3014), new CostModel(), 0, 0, 0);
		ClassPath var3016 = new ClassPathFactory().createFromPaths(var3017,
				"core/" + var3017);
		ClassRepository var3018 = new JavaClassRepository(var3016);
		ByteArrayOutputStream var3019 = new ByteArrayOutputStream();
		PrintStream var3020 = new PrintStream(var3019);
		List<String> var3021 = Arrays.asList("");
		JavaTestabilityRunner var3072 = new JavaTestabilityRunner(var3015,
				var3016, var3018, var3070, var3021, var3068, new PrintStream(
						var3019));
		var3068.isClassWhiteListed("java.lang.String");
		var3068.isClassWhiteListed("java.x.Z");
		var3068.isClassWhiteListed("java.lang.String");
		var3068.isClassWhiteListed("com.company.String");
		var3070.compute(TestClass.class.getCanonicalName());
		var3070.compute(Setters.class.getCanonicalName());
		var3069.getClass(EmptyClass.class.getCanonicalName());
		var3069.getClass(EmptyClass.class.getCanonicalName());
		MethodCost var3074 = new MethodCost("", "methodName", 1, false, false,
				false);
		var3074.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3074.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		ViolationCost var3075 = new MethodInvocationCost(new SourceLocation(
				"source.file", 123), var3074, IMPLICIT_STATIC_INIT, Cost
				.cyclomatic(2).add(Cost.global(3)));
		var3071.writeCost(var3075);
		var3074.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3074.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3071.writeCost(var3075);
	}

	public static class EmptyClass {
	}

	private static class Monitor {
		public void method() {
			synchronized (this) {
				hashCode();
			}
		}

		public void method2() {
			hashCode();
			synchronized (this) {
				hashCode();
			}
			hashCode();
		}
	}

	public static class Medium {
		public Medium() {
			statiCost1();
			cost2();
		}

		/**
		 * I cost 1
		 */
		public static int statiCost1() {
			int i = 0;
			return i > 0 ? 1 : 2;
		}

		/**
		 * I cost 2, but I am instance method so I can be overridden. so my cost may be avoided in most cases.
		 */
		public int cost2() {
			int i = 0;
			return i > 0 ? i > 1 ? 1 : 2 : 2;
		}

		/**
		 * I cost 2, but I am a <em>final</em> instance method that can not be overridden. My cost is unavoidable.
		 */
		public final int finalCost2() {
			int i = 0;
			return i > 0 ? i > 1 ? 1 : 2 : 2;
		}

		/**
		 * I am instance method hence you will have to add the cost of constructor to me. (by myself I cost 4)
		 */
		public Object testMethod4() {
			int i = 0;
			i = i > 0 ? 1 : 2;
			i = i > 0 ? 1 : 2;
			i = i > 0 ? 1 : 2;
			i = i > 0 ? 1 : 2;
			return new Object();
		}
	}

	private static class DeeplyNestedIfStatements {
		@SuppressWarnings("unused")
		private static void nested(boolean x) {
			int num = (x ? 1 : 0) + (x ? 1 : 0) + (x ? 1 : 0) + (x ? 1 : 0)
					+ (x ? 1 : 0) + (x ? 1 : 0) + (x ? 1 : 0) + (x ? 1 : 0)
					+ (x ? 1 : 0) + (x ? 1 : 0) + (x ? 1 : 0) + (x ? 1 : 0)
					+ (x ? 1 : 0) + (x ? 1 : 0) + (x ? 1 : 0);
		}
	}

	private static class Setters {
		boolean foo;

		public void setFoo(String foo) {
			this.foo = (foo == null);
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