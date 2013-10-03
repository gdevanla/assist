package com.google.test.metric.method.op.stack;


import com.google.test.metric.Variable;
import com.google.test.metric.LocalField;
import java.lang.String;
import com.google.test.metric.Type;
import com.google.test.metric.JavaType;
import com.google.test.metric.method.Constant;
import junit.framework.TestCase;
import com.google.test.metric.FieldInfo;
import static java.util.Collections.EMPTY_LIST;
import static com.google.test.metric.JavaType.VOID;
import static java.util.Arrays.asList;

public class LoadGenTest extends TestCase {
	public LocalField instantiateLocalField655() {
		Variable var3699 = new Variable("var", null, false, false);
		FieldInfo var3700 = new FieldInfo(null, "field", null, false, false,
				false);
		return new LocalField(var3699, var3700);
	}

	/**
	 * Test method for the class com.google.test.metric.method.op.stack.Load
	 */
	public void testLoad1125() throws Exception {
		LocalField var3701 = instantiateLocalField655();
		Load var3702 = new Load(22, new Constant(4, JavaType.INT));
		var3701.getDescription();
		var3701.getDescription();
	}

	public LocalField instantiateLocalField656() {
		Variable var3704 = new Variable("instance", null, false, false);
		FieldInfo var3705 = new FieldInfo(null, "field", null, true, false,
				false);
		return new LocalField(var3704, var3705);
	}

	/**
	 * Test method for the class com.google.test.metric.method.op.stack.Load
	 */
	public void testLoad1126() throws Exception {
		LocalField var3706 = instantiateLocalField656();
		Load var3707 = new Load(0, new Constant(1, JavaType.INT));
		var3706.getDescription();
		var3706.getDescription();
	}

	public Variable instantiateVariable657() {
		return new Variable("var", null, false, false);
	}

	/**
	 * Test method for the class com.google.test.metric.method.op.stack.Load
	 */
	public void testLoad1127() throws Exception {
		Variable var3708 = instantiateVariable657();
		Load var3709 = new Load(2, new Constant(2, JavaType.INT));
	}

	public Variable instantiateVariable658() {
		return new Variable("a", JavaType.BOOLEAN, false, false);
	}

	/**
	 * Test method for the class com.google.test.metric.method.op.stack.Load
	 */
	public void testLoad1128() throws Exception {
		Variable var3710 = instantiateVariable658();
		Load var3711 = new Load(-1, var3710);
	}

	public Variable instantiateVariable659() {
		return new Variable(null, JavaType.BOOLEAN, false, false);
	}

	/**
	 * Test method for the class com.google.test.metric.method.op.stack.Load
	 */
	public void testLoad1129() throws Exception {
		Variable var3712 = instantiateVariable659();
		Load var3713 = new Load(16, new Constant(4, JavaType.INT));
	}
}