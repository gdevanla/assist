package com.google.test.metric.report.html;


import com.google.test.metric.CostModel;
import com.google.test.metric.report.issues.Issue;
import com.google.test.metric.report.issues.IssueType;
import com.google.test.metric.ClassInfo;
import freemarker.template.DefaultObjectWrapper;
import com.google.test.metric.Cost;
import com.google.test.metric.report.issues.IssuesReporter;
import junit.framework.TestCase;
import com.google.test.metric.report.issues.IssueSubType;
import com.google.test.metric.report.html.SourceLinkerModel;
import com.google.test.metric.MethodCost;
import freemarker.ext.beans.ResourceBundleModel;
import com.google.common.collect.Lists;
import java.util.ResourceBundle;
import freemarker.ext.beans.BeansWrapper;
import com.google.test.metric.GlobalCost;
import com.google.test.metric.report.SourceLinker;
import com.google.test.metric.Reason;
import java.util.LinkedList;
import com.google.test.metric.CyclomaticCost;
import com.google.test.metric.testing.MetricComputerBuilder;
import java.util.List;
import java.lang.Override;
import com.google.test.metric.MetricComputer;
import com.google.test.metric.JavaClassRepository;
import java.lang.String;
import com.google.test.metric.report.ReportOptions;
import com.google.test.metric.report.issues.ClassIssues;
import com.google.test.metric.MethodInvocationCost;
import com.google.test.metric.SourceLocation;
import java.util.Arrays;
import com.google.test.metric.AnalysisModel;
import com.google.test.metric.ClassRepository;
import com.google.test.metric.ClassCost;
import static com.google.test.metric.Reason.NON_OVERRIDABLE_METHOD_CALL;
import static com.google.test.metric.report.DrillDownReportGenerator.NEW_LINE;
import static java.lang.Integer.MAX_VALUE;
import static com.google.test.metric.report.FreemarkerReportGenerator.HTML_REPORT_TEMPLATE;
import static java.text.MessageFormat.format;
import static java.util.ResourceBundle.getBundle;
import static com.google.test.metric.Reason.IMPLICIT_STATIC_INIT;

public class HtmlReportModelGenTest extends TestCase {
	public ReportOptions instantiateReportOptions539() {
		return new ReportOptions(1, 10, 1, 10, 20, 5, 100, 100, 1, 10, "", "");
	}

	public CostModel instantiateCostModel540() {
		return new CostModel(2, 10, 1);
	}

	/**
	 * Test method for the class com.google.test.metric.report.html.HtmlReportModel
	 */
	public void testHtmlReportModel1010() throws Exception {
		ReportOptions var3250 = instantiateReportOptions539();
		CostModel var3251 = instantiateCostModel540();
		MethodCost var3252 = new MethodCost("", "void setUp()", 1, false,
				false, false);
		SourceLocation var3253 = new SourceLocation("com/google/FooClass.java",
				1);
		var3252.addCostSource(new MethodInvocationCost(var3253, var3252,
				Reason.IMPLICIT_SETTER, new Cost(100, 1, new int[0])));
		ClassCost var3254 = new ClassCost("com/google/FooClass",
				Arrays.asList(var3252));
		List<ClassIssues> var3255 = Lists.newLinkedList();
		Issue var3256 = new Issue(var3253, "void doThing()", 0.5f,
				IssueType.CONSTRUCTION, IssueSubType.SETTER);
		IssuesReporter var3257 = new IssuesReporter(null, null) {
			@Override
			public List<ClassIssues> getMostImportantIssues() {
				ClassCost var3254 = new ClassCost("com/google/FooClass",
						Arrays.asList(var3252));
				var3255.add(determineIssues(var3254));
				return var3255;
			}

			@Override
			public void inspectClass(ClassCost var3258) {
			}

			@Override
			public ClassIssues determineIssues(ClassCost var3259) {
				return new ClassIssues("com/google/FooClass", 100,
						new LinkedList<Issue>(Arrays.asList(var3256)));
			}
		};
		HtmlReportModel var3260 = new HtmlReportModel(var3251,
				new AnalysisModel(var3257), var3250);
		MethodCost var3261 = new MethodCost("", "a", 0, false, false, false);
		var3261.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3261.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		MethodCost var3262 = new MethodCost("", "b", 0, false, false, false);
		var3262.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3262.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3262.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3261.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var3262, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var3261.link();
		var3261.getTotalCost();
		var3251.computeOverall(var3261.getTotalCost());
		var3261.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3261.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		var3262.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3262.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3262.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3261.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var3262, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var3261.link();
		var3261.getTotalCost();
		var3251.computeOverall(var3261.getTotalCost());
		BeansWrapper var3264 = new DefaultObjectWrapper();
		ResourceBundleModel var3265 = new ResourceBundleModel(
				getBundle("messages"), var3264);
		var3260.setMessageBundle(var3265);
		SourceLinker var3267 = new SourceLinker(
				"http://code.repository/basepath/{path}&line={line}",
				"http://code.repository/basepath/{path}");
		var3260.setSourceLinker(new SourceLinkerModel(var3267));
		var3260.setMessageBundle(var3265);
		var3260.setSourceLinker(new SourceLinkerModel(var3267));
	}

	public ReportOptions instantiateReportOptions541() {
		return new ReportOptions(1, 10, 1, 10, 20, 5, 100, 100, 1, 10, "", "");
	}

	public CostModel instantiateCostModel542() {
		return new CostModel(1, 1, 10);
	}

	/**
	 * Test method for the class com.google.test.metric.report.html.HtmlReportModel
	 */
	public void testHtmlReportModel1011() throws Exception {
		ReportOptions var3268 = instantiateReportOptions541();
		CostModel var3269 = instantiateCostModel542();
		MethodCost var3252 = new MethodCost("", "void setUp()", 1, false,
				false, false);
		SourceLocation var3253 = new SourceLocation("com/google/FooClass.java",
				1);
		var3252.addCostSource(new MethodInvocationCost(var3253, var3252,
				Reason.IMPLICIT_SETTER, new Cost(100, 1, new int[0])));
		ClassCost var3254 = new ClassCost("com/google/FooClass",
				Arrays.asList(var3252));
		List<ClassIssues> var3255 = Lists.newLinkedList();
		Issue var3256 = new Issue(var3253, "void doThing()", 0.5f,
				IssueType.CONSTRUCTION, IssueSubType.SETTER);
		IssuesReporter var3257 = new IssuesReporter(null, null) {
			@Override
			public List<ClassIssues> getMostImportantIssues() {
				ClassCost var3254 = new ClassCost("com/google/FooClass",
						Arrays.asList(var3252));
				var3255.add(determineIssues(var3254));
				return var3255;
			}

			@Override
			public void inspectClass(ClassCost var3258) {
			}

			@Override
			public ClassIssues determineIssues(ClassCost var3259) {
				return new ClassIssues("com/google/FooClass", 100,
						new LinkedList<Issue>(Arrays.asList(var3256)));
			}
		};
		HtmlReportModel var3270 = new HtmlReportModel(var3269,
				new AnalysisModel(var3257), var3268);
		MethodCost var3261 = new MethodCost("", "a", 0, false, false, false);
		var3261.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3261.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		MethodCost var3262 = new MethodCost("", "b", 0, false, false, false);
		var3262.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3262.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3262.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3261.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var3262, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var3261.link();
		var3261.getTotalCost();
		var3269.computeOverall(var3261.getTotalCost());
		var3261.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3261.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		var3262.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3262.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3262.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3261.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var3262, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var3261.link();
		var3261.getTotalCost();
		var3269.computeOverall(var3261.getTotalCost());
		BeansWrapper var3264 = new DefaultObjectWrapper();
		ResourceBundleModel var3265 = new ResourceBundleModel(
				getBundle("messages"), var3264);
		var3270.setMessageBundle(var3265);
		SourceLinker var3267 = new SourceLinker(
				"http://code.repository/basepath/{path}&line={line}",
				"http://code.repository/basepath/{path}");
		var3270.setSourceLinker(new SourceLinkerModel(var3267));
		var3270.setMessageBundle(var3265);
		var3270.setSourceLinker(new SourceLinkerModel(var3267));
	}

	public ReportOptions instantiateReportOptions543() {
		return new ReportOptions(1, 10, 1, 10, 20, 5, 100, 100, 1, 10, "", "");
	}

	public CostModel instantiateCostModel544() {
		return new CostModel();
	}

	/**
	 * Test method for the class com.google.test.metric.report.html.HtmlReportModel
	 */
	public void testHtmlReportModel1012() throws Exception {
		ReportOptions var3271 = instantiateReportOptions543();
		CostModel var3272 = instantiateCostModel544();
		MethodCost var3252 = new MethodCost("", "void setUp()", 1, false,
				false, false);
		SourceLocation var3253 = new SourceLocation("com/google/FooClass.java",
				1);
		var3252.addCostSource(new MethodInvocationCost(var3253, var3252,
				Reason.IMPLICIT_SETTER, new Cost(100, 1, new int[0])));
		ClassCost var3254 = new ClassCost("com/google/FooClass",
				Arrays.asList(var3252));
		List<ClassIssues> var3255 = Lists.newLinkedList();
		Issue var3256 = new Issue(var3253, "void doThing()", 0.5f,
				IssueType.CONSTRUCTION, IssueSubType.SETTER);
		IssuesReporter var3257 = new IssuesReporter(null, null) {
			@Override
			public List<ClassIssues> getMostImportantIssues() {
				ClassCost var3254 = new ClassCost("com/google/FooClass",
						Arrays.asList(var3252));
				var3255.add(determineIssues(var3254));
				return var3255;
			}

			@Override
			public void inspectClass(ClassCost var3258) {
			}

			@Override
			public ClassIssues determineIssues(ClassCost var3259) {
				return new ClassIssues("com/google/FooClass", 100,
						new LinkedList<Issue>(Arrays.asList(var3256)));
			}
		};
		HtmlReportModel var3273 = new HtmlReportModel(var3272,
				new AnalysisModel(var3257), var3271);
		ClassRepository var3274 = new JavaClassRepository();
		MetricComputer var3275 = new MetricComputerBuilder()
				.withClassRepository(var3274).build();
		ClassInfo var3276 = var3274.getClass(Example.class.getCanonicalName());
		ClassCost var3277 = var3275.compute(var3276);
		var3272.computeClass(var3277);
		var3272.computeClass(var3277);
		BeansWrapper var3264 = new DefaultObjectWrapper();
		ResourceBundleModel var3265 = new ResourceBundleModel(
				getBundle("messages"), var3264);
		var3273.setMessageBundle(var3265);
		SourceLinker var3267 = new SourceLinker(
				"http://code.repository/basepath/{path}&line={line}",
				"http://code.repository/basepath/{path}");
		var3273.setSourceLinker(new SourceLinkerModel(var3267));
		var3273.setMessageBundle(var3265);
		var3273.setSourceLinker(new SourceLinkerModel(var3267));
	}

	public ReportOptions instantiateReportOptions545() {
		return new ReportOptions(1, 10, 1, 10, 20, 5, 100, 100, 1, 10, "", "");
	}

	public CostModel instantiateCostModel546() {
		return new CostModel();
	}

	/**
	 * Test method for the class com.google.test.metric.report.html.HtmlReportModel
	 */
	public void testHtmlReportModel1013() throws Exception {
		ReportOptions var3279 = instantiateReportOptions545();
		CostModel var3280 = instantiateCostModel546();
		MethodCost var3252 = new MethodCost("", "void setUp()", 1, false,
				false, false);
		SourceLocation var3253 = new SourceLocation("com/google/FooClass.java",
				1);
		var3252.addCostSource(new MethodInvocationCost(var3253, var3252,
				Reason.IMPLICIT_SETTER, new Cost(100, 1, new int[0])));
		ClassCost var3254 = new ClassCost("com/google/FooClass",
				Arrays.asList(var3252));
		List<ClassIssues> var3255 = Lists.newLinkedList();
		Issue var3256 = new Issue(var3253, "void doThing()", 0.5f,
				IssueType.CONSTRUCTION, IssueSubType.SETTER);
		IssuesReporter var3257 = new IssuesReporter(null, null) {
			@Override
			public List<ClassIssues> getMostImportantIssues() {
				ClassCost var3254 = new ClassCost("com/google/FooClass",
						Arrays.asList(var3252));
				var3255.add(determineIssues(var3254));
				return var3255;
			}

			@Override
			public void inspectClass(ClassCost var3258) {
			}

			@Override
			public ClassIssues determineIssues(ClassCost var3259) {
				return new ClassIssues("com/google/FooClass", 100,
						new LinkedList<Issue>(Arrays.asList(var3256)));
			}
		};
		HtmlReportModel var3281 = new HtmlReportModel(var3280,
				new AnalysisModel(var3257), var3279);
		ClassRepository var3274 = new JavaClassRepository();
		MetricComputer var3275 = new MetricComputerBuilder()
				.withClassRepository(var3274).build();
		ClassInfo var3276 = var3274.getClass(Example.class.getCanonicalName());
		ClassCost var3277 = var3275.compute(var3276);
		var3280.computeClass(var3277);
		var3280.computeClass(var3277);
		BeansWrapper var3264 = new DefaultObjectWrapper();
		ResourceBundleModel var3265 = new ResourceBundleModel(
				getBundle("messages"), var3264);
		var3281.setMessageBundle(var3265);
		SourceLinker var3267 = new SourceLinker(
				"http://code.repository/basepath/{path}&line={line}",
				"http://code.repository/basepath/{path}");
		var3281.setSourceLinker(new SourceLinkerModel(var3267));
		var3281.setMessageBundle(var3265);
		var3281.setSourceLinker(new SourceLinkerModel(var3267));
	}

	public ReportOptions instantiateReportOptions547() {
		return new ReportOptions(1, 10, 1, 10, 20, 5, 100, 100, 1, 10, "", "");
	}

	public CostModel instantiateCostModel548() {
		return new CostModel(2, 10, 1);
	}

	/**
	 * Test method for the class com.google.test.metric.report.html.HtmlReportModel
	 */
	public void testHtmlReportModel1014() throws Exception {
		ReportOptions var3282 = instantiateReportOptions547();
		CostModel var3283 = instantiateCostModel548();
		MethodCost var3252 = new MethodCost("", "void setUp()", 1, false,
				false, false);
		SourceLocation var3253 = new SourceLocation("com/google/FooClass.java",
				1);
		var3252.addCostSource(new MethodInvocationCost(var3253, var3252,
				Reason.IMPLICIT_SETTER, new Cost(100, 1, new int[0])));
		ClassCost var3254 = new ClassCost("com/google/FooClass",
				Arrays.asList(var3252));
		List<ClassIssues> var3255 = Lists.newLinkedList();
		Issue var3256 = new Issue(var3253, "void doThing()", 0.5f,
				IssueType.CONSTRUCTION, IssueSubType.SETTER);
		IssuesReporter var3257 = new IssuesReporter(null, null) {
			@Override
			public List<ClassIssues> getMostImportantIssues() {
				ClassCost var3254 = new ClassCost("com/google/FooClass",
						Arrays.asList(var3252));
				var3255.add(determineIssues(var3254));
				return var3255;
			}

			@Override
			public void inspectClass(ClassCost var3258) {
			}

			@Override
			public ClassIssues determineIssues(ClassCost var3259) {
				return new ClassIssues("com/google/FooClass", 100,
						new LinkedList<Issue>(Arrays.asList(var3256)));
			}
		};
		HtmlReportModel var3284 = new HtmlReportModel(var3283,
				new AnalysisModel(var3257), var3282);
		MethodCost var3261 = new MethodCost("", "a", 0, false, false, false);
		var3261.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3261.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		MethodCost var3262 = new MethodCost("", "b", 0, false, false, false);
		var3262.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3262.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3262.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3261.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var3262, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var3261.link();
		var3261.getTotalCost();
		var3283.computeOverall(var3261.getTotalCost());
		var3261.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3261.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		var3262.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3262.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3262.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3261.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var3262, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var3261.link();
		var3261.getTotalCost();
		var3283.computeOverall(var3261.getTotalCost());
		BeansWrapper var3264 = new DefaultObjectWrapper();
		ResourceBundleModel var3265 = new ResourceBundleModel(
				getBundle("messages"), var3264);
		var3284.setMessageBundle(var3265);
		SourceLinker var3267 = new SourceLinker(
				"http://code.repository/basepath/{path}&line={line}",
				"http://code.repository/basepath/{path}");
		var3284.setSourceLinker(new SourceLinkerModel(var3267));
		var3284.setMessageBundle(var3265);
		var3284.setSourceLinker(new SourceLinkerModel(var3267));
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