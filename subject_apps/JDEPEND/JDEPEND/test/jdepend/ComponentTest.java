package test.jdepend;

import jdepend.DependencyConstraint;
import jdepend.JDepend;
import jdepend.JavaClass;
import jdepend.JavaPackage;
import jdepend.PackageFilter;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Iterator;

import junit.framework.*;

/**
 * @author <b>Mike Clark</b> 
 * @author Clarkware Consulting, Inc.
 */

public class ComponentTest extends JDependTestCase {

    private JDepend jdepend;
    private static NumberFormat formatter;

    static {
        formatter = NumberFormat.getInstance();
        formatter.setMaximumFractionDigits(2);
    }
    
    public ComponentTest(String name) {
        super(name);
    }

    protected void setUp() {
        super.setUp();
        jdepend = new JDepend();
        jdepend.analyzeInnerClasses(false);
    }

    protected void tearDown() {
        super.tearDown();
    }
    
    public void testJDependComponentsExample1() throws Exception {

        jdepend.setComponents("jdepend,junit,java,javax");
        
        jdepend.addDirectory(getBuildDir());
        
        jdepend.analyze();

        Collection packages = jdepend.getPackages();

        assertEquals(3, packages.size());
        assertEquals(3, jdepend.countPackages());
        assertEquals(26, jdepend.countClasses());
        assertFalse(jdepend.containsCycles());
        
    }
    
    public void testJDependComponentsExample2() throws Exception {

        jdepend.setComponents("jdepend,junit,java,javax");
        
        jdepend.addDirectory(getBuildDir());
        
        jdepend.analyze();
        
        JavaPackage A = jdepend.getPackage("jdepend");
        JavaPackage B = jdepend.getPackage("java");
        
        A.dependsUpon(B);
        B.dependsUpon(A);
        
        assertTrue(jdepend.containsCycles());
    }
    
    public void testJDependComponentsExample3() throws Exception {

        jdepend.setComponents("jdepend,junit,java,javax");
        
        jdepend.addDirectory(getBuildDir());
        
        jdepend.analyze();
        
        assertJDependPackage();
        //assertJUnitPackage();
        assertJavaPackage();
        assertJavaxPackage();
        
    }
    
    public void testJDependComponentsExample4() throws Exception {

    	DependencyConstraint constraint = new DependencyConstraint();
        jdepend.setComponents("jdepend,junit,java,javax");
        
        jdepend.addDirectory(getBuildDir());
        
        jdepend.analyze();
        
        jdepend.framework.JavaPackage junitframework = constraint.addPackage("junit.framework");
        
        assertFalse(jdepend.dependencyMatch(constraint));
        
    }
    
    public void testAddPackageExample1() throws Exception {

        jdepend.setComponents("jdepend,junit,java,javax");
        
        jdepend.addDirectory(getBuildDir());
        
        jdepend.analyze();

        Collection packages = jdepend.getPackages();
        
        JDepend jd = new JDepend();
        jd.addPackages(packages);

        assertEquals(3, jd.countPackages());
        assertEquals(0, jd.countClasses());
        assertFalse(jd.containsCycles());
        
    } 
    
    public void testAddPackageExample2() throws Exception {

    	DependencyConstraint constraint = new DependencyConstraint();
        jdepend.setComponents("jdepend,junit,java,javax");
        
        jdepend.addDirectory(getBuildDir());
        
        jdepend.analyze();
        
        assertEquals("junit", jdepend.addPackage("junit").getName());
        assertEquals("jdepend", jdepend.addPackage("jdepend.framework").getName());
        
        
    }

    public void testAddPackageExample3() throws Exception {

    	DependencyConstraint constraint = new DependencyConstraint();
        jdepend.setComponents("jdepend,junit,java,javax");
        
        jdepend.addDirectory(getBuildDir());
        
        jdepend.analyze();
        
        JavaPackage p = jdepend.getPackage("jdepend");
        jdepend.addPackage(p);
        
        assertEquals(3, jdepend.countPackages());
        
        
    }
    
    public void testSetFilterExample1() throws Exception {
        
        PackageFilter filter = new PackageFilter();
        jdepend.setFilter(filter);
        
        assertEquals(filter, jdepend.getFilter());
        
    }
    
    public void testSetFilterExample2() throws Exception {
        
        jdepend.setFilter(null);
        
        assertEquals(PackageFilter.class, jdepend.getFilter().getClass());
        
    }
    
    private void assertJDependPackage() {
        JavaPackage p = jdepend.getPackage("jdepend");
        assertEquals("jdepend", p.getName());
        assertEquals(21, p.getConcreteClassCount());
        assertEquals(5, p.getAbstractClassCount());
        assertEquals(0, p.afferentCoupling());
        assertEquals(2, p.efferentCoupling());
        assertEquals(format(0.19f), format(p.abstractness()));
        assertEquals("1", format(p.instability()));
        assertEquals(format(0.19f), format(p.distance()));
        assertEquals(1, p.getVolatility());
        
        Collection efferents = p.getEfferents();
        assertEquals(2, efferents.size());
        assertTrue(efferents.contains(new JavaPackage("java")));
        assertTrue(efferents.contains(new JavaPackage("javax")));
        assertFalse(efferents.contains(new JavaPackage("junit")));
        
        Collection afferents = p.getAfferents();
        assertEquals(0, afferents.size());
 
        
    }

    private void assertJUnitPackage() {
        JavaPackage p = jdepend.getPackage("junit");
        assertEquals("junit", p.getName());
        
        Collection afferents = p.getAfferents();
        assertEquals(1, afferents.size());
        assertTrue(afferents.contains(new JavaPackage("jdepend")));
        
        Collection efferents = p.getEfferents();
        assertEquals(0, efferents.size());

    }
    
    private void assertJavaPackage() {
        JavaPackage p = jdepend.getPackage("java");
        assertEquals("java", p.getName());
        
        Collection afferents = p.getAfferents();
        assertEquals(1, afferents.size());
        assertTrue(afferents.contains(new JavaPackage("jdepend")));
        
        Collection efferents = p.getEfferents();
        assertEquals(0, efferents.size());

    }
    
    private void assertJavaxPackage() {
        JavaPackage p = jdepend.getPackage("javax");
        assertEquals("javax", p.getName());
        
        Collection afferents = p.getAfferents();
        assertEquals(1, afferents.size());
        assertTrue(afferents.contains(new JavaPackage("jdepend")));
        
        Collection efferents = p.getEfferents();
        assertEquals(0, efferents.size());

    }

    private String format(float f) {
        return formatter.format(f);
    }
}