package jdepend.swingui;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTree;
import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.TreeModel;


import jdepend.framework.JavaPackage;

/**
 * 
 * @author icewariya
 *
 */
public class DependTreeModelTest extends JDependTestCase {

	private static final TreeModelListener TreeModelListener = null;
	private DependTreeModel dependTreeModel;
	  public DependTreeModelTest(String name) {
	        super(name);
	    }

	    protected void setUp() {
	        super.setUp();
	        dependTreeModel = new DependTreeModel(null);
	    }

	    protected void tearDown() {
	        super.tearDown();
	    }
	    
	    public void testDependTreeExample1() {
	    	assertEquals(null, dependTreeModel.getRoot());
	    }
	    
	    public void testDependTreeExample2() {
	    	JavaPackage jpackage = new JavaPackage("root");
	    	EfferentNode efferentNode = new EfferentNode(null, jpackage);
	    	PackageNode newNode = efferentNode.makeNode(efferentNode, jpackage);
	    	
	    	DependTreeModel model = new DependTreeModel(newNode);
	    	assertEquals(newNode, model.getRoot());
	    }
	    
	    public void testGetChildExample1() {
	    	assertEquals(null, dependTreeModel.getChild(dependTreeModel, 0));
	    }
	    
	    public void testGetChildExample2() {
	    	JavaPackage jpackage = new JavaPackage("A");
	    	EfferentNode efferentNode = new EfferentNode(null, jpackage);
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
	    	
	    	assertEquals("java.awt", dependTreeModel.getChild(newNode, 0).toString());
	    	assertEquals(null, dependTreeModel.getChild(efferentNode, 0));
	    	
	    }
	    
	    public void testGetChildCountExample1() {
	    	assertEquals(0, dependTreeModel.getChildCount(dependTreeModel));
	    }
	    
	    public void testGetChildCountExample2() {
	    	JavaPackage jpackage = new JavaPackage("A");
	    	EfferentNode efferentNode = new EfferentNode(null, jpackage);
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
	    	
	    	assertEquals(0, dependTreeModel.getChildCount(mathPkg));
	    	assertEquals(3, dependTreeModel.getChildCount(newNode));
	    	assertEquals(0, dependTreeModel.getChildCount(efferentNode));
	    	
	    }  
	    
	    public void testIsLeafExample1() {
	    	assertTrue(dependTreeModel.isLeaf(dependTreeModel));
	    }
	    
	    public void testIsLeafExample2() {
	    	JavaPackage jpackage = new JavaPackage("A");
	    	EfferentNode efferentNode = new EfferentNode(null, jpackage);
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
	    	
	    	assertTrue(dependTreeModel.isLeaf(mathPkg));
	    	assertFalse(dependTreeModel.isLeaf(newNode));
	    	assertFalse(dependTreeModel.isLeaf(efferentNode));
	    	
	    }  
	    
	    public void testGetIndexOfChildExample1() {
	    	assertEquals(-1, dependTreeModel.getIndexOfChild(dependTreeModel,0));
	    }
	    
	    public void testGetIndexOfChildExample2() {
	    	JavaPackage jpackage = new JavaPackage("A");
	    	EfferentNode efferentNode = new EfferentNode(null, jpackage);
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
	    	
	    	assertEquals(-1,dependTreeModel.getIndexOfChild(mathPkg, ioPkg));
	    	assertEquals(-1,dependTreeModel.getIndexOfChild(newNode,awtPkg));
	    	assertEquals(-1,dependTreeModel.getIndexOfChild(efferentNode,awtPkg));
	    	
	    }  
	    
	    public void testAddTreeModelListenerExample1() throws Exception{
	    	
	    	dependTreeModel.addTreeModelListener(null);

	    	Field start = dependTreeModel.getClass().getDeclaredField("listeners");
			start.setAccessible(true);
			Vector listeners = (Vector) start.get(dependTreeModel);
			Vector expected = new Vector();
			
			assertEquals(expected, listeners);

	    }
	    
	    public void testAddTreeModelListenerExample2() throws Exception{
	    	
	    	DependTree tree = new DependTree();
	    	tree.addTreeSelectionListener(tree);
	    	
	    	try {
	    		tree.getListeners(TreeModelListener.getClass());
	    	} catch(NullPointerException e) {
	    		
	    	}

	    }
	    
	    public void testRemoveTreeModelListenerExample1() throws Exception{

	    	dependTreeModel.removeTreeModelListener(null);
	    	
	    	Field start = dependTreeModel.getClass().getDeclaredField("listeners");
			start.setAccessible(true);
			Vector listeners = (Vector) start.get(dependTreeModel);
			Vector expected = new Vector();
			
			assertEquals(expected, listeners);

	    }
	   	   
}
