package com.google.test.metric.report;


import java.lang.String;
import com.google.test.metric.report.CharMarker;
import junit.framework.TestCase;

public class TextHistogramGenTest extends TestCase {
	public CharMarker instantiateCharMarker691() {
		return new CharMarker('.', '=', '#');
	}

	/**
	 * Test method for the class com.google.test.metric.report.TextHistogram
	 */
	public void testTextHistogram1210() throws Exception {
		CharMarker var3870 = instantiateCharMarker691();
		TextHistogram var3871 = new TextHistogram(6, 3, var3870);
		var3871.setMin(0);
		var3871.setMax(30);
		var3871.graph(10, 20, 20, 30, 30, 30);
		var3871.setMin(0);
		var3871.setMax(30);
		var3871.count(10, 10, 20, 20, 30, 30, 30);
	}

	public CharMarker instantiateCharMarker692() {
		return new CharMarker('.', '=', '#');
	}

	/**
	 * Test method for the class com.google.test.metric.report.TextHistogram
	 */
	public void testTextHistogram1211() throws Exception {
		CharMarker var3874 = instantiateCharMarker692();
		TextHistogram var3875 = new TextHistogram(-1, -1, null);
		var3875.setMin(0);
		var3875.setMax(30);
		var3875.count(10, 10, 20, 20, 30, 30, 30);
		var3875.setMin(0);
		var3875.setMax(30);
		var3875.graph(10, 20, 20, 30, 30, 30);
	}

	public CharMarker instantiateCharMarker693() {
		return new CharMarker('.', '=', '#');
	}

	/**
	 * Test method for the class com.google.test.metric.report.TextHistogram
	 */
	public void testTextHistogram1212() throws Exception {
		CharMarker var3876 = instantiateCharMarker693();
		TextHistogram var3877 = new TextHistogram(6, 3, var3876);
		var3877.setMin(0);
		var3877.setMax(30);
		var3877.graph(10, 20, 20, 30, 30, 30);
		var3877.setMin(0);
		var3877.setMax(30);
		var3877.graph(10, 20, 20, 30, 30, 30);
	}

	public CharMarker instantiateCharMarker694() {
		return new CharMarker('.', '=', '#');
	}

	/**
	 * Test method for the class com.google.test.metric.report.TextHistogram
	 */
	public void testTextHistogram1213() throws Exception {
		CharMarker var3878 = instantiateCharMarker694();
		TextHistogram var3875 = new TextHistogram(-1, -1, null);
		var3875.setMin(0);
		var3875.setMax(30);
		var3875.graph(10, 20, 20, 30, 30, 30);
		var3875.setMin(0);
		var3875.setMax(30);
		var3875.graph(10, 20, 20, 30, 30, 30);
	}

	public CharMarker instantiateCharMarker695() {
		return new CharMarker('.', '=', '#');
	}

	/**
	 * Test method for the class com.google.test.metric.report.TextHistogram
	 */
	public void testTextHistogram1214() throws Exception {
		CharMarker var3879 = instantiateCharMarker695();
		TextHistogram var3880 = new TextHistogram(-1, 3, null);
		var3880.setMin(0);
		var3880.setMax(30);
		var3880.graph(10, 20, 20, 30, 30, 30);
		var3880.setMin(0);
		var3880.setMax(30);
		var3880.graph(10, 20, 20, 30, 30, 30);
	}
}