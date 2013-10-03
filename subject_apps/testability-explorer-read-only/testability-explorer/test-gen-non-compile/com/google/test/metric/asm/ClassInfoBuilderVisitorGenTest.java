package com.google.test.metric.asm;


import com.google.classpath.ClassPathFactory;
import com.google.test.metric.ClassInfo;
import com.google.classpath.DirectoryClassPath;
import com.google.test.metric.JavaClassRepository;
import java.lang.String;
import java.io.File;
import com.google.classpath.ClassPath;
import junit.framework.TestCase;
import static java.util.Collections.EMPTY_LIST;

public class ClassInfoBuilderVisitorGenTest extends TestCase {
	public JavaClassRepository instantiateJavaClassRepository630() {
		return new JavaClassRepository(new DirectoryClassPath(new File(
				"classes-for-test")));
	}

	/**
	 * Test method for the class com.google.test.metric.asm.ClassInfoBuilderVisitor
	 */
	public void testClassInfoBuilderVisitor1090() throws Exception {
		JavaClassRepository var3602 = instantiateJavaClassRepository630();
		ClassInfoBuilderVisitor var3603 = new ClassInfoBuilderVisitor(null);
		var3602.getClass("invalidByteCode");
		String var3605 = "com.google.test.metric.JavaClassRepositoryTest.MyClass.MyInnerClass";
		var3602.getClass(var3605);
		var3603.guessSourceFileName("com.google.Foo$1");
		var3603.guessSourceFileName("com.google.Foo$1");
	}

	public JavaClassRepository instantiateJavaClassRepository631() {
		ClassPath var3608 = new ClassPathFactory().createFromPaths(var3609,
				"core/" + var3609);
		return new JavaClassRepository(var3608);
	}

	/**
	 * Test method for the class com.google.test.metric.asm.ClassInfoBuilderVisitor
	 */
	public void testClassInfoBuilderVisitor1091() throws Exception {
		JavaClassRepository var3610 = instantiateJavaClassRepository631();
		ClassInfoBuilderVisitor var3603 = new ClassInfoBuilderVisitor(null);
		var3610.getClass(DeeplyNestedIfStatements.class.getCanonicalName());
		var3610.getClass(DeeplyNestedIfStatements.class.getCanonicalName());
		var3603.guessSourceFileName("com.google.Foo$1");
		var3603.guessSourceFileName("com.google.Foo$1");
	}

	public JavaClassRepository instantiateJavaClassRepository632() {
		return new JavaClassRepository(new DirectoryClassPath(new File(
				"classes-for-test")));
	}

	/**
	 * Test method for the class com.google.test.metric.asm.ClassInfoBuilderVisitor
	 */
	public void testClassInfoBuilderVisitor1092() throws Exception {
		JavaClassRepository var3611 = instantiateJavaClassRepository632();
		ClassInfoBuilderVisitor var3603 = new ClassInfoBuilderVisitor(null);
		String var3612 = "java.util.Map.Entry";
		var3611.getClass(var3612);
		var3611.getClass(DeeplyNestedIfStatements.class.getCanonicalName());
		var3603.guessSourceFileName("com.google.Foo$1");
		var3603.guessSourceFileName("java.lang.String");
	}

	public JavaClassRepository instantiateJavaClassRepository633() {
		return new JavaClassRepository();
	}

	/**
	 * Test method for the class com.google.test.metric.asm.ClassInfoBuilderVisitor
	 */
	public void testClassInfoBuilderVisitor1093() throws Exception {
		JavaClassRepository var3613 = instantiateJavaClassRepository633();
		ClassInfoBuilderVisitor var3603 = new ClassInfoBuilderVisitor(null);
		var3613.getClass(EmptyClass.class.getCanonicalName());
		var3613.getClass(EmptyClass.class.getCanonicalName());
		var3613.getClass(LoDMultipleDifferentInvocations.class
				.getCanonicalName());
		var3603.guessSourceFileName("com.google.Foo$1");
		var3603.guessSourceFileName("com.google.Foo$1");
	}

	public JavaClassRepository instantiateJavaClassRepository634() {
		return new JavaClassRepository(new DirectoryClassPath(new File(
				"classes-for-test")));
	}

	/**
	 * Test method for the class com.google.test.metric.asm.ClassInfoBuilderVisitor
	 */
	public void testClassInfoBuilderVisitor1094() throws Exception {
		JavaClassRepository var3616 = instantiateJavaClassRepository634();
		ClassInfoBuilderVisitor var3603 = new ClassInfoBuilderVisitor(null);
		String var3612 = "java.util.Map.Entry";
		var3616.getClass(var3612);
		var3616.getClass("invalidByteCode");
		var3603.guessSourceFileName("com.google.Foo$1");
		var3603.guessSourceFileName("java.lang.String");
	}

	private static class LoDMultipleDifferentInvocations {
		Obj plus2;

		public void execute(Obj plus0) {
			Obj plus1 = plus0.getValueA();
			plus2 = plus1.getValueB();
			plus2 = plus1;
		}
	}

	private static class DeeplyNestedIfStatements {
		@SuppressWarnings("unused")
		private static void nested(boolean x) {
			int num = (x ? 1 : 0) + (x ? 1 : 0) + (x ? 1 : 0) + (x ? 1 : 0)
					+ (x ? 1 : 0) + (x ? 1 : 0) + (x ? 1 : 0) + (x ? 1 : 0)
					+ (x ? 1 : 0) + (x ? 1 : 0) + (x ? 1 : 0) + (x ? 1 : 0)
					+ (x ? 1 : 0) + (x ? 1 : 0) + (x ? 1 : 0);
		}
	}

	public static class EmptyClass {
	}
}