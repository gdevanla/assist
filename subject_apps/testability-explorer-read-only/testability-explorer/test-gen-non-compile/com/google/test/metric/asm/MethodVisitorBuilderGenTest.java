package com.google.test.metric.asm;


import com.google.classpath.ClassPathFactory;
import com.google.test.metric.ClassInfo;
import java.util.Collections;
import com.google.test.metric.Visibility;
import junit.framework.TestCase;
import com.google.classpath.ClassPath;
import com.google.test.metric.LocalVariableInfo;
import java.util.Collection;
import com.google.test.metric.method.op.turing.Operation;
import java.lang.Object;
import java.util.List;
import com.google.test.metric.JavaClassRepository;
import com.google.classpath.DirectoryClassPath;
import com.google.test.metric.ParameterInfo;
import java.lang.Integer;
import java.lang.String;
import org.objectweb.asm.Opcodes;
import com.google.test.metric.javasrc.JavaSrcRepository;
import java.io.File;
import com.google.test.metric.MethodInfo;
import com.google.test.metric.ClassRepository;
import static java.util.Arrays.asList;
import static java.util.Collections.EMPTY_LIST;

public class MethodVisitorBuilderGenTest extends TestCase {
	public ClassInfo instantiateClassInfo620() {
		List<ClassInfo> var3569 = Collections.emptyList();
		return new ClassInfo("super", false, null, var3569, null);
	}

	public JavaSrcRepository instantiateJavaSrcRepository621() {
		ClassRepository var3570 = new JavaClassRepository();
		return new JavaSrcRepository(var3570,
				new ClassPathFactory().createFromPaths("core/src/test/java",
						"src/test/java"));
	}

	/**
	 * Test method for the class com.google.test.metric.asm.MethodVisitorBuilder
	 */
	public void testMethodVisitorBuilder1085() throws Exception {
		ClassInfo var3571 = instantiateClassInfo620();
		JavaSrcRepository var3572 = instantiateJavaSrcRepository621();
		MethodVisitorBuilder var3573 = new MethodVisitorBuilder(null, var3571,
				"test", "()V", null, null, true, false, Visibility.PUBLIC);
		List<ClassInfo> var3569 = Collections.emptyList();
		ClassInfo var3574 = new ClassInfo("super", false, null, var3569, null);
		List<ParameterInfo> var3575 = Collections.emptyList();
		List<LocalVariableInfo> var3576 = Collections.emptyList();
		List<Operation> var3577 = Collections.emptyList();
		var3574.addMethod(new MethodInfo(var3574, "void setB()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3571.addMethod(new MethodInfo(var3574, "void setB()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "void setB()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "void setA()", -1, null,
				var3575, var3576, Visibility.PRIVATE, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3571.addMethod(new MethodInfo(var3574, "void setA()", -1, null,
				var3575, var3576, Visibility.PRIVATE, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "void setB()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "void setA()", -1, null,
				var3575, var3576, Visibility.PRIVATE, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "voidX()", -1, null, var3575,
				var3576, Visibility.PUBLIC, var3577, false, false, Collections
						.<Integer> emptyList()));
		var3571.addMethod(new MethodInfo(var3574, "voidX()", -1, null, var3575,
				var3576, Visibility.PUBLIC, var3577, false, false, Collections
						.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "void setB()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "void setA()", -1, null,
				var3575, var3576, Visibility.PRIVATE, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "voidX()", -1, null, var3575,
				var3576, Visibility.PUBLIC, var3577, false, false, Collections
						.<Integer> emptyList()));
		ClassInfo var3578 = new ClassInfo("super", false, var3574, var3569,
				null);
		var3578.addMethod(new MethodInfo(var3578, "void setD()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3571.addMethod(new MethodInfo(var3578, "void setD()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "void setB()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "void setA()", -1, null,
				var3575, var3576, Visibility.PRIVATE, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "voidX()", -1, null, var3575,
				var3576, Visibility.PUBLIC, var3577, false, false, Collections
						.<Integer> emptyList()));
		var3578.addMethod(new MethodInfo(var3578, "void setD()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3578.addMethod(new MethodInfo(var3578, "void setC()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3571.addMethod(new MethodInfo(var3578, "void setC()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3571.getSetters();
		var3572.getClass(InnerClass.class.getCanonicalName());
		var3572.getClass(InnerInnerClass.class.getCanonicalName());
		var3572.getClass("pkg.A$B");
		var3573.visitInsn(Opcodes.ICONST_0);
		var3573.visitInsn(Opcodes.ICONST_0);
		var3573.visitInsn(Opcodes.SWAP);
		var3573.visitEnd();
		var3573.visitInsn(Opcodes.NOP);
		var3573.visitEnd();
	}

	public ClassInfo instantiateClassInfo622() {
		List<ClassInfo> var3569 = Collections.emptyList();
		return new ClassInfo("super", false, null, var3569, null);
	}

	public JavaClassRepository instantiateJavaClassRepository623() {
		return new JavaClassRepository();
	}

	/**
	 * Test method for the class com.google.test.metric.asm.MethodVisitorBuilder
	 */
	public void testMethodVisitorBuilder1086() throws Exception {
		ClassInfo var3583 = instantiateClassInfo622();
		JavaClassRepository var3584 = instantiateJavaClassRepository623();
		MethodVisitorBuilder var3585 = new MethodVisitorBuilder(null, var3583,
				"test", "()V", null, null, true, false, Visibility.PUBLIC);
		List<ClassInfo> var3569 = Collections.emptyList();
		ClassInfo var3574 = new ClassInfo("super", false, null, var3569, null);
		List<ParameterInfo> var3575 = Collections.emptyList();
		List<LocalVariableInfo> var3576 = Collections.emptyList();
		List<Operation> var3577 = Collections.emptyList();
		var3574.addMethod(new MethodInfo(var3574, "void setB()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "void setA()", -1, null,
				var3575, var3576, Visibility.PRIVATE, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "voidX()", -1, null, var3575,
				var3576, Visibility.PUBLIC, var3577, false, false, Collections
						.<Integer> emptyList()));
		ClassInfo var3578 = new ClassInfo("super", false, var3574, var3569,
				null);
		var3578.addMethod(new MethodInfo(var3578, "void setD()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3583.addMethod(new MethodInfo(var3578, "void setD()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "void setB()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "void setA()", -1, null,
				var3575, var3576, Visibility.PRIVATE, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "voidX()", -1, null, var3575,
				var3576, Visibility.PUBLIC, var3577, false, false, Collections
						.<Integer> emptyList()));
		var3578.addMethod(new MethodInfo(var3578, "void setD()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3578.addMethod(new MethodInfo(var3578, "void setC()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3583.addMethod(new MethodInfo(var3578, "void setC()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3583.getSetters();
		var3574.addMethod(new MethodInfo(var3574, "void setB()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3583.addMethod(new MethodInfo(var3574, "void setB()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "void setB()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "void setA()", -1, null,
				var3575, var3576, Visibility.PRIVATE, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3583.addMethod(new MethodInfo(var3574, "void setA()", -1, null,
				var3575, var3576, Visibility.PRIVATE, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "void setB()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "void setA()", -1, null,
				var3575, var3576, Visibility.PRIVATE, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "voidX()", -1, null, var3575,
				var3576, Visibility.PUBLIC, var3577, false, false, Collections
						.<Integer> emptyList()));
		var3583.addMethod(new MethodInfo(var3574, "voidX()", -1, null, var3575,
				var3576, Visibility.PUBLIC, var3577, false, false, Collections
						.<Integer> emptyList()));
		var3584.getClass(Object.class.getCanonicalName());
		var3584.getClass(var3587.getCanonicalName());
		var3584.getClass(MultipleInjectability.class.getCanonicalName());
		var3585.visitInsn(Opcodes.ICONST_0);
		var3585.visitInsn(Opcodes.ICONST_0);
		var3585.visitInsn(Opcodes.SWAP);
		var3585.visitEnd();
		var3585.visitInsn(Opcodes.NOP);
		var3585.visitEnd();
	}

	public ClassInfo instantiateClassInfo624() {
		List<ClassInfo> var3569 = Collections.emptyList();
		return new ClassInfo("super", false, null, var3569, null);
	}

	public JavaClassRepository instantiateJavaClassRepository625() {
		return new JavaClassRepository(new DirectoryClassPath(new File(
				"classes-for-test")));
	}

	/**
	 * Test method for the class com.google.test.metric.asm.MethodVisitorBuilder
	 */
	public void testMethodVisitorBuilder1087() throws Exception {
		ClassInfo var3589 = instantiateClassInfo624();
		JavaClassRepository var3590 = instantiateJavaClassRepository625();
		MethodVisitorBuilder var3591 = new MethodVisitorBuilder(null, var3589,
				"test", "()V", null, null, true, false, Visibility.PUBLIC);
		List<ClassInfo> var3569 = Collections.emptyList();
		ClassInfo var3574 = new ClassInfo("super", false, null, var3569, null);
		List<ParameterInfo> var3575 = Collections.emptyList();
		List<LocalVariableInfo> var3576 = Collections.emptyList();
		List<Operation> var3577 = Collections.emptyList();
		var3574.addMethod(new MethodInfo(var3574, "void setB()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3589.addMethod(new MethodInfo(var3574, "void setB()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "void setB()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "void setA()", -1, null,
				var3575, var3576, Visibility.PRIVATE, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3589.addMethod(new MethodInfo(var3574, "void setA()", -1, null,
				var3575, var3576, Visibility.PRIVATE, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "void setB()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "void setA()", -1, null,
				var3575, var3576, Visibility.PRIVATE, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "voidX()", -1, null, var3575,
				var3576, Visibility.PUBLIC, var3577, false, false, Collections
						.<Integer> emptyList()));
		var3589.addMethod(new MethodInfo(var3574, "voidX()", -1, null, var3575,
				var3576, Visibility.PUBLIC, var3577, false, false, Collections
						.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "void setB()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "void setA()", -1, null,
				var3575, var3576, Visibility.PRIVATE, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "voidX()", -1, null, var3575,
				var3576, Visibility.PUBLIC, var3577, false, false, Collections
						.<Integer> emptyList()));
		ClassInfo var3578 = new ClassInfo("super", false, var3574, var3569,
				null);
		var3578.addMethod(new MethodInfo(var3578, "void setD()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3589.addMethod(new MethodInfo(var3578, "void setD()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "void setB()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "void setA()", -1, null,
				var3575, var3576, Visibility.PRIVATE, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "voidX()", -1, null, var3575,
				var3576, Visibility.PUBLIC, var3577, false, false, Collections
						.<Integer> emptyList()));
		var3578.addMethod(new MethodInfo(var3578, "void setD()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3578.addMethod(new MethodInfo(var3578, "void setC()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3589.addMethod(new MethodInfo(var3578, "void setC()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3589.getSetters();
		String var3592 = "java.util.Map.Entry";
		var3590.getClass(var3592);
		var3590.getClass("invalidByteCode");
		var3591.visitInsn(Opcodes.ICONST_0);
		var3591.visitInsn(Opcodes.ICONST_0);
		var3591.visitInsn(Opcodes.SWAP);
		var3591.visitEnd();
		var3591.visitInsn(Opcodes.ICONST_0);
		var3591.visitInsn(Opcodes.ICONST_0);
		var3591.visitInsn(Opcodes.SWAP);
		var3591.visitEnd();
	}

	public ClassInfo instantiateClassInfo626() {
		return new ClassInfo("TestClass", false, null, null, null);
	}

	public JavaClassRepository instantiateJavaClassRepository627() {
		return new JavaClassRepository();
	}

	/**
	 * Test method for the class com.google.test.metric.asm.MethodVisitorBuilder
	 */
	public void testMethodVisitorBuilder1088() throws Exception {
		ClassInfo var3595 = instantiateClassInfo626();
		JavaClassRepository var3596 = instantiateJavaClassRepository627();
		MethodVisitorBuilder var3597 = new MethodVisitorBuilder(null, var3595,
				"test", "()V", null, null, true, false, Visibility.PUBLIC);
		List<ClassInfo> var3569 = Collections.emptyList();
		ClassInfo var3574 = new ClassInfo("super", false, null, var3569, null);
		List<ParameterInfo> var3575 = Collections.emptyList();
		List<LocalVariableInfo> var3576 = Collections.emptyList();
		List<Operation> var3577 = Collections.emptyList();
		var3574.addMethod(new MethodInfo(var3574, "void setB()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "void setA()", -1, null,
				var3575, var3576, Visibility.PRIVATE, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "voidX()", -1, null, var3575,
				var3576, Visibility.PUBLIC, var3577, false, false, Collections
						.<Integer> emptyList()));
		ClassInfo var3578 = new ClassInfo("super", false, var3574, var3569,
				null);
		var3578.addMethod(new MethodInfo(var3578, "void setD()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3595.addMethod(new MethodInfo(var3578, "void setD()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "void setB()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "void setA()", -1, null,
				var3575, var3576, Visibility.PRIVATE, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "voidX()", -1, null, var3575,
				var3576, Visibility.PUBLIC, var3577, false, false, Collections
						.<Integer> emptyList()));
		var3578.addMethod(new MethodInfo(var3578, "void setD()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3578.addMethod(new MethodInfo(var3578, "void setC()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3595.addMethod(new MethodInfo(var3578, "void setC()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3595.getSetters();
		var3574.addMethod(new MethodInfo(var3574, "void setB()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "void setA()", -1, null,
				var3575, var3576, Visibility.PRIVATE, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "voidX()", -1, null, var3575,
				var3576, Visibility.PUBLIC, var3577, false, false, Collections
						.<Integer> emptyList()));
		var3578.addMethod(new MethodInfo(var3578, "void setD()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3595.addMethod(new MethodInfo(var3578, "void setD()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "void setB()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "void setA()", -1, null,
				var3575, var3576, Visibility.PRIVATE, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "voidX()", -1, null, var3575,
				var3576, Visibility.PUBLIC, var3577, false, false, Collections
						.<Integer> emptyList()));
		var3578.addMethod(new MethodInfo(var3578, "void setD()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3578.addMethod(new MethodInfo(var3578, "void setC()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3595.addMethod(new MethodInfo(var3578, "void setC()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3595.getSetters();
		var3596.getClass(Object.class.getCanonicalName());
		var3596.getClass(MultipleInjectability.class.getCanonicalName());
		var3597.visitInsn(Opcodes.NOP);
		var3597.visitEnd();
		var3597.visitInsn(Opcodes.NOP);
		var3597.visitEnd();
	}

	public ClassInfo instantiateClassInfo628() {
		return new ClassInfo("TestClass", false, null, null, null);
	}

	public JavaSrcRepository instantiateJavaSrcRepository629() {
		ClassRepository var3570 = new JavaClassRepository();
		return new JavaSrcRepository(var3570,
				new ClassPathFactory().createFromPaths("core/src/test/java",
						"src/test/java"));
	}

	/**
	 * Test method for the class com.google.test.metric.asm.MethodVisitorBuilder
	 */
	public void testMethodVisitorBuilder1089() throws Exception {
		ClassInfo var3599 = instantiateClassInfo628();
		JavaSrcRepository var3600 = instantiateJavaSrcRepository629();
		MethodVisitorBuilder var3601 = new MethodVisitorBuilder(null, var3599,
				"test", "()V", null, null, true, false, Visibility.PUBLIC);
		List<ClassInfo> var3569 = Collections.emptyList();
		ClassInfo var3574 = new ClassInfo("super", false, null, var3569, null);
		List<ParameterInfo> var3575 = Collections.emptyList();
		List<LocalVariableInfo> var3576 = Collections.emptyList();
		List<Operation> var3577 = Collections.emptyList();
		var3574.addMethod(new MethodInfo(var3574, "void setB()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "void setA()", -1, null,
				var3575, var3576, Visibility.PRIVATE, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "voidX()", -1, null, var3575,
				var3576, Visibility.PUBLIC, var3577, false, false, Collections
						.<Integer> emptyList()));
		ClassInfo var3578 = new ClassInfo("super", false, var3574, var3569,
				null);
		var3578.addMethod(new MethodInfo(var3578, "void setD()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3599.addMethod(new MethodInfo(var3578, "void setD()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "void setB()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "void setA()", -1, null,
				var3575, var3576, Visibility.PRIVATE, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "voidX()", -1, null, var3575,
				var3576, Visibility.PUBLIC, var3577, false, false, Collections
						.<Integer> emptyList()));
		var3578.addMethod(new MethodInfo(var3578, "void setD()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3578.addMethod(new MethodInfo(var3578, "void setC()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3599.addMethod(new MethodInfo(var3578, "void setC()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3599.getSetters();
		var3574.addMethod(new MethodInfo(var3574, "void setB()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "void setA()", -1, null,
				var3575, var3576, Visibility.PRIVATE, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "voidX()", -1, null, var3575,
				var3576, Visibility.PUBLIC, var3577, false, false, Collections
						.<Integer> emptyList()));
		var3578.addMethod(new MethodInfo(var3578, "void setD()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3599.addMethod(new MethodInfo(var3578, "void setD()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "void setB()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "void setA()", -1, null,
				var3575, var3576, Visibility.PRIVATE, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3574.addMethod(new MethodInfo(var3574, "voidX()", -1, null, var3575,
				var3576, Visibility.PUBLIC, var3577, false, false, Collections
						.<Integer> emptyList()));
		var3578.addMethod(new MethodInfo(var3578, "void setD()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3578.addMethod(new MethodInfo(var3578, "void setC()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3599.addMethod(new MethodInfo(var3578, "void setC()", -1, null,
				var3575, var3576, Visibility.PUBLIC, var3577, false, false,
				Collections.<Integer> emptyList()));
		var3599.getSetters();
		var3600.getClass(ClassExtendsAndImplements.class.getCanonicalName());
		var3600.getClass(ClassExtendsAndImplements.class.getCanonicalName());
		var3601.visitInsn(Opcodes.NOP);
		var3601.visitEnd();
		var3601.visitInsn(Opcodes.NOP);
		var3601.visitEnd();
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

	static class InnerClass {
		static class InnerInnerClass {
		}
	}

	static class ClassExtendsAndImplements extends ArrayList<Object> implements
			Set<Object>, List<Object> {
		private static final long serialVersionUID = 1L;
	}

	static class InnerInnerClass {
	}
}