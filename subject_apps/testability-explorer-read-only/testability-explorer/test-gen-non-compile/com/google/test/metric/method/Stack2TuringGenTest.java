package com.google.test.metric.method;


import com.google.test.metric.method.op.turing.Operation;
import com.google.test.metric.method.op.stack.Load;
import java.lang.Object;
import com.google.test.metric.Variable;
import java.util.List;
import java.lang.String;
import com.google.test.metric.JavaType;
import junit.framework.TestCase;
import com.google.test.metric.FieldInfo;
import com.google.test.metric.method.op.stack.Invoke;
import com.google.test.metric.method.Block;
import com.google.test.metric.method.op.stack.RetSub;
import com.google.test.metric.method.op.stack.GetField;
import com.google.test.metric.method.op.stack.JSR;
import com.google.test.metric.Type;
import com.google.test.metric.method.op.stack.PutField;
import static java.util.Arrays.asList;

public class Stack2TuringGenTest extends TestCase {
	public Block instantiateBlock668() {
		return new Block("sub2");
	}

	/**
	 * Test method for the class com.google.test.metric.method.Stack2Turing
	 */
	public void testStack2Turing1175() throws Exception {
		Block var3774 = instantiateBlock668();
		var3775.addOp(new JSR(0, var3776));
		var3775.addOp(new JSR(0, var3776));
		Stack2Turing var3777 = new Stack2Turing(var3774);
		var3774.addOp(new Load(-1, var("this")));
		var3774.addOp(new Load(-1, var(1)));
		Type var3779 = JavaType.fromClass(Object.class);
		var3774.addOp(new PutField(-1, new FieldInfo(null, "abc", var3779,
				false, false, false)));
		Block var3780 = new Block("branchA");
		var3774.addNextBlock(var3780);
		Block var3782 = new Block("branchB");
		var3774.addNextBlock(var3782);
		var3774.addOp(new Load(-1, var("this")));
		var3774.addOp(new Load(-1, var("root")));
		var3777.translate();
		var3777.translate();
	}

	public Block instantiateBlock669() {
		return new Block("sub1");
	}

	/**
	 * Test method for the class com.google.test.metric.method.Stack2Turing
	 */
	public void testStack2Turing1176() throws Exception {
		Block var3785 = instantiateBlock669();
		var3786.addNextBlock(var3787);
		var3788.addOp(new JSR(0, var3786));
		Stack2Turing var3789 = new Stack2Turing(var3785);
		var3785.toString();
		var3785.addOp(new Load(-1, var(1)));
		var3785.toString();
		var3785.addOp(new Load(0, new Variable("this", JavaType.OBJECT, false,
				false)));
		Block var3792 = new Block("sub");
		var3785.addOp(new JSR(0, var3792));
		var3785.addOp(new PutField(0, new FieldInfo(null, "a", JavaType.INT,
				false, false, false)));
		var3789.translate();
		var3789.translate();
	}

	public Block instantiateBlock670() {
		return new Block("sub");
	}

	/**
	 * Test method for the class com.google.test.metric.method.Stack2Turing
	 */
	public void testStack2Turing1177() throws Exception {
		Block var3794 = instantiateBlock670();
		var3791.addOp(new Load(0, new Variable("this", JavaType.OBJECT, false,
				false)));
		var3791.addOp(new JSR(0, var3792));
		var3791.addOp(new PutField(0, new FieldInfo(null, "a", JavaType.INT,
				false, false, false)));
		Stack2Turing var3795 = new Stack2Turing(var3794);
		var3794.addOp(new Load(-1, var("methodThis")));
		Type var3779 = JavaType.fromClass(Object.class);
		var3794.addOp(new GetField(-1, new FieldInfo(null, "p1", var3779,
				false, true, false)));
		var3794.addOp(new GetField(-1, new FieldInfo(null, "p2", var3779,
				false, true, false)));
		var3794.addOp(new Invoke(-1, null, "int methodA(int, int)", asList(
				JavaType.INT, JavaType.INT), false, var3779));
		var3794.addOp(new PutField(-1, new FieldInfo(null, "dst", var3779,
				false, true, false)));
		var3794.addOp(new RetSub(1));
		Block var3775 = new Block("main");
		Block var3776 = new Block("sub");
		var3775.addOp(new JSR(0, var3776));
		var3775.addOp(new JSR(0, var3776));
		var3794.addNextBlock(var3775);
		var3795.translate();
		var3795.translate();
	}

	public Block instantiateBlock671() {
		return new Block("sub");
	}

	/**
	 * Test method for the class com.google.test.metric.method.Stack2Turing
	 */
	public void testStack2Turing1178() throws Exception {
		Block var3797 = instantiateBlock671();
		var3791.addOp(new Load(0, new Variable("this", JavaType.OBJECT, false,
				false)));
		var3791.addOp(new JSR(0, var3792));
		var3791.addOp(new PutField(0, new FieldInfo(null, "a", JavaType.INT,
				false, false, false)));
		Stack2Turing var3798 = new Stack2Turing(var3797);
		Block var3799 = new Block("joined");
		var3797.addNextBlock(var3799);
		var3797.addOp(new Load(-1, var("B")));
		var3797.addNextBlock(var3799);
		var3797.addOp(new Load(-1, var("A")));
		var3798.translate();
		var3798.translate();
	}

	public Block instantiateBlock672() {
		return new Block("main");
	}

	/**
	 * Test method for the class com.google.test.metric.method.Stack2Turing
	 */
	public void testStack2Turing1179() throws Exception {
		Block var3800 = instantiateBlock672();
		var3778.addOp(new Load(-1, var("this")));
		var3778.addOp(new Load(-1, var(1)));
		Type var3779 = JavaType.fromClass(Object.class);
		var3778.addOp(new PutField(-1, new FieldInfo(null, "abc", var3779,
				false, false, false)));
		Stack2Turing var3801 = new Stack2Turing(var3800);
		var3800.addOp(new Load(-1, var("this")));
		var3800.addOp(new Load(-1, var(1)));
		var3800.addOp(new PutField(-1, new FieldInfo(null, "abc", var3779,
				false, false, false)));
		var3800.addOp(new Load(-1, var("methodThis")));
		var3800.addOp(new GetField(-1, new FieldInfo(null, "p1", var3779,
				false, true, false)));
		var3800.addOp(new GetField(-1, new FieldInfo(null, "p2", var3779,
				false, true, false)));
		var3800.addOp(new Invoke(-1, null, "int methodA(int, int)", asList(
				JavaType.INT, JavaType.INT), false, var3779));
		var3800.addOp(new PutField(-1, new FieldInfo(null, "dst", var3779,
				false, true, false)));
		var3801.translate();
		var3801.translate();
	}
}