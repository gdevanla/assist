package test.jdepend;

import jdepend.ClassFileParser;
import jdepend.JDepend;
import jdepend.JavaClass;
import jdepend.PackageFilter;
import jdepend.DependencyConstraint;
import jdepend.JavaPackage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author <b>Mike Clark</b>
 * @author Clarkware Consulting, Inc.
 */

public class ConstraintTest extends JDependTestCase {

    private JDepend jdepend;

    public ConstraintTest(String name) {
        super(name);
    }

    protected void setUp() {
        super.setUp();
        PackageFilter filter = new PackageFilter();
        filter.addPackage("java.*");
        filter.addPackage("javax.*");
        jdepend = new JDepend(filter);
    }

    public void testMatchPass() {

        DependencyConstraint constraint = new DependencyConstraint();

        jdepend.framework.JavaPackage expectedA = constraint.addPackage("A");
        jdepend.framework.JavaPackage expectedB = constraint.addPackage("B");
        jdepend.framework.JavaPackage expectedC = constraint.addPackage("A");
        
        expectedA.dependsUpon(expectedB);

        JavaPackage actualA = new JavaPackage("A");
        JavaPackage actualB = new JavaPackage("B");

        actualA.dependsUpon(actualB);

        jdepend.addPackage(actualA);
        jdepend.addPackage(actualB);

        assertFalse(jdepend.dependencyMatch(constraint));
    }

    public void testMatchFail() {

        DependencyConstraint constraint = new DependencyConstraint();

        jdepend.framework.JavaPackage expectedA = constraint.addPackage("A");
        jdepend.framework.JavaPackage expectedB = constraint.addPackage("B");
        jdepend.framework.JavaPackage expectedC = constraint.addPackage("C");

        expectedA.dependsUpon(expectedB);

        JavaPackage actualA = new JavaPackage("A");
        JavaPackage actualB = new JavaPackage("B");
        JavaPackage actualC = new JavaPackage("C");

        actualA.dependsUpon(actualB);
        actualA.dependsUpon(actualC);

        jdepend.addPackage(actualA);
        jdepend.addPackage(actualB);
        jdepend.addPackage(actualC);

        assertFalse(jdepend.dependencyMatch(constraint));
    }

    public void testJDependConstraints() throws IOException {
        
        jdepend.addDirectory(getBuildDir());

        jdepend.analyze();

        DependencyConstraint constraint = new DependencyConstraint();

        jdepend.framework.JavaPackage junitframework = constraint.addPackage("junit.framework");
        jdepend.framework.JavaPackage junitui = constraint.addPackage("junit.textui");
        jdepend.framework.JavaPackage framework = constraint.addPackage("jdepend.framework");
        jdepend.framework.JavaPackage text = constraint.addPackage("jdepend.textui");
        jdepend.framework.JavaPackage xml = constraint.addPackage("jdepend.xmlui");
        jdepend.framework.JavaPackage swing = constraint.addPackage("jdepend.swingui");

        framework.dependsUpon(junitframework);
        framework.dependsUpon(junitui);
        text.dependsUpon(framework);
        xml.dependsUpon(text);
        swing.dependsUpon(framework);

        assertFalse(jdepend.dependencyMatch(constraint));
    }
    /**
     * @author icewariya
     */
    public void testAddPackageExample1() {

        DependencyConstraint constraint = new DependencyConstraint();

        constraint.addPackage("A");
        constraint.addPackage("B");
        
        Collection packages = constraint.getPackages();
        assertEquals(2, packages.size());
    }
    
    public void testMatchExample1() {

        DependencyConstraint constraint = new DependencyConstraint();
        jdepend.framework.JavaPackage actualA = new jdepend.framework.JavaPackage("A");
        jdepend.framework.JavaPackage actualB = new jdepend.framework.JavaPackage("B");
        constraint.addPackage(actualA);
        constraint.addPackage(actualB);
        
        Collection expected = constraint.getPackages();

        assertTrue(constraint.match(expected));
    }
    
    public void testMatchExample2() throws Exception{
    	
    	DependencyConstraint constraint = new DependencyConstraint();

    	ClassFileParser parser = new ClassFileParser();
    	File f = new File(getBuildDir() + getPackageSubDir() +
                "ExampleConcreteClass.class");

    	JavaClass clazz = parser.parse(f);
    	Collection imports = clazz.getImportedPackages();
    	
    	assertFalse(constraint.match(imports));
    	
    	
    }
    
    public void testMatchPackageExample1() throws Exception{
    	DependencyConstraint constraint = new DependencyConstraint();
    	jdepend.framework.JavaPackage actualA = new jdepend.framework.JavaPackage("A");
    	
    	Class c 	= constraint.getClass();
    	Object[] params = new Object[1];
    	params[0] = actualA;
    	Class[] types 	= new Class[params.length];
    	types[0] = params[0].getClass();
    	
    	Method method = c.getDeclaredMethod("matchPackage", types);
    	method.setAccessible( true );
    	assertFalse((Boolean) method.invoke(constraint, params));
    }
    
    public void testMatchPackageExample2() throws Exception{
    	DependencyConstraint constraint = new DependencyConstraint();
    	
    	jdepend.framework.JavaPackage actualA = new jdepend.framework.JavaPackage("A");
    	jdepend.framework.JavaPackage actualB= new jdepend.framework.JavaPackage("B");
		List efferents = new ArrayList();
		efferents.add(actualB);
		actualA.setEfferents(efferents);
    	
		constraint.addPackage(actualB);
		
    	Class c 	= constraint.getClass();
    	Object[] params = new Object[1];
    	params[0] = actualA;
    	Class[] types 	= new Class[params.length];
    	types[0] = params[0].getClass();
    	
    	Method method = c.getDeclaredMethod("matchPackage", types);
    	method.setAccessible( true );
    	
    	assertFalse((Boolean) method.invoke(constraint, params));
    }
    
    public void testEqualsDependenciesExample1() throws Exception{
    	DependencyConstraint constraint = new DependencyConstraint();
    	
    	jdepend.framework.JavaPackage actualA = new jdepend.framework.JavaPackage("A");
    	jdepend.framework.JavaPackage actualB = new jdepend.framework.JavaPackage("B");
    	
    	Class c 	= constraint.getClass();
    	Object[] params = new Object[2];
    	params[0] = actualA;
    	params[1] = actualB;
    	Class[] types 	= new Class[params.length];
    	types[0] = params[0].getClass();
    	types[1] = params[1].getClass(); 
    	
    	Method method = c.getDeclaredMethod("equalsDependencies", types);
    	method.setAccessible( true );
    	assertFalse((Boolean) method.invoke(constraint, params));
    }
    
    public void testEqualsDependenciesExample2() throws Exception{
    	DependencyConstraint constraint = new DependencyConstraint();
    	
    	jdepend.framework.JavaPackage actualA = new jdepend.framework.JavaPackage("A");
		
    	Class c 	= constraint.getClass();
    	Object[] params = new Object[2];
    	params[0] = actualA;
    	params[1] = actualA;
    	Class[] types 	= new Class[params.length];
    	types[0] = params[0].getClass();
    	types[1] = params[1].getClass(); 
    	
    	Method method = c.getDeclaredMethod("equalsDependencies", types);
    	method.setAccessible( true );
    	assertTrue((Boolean) method.invoke(constraint, params));
    }
    
    public void testEqualsAfferentsExample1() throws Exception{
    	DependencyConstraint constraint = new DependencyConstraint();
    	
    	jdepend.framework.JavaPackage actualA = new jdepend.framework.JavaPackage("A");
		
    	Class c 	= constraint.getClass();
    	Object[] params = new Object[2];
    	params[0] = actualA;
    	params[1] = actualA;
    	Class[] types 	= new Class[params.length];
    	types[0] = params[0].getClass();
    	types[1] = params[1].getClass(); 
    	
    	Method method = c.getDeclaredMethod("equalsAfferents", types);
    	method.setAccessible( true );
    	assertTrue((Boolean) method.invoke(constraint, params));
    }
    
    public void testEqualsAfferentsExample2() throws Exception{
    	DependencyConstraint constraint = new DependencyConstraint();
    	
    	jdepend.framework.JavaPackage actualA = new jdepend.framework.JavaPackage("A");
    	jdepend.framework.JavaPackage actualB = new jdepend.framework.JavaPackage("B");
    	jdepend.framework.JavaPackage actualC = new jdepend.framework.JavaPackage("C");
    	jdepend.framework.JavaPackage actualD = new jdepend.framework.JavaPackage("D");
    	
    	List afferents = new ArrayList();
		afferents.add(actualD);
		afferents.add(actualC);
		actualA.setAfferents(afferents);
		
    	Class c 	= constraint.getClass();
    	Object[] params = new Object[2];
    	params[0] = actualA;
    	params[1] = actualB;
    	Class[] types 	= new Class[params.length];
    	types[0] = params[0].getClass();
    	types[1] = params[1].getClass(); 
    	
    	Method method = c.getDeclaredMethod("equalsAfferents", types);
    	method.setAccessible( true );
    	assertFalse((Boolean) method.invoke(constraint, params));
    }
   
    public void testEqualsEfferentsExample1() throws Exception{
    	DependencyConstraint constraint = new DependencyConstraint();
    	
    	jdepend.framework.JavaPackage actualA = new jdepend.framework.JavaPackage("A");
		
    	Class c 	= constraint.getClass();
    	Object[] params = new Object[2];
    	params[0] = actualA;
    	params[1] = actualA;
    	Class[] types 	= new Class[params.length];
    	types[0] = params[0].getClass();
    	types[1] = params[1].getClass(); 
    	
    	Method method = c.getDeclaredMethod("equalsEfferents", types);
    	method.setAccessible( true );
    	assertTrue((Boolean) method.invoke(constraint, params));
    }
    
    public void testEqualsEfferentsExample2() throws Exception{
    	DependencyConstraint constraint = new DependencyConstraint();
    	
    	jdepend.framework.JavaPackage actualA = new jdepend.framework.JavaPackage("A");
    	jdepend.framework.JavaPackage actualB = new jdepend.framework.JavaPackage("B");
    	jdepend.framework.JavaPackage actualC = new jdepend.framework.JavaPackage("C");
    	jdepend.framework.JavaPackage actualD = new jdepend.framework.JavaPackage("D");
    	
    	List efferents = new ArrayList();
		efferents.add(actualD);
		efferents.add(actualC);
		actualA.setEfferents(efferents);
		
    	Class c 	= constraint.getClass();
    	Object[] params = new Object[2];
    	params[0] = actualA;
    	params[1] = actualB;
    	Class[] types 	= new Class[params.length];
    	types[0] = params[0].getClass();
    	types[1] = params[1].getClass(); 
    	
    	Method method = c.getDeclaredMethod("equalsEfferents", types);
    	method.setAccessible( true );
    	assertFalse((Boolean) method.invoke(constraint, params));
    }
    
    
}