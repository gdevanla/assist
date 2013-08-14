package jdepend.swingui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.tree.*;

import jdepend.framework.ClassFileParser;
import jdepend.framework.FileManager;
import jdepend.framework.JavaClass;
import jdepend.framework.JavaPackage;
import jdepend.framework.PackageComparator;
import jdepend.framework.PackageFilter;
import jdepend.framework.ParserListener;
import jdepend.swingui.JDepend;
import jdepend.swingui.JDependTestCase;

/**
 *
 * @author icewariya
 */

public class JDependTest extends JDependTestCase {
	private JDepend jdepend;
	  public JDependTest(String name) {
	        super(name);
	    }

	    protected void setUp() {
	        super.setUp();
	        jdepend = new JDepend();
	    }

	    protected void tearDown() {
	        super.tearDown();
	    }
	    
	    public void testAddDirectoryExample() throws Exception {
	        String name = "sample";
	    	jdepend.addDirectory(name);
	    	
	    	jdepend.analyze();
	    	
	    	Field required = jdepend.getClass().getDeclaredField("analyzer");
	    	required.setAccessible(true);
	    	jdepend.framework.JDepend framework = (jdepend.framework.JDepend) required.get(jdepend);
	    	
	    	Field comp = framework.getClass().getDeclaredField("fileManager");
	    	comp.setAccessible(true);
	    	FileManager expected = (FileManager) comp.get(framework);
	    	
	    	Field inter = expected.getClass().getDeclaredField("directories");
	    	inter.setAccessible(true);
	    	ArrayList directories = (ArrayList) inter.get(expected);

	    	assertEquals(1, directories.size());
	    }
	    
	    public void testSetFilterExample() throws Exception {
	        
	    	// this filter is not being used anywhere else in the file.
	        PackageFilter filter = new PackageFilter();
	        jdepend.setFilter(filter);
	        
	        Collection filters = filter.getFilters();
	        assertEquals(filters, filter.getFilters());
	        	 	        
	    }
	    
	    public void testSetComponentsExample() throws Exception {
	        
	    	jdepend.setComponents("jdepend,junit,java,javax");
	    	
	    	Field required = jdepend.getClass().getDeclaredField("analyzer");
	    	required.setAccessible(true);
	    	jdepend.framework.JDepend framework = (jdepend.framework.JDepend) required.get(jdepend);
	    	
	    	Field comp = framework.getClass().getDeclaredField("components");
	    	comp.setAccessible(true);
	    	Collection expected = (Collection) comp.get(framework);

	 	    assertEquals(4, expected.size());
	 	    assertTrue(expected.contains("javax"));
	    }
	    
	    public void testOnParsedJavaClassExample() throws Exception {
	        
	    	JavaClass clazz = new JavaClass("ExampleInterface.class");
	    	jdepend.onParsedJavaClass(clazz);

	    	assertEquals("ExampleInterface.class", clazz.getName());
	    }
	    
	    /*public void testDisplay() throws Exception {
	        
	    	Class c 	= jdepend.getClass();
			Method method = c.getDeclaredMethod("display");
			method.setAccessible( true );
			method.invoke(jdepend);

			Field jframe = jdepend.getClass().getDeclaredField("frame");
			jframe.setAccessible(true);
			JFrame frame = (JFrame) jframe.get(jdepend);

			assertTrue(frame.isVisible());
	    }*/
	    
	    public void testUpdateTree() throws Exception {
	    	Field required = jdepend.getClass().getDeclaredField("analyzer");
	    	required.setAccessible(true);
	    	jdepend.framework.JDepend framework = (jdepend.framework.JDepend) required.get(jdepend);
	    	
	    	ArrayList packages = new ArrayList(framework.analyze());
	    	
	    	Object[] params = new Object[1];
			params[0] = packages;
			
			Class[] types 	= new Class[1];
			types[0] = params[0].getClass();

			Method method = jdepend.getClass().getDeclaredMethod("updateTree", types);
			method.setAccessible( true );
			method.invoke(jdepend, params);
			
			Field efferentTree = jdepend.getClass().getDeclaredField("efferentTree");
			efferentTree.setAccessible(true);
			DependTree efferent = (DependTree) efferentTree.get(jdepend);
			
			Field afferentTree = jdepend.getClass().getDeclaredField("afferentTree");
			afferentTree.setAccessible(true);
			DependTree afferent = (DependTree) afferentTree.get(jdepend);
			
			assertEquals("Depends Upon - Efferent Dependencies (0 Packages)", efferent.getModel().getRoot().toString());
			assertEquals("Used By - Afferent Dependencies (0 Packages)", afferent.getModel().getRoot().toString());
			
	    }
	    
	    public void testStartProgressMonitor() throws Exception {
	    	int min = 0;
	    	final int max = 5;
	    	Object[] params = new Object[1];
			params[0] = max;
			
			Class[] types 	= new Class[1];
			types[0] = int.class;

			Method method = jdepend.getClass().getDeclaredMethod("startProgressMonitor", types);
			method.setAccessible( true );
			method.invoke(jdepend, params);
			
	    }
	    
	    public void testCreateUI() throws Exception {

			Method method = jdepend.getClass().getDeclaredMethod("createUI");
			method.setAccessible( true );
			JFrame frame = (JFrame) method.invoke(jdepend);

			assertEquals("JDepend", frame.getTitle());
			assertEquals(700, frame.getWidth());
			assertEquals(500, frame.getHeight());
	    }
	    
	    public void testCreateFrame() throws Exception {
	    	String title = "JDepend";
	    	Object[] params = new Object[1];
			params[0] = title;
			
			Class[] types 	= new Class[1];
			types[0] = params[0].getClass();
			
			Method method = jdepend.getClass().getDeclaredMethod("createFrame", types);
			method.setAccessible( true );
			JFrame frame = (JFrame) method.invoke(jdepend, params);

			assertEquals("JDepend", frame.getTitle());
			assertEquals("class java.awt.BorderLayout", frame.getContentPane().getLayout().getClass().toString());
			assertEquals(0, frame.getWidth());
			assertEquals(0, frame.getHeight());
	    }
	    
	    public void testCreateTreePanel() throws Exception {			
			Method method = jdepend.getClass().getDeclaredMethod("createTreePanel");
			method.setAccessible( true );
			JPanel panel = (JPanel) method.invoke(jdepend);
			
			assertEquals(2, panel.getComponentCount());
			assertEquals("class jdepend.swingui.DependTree", panel.getComponent(0).getClass().toString());
	    }
	    
	    public void testCreateStatusPanel() throws Exception {			
			Method method = jdepend.getClass().getDeclaredMethod("createStatusPanel");
			method.setAccessible( true );
			JPanel panel = (JPanel) method.invoke(jdepend);
			
			assertEquals(1, panel.getComponentCount());
			assertEquals("class javax.swing.JTextField", panel.getComponent(0).getClass().toString());
	    }
	    
	    public void testCreateProgressBar() throws Exception {			
			Method method = jdepend.getClass().getDeclaredMethod("createProgressBar");
			method.setAccessible( true );
			JProgressBar progressBar = (JProgressBar) method.invoke(jdepend);
			
			assertTrue(progressBar.isStringPainted());
	    }
	    
	    public void testCreateStatusField() throws Exception {			
			Method method = jdepend.getClass().getDeclaredMethod("createStatusField");
			method.setAccessible( true );
			JTextField textField = (JTextField) method.invoke(jdepend);
			
			assertEquals(12, textField.getFont().getSize());
			assertEquals(1, textField.getFont().getStyle());
			assertFalse(textField.isEditable());
			assertEquals(Color.black, textField.getForeground());
			assertEquals(5, textField.getMargin().bottom);
			assertEquals(5, textField.getMargin().top);
			assertEquals(5, textField.getMargin().left);
			assertEquals(5, textField.getMargin().right);
	    }
	    
	    public void testCreateMenubar() throws Exception {			
			Method method = jdepend.getClass().getDeclaredMethod("createMenubar");
			method.setAccessible( true );
			JMenuBar menuBar = (JMenuBar) method.invoke(jdepend);
			JMenu m = menuBar.getMenu(0);
			
			assertEquals(1, menuBar.getComponentCount());
			assertEquals("File", m.getText().toString());

	    }
	    
	    public void testCreateMenu() throws Exception {	
	    	
	    	String title = "File";
	    	Object[] params = new Object[1];
			params[0] = title;
			
			Class[] types 	= new Class[1];
			types[0] = params[0].getClass();
			
			Method method = jdepend.getClass().getDeclaredMethod("createMenu", types);
			method.setAccessible( true );
			JMenu menu = (JMenu) method.invoke(jdepend, params);
			
			assertEquals(0, menu.getComponentCount());
			assertEquals("File", menu.getText().toString());
			assertEquals(70, menu.getMnemonic());
	    }

	    public void testCreateMenuItem() throws Exception {	
	    	
	    	String title = "About";
	    	Object[] params = new Object[1];
			params[0] = title;
			
			Class[] types 	= new Class[1];
			types[0] = params[0].getClass();
			
			Method method = jdepend.getClass().getDeclaredMethod("createMenuItem", types);
			method.setAccessible( true );
			JMenuItem menu = (JMenuItem) method.invoke(jdepend, params);

			assertEquals("About", menu.getText().toString());
	    }

	    public void testShowStatusMessage() throws Exception {	
	    	
	    	String title = "Status Message";
	    	Object[] params = new Object[1];
			params[0] = title;
			
			Class[] types 	= new Class[1];
			types[0] = params[0].getClass();
			
			Method method = jdepend.getClass().getDeclaredMethod("showStatusMessage", types);
			method.setAccessible( true );
			method.invoke(jdepend, params);
			
			Field status = jdepend.getClass().getDeclaredField("statusField");
			status.setAccessible(true);
			JTextField statusField = (JTextField) status.get(jdepend);
			
			assertEquals(" Status Message", statusField.getText().toString());
			assertEquals(Color.black, statusField.getForeground());
	    }
	    
	    public void testShowStatusError() throws Exception {	
	    	
	    	String title = "Status Error";
	    	Object[] params = new Object[1];
			params[0] = title;
			
			Class[] types 	= new Class[1];
			types[0] = params[0].getClass();
			
			Method method = jdepend.getClass().getDeclaredMethod("showStatusError", types);
			method.setAccessible( true );
			method.invoke(jdepend, params);
			
			Field status = jdepend.getClass().getDeclaredField("statusField");
			status.setAccessible(true);
			JTextField statusField = (JTextField) status.get(jdepend);
			
			assertEquals(" Status Error", statusField.getText().toString());
			assertEquals(Color.red, statusField.getForeground());
	    }
	    
	    public void testGetAfferentTree() throws Exception {	
			
			Method method = jdepend.getClass().getDeclaredMethod("getAfferentTree");
			method.setAccessible( true );
			DependTree tree = (DependTree)method.invoke(jdepend);
			
			assertEquals(1, tree.getComponentCount());
	    }
	    
	    public void testGetEfferentTree() throws Exception {	
			
			Method method = jdepend.getClass().getDeclaredMethod("getEfferentTree");
			method.setAccessible( true );
			DependTree tree = (DependTree)method.invoke(jdepend);
			
			assertEquals(1, tree.getComponentCount());
	    }
	    
	    public void testGetStatusPanel() throws Exception {	
			
			Method method = jdepend.getClass().getDeclaredMethod("getStatusPanel");
			method.setAccessible( true );
			StatusPanel panel = (StatusPanel)method.invoke(jdepend);
				
			assertEquals(1, panel.getComponentCount());
	    }
		
	    public void testGetProgressBar() throws Exception {	
			
			Method method = jdepend.getClass().getDeclaredMethod("getProgressBar");
			method.setAccessible( true );
			JProgressBar progress = (JProgressBar)method.invoke(jdepend);
				
			assertEquals(0, progress.getComponentCount());
	    }
	    
	    public void testGetStatusField() throws Exception {	
			
			Method method = jdepend.getClass().getDeclaredMethod("getStatusField");
			method.setAccessible( true );
			JTextField text = (JTextField)method.invoke(jdepend);
				
			assertEquals(0, text.getComponentCount());
	    }
	    
	    public void testGetActionForCommand() throws Exception {	
	      	String title = "About";
	    	Object[] params = new Object[1];
			params[0] = title;
			
			Class[] types 	= new Class[1];
			types[0] = params[0].getClass();
			
			Method method = jdepend.getClass().getDeclaredMethod("getActionForCommand", types);
			method.setAccessible( true );
			Action action = (Action)method.invoke(jdepend, params);
				
			assertTrue(action.isEnabled());
	    }
	    
	    public void testPostStatus() throws Exception {	
	      	String title = "Post Status";
	    	Object[] params = new Object[1];
			params[0] = title;
			
			Class[] types 	= new Class[1];
			types[0] = params[0].getClass();
			
			Method method = jdepend.getClass().getDeclaredMethod("postStatusMessage", types);
			method.setAccessible( true );
			method.invoke(jdepend, params);				
	    }
	    
	    public void testPostError() throws Exception {	
	      	String title = "Post Error";
	    	Object[] params = new Object[1];
			params[0] = title;
			
			Class[] types 	= new Class[1];
			types[0] = params[0].getClass();
			
			Method method = jdepend.getClass().getDeclaredMethod("postStatusError", types);
			method.setAccessible( true );
			method.invoke(jdepend, params);
				
	    }
	    public void testStopProgressMonitor() throws Exception {
			Method method = jdepend.getClass().getDeclaredMethod("stopProgressMonitor");
			method.setAccessible( true );
			method.invoke(jdepend);
			
			Field required = jdepend.getClass().getDeclaredField("analyzer");
	    	required.setAccessible(true);
	    	jdepend.framework.JDepend framework = (jdepend.framework.JDepend) required.get(jdepend);
	    	
	    	assertEquals(0, framework.countClasses());
			
	    }
	    
}

