package com.google.test.metric;


import com.google.test.metric.RegExpWhiteList;
import java.lang.Object;
import java.net.InetAddress;
import com.google.classpath.ClassPathFactory;
import com.google.test.metric.ClassInfo;
import com.google.test.metric.JavaClassRepository;
import java.lang.String;
import com.google.test.metric.javasrc.JavaSrcRepository;
import junit.framework.TestCase;
import java.lang.Class;
import com.google.classpath.ClassPath;
import com.google.test.metric.ClassRepository;
import com.google.test.metric.ClassCost;
import static java.util.Arrays.asList;
import static java.util.Collections.EMPTY_LIST;
import static com.google.test.metric.report.issues.IssueSubType.COMPLEXITY;
import static com.google.test.metric.report.issues.IssueSubType.FINAL_METHOD;
import static com.google.test.metric.report.issues.IssueSubType.NON_MOCKABLE;
import static com.google.test.metric.report.issues.IssueSubType.SINGLETON;
import static com.google.test.metric.report.issues.IssueSubType.STATIC_METHOD;
import static com.google.test.metric.Reason.IMPLICIT_STATIC_INIT;

public class MetricComputerGenTest extends TestCase {
	public RegExpWhiteList instantiateRegExpWhiteList579() {
		return new RegExpWhiteList("String");
	}

	public JavaClassRepository instantiateJavaClassRepository580() {
		return new JavaClassRepository();
	}

	/**
	 * Test method for the class com.google.test.metric.MetricComputer
	 */
	public void testMetricComputer1035() throws Exception {
		RegExpWhiteList var3438 = instantiateRegExpWhiteList579();
		JavaClassRepository var3439 = instantiateJavaClassRepository580();
		MetricComputer var3440 = new MetricComputer(var3439, null, var3438, 1);
		var3438.isClassWhiteListed("java.lang.String");
		var3438.isClassWhiteListed("com.company.String");
		var3438.isClassWhiteListed("java.lang.String");
		var3438.isClassWhiteListed("com.company.String");
		var3439.getClass(InetAddress.class.getCanonicalName());
		var3439.getClass(EmptyClass.class.getCanonicalName());
		var3440.compute(Setters.class.getCanonicalName());
		var3440.compute(HasImplicitCostFromOtherClass.class.getCanonicalName());
	}

	public RegExpWhiteList instantiateRegExpWhiteList581() {
		return new RegExpWhiteList(".*String");
	}

	public JavaClassRepository instantiateJavaClassRepository582() {
		return new JavaClassRepository();
	}

	/**
	 * Test method for the class com.google.test.metric.MetricComputer
	 */
	public void testMetricComputer1036() throws Exception {
		RegExpWhiteList var3445 = instantiateRegExpWhiteList581();
		JavaClassRepository var3446 = instantiateJavaClassRepository582();
		MetricComputer var3447 = new MetricComputer(var3446, null, var3445, 1);
		var3445.isClassWhiteListed("java.lang.String");
		var3445.isClassWhiteListed("com.company.String");
		var3445.isClassWhiteListed("java.lang.String");
		var3445.isClassWhiteListed("com.company.String");
		var3446.getClass(InetAddress.class.getCanonicalName());
		var3446.getClass(LoDMultipleDifferentInvocations.class
				.getCanonicalName());
		var3447.compute(TestClass.class.getCanonicalName());
		var3447.compute(TestClass.class.getCanonicalName());
	}

	public RegExpWhiteList instantiateRegExpWhiteList583() {
		return new RegExpWhiteList("java.");
	}

	public JavaClassRepository instantiateJavaClassRepository584() {
		return new JavaClassRepository();
	}

	/**
	 * Test method for the class com.google.test.metric.MetricComputer
	 */
	public void testMetricComputer1037() throws Exception {
		RegExpWhiteList var3451 = instantiateRegExpWhiteList583();
		JavaClassRepository var3452 = instantiateJavaClassRepository584();
		ClassRepository var3453 = new JavaClassRepository();
		MetricComputer var3454 = new MetricComputer(var3453, null, var3451, 1);
		var3451.isClassWhiteListed("java.lang.String");
		var3451.isClassWhiteListed("com.company.String");
		var3451.addPackage("javax.");
		var3452.getClass(Object.class.getCanonicalName());
		var3452.getClass(var3457.getCanonicalName());
		var3452.getClass(Singleton.class.getCanonicalName());
		var3454.compute(HasImplicitCostFromOtherClass.class.getCanonicalName());
		var3454.compute(Setters.class.getCanonicalName());
	}

	public RegExpWhiteList instantiateRegExpWhiteList585() {
		return new RegExpWhiteList("java.");
	}

	public JavaSrcRepository instantiateJavaSrcRepository586() {
		ClassRepository var3459 = new JavaClassRepository();
		return new JavaSrcRepository(var3459,
				new ClassPathFactory().createFromPaths("core/src/test/java",
						"src/test/java"));
	}

	/**
	 * Test method for the class com.google.test.metric.MetricComputer
	 */
	public void testMetricComputer1038() throws Exception {
		RegExpWhiteList var3460 = instantiateRegExpWhiteList585();
		JavaSrcRepository var3461 = instantiateJavaSrcRepository586();
		MetricComputer var3462 = new MetricComputer(new JavaClassRepository(),
				null, null, 1);
		var3460.isClassWhiteListed("java.lang.String");
		var3460.isClassWhiteListed("com.company.String");
		var3460.isClassWhiteListed("java.lang.String");
		var3460.isClassWhiteListed("com.company.String");
		var3461.getClass(TypeQualifications.class.getName());
		var3461.getCachedClass(TypeQualifications.class.getName());
		var3461.getClass(My.class.getName());
		var3461.getClass("IDontExist");
		var3462.compute(TestClass.class.getCanonicalName());
		var3462.compute(Setters.class.getCanonicalName());
	}

	public RegExpWhiteList instantiateRegExpWhiteList587() {
		return new RegExpWhiteList();
	}

	public JavaSrcRepository instantiateJavaSrcRepository588() {
		ClassRepository var3464 = new JavaClassRepository();
		return new JavaSrcRepository(var3464, null);
	}

	/**
	 * Test method for the class com.google.test.metric.MetricComputer
	 */
	public void testMetricComputer1039() throws Exception {
		RegExpWhiteList var3465 = instantiateRegExpWhiteList587();
		JavaSrcRepository var3466 = instantiateJavaSrcRepository588();
		MetricComputer var3467 = new MetricComputer(new JavaClassRepository(),
				null, var3465, 1);
		var3465.isClassWhiteListed("java.lang.String");
		var3465.isClassWhiteListed("com.company.String");
		var3465.isClassWhiteListed("java.lang.String");
		var3465.isClassWhiteListed("com.company.String");
		var3466.getClass("pkg.A");
		var3466.getClass(getClass().getName());
		var3467.compute(HasImplicitCostFromOtherClass.class.getCanonicalName());
		var3467.compute(Setters.class.getCanonicalName());
	}

	private static class LoDMultipleDifferentInvocations {
		Obj plus2;

		public void execute(Obj plus0) {
			Obj plus1 = plus0.getValueA();
			plus2 = plus1.getValueB();
			plus2 = plus1;
		}
	}

	static class Singleton {
		private Singleton() {
			CostUtil.staticCost1();
		}

		public void doWork() {
			CostUtil.staticCost2();
		}
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

	public static class EmptyClass {
	}

	private static class Setters {
		boolean foo;

		public void setFoo(String foo) {
			this.foo = (foo == null);
		}
	}

	static class TestClass {
		void m1() {
			CostUtil.staticCost4();
		}

		void m2() {
			CostUtil.staticCost3();
		}
	}

	static class HasImplicitCostFromOtherClass extends HasSetterCost {
		public void doThing() {
		}
	}
}