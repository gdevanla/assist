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
public class EfferentNodeTest extends JDependTestCase{
	private EfferentNode efferentNode;
	  public EfferentNodeTest(String name) {
	        super(name);
	    }

	    protected void setUp() {
	        super.setUp();
	        JavaPackage jpackage = new JavaPackage("root");
	        efferentNode = new EfferentNode(null, jpackage);
	    }

	    protected void tearDown() {
	        super.tearDown();
	    }
	    
	    public void testMakeNode() {
	    	JavaPackage pkg = new JavaPackage("A");
	    	PackageNode newNode = efferentNode.makeNode(efferentNode, pkg);
	    	assertEquals(EfferentNode.class, newNode.getClass());
	    }
	    
	    public void testGetCoupledPackagesExample1() {
	    	JavaPackage ioPkg = new JavaPackage("java.io");
	    	JavaPackage mathPkg = new JavaPackage("java.math");
	    	ArrayList list = new ArrayList();
	    	list.add(ioPkg);
	    	mathPkg.setEfferents(list);
	    	
	    	PackageNode newNode = efferentNode.makeNode(efferentNode, mathPkg);
	    	
	    	Collection newNodeColl = newNode.getCoupledPackages();
	    	Collection oldNodeColl = efferentNode.getCoupledPackages();
	    	
	    	assertEquals(1, newNodeColl.size());
	    	assertEquals(0, oldNodeColl.size());
	    }
	    
	    public void testGetCoupledPackagesExample2() {
	    	JavaPackage mathPkg = new JavaPackage("java.math");
	    	
	    	PackageNode newNode = efferentNode.makeNode(efferentNode, mathPkg);
	    	
	    	Collection newNodeColl = newNode.getCoupledPackages();
	    	Collection oldNodeColl = efferentNode.getCoupledPackages();
	    	
	    	assertEquals(0, newNodeColl.size());
	    	assertEquals(0, oldNodeColl.size());
	    }
	    
	    public void testGetCoupledPackagesExample3() {
	    	JavaPackage mathPkg = new JavaPackage("java.math");
	    	JavaPackage ioPkg = new JavaPackage("java.io");
	    	JavaPackage awtPkg = new JavaPackage("java.awt");
	    	
	    	ArrayList efferents = new ArrayList();
	    	efferents.add(mathPkg);
	    	efferents.add(awtPkg);
	    	efferents.add(ioPkg);
	    	
	    	ioPkg.setEfferents(efferents);
	    	efferentNode.getPackage().setEfferents(efferents);
	    	
	    	PackageNode newNode = efferentNode.makeNode(efferentNode, ioPkg);
	    	
	    	Collection newNodeColl = newNode.getCoupledPackages();
	    	Collection oldNodeColl = efferentNode.getCoupledPackages();
	    	
	    	assertEquals(3, newNodeColl.size());
	    	assertEquals(3, oldNodeColl.size());
	    }
	    
	    public void testGetCoupledPackagesExample4() {
	    	JavaPackage mathPkg = new JavaPackage("java.math");
	    	JavaPackage ioPkg = new JavaPackage("java.io");
	    	JavaPackage awtPkg = new JavaPackage("java.awt");
	    	
	    	ArrayList efferents = new ArrayList();
	    	efferents.add(mathPkg);
	    	ioPkg.setEfferents(efferents);
	    	efferents.add(awtPkg);
	    	efferents.add(ioPkg);

	    	efferentNode.getPackage().setEfferents(efferents);
	    	
	    	PackageNode newNode = efferentNode.makeNode(efferentNode, ioPkg);
	    	
	    	Collection newNodeColl = newNode.getCoupledPackages();
	    	Collection oldNodeColl = efferentNode.getCoupledPackages();
	    	
	    	assertEquals(1, newNodeColl.size());
	    	assertEquals(3, oldNodeColl.size());
	    }
	    
	    public void testIsChildExample1() {
	    	JavaPackage pkg = new JavaPackage("A");
	    	PackageNode newNode = efferentNode.makeNode(efferentNode, pkg);
	    	
	    	assertFalse(efferentNode.isChild(pkg));
	    	assertTrue(newNode.isChild(pkg));
	    }
	    
	    public void testIsChildExample2() {
	    	JavaPackage pkg = new JavaPackage("A");
	    	
	    	JavaClass clazz = new JavaClass("ExampleInterface.class");
	    	pkg.addClass(clazz);
	    	
	    	PackageNode newNode = efferentNode.makeNode(efferentNode, pkg);
	    	
	    	assertTrue(efferentNode.isChild(pkg));
	    	assertTrue(newNode.isChild(pkg));
	    }
	    
	    public void testToStringExample1() {
	    	JavaPackage pkg = new JavaPackage("java.awt");
	    	PackageNode newNode = efferentNode.makeNode(efferentNode, pkg);

	    	assertEquals("Depends Upon - Efferent Dependencies (0 Packages)", efferentNode.toString());
	    	assertTrue(newNode.toString().contains(pkg.getName()));
	    }
	    
	    public void testIsLeaf() {
	    	JavaPackage ioPkg = new JavaPackage("java.io");
	    	JavaPackage mathPkg = new JavaPackage("java.math");
	    	ArrayList list = new ArrayList();
	    	list.add(ioPkg);
	    	mathPkg.setEfferents(list);
	    	
	    	PackageNode newNode = efferentNode.makeNode(efferentNode, mathPkg);
	    	
	    	assertFalse(newNode.isLeaf());
	    	assertTrue(efferentNode.isLeaf());
	    }
	    
	    public void testGetChildren() {
	    	JavaPackage mathPkg = new JavaPackage("java.math");
	    	JavaPackage ioPkg = new JavaPackage("java.io");
	    	JavaPackage awtPkg = new JavaPackage("java.awt");
	    	
	    	ArrayList efferents = new ArrayList();
	    	efferents.add(mathPkg);
	    	efferents.add(awtPkg);
	    	efferents.add(ioPkg);
	    	
	    	ioPkg.setEfferents(efferents);
	    	efferentNode.getPackage().setEfferents(efferents);
	    	
	    	PackageNode newNode = efferentNode.makeNode(efferentNode, ioPkg);
	    	
	    	assertEquals(0, efferentNode.getChildren().size());
	    	assertEquals(3, newNode.getChildren().size());
	    }
	    
	    public void testCycles() {
	    	JavaPackage mathPkg = new JavaPackage("java.math");
	    	JavaPackage ioPkg = new JavaPackage("java.io");
	    	JavaPackage awtPkg = new JavaPackage("java.awt");
	    	
	    	ArrayList efferents = new ArrayList();
	    	efferents.add(mathPkg);
	    	efferents.add(awtPkg);
	    	efferents.add(ioPkg);
	    	
	    	mathPkg.dependsUpon(awtPkg);
	    	awtPkg.dependsUpon(ioPkg);
	    	ioPkg.dependsUpon(mathPkg);
	    	
	    	ioPkg.setEfferents(efferents);
	    	efferentNode.getPackage().setEfferents(efferents);
	    	
	    	PackageNode newNode = efferentNode.makeNode(efferentNode, ioPkg);
	    	String expected = "Cyclic";
	    	
	    	assertTrue(efferentNode.toMetricsString().contains(expected));
	    }

}
