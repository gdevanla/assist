package test.jdepend.framework;

import jdepend.framework.ClassFileParser;

import jdepend.framework.AbstractParser;
import jdepend.framework.PackageFilter;
import jdepend.framework.JavaClass;
import jdepend.framework.JavaPackage;
import jdepend.framework.ParserListener;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author <b>Mike Clark</b>
 * @author Clarkware Consulting, Inc.
 */

public class ClassFileParserTest extends JDependTestCase{
	
    private ClassFileParser parser;

    public ClassFileParserTest(String name) {
        super(name);
    }

    protected void setUp() {
        super.setUp();
        PackageFilter filter = new PackageFilter(new ArrayList());
        parser = new ClassFileParser(filter);
    }

    protected void tearDown() {
        super.tearDown();
    }

    public void testInvalidClassFile() {

        File f = new File(getTestDir() + getPackageSubDir() + 
                          "ExampleTest.java");

        try {

            parser.parse(f);
            fail("Invalid class file: Should raise IOException");

        } catch (IOException expected) {
            assertTrue(true);
        }
    }

    public void testInterfaceClass() throws IOException {

        File f = new File(getBuildDir() + getPackageSubDir() +
                          "ExampleInterface.class");
        
        JavaClass clazz = parser.parse(f);

        assertTrue(clazz.isAbstract());

        assertEquals("jdepend.framework.ExampleInterface", clazz.getName());

        assertEquals("ExampleInterface.java", clazz.getSourceFile());

        Collection imports = clazz.getImportedPackages();
        assertEquals(6, imports.size());

        assertTrue(imports.contains(new JavaPackage("java.math")));
        assertTrue(imports.contains(new JavaPackage("java.text")));
        assertTrue(imports.contains(new JavaPackage("java.lang")));
        assertTrue(imports.contains(new JavaPackage("java.io")));
        assertTrue(imports.contains(new JavaPackage("java.rmi")));
        assertTrue(imports.contains(new JavaPackage("java.util")));
    }

    public void testAbstractClass() throws IOException {

        File f = new File(getBuildDir() + getPackageSubDir() +
                          "ExampleAbstractClass.class");
        
        JavaClass clazz = parser.parse(f);

        assertTrue(clazz.isAbstract());

        assertEquals("jdepend.framework.ExampleAbstractClass", clazz.getName());

        assertEquals("ExampleAbstractClass.java", clazz.getSourceFile());

        Collection imports = clazz.getImportedPackages();
        assertEquals(7, imports.size());

        assertTrue(imports.contains(new JavaPackage("java.math")));
        assertTrue(imports.contains(new JavaPackage("java.text")));
        assertTrue(imports.contains(new JavaPackage("java.lang")));
        assertTrue(imports.contains(new JavaPackage("java.lang.reflect")));
        assertTrue(imports.contains(new JavaPackage("java.io")));
        assertTrue(imports.contains(new JavaPackage("java.rmi")));
        assertTrue(imports.contains(new JavaPackage("java.util")));
    }

    public void testConcreteClass() throws IOException {

        File f = new File(getBuildDir() + getPackageSubDir() +
                          "ExampleConcreteClass.class");
        
        JavaClass clazz = parser.parse(f);

        assertFalse(clazz.isAbstract());

        assertEquals("jdepend.framework.ExampleConcreteClass", clazz.getName());

        assertEquals("ExampleConcreteClass.java", clazz.getSourceFile());

        Collection imports = clazz.getImportedPackages();
        assertEquals(9, imports.size());

        assertTrue(imports.contains(new JavaPackage("java.net")));
        assertTrue(imports.contains(new JavaPackage("java.text")));
        assertTrue(imports.contains(new JavaPackage("java.sql")));
        assertTrue(imports.contains(new JavaPackage("java.lang")));
        assertTrue(imports.contains(new JavaPackage("java.io")));
        assertTrue(imports.contains(new JavaPackage("java.rmi")));
        assertTrue(imports.contains(new JavaPackage("java.util")));
        assertTrue(imports.contains(new JavaPackage("java.util.jar")));
        assertTrue(imports.contains(new JavaPackage("java.math")));
    }

    public void testInnerClass() throws IOException {

        File f = new File(getBuildDir() + getPackageSubDir() +
                          "ExampleConcreteClass$ExampleInnerClass.class");
        
        JavaClass clazz = parser.parse(f);

        assertFalse(clazz.isAbstract());

        assertEquals("jdepend.framework.ExampleConcreteClass$ExampleInnerClass",
                clazz.getName());

        assertEquals("ExampleConcreteClass.java", clazz.getSourceFile());

        Collection imports = clazz.getImportedPackages();
        assertEquals(1, imports.size());

        assertTrue(imports.contains(new JavaPackage("java.lang")));

    }

    public void testPackageClass() throws IOException {

        File f = new File(getBuildDir() + getPackageSubDir() +
                          "ExamplePackageClass.class");
        
        JavaClass clazz = parser.parse(f);

        assertFalse(clazz.isAbstract());

        assertEquals("jdepend.framework.ExamplePackageClass", clazz.getName());

        assertEquals("ExampleConcreteClass.java", clazz.getSourceFile());

        Collection imports = clazz.getImportedPackages();
        assertEquals(1, imports.size());

        assertTrue(imports.contains(new JavaPackage("java.lang")));

    }
    /**
     * @author icewariya
     * @throws IOException
     */
    public void testPrivateReset() throws Exception {
    	ClassFileParser parse = new ClassFileParser();
    	
    	Class c = parse.getClass();
		Field className = c.getDeclaredField( "className" );
		className.setAccessible( true );
		
		Field superClassName = c.getDeclaredField( "superClassName" );
		superClassName.setAccessible( true );
		
		Field interfaceNames = c.getDeclaredField( "interfaceNames" );
		interfaceNames.setAccessible( true );
		String nameInterface[] = (String[])interfaceNames.get(parse);
				
		Field isAbstract = c.getDeclaredField( "isAbstract" );
		isAbstract.setAccessible( true );
		
		Field jClass = c.getDeclaredField( "jClass" );
		jClass.setAccessible( true );

		assertEquals(null, className.get(parse));
		assertEquals(null, superClassName.get(parse));
		assertEquals(0, nameInterface.length);
		assertFalse((Boolean) isAbstract.get(parse));
		assertEquals(null, jClass.get(parse));
    }
    
    public void testInvalidClassInputStream() {

        File f = new File(getTestDir() + getPackageSubDir() + 
                          "ExampleTest.java");
        FileInputStream in = null;
        try {
        	in = new FileInputStream(f);
            parser.parse(in);
            fail("Invalid class file: Should raise IOException");

        } catch (IOException expected) {
            assertTrue(true);
        }
    }

    public void testInterfaceClassInputStream() throws IOException {

        File f = new File(getBuildDir() + getPackageSubDir() +
                          "ExampleInterface.class");
        FileInputStream in = new FileInputStream(f);
        
        JavaClass clazz = parser.parse(in);

        assertTrue(clazz.isAbstract());

        assertEquals("jdepend.framework.ExampleInterface", clazz.getName());

        assertEquals("ExampleInterface.java", clazz.getSourceFile());

        Collection imports = clazz.getImportedPackages();
        assertEquals(6, imports.size());

        assertTrue(imports.contains(new JavaPackage("java.math")));
        assertTrue(imports.contains(new JavaPackage("java.text")));
        assertTrue(imports.contains(new JavaPackage("java.lang")));
        assertTrue(imports.contains(new JavaPackage("java.io")));
        assertTrue(imports.contains(new JavaPackage("java.rmi")));
        assertTrue(imports.contains(new JavaPackage("java.util")));
    }

    public void testAbstractClassInputStream() throws IOException {

        File f = new File(getBuildDir() + getPackageSubDir() +
                          "ExampleAbstractClass.class");
        FileInputStream in = new FileInputStream(f);
        JavaClass clazz = parser.parse(in);

        assertTrue(clazz.isAbstract());

        assertEquals("jdepend.framework.ExampleAbstractClass", clazz.getName());

        assertEquals("ExampleAbstractClass.java", clazz.getSourceFile());

        Collection imports = clazz.getImportedPackages();
        assertEquals(7, imports.size());

        assertTrue(imports.contains(new JavaPackage("java.math")));
        assertTrue(imports.contains(new JavaPackage("java.text")));
        assertTrue(imports.contains(new JavaPackage("java.lang")));
        assertTrue(imports.contains(new JavaPackage("java.lang.reflect")));
        assertTrue(imports.contains(new JavaPackage("java.io")));
        assertTrue(imports.contains(new JavaPackage("java.rmi")));
        assertTrue(imports.contains(new JavaPackage("java.util")));
    }

    public void testConcreteClassInputStream() throws IOException {

        File f = new File(getBuildDir() + getPackageSubDir() +
                          "ExampleConcreteClass.class");
        FileInputStream in = new FileInputStream(f);
        JavaClass clazz = parser.parse(in);

        assertFalse(clazz.isAbstract());

        assertEquals("jdepend.framework.ExampleConcreteClass", clazz.getName());

        assertEquals("ExampleConcreteClass.java", clazz.getSourceFile());

        Collection imports = clazz.getImportedPackages();
        assertEquals(9, imports.size());

        assertTrue(imports.contains(new JavaPackage("java.net")));
        assertTrue(imports.contains(new JavaPackage("java.text")));
        assertTrue(imports.contains(new JavaPackage("java.sql")));
        assertTrue(imports.contains(new JavaPackage("java.lang")));
        assertTrue(imports.contains(new JavaPackage("java.io")));
        assertTrue(imports.contains(new JavaPackage("java.rmi")));
        assertTrue(imports.contains(new JavaPackage("java.util")));
        assertTrue(imports.contains(new JavaPackage("java.util.jar")));
        assertTrue(imports.contains(new JavaPackage("java.math")));
    }

    public void testInnerClassInputStream() throws IOException {

        File f = new File(getBuildDir() + getPackageSubDir() +
                          "ExampleConcreteClass$ExampleInnerClass.class");
        FileInputStream in = new FileInputStream(f);
        JavaClass clazz = parser.parse(in);

        assertFalse(clazz.isAbstract());

        assertEquals("jdepend.framework.ExampleConcreteClass$ExampleInnerClass",
                clazz.getName());

        assertEquals("ExampleConcreteClass.java", clazz.getSourceFile());

        Collection imports = clazz.getImportedPackages();
        assertEquals(1, imports.size());

        assertTrue(imports.contains(new JavaPackage("java.lang")));

    }

    public void testPackageClassInputStream() throws IOException {

        File f = new File(getBuildDir() + getPackageSubDir() +
                          "ExamplePackageClass.class");
        FileInputStream in = new FileInputStream(f);
        JavaClass clazz = parser.parse(in);

        assertFalse(clazz.isAbstract());

        assertEquals("jdepend.framework.ExamplePackageClass", clazz.getName());

        assertEquals("ExampleConcreteClass.java", clazz.getSourceFile());

        Collection imports = clazz.getImportedPackages();
        assertEquals(1, imports.size());

        assertTrue(imports.contains(new JavaPackage("java.lang")));

    }
    
    public void testToStringPackageClass() throws Exception {

        File f = new File(getBuildDir() + getPackageSubDir() +
                          "ExamplePackageClass.class");
        FileInputStream in = new FileInputStream(f);
        JavaClass clazz = parser.parse(in);

        assertEquals("Constants", parser.toString().substring(41, 50));

    }
    
    public void testToStringInterface() throws Exception {

        File f = new File(getBuildDir() + getPackageSubDir() +
                          "ExampleInterface.class");
        FileInputStream in = new FileInputStream(f);
        JavaClass clazz = parser.parse(in);

        assertEquals("Constants", parser.toString().substring(38, 47));

    }
    
    public void testMainExample1() throws Exception {

        File f = new File(getBuildDir() + getPackageSubDir() +
                          "ExampleInterface.class");
        String[] args = new String[1];
        args[0] = f.toString();
        ClassFileParser.main(args);

        assertTrue(ClassFileParser.DEBUG);

    }
   
}    