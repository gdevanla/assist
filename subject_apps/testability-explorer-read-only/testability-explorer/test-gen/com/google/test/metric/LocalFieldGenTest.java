package com.google.test.metric;


import com.google.test.metric.Variable;
import com.google.test.metric.LocalField;
import com.google.test.metric.Type;
import java.lang.String;
import com.google.test.metric.JavaType;
import com.google.test.metric.method.Constant;
import junit.framework.TestCase;
import com.google.test.metric.LocalVariableInfo;
import com.google.test.metric.FieldInfo;
import static java.util.Collections.EMPTY_LIST;
import static com.google.test.metric.JavaType.VOID;
import static java.util.Arrays.asList;

public class LocalFieldGenTest extends TestCase {
	public LocalField instantiateLocalField442() {
		Variable var2820 = new Variable("instance", null, false, false);
		FieldInfo var2821 = new FieldInfo(null, "field", null, true, false,
				false);
		return new LocalField(var2820, var2821);
	}

	public FieldInfo instantiateFieldInfo443() {
		return new FieldInfo(null, "field", null, true, true, false);
	}

	/**
	 * Test method for the class com.google.test.metric.LocalField
	 */
	public void testLocalField900() throws Exception {
		LocalField var2822 = instantiateLocalField442();
		FieldInfo var2823 = instantiateFieldInfo443();
		Variable var2820 = new Variable("instance", null, false, false);
		LocalField var2824 = new LocalField(var2820, var2823);
		var2822.getDescription();
		var2822.getDescription();
		var2824.getDescription();
		var2824.getDescription();
	}

	public Constant instantiateConstant444() {
		return new Constant("a", JavaType.fromClass(String.class));
	}

	public FieldInfo instantiateFieldInfo445() {
		return new FieldInfo(null, "field", null, false, false, false);
	}

	/**
	 * Test method for the class com.google.test.metric.LocalField
	 */
	public void testLocalField901() throws Exception {
		Constant var2826 = instantiateConstant444();
		FieldInfo var2827 = instantiateFieldInfo445();
		Variable var2828 = new Variable("var", null, false, false);
		LocalField var2829 = new LocalField(var2828, var2827);
		var2829.getDescription();
		var2829.getDescription();
	}

	public LocalVariableInfo instantiateLocalVariableInfo446() {
		return new LocalVariableInfo("b", JavaType.INT);
	}

	public FieldInfo instantiateFieldInfo447() {
		Type var2830 = JavaType
				.fromJava("com.google.test.metric.example.MutableGlobalState."
						+ "FinalGlobalExample$Gadget");
		return new FieldInfo(null, "finalInstance", var2830, false, false,
				false);
	}

	/**
	 * Test method for the class com.google.test.metric.LocalField
	 */
	public void testLocalField902() throws Exception {
		FieldInfo var2831 = instantiateFieldInfo447();
		LocalVariableInfo var2832 = instantiateLocalVariableInfo446();
		LocalField var2833 = new LocalField(null, var2831);
		var2833.getDescription();
		var2833.getDescription();
	}

	public FieldInfo instantiateFieldInfo448() {
		return new FieldInfo(null, "field", null, false, false, false);
	}

	/**
	 * Test method for the class com.google.test.metric.LocalField
	 */
	public void testLocalField903() throws Exception {
		FieldInfo var2834 = instantiateFieldInfo448();
		Variable var2820 = new Variable("instance", null, false, false);
		LocalField var2835 = new LocalField(var2820, var2834);
		var2835.getDescription();
		var2835.getDescription();
	}

	public FieldInfo instantiateFieldInfo449() {
		Type var2830 = JavaType
				.fromJava("com.google.test.metric.example.MutableGlobalState."
						+ "FinalGlobalExample$Gadget");
		return new FieldInfo(null, "finalInstance", var2830, false, false,
				false);
	}

	/**
	 * Test method for the class com.google.test.metric.LocalField
	 */
	public void testLocalField904() throws Exception {
		FieldInfo var2836 = instantiateFieldInfo449();
		Variable var2820 = new Variable("instance", null, false, false);
		LocalField var2837 = new LocalField(var2820, var2836);
		var2837.getDescription();
		var2837.getDescription();
	}
}