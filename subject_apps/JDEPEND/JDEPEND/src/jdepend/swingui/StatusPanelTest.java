package jdepend.swingui;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JTextField;

/**
 * 
 * @author icewariya
 * 
 */

public class StatusPanelTest extends JDependTestCase {
	private StatusPanel statusPanel;
	  public StatusPanelTest(String name) {
	        super(name);
	    }

	    protected void setUp() {
	        super.setUp();
	        statusPanel = new StatusPanel();
	    }

	    protected void tearDown() {
	        super.tearDown();
	    }
	   
	    public void testStatusPanel() {
	    	assertEquals(BoxLayout.class, statusPanel.getLayout().getClass());
	    }
	    
	    public void testSetStatusComponents() {
	    	JTextField text = new JTextField();
	    	statusPanel.setStatusComponent(text);
	    	
	    	float expected = (float) 0.5;
	    	
	    	assertEquals("class javax.swing.JTextField", statusPanel.getComponent(0).getClass().toString());
	    	assertEquals(expected, statusPanel.getComponent(0).getAlignmentX());
	    	assertEquals(expected, statusPanel.getComponent(0).getAlignmentY());
	    	
	    }
}