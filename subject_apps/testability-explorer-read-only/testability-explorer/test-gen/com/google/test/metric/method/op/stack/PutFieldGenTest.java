package com.google.test.metric.method.op.stack;


import java.lang.Object;
import com.google.test.metric.Type;
import com.google.test.metric.JavaType;
import junit.framework.TestCase;
import com.google.test.metric.FieldInfo;
import static java.util.Collections.EMPTY_LIST;
import static java.util.Arrays.asList;

public class PutFieldGenTest extends TestCase {
	public FieldInfo instantiateFieldInfo645() {
		return new FieldInfo(null, "field", null, false, false, false);
	}

	/**
	 * Test method for the class com.google.test.metric.method.op.stack.PutField
	 */
	public void testPutField1115() throws Exception {
		FieldInfo var3675 = instantiateFieldInfo645();
		PutField var3676 = new PutField(0, var3675);
	}

	public FieldInfo instantiateFieldInfo646() {
		return new FieldInfo(null, "field", null, true, false, false);
	}

	/**
	 * Test method for the class com.google.test.metric.method.op.stack.PutField
	 */
	public void testPutField1116() throws Exception {
		FieldInfo var3677 = instantiateFieldInfo646();
		Type var3678 = JavaType.fromClass(Object.class);
		PutField var3679 = new PutField(-1, var3677);
	}

	public FieldInfo instantiateFieldInfo647() {
		return new FieldInfo(null, "field", null, false, false, false);
	}

	/**
	 * Test method for the class com.google.test.metric.method.op.stack.PutField
	 */
	public void testPutField1117() throws Exception {
		FieldInfo var3680 = instantiateFieldInfo647();
		Type var3678 = JavaType.fromClass(Object.class);
		PutField var3681 = new PutField(-1, var3680);
	}

	public FieldInfo instantiateFieldInfo648() {
		return new FieldInfo(null, "field", null, false, false, false);
	}

	/**
	 * Test method for the class com.google.test.metric.method.op.stack.PutField
	 */
	public void testPutField1118() throws Exception {
		FieldInfo var3682 = instantiateFieldInfo648();
		Type var3678 = JavaType.fromClass(Object.class);
		PutField var3683 = new PutField(-1, var3682);
	}

	public FieldInfo instantiateFieldInfo649() {
		return new FieldInfo(null, "field", null, true, true, false);
	}

	/**
	 * Test method for the class com.google.test.metric.method.op.stack.PutField
	 */
	public void testPutField1119() throws Exception {
		FieldInfo var3684 = instantiateFieldInfo649();
		PutField var3685 = new PutField(0, var3684);
	}
}