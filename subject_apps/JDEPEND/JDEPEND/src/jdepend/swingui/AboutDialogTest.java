package jdepend.swingui;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

import javax.swing.*;

/**
 * 
 * @author icewariya
 */

public class AboutDialogTest extends JDependTestCase{
	private AboutDialog aboutDialog;
	private JFrame parent = new JFrame();
	  public AboutDialogTest(String name) {
	        super(name);
	    }

	    protected void setUp() {
	        super.setUp();
	        aboutDialog = new AboutDialog(parent);
	    }

	    protected void tearDown() {
	        super.tearDown();
	    }
	    
	public void testAboutDialog() {
		assertEquals(300,aboutDialog.getSize().width);
		assertEquals(200,aboutDialog.getSize().height);
		assertEquals("About", aboutDialog.getTitle());
		assertFalse(aboutDialog.isResizable());
    }
	
	public void testPanelContents() {
		Container container = aboutDialog.getContentPane();
		JPanel panel = (JPanel) container.getComponent(0);
		
		assertEquals("class javax.swing.JPanel", panel.getClass().toString());
		assertEquals(8, panel.getComponentCount());
		
		assertEquals("class javax.swing.JLabel", panel.getComponent(0).getClass().toString());
		assertEquals("JDepend", ((JLabel)panel.getComponent(0)).getText());
		
		assertEquals("class javax.swing.JLabel", panel.getComponent(1).getClass().toString());
		assertEquals(" ", ((JLabel)panel.getComponent(1)).getText());
		
		assertEquals("class javax.swing.JLabel", panel.getComponent(2).getClass().toString());
		assertEquals("Mike Clark", ((JLabel)panel.getComponent(2)).getText());
		
		assertEquals("class javax.swing.JLabel", panel.getComponent(3).getClass().toString());
		assertEquals("Clarkware Consulting, Inc.", ((JLabel)panel.getComponent(3)).getText());
		
		assertEquals("class javax.swing.JLabel", panel.getComponent(4).getClass().toString());
		assertEquals("www.clarkware.com", ((JLabel)panel.getComponent(4)).getText());
		
		assertEquals("class javax.swing.JLabel", panel.getComponent(5).getClass().toString());
		assertEquals(" ", ((JLabel)panel.getComponent(5)).getText());
		
		assertEquals("class javax.swing.JLabel", panel.getComponent(6).getClass().toString());
		assertEquals(" ", ((JLabel)panel.getComponent(6)).getText());
		
		assertEquals("class javax.swing.JButton", panel.getComponent(7).getClass().toString());
		assertEquals("Close", ((JButton)panel.getComponent(7)).getText());
    }
	
	public void testCreateButton() throws Exception{

		Class c 	= aboutDialog.getClass();
		
		Object[] params = new Object[1];
		params[0] = "SampleButton";
		
		Class[] types 	= new Class[1];
		types[0] = params[0].getClass();		
				
		Method method = c.getDeclaredMethod("createButton", types);
		method.setAccessible( true );
		JButton button = (JButton) method.invoke(aboutDialog, params);
		
		assertEquals("SampleButton", button.getText());

    }
	
	public void testCreateConstraints() throws Exception{
		int x = 2;
		int y = 2;
		
		Class c 	= aboutDialog.getClass();
		
		Object[] params = new Object[2];
		params[0] = x;
		params[1] = y;
		
		Class[] types 	= new Class[2];
		types[0] = int.class;
		types[1] = int.class;
				
		Method method = c.getDeclaredMethod("createConstraints", types);
		method.setAccessible( true );
		GridBagConstraints constraints = (GridBagConstraints) method.invoke(aboutDialog, params);
		
		assertEquals(x, constraints.gridx);
		assertEquals(y, constraints.gridy);
		assertEquals(1, constraints.gridheight);
		assertEquals(1, constraints.gridwidth);
		assertEquals(constraints.CENTER, constraints.anchor);
		assertEquals(0.0, constraints.weightx);
		assertEquals(0.0, constraints.weighty);
    }
	

}