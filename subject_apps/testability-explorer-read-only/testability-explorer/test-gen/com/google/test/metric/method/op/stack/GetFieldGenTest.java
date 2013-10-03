package com.google.test.metric.method.op.stack;


import java.lang.Object;
import com.google.test.metric.Type;
import com.google.test.metric.JavaType;
import junit.framework.TestCase;
import com.google.test.metric.FieldInfo;
import static java.util.Collections.EMPTY_LIST;
import static java.util.Arrays.asList;

public class GetFieldGenTest extends TestCase {
	public FieldInfo instantiateFieldInfo640() {
		Type var3663 = JavaType
				.fromJava("com.google.test.metric.example.MutableGlobalState."
						+ "FinalGlobalExample$Gadget");
		return new FieldInfo(null, "finalInstance", var3663, false, false,
				false);
	}

	/**
	 * Test method for the class com.google.test.metric.method.op.stack.GetField
	 */
	public void testGetField1110() throws Exception {
		FieldInfo var3664 = instantiateFieldInfo640();
		Type var3665 = JavaType.fromClass(Object.class);
		GetField var3666 = new GetField(-1, var3664);
	}

	public FieldInfo instantiateFieldInfo641() {
		return new FieldInfo(null, "field", null, true, false, false);
	}

	/**
	 * Test method for the class com.google.test.metric.method.op.stack.GetField
	 */
	public void testGetField1111() throws Exception {
		FieldInfo var3667 = instantiateFieldInfo641();
		Type var3665 = JavaType.fromClass(Object.class);
		GetField var3668 = new GetField(-1, var3667);
	}

	public FieldInfo instantiateFieldInfo642() {
		return new FieldInfo(null, "dstField", null, false, false, false);
	}

	/**
	 * Test method for the class com.google.test.metric.method.op.stack.GetField
	 */
	public void testGetField1112() throws Exception {
		FieldInfo var3669 = instantiateFieldInfo642();
		Type var3665 = JavaType.fromClass(Object.class);
		GetField var3670 = new GetField(-1, var3669);
	}

	public FieldInfo instantiateFieldInfo643() {
		return new FieldInfo(null, "field", null, false, false, false);
	}

	/**
	 * Test method for the class com.google.test.metric.method.op.stack.GetField
	 */
	public void testGetField1113() throws Exception {
		FieldInfo var3671 = instantiateFieldInfo643();
		Type var3665 = JavaType.fromClass(Object.class);
		GetField var3672 = new GetField(-1, var3671);
	}

	public FieldInfo instantiateFieldInfo644() {
		Type var3663 = JavaType
				.fromJava("com.google.test.metric.example.MutableGlobalState."
						+ "FinalGlobalExample$Gadget");
		return new FieldInfo(null, "finalInstance", var3663, false, false,
				false);
	}

	/**
	 * Test method for the class com.google.test.metric.method.op.stack.GetField
	 */
	public void testGetField1114() throws Exception {
		FieldInfo var3673 = instantiateFieldInfo644();
		Type var3665 = JavaType.fromClass(Object.class);
		GetField var3674 = new GetField(-1, var3673);
	}
}