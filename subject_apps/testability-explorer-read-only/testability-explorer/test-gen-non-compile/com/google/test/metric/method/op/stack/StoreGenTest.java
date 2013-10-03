package com.google.test.metric.method.op.stack;


import com.google.test.metric.Variable;
import java.lang.Class;
import com.google.test.metric.Type;
import com.google.test.metric.JavaType;
import com.google.test.metric.method.Constant;
import junit.framework.TestCase;
import com.google.test.metric.FieldInfo;
import com.google.test.metric.LocalVariableInfo;
import static java.util.Collections.EMPTY_LIST;
import static com.google.test.metric.JavaType.VOID;
import static java.util.Arrays.asList;

public class StoreGenTest extends TestCase {
	/**
	 * Test method for the class com.google.test.metric.method.op.stack.Store
	 */
	public void testStore1130() throws Exception {
		Variable var3714 = new LocalVariableInfo("b", JavaType.INT);
		Store var3715 = new Store(23, var3714);
	}

	public FieldInfo instantiateFieldInfo660() {
		return new FieldInfo(null, "field", null, false, false, false);
	}

	/**
	 * Test method for the class com.google.test.metric.method.op.stack.Store
	 */
	public void testStore1131() throws Exception {
		FieldInfo var3716 = instantiateFieldInfo660();
		Variable var3714 = new LocalVariableInfo("b", JavaType.INT);
		Store var3717 = new Store(17, var3714);
	}

	public Variable instantiateVariable661() {
		return new Variable("instance", null, true, false);
	}

	/**
	 * Test method for the class com.google.test.metric.method.op.stack.Store
	 */
	public void testStore1132() throws Exception {
		Variable var3718 = instantiateVariable661();
		Store var3719 = new Store(9, var3718);
	}

	public Constant instantiateConstant662() {
		return new Constant(var3720, JavaType.fromClass(var3720.getClass()));
	}

	/**
	 * Test method for the class com.google.test.metric.method.op.stack.Store
	 */
	public void testStore1133() throws Exception {
		Constant var3721 = instantiateConstant662();
		Variable var3714 = new LocalVariableInfo("b", JavaType.INT);
		Store var3722 = new Store(9, var3714);
	}

	/**
	 * Test method for the class com.google.test.metric.method.op.stack.Store
	 */
	public void testStore1134() throws Exception {
		Variable var3714 = new LocalVariableInfo("b", JavaType.INT);
		Store var3723 = new Store(21, var3714);
	}
}