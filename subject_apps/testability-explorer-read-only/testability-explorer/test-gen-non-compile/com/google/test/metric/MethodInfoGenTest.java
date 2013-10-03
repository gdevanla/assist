package com.google.test.metric;


import com.google.test.metric.method.op.turing.Operation;
import com.google.test.metric.Variable;
import java.util.List;
import com.google.test.metric.ClassInfo;
import java.lang.String;
import java.util.Collections;
import com.google.test.metric.ParameterInfo;
import java.lang.Integer;
import com.google.test.metric.Visibility;
import com.google.test.metric.JavaType;
import junit.framework.TestCase;
import com.google.test.metric.FieldInfo;
import com.google.test.metric.LocalField;
import com.google.test.metric.Type;
import com.google.test.metric.MethodInfo;
import com.google.test.metric.LocalVariableInfo;
import java.util.Collection;
import static java.util.Collections.EMPTY_LIST;
import static com.google.test.metric.JavaType.VOID;

public class MethodInfoGenTest extends TestCase {
	public LocalVariableInfo instantiateLocalVariableInfo423() {
		return new LocalVariableInfo("e", JavaType.OBJECT);
	}

	public ClassInfo instantiateClassInfo424() {
		List<ClassInfo> var2764 = Collections.emptyList();
		ClassInfo var2765 = new ClassInfo("super", false, null, var2764, null);
		List<ParameterInfo> var2766 = Collections.emptyList();
		List<LocalVariableInfo> var2767 = Collections.emptyList();
		List<Operation> var2768 = Collections.emptyList();
		var2765.addMethod(new MethodInfo(var2765, "void setB()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "void setA()", -1, null,
				var2766, var2767, Visibility.PRIVATE, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "voidX()", -1, null, var2766,
				var2767, Visibility.PUBLIC, var2768, false, false, Collections
						.<Integer> emptyList()));
		return new ClassInfo("super", false, var2765, var2764, null);
	}

	/**
	 * Test method for the class com.google.test.metric.MethodInfo
	 */
	public void testMethodInfo885() throws Exception {
		ClassInfo var2769 = instantiateClassInfo424();
		LocalVariableInfo var2770 = instantiateLocalVariableInfo423();
		List<ClassInfo> var2764 = Collections.emptyList();
		List<ParameterInfo> var2766 = Collections.emptyList();
		List<LocalVariableInfo> var2767 = Collections.emptyList();
		List<Operation> var2768 = Collections.emptyList();
		var2765.addMethod(new MethodInfo(var2765, "void setB()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "void setA()", -1, null,
				var2766, var2767, Visibility.PRIVATE, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "voidX()", -1, null, var2766,
				var2767, Visibility.PUBLIC, var2768, false, false, Collections
						.<Integer> emptyList()));
		var2771.addMethod(new MethodInfo(var2771, "void setD()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2771.addMethod(new MethodInfo(var2771, "void setC()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		MethodInfo var2772 = new MethodInfo(var2769, "void setC()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList());
		ClassInfo var2765 = new ClassInfo("super", false, null, var2764, null);
		var2765.addMethod(new MethodInfo(var2765, "void setB()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "void setA()", -1, null,
				var2766, var2767, Visibility.PRIVATE, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "voidX()", -1, null, var2766,
				var2767, Visibility.PUBLIC, var2768, false, false, Collections
						.<Integer> emptyList()));
		ClassInfo var2771 = new ClassInfo("super", false, var2765, var2764,
				null);
		var2771.addMethod(new MethodInfo(var2771, "void setD()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2769.addMethod(new MethodInfo(var2771, "void setD()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "void setB()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "void setA()", -1, null,
				var2766, var2767, Visibility.PRIVATE, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "voidX()", -1, null, var2766,
				var2767, Visibility.PUBLIC, var2768, false, false, Collections
						.<Integer> emptyList()));
		var2771.addMethod(new MethodInfo(var2771, "void setD()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2771.addMethod(new MethodInfo(var2771, "void setC()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2769.addMethod(new MethodInfo(var2771, "void setC()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2769.getSetters();
		var2765.addMethod(new MethodInfo(var2765, "void setB()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "void setA()", -1, null,
				var2766, var2767, Visibility.PRIVATE, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "voidX()", -1, null, var2766,
				var2767, Visibility.PUBLIC, var2768, false, false, Collections
						.<Integer> emptyList()));
		var2771.addMethod(new MethodInfo(var2771, "void setD()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2769.addMethod(new MethodInfo(var2771, "void setD()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "void setB()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "void setA()", -1, null,
				var2766, var2767, Visibility.PRIVATE, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "voidX()", -1, null, var2766,
				var2767, Visibility.PUBLIC, var2768, false, false, Collections
						.<Integer> emptyList()));
		var2771.addMethod(new MethodInfo(var2771, "void setD()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2771.addMethod(new MethodInfo(var2771, "void setC()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2769.addMethod(new MethodInfo(var2771, "void setC()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2769.getSetters();
		var2772.isSetter();
		var2772.isSetter();
	}

	public LocalField instantiateLocalField425() {
		Variable var2774 = new Variable("var", null, false, false);
		FieldInfo var2775 = new FieldInfo(null, "field", null, false, false,
				false);
		return new LocalField(var2774, var2775);
	}

	public ClassInfo instantiateClassInfo426() {
		List<ClassInfo> var2764 = Collections.emptyList();
		ClassInfo var2765 = new ClassInfo("super", false, null, var2764, null);
		List<ParameterInfo> var2766 = Collections.emptyList();
		List<LocalVariableInfo> var2767 = Collections.emptyList();
		List<Operation> var2768 = Collections.emptyList();
		var2765.addMethod(new MethodInfo(var2765, "void setB()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "void setA()", -1, null,
				var2766, var2767, Visibility.PRIVATE, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "voidX()", -1, null, var2766,
				var2767, Visibility.PUBLIC, var2768, false, false, Collections
						.<Integer> emptyList()));
		return new ClassInfo("super", false, var2765, var2764, null);
	}

	/**
	 * Test method for the class com.google.test.metric.MethodInfo
	 */
	public void testMethodInfo886() throws Exception {
		ClassInfo var2776 = instantiateClassInfo426();
		LocalField var2777 = instantiateLocalField425();
		List<ClassInfo> var2764 = Collections.emptyList();
		List<ParameterInfo> var2766 = Collections.emptyList();
		List<LocalVariableInfo> var2767 = Collections.emptyList();
		List<Operation> var2768 = Collections.emptyList();
		var2765.addMethod(new MethodInfo(var2765, "void setB()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		MethodInfo var2778 = new MethodInfo(var2776, "void setB()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList());
		var2777.getDescription();
		var2777.getDescription();
		ClassInfo var2765 = new ClassInfo("super", false, null, var2764, null);
		var2765.addMethod(new MethodInfo(var2765, "void setB()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "void setA()", -1, null,
				var2766, var2767, Visibility.PRIVATE, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "voidX()", -1, null, var2766,
				var2767, Visibility.PUBLIC, var2768, false, false, Collections
						.<Integer> emptyList()));
		ClassInfo var2771 = new ClassInfo("super", false, var2765, var2764,
				null);
		var2771.addMethod(new MethodInfo(var2771, "void setD()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2776.addMethod(new MethodInfo(var2771, "void setD()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "void setB()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "void setA()", -1, null,
				var2766, var2767, Visibility.PRIVATE, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "voidX()", -1, null, var2766,
				var2767, Visibility.PUBLIC, var2768, false, false, Collections
						.<Integer> emptyList()));
		var2771.addMethod(new MethodInfo(var2771, "void setD()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2771.addMethod(new MethodInfo(var2771, "void setC()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2776.addMethod(new MethodInfo(var2771, "void setC()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2776.getSetters();
		var2765.addMethod(new MethodInfo(var2765, "void setB()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "void setA()", -1, null,
				var2766, var2767, Visibility.PRIVATE, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "voidX()", -1, null, var2766,
				var2767, Visibility.PUBLIC, var2768, false, false, Collections
						.<Integer> emptyList()));
		var2771.addMethod(new MethodInfo(var2771, "void setD()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2776.addMethod(new MethodInfo(var2771, "void setD()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "void setB()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "void setA()", -1, null,
				var2766, var2767, Visibility.PRIVATE, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "voidX()", -1, null, var2766,
				var2767, Visibility.PUBLIC, var2768, false, false, Collections
						.<Integer> emptyList()));
		var2771.addMethod(new MethodInfo(var2771, "void setD()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2771.addMethod(new MethodInfo(var2771, "void setC()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2776.addMethod(new MethodInfo(var2771, "void setC()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2776.getSetters();
		var2778.isSetter();
		var2778.isSetter();
	}

	public LocalField instantiateLocalField427() {
		Variable var2780 = new Variable("instance", null, false, false);
		FieldInfo var2781 = new FieldInfo(null, "field", null, false, false,
				false);
		return new LocalField(var2780, var2781);
	}

	public ClassInfo instantiateClassInfo428() {
		List<ClassInfo> var2764 = Collections.emptyList();
		return new ClassInfo("super", false, null, var2764, null);
	}

	/**
	 * Test method for the class com.google.test.metric.MethodInfo
	 */
	public void testMethodInfo887() throws Exception {
		ClassInfo var2782 = instantiateClassInfo428();
		LocalField var2783 = instantiateLocalField427();
		List<ClassInfo> var2764 = Collections.emptyList();
		List<ParameterInfo> var2766 = Collections.emptyList();
		List<LocalVariableInfo> var2767 = Collections.emptyList();
		List<Operation> var2768 = Collections.emptyList();
		var2765.addMethod(new MethodInfo(var2765, "void setB()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "void setA()", -1, null,
				var2766, var2767, Visibility.PRIVATE, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "voidX()", -1, null, var2766,
				var2767, Visibility.PUBLIC, var2768, false, false, Collections
						.<Integer> emptyList()));
		MethodInfo var2784 = new MethodInfo(var2782, "voidX()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList());
		var2783.getDescription();
		var2783.getDescription();
		ClassInfo var2765 = new ClassInfo("super", false, null, var2764, null);
		var2765.addMethod(new MethodInfo(var2765, "void setB()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "void setA()", -1, null,
				var2766, var2767, Visibility.PRIVATE, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "voidX()", -1, null, var2766,
				var2767, Visibility.PUBLIC, var2768, false, false, Collections
						.<Integer> emptyList()));
		ClassInfo var2771 = new ClassInfo("super", false, var2765, var2764,
				null);
		var2771.addMethod(new MethodInfo(var2771, "void setD()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2782.addMethod(new MethodInfo(var2771, "void setD()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "void setB()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "void setA()", -1, null,
				var2766, var2767, Visibility.PRIVATE, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "voidX()", -1, null, var2766,
				var2767, Visibility.PUBLIC, var2768, false, false, Collections
						.<Integer> emptyList()));
		var2771.addMethod(new MethodInfo(var2771, "void setD()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2771.addMethod(new MethodInfo(var2771, "void setC()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2782.addMethod(new MethodInfo(var2771, "void setC()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2782.getSetters();
		var2765.addMethod(new MethodInfo(var2765, "void setB()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2782.addMethod(new MethodInfo(var2765, "void setB()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "void setB()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "void setA()", -1, null,
				var2766, var2767, Visibility.PRIVATE, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2782.addMethod(new MethodInfo(var2765, "void setA()", -1, null,
				var2766, var2767, Visibility.PRIVATE, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "void setB()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "void setA()", -1, null,
				var2766, var2767, Visibility.PRIVATE, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "voidX()", -1, null, var2766,
				var2767, Visibility.PUBLIC, var2768, false, false, Collections
						.<Integer> emptyList()));
		var2782.addMethod(new MethodInfo(var2765, "voidX()", -1, null, var2766,
				var2767, Visibility.PUBLIC, var2768, false, false, Collections
						.<Integer> emptyList()));
		var2784.isSetter();
		var2784.isSetter();
	}

	public FieldInfo instantiateFieldInfo429() {
		return new FieldInfo(null, "field", null, false, false, false);
	}

	public ClassInfo instantiateClassInfo430() {
		return new ClassInfo("TestClass", false, null, null, null);
	}

	/**
	 * Test method for the class com.google.test.metric.MethodInfo
	 */
	public void testMethodInfo888() throws Exception {
		ClassInfo var2785 = instantiateClassInfo430();
		FieldInfo var2786 = instantiateFieldInfo429();
		List<ClassInfo> var2764 = Collections.emptyList();
		List<ParameterInfo> var2766 = Collections.emptyList();
		List<LocalVariableInfo> var2767 = Collections.emptyList();
		List<Operation> var2768 = Collections.emptyList();
		var2765.addMethod(new MethodInfo(var2765, "void setB()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		MethodInfo var2787 = new MethodInfo(var2785, "void setB()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList());
		ClassInfo var2765 = new ClassInfo("super", false, null, var2764, null);
		var2765.addMethod(new MethodInfo(var2765, "void setB()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2785.addMethod(new MethodInfo(var2765, "void setB()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "void setB()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "void setA()", -1, null,
				var2766, var2767, Visibility.PRIVATE, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2785.addMethod(new MethodInfo(var2765, "void setA()", -1, null,
				var2766, var2767, Visibility.PRIVATE, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "void setB()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "void setA()", -1, null,
				var2766, var2767, Visibility.PRIVATE, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "voidX()", -1, null, var2766,
				var2767, Visibility.PUBLIC, var2768, false, false, Collections
						.<Integer> emptyList()));
		var2785.addMethod(new MethodInfo(var2765, "voidX()", -1, null, var2766,
				var2767, Visibility.PUBLIC, var2768, false, false, Collections
						.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "void setB()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "void setA()", -1, null,
				var2766, var2767, Visibility.PRIVATE, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "voidX()", -1, null, var2766,
				var2767, Visibility.PUBLIC, var2768, false, false, Collections
						.<Integer> emptyList()));
		ClassInfo var2771 = new ClassInfo("super", false, var2765, var2764,
				null);
		var2771.addMethod(new MethodInfo(var2771, "void setD()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2785.addMethod(new MethodInfo(var2771, "void setD()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "void setB()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "void setA()", -1, null,
				var2766, var2767, Visibility.PRIVATE, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "voidX()", -1, null, var2766,
				var2767, Visibility.PUBLIC, var2768, false, false, Collections
						.<Integer> emptyList()));
		var2771.addMethod(new MethodInfo(var2771, "void setD()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2771.addMethod(new MethodInfo(var2771, "void setC()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2785.addMethod(new MethodInfo(var2771, "void setC()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2785.getSetters();
		var2787.isSetter();
		var2787.isSetter();
	}

	public ClassInfo instantiateClassInfo431() {
		return new ClassInfo("c.g.t.A", false, null, EMPTY_LIST, null);
	}

	/**
	 * Test method for the class com.google.test.metric.MethodInfo
	 */
	public void testMethodInfo889() throws Exception {
		ClassInfo var2788 = instantiateClassInfo431();
		List<ClassInfo> var2764 = Collections.emptyList();
		List<ParameterInfo> var2766 = Collections.emptyList();
		List<LocalVariableInfo> var2767 = Collections.emptyList();
		List<Operation> var2768 = Collections.emptyList();
		var2765.addMethod(new MethodInfo(var2765, "void setB()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "void setA()", -1, null,
				var2766, var2767, Visibility.PRIVATE, var2768, false, false,
				Collections.<Integer> emptyList()));
		MethodInfo var2789 = new MethodInfo(var2788, "void setA()", -1, null,
				var2766, var2767, Visibility.PRIVATE, var2768, false, false,
				Collections.<Integer> emptyList());
		ClassInfo var2765 = new ClassInfo("super", false, null, var2764, null);
		var2765.addMethod(new MethodInfo(var2765, "void setB()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "void setA()", -1, null,
				var2766, var2767, Visibility.PRIVATE, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "voidX()", -1, null, var2766,
				var2767, Visibility.PUBLIC, var2768, false, false, Collections
						.<Integer> emptyList()));
		ClassInfo var2771 = new ClassInfo("super", false, var2765, var2764,
				null);
		var2771.addMethod(new MethodInfo(var2771, "void setD()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2788.addMethod(new MethodInfo(var2771, "void setD()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "void setB()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "void setA()", -1, null,
				var2766, var2767, Visibility.PRIVATE, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "voidX()", -1, null, var2766,
				var2767, Visibility.PUBLIC, var2768, false, false, Collections
						.<Integer> emptyList()));
		var2771.addMethod(new MethodInfo(var2771, "void setD()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2771.addMethod(new MethodInfo(var2771, "void setC()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2788.addMethod(new MethodInfo(var2771, "void setC()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2788.getSetters();
		var2765.addMethod(new MethodInfo(var2765, "void setB()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "void setA()", -1, null,
				var2766, var2767, Visibility.PRIVATE, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "voidX()", -1, null, var2766,
				var2767, Visibility.PUBLIC, var2768, false, false, Collections
						.<Integer> emptyList()));
		var2771.addMethod(new MethodInfo(var2771, "void setD()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2788.addMethod(new MethodInfo(var2771, "void setD()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "void setB()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "void setA()", -1, null,
				var2766, var2767, Visibility.PRIVATE, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2765.addMethod(new MethodInfo(var2765, "voidX()", -1, null, var2766,
				var2767, Visibility.PUBLIC, var2768, false, false, Collections
						.<Integer> emptyList()));
		var2771.addMethod(new MethodInfo(var2771, "void setD()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2771.addMethod(new MethodInfo(var2771, "void setC()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2788.addMethod(new MethodInfo(var2771, "void setC()", -1, null,
				var2766, var2767, Visibility.PUBLIC, var2768, false, false,
				Collections.<Integer> emptyList()));
		var2788.getSetters();
		var2789.isSetter();
		var2789.isSetter();
	}
}