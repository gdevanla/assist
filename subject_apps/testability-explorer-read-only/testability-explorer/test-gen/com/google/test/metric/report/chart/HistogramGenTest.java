package com.google.test.metric.report.chart;


import com.google.test.metric.report.chart.Histogram.Linear;
import com.google.test.metric.report.chart.Histogram.Logarithmic;
import junit.framework.TestCase;
import static java.lang.Integer.MAX_VALUE;

public class HistogramGenTest extends TestCase {
	/**
	 * Test method for the class com.google.test.metric.report.chart.Histogram
	 */
	public void testHistogram1205() throws Exception {
		Histogram var3866 = new Histogram(1, 1, 3, new Logarithmic());
		var3866.value(1);
		var3866.value(1);
		var3866.value(1);
		var3866.value(2);
		var3866.value(2);
		var3866.value(3);
		var3866.getBins();
		var3866.getScaledBinRange(1, 2, 30);
		var3866.getScaledBinRange(2, 3, 30);
		var3866.getScaledBinRange(3, MAX_VALUE, 30);
		var3866.value(1);
		var3866.value(2);
		var3866.value(3);
		var3866.getBins();
		var3866.getScaledBinRange(1, 2, 30);
		var3866.getScaledBinRange(2, 3, 30);
		var3866.getScaledBinRange(3, MAX_VALUE, 30);
	}

	/**
	 * Test method for the class com.google.test.metric.report.chart.Histogram
	 */
	public void testHistogram1206() throws Exception {
		Histogram var3869 = new Histogram(1, 1, 3, new Linear());
		var3869.value(1);
		var3869.value(2);
		var3869.value(3);
		var3869.getBins();
		var3869.getScaledBinRange(1, 2, 30);
		var3869.getScaledBinRange(2, 3, 30);
		var3869.getScaledBinRange(3, MAX_VALUE, 30);
		var3869.value(1);
		var3869.value(2);
		var3869.value(3);
		var3869.getBins();
		var3869.getScaledBinRange(1, 2, 30);
		var3869.getScaledBinRange(2, 3, 30);
		var3869.getScaledBinRange(3, MAX_VALUE, 30);
	}

	/**
	 * Test method for the class com.google.test.metric.report.chart.Histogram
	 */
	public void testHistogram1207() throws Exception {
		Histogram var3866 = new Histogram(1, 1, 3, new Logarithmic());
		var3866.value(1);
		var3866.value(1);
		var3866.value(1);
		var3866.value(2);
		var3866.value(2);
		var3866.value(3);
		var3866.getBins();
		var3866.getScaledBinRange(1, 2, 30);
		var3866.getScaledBinRange(2, 3, 30);
		var3866.getScaledBinRange(3, MAX_VALUE, 30);
		var3866.value(1);
		var3866.value(2);
		var3866.value(3);
		var3866.getBins();
		var3866.getScaledBinRange(1, 2, 30);
		var3866.getScaledBinRange(2, 3, 30);
		var3866.getScaledBinRange(3, MAX_VALUE, 30);
	}

	/**
	 * Test method for the class com.google.test.metric.report.chart.Histogram
	 */
	public void testHistogram1208() throws Exception {
		Histogram var3866 = new Histogram(1, 1, 3, new Logarithmic());
		var3866.value(1);
		var3866.value(2);
		var3866.value(3);
		var3866.getBins();
		var3866.getScaledBinRange(1, 2, 30);
		var3866.getScaledBinRange(2, 3, 30);
		var3866.getScaledBinRange(3, MAX_VALUE, 30);
		var3866.value(1);
		var3866.value(1);
		var3866.value(1);
		var3866.value(2);
		var3866.value(2);
		var3866.value(3);
		var3866.getBins();
		var3866.getScaledBinRange(1, 2, 30);
		var3866.getScaledBinRange(2, 3, 30);
		var3866.getScaledBinRange(3, MAX_VALUE, 30);
	}

	/**
	 * Test method for the class com.google.test.metric.report.chart.Histogram
	 */
	public void testHistogram1209() throws Exception {
		Histogram var3866 = new Histogram(1, 1, 3, new Logarithmic());
		var3866.value(1);
		var3866.value(2);
		var3866.value(3);
		var3866.getBins();
		var3866.getScaledBinRange(1, 2, 30);
		var3866.getScaledBinRange(2, 3, 30);
		var3866.getScaledBinRange(3, MAX_VALUE, 30);
		var3866.value(1);
		var3866.value(2);
		var3866.value(3);
		var3866.getBins();
		var3866.getScaledBinRange(1, 2, 30);
		var3866.getScaledBinRange(2, 3, 30);
		var3866.getScaledBinRange(3, MAX_VALUE, 30);
	}
}