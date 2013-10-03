package com.google.test.metric.method;


import com.google.test.metric.method.op.stack.Load;
import java.lang.Object;
import com.google.test.metric.Variable;
import java.util.List;
import com.google.test.metric.JavaType;
import junit.framework.TestCase;
import com.google.test.metric.FieldInfo;
import com.google.test.metric.method.Block;
import com.google.test.metric.method.op.stack.Invoke;
import com.google.test.metric.method.op.stack.GetField;
import com.google.test.metric.Type;
import com.google.test.metric.method.op.stack.JSR;
import com.google.test.metric.method.op.stack.PutField;
import static java.util.Arrays.asList;

public class BlockGenTest extends TestCase {
	/**
	 * Test method for the class com.google.test.metric.method.Block
	 */
	public void testBlock1140() throws Exception {
		Block var3743 = new Block("1");
		Block var3744 = new Block("sub1");
		var3743.addNextBlock(var3744);
		Block var3746 = new Block("joined");
		var3743.addNextBlock(var3746);
		var3743.addOp(new Load(-1, var("B")));
	}

	/**
	 * Test method for the class com.google.test.metric.method.Block
	 */
	public void testBlock1141() throws Exception {
		Block var3748 = new Block("sub2");
		var3748.addOp(new Load(-1, var(1)));
		Type var3750 = JavaType.fromClass(Object.class);
		var3748.addOp(new PutField(-1, new FieldInfo(null, "abc", var3750,
				false, true, false)));
		Block var3744 = new Block("sub1");
		var3748.addNextBlock(var3744);
	}

	/**
	 * Test method for the class com.google.test.metric.method.Block
	 */
	public void testBlock1142() throws Exception {
		Block var3751 = new Block("sub");
		Block var3746 = new Block("joined");
		var3751.addNextBlock(var3746);
		var3751.addOp(new Load(-1, var("B")));
		Type var3750 = JavaType.fromClass(Object.class);
		var3751.addOp(new GetField(-1, new FieldInfo(null, "src", var3750,
				false, true, false)));
		var3751.addOp(new PutField(-1, new FieldInfo(null, "dst", var3750,
				false, true, false)));
	}

	/**
	 * Test method for the class com.google.test.metric.method.Block
	 */
	public void testBlock1143() throws Exception {
		Block var3753 = new Block("main");
		var3753.addOp(new Load(0, new Variable("this", JavaType.OBJECT, false,
				false)));
		Block var3755 = new Block("sub");
		var3753.addOp(new JSR(0, var3755));
		var3753.addOp(new PutField(0, new FieldInfo(null, "a", JavaType.INT,
				false, false, false)));
		Block var3746 = new Block("joined");
		var3753.addNextBlock(var3746);
		var3753.addOp(new Load(-1, var("A")));
	}

	/**
	 * Test method for the class com.google.test.metric.method.Block
	 */
	public void testBlock1144() throws Exception {
		Block var3751 = new Block("sub");
		var3751.addOp(new Load(-1, var("methodThis")));
		Type var3750 = JavaType.fromClass(Object.class);
		var3751.addOp(new GetField(-1, new FieldInfo(null, "p1", var3750,
				false, true, false)));
		var3751.addOp(new GetField(-1, new FieldInfo(null, "p2", var3750,
				false, true, false)));
		var3751.addOp(new Invoke(-1, null, "int methodA(int, int)", asList(
				JavaType.INT, JavaType.INT), false, var3750));
		var3751.addOp(new PutField(-1, new FieldInfo(null, "dst", var3750,
				false, true, false)));
		Block var3758 = new Block("sub");
		var3751.addOp(new JSR(0, var3758));
		var3751.addOp(new JSR(0, var3758));
	}
}