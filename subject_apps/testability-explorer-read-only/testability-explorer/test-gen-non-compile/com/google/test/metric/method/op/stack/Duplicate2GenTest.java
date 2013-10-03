package com.google.test.metric.method.op.stack;


import com.google.test.metric.Variable;
import java.util.List;
import java.lang.String;
import junit.framework.TestCase;
import static java.util.Arrays.asList;

public class Duplicate2GenTest extends TestCase {
	/**
	 * Test method for the class com.google.test.metric.method.op.stack.Duplicate2
	 */
	public void testDuplicate21105() throws Exception {
		Duplicate2 var3643 = new Duplicate2(1, 2);
		var3643.getOperatorCount();
		Variable var3645 = var(3);
		Variable var3646 = var(2);
		Variable var3647 = var(1l);
		var3643.apply(asList(var3645, var3646, var3647, var3647));
		var3643.toString();
		var3643.getOperatorCount();
		Variable var3649 = var(2);
		Variable var3650 = var(1l);
		var3643.apply(asList(var3649, var3650, var3650));
		var3643.toString();
	}

	/**
	 * Test method for the class com.google.test.metric.method.op.stack.Duplicate2
	 */
	public void testDuplicate21106() throws Exception {
		Duplicate2 var3651 = new Duplicate2(1, 0);
		var3651.getOperatorCount();
		Variable var3653 = var(1);
		Variable var3654 = var(2);
		var3651.apply(asList(var3653, var3654));
		var3651.toString();
		var3651.getOperatorCount();
		Variable var3656 = var(1l);
		var3651.apply(asList(var3656, var3656));
		var3651.toString();
	}

	/**
	 * Test method for the class com.google.test.metric.method.op.stack.Duplicate2
	 */
	public void testDuplicate21107() throws Exception {
		Duplicate2 var3657 = new Duplicate2(1, 1);
		var3657.getOperatorCount();
		Variable var3659 = var(4);
		Variable var3660 = var(3);
		Variable var3661 = var(2);
		Variable var3662 = var(1);
		var3657.apply(asList(var3659, var3660, var3661, var3662));
		var3657.toString();
		var3657.getOperatorCount();
		var3657.apply(asList(var3659, var3660, var3661, var3662));
		var3657.toString();
	}

	/**
	 * Test method for the class com.google.test.metric.method.op.stack.Duplicate2
	 */
	public void testDuplicate21108() throws Exception {
		Duplicate2 var3651 = new Duplicate2(1, 0);
		var3651.getOperatorCount();
		Variable var3645 = var(3);
		Variable var3646 = var(2);
		Variable var3647 = var(1l);
		var3651.apply(asList(var3645, var3646, var3647, var3647));
		var3651.toString();
		var3651.getOperatorCount();
		Variable var3653 = var(1);
		Variable var3654 = var(2);
		var3651.apply(asList(var3653, var3654));
		var3651.toString();
	}

	/**
	 * Test method for the class com.google.test.metric.method.op.stack.Duplicate2
	 */
	public void testDuplicate21109() throws Exception {
		Duplicate2 var3643 = new Duplicate2(1, 2);
		var3643.getOperatorCount();
		Variable var3645 = var(3);
		Variable var3646 = var(2);
		Variable var3647 = var(1l);
		var3643.apply(asList(var3645, var3646, var3647, var3647));
		var3643.toString();
		var3643.getOperatorCount();
		var3643.apply(asList(var3645, var3646, var3647, var3647));
		var3643.toString();
	}
}