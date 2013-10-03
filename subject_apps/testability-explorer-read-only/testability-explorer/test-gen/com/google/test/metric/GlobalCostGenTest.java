package com.google.test.metric;


import com.google.test.metric.Variable;
import com.google.test.metric.Type;
import com.google.test.metric.Cost;
import com.google.test.metric.SourceLocation;
import com.google.test.metric.JavaType;
import junit.framework.TestCase;
import com.google.test.metric.FieldInfo;
import static java.util.Collections.EMPTY_LIST;
import static com.google.test.metric.Reason.NON_OVERRIDABLE_METHOD_CALL;
import static com.google.test.metric.report.DrillDownReportGenerator.NEW_LINE;
import static java.lang.Integer.MAX_VALUE;
import static com.google.test.metric.report.issues.IssueSubType.COMPLEXITY;
import static com.google.test.metric.report.issues.IssueSubType.STATIC_METHOD;
import static com.google.test.metric.report.issues.IssueType.COLLABORATOR;
import static com.google.test.metric.report.issues.IssueType.DIRECT_COST;
import static com.google.test.metric.report.FreemarkerReportGenerator.HTML_REPORT_TEMPLATE;
import static java.text.MessageFormat.format;
import static java.util.ResourceBundle.getBundle;
import static com.google.test.metric.Reason.IMPLICIT_STATIC_INIT;
import static java.util.Arrays.asList;

public class GlobalCostGenTest extends TestCase {
	public SourceLocation instantiateSourceLocation609() {
		return new SourceLocation("com/google/FooClass.java", 1);
	}

	public Cost instantiateCost610() {
		return new Cost();
	}

	/**
	 * Test method for the class com.google.test.metric.GlobalCost
	 */
	public void testGlobalCost1065() throws Exception {
		Cost var3527 = instantiateCost610();
		SourceLocation var3528 = instantiateSourceLocation609();
		GlobalCost var3529 = new GlobalCost(var3528, null, var3527);
		var3527.add(new Cost());
		var3527.add(new Cost());
	}

	public FieldInfo instantiateFieldInfo611() {
		return new FieldInfo(null, "field", null, false, false, false);
	}

	public SourceLocation instantiateSourceLocation612() {
		return new SourceLocation("foo", 1);
	}

	/**
	 * Test method for the class com.google.test.metric.GlobalCost
	 */
	public void testGlobalCost1066() throws Exception {
		SourceLocation var3531 = instantiateSourceLocation612();
		FieldInfo var3532 = instantiateFieldInfo611();
		GlobalCost var3533 = new GlobalCost(var3531, null, Cost.global(1));
	}

	public Variable instantiateVariable613() {
		return new Variable("a", JavaType.BOOLEAN, false, false);
	}

	public SourceLocation instantiateSourceLocation614() {
		return new SourceLocation("foo", 1);
	}

	public Cost instantiateCost615() {
		return new Cost();
	}

	/**
	 * Test method for the class com.google.test.metric.GlobalCost
	 */
	public void testGlobalCost1067() throws Exception {
		Variable var3534 = instantiateVariable613();
		Cost var3535 = instantiateCost615();
		SourceLocation var3536 = instantiateSourceLocation614();
		GlobalCost var3537 = new GlobalCost(var3536, null, var3535);
		var3535.add(new Cost());
		var3535.add(new Cost());
	}

	public FieldInfo instantiateFieldInfo616() {
		return new FieldInfo(null, "field", null, false, false, false);
	}

	public SourceLocation instantiateSourceLocation617() {
		return new SourceLocation("com/google/FooClass.java", 1);
	}

	/**
	 * Test method for the class com.google.test.metric.GlobalCost
	 */
	public void testGlobalCost1068() throws Exception {
		SourceLocation var3538 = instantiateSourceLocation617();
		FieldInfo var3539 = instantiateFieldInfo616();
		GlobalCost var3540 = new GlobalCost(var3538, null, Cost.global(1));
	}

	public FieldInfo instantiateFieldInfo618() {
		Type var3541 = JavaType
				.fromJava("com.google.test.metric.example.MutableGlobalState."
						+ "FinalGlobalExample$Gadget");
		return new FieldInfo(null, "finalInstance", var3541, false, false,
				false);
	}

	public SourceLocation instantiateSourceLocation619() {
		return new SourceLocation("foo", 1);
	}

	/**
	 * Test method for the class com.google.test.metric.GlobalCost
	 */
	public void testGlobalCost1069() throws Exception {
		SourceLocation var3542 = instantiateSourceLocation619();
		FieldInfo var3543 = instantiateFieldInfo618();
		GlobalCost var3544 = new GlobalCost(var3542, null, Cost.global(1));
	}
}