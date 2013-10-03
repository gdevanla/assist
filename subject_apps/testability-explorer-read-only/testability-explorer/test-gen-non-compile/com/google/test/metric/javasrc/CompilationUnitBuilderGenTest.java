package com.google.test.metric.javasrc;


import java.util.ArrayList;
import com.google.test.metric.javasrc.Qualifier;
import com.google.test.metric.ClassInfo;
import com.google.classpath.ClassPathFactory;
import com.google.test.metric.JavaClassRepository;
import java.lang.String;
import junit.framework.TestCase;
import com.google.test.metric.javasrc.JavaSrcRepository;
import java.lang.Class;
import com.google.test.metric.Type;
import com.google.classpath.ClassPath;
import com.google.test.metric.ClassRepository;

public class CompilationUnitBuilderGenTest extends TestCase {
	public JavaSrcRepository instantiateJavaSrcRepository635() {
		ClassRepository var3627 = new JavaClassRepository();
		return new JavaSrcRepository(var3627, null);
	}

	/**
	 * Test method for the class com.google.test.metric.javasrc.CompilationUnitBuilder
	 */
	public void testCompilationUnitBuilder1100() throws Exception {
		JavaSrcRepository var3628 = instantiateJavaSrcRepository635();
		ClassRepository var3627 = new JavaClassRepository();
		Qualifier var3629 = new Qualifier();
		CompilationUnitBuilder var3630 = new CompilationUnitBuilder(var3628,
				var3629, "");
		var3628.getClass("pkg.A");
		var3628.getClass(InnerClass.class.getCanonicalName());
		var3628.getClass(InnerInnerClass.class.getCanonicalName());
		var3630.startType(0, "A", null, new ArrayList<Type>());
		var3630.startType(0, "B", null, new ArrayList<Type>());
		var3630.endType();
		var3630.endType();
		var3630.startType(0, "A", null, new ArrayList<Type>());
		var3630.endType();
	}

	public JavaSrcRepository instantiateJavaSrcRepository636() {
		ClassRepository var3634 = new JavaClassRepository();
		return new JavaSrcRepository(var3634,
				new ClassPathFactory().createFromPaths("core/src/test/java",
						"src/test/java"));
	}

	/**
	 * Test method for the class com.google.test.metric.javasrc.CompilationUnitBuilder
	 */
	public void testCompilationUnitBuilder1101() throws Exception {
		JavaSrcRepository var3635 = instantiateJavaSrcRepository636();
		ClassRepository var3627 = new JavaClassRepository();
		Qualifier var3629 = new Qualifier();
		CompilationUnitBuilder var3636 = new CompilationUnitBuilder(var3635,
				var3629, "");
		var3635.getClass(ClassExtendsAndImplements.class.getCanonicalName());
		var3635.getClass(InnerClass.class.getCanonicalName());
		var3635.getClass(InnerInnerClass.class.getCanonicalName());
		var3636.startType(0, "A", null, new ArrayList<Type>());
		var3636.startType(0, "B", null, new ArrayList<Type>());
		var3636.endType();
		var3636.endType();
		var3636.startType(0, "A", null, new ArrayList<Type>());
		var3636.endType();
	}

	public JavaSrcRepository instantiateJavaSrcRepository637() {
		ClassRepository var3634 = new JavaClassRepository();
		return new JavaSrcRepository(var3634,
				new ClassPathFactory().createFromPaths("core/src/test/java",
						"src/test/java"));
	}

	/**
	 * Test method for the class com.google.test.metric.javasrc.CompilationUnitBuilder
	 */
	public void testCompilationUnitBuilder1102() throws Exception {
		JavaSrcRepository var3637 = instantiateJavaSrcRepository637();
		ClassRepository var3627 = new JavaClassRepository();
		Qualifier var3629 = new Qualifier();
		CompilationUnitBuilder var3638 = new CompilationUnitBuilder(var3637,
				var3629, "");
		var3637.getClass(getClass().getName());
		var3637.getClass(FieldDeclaration.class.getCanonicalName());
		var3638.startType(0, "A", null, new ArrayList<Type>());
		var3638.endType();
		var3638.startType(0, "A", null, new ArrayList<Type>());
		var3638.endType();
	}

	public JavaSrcRepository instantiateJavaSrcRepository638() {
		ClassRepository var3627 = new JavaClassRepository();
		return new JavaSrcRepository(var3627, null);
	}

	/**
	 * Test method for the class com.google.test.metric.javasrc.CompilationUnitBuilder
	 */
	public void testCompilationUnitBuilder1103() throws Exception {
		JavaSrcRepository var3639 = instantiateJavaSrcRepository638();
		ClassRepository var3627 = new JavaClassRepository();
		Qualifier var3629 = new Qualifier();
		CompilationUnitBuilder var3640 = new CompilationUnitBuilder(var3639,
				var3629, "");
		var3639.getClass(InnerClass.class.getCanonicalName());
		var3639.getClass(InnerInnerClass.class.getCanonicalName());
		var3639.getClass(FieldDeclaration.class.getCanonicalName());
		var3640.startType(0, "A", null, new ArrayList<Type>());
		var3640.endType();
		var3640.startType(0, "A", null, new ArrayList<Type>());
		var3640.startType(0, "B", null, new ArrayList<Type>());
		var3640.endType();
		var3640.endType();
	}

	public JavaSrcRepository instantiateJavaSrcRepository639() {
		ClassRepository var3634 = new JavaClassRepository();
		return new JavaSrcRepository(var3634,
				new ClassPathFactory().createFromPaths("core/src/test/java",
						"src/test/java"));
	}

	/**
	 * Test method for the class com.google.test.metric.javasrc.CompilationUnitBuilder
	 */
	public void testCompilationUnitBuilder1104() throws Exception {
		JavaSrcRepository var3641 = instantiateJavaSrcRepository639();
		ClassRepository var3627 = new JavaClassRepository();
		Qualifier var3629 = new Qualifier();
		CompilationUnitBuilder var3642 = new CompilationUnitBuilder(var3641,
				var3629, "");
		var3641.getClass(TypeQualifications.class.getName());
		var3641.getCachedClass(TypeQualifications.class.getName());
		var3641.getClass(My.class.getName());
		var3641.getClass(ClassExtendsAndImplements.class.getCanonicalName());
		var3642.startType(0, "A", null, new ArrayList<Type>());
		var3642.startType(0, "B", null, new ArrayList<Type>());
		var3642.endType();
		var3642.endType();
		var3642.startType(0, "A", null, new ArrayList<Type>());
		var3642.startType(0, "B", null, new ArrayList<Type>());
		var3642.endType();
		var3642.endType();
	}

	static class InnerClass {
		static class InnerInnerClass {
		}
	}

	static class ClassExtendsAndImplements extends ArrayList<Object> implements
			Set<Object>, List<Object> {
		private static final long serialVersionUID = 1L;
	}

	static class FieldDeclaration {
		static final String field1 = "";
		public int field2;
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

	static class InnerInnerClass {
	}
}