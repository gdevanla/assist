package com.google.test.metric;


import com.google.test.metric.method.op.turing.Operation;
import com.google.test.metric.ClassInfo;
import java.util.List;
import java.lang.Integer;
import com.google.test.metric.ParameterInfo;
import java.util.Collections;
import com.google.test.metric.Visibility;
import com.google.test.metric.MethodInfo;
import junit.framework.TestCase;
import com.google.test.metric.LocalVariableInfo;
import java.util.Collection;
import static java.util.Collections.EMPTY_LIST;

public class FieldInfoGenTest extends TestCase {
	public ClassInfo instantiateClassInfo437() {
		List<ClassInfo> var2806 = Collections.emptyList();
		return new ClassInfo("super", false, null, var2806, null);
	}

	/**
	 * Test method for the class com.google.test.metric.FieldInfo
	 */
	public void testFieldInfo895() throws Exception {
		ClassInfo var2807 = instantiateClassInfo437();
		FieldInfo var2808 = new FieldInfo(null, "field", null, true, true,
				false);
		List<ClassInfo> var2806 = Collections.emptyList();
		ClassInfo var2809 = new ClassInfo("super", false, null, var2806, null);
		List<ParameterInfo> var2810 = Collections.emptyList();
		List<LocalVariableInfo> var2811 = Collections.emptyList();
		List<Operation> var2812 = Collections.emptyList();
		var2809.addMethod(new MethodInfo(var2809, "void setB()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "void setA()", -1, null,
				var2810, var2811, Visibility.PRIVATE, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "voidX()", -1, null, var2810,
				var2811, Visibility.PUBLIC, var2812, false, false, Collections
						.<Integer> emptyList()));
		ClassInfo var2813 = new ClassInfo("super", false, var2809, var2806,
				null);
		var2813.addMethod(new MethodInfo(var2813, "void setD()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2807.addMethod(new MethodInfo(var2813, "void setD()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "void setB()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "void setA()", -1, null,
				var2810, var2811, Visibility.PRIVATE, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "voidX()", -1, null, var2810,
				var2811, Visibility.PUBLIC, var2812, false, false, Collections
						.<Integer> emptyList()));
		var2813.addMethod(new MethodInfo(var2813, "void setD()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2813.addMethod(new MethodInfo(var2813, "void setC()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2807.addMethod(new MethodInfo(var2813, "void setC()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2807.getSetters();
		var2809.addMethod(new MethodInfo(var2809, "void setB()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2807.addMethod(new MethodInfo(var2809, "void setB()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "void setB()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "void setA()", -1, null,
				var2810, var2811, Visibility.PRIVATE, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2807.addMethod(new MethodInfo(var2809, "void setA()", -1, null,
				var2810, var2811, Visibility.PRIVATE, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "void setB()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "void setA()", -1, null,
				var2810, var2811, Visibility.PRIVATE, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "voidX()", -1, null, var2810,
				var2811, Visibility.PUBLIC, var2812, false, false, Collections
						.<Integer> emptyList()));
		var2807.addMethod(new MethodInfo(var2809, "voidX()", -1, null, var2810,
				var2811, Visibility.PUBLIC, var2812, false, false, Collections
						.<Integer> emptyList()));
	}

	public ClassInfo instantiateClassInfo438() {
		List<ClassInfo> var2806 = Collections.emptyList();
		ClassInfo var2809 = new ClassInfo("super", false, null, var2806, null);
		List<ParameterInfo> var2810 = Collections.emptyList();
		List<LocalVariableInfo> var2811 = Collections.emptyList();
		List<Operation> var2812 = Collections.emptyList();
		var2809.addMethod(new MethodInfo(var2809, "void setB()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "void setA()", -1, null,
				var2810, var2811, Visibility.PRIVATE, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "voidX()", -1, null, var2810,
				var2811, Visibility.PUBLIC, var2812, false, false, Collections
						.<Integer> emptyList()));
		return new ClassInfo("super", false, var2809, var2806, null);
	}

	/**
	 * Test method for the class com.google.test.metric.FieldInfo
	 */
	public void testFieldInfo896() throws Exception {
		ClassInfo var2814 = instantiateClassInfo438();
		FieldInfo var2815 = new FieldInfo(null, "field", null, false, false,
				false);
		List<ClassInfo> var2806 = Collections.emptyList();
		ClassInfo var2809 = new ClassInfo("super", false, null, var2806, null);
		List<ParameterInfo> var2810 = Collections.emptyList();
		List<LocalVariableInfo> var2811 = Collections.emptyList();
		List<Operation> var2812 = Collections.emptyList();
		var2809.addMethod(new MethodInfo(var2809, "void setB()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2814.addMethod(new MethodInfo(var2809, "void setB()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "void setB()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "void setA()", -1, null,
				var2810, var2811, Visibility.PRIVATE, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2814.addMethod(new MethodInfo(var2809, "void setA()", -1, null,
				var2810, var2811, Visibility.PRIVATE, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "void setB()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "void setA()", -1, null,
				var2810, var2811, Visibility.PRIVATE, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "voidX()", -1, null, var2810,
				var2811, Visibility.PUBLIC, var2812, false, false, Collections
						.<Integer> emptyList()));
		var2814.addMethod(new MethodInfo(var2809, "voidX()", -1, null, var2810,
				var2811, Visibility.PUBLIC, var2812, false, false, Collections
						.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "void setB()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "void setA()", -1, null,
				var2810, var2811, Visibility.PRIVATE, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "voidX()", -1, null, var2810,
				var2811, Visibility.PUBLIC, var2812, false, false, Collections
						.<Integer> emptyList()));
		ClassInfo var2813 = new ClassInfo("super", false, var2809, var2806,
				null);
		var2813.addMethod(new MethodInfo(var2813, "void setD()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2814.addMethod(new MethodInfo(var2813, "void setD()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "void setB()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "void setA()", -1, null,
				var2810, var2811, Visibility.PRIVATE, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "voidX()", -1, null, var2810,
				var2811, Visibility.PUBLIC, var2812, false, false, Collections
						.<Integer> emptyList()));
		var2813.addMethod(new MethodInfo(var2813, "void setD()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2813.addMethod(new MethodInfo(var2813, "void setC()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2814.addMethod(new MethodInfo(var2813, "void setC()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2814.getSetters();
	}

	public ClassInfo instantiateClassInfo439() {
		return new ClassInfo("TestClass", false, null, null, null);
	}

	/**
	 * Test method for the class com.google.test.metric.FieldInfo
	 */
	public void testFieldInfo897() throws Exception {
		ClassInfo var2816 = instantiateClassInfo439();
		FieldInfo var2817 = new FieldInfo(null, "dstField", null, false, false,
				false);
		List<ClassInfo> var2806 = Collections.emptyList();
		ClassInfo var2809 = new ClassInfo("super", false, null, var2806, null);
		List<ParameterInfo> var2810 = Collections.emptyList();
		List<LocalVariableInfo> var2811 = Collections.emptyList();
		List<Operation> var2812 = Collections.emptyList();
		var2809.addMethod(new MethodInfo(var2809, "void setB()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "void setA()", -1, null,
				var2810, var2811, Visibility.PRIVATE, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "voidX()", -1, null, var2810,
				var2811, Visibility.PUBLIC, var2812, false, false, Collections
						.<Integer> emptyList()));
		ClassInfo var2813 = new ClassInfo("super", false, var2809, var2806,
				null);
		var2813.addMethod(new MethodInfo(var2813, "void setD()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2816.addMethod(new MethodInfo(var2813, "void setD()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "void setB()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "void setA()", -1, null,
				var2810, var2811, Visibility.PRIVATE, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "voidX()", -1, null, var2810,
				var2811, Visibility.PUBLIC, var2812, false, false, Collections
						.<Integer> emptyList()));
		var2813.addMethod(new MethodInfo(var2813, "void setD()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2813.addMethod(new MethodInfo(var2813, "void setC()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2816.addMethod(new MethodInfo(var2813, "void setC()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2816.getSetters();
		var2809.addMethod(new MethodInfo(var2809, "void setB()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "void setA()", -1, null,
				var2810, var2811, Visibility.PRIVATE, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "voidX()", -1, null, var2810,
				var2811, Visibility.PUBLIC, var2812, false, false, Collections
						.<Integer> emptyList()));
		var2813.addMethod(new MethodInfo(var2813, "void setD()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2816.addMethod(new MethodInfo(var2813, "void setD()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "void setB()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "void setA()", -1, null,
				var2810, var2811, Visibility.PRIVATE, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "voidX()", -1, null, var2810,
				var2811, Visibility.PUBLIC, var2812, false, false, Collections
						.<Integer> emptyList()));
		var2813.addMethod(new MethodInfo(var2813, "void setD()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2813.addMethod(new MethodInfo(var2813, "void setC()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2816.addMethod(new MethodInfo(var2813, "void setC()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2816.getSetters();
	}

	public ClassInfo instantiateClassInfo440() {
		return new ClassInfo("c.g.t.A", false, null, EMPTY_LIST, null);
	}

	/**
	 * Test method for the class com.google.test.metric.FieldInfo
	 */
	public void testFieldInfo898() throws Exception {
		ClassInfo var2818 = instantiateClassInfo440();
		FieldInfo var2817 = new FieldInfo(null, "dstField", null, false, false,
				false);
		List<ClassInfo> var2806 = Collections.emptyList();
		ClassInfo var2809 = new ClassInfo("super", false, null, var2806, null);
		List<ParameterInfo> var2810 = Collections.emptyList();
		List<LocalVariableInfo> var2811 = Collections.emptyList();
		List<Operation> var2812 = Collections.emptyList();
		var2809.addMethod(new MethodInfo(var2809, "void setB()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "void setA()", -1, null,
				var2810, var2811, Visibility.PRIVATE, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "voidX()", -1, null, var2810,
				var2811, Visibility.PUBLIC, var2812, false, false, Collections
						.<Integer> emptyList()));
		ClassInfo var2813 = new ClassInfo("super", false, var2809, var2806,
				null);
		var2813.addMethod(new MethodInfo(var2813, "void setD()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2818.addMethod(new MethodInfo(var2813, "void setD()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "void setB()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "void setA()", -1, null,
				var2810, var2811, Visibility.PRIVATE, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "voidX()", -1, null, var2810,
				var2811, Visibility.PUBLIC, var2812, false, false, Collections
						.<Integer> emptyList()));
		var2813.addMethod(new MethodInfo(var2813, "void setD()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2813.addMethod(new MethodInfo(var2813, "void setC()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2818.addMethod(new MethodInfo(var2813, "void setC()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2818.getSetters();
		var2809.addMethod(new MethodInfo(var2809, "void setB()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2818.addMethod(new MethodInfo(var2809, "void setB()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "void setB()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "void setA()", -1, null,
				var2810, var2811, Visibility.PRIVATE, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2818.addMethod(new MethodInfo(var2809, "void setA()", -1, null,
				var2810, var2811, Visibility.PRIVATE, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "void setB()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "void setA()", -1, null,
				var2810, var2811, Visibility.PRIVATE, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "voidX()", -1, null, var2810,
				var2811, Visibility.PUBLIC, var2812, false, false, Collections
						.<Integer> emptyList()));
		var2818.addMethod(new MethodInfo(var2809, "voidX()", -1, null, var2810,
				var2811, Visibility.PUBLIC, var2812, false, false, Collections
						.<Integer> emptyList()));
	}

	public ClassInfo instantiateClassInfo441() {
		List<ClassInfo> var2806 = Collections.emptyList();
		return new ClassInfo("super", false, null, var2806, null);
	}

	/**
	 * Test method for the class com.google.test.metric.FieldInfo
	 */
	public void testFieldInfo899() throws Exception {
		ClassInfo var2819 = instantiateClassInfo441();
		FieldInfo var2815 = new FieldInfo(null, "field", null, false, false,
				false);
		List<ClassInfo> var2806 = Collections.emptyList();
		ClassInfo var2809 = new ClassInfo("super", false, null, var2806, null);
		List<ParameterInfo> var2810 = Collections.emptyList();
		List<LocalVariableInfo> var2811 = Collections.emptyList();
		List<Operation> var2812 = Collections.emptyList();
		var2809.addMethod(new MethodInfo(var2809, "void setB()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2819.addMethod(new MethodInfo(var2809, "void setB()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "void setB()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "void setA()", -1, null,
				var2810, var2811, Visibility.PRIVATE, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2819.addMethod(new MethodInfo(var2809, "void setA()", -1, null,
				var2810, var2811, Visibility.PRIVATE, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "void setB()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "void setA()", -1, null,
				var2810, var2811, Visibility.PRIVATE, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "voidX()", -1, null, var2810,
				var2811, Visibility.PUBLIC, var2812, false, false, Collections
						.<Integer> emptyList()));
		var2819.addMethod(new MethodInfo(var2809, "voidX()", -1, null, var2810,
				var2811, Visibility.PUBLIC, var2812, false, false, Collections
						.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "void setB()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "void setA()", -1, null,
				var2810, var2811, Visibility.PRIVATE, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "voidX()", -1, null, var2810,
				var2811, Visibility.PUBLIC, var2812, false, false, Collections
						.<Integer> emptyList()));
		ClassInfo var2813 = new ClassInfo("super", false, var2809, var2806,
				null);
		var2813.addMethod(new MethodInfo(var2813, "void setD()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2819.addMethod(new MethodInfo(var2813, "void setD()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "void setB()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "void setA()", -1, null,
				var2810, var2811, Visibility.PRIVATE, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2809.addMethod(new MethodInfo(var2809, "voidX()", -1, null, var2810,
				var2811, Visibility.PUBLIC, var2812, false, false, Collections
						.<Integer> emptyList()));
		var2813.addMethod(new MethodInfo(var2813, "void setD()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2813.addMethod(new MethodInfo(var2813, "void setC()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2819.addMethod(new MethodInfo(var2813, "void setC()", -1, null,
				var2810, var2811, Visibility.PUBLIC, var2812, false, false,
				Collections.<Integer> emptyList()));
		var2819.getSetters();
	}
}