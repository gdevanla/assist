package jdepend.swingui;

import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.EventObject;

import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;

import jdepend.framework.JavaPackage;

/**
 * 
 * @author icewariya
 *
 */
public class DependTreeTest extends JDependTestCase {
	private DependTree dependTree;
	  public DependTreeTest(String name) {
	        super(name);
	    }

	    protected void setUp() {
	        super.setUp();
	        dependTree = new DependTree();
	    }

	    protected void tearDown() {
	        super.tearDown();
	    }
	    
	    public void testDependTreeExample1() {
	    	
	    	assertEquals("Used By - Afferent Dependencies (0 Packages)", dependTree.getModel().getRoot().toString());
	    	assertEquals(BorderLayout.class,dependTree.getLayout().getClass());
	    	
	    }
	    
	    
	    public void testDependTreeExample2() {
	    	
	    	JavaPackage jpackage = new JavaPackage("root");
	    	EfferentNode efferentNode = new EfferentNode(null, jpackage);
	    	PackageNode newNode = efferentNode.makeNode(efferentNode, jpackage);
	    	
	    	DependTreeModel model = new DependTreeModel(newNode);
	    	
	    	DependTree copyTree = new DependTree(model);
	    	
	    	assertEquals(model, copyTree.getModel());
	    	assertEquals(BorderLayout.class,copyTree.getLayout().getClass());
	    	
	    }
	    
	    public void testSetModelExample() {
	    	
	    	JavaPackage jpackage = new JavaPackage("root");
	    	EfferentNode efferentNode = new EfferentNode(null, jpackage);
	    	PackageNode newNode = efferentNode.makeNode(efferentNode, jpackage);
	    	
	    	DependTreeModel model = new DependTreeModel(newNode);
	    	
	    	DependTree copyTree = new DependTree();
	    	copyTree.setModel(model);
	    	
	    	assertEquals(model, copyTree.getModel());
	    	
	    }
	    
	    public void testTreeSelectionListenerExample() {
	    	DependTree listener = new DependTree();
	    	dependTree.addTreeSelectionListener(listener);
	    	
	    	EventObject event = new EventObject(dependTree); 
	    	TreeSelectionEvent e = new TreeSelectionEvent(event.getSource(), null, null, null, null);
	    	
	    	dependTree.valueChanged(e);
	    	TreePath path = e.getNewLeadSelectionPath();
	    	assertEquals(null, path);
	    }
	    
	    public void testCreateScrollPane() throws Exception{
			Class c 	= dependTree.getClass();
			Method method = c.getDeclaredMethod("createScrollPane");
			method.setAccessible( true );
			
			JScrollPane pane = (JScrollPane) method.invoke(dependTree);
			float expected = (float) 0.5;
			
			assertEquals(expected, pane.getAlignmentX());
			assertEquals(expected, pane.getAlignmentY());

	    }
	    
	    public void testCreateTree() throws Exception{
			Class c 	= dependTree.getClass();
			Method method = c.getDeclaredMethod("createTree");
			method.setAccessible( true );
			
			JTree tree = (JTree) method.invoke(dependTree);

			assertFalse(tree.getShowsRootHandles());

	    }
	    
	    public void testGetTreeExample() throws Exception{
			Class c 	= dependTree.getClass();
			Method method = c.getDeclaredMethod("getTree");
			method.setAccessible( true );
			
			Field start = dependTree.getClass().getDeclaredField("tree");
			start.setAccessible(true);
			JTree privateStart = (JTree) start.get(dependTree);
			privateStart = null;
			
			JTree tree = (JTree) method.invoke(dependTree);

			assertTrue(tree.getExpandsSelectedPaths());

	    }
	   
}

