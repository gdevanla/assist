package com.google.test.metric.report;


import java.util.List;
import com.google.test.metric.report.chart.PieChartUrl;
import java.lang.Integer;
import com.google.test.metric.report.chart.HistogramChartUrl;
import junit.framework.TestCase;
import static java.util.Arrays.asList;

public class GradeCategoriesGenTest extends TestCase {
	/**
	 * Test method for the class com.google.test.metric.report.GradeCategories
	 */
	public void testGradeCategories990() throws Exception {
		GradeCategories var3177 = new GradeCategories(50, 100);
		var3177.createHistogram(0, 0, asList(1, 2, 2, 3, 3, 3));
		var3177.createHistogram(0, 0, asList(4, 90));
	}

	/**
	 * Test method for the class com.google.test.metric.report.GradeCategories
	 */
	public void testGradeCategories991() throws Exception {
		GradeCategories var3177 = new GradeCategories(50, 100);
		var3177.createDistributionChart(asList(1, 2, 2, 3, 3, 3));
		var3177.createHistogram(0, 0, asList(1, 2, 2, 3, 3, 3));
	}

	/**
	 * Test method for the class com.google.test.metric.report.GradeCategories
	 */
	public void testGradeCategories992() throws Exception {
		GradeCategories var3179 = new GradeCategories(1, 2);
		var3179.getGoodCount(asList(1, 2, 2, 3));
		var3179.getGoodCount(asList(1, 2, 2, 3));
	}

	/**
	 * Test method for the class com.google.test.metric.report.GradeCategories
	 */
	public void testGradeCategories993() throws Exception {
		GradeCategories var3177 = new GradeCategories(50, 100);
		var3177.getNeedsWorkCount(asList(1, 2, 3, 3));
		var3177.getGoodCount(asList(1, 2, 2, 3));
	}

	/**
	 * Test method for the class com.google.test.metric.report.GradeCategories
	 */
	public void testGradeCategories994() throws Exception {
		GradeCategories var3179 = new GradeCategories(1, 2);
		var3179.createHistogram(0, 0, asList(4, 90));
		var3179.createHistogram(0, 0, asList(4, 90));
	}
}