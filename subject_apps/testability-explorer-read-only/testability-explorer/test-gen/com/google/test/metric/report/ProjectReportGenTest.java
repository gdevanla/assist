package com.google.test.metric.report;


import com.google.test.metric.Testability;
import com.google.test.metric.CostModel;
import java.util.List;
import java.lang.Package;
import com.google.test.metric.report.chart.PieChartUrl;
import java.lang.String;
import java.lang.Integer;
import com.google.test.metric.report.chart.GoodnessChart;
import com.google.test.metric.report.chart.HistogramChartUrl;
import com.google.test.metric.WeightedAverage;
import junit.framework.TestCase;
import com.google.test.metric.report.GradeCategories;
import static java.util.Arrays.asList;
import static java.lang.Math.pow;

public class ProjectReportGenTest extends TestCase {
	public GradeCategories instantiateGradeCategories683() {
		return new GradeCategories(50, 100);
	}

	/**
	 * Test method for the class com.google.test.metric.report.ProjectReport
	 */
	public void testProjectReport1195() throws Exception {
		GradeCategories var3846 = instantiateGradeCategories683();
		ProjectReport var3847 = new ProjectReport("", var3846,
				new WeightedAverage());
		var3846.createOverallChart(0);
		var3846.getGoodCount(asList(1, 2, 2, 3));
		var3847.addPackage("a.b.c", 1);
		var3847.addPackage("a.b.d", 51);
		var3847.addPackage("a.b.e", 101);
		var3847.addPackage("a.b.c", 1);
		var3847.addPackage("a.b.d", 51);
		var3847.addPackage("a.b.e", 101);
	}

	public WeightedAverage instantiateWeightedAverage684() {
		return new WeightedAverage(1);
	}

	public GradeCategories instantiateGradeCategories685() {
		return new GradeCategories(50, 100);
	}

	/**
	 * Test method for the class com.google.test.metric.report.ProjectReport
	 */
	public void testProjectReport1196() throws Exception {
		WeightedAverage var3850 = instantiateWeightedAverage684();
		GradeCategories var3851 = instantiateGradeCategories685();
		ProjectReport var3852 = new ProjectReport(Testability.class
				.getPackage().getName(), var3851, var3850);
		var3850.addValue(1);
		var3850.addValue(3);
		var3850.getAverage();
		var3850.addValue(1);
		var3850.addValue(3);
		var3850.getAverage();
		var3851.createOverallChart(0);
		var3851.createHistogram(0, 0, asList(1, 2, 2, 3, 3, 3));
		var3852.addClass("a.b.C", 30);
		var3852.addClass("a.b.D", 80);
		var3852.addClass("a.b.E", 130);
		var3852.addClass("a.b.F", 13);
		var3852.addClass("a.b.G", 10);
		var3852.addClass("a.b.H", 3);
		var3852.addClass("a.b.C", 30);
		var3852.addClass("a.b.D", 80);
		var3852.addClass("a.b.E", 130);
		var3852.addClass("a.b.F", 13);
		var3852.addClass("a.b.G", 10);
		var3852.addClass("a.b.H", 3);
	}

	public WeightedAverage instantiateWeightedAverage686() {
		double var3855 = CostModel.WEIGHT_TO_EMPHASIZE_EXPENSIVE_METHODS;
		return new WeightedAverage(var3855);
	}

	public GradeCategories instantiateGradeCategories687() {
		return new GradeCategories(1, 2);
	}

	/**
	 * Test method for the class com.google.test.metric.report.ProjectReport
	 */
	public void testProjectReport1197() throws Exception {
		WeightedAverage var3856 = instantiateWeightedAverage686();
		GradeCategories var3857 = instantiateGradeCategories687();
		ProjectReport var3858 = new ProjectReport("", var3857, var3856);
		var3856.addValue(1);
		var3856.addValue(3);
		var3856.getAverage();
		var3856.addValue(1);
		var3856.addValue(3);
		var3856.getAverage();
		var3857.getExcellentCount(asList(1, 1, 2, 3));
		var3857.createOverallChart(0);
		var3858.addClass("a.b.C", 30);
		var3858.addClass("a.b.D", 80);
		var3858.addClass("a.b.E", 130);
		var3858.addClass("a.b.F", 13);
		var3858.addClass("a.b.G", 10);
		var3858.addClass("a.b.H", 3);
		var3858.addPackage("a.b.c", 1);
		var3858.addPackage("a.b.d", 51);
		var3858.addPackage("a.b.e", 101);
	}

	public WeightedAverage instantiateWeightedAverage688() {
		double var3855 = CostModel.WEIGHT_TO_EMPHASIZE_EXPENSIVE_METHODS;
		return new WeightedAverage(var3855);
	}

	public GradeCategories instantiateGradeCategories689() {
		return new GradeCategories(1, 2);
	}

	/**
	 * Test method for the class com.google.test.metric.report.ProjectReport
	 */
	public void testProjectReport1198() throws Exception {
		WeightedAverage var3860 = instantiateWeightedAverage688();
		GradeCategories var3861 = instantiateGradeCategories689();
		ProjectReport var3862 = new ProjectReport(Testability.class
				.getPackage().getName(), var3861, var3860);
		var3860.addValue(1);
		var3860.addValue(3);
		var3860.getAverage();
		var3860.addValue(1);
		var3860.addValue(3);
		var3860.getAverage();
		var3861.createDistributionChart(asList(1, 2, 2, 3, 3, 3));
		var3861.createHistogram(0, 0, asList(4, 90));
		var3862.addClass("a.b.C", 30);
		var3862.addClass("a.b.D", 80);
		var3862.addClass("a.b.E", 130);
		var3862.addClass("a.b.F", 13);
		var3862.addClass("a.b.G", 10);
		var3862.addClass("a.b.H", 3);
		var3862.addClass("a.b.C", 30);
		var3862.addClass("a.b.D", 80);
		var3862.addClass("a.b.E", 130);
		var3862.addClass("a.b.F", 13);
		var3862.addClass("a.b.G", 10);
		var3862.addClass("a.b.H", 3);
	}

	public GradeCategories instantiateGradeCategories690() {
		return new GradeCategories(1, 2);
	}

	/**
	 * Test method for the class com.google.test.metric.report.ProjectReport
	 */
	public void testProjectReport1199() throws Exception {
		GradeCategories var3863 = instantiateGradeCategories690();
		ProjectReport var3864 = new ProjectReport("", var3863,
				new WeightedAverage());
		var3863.getGoodCount(asList(1, 2, 2, 3));
		var3863.createHistogram(0, 0, asList(4, 90));
		var3864.addClass("a.b.C", 30);
		var3864.addClass("a.b.D", 80);
		var3864.addClass("a.b.E", 130);
		var3864.addClass("a.b.F", 13);
		var3864.addClass("a.b.G", 10);
		var3864.addClass("a.b.H", 3);
		var3864.addClass("a.b.C", 30);
		var3864.addClass("a.b.D", 80);
		var3864.addClass("a.b.E", 130);
		var3864.addClass("a.b.F", 13);
		var3864.addClass("a.b.G", 10);
		var3864.addClass("a.b.H", 3);
	}
}