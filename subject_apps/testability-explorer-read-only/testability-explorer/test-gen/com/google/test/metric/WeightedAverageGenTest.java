package com.google.test.metric;


import com.google.test.metric.CostModel;
import junit.framework.TestCase;
import static java.lang.Math.pow;

public class WeightedAverageGenTest extends TestCase {
	/**
	 * Test method for the class com.google.test.metric.WeightedAverage
	 */
	public void testWeightedAverage1075() throws Exception {
		double var3550 = CostModel.WEIGHT_TO_EMPHASIZE_EXPENSIVE_METHODS;
		WeightedAverage var3551 = new WeightedAverage(var3550);
		var3551.addValue(1);
		var3551.addValue(3);
		var3551.getAverage();
		var3551.addValue(1);
		var3551.addValue(3);
		var3551.getAverage();
	}

	/**
	 * Test method for the class com.google.test.metric.WeightedAverage
	 */
	public void testWeightedAverage1076() throws Exception {
		WeightedAverage var3554 = new WeightedAverage(1);
		var3554.addValue(1);
		var3554.addValue(3);
		var3554.getAverage();
		var3554.addValue(1);
		var3554.addValue(3);
		var3554.getAverage();
	}

	/**
	 * Test method for the class com.google.test.metric.WeightedAverage
	 */
	public void testWeightedAverage1077() throws Exception {
		WeightedAverage var3554 = new WeightedAverage(1);
		var3554.addValue(1);
		var3554.addValue(3);
		var3554.getAverage();
		var3554.addValue(1);
		var3554.addValue(3);
		var3554.getAverage();
	}

	/**
	 * Test method for the class com.google.test.metric.WeightedAverage
	 */
	public void testWeightedAverage1078() throws Exception {
		WeightedAverage var3554 = new WeightedAverage(1);
		var3554.addValue(1);
		var3554.addValue(3);
		var3554.getAverage();
		var3554.addValue(1);
		var3554.addValue(3);
		var3554.getAverage();
	}

	/**
	 * Test method for the class com.google.test.metric.WeightedAverage
	 */
	public void testWeightedAverage1079() throws Exception {
		double var3550 = CostModel.WEIGHT_TO_EMPHASIZE_EXPENSIVE_METHODS;
		WeightedAverage var3551 = new WeightedAverage(var3550);
		var3551.addValue(1);
		var3551.addValue(3);
		var3551.getAverage();
		var3551.addValue(1);
		var3551.addValue(3);
		var3551.getAverage();
	}
}