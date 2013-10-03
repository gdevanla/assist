package com.google.test.metric;


import com.google.test.metric.RegExpWhiteList;
import com.google.test.metric.Variable;
import com.google.test.metric.ClassInfo;
import com.google.classpath.ClassPathFactory;
import junit.framework.TestCase;
import com.google.test.metric.TestabilityVisitor.CostRecordingFrame;
import java.lang.Class;
import com.google.classpath.ClassPath;
import com.google.test.metric.LocalVariableState;
import java.util.List;
import com.google.test.metric.JavaClassRepository;
import com.google.test.metric.ParameterInfo;
import java.lang.String;
import com.google.test.metric.Obj;
import com.google.test.metric.javasrc.JavaSrcRepository;
import com.google.test.metric.FieldInfo;
import com.google.test.metric.VariableState;
import com.google.test.metric.MethodInfo;
import com.google.test.metric.ClassRepository;
import static java.util.Collections.EMPTY_LIST;

public class TestabilityVisitorGenTest extends TestCase {
	public LocalVariableState instantiateLocalVariableState467() {
		VariableState var2949 = new VariableState();
		return new LocalVariableState(var2949);
	}

	public RegExpWhiteList instantiateRegExpWhiteList468() {
		return new RegExpWhiteList("java.");
	}

	public JavaClassRepository instantiateJavaClassRepository469() {
		return new JavaClassRepository();
	}

	/**
	 * Test method for the class com.google.test.metric.TestabilityVisitor
	 */
	public void testTestabilityVisitor955() throws Exception {
		RegExpWhiteList var2950 = instantiateRegExpWhiteList468();
		LocalVariableState var2951 = instantiateLocalVariableState467();
		JavaClassRepository var2952 = instantiateJavaClassRepository469();
		VariableState var2953 = new VariableState();
		TestabilityVisitor var2954 = new TestabilityVisitor(var2952, var2953,
				null, var2950);
		var2950.isClassWhiteListed("java.lang.String");
		var2950.isClassWhiteListed("com.company.String");
		var2950.addPackage("javax.");
		Variable var2957 = new Variable("lod", null, false, false);
		var2951.setLoDCount(var2957, 123);
		var2951.getLoDCount(var2957);
		Variable var2959 = new Variable("var", null, false, false);
		var2951.getLoDCount(var2959);
		var2951.setLoDCount(var2957, 123);
		var2951.getLoDCount(var2957);
		var2951.getLoDCount(var2959);
		var2952.getClass(Monitor.class.getCanonicalName());
		var2952.getClass(SingleMethodClass.class.getCanonicalName());
		JavaClassRepository var2961 = new JavaClassRepository();
		ClassInfo var2962 = var2961.getClass(LoDMultipleSameInvocations.class
				.getCanonicalName());
		MethodInfo var2963 = var2962.getMethod(method("execute", Obj.class));
		var2954.createFrame(var2963, 1);
		ClassInfo var2965 = var2961
				.getClass(LoDMultipleDifferentInvocations.class
						.getCanonicalName());
		MethodInfo var2966 = var2965.getMethod(method("execute", Obj.class));
		var2954.createFrame(var2966, 1);
	}

	public LocalVariableState instantiateLocalVariableState470() {
		VariableState var2949 = new VariableState();
		return new LocalVariableState(var2949);
	}

	public RegExpWhiteList instantiateRegExpWhiteList471() {
		return new RegExpWhiteList("java.");
	}

	public JavaSrcRepository instantiateJavaSrcRepository472() {
		ClassRepository var2967 = new JavaClassRepository();
		return new JavaSrcRepository(var2967,
				new ClassPathFactory().createFromPaths("core/src/test/java",
						"src/test/java"));
	}

	/**
	 * Test method for the class com.google.test.metric.TestabilityVisitor
	 */
	public void testTestabilityVisitor956() throws Exception {
		RegExpWhiteList var2968 = instantiateRegExpWhiteList471();
		LocalVariableState var2969 = instantiateLocalVariableState470();
		JavaSrcRepository var2970 = instantiateJavaSrcRepository472();
		JavaClassRepository var2961 = new JavaClassRepository();
		VariableState var2953 = new VariableState();
		TestabilityVisitor var2971 = new TestabilityVisitor(var2961, var2953,
				null, var2968);
		var2968.isClassWhiteListed("java.lang.String");
		var2968.isClassWhiteListed("com.company.String");
		var2968.isClassWhiteListed("java.lang.String");
		var2968.isClassWhiteListed("java.x.Z");
		Variable var2957 = new Variable("lod", null, false, false);
		var2969.setLoDCount(var2957, 123);
		var2969.getLoDCount(var2957);
		Variable var2959 = new Variable("var", null, false, false);
		var2969.getLoDCount(var2959);
		var2969.setGlobal(var2959);
		var2969.setInjectable(var2959);
		var2969.isGlobal(var2959);
		var2969.isInjectable(var2959);
		var2970.getClass(ClassExtendsAndImplements.class.getCanonicalName());
		var2970.getClass(TypeQualifications.class.getName());
		var2970.getCachedClass(TypeQualifications.class.getName());
		var2970.getClass(My.class.getName());
		ClassInfo var2974 = var2961.getClass(MultipleInjectability.class
				.getCanonicalName());
		MethodInfo var2975 = var2974.getMethod(method("execute", Obj.class));
		var2975.getParameters();
		var2971.createFrame(var2975, 1);
		ClassInfo var2962 = var2961.getClass(LoDMultipleSameInvocations.class
				.getCanonicalName());
		MethodInfo var2963 = var2962.getMethod(method("execute", Obj.class));
		var2971.createFrame(var2963, 1);
	}

	public LocalVariableState instantiateLocalVariableState473() {
		VariableState var2949 = new VariableState();
		return new LocalVariableState(var2949);
	}

	public RegExpWhiteList instantiateRegExpWhiteList474() {
		return new RegExpWhiteList("java.lang");
	}

	public JavaClassRepository instantiateJavaClassRepository475() {
		ClassPath var2976 = new ClassPathFactory().createFromJVM();
		return new JavaClassRepository(var2976);
	}

	/**
	 * Test method for the class com.google.test.metric.TestabilityVisitor
	 */
	public void testTestabilityVisitor957() throws Exception {
		RegExpWhiteList var2977 = instantiateRegExpWhiteList474();
		LocalVariableState var2978 = instantiateLocalVariableState473();
		JavaClassRepository var2979 = instantiateJavaClassRepository475();
		VariableState var2953 = new VariableState();
		TestabilityVisitor var2980 = new TestabilityVisitor(var2979, var2953,
				null, var2977);
		var2977.addPackage("javax.");
		var2977.isClassWhiteListed("java.lang.String");
		var2977.isClassWhiteListed("com.company.String");
		FieldInfo var2981 = new FieldInfo(null, "field", null, false, false,
				false);
		var2978.setGlobal(var2981);
		var2978.setInjectable(var2981);
		Variable var2959 = new Variable("var", null, false, false);
		var2978.setGlobal(var2959);
		var2978.setInjectable(var2981);
		Variable var2957 = new Variable("lod", null, false, false);
		var2978.setLoDCount(var2957, 123);
		var2978.toString();
		String var2982 = "com.google.test.metric.JavaClassRepositoryTest.MyClass.MyInnerClass";
		var2979.getClass(var2982);
		var2979.getClass(var2982);
		JavaClassRepository var2961 = new JavaClassRepository();
		ClassInfo var2974 = var2961.getClass(MultipleInjectability.class
				.getCanonicalName());
		MethodInfo var2975 = var2974.getMethod(method("execute", Obj.class));
		var2975.getParameters();
		var2980.createFrame(var2975, 1);
		ClassInfo var2965 = var2961
				.getClass(LoDMultipleDifferentInvocations.class
						.getCanonicalName());
		MethodInfo var2966 = var2965.getMethod(method("execute", Obj.class));
		var2980.createFrame(var2966, 1);
	}

	public LocalVariableState instantiateLocalVariableState476() {
		VariableState var2949 = new VariableState();
		return new LocalVariableState(var2949);
	}

	public RegExpWhiteList instantiateRegExpWhiteList477() {
		return new RegExpWhiteList("String");
	}

	public JavaClassRepository instantiateJavaClassRepository478() {
		ClassPath var2976 = new ClassPathFactory().createFromJVM();
		return new JavaClassRepository(var2976);
	}

	/**
	 * Test method for the class com.google.test.metric.TestabilityVisitor
	 */
	public void testTestabilityVisitor958() throws Exception {
		RegExpWhiteList var2984 = instantiateRegExpWhiteList477();
		LocalVariableState var2985 = instantiateLocalVariableState476();
		JavaClassRepository var2986 = instantiateJavaClassRepository478();
		VariableState var2953 = new VariableState();
		TestabilityVisitor var2987 = new TestabilityVisitor(var2986, var2953,
				null, var2984);
		var2984.addPackage("javax.");
		var2984.isClassWhiteListed("java.lang.String");
		var2984.isClassWhiteListed("com.company.String");
		FieldInfo var2981 = new FieldInfo(null, "field", null, false, false,
				false);
		var2985.setGlobal(var2981);
		var2985.setInjectable(var2981);
		var2985.setGlobal(var2981);
		var2985.setInjectable(var2981);
		var2986.getClass("invalidByteCode");
		var2986.getClass("invalidByteCode");
		JavaClassRepository var2961 = new JavaClassRepository();
		ClassInfo var2974 = var2961.getClass(MultipleInjectability.class
				.getCanonicalName());
		MethodInfo var2975 = var2974.getMethod(method("execute", Obj.class));
		var2975.getParameters();
		var2987.createFrame(var2975, 1);
		ClassInfo var2989 = var2961.getClass(LoDStaticCall.class
				.getCanonicalName());
		MethodInfo var2990 = var2989.getMethod("void execute()");
		var2987.createFrame(var2990, 1);
	}

	public LocalVariableState instantiateLocalVariableState479() {
		VariableState var2949 = new VariableState();
		return new LocalVariableState(var2949);
	}

	public RegExpWhiteList instantiateRegExpWhiteList480() {
		return new RegExpWhiteList("String");
	}

	public JavaSrcRepository instantiateJavaSrcRepository481() {
		ClassRepository var2967 = new JavaClassRepository();
		return new JavaSrcRepository(var2967,
				new ClassPathFactory().createFromPaths("core/src/test/java",
						"src/test/java"));
	}

	/**
	 * Test method for the class com.google.test.metric.TestabilityVisitor
	 */
	public void testTestabilityVisitor959() throws Exception {
		RegExpWhiteList var2991 = instantiateRegExpWhiteList480();
		LocalVariableState var2992 = instantiateLocalVariableState479();
		JavaSrcRepository var2993 = instantiateJavaSrcRepository481();
		JavaClassRepository var2961 = new JavaClassRepository();
		VariableState var2953 = new VariableState();
		TestabilityVisitor var2994 = new TestabilityVisitor(var2961, var2953,
				null, var2991);
		var2991.isClassWhiteListed("java.lang.String");
		var2991.isClassWhiteListed("java.x.Z");
		var2991.addPackage("javax.");
		FieldInfo var2981 = new FieldInfo(null, "field", null, false, false,
				false);
		var2992.setGlobal(var2981);
		var2992.setInjectable(var2981);
		Variable var2959 = new Variable("var", null, false, false);
		var2992.setGlobal(var2959);
		var2992.setInjectable(var2959);
		var2992.isGlobal(var2959);
		var2992.isInjectable(var2959);
		var2993.getClass(getClass().getName());
		var2993.getClass("IDontExist");
		ClassInfo var2962 = var2961.getClass(LoDMultipleSameInvocations.class
				.getCanonicalName());
		MethodInfo var2963 = var2962.getMethod(method("execute", Obj.class));
		var2994.createFrame(var2963, 1);
		var2994.createFrame(var2963, 1);
	}

	private static class LoDMultipleSameInvocations {
		Obj plus2;

		public void execute(Obj plus0) {
			Obj plus1 = plus0.getValueA();
			plus2 = plus1.getValueA();
			plus2 = plus1;
		}
	}

	private static class LoDMultipleDifferentInvocations {
		Obj plus2;

		public void execute(Obj plus0) {
			Obj plus1 = plus0.getValueA();
			plus2 = plus1.getValueB();
			plus2 = plus1;
		}
	}

	private static class MultipleInjectability {
		private Obj injectable1;
		private Obj injectable2;

		public void execute(Obj injectable) {
			injectable1 = injectable.getValueA();
			injectable2 = injectable.getValueA();
		}

		public boolean useFields() {
			return injectable1 == injectable2;
		}
	}

	private static class LoDStaticCall {
		Obj plus1;

		public void execute() {
			plus1 = Obj.getStaticValueA();
		}
	}

	static class ClassExtendsAndImplements extends ArrayList<Object> implements
			Set<Object>, List<Object> {
		private static final long serialVersionUID = 1L;
	}

	static class TypeQualifications {
		String field0;
		My.String field1;
		JavaSrcRepositoryTest.My.String field2;
		com.google.test.metric.javasrc.JavaSrcRepositoryTest.My.String field3;
	}

	static class My {
		String field;

		static class String {
		}
	}

	public static class SingleMethodClass {
		public void methodA() {
		}
	}

	private static class Monitor {
		public void method() {
			synchronized (this) {
				hashCode();
			}
		}

		public void method2() {
			hashCode();
			synchronized (this) {
				hashCode();
			}
			hashCode();
		}
	}
}