package test.jdepend.framework;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import jdepend.framework.ClassFileParser;
import jdepend.framework.JavaClass;
import jdepend.framework.JavaClass.ClassComparator;
import jdepend.framework.JavaPackage;
import jdepend.framework.PackageComparator;
import jdepend.framework.PackageFilter;

/**
 * 
 * @author icewariya
 *
 */
public class JavaClassTest extends JDependTestCase{
	private JavaClass clazz;

	public JavaClassTest(String name) {
		super(name);
	}

    protected void setUp() {
        super.setUp();
        clazz = new JavaClass("ExampleInterface.class");
    }
    
	protected void tearDown() {
		super.tearDown();
	}
	
	 public void testJavaClassVariables() {	        
	        assertEquals("ExampleInterface.class", clazz.getName());
	        assertEquals("default", clazz.getPackageName());
	        assertFalse(clazz.isAbstract());
	        assertEquals("Unknown", clazz.getSourceFile());
	    }
	 
	 public void testClassName() {
	        clazz.setName("ExampleTest.java");
	        assertEquals("ExampleTest.java", clazz.getName());
	    }
	 
	 public void testPackageName() {
	        clazz.setPackageName("Example");
	        assertEquals("Example", clazz.getPackageName());
	    }
	 
	 public void testIsAbstract() {
	        clazz.isAbstract(true);
	        assertTrue(clazz.isAbstract());
	    }
	 
	 public void testSourceFile() {
	        clazz.setSourceFile("Source");
	        assertEquals("Source", clazz.getSourceFile());
	    }
	 
	 public void testImportedPackageExample1() {
		 JavaPackage a = new JavaPackage("A");
		 JavaPackage b = new JavaPackage("B");
		 JavaPackage c = new JavaPackage("C");
		 clazz.addImportedPackage(a);
		 clazz.addImportedPackage(b);
	     
		 Collection pkgs = clazz.getImportedPackages();
	     
		 assertTrue(pkgs.contains(a));
		 assertTrue(pkgs.contains(b));
		 assertFalse(pkgs.contains(c));
	    }
	 
	 public void testImportedPackageExample2() {
		 JavaPackage a = new JavaPackage("default");
		 clazz.addImportedPackage(a);
		 
		 Collection pkgs = clazz.getImportedPackages();
	     
		 assertFalse(pkgs.contains(a));
	    }
	 
	 public void testEqualsExample1() {
		 
		 Object other = new JavaClass("Example");
	     
		 assertFalse(clazz.equals(other));
	    }
	 
	 public void testEqualsExample2() {
		 
	     assertTrue(clazz.equals(clazz));
	    }
	 
	 public void testEqualsExample3() {
		 Object other = new JavaPackage("A");
	     assertFalse(clazz.equals(other));
	    }
	 
	 public void testHashCode() {
		 int hashCode = clazz.hashCode();
		 assertEquals(hashCode, clazz.hashCode());
	    }
	 
	 public void testCompare() {
		 ClassComparator comp = new ClassComparator();
		 Object other = new JavaClass("Example");
		 
		 assertEquals(15, comp.compare(clazz, other));
		 assertEquals(0, comp.compare(other, other));
	    }
}

