package com.google.test.metric.method;


import java.lang.Object;
import com.google.test.metric.ClassInfo;
import com.google.classpath.ClassPathFactory;
import com.google.test.metric.JavaClassRepository;
import java.lang.Class;
import com.google.test.metric.Cost;
import java.lang.String;
import com.google.test.metric.Type;
import com.google.classpath.ClassPath;
import com.google.test.metric.JavaType;
import com.google.test.metric.javasrc.JavaSrcRepository;
import junit.framework.TestCase;
import com.google.test.metric.ClassRepository;
import static java.util.Arrays.asList;

public class ConstantGenTest extends TestCase {
	/**
	 * Test method for the class com.google.test.metric.method.Constant
	 */
	public void testConstant910() throws Exception {
		Constant var2842 = new Constant(var2841, JavaType.fromClass(var2841
				.getClass()));
	}

	/**
	 * Test method for the class com.google.test.metric.method.Constant
	 */
	public void testConstant911() throws Exception {
		Constant var2843 = new Constant("a", JavaType.OBJECT);
	}

	/**
	 * Test method for the class com.google.test.metric.method.Constant
	 */
	public void testConstant912() throws Exception {
		Constant var2844 = new Constant("a", JavaType.INT);
	}

	public JavaSrcRepository instantiateJavaSrcRepository450() {
		ClassRepository var2845 = new JavaClassRepository();
		return new JavaSrcRepository(var2845,
				new ClassPathFactory().createFromPaths("core/src/test/java",
						"src/test/java"));
	}

	/**
	 * Test method for the class com.google.test.metric.method.Constant
	 */
	public void testConstant913() throws Exception {
		JavaSrcRepository var2846 = instantiateJavaSrcRepository450();
		Constant var2842 = new Constant(var2841, JavaType.fromClass(var2841
				.getClass()));
		var2846.getClass("pkg.A$B");
		var2846.getClass(getClass().getName());
	}

	public Cost instantiateCost451() {
		return new Cost();
	}

	/**
	 * Test method for the class com.google.test.metric.method.Constant
	 */
	public void testConstant914() throws Exception {
		Cost var2849 = instantiateCost451();
		Type var2850 = JavaType.fromClass(Object.class);
		Constant var2852 = new Constant(var2851, var2850);
		var2849.add(new Cost());
		var2849.add(new Cost());
	}
}