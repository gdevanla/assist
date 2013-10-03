package com.google.test.metric.javasrc;


import java.lang.Object;
import com.google.classpath.ClassPathFactory;
import com.google.test.metric.ClassInfo;
import com.google.test.metric.JavaClassRepository;
import java.lang.String;
import junit.framework.TestCase;
import com.google.test.metric.javasrc.JavaSrcRepository;
import java.lang.Class;
import com.google.classpath.ClassPath;
import com.google.test.metric.ClassRepository;
import static java.util.Arrays.asList;
import static java.util.Collections.EMPTY_LIST;

public class JavaSrcRepositoryGenTest extends TestCase {
	public JavaSrcRepository instantiateJavaSrcRepository418() {
		ClassRepository var2737 = new JavaClassRepository();
		return new JavaSrcRepository(var2737,
				new ClassPathFactory().createFromPaths("core/src/test/java",
						"src/test/java"));
	}

	/**
	 * Test method for the class com.google.test.metric.javasrc.JavaSrcRepository
	 */
	public void testJavaSrcRepository875() throws Exception {
		JavaSrcRepository var2738 = instantiateJavaSrcRepository418();
		ClassRepository var2737 = new JavaClassRepository();
		JavaSrcRepository var2739 = new JavaSrcRepository(var2737,
				new ClassPathFactory().createFromPaths("core/src/test/java",
						"src/test/java"));
		var2738.getClass(getClass().getName());
		var2738.getClass(ClassExtendsAndImplements.class.getCanonicalName());
		var2739.getClass("pkg.A$B");
		var2739.getClass("IDontExist");
	}

	public JavaSrcRepository instantiateJavaSrcRepository419() {
		ClassRepository var2737 = new JavaClassRepository();
		return new JavaSrcRepository(var2737,
				new ClassPathFactory().createFromPaths("core/src/test/java",
						"src/test/java"));
	}

	/**
	 * Test method for the class com.google.test.metric.javasrc.JavaSrcRepository
	 */
	public void testJavaSrcRepository876() throws Exception {
		JavaSrcRepository var2742 = instantiateJavaSrcRepository419();
		ClassRepository var2737 = new JavaClassRepository();
		JavaSrcRepository var2739 = new JavaSrcRepository(var2737,
				new ClassPathFactory().createFromPaths("core/src/test/java",
						"src/test/java"));
		var2742.getClass(TypeQualifications.class.getName());
		var2742.getCachedClass(TypeQualifications.class.getName());
		var2742.getClass(My.class.getName());
		var2742.getClass(getClass().getName());
		var2739.getClass("pkg.A");
		var2739.getClass(getClass().getName());
	}

	public JavaClassRepository instantiateJavaClassRepository420() {
		ClassPath var2743 = new ClassPathFactory().createFromJVM();
		return new JavaClassRepository(var2743);
	}

	/**
	 * Test method for the class com.google.test.metric.javasrc.JavaSrcRepository
	 */
	public void testJavaSrcRepository877() throws Exception {
		JavaClassRepository var2744 = instantiateJavaClassRepository420();
		ClassRepository var2737 = new JavaClassRepository();
		JavaSrcRepository var2739 = new JavaSrcRepository(var2737,
				new ClassPathFactory().createFromPaths("core/src/test/java",
						"src/test/java"));
		String var2745 = "com.google.test.metric.JavaClassRepositoryTest.MyClass.MyInnerClass";
		var2744.getClass(var2745);
		var2744.getClass("invalidByteCode");
		var2739.getClass(ClassExtendsAndImplements.class.getCanonicalName());
		var2739.getClass("pkg.A$B");
	}

	public JavaClassRepository instantiateJavaClassRepository421() {
		return new JavaClassRepository();
	}

	/**
	 * Test method for the class com.google.test.metric.javasrc.JavaSrcRepository
	 */
	public void testJavaSrcRepository878() throws Exception {
		JavaClassRepository var2748 = instantiateJavaClassRepository421();
		ClassRepository var2737 = new JavaClassRepository();
		JavaSrcRepository var2739 = new JavaSrcRepository(var2737,
				new ClassPathFactory().createFromPaths("core/src/test/java",
						"src/test/java"));
		var2748.getClass(Foreach.class.getCanonicalName());
		var2748.getClass(LoDMultipleSameInvocations.class.getCanonicalName());
		var2739.getClass("pkg.A");
		var2739.getClass(getClass().getName());
	}

	public JavaClassRepository instantiateJavaClassRepository422() {
		return new JavaClassRepository();
	}

	/**
	 * Test method for the class com.google.test.metric.javasrc.JavaSrcRepository
	 */
	public void testJavaSrcRepository879() throws Exception {
		JavaClassRepository var2751 = instantiateJavaClassRepository422();
		ClassRepository var2752 = new JavaClassRepository();
		JavaSrcRepository var2753 = new JavaSrcRepository(var2752, null);
		var2751.getClass(Object.class.getCanonicalName());
		var2751.getClass(var2755.getCanonicalName());
		var2751.getClass(Medium.class.getCanonicalName());
		var2753.getClass("pkg.A$B");
		var2753.getClass(ClassExtendsAndImplements.class.getCanonicalName());
	}

	static class Foreach {
		@SuppressWarnings("unused")
		public void method() {
			for (String names : new String[0]) {
			}
		}
	}

	public static class Medium {
		public Medium() {
			statiCost1();
			cost2();
		}

		/**
		 * I cost 1
		 */
		public static int statiCost1() {
			int i = 0;
			return i > 0 ? 1 : 2;
		}

		/**
		 * I cost 2, but I am instance method so I can be overridden. so my cost may be avoided in most cases.
		 */
		public int cost2() {
			int i = 0;
			return i > 0 ? i > 1 ? 1 : 2 : 2;
		}

		/**
		 * I cost 2, but I am a <em>final</em> instance method that can not be overridden. My cost is unavoidable.
		 */
		public final int finalCost2() {
			int i = 0;
			return i > 0 ? i > 1 ? 1 : 2 : 2;
		}

		/**
		 * I am instance method hence you will have to add the cost of constructor to me. (by myself I cost 4)
		 */
		public Object testMethod4() {
			int i = 0;
			i = i > 0 ? 1 : 2;
			i = i > 0 ? 1 : 2;
			i = i > 0 ? 1 : 2;
			i = i > 0 ? 1 : 2;
			return new Object();
		}
	}

	private static class LoDMultipleSameInvocations {
		Obj plus2;

		public void execute(Obj plus0) {
			Obj plus1 = plus0.getValueA();
			plus2 = plus1.getValueA();
			plus2 = plus1;
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
}