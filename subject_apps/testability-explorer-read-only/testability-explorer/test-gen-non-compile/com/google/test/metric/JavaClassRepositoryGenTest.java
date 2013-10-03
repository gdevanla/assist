package com.google.test.metric;


import com.google.classpath.ClassPathFactory;
import com.google.test.metric.ClassInfo;
import com.google.classpath.DirectoryClassPath;
import java.lang.String;
import java.io.File;
import com.google.classpath.ClassPath;
import junit.framework.TestCase;
import com.google.test.metric.example.MutableGlobalState.FinalGlobalExample.Gadget;
import static java.util.Arrays.asList;
import static java.util.Collections.EMPTY_LIST;
import static com.google.test.metric.Reason.NON_OVERRIDABLE_METHOD_CALL;

public class JavaClassRepositoryGenTest extends TestCase {
	/**
	 * Test method for the class com.google.test.metric.JavaClassRepository
	 */
	public void testJavaClassRepository865() throws Exception {
		JavaClassRepository var2720 = new JavaClassRepository(
				new DirectoryClassPath(new File("classes-for-test")));
		var2720.getClass("invalidByteCode");
		String var2722 = "java.util.Map.Entry";
		var2720.getClass(var2722);
	}

	/**
	 * Test method for the class com.google.test.metric.JavaClassRepository
	 */
	public void testJavaClassRepository866() throws Exception {
		ClassPath var2724 = new ClassPathFactory().createFromPaths(var2725,
				"core/" + var2725);
		JavaClassRepository var2726 = new JavaClassRepository(var2724);
		String var2727 = "com.google.test.metric.JavaClassRepositoryTest.MyClass.MyInnerClass";
		var2726.getClass(var2727);
		var2726.getClass(var2727);
	}

	/**
	 * Test method for the class com.google.test.metric.JavaClassRepository
	 */
	public void testJavaClassRepository867() throws Exception {
		ClassPath var2728 = new ClassPathFactory().createFromJVM();
		JavaClassRepository var2729 = new JavaClassRepository(var2728);
		var2729.getClass(DeeplyNestedIfStatements.class.getCanonicalName());
		var2729.getClass(DeeplyNestedIfStatements.class.getCanonicalName());
	}

	/**
	 * Test method for the class com.google.test.metric.JavaClassRepository
	 */
	public void testJavaClassRepository868() throws Exception {
		ClassPath var2724 = new ClassPathFactory().createFromPaths(var2725,
				"core/" + var2725);
		JavaClassRepository var2726 = new JavaClassRepository(var2724);
		var2726.getClass("invalidByteCode");
		String var2727 = "com.google.test.metric.JavaClassRepositoryTest.MyClass.MyInnerClass";
		var2726.getClass(var2727);
	}

	/**
	 * Test method for the class com.google.test.metric.JavaClassRepository
	 */
	public void testJavaClassRepository869() throws Exception {
		ClassPath var2728 = new ClassPathFactory().createFromJVM();
		JavaClassRepository var2729 = new JavaClassRepository(var2728);
		String var2727 = "com.google.test.metric.JavaClassRepositoryTest.MyClass.MyInnerClass";
		var2729.getClass(var2727);
		String var2722 = "java.util.Map.Entry";
		var2729.getClass(var2722);
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

	/**
	 * Test method for the class com.google.test.metric.JavaClassRepository
	 */
	public void testJavaClassRepository870() throws Exception {
		JavaClassRepository var2730 = new JavaClassRepository();
		var2730.getClass(CyclomaticMethods.class.getCanonicalName());
		var2730.getClass(LineNumbersForConditionals.class.getCanonicalName());
		var2730.getClass(EmptyClass.class.getCanonicalName());
	}

	/**
	 * Test method for the class com.google.test.metric.JavaClassRepository
	 */
	public void testJavaClassRepository871() throws Exception {
		JavaClassRepository var2730 = new JavaClassRepository();
		var2730.getClass(Foreach.class.getCanonicalName());
		var2730.getClass(CyclomaticMethods.class.getCanonicalName());
		var2730.getClass(LineNumbersForConditionals.class.getCanonicalName());
	}

	/**
	 * Test method for the class com.google.test.metric.JavaClassRepository
	 */
	public void testJavaClassRepository872() throws Exception {
		JavaClassRepository var2730 = new JavaClassRepository();
		var2730.getClass(SingleMethodClass.class.getCanonicalName());
		var2730.getClass(Example.class.getCanonicalName());
	}

	/**
	 * Test method for the class com.google.test.metric.JavaClassRepository
	 */
	public void testJavaClassRepository873() throws Exception {
		JavaClassRepository var2730 = new JavaClassRepository();
		var2730.getClass(CyclomaticMethods.class.getCanonicalName());
		var2730.getClass(LineNumbersForConditionals.class.getCanonicalName());
		var2730.getClass(LoDMultipleSameInvocations.class.getCanonicalName());
	}

	/**
	 * Test method for the class com.google.test.metric.JavaClassRepository
	 */
	public void testJavaClassRepository874() throws Exception {
		JavaClassRepository var2730 = new JavaClassRepository();
		var2730.getClass(Gadget.class.getCanonicalName());
		var2730.getClass(LoDStaticCall.class.getCanonicalName());
	}

	static class Foreach {
		@SuppressWarnings("unused")
		public void method() {
			for (String names : new String[0]) {
			}
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

	private static class LoDStaticCall {
		Obj plus1;

		public void execute() {
			plus1 = Obj.getStaticValueA();
		}
	}

	private static class Example {
		public Example() {
			new CostUtil().instanceCost4();
		}

		public int doThing() {
			new CostUtil().instanceCost3();
			return 1;
		}
	}

	public static class EmptyClass {
	}

	public static class SingleMethodClass {
		public void methodA() {
		}
	}

	public static class CyclomaticMethods {
		public void emptyMethod_1() {
		}

		public void simpleMethod_1() {
			int i = 0;
			i += 1;
		}

		public void ifMethod_2() {
			int a = 0;
			if (a < 0) {
				a++;
			} else {
				a--;
			}
		}

		public void ifMethodNoElse_2() {
			int a = 0;
			if (a < 0) {
				a++;
			}
		}

		public void tryCatch_2() {
			int a = 0;
			try {
				a++;
			} catch (RuntimeException e) {
				a++;
			}
		}

		public void tryCatchFinally_2() {
			int a = 0;
			try {
				a++;
			} catch (RuntimeException e) {
				a++;
			} finally {
				a++;
			}
		}

		public void emptySwitch_2() {
			int a = 0;
			switch (a) {
			case 0:
				a = 0;
			}
		}

		public void emptySwitch_5() {
			int a = 0;
			switch (a) {
			case 0:
				a = 0;
				break;
			case 1:
				a = 1;
				break;
			case 2:
				a = 2;
				break;
			case 4:
				a = 4;
				break;
			default:
			}
		}

		public void switchImplementWithLookUp_3() {
			int a = 0;
			switch (a) {
			case 0:
				a = 0;
				break;
			case 9999:
				a = 9999;
				break;
			default:
				a = -1;
			}
		}

		public void switchWithDefault_2() {
			int a = 0;
			switch (a) {
			case 0:
				a = 0;
				break;
			default:
			}
		}
	}

	public static class LineNumbersForConditionals {
		int a = 0;
		boolean flag;

		public void method() {
			a = flag ? 1 : 2;
			a = !flag ? 3 : 4;
		}

		public void tryCatch() {
			try {
				a = 1;
			} catch (RuntimeException e) {
				a = 2;
			} finally {
				a = 3;
			}
		}

		public void tryCatchCatch() {
			try {
				a = 1;
			} catch (RuntimeException e) {
				a = 2;
			} catch (Exception e) {
				a = 2;
			} finally {
				a = 3;
			}
		}

		public void nestedTryCatch() {
			try {
				try {
					a = 1;
				} catch (RuntimeException e) {
					a = 2;
				} finally {
					a = 3;
				}
			} catch (RuntimeException e) {
				a = 2;
			} finally {
				a = 3;
			}
		}

		public void tableSwitchMethod() {
			switch (a) {
			case 1:
				a = 1;
				break;
			default:
				a = 2;
				break;
			}
		}

		public void lookupSwitchMethod() {
			switch (a) {
			case 1:
				a = 1;
				break;
			case 1000:
				a = 2;
				break;
			default:
				a = 3;
				break;
			}
		}
	}
}