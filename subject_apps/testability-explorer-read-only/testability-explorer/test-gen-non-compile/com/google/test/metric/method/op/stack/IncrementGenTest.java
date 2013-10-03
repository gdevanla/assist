package com.google.test.metric.method.op.stack;


import java.lang.Object;
import com.google.test.metric.Variable;
import com.google.test.metric.LocalField;
import java.lang.String;
import com.google.test.metric.Type;
import com.google.test.metric.JavaType;
import com.google.test.metric.method.Constant;
import junit.framework.TestCase;
import com.google.test.metric.FieldInfo;
import com.google.test.metric.LocalVariableInfo;
import static java.util.Collections.EMPTY_LIST;
import static com.google.test.metric.JavaType.VOID;
import static java.util.Arrays.asList;

public class IncrementGenTest extends TestCase {
	public LocalVariableInfo instantiateLocalVariableInfo650() {
		return new LocalVariableInfo("b", JavaType.INT);
	}

	/**
	 * Test method for the class com.google.test.metric.method.op.stack.Increment
	 */
	public void testIncrement1120() throws Exception {
		LocalVariableInfo var3686 = instantiateLocalVariableInfo650();
		Variable var3687 = new Variable("a", JavaType.BOOLEAN, false, false);
		Increment var3688 = new Increment(-1, 1, var3687);
		var3688.toString();
		var3688.toString();
	}

	public FieldInfo instantiateFieldInfo651() {
		return new FieldInfo(null, "field", null, true, true, false);
	}

	/**
	 * Test method for the class com.google.test.metric.method.op.stack.Increment
	 */
	public void testIncrement1121() throws Exception {
		FieldInfo var3690 = instantiateFieldInfo651();
		Variable var3687 = new Variable("a", JavaType.BOOLEAN, false, false);
		Increment var3688 = new Increment(-1, 1, var3687);
		var3688.toString();
		var3688.toString();
	}

	public LocalField instantiateLocalField652() {
		Type var3691 = JavaType
				.fromJava("com.google.test.metric.example.MutableGlobalState."
						+ "FinalGlobalExample$Gadget");
		FieldInfo var3692 = new FieldInfo(null, "finalInstance", var3691,
				false, false, false);
		return new LocalField(new Variable(
				"com.google.test.metric.example.MutableGlobalState.FinalGlobalExample$"
						+ "FinalGlobal.finalInstance", var3691, false, false),
				var3692);
	}

	/**
	 * Test method for the class com.google.test.metric.method.op.stack.Increment
	 */
	public void testIncrement1122() throws Exception {
		LocalField var3693 = instantiateLocalField652();
		Variable var3687 = new Variable("a", JavaType.BOOLEAN, false, false);
		Increment var3688 = new Increment(-1, 1, var3687);
		var3693.getDescription();
		var3693.getDescription();
		var3688.toString();
		var3688.toString();
	}

	public Constant instantiateConstant653() {
		Type var3695 = JavaType.fromClass(Object.class);
		return new Constant(var3696, var3695);
	}

	/**
	 * Test method for the class com.google.test.metric.method.op.stack.Increment
	 */
	public void testIncrement1123() throws Exception {
		Constant var3697 = instantiateConstant653();
		Variable var3687 = new Variable("a", JavaType.BOOLEAN, false, false);
		Increment var3688 = new Increment(-1, 1, var3687);
		var3688.toString();
		var3688.toString();
	}

	public FieldInfo instantiateFieldInfo654() {
		return new FieldInfo(null, "field", null, false, false, false);
	}

	/**
	 * Test method for the class com.google.test.metric.method.op.stack.Increment
	 */
	public void testIncrement1124() throws Exception {
		FieldInfo var3698 = instantiateFieldInfo654();
		Variable var3687 = new Variable("a", JavaType.BOOLEAN, false, false);
		Increment var3688 = new Increment(-1, 1, var3687);
		var3688.toString();
		var3688.toString();
	}
}