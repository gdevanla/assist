package com.google.test.metric.report;


import com.google.test.metric.CostModel;
import com.google.test.metric.report.html.HtmlReportModel;
import com.google.test.metric.report.issues.Issue;
import com.google.test.metric.report.issues.IssueType;
import freemarker.template.DefaultObjectWrapper;
import com.google.test.metric.report.XMLReportGenerator;
import com.google.test.metric.Cost;
import com.google.test.metric.report.issues.IssuesReporter;
import java.io.PrintStream;
import junit.framework.TestCase;
import org.apache.xml.serialize.OutputFormat;
import com.google.test.metric.report.issues.IssueSubType;
import com.google.test.metric.report.html.SourceLinkerModel;
import com.google.test.metric.MethodCost;
import freemarker.ext.beans.ResourceBundleModel;
import com.google.common.collect.Lists;
import com.google.test.metric.report.about.AboutTestabilityReport;
import com.google.test.metric.report.Source.Line;
import java.lang.Class;
import com.google.test.metric.report.ClassPathTemplateLoader;
import java.util.ResourceBundle;
import freemarker.ext.beans.BeansWrapper;
import java.io.ByteArrayOutputStream;
import com.google.test.metric.report.SourceLinker;
import com.google.test.metric.Reason;
import java.util.LinkedList;
import com.google.test.metric.CyclomaticCost;
import freemarker.template.Configuration;
import java.util.List;
import java.lang.Override;
import com.google.test.metric.report.SourceLoader;
import java.lang.String;
import com.google.test.metric.ViolationCost;
import java.io.StringWriter;
import com.google.test.metric.report.ReportOptions;
import com.google.test.metric.report.issues.ClassIssues;
import com.google.test.metric.MethodInvocationCost;
import com.google.test.metric.report.ReportModel;
import com.google.test.metric.report.Source;
import com.google.test.metric.SourceLocation;
import com.google.test.metric.ReportGeneratorProvider;
import org.apache.xml.serialize.XMLSerializer;
import com.google.test.metric.AnalysisModel;
import java.util.Arrays;
import com.google.test.metric.report.issues.HypotheticalCostModel;
import com.google.test.metric.ClassCost;
import static java.util.Arrays.asList;
import static java.util.ResourceBundle.getBundle;
import static com.google.test.metric.report.FreemarkerReportGenerator.HTML_REPORT_TEMPLATE;
import static java.text.MessageFormat.format;
import static com.google.test.metric.Reason.IMPLICIT_STATIC_INIT;
import static com.google.test.metric.Reason.NON_OVERRIDABLE_METHOD_CALL;

public class FreemarkerReportGeneratorGenTest extends TestCase {
	/**
	 * Test method for the class com.google.test.metric.report.FreemarkerReportGenerator
	 */
	public void testFreemarkerReportGenerator1000() throws Exception {
		HypotheticalCostModel var3187 = new HypotheticalCostModel(
				new CostModel());
		IssuesReporter var3188 = new IssuesReporter(
				new LinkedList<ClassIssues>(), var3187);
		ReportModel var3189 = new AboutTestabilityReport(var3188,
				new SourceLoader(null) {
					@Override
					public Source load(String var3190) {
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
		BeansWrapper var3191 = new DefaultObjectWrapper();
		ResourceBundleModel var3192 = new ResourceBundleModel(
				getBundle("messages"), var3191);
		var3189.setMessageBundle(var3192);
		ByteArrayOutputStream var3193 = new ByteArrayOutputStream();
		Configuration var3194 = new Configuration();
		var3194.setTemplateLoader(new ClassPathTemplateLoader(
				ReportGeneratorProvider.PREFIX));
		var3194.setObjectWrapper(var3191);
		FreemarkerReportGenerator var3195 = new FreemarkerReportGenerator(
				var3189, new PrintStream(var3193), "about/Report.html", var3194);
		var3195.printHeader();
		ClassCost var3197;
		MethodCost var3198 = new MethodCost("", "void setUp()", 1, false,
				false, false);
		SourceLocation var3199 = new SourceLocation("com/google/FooClass.java",
				1);
		var3198.addCostSource(new MethodInvocationCost(var3199, var3198,
				Reason.IMPLICIT_SETTER, new Cost(100, 1, new int[0])));
		var3197 = new ClassCost(getClass().getName(), Arrays.asList(var3198));
		var3195.addClassCost(var3197);
		var3195.printFooter();
		var3195.printHeader();
		var3195.addClassCost(new ClassCost(
				"com.google.test.metric.example.Lessons.SumOfPrimes1",
				asList(new MethodCost("", "foo()", 1, false, false, false))));
		var3195.printFooter();
	}

	public HtmlReportModel instantiateHtmlReportModel530() {
		CostModel var3201 = new CostModel();
		MethodCost var3198 = new MethodCost("", "void setUp()", 1, false,
				false, false);
		SourceLocation var3199 = new SourceLocation("com/google/FooClass.java",
				1);
		var3198.addCostSource(new MethodInvocationCost(var3199, var3198,
				Reason.IMPLICIT_SETTER, new Cost(100, 1, new int[0])));
		ClassCost var3202 = new ClassCost("com/google/FooClass",
				Arrays.asList(var3198));
		List<ClassIssues> var3203 = Lists.newLinkedList();
		Issue var3204 = new Issue(var3199, "void doThing()", 0.5f,
				IssueType.CONSTRUCTION, IssueSubType.SETTER);
		IssuesReporter var3205 = new IssuesReporter(null, null) {
			@Override
			public List<ClassIssues> getMostImportantIssues() {
				ClassCost var3202 = new ClassCost("com/google/FooClass",
						Arrays.asList(var3198));
				var3203.add(determineIssues(var3202));
				return var3203;
			}

			@Override
			public void inspectClass(ClassCost var3206) {
			}

			@Override
			public ClassIssues determineIssues(ClassCost var3207) {
				return new ClassIssues("com/google/FooClass", 100,
						new LinkedList<Issue>(Arrays.asList(var3204)));
			}
		};
		ReportOptions var3208 = new ReportOptions(1, 10, 1, 10, 20, 5, 100,
				100, 1, 10, "", "");
		return new HtmlReportModel(var3201, new AnalysisModel(var3205), var3208);
	}

	/**
	 * Test method for the class com.google.test.metric.report.FreemarkerReportGenerator
	 */
	public void testFreemarkerReportGenerator1001() throws Exception {
		HtmlReportModel var3209 = instantiateHtmlReportModel530();
		CostModel var3201 = new CostModel();
		MethodCost var3198 = new MethodCost("", "void setUp()", 1, false,
				false, false);
		SourceLocation var3199 = new SourceLocation("com/google/FooClass.java",
				1);
		var3198.addCostSource(new MethodInvocationCost(var3199, var3198,
				Reason.IMPLICIT_SETTER, new Cost(100, 1, new int[0])));
		ClassCost var3202 = new ClassCost("com/google/FooClass",
				Arrays.asList(var3198));
		List<ClassIssues> var3203 = Lists.newLinkedList();
		Issue var3204 = new Issue(var3199, "void doThing()", 0.5f,
				IssueType.CONSTRUCTION, IssueSubType.SETTER);
		IssuesReporter var3205 = new IssuesReporter(null, null) {
			@Override
			public List<ClassIssues> getMostImportantIssues() {
				ClassCost var3202 = new ClassCost("com/google/FooClass",
						Arrays.asList(var3198));
				var3203.add(determineIssues(var3202));
				return var3203;
			}

			@Override
			public void inspectClass(ClassCost var3206) {
			}

			@Override
			public ClassIssues determineIssues(ClassCost var3207) {
				return new ClassIssues("com/google/FooClass", 100,
						new LinkedList<Issue>(Arrays.asList(var3204)));
			}
		};
		ReportOptions var3208 = new ReportOptions(1, 10, 1, 10, 20, 5, 100,
				100, 1, 10, "", "");
		BeansWrapper var3210 = new DefaultObjectWrapper();
		ResourceBundleModel var3211 = new ResourceBundleModel(
				getBundle("messages"), var3210);
		var3212.setMessageBundle(var3211);
		SourceLinker var3213 = new SourceLinker(
				"http://code.repository/basepath/{path}&line={line}",
				"http://code.repository/basepath/{path}");
		var3212.setSourceLinker(new SourceLinkerModel(var3213));
		ByteArrayOutputStream var3214;
		var3214 = new ByteArrayOutputStream();
		Configuration var3215 = new Configuration();
		var3215.setObjectWrapper(var3210);
		var3215.setTemplateLoader(new ClassPathTemplateLoader(
				ReportGeneratorProvider.PREFIX));
		FreemarkerReportGenerator var3216 = new FreemarkerReportGenerator(
				var3209, new PrintStream(var3214), HTML_REPORT_TEMPLATE,
				var3215);
		var3209.setMessageBundle(var3211);
		var3209.setSourceLinker(new SourceLinkerModel(var3213));
		var3209.setMessageBundle(var3211);
		var3209.setSourceLinker(new SourceLinkerModel(var3213));
		var3216.printHeader();
		ClassCost var3217 = new ClassCost("classFoo",
				Arrays.asList(new MethodCost("", "methodFoo", 1, false, false,
						false)));
		var3216.addClassCost(var3217);
		var3216.printFooter();
		var3216.printHeader();
		ClassCost var3197;
		var3198.addCostSource(new MethodInvocationCost(var3199, var3198,
				Reason.IMPLICIT_SETTER, new Cost(100, 1, new int[0])));
		var3197 = new ClassCost(getClass().getName(), Arrays.asList(var3198));
		var3216.addClassCost(var3197);
		var3216.printFooter();
	}

	public XMLReportGenerator instantiateXMLReportGenerator531() {
		XMLSerializer var3218 = new XMLSerializer();
		StringWriter var3219 = new StringWriter();
		var3218.setOutputCharStream(var3219);
		var3218.startDocument();
		OutputFormat var3220 = new OutputFormat();
		var3220.setIndenting(true);
		var3218.setOutputFormat(var3220);
		CostModel var3221 = new CostModel();
		return new XMLReportGenerator(var3218, var3221, 0, 0, 0);
	}

	/**
	 * Test method for the class com.google.test.metric.report.FreemarkerReportGenerator
	 */
	public void testFreemarkerReportGenerator1002() throws Exception {
		XMLReportGenerator var3222 = instantiateXMLReportGenerator531();
		CostModel var3201 = new CostModel();
		MethodCost var3198 = new MethodCost("", "void setUp()", 1, false,
				false, false);
		SourceLocation var3199 = new SourceLocation("com/google/FooClass.java",
				1);
		var3198.addCostSource(new MethodInvocationCost(var3199, var3198,
				Reason.IMPLICIT_SETTER, new Cost(100, 1, new int[0])));
		ClassCost var3202 = new ClassCost("com/google/FooClass",
				Arrays.asList(var3198));
		List<ClassIssues> var3203 = Lists.newLinkedList();
		Issue var3204 = new Issue(var3199, "void doThing()", 0.5f,
				IssueType.CONSTRUCTION, IssueSubType.SETTER);
		IssuesReporter var3205 = new IssuesReporter(null, null) {
			@Override
			public List<ClassIssues> getMostImportantIssues() {
				ClassCost var3202 = new ClassCost("com/google/FooClass",
						Arrays.asList(var3198));
				var3203.add(determineIssues(var3202));
				return var3203;
			}

			@Override
			public void inspectClass(ClassCost var3206) {
			}

			@Override
			public ClassIssues determineIssues(ClassCost var3207) {
				return new ClassIssues("com/google/FooClass", 100,
						new LinkedList<Issue>(Arrays.asList(var3204)));
			}
		};
		ReportOptions var3208 = new ReportOptions(1, 10, 1, 10, 20, 5, 100,
				100, 1, 10, "", "");
		HtmlReportModel var3212 = new HtmlReportModel(var3201,
				new AnalysisModel(var3205), var3208);
		BeansWrapper var3210 = new DefaultObjectWrapper();
		ResourceBundleModel var3211 = new ResourceBundleModel(
				getBundle("messages"), var3210);
		var3212.setMessageBundle(var3211);
		SourceLinker var3213 = new SourceLinker(
				"http://code.repository/basepath/{path}&line={line}",
				"http://code.repository/basepath/{path}");
		var3212.setSourceLinker(new SourceLinkerModel(var3213));
		ByteArrayOutputStream var3214;
		var3214 = new ByteArrayOutputStream();
		Configuration var3215 = new Configuration();
		var3215.setObjectWrapper(var3210);
		var3215.setTemplateLoader(new ClassPathTemplateLoader(
				ReportGeneratorProvider.PREFIX));
		FreemarkerReportGenerator var3223 = new FreemarkerReportGenerator(
				var3212, new PrintStream(var3214), HTML_REPORT_TEMPLATE,
				var3215);
		MethodCost var3224 = new MethodCost("", "methodName", 1, false, false,
				false);
		var3224.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3224.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		ViolationCost var3225 = new MethodInvocationCost(new SourceLocation(
				"source.file", 123), var3224, IMPLICIT_STATIC_INIT, Cost
				.cyclomatic(2).add(Cost.global(3)));
		var3222.writeCost(var3225);
		var3224.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3224.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3222.writeCost(var3225);
		var3223.printHeader();
		ClassCost var3217 = new ClassCost("classFoo",
				Arrays.asList(new MethodCost("", "methodFoo", 1, false, false,
						false)));
		var3223.addClassCost(var3217);
		var3223.printFooter();
		var3223.printHeader();
		var3223.addClassCost(var3217);
		var3223.printFooter();
	}

	public HtmlReportModel instantiateHtmlReportModel532() {
		CostModel var3201 = new CostModel();
		MethodCost var3198 = new MethodCost("", "void setUp()", 1, false,
				false, false);
		SourceLocation var3199 = new SourceLocation("com/google/FooClass.java",
				1);
		var3198.addCostSource(new MethodInvocationCost(var3199, var3198,
				Reason.IMPLICIT_SETTER, new Cost(100, 1, new int[0])));
		ClassCost var3202 = new ClassCost("com/google/FooClass",
				Arrays.asList(var3198));
		List<ClassIssues> var3203 = Lists.newLinkedList();
		Issue var3204 = new Issue(var3199, "void doThing()", 0.5f,
				IssueType.CONSTRUCTION, IssueSubType.SETTER);
		IssuesReporter var3205 = new IssuesReporter(null, null) {
			@Override
			public List<ClassIssues> getMostImportantIssues() {
				ClassCost var3202 = new ClassCost("com/google/FooClass",
						Arrays.asList(var3198));
				var3203.add(determineIssues(var3202));
				return var3203;
			}

			@Override
			public void inspectClass(ClassCost var3206) {
			}

			@Override
			public ClassIssues determineIssues(ClassCost var3207) {
				return new ClassIssues("com/google/FooClass", 100,
						new LinkedList<Issue>(Arrays.asList(var3204)));
			}
		};
		ReportOptions var3208 = new ReportOptions(1, 10, 1, 10, 20, 5, 100,
				100, 1, 10, "", "");
		return new HtmlReportModel(var3201, new AnalysisModel(var3205), var3208);
	}

	/**
	 * Test method for the class com.google.test.metric.report.FreemarkerReportGenerator
	 */
	public void testFreemarkerReportGenerator1003() throws Exception {
		HtmlReportModel var3227 = instantiateHtmlReportModel532();
		HypotheticalCostModel var3187 = new HypotheticalCostModel(
				new CostModel());
		IssuesReporter var3188 = new IssuesReporter(
				new LinkedList<ClassIssues>(), var3187);
		ReportModel var3189 = new AboutTestabilityReport(var3188,
				new SourceLoader(null) {
					@Override
					public Source load(String var3190) {
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
		BeansWrapper var3191 = new DefaultObjectWrapper();
		ResourceBundleModel var3192 = new ResourceBundleModel(
				getBundle("messages"), var3191);
		var3189.setMessageBundle(var3192);
		ByteArrayOutputStream var3193 = new ByteArrayOutputStream();
		Configuration var3194 = new Configuration();
		var3194.setTemplateLoader(new ClassPathTemplateLoader(
				ReportGeneratorProvider.PREFIX));
		var3194.setObjectWrapper(var3191);
		FreemarkerReportGenerator var3195 = new FreemarkerReportGenerator(
				var3189, new PrintStream(var3193), "about/Report.html", var3194);
		BeansWrapper var3210 = new DefaultObjectWrapper();
		ResourceBundleModel var3211 = new ResourceBundleModel(
				getBundle("messages"), var3210);
		var3227.setMessageBundle(var3211);
		SourceLinker var3213 = new SourceLinker(
				"http://code.repository/basepath/{path}&line={line}",
				"http://code.repository/basepath/{path}");
		var3227.setSourceLinker(new SourceLinkerModel(var3213));
		var3227.setMessageBundle(var3211);
		var3227.setSourceLinker(new SourceLinkerModel(var3213));
		var3195.printHeader();
		ClassCost var3217 = new ClassCost("classFoo",
				Arrays.asList(new MethodCost("", "methodFoo", 1, false, false,
						false)));
		var3195.addClassCost(var3217);
		var3195.printFooter();
		var3195.printHeader();
		var3195.addClassCost(var3217);
		var3195.printFooter();
	}

	public AboutTestabilityReport instantiateAboutTestabilityReport533() {
		HypotheticalCostModel var3187 = new HypotheticalCostModel(
				new CostModel());
		IssuesReporter var3188 = new IssuesReporter(
				new LinkedList<ClassIssues>(), var3187);
		return new AboutTestabilityReport(var3188, new SourceLoader(null) {
			@Override
			public Source load(String var3190) {
				return new Source(
						asList(new Line(1, "Copyright garbage!"), new Line(2,
								"package com.google.test.metric.example;"),
								new Line(3, "import java.util.List;"),
								new Line(4, "  "), new Line(5,
										"class SumOfPrimes {"), new Line(6,
										"  public void sum() {}"), new Line(7,
										"}")));
			}
		});
	}

	/**
	 * Test method for the class com.google.test.metric.report.FreemarkerReportGenerator
	 */
	public void testFreemarkerReportGenerator1004() throws Exception {
		AboutTestabilityReport var3228 = instantiateAboutTestabilityReport533();
		CostModel var3201 = new CostModel();
		MethodCost var3198 = new MethodCost("", "void setUp()", 1, false,
				false, false);
		SourceLocation var3199 = new SourceLocation("com/google/FooClass.java",
				1);
		var3198.addCostSource(new MethodInvocationCost(var3199, var3198,
				Reason.IMPLICIT_SETTER, new Cost(100, 1, new int[0])));
		ClassCost var3202 = new ClassCost("com/google/FooClass",
				Arrays.asList(var3198));
		List<ClassIssues> var3203 = Lists.newLinkedList();
		Issue var3204 = new Issue(var3199, "void doThing()", 0.5f,
				IssueType.CONSTRUCTION, IssueSubType.SETTER);
		IssuesReporter var3205 = new IssuesReporter(null, null) {
			@Override
			public List<ClassIssues> getMostImportantIssues() {
				ClassCost var3202 = new ClassCost("com/google/FooClass",
						Arrays.asList(var3198));
				var3203.add(determineIssues(var3202));
				return var3203;
			}

			@Override
			public void inspectClass(ClassCost var3206) {
			}

			@Override
			public ClassIssues determineIssues(ClassCost var3207) {
				return new ClassIssues("com/google/FooClass", 100,
						new LinkedList<Issue>(Arrays.asList(var3204)));
			}
		};
		ReportOptions var3208 = new ReportOptions(1, 10, 1, 10, 20, 5, 100,
				100, 1, 10, "", "");
		HtmlReportModel var3212 = new HtmlReportModel(var3201,
				new AnalysisModel(var3205), var3208);
		BeansWrapper var3210 = new DefaultObjectWrapper();
		ResourceBundleModel var3211 = new ResourceBundleModel(
				getBundle("messages"), var3210);
		var3212.setMessageBundle(var3211);
		SourceLinker var3213 = new SourceLinker(
				"http://code.repository/basepath/{path}&line={line}",
				"http://code.repository/basepath/{path}");
		var3212.setSourceLinker(new SourceLinkerModel(var3213));
		ByteArrayOutputStream var3214;
		var3214 = new ByteArrayOutputStream();
		Configuration var3215 = new Configuration();
		var3215.setObjectWrapper(var3210);
		var3215.setTemplateLoader(new ClassPathTemplateLoader(
				ReportGeneratorProvider.PREFIX));
		FreemarkerReportGenerator var3223 = new FreemarkerReportGenerator(
				var3212, new PrintStream(var3214), HTML_REPORT_TEMPLATE,
				var3215);
		BeansWrapper var3191 = new DefaultObjectWrapper();
		ResourceBundleModel var3192 = new ResourceBundleModel(
				getBundle("messages"), var3191);
		var3228.setMessageBundle(var3192);
		var3228.setMessageBundle(var3192);
		var3223.printHeader();
		ClassCost var3197;
		var3198.addCostSource(new MethodInvocationCost(var3199, var3198,
				Reason.IMPLICIT_SETTER, new Cost(100, 1, new int[0])));
		var3197 = new ClassCost(getClass().getName(), Arrays.asList(var3198));
		var3223.addClassCost(var3197);
		var3223.printFooter();
		var3223.printHeader();
		ClassCost var3217 = new ClassCost("classFoo",
				Arrays.asList(new MethodCost("", "methodFoo", 1, false, false,
						false)));
		var3223.addClassCost(var3217);
		var3223.printFooter();
	}
}