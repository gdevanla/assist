package com.google.test.metric.report;


import com.google.test.metric.report.Diff;
import freemarker.template.Configuration;
import java.util.List;
import java.io.Writer;
import com.google.test.metric.report.ClassPathTemplateLoader;
import java.lang.String;
import com.google.test.metric.ReportGeneratorProvider;
import java.util.Arrays;
import junit.framework.TestCase;
import com.google.test.metric.report.Diff.ClassDiff;
import java.io.StringWriter;
import com.google.test.metric.report.Diff.MethodDiff;

public class DiffReportGenTest extends TestCase {
	public Diff instantiateDiff673() {
		Diff.MethodDiff var3802 = new Diff.MethodDiff("doThing", 123, null);
		Diff.ClassDiff var3803 = new Diff.ClassDiff("Foo", 1, 1,
				Arrays.asList(var3802));
		return new Diff(Arrays.asList(var3803));
	}

	/**
	 * Test method for the class com.google.test.metric.report.DiffReport
	 */
	public void testDiffReport1180() throws Exception {
		Diff var3804 = instantiateDiff673();
		Configuration var3805;
		var3805 = new Configuration();
		var3805.setTemplateLoader(new ClassPathTemplateLoader(
				ReportGeneratorProvider.PREFIX));
		DiffReport var3806 = new DiffReport(var3804, var3805);
		Writer var3807;
		var3807 = new StringWriter();
		var3806.writeHtml(var3807);
		var3807 = new StringWriter();
		var3806.writeHtml(var3807);
	}

	public Diff instantiateDiff674() {
		return new Diff(Arrays.asList(new Diff.ClassDiff("Foo", null, 456)));
	}

	/**
	 * Test method for the class com.google.test.metric.report.DiffReport
	 */
	public void testDiffReport1181() throws Exception {
		Diff var3810 = instantiateDiff674();
		Diff.MethodDiff var3802 = new Diff.MethodDiff("doThing", 123, null);
		Diff.ClassDiff var3803 = new Diff.ClassDiff("Foo", 1, 1,
				Arrays.asList(var3802));
		Configuration var3805;
		var3805 = new Configuration();
		var3805.setTemplateLoader(new ClassPathTemplateLoader(
				ReportGeneratorProvider.PREFIX));
		DiffReport var3811 = new DiffReport(var3810, var3805);
		Writer var3807;
		var3807 = new StringWriter();
		var3811.writeHtml(var3807);
		var3807 = new StringWriter();
		var3811.writeHtml(var3807);
	}

	public Diff instantiateDiff675() {
		Diff.MethodDiff var3813 = new Diff.MethodDiff("doThing", null, 123);
		Diff.ClassDiff var3814 = new Diff.ClassDiff("Foo", 1, 1,
				Arrays.asList(var3813));
		return new Diff(Arrays.asList(var3814));
	}

	/**
	 * Test method for the class com.google.test.metric.report.DiffReport
	 */
	public void testDiffReport1182() throws Exception {
		Diff var3815 = instantiateDiff675();
		Configuration var3805;
		var3805 = new Configuration();
		var3805.setTemplateLoader(new ClassPathTemplateLoader(
				ReportGeneratorProvider.PREFIX));
		DiffReport var3816 = new DiffReport(var3815, var3805);
		Writer var3807;
		var3807 = new StringWriter();
		var3816.writeHtml(var3807);
		var3807 = new StringWriter();
		var3816.writeHtml(var3807);
	}

	public Diff instantiateDiff676() {
		Diff.MethodDiff var3813 = new Diff.MethodDiff("doThing", null, 123);
		Diff.ClassDiff var3814 = new Diff.ClassDiff("Foo", 1, 1,
				Arrays.asList(var3813));
		return new Diff(Arrays.asList(var3814));
	}

	/**
	 * Test method for the class com.google.test.metric.report.DiffReport
	 */
	public void testDiffReport1183() throws Exception {
		Diff var3817 = instantiateDiff676();
		Configuration var3805;
		var3805 = new Configuration();
		var3805.setTemplateLoader(new ClassPathTemplateLoader(
				ReportGeneratorProvider.PREFIX));
		DiffReport var3818 = new DiffReport(var3817, var3805);
		Writer var3807;
		var3807 = new StringWriter();
		var3818.writeHtml(var3807);
		var3807 = new StringWriter();
		var3818.writeHtml(var3807);
	}

	public Diff instantiateDiff677() {
		Diff.MethodDiff var3821 = new Diff.MethodDiff("doThing", 456, 123);
		Diff.ClassDiff var3822 = new Diff.ClassDiff("Foo", 1, 1,
				Arrays.asList(var3821));
		return new Diff(Arrays.asList(var3822));
	}

	/**
	 * Test method for the class com.google.test.metric.report.DiffReport
	 */
	public void testDiffReport1184() throws Exception {
		Diff var3823 = instantiateDiff677();
		Configuration var3805;
		var3805 = new Configuration();
		var3805.setTemplateLoader(new ClassPathTemplateLoader(
				ReportGeneratorProvider.PREFIX));
		DiffReport var3824 = new DiffReport(var3823, var3805);
		Writer var3807;
		var3807 = new StringWriter();
		var3824.writeHtml(var3807);
		var3807 = new StringWriter();
		var3824.writeHtml(var3807);
	}
}