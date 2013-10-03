package com.google.test.metric.report;


import com.google.test.metric.Testability;
import com.google.test.metric.report.ProjectReport;
import java.lang.Package;
import java.lang.String;
import com.google.test.metric.WeightedAverage;
import junit.framework.TestCase;
import com.google.test.metric.report.GradeCategories;

public class ProjectSummaryReportGenTest extends TestCase {
	public ProjectReport instantiateProjectReport678() {
		GradeCategories var3833 = new GradeCategories(50, 100);
		return new ProjectReport(Testability.class.getPackage().getName(),
				var3833, new WeightedAverage());
	}

	/**
	 * Test method for the class com.google.test.metric.report.ProjectSummaryReport
	 */
	public void testProjectSummaryReport1190() throws Exception {
		ProjectReport var3834 = instantiateProjectReport678();
		GradeCategories var3833 = new GradeCategories(50, 100);
		var3835.addClass("a.b.C", 30);
		var3835.addClass("a.b.D", 80);
		var3835.addClass("a.b.E", 130);
		var3835.addClass("a.b.F", 13);
		var3835.addClass("a.b.G", 10);
		var3835.addClass("a.b.H", 3);
		var3836.addPackage("a.b.c", 1);
		var3836.addPackage("a.b.d", 51);
		var3836.addPackage("a.b.e", 101);
		ProjectSummaryReport var3837 = new ProjectSummaryReport(var3834,
				var3834);
		var3834.addPackage("a.b.c", 1);
		var3834.addPackage("a.b.d", 51);
		var3834.addPackage("a.b.e", 101);
		var3834.addPackage("a.b.c", 1);
		var3834.addPackage("a.b.d", 51);
		var3834.addPackage("a.b.e", 101);
	}

	public ProjectReport instantiateProjectReport679() {
		GradeCategories var3833 = new GradeCategories(50, 100);
		return new ProjectReport(Testability.class.getPackage().getName(),
				var3833, new WeightedAverage());
	}

	/**
	 * Test method for the class com.google.test.metric.report.ProjectSummaryReport
	 */
	public void testProjectSummaryReport1191() throws Exception {
		ProjectReport var3838 = instantiateProjectReport679();
		GradeCategories var3833 = new GradeCategories(50, 100);
		var3835.addClass("a.b.C", 30);
		var3835.addClass("a.b.D", 80);
		var3835.addClass("a.b.E", 130);
		var3835.addClass("a.b.F", 13);
		var3835.addClass("a.b.G", 10);
		var3835.addClass("a.b.H", 3);
		var3836.addPackage("a.b.c", 1);
		var3836.addPackage("a.b.d", 51);
		var3836.addPackage("a.b.e", 101);
		ProjectSummaryReport var3839 = new ProjectSummaryReport(var3838,
				var3838);
		var3838.addPackage("a.b.c", 1);
		var3838.addPackage("a.b.d", 51);
		var3838.addPackage("a.b.e", 101);
		var3838.addPackage("a.b.c", 1);
		var3838.addPackage("a.b.d", 51);
		var3838.addPackage("a.b.e", 101);
	}

	public ProjectReport instantiateProjectReport680() {
		GradeCategories var3833 = new GradeCategories(50, 100);
		return new ProjectReport(Testability.class.getPackage().getName(),
				var3833, new WeightedAverage());
	}

	/**
	 * Test method for the class com.google.test.metric.report.ProjectSummaryReport
	 */
	public void testProjectSummaryReport1192() throws Exception {
		ProjectReport var3840 = instantiateProjectReport680();
		GradeCategories var3833 = new GradeCategories(50, 100);
		var3835.addClass("a.b.C", 30);
		var3835.addClass("a.b.D", 80);
		var3835.addClass("a.b.E", 130);
		var3835.addClass("a.b.F", 13);
		var3835.addClass("a.b.G", 10);
		var3835.addClass("a.b.H", 3);
		var3836.addPackage("a.b.c", 1);
		var3836.addPackage("a.b.d", 51);
		var3836.addPackage("a.b.e", 101);
		ProjectSummaryReport var3841 = new ProjectSummaryReport(var3840,
				var3840);
		var3840.addClass("a.b.C", 30);
		var3840.addClass("a.b.D", 80);
		var3840.addClass("a.b.E", 130);
		var3840.addClass("a.b.F", 13);
		var3840.addClass("a.b.G", 10);
		var3840.addClass("a.b.H", 3);
		var3840.addPackage("a.b.c", 1);
		var3840.addPackage("a.b.d", 51);
		var3840.addPackage("a.b.e", 101);
	}

	public ProjectReport instantiateProjectReport681() {
		GradeCategories var3833 = new GradeCategories(50, 100);
		return new ProjectReport("", var3833, new WeightedAverage());
	}

	/**
	 * Test method for the class com.google.test.metric.report.ProjectSummaryReport
	 */
	public void testProjectSummaryReport1193() throws Exception {
		ProjectReport var3842 = instantiateProjectReport681();
		GradeCategories var3833 = new GradeCategories(50, 100);
		var3835.addClass("a.b.C", 30);
		var3835.addClass("a.b.D", 80);
		var3835.addClass("a.b.E", 130);
		var3835.addClass("a.b.F", 13);
		var3835.addClass("a.b.G", 10);
		var3835.addClass("a.b.H", 3);
		var3836.addPackage("a.b.c", 1);
		var3836.addPackage("a.b.d", 51);
		var3836.addPackage("a.b.e", 101);
		ProjectSummaryReport var3843 = new ProjectSummaryReport(var3842,
				var3842);
		var3842.addClass("a.b.C", 30);
		var3842.addClass("a.b.D", 80);
		var3842.addClass("a.b.E", 130);
		var3842.addClass("a.b.F", 13);
		var3842.addClass("a.b.G", 10);
		var3842.addClass("a.b.H", 3);
		var3842.addClass("a.b.C", 30);
		var3842.addClass("a.b.D", 80);
		var3842.addClass("a.b.E", 130);
		var3842.addClass("a.b.F", 13);
		var3842.addClass("a.b.G", 10);
		var3842.addClass("a.b.H", 3);
	}

	public ProjectReport instantiateProjectReport682() {
		GradeCategories var3833 = new GradeCategories(50, 100);
		return new ProjectReport(Testability.class.getPackage().getName(),
				var3833, new WeightedAverage());
	}

	/**
	 * Test method for the class com.google.test.metric.report.ProjectSummaryReport
	 */
	public void testProjectSummaryReport1194() throws Exception {
		ProjectReport var3844 = instantiateProjectReport682();
		GradeCategories var3833 = new GradeCategories(50, 100);
		var3835.addClass("a.b.C", 30);
		var3835.addClass("a.b.D", 80);
		var3835.addClass("a.b.E", 130);
		var3835.addClass("a.b.F", 13);
		var3835.addClass("a.b.G", 10);
		var3835.addClass("a.b.H", 3);
		var3836.addPackage("a.b.c", 1);
		var3836.addPackage("a.b.d", 51);
		var3836.addPackage("a.b.e", 101);
		ProjectSummaryReport var3845 = new ProjectSummaryReport(var3844,
				var3844);
		var3844.addClass("a.b.C", 30);
		var3844.addClass("a.b.D", 80);
		var3844.addClass("a.b.E", 130);
		var3844.addClass("a.b.F", 13);
		var3844.addClass("a.b.G", 10);
		var3844.addClass("a.b.H", 3);
		var3844.addPackage("a.b.c", 1);
		var3844.addPackage("a.b.d", 51);
		var3844.addPackage("a.b.e", 101);
	}
}