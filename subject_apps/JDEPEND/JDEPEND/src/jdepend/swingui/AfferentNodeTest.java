package jdepend.swingui;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

import jdepend.framework.JavaClass;
import jdepend.framework.JavaPackage;
import jdepend.swingui.EfferentNode;
import jdepend.swingui.JDepend;
import jdepend.swingui.PackageNode;

/**
 * 
 * @author icewariya
 *
 */
public class AfferentNodeTest extends JDependTestCase{
	private AfferentNode afferentNode;
	  public AfferentNodeTest(String name) {
	        super(name);
	    }

	    protected void setUp() {
	        super.setUp();
	        JavaPackage jpackage = new JavaPackage("root");
	        afferentNode = new AfferentNode(null, jpackage);
	    }

	    protected void tearDown() {
	        super.tearDown();
	    }
	    
	    public void testMakeNode() {
	    	JavaPackage pkg = new JavaPackage("A");
	    	PackageNode newNode = afferentNode.makeNode(afferentNode, pkg);
	    	assertEquals(AfferentNode.class, newNode.getClass());
	    }
	    
	    public void testGetCoupledPackagesExample1() {
	    	JavaPackage ioPkg = new JavaPackage("java.io");
	    	JavaPackage mathPkg = new JavaPackage("java.math");
	    	ArrayList list = new ArrayList();
	    	list.add(ioPkg);
	    	mathPkg.setAfferents(list);
	    	
	    	PackageNode newNode = afferentNode.makeNode(afferentNode, mathPkg);
	    	
	    	Collection newNodeColl = newNode.getCoupledPackages();
	    	Collection oldNodeColl = afferentNode.getCoupledPackages();
	    	
	    	assertEquals(1, newNodeColl.size());
	    	assertEquals(0, oldNodeColl.size());
	    }
	    
	    public void testGetCoupledPackagesExample2() {
	    	JavaPackage mathPkg = new JavaPackage("java.math");
	    	
	    	PackageNode newNode = afferentNode.makeNode(afferentNode, mathPkg);
	    	
	    	Collection newNodeColl = newNode.getCoupledPackages();
	    	Collection oldNodeColl = afferentNode.getCoupledPackages();
	    	
	    	assertEquals(0, newNodeColl.size());
	    	assertEquals(0, oldNodeColl.size());
	    }
	    
	    public void testGetCoupledPackagesExample3() {
	    	JavaPackage mathPkg = new JavaPackage("java.math");
	    	JavaPackage ioPkg = new JavaPackage("java.io");
	    	JavaPackage awtPkg = new JavaPackage("java.awt");
	    	
	    	ArrayList afferents = new ArrayList();
	    	afferents.add(mathPkg);
	    	afferents.add(awtPkg);
	    	afferents.add(ioPkg);
	    	
	    	ioPkg.setAfferents(afferents);
	    	afferentNode.getPackage().setAfferents(afferents);
	    	
	    	PackageNode newNode = afferentNode.makeNode(afferentNode, ioPkg);
	    	
	    	Collection newNodeColl = newNode.getCoupledPackages();
	    	Collection oldNodeColl = afferentNode.getCoupledPackages();
	    	
	    	assertEquals(3, newNodeColl.size());
	    	assertEquals(3, oldNodeColl.size());
	    }
	    
	    public void testGetCoupledPackagesExample4() {
	    	JavaPackage mathPkg = new JavaPackage("java.math");
	    	JavaPackage ioPkg = new JavaPackage("java.io");
	    	JavaPackage awtPkg = new JavaPackage("java.awt");
	    	
	    	ArrayList afferents = new ArrayList();
	    	afferents.add(mathPkg);
	    	ioPkg.setAfferents(afferents);
	    	afferents.add(awtPkg);
	    	afferents.add(ioPkg);

	    	afferentNode.getPackage().setAfferents(afferents);
	    	
	    	PackageNode newNode = afferentNode.makeNode(afferentNode, ioPkg);
	    	
	    	Collection newNodeColl = newNode.getCoupledPackages();
	    	Collection oldNodeColl = afferentNode.getCoupledPackages();
	    	
	    	assertEquals(1, newNodeColl.size());
	    	assertEquals(3, oldNodeColl.size());
	    }
	    
	    public void testIsChildExample1() {
	    	JavaPackage pkg = new JavaPackage("A");
	    	PackageNode newNode = afferentNode.makeNode(afferentNode, pkg);
	    	
	    	assertTrue(afferentNode.isChild(pkg));
	    	assertTrue(newNode.isChild(pkg));
	    }
	    
	    public void testIsChildExample2() {
	    	JavaPackage pkg = new JavaPackage("A");
	    	
	    	JavaClass clazz = new JavaClass("ExampleInterface.class");
	    	pkg.addClass(clazz);
	    	
	    	PackageNode newNode = afferentNode.makeNode(afferentNode, pkg);
	    	
	    	assertTrue(afferentNode.isChild(pkg));
	    	assertTrue(newNode.isChild(pkg));
	    }
	    
	    public void testToStringExample1() {
	    	JavaPackage pkg = new JavaPackage("java.awt");
	    	PackageNode newNode = afferentNode.makeNode(afferentNode, pkg);

	    	assertEquals("Used By - Afferent Dependencies (0 Packages)", afferentNode.toString());
	    	assertTrue(newNode.toString().contains(pkg.getName()));
	    }
	    
	    public void testIsLeaf() {
	    	JavaPackage ioPkg = new JavaPackage("java.io");
	    	JavaPackage mathPkg = new JavaPackage("java.math");
	    	ArrayList list = new ArrayList();
	    	list.add(ioPkg);
	    	mathPkg.setAfferents(list);
	    	
	    	PackageNode newNode = afferentNode.makeNode(afferentNode, mathPkg);
	    	
	    	assertFalse(newNode.isLeaf());
	    	assertTrue(afferentNode.isLeaf());
	    }
	    
	    public void testGetChildren() {
	    	JavaPackage mathPkg = new JavaPackage("java.math");
	    	JavaPackage ioPkg = new JavaPackage("java.io");
	    	JavaPackage awtPkg = new JavaPackage("java.awt");
	    	
	    	ArrayList afferents = new ArrayList();
	    	afferents.add(mathPkg);
	    	afferents.add(awtPkg);
	    	afferents.add(ioPkg);
	    	
	    	ioPkg.setAfferents(afferents);
	    	afferentNode.getPackage().setAfferents(afferents);
	    	
	    	PackageNode newNode = afferentNode.makeNode(afferentNode, ioPkg);
	    	
	    	assertEquals(3, afferentNode.getChildren().size());
	    	assertEquals(3, newNode.getChildren().size());
	    	
	    }
	    
	    public void testCycles() {
	    	JavaPackage mathPkg = new JavaPackage("java.math");
	    	JavaPackage ioPkg = new JavaPackage("java.io");
	    	JavaPackage awtPkg = new JavaPackage("java.awt");
	    	
	    	ArrayList afferents = new ArrayList();
	    	afferents.add(mathPkg);
	    	afferents.add(awtPkg);
	    	afferents.add(ioPkg);
	    	
	    	mathPkg.dependsUpon(awtPkg);
	    	awtPkg.dependsUpon(ioPkg);
	    	ioPkg.dependsUpon(mathPkg);
	    	
	    	ioPkg.setAfferents(afferents);
	    	afferentNode.getPackage().setEfferents(afferents);
	    	
	    	PackageNode newNode = afferentNode.makeNode(afferentNode, ioPkg);
	    	String expected = "Cyclic";
	    	
	    	assertTrue(afferentNode.toMetricsString().contains(expected));
	    }

}
