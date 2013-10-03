package com.google.test.metric.method.op.stack;


import com.google.test.metric.method.op.stack.Load;
import java.lang.Object;
import com.google.test.metric.Variable;
import com.google.test.metric.method.op.stack.GetField;
import com.google.test.metric.method.op.stack.Return;
import com.google.test.metric.Type;
import com.google.test.metric.JavaType;
import com.google.test.metric.method.Constant;
import junit.framework.TestCase;
import com.google.test.metric.FieldInfo;
import com.google.test.metric.method.op.stack.PutField;
import com.google.test.metric.method.Block;
import static java.util.Arrays.asList;

public class JSRGenTest extends TestCase {
	public Block instantiateBlock663() {
		return new Block("main");
	}

	/**
	 * Test method for the class com.google.test.metric.method.op.stack.JSR
	 */
	public void testJSR1135() throws Exception {
		Block var3724 = instantiateBlock663();
		JSR var3725 = new JSR(0, var3724);
		var3724.addOp(new Load(0, new Constant(1, JavaType.INT)));
		var3724.addOp(new Return(0, JavaType.VOID));
		Block var3727 = new Block("branchA");
		var3724.addNextBlock(var3727);
		Block var3729 = new Block("branchB");
		var3724.addNextBlock(var3729);
		var3724.addOp(new Load(-1, var("this")));
		var3724.addOp(new Load(-1, var("root")));
	}

	public Block instantiateBlock664() {
		return new Block("sub");
	}

	/**
	 * Test method for the class com.google.test.metric.method.op.stack.JSR
	 */
	public void testJSR1136() throws Exception {
		Block var3730 = instantiateBlock664();
		JSR var3731 = new JSR(0, var3730);
		Type var3732 = JavaType.fromClass(Object.class);
		var3730.addOp(new GetField(-1, new FieldInfo(null, "src", var3732,
				false, true, false)));
		var3730.addOp(new PutField(-1, new FieldInfo(null, "dst", var3732,
				false, true, false)));
		Block var3734 = new Block("joined");
		var3730.addNextBlock(var3734);
		var3730.addOp(new Load(-1, var("A")));
	}

	public Block instantiateBlock665() {
		return new Block("joined");
	}

	/**
	 * Test method for the class com.google.test.metric.method.op.stack.JSR
	 */
	public void testJSR1137() throws Exception {
		Block var3735 = instantiateBlock665();
		JSR var3736 = new JSR(0, var3735);
		Block var3727 = new Block("branchA");
		var3735.addNextBlock(var3727);
		Block var3729 = new Block("branchB");
		var3735.addNextBlock(var3729);
		var3735.addOp(new Load(-1, var("this")));
		var3735.addOp(new Load(-1, var("root")));
		Block var3734 = new Block("joined");
		var3735.addNextBlock(var3734);
		var3735.addOp(new Load(-1, var("B")));
	}

	public Block instantiateBlock666() {
		return new Block("branchA");
	}

	/**
	 * Test method for the class com.google.test.metric.method.op.stack.JSR
	 */
	public void testJSR1138() throws Exception {
		Block var3737 = instantiateBlock666();
		JSR var3738 = new JSR(0, var3737);
		Block var3734 = new Block("joined");
		var3737.addNextBlock(var3734);
		var3737.addOp(new Load(-1, var("B")));
		Block var3739 = new Block("sub2");
		var3737.addNextBlock(var3739);
	}

	public Block instantiateBlock667() {
		return new Block("joined");
	}

	/**
	 * Test method for the class com.google.test.metric.method.op.stack.JSR
	 */
	public void testJSR1139() throws Exception {
		Block var3741 = instantiateBlock667();
		JSR var3742 = new JSR(0, var3741);
		Block var3727 = new Block("branchA");
		var3741.addNextBlock(var3727);
		Block var3729 = new Block("branchB");
		var3741.addNextBlock(var3729);
		var3741.addOp(new Load(-1, var("this")));
		var3741.addOp(new Load(-1, var("root")));
		Block var3734 = new Block("joined");
		var3741.addNextBlock(var3734);
		var3741.addOp(new Load(-1, var("A")));
	}
}