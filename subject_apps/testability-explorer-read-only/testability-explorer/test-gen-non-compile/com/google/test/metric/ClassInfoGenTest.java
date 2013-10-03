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

public class ClassInfoGenTest extends TestCase {
	public ClassInfo instantiateClassInfo432() {
		List<ClassInfo> var2790 = Collections.emptyList();
		ClassInfo var2791 = new ClassInfo("super", false, null, var2790, null);
		List<ParameterInfo> var2792 = Collections.emptyList();
		List<LocalVariableInfo> var2793 = Collections.emptyList();
		List<Operation> var2794 = Collections.emptyList();
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		return new ClassInfo("super", false, var2791, var2790, null);
	}

	/**
	 * Test method for the class com.google.test.metric.ClassInfo
	 */
	public void testClassInfo890() throws Exception {
		ClassInfo var2795 = instantiateClassInfo432();
		List<ClassInfo> var2790 = Collections.emptyList();
		List<ParameterInfo> var2792 = Collections.emptyList();
		List<LocalVariableInfo> var2793 = Collections.emptyList();
		List<Operation> var2794 = Collections.emptyList();
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		ClassInfo var2796 = new ClassInfo("super", false, var2795, var2790,
				null);
		ClassInfo var2791 = new ClassInfo("super", false, null, var2790, null);
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2795.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2795.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		var2795.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2795.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2795.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		var2795.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2796.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2796.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		var2796.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		ClassInfo var2797 = new ClassInfo("super", false, var2791, var2790,
				null);
		var2797.addMethod(new MethodInfo(var2797, "void setD()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2796.addMethod(new MethodInfo(var2797, "void setD()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		var2797.addMethod(new MethodInfo(var2797, "void setD()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2797.addMethod(new MethodInfo(var2797, "void setC()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2796.addMethod(new MethodInfo(var2797, "void setC()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2796.getSetters();
	}

	public ClassInfo instantiateClassInfo433() {
		return new ClassInfo("TestClass", false, null, null, null);
	}

	/**
	 * Test method for the class com.google.test.metric.ClassInfo
	 */
	public void testClassInfo891() throws Exception {
		ClassInfo var2798 = instantiateClassInfo433();
		List<ClassInfo> var2790 = Collections.emptyList();
		List<ParameterInfo> var2792 = Collections.emptyList();
		List<LocalVariableInfo> var2793 = Collections.emptyList();
		List<Operation> var2794 = Collections.emptyList();
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		ClassInfo var2799 = new ClassInfo("super", false, var2798, var2790,
				null);
		ClassInfo var2791 = new ClassInfo("super", false, null, var2790, null);
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		ClassInfo var2797 = new ClassInfo("super", false, var2791, var2790,
				null);
		var2797.addMethod(new MethodInfo(var2797, "void setD()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2798.addMethod(new MethodInfo(var2797, "void setD()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		var2797.addMethod(new MethodInfo(var2797, "void setD()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2797.addMethod(new MethodInfo(var2797, "void setC()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2798.addMethod(new MethodInfo(var2797, "void setC()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2798.getSetters();
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		var2797.addMethod(new MethodInfo(var2797, "void setD()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2798.addMethod(new MethodInfo(var2797, "void setD()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		var2797.addMethod(new MethodInfo(var2797, "void setD()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2797.addMethod(new MethodInfo(var2797, "void setC()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2798.addMethod(new MethodInfo(var2797, "void setC()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2798.getSetters();
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2799.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2799.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		var2799.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		var2797.addMethod(new MethodInfo(var2797, "void setD()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2799.addMethod(new MethodInfo(var2797, "void setD()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		var2797.addMethod(new MethodInfo(var2797, "void setD()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2797.addMethod(new MethodInfo(var2797, "void setC()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2799.addMethod(new MethodInfo(var2797, "void setC()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2799.getSetters();
	}

	public ClassInfo instantiateClassInfo434() {
		return new ClassInfo("c.g.t.A", false, null, EMPTY_LIST, null);
	}

	/**
	 * Test method for the class com.google.test.metric.ClassInfo
	 */
	public void testClassInfo892() throws Exception {
		ClassInfo var2800 = instantiateClassInfo434();
		ClassInfo var2801 = new ClassInfo("TestClass", false, null, null, null);
		List<ClassInfo> var2790 = Collections.emptyList();
		ClassInfo var2791 = new ClassInfo("super", false, null, var2790, null);
		List<ParameterInfo> var2792 = Collections.emptyList();
		List<LocalVariableInfo> var2793 = Collections.emptyList();
		List<Operation> var2794 = Collections.emptyList();
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2800.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2800.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		var2800.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2800.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2800.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		var2800.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		ClassInfo var2797 = new ClassInfo("super", false, var2791, var2790,
				null);
		var2797.addMethod(new MethodInfo(var2797, "void setD()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2801.addMethod(new MethodInfo(var2797, "void setD()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		var2797.addMethod(new MethodInfo(var2797, "void setD()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2797.addMethod(new MethodInfo(var2797, "void setC()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2801.addMethod(new MethodInfo(var2797, "void setC()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2801.getSetters();
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		var2797.addMethod(new MethodInfo(var2797, "void setD()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2801.addMethod(new MethodInfo(var2797, "void setD()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		var2797.addMethod(new MethodInfo(var2797, "void setD()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2797.addMethod(new MethodInfo(var2797, "void setC()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2801.addMethod(new MethodInfo(var2797, "void setC()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2801.getSetters();
	}

	public ClassInfo instantiateClassInfo435() {
		List<ClassInfo> var2790 = Collections.emptyList();
		return new ClassInfo("super", false, null, var2790, null);
	}

	/**
	 * Test method for the class com.google.test.metric.ClassInfo
	 */
	public void testClassInfo893() throws Exception {
		ClassInfo var2802 = instantiateClassInfo435();
		List<ClassInfo> var2790 = Collections.emptyList();
		List<ParameterInfo> var2792 = Collections.emptyList();
		List<LocalVariableInfo> var2793 = Collections.emptyList();
		List<Operation> var2794 = Collections.emptyList();
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		ClassInfo var2803 = new ClassInfo("super", false, var2802, var2790,
				null);
		ClassInfo var2791 = new ClassInfo("super", false, null, var2790, null);
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		ClassInfo var2797 = new ClassInfo("super", false, var2791, var2790,
				null);
		var2797.addMethod(new MethodInfo(var2797, "void setD()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2802.addMethod(new MethodInfo(var2797, "void setD()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		var2797.addMethod(new MethodInfo(var2797, "void setD()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2797.addMethod(new MethodInfo(var2797, "void setC()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2802.addMethod(new MethodInfo(var2797, "void setC()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2802.getSetters();
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		var2797.addMethod(new MethodInfo(var2797, "void setD()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2802.addMethod(new MethodInfo(var2797, "void setD()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		var2797.addMethod(new MethodInfo(var2797, "void setD()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2797.addMethod(new MethodInfo(var2797, "void setC()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2802.addMethod(new MethodInfo(var2797, "void setC()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2802.getSetters();
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		var2797.addMethod(new MethodInfo(var2797, "void setD()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2803.addMethod(new MethodInfo(var2797, "void setD()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		var2797.addMethod(new MethodInfo(var2797, "void setD()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2797.addMethod(new MethodInfo(var2797, "void setC()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2803.addMethod(new MethodInfo(var2797, "void setC()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2803.getSetters();
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		var2797.addMethod(new MethodInfo(var2797, "void setD()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2803.addMethod(new MethodInfo(var2797, "void setD()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		var2797.addMethod(new MethodInfo(var2797, "void setD()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2797.addMethod(new MethodInfo(var2797, "void setC()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2803.addMethod(new MethodInfo(var2797, "void setC()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2803.getSetters();
	}

	public ClassInfo instantiateClassInfo436() {
		return new ClassInfo("c.g.t.A", false, null, EMPTY_LIST, null);
	}

	/**
	 * Test method for the class com.google.test.metric.ClassInfo
	 */
	public void testClassInfo894() throws Exception {
		ClassInfo var2804 = instantiateClassInfo436();
		List<ClassInfo> var2790 = Collections.emptyList();
		List<ParameterInfo> var2792 = Collections.emptyList();
		List<LocalVariableInfo> var2793 = Collections.emptyList();
		List<Operation> var2794 = Collections.emptyList();
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		ClassInfo var2805 = new ClassInfo("super", false, var2804, var2790,
				null);
		ClassInfo var2791 = new ClassInfo("super", false, null, var2790, null);
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2804.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2804.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		var2804.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2804.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2804.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		var2804.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2805.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2805.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		var2805.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		ClassInfo var2797 = new ClassInfo("super", false, var2791, var2790,
				null);
		var2797.addMethod(new MethodInfo(var2797, "void setD()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2805.addMethod(new MethodInfo(var2797, "void setD()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setB()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "void setA()", -1, null,
				var2792, var2793, Visibility.PRIVATE, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2791.addMethod(new MethodInfo(var2791, "voidX()", -1, null, var2792,
				var2793, Visibility.PUBLIC, var2794, false, false, Collections
						.<Integer> emptyList()));
		var2797.addMethod(new MethodInfo(var2797, "void setD()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2797.addMethod(new MethodInfo(var2797, "void setC()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2805.addMethod(new MethodInfo(var2797, "void setC()", -1, null,
				var2792, var2793, Visibility.PUBLIC, var2794, false, false,
				Collections.<Integer> emptyList()));
		var2805.getSetters();
	}
}