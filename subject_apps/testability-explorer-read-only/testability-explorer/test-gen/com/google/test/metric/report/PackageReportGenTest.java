package com.google.test.metric.report;


import com.google.test.metric.Testability;
import com.google.test.metric.CostModel;
import java.util.List;
import java.lang.Package;
import com.google.test.metric.report.chart.PieChartUrl;
import com.google.test.metric.report.chart.GoodnessChart;
import java.lang.Integer;
import java.lang.String;
import com.google.test.metric.report.chart.HistogramChartUrl;
import com.google.test.metric.WeightedAverage;
import junit.framework.TestCase;
import com.google.test.metric.report.GradeCategories;
import static java.util.Arrays.asList;
import static java.lang.Math.pow;

public class PackageReportGenTest extends TestCase {
	public GradeCategories instantiateGradeCategories696() {
		return new GradeCategories(50, 100);
	}

	/**
	 * Test method for the class com.google.test.metric.report.PackageReport
	 */
	public void testPackageReport1240() throws Exception {
		GradeCategories var3901 = instantiateGradeCategories696();
		PackageReport var3902 = new PackageReport(Testability.class
				.getPackage().getName(), var3901, new WeightedAverage());
		var3901.createHistogram(0, 0, asList(1, 2, 2, 3, 3, 3));
		var3901.getNeedsWorkCount(asList(1, 2, 3, 3));
		var3902.addClass("a.b.C", 30);
		var3902.addClass("a.b.D", 80);
		var3902.addClass("a.b.E", 130);
		var3902.addClass("a.b.C", 30);
		var3902.addClass("a.b.D", 80);
		var3902.addClass("a.b.E", 130);
	}

	public WeightedAverage instantiateWeightedAverage697() {
		double var3905 = CostModel.WEIGHT_TO_EMPHASIZE_EXPENSIVE_METHODS;
		return new WeightedAverage(var3905);
	}

	public GradeCategories instantiateGradeCategories698() {
		return new GradeCategories(50, 100);
	}

	/**
	 * Test method for the class com.google.test.metric.report.PackageReport
	 */
	public void testPackageReport1241() throws Exception {
		WeightedAverage var3906 = instantiateWeightedAverage697();
		GradeCategories var3907 = instantiateGradeCategories698();
		PackageReport var3908 = new PackageReport(Testability.class
				.getPackage().getName(), var3907, var3906);
		var3906.addValue(1);
		var3906.addValue(3);
		var3906.getAverage();
		var3906.addValue(1);
		var3906.addValue(3);
		var3906.getAverage();
		var3907.createHistogram(0, 0, asList(4, 90));
		var3907.createOverallChart(0);
		var3908.addClass("a.b.C", 30);
		var3908.addClass("a.b.D", 80);
		var3908.addClass("a.b.E", 130);
		var3908.addClass("a.b.C", 30);
		var3908.addClass("a.b.D", 80);
		var3908.addClass("a.b.E", 130);
	}

	public WeightedAverage instantiateWeightedAverage699() {
		return new WeightedAverage(1);
	}

	public GradeCategories instantiateGradeCategories700() {
		return new GradeCategories(1, 2);
	}

	/**
	 * Test method for the class com.google.test.metric.report.PackageReport
	 */
	public void testPackageReport1242() throws Exception {
		WeightedAverage var3910 = instantiateWeightedAverage699();
		GradeCategories var3911 = instantiateGradeCategories700();
		PackageReport var3912 = new PackageReport(Testability.class
				.getPackage().getName(), var3911, var3910);
		var3910.addValue(1);
		var3910.addValue(3);
		var3910.getAverage();
		var3910.addValue(1);
		var3910.addValue(3);
		var3910.getAverage();
		var3911.createDistributionChart(asList(1, 2, 2, 3, 3, 3));
		var3911.createHistogram(0, 0, asList(4, 90));
		var3912.addClass("a.b.C", 30);
		var3912.addClass("a.b.D", 80);
		var3912.addClass("a.b.E", 130);
		var3912.addClass("a.b.C", 30);
		var3912.addClass("a.b.D", 80);
		var3912.addClass("a.b.E", 130);
	}

	public GradeCategories instantiateGradeCategories701() {
		return new GradeCategories(1, 2);
	}

	/**
	 * Test method for the class com.google.test.metric.report.PackageReport
	 */
	public void testPackageReport1243() throws Exception {
		GradeCategories var3914 = instantiateGradeCategories701();
		PackageReport var3915 = new PackageReport(Testability.class
				.getPackage().getName(), var3914, new WeightedAverage());
		var3914.createDistributionChart(asList(1, 2, 2, 3, 3, 3));
		var3914.createOverallChart(0);
		var3915.addClass("a.b.C", 30);
		var3915.addClass("a.b.D", 80);
		var3915.addClass("a.b.E", 130);
		var3915.addClass("a.b.C", 30);
		var3915.addClass("a.b.D", 80);
		var3915.addClass("a.b.E", 130);
	}

	public GradeCategories instantiateGradeCategories702() {
		return new GradeCategories(1, 2);
	}

	/**
	 * Test method for the class com.google.test.metric.report.PackageReport
	 */
	public void testPackageReport1244() throws Exception {
		GradeCategories var3916 = instantiateGradeCategories702();
		PackageReport var3917 = new PackageReport(Testability.class
				.getPackage().getName(), var3916, new WeightedAverage());
		var3916.getGoodCount(asList(1, 2, 2, 3));
		var3916.createOverallChart(0);
		var3917.addClass("a.b.C", 30);
		var3917.addClass("a.b.D", 80);
		var3917.addClass("a.b.E", 130);
		var3917.addClass("a.b.C", 30);
		var3917.addClass("a.b.D", 80);
		var3917.addClass("a.b.E", 130);
	}
}