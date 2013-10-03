package com.google.test.metric.report;


import com.google.test.metric.Reason;
import com.google.test.metric.CyclomaticCost;
import com.google.test.metric.CostModel;
import com.google.test.metric.testing.MetricComputerBuilder;
import com.google.test.metric.ClassInfo;
import com.google.test.metric.JavaClassRepository;
import com.google.test.metric.MetricComputer;
import com.google.test.metric.Cost;
import java.lang.String;
import junit.framework.TestCase;
import com.google.test.metric.ViolationCost;
import org.apache.xml.serialize.OutputFormat;
import java.io.StringWriter;
import com.google.test.metric.MethodCost;
import com.google.test.metric.MethodInvocationCost;
import com.google.test.metric.SourceLocation;
import org.apache.xml.serialize.XMLSerializer;
import com.google.test.metric.ClassRepository;
import com.google.test.metric.GlobalCost;
import com.google.test.metric.ClassCost;
import static com.google.test.metric.Reason.NON_OVERRIDABLE_METHOD_CALL;
import static com.google.test.metric.Reason.IMPLICIT_STATIC_INIT;
import static java.util.Arrays.asList;

public class XMLReportGeneratorGenTest extends TestCase {
	public CostModel instantiateCostModel505() {
		return new CostModel();
	}

	/**
	 * Test method for the class com.google.test.metric.report.XMLReportGenerator
	 */
	public void testXMLReportGenerator975() throws Exception {
		CostModel var3078 = instantiateCostModel505();
		XMLSerializer var3079 = new XMLSerializer();
		StringWriter var3080 = new StringWriter();
		var3079.setOutputCharStream(var3080);
		var3079.startDocument();
		OutputFormat var3081 = new OutputFormat();
		var3081.setIndenting(true);
		var3079.setOutputFormat(var3081);
		XMLReportGenerator var3082 = new XMLReportGenerator(var3079, var3078,
				0, 0, 0);
		ClassRepository var3083 = new JavaClassRepository();
		MetricComputer var3084 = new MetricComputerBuilder()
				.withClassRepository(var3083).build();
		ClassInfo var3085 = var3083.getClass(Example.class.getCanonicalName());
		ClassCost var3086 = var3084.compute(var3085);
		var3078.computeClass(var3086);
		var3078.computeClass(var3086);
		MethodCost var3088 = new MethodCost("", "methodName", 1, false, false,
				false);
		var3088.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3088.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		ViolationCost var3089 = new MethodInvocationCost(new SourceLocation(
				"source.file", 123), var3088, NON_OVERRIDABLE_METHOD_CALL, Cost
				.cyclomatic(2).add(Cost.global(3)));
		var3082.writeCost(var3089);
		MethodCost var3091 = new MethodCost("", "methodName", 1, false, false,
				false);
		var3091.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3091.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		ViolationCost var3092 = new MethodInvocationCost(new SourceLocation(
				"source.file", 123), var3091, IMPLICIT_STATIC_INIT, Cost
				.cyclomatic(2).add(Cost.global(3)));
		var3082.writeCost(var3092);
	}

	public CostModel instantiateCostModel506() {
		return new CostModel(1, 1, 1);
	}

	/**
	 * Test method for the class com.google.test.metric.report.XMLReportGenerator
	 */
	public void testXMLReportGenerator976() throws Exception {
		CostModel var3094 = instantiateCostModel506();
		XMLSerializer var3079 = new XMLSerializer();
		StringWriter var3080 = new StringWriter();
		var3079.setOutputCharStream(var3080);
		var3079.startDocument();
		OutputFormat var3081 = new OutputFormat();
		var3081.setIndenting(true);
		var3079.setOutputFormat(var3081);
		XMLReportGenerator var3095 = new XMLReportGenerator(var3079, var3094,
				0, 0, 0);
		MethodCost var3096 = new MethodCost("", "a", 0, false, false, false);
		var3096.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3096.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		MethodCost var3097 = new MethodCost("", "b", 0, false, false, false);
		var3097.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3097.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3097.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3096.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var3097, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var3096.link();
		var3096.getTotalCost();
		var3094.computeOverall(var3096.getTotalCost());
		var3096.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3096.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		var3097.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3097.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3097.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3096.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var3097, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var3096.link();
		var3096.getTotalCost();
		var3094.computeOverall(var3096.getTotalCost());
		MethodCost var3088 = new MethodCost("", "methodName", 1, false, false,
				false);
		var3088.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3088.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		ViolationCost var3089 = new MethodInvocationCost(new SourceLocation(
				"source.file", 123), var3088, NON_OVERRIDABLE_METHOD_CALL, Cost
				.cyclomatic(2).add(Cost.global(3)));
		var3095.writeCost(var3089);
		MethodCost var3091 = new MethodCost("", "methodName", 1, false, false,
				false);
		var3091.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3091.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		ViolationCost var3092 = new MethodInvocationCost(new SourceLocation(
				"source.file", 123), var3091, IMPLICIT_STATIC_INIT, Cost
				.cyclomatic(2).add(Cost.global(3)));
		var3095.writeCost(var3092);
	}

	public CostModel instantiateCostModel507() {
		return new CostModel(2, 10, 1);
	}

	/**
	 * Test method for the class com.google.test.metric.report.XMLReportGenerator
	 */
	public void testXMLReportGenerator977() throws Exception {
		CostModel var3099 = instantiateCostModel507();
		XMLSerializer var3079 = new XMLSerializer();
		StringWriter var3080 = new StringWriter();
		var3079.setOutputCharStream(var3080);
		var3079.startDocument();
		OutputFormat var3081 = new OutputFormat();
		var3081.setIndenting(true);
		var3079.setOutputFormat(var3081);
		XMLReportGenerator var3100 = new XMLReportGenerator(var3079, var3099,
				0, 0, 0);
		MethodCost var3096 = new MethodCost("", "a", 0, false, false, false);
		var3096.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3096.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		MethodCost var3097 = new MethodCost("", "b", 0, false, false, false);
		var3097.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3097.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3097.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3096.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var3097, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var3096.link();
		var3096.getTotalCost();
		var3099.computeOverall(var3096.getTotalCost());
		var3096.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3096.addCostSource(new GlobalCost(new SourceLocation(null, 0), null,
				Cost.global(1)));
		var3097.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3097.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3097.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3096.addCostSource(new MethodInvocationCost(new SourceLocation(null,
				0), var3097, IMPLICIT_STATIC_INIT, Cost.cyclomatic(3)));
		var3096.link();
		var3096.getTotalCost();
		var3099.computeOverall(var3096.getTotalCost());
		MethodCost var3088 = new MethodCost("", "methodName", 1, false, false,
				false);
		var3088.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3088.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		ViolationCost var3089 = new MethodInvocationCost(new SourceLocation(
				"source.file", 123), var3088, NON_OVERRIDABLE_METHOD_CALL, Cost
				.cyclomatic(2).add(Cost.global(3)));
		var3100.writeCost(var3089);
		MethodCost var3091 = new MethodCost("", "methodName", 1, false, false,
				false);
		var3091.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3091.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		ViolationCost var3092 = new MethodInvocationCost(new SourceLocation(
				"source.file", 123), var3091, IMPLICIT_STATIC_INIT, Cost
				.cyclomatic(2).add(Cost.global(3)));
		var3100.writeCost(var3092);
	}

	public CostModel instantiateCostModel508() {
		return new CostModel();
	}

	/**
	 * Test method for the class com.google.test.metric.report.XMLReportGenerator
	 */
	public void testXMLReportGenerator978() throws Exception {
		CostModel var3101 = instantiateCostModel508();
		XMLSerializer var3079 = new XMLSerializer();
		StringWriter var3080 = new StringWriter();
		var3079.setOutputCharStream(var3080);
		var3079.startDocument();
		OutputFormat var3081 = new OutputFormat();
		var3081.setIndenting(true);
		var3079.setOutputFormat(var3081);
		XMLReportGenerator var3102 = new XMLReportGenerator(var3079, var3101,
				0, 0, 0);
		ClassRepository var3083 = new JavaClassRepository();
		MetricComputer var3084 = new MetricComputerBuilder()
				.withClassRepository(var3083).build();
		ClassInfo var3085 = var3083.getClass(Example.class.getCanonicalName());
		ClassCost var3086 = var3084.compute(var3085);
		var3101.computeClass(var3086);
		var3101.computeClass(var3086);
		MethodCost var3088 = new MethodCost("", "methodName", 1, false, false,
				false);
		var3088.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3088.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		ViolationCost var3089 = new MethodInvocationCost(new SourceLocation(
				"source.file", 123), var3088, NON_OVERRIDABLE_METHOD_CALL, Cost
				.cyclomatic(2).add(Cost.global(3)));
		var3102.writeCost(var3089);
		MethodCost var3091 = new MethodCost("", "methodName", 1, false, false,
				false);
		var3091.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3091.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		ViolationCost var3092 = new MethodInvocationCost(new SourceLocation(
				"source.file", 123), var3091, IMPLICIT_STATIC_INIT, Cost
				.cyclomatic(2).add(Cost.global(3)));
		var3102.writeCost(var3092);
	}

	public CostModel instantiateCostModel509() {
		return new CostModel();
	}

	/**
	 * Test method for the class com.google.test.metric.report.XMLReportGenerator
	 */
	public void testXMLReportGenerator979() throws Exception {
		CostModel var3103 = instantiateCostModel509();
		XMLSerializer var3079 = new XMLSerializer();
		StringWriter var3080 = new StringWriter();
		var3079.setOutputCharStream(var3080);
		var3079.startDocument();
		OutputFormat var3081 = new OutputFormat();
		var3081.setIndenting(true);
		var3079.setOutputFormat(var3081);
		XMLReportGenerator var3104 = new XMLReportGenerator(var3079, var3103,
				0, 0, 0);
		ClassRepository var3083 = new JavaClassRepository();
		MetricComputer var3084 = new MetricComputerBuilder()
				.withClassRepository(var3083).build();
		ClassInfo var3085 = var3083.getClass(Example.class.getCanonicalName());
		ClassCost var3086 = var3084.compute(var3085);
		var3103.computeClass(var3086);
		var3103.computeClass(var3086);
		MethodCost var3088 = new MethodCost("", "methodName", 1, false, false,
				false);
		var3088.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3088.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		ViolationCost var3089 = new MethodInvocationCost(new SourceLocation(
				"source.file", 123), var3088, NON_OVERRIDABLE_METHOD_CALL, Cost
				.cyclomatic(2).add(Cost.global(3)));
		var3104.writeCost(var3089);
		var3088.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3088.addCostSource(new CyclomaticCost(new SourceLocation(null, 0),
				Cost.cyclomatic(1)));
		var3104.writeCost(var3089);
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