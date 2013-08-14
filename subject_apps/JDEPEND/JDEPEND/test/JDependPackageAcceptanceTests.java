package test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;

import test.jdepend.JDependTestCase;

import jdepend.ClassFileParser;
import jdepend.PackageFilter;

public class JDependPackageAcceptanceTests extends JDependTestCase{

	private ClassFileParser parser;
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	public JDependPackageAcceptanceTests(String name) {
		super(name);
	}

	protected void setUp() {
		super.setUp();
		PackageFilter filter = new PackageFilter(new ArrayList());
		parser = new ClassFileParser(filter);
		System.setErr(new PrintStream(errContent));
		System.setSecurityManager(new NoExitSecurityManager());
	}

	protected void tearDown() {
		System.setErr(null);
		System.setSecurityManager(null);
		super.tearDown();
	}

	// positive test case
	public void testAcceptanceExample1() throws Exception {

		File f = new File(getBuildDir() + getPackageSubDir() +
				"ExampleInterface.class");
		String[] args = new String[1];
		args[0] = f.toString();
		ClassFileParser.main(args);

		assertTrue(ClassFileParser.DEBUG);
		assertTrue(errContent.toString().contains("Parser: class name = jdepend.framework.ExampleInterface"));

	}

	// empty input
	public void testAcceptanceExample2() throws Exception {

		String[] args = new String[0];
		try {
			ClassFileParser.main(args);
		} catch(ExitException e) {
			assertTrue(errContent.toString().contains("usage: ClassFileParser <class-file>"));
		}

	}

	// input is not expected type
	public void testAcceptanceExample3() throws Exception {

		File f = new File(getTestDataDir() +
				"ExitException.java");
		String[] args = new String[1];
		args[0] = f.toString();
		try {
			ClassFileParser.main(args);
		} catch(Exception e) {
			assertTrue(errContent.toString().contains(e.getMessage()));
		}
	}

	// null input
	public void testAcceptanceExample4() throws Exception {
		String[] args = new String[1];
		args[0] = null;
		try {
			ClassFileParser.main(args);
		} catch(Exception e) {
			assertTrue(errContent.toString().contains(e.getMessage()));
		}
	}

	// two class files is given as input
	public void testAcceptanceExample5() throws Exception {

		File f1 = new File(getBuildDir() + getPackageSubDir() +
				"ExampleInterface.class");
		File f2 = new File(getBuildDir() + getPackageSubDir() +
				"ExampleConcreteClass.class");
		String[] args = new String[2];
		args[0] = f1.toString();
		args[1] = f2.toString();
		try {
			ClassFileParser.main(args);
		} catch(ExitException e) {
			assertTrue(errContent.toString().contains("usage: ClassFileParser <class-file>"));
		}
	}

	// same class file is given as input
	public void testAcceptanceExample6() throws Exception {

		File f1 = new File(getBuildDir() + getPackageSubDir() +
				"ExampleInterface.class");
		File f2 = new File(getBuildDir() + getPackageSubDir() +
				"ExampleInterface.class");
		String[] args = new String[2];
		args[0] = f1.toString();
		args[1] = f2.toString();
		try {
			ClassFileParser.main(args);
		} catch(ExitException e) {
			assertTrue(errContent.toString().contains("usage: ClassFileParser <class-file>"));
		}
	}

	// file which does not exist is given as input
	public void testAcceptanceExample7() throws Exception {

		File f1 = new File(getBuildDir() + getPackageSubDir() +
				"Example.class");
		String[] args = new String[1];
		args[0] = f1.toString();
		try {
			ClassFileParser.main(args);
		} catch(ExitException e) {
			assertTrue(errContent.toString().contains("usage: ClassFileParser <class-file>"));
		}
	}
	public void testAcceptanceExample8() throws Exception {

		File f = new File(getBuildDir() + getPackageSubDir() +
				"ExampleAbstractClass.class");
		String[] args = new String[1];
		args[0] = f.toString();
		try {
			ClassFileParser.main(args);
		} catch(ExitException e) {
			assertTrue(errContent.toString().contains("usage: ClassFileParser <class-file>"));
		}
	}

	public void testAcceptanceExample9() throws Exception {

		File f = new File(getBuildDir() + getPackageSubDir() +
				"ExampleConcreteClass.class");
		String[] args = new String[1];
		args[0] = f.toString();
		try {
			ClassFileParser.main(args);
		} catch(ExitException e) {
			assertTrue(errContent.toString().contains("usage: ClassFileParser <class-file>"));
		}
	}

	public void testAcceptanceExample10() throws Exception {

		File f = new File(getBuildDir() + getPackageSubDir() +
				"ExampleConcreteClass$ExampleInnerClass.class");
		String[] args = new String[1];
		args[0] = f.toString();
		try {
			ClassFileParser.main(args);
		} catch(ExitException e) {
			assertTrue(errContent.toString().contains("usage: ClassFileParser <class-file>"));
		}
	}

	public void testAcceptanceExample11() throws Exception {

		File f = new File(getBuildDir() + getPackageSubDir() +
				"ExamplePackageClass.class");
		String[] args = new String[1];
		args[0] = f.toString();
		try {
			ClassFileParser.main(args);
		} catch(ExitException e) {
			assertTrue(errContent.toString().contains("usage: ClassFileParser <class-file>"));
		}
	}
}
