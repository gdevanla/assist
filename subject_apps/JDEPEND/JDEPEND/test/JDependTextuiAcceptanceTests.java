package test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;

import jdepend.framework.FileManager;
import jdepend.textui.JDepend;

public class JDependTextuiAcceptanceTests extends JDependTestCase{
	private JDepend jdepend;
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	  public JDependTextuiAcceptanceTests(String name) {
	        super(name);
	    }

	    protected void setUp() {
	    	try {
	    		super.setUp();	        
	    		jdepend = new JDepend();
	    		jdepend.addDirectory("sample");
	    		jdepend.analyzeInnerClasses(false);
	    	} catch (Exception e) {
	    		fail("An IOException occured: " + e.getMessage());
	    	}
	    	
	        System.setSecurityManager(new NoExitSecurityManager());
	        System.setErr(new PrintStream(errContent));
	    }

	    protected void tearDown() {
	    	System.setSecurityManager(null);
	    	System.setErr(null);
	    	
	        super.tearDown();
	    }

	    public void testMainExample1() throws Exception {
	        String[] args = new String[0];	        
	        try {
	        	JDepend.main(args);
	        } catch(ExitException e) {
	        	assertTrue(errContent.toString().contains("Must specify at least one directory."));
	        	assertEquals(1, e.status);
	        }
	    }
	    
	    public void testMainExample2() throws Exception {
	    	File f = new File(getBuildDir() + getPackageSubDir());
	        String[] args = new String[4];	
	        args[0] = "JDepend"; 
	        args[1] = "jdepend.textui.JDepend";
	        args[2] = "-file";
	        args[3] = f.toString();
	        try {
	        	JDepend.main(args);
	        } catch(ExitException e) {
	        	assertTrue(errContent.toString().contains("Directory does not exist: JDepend"));
	        	assertEquals(1, e.status);
	        }
	    }
	    
	    public void testMainExample3() throws Exception {
	        String[] args = new String[2];	
	        
	        args[0] = getHomeDir();
	        args[1] = "-file";
	        try {
	        	JDepend.main(args);
	        } catch(ExitException e) {
	        	assertTrue(errContent.toString().contains("Output file name not specified."));
	        	assertEquals(1, e.status);
	        }
	    }
	    
	    public void testMainExample4() throws Exception {
	        String[] args = new String[2];	
	        
	        args[0] = getHomeDir();
	        args[1] = "-directory";
	        try {
	        	JDepend.main(args);
	        } catch(ExitException e) {
	        	assertTrue(errContent.toString().contains("Invalid argument: "));
	        	assertEquals(1, e.status);
	        }
	    }
	    
	    public void testMainExample5() throws Exception {
	        String[] args = new String[2];	
	        
	        args[0] = getHomeDir();
	        args[1] = "-components";
	        try {
	        	JDepend.main(args);
	        } catch(ExitException e) {
	        	assertTrue(errContent.toString().contains("Components not specified."));
	        	assertEquals(1, e.status);
	        }
	    }
	    
	    public void testMainExample6() throws Exception {
	        String[] args = new String[1];	
 	        try {
	        	JDepend.main(args);
	        } catch(Exception e) {
	        	assertTrue(errContent.toString().isEmpty());
	        }
	    }
	    	    
	    public void testMainExample7() throws Exception {
	        String[] args = new String[1];	
	        args[0] = null;	        
 	        try {
	        	JDepend.main(args);
	        } catch(NullPointerException e) {
	        	
	        }
	    }
	    
	    public void testMainExample8() throws Exception {
	        String[] args = new String[2];	
	        args[1] = "-components test.jdepend";
	        args[0] = getTestDir();
	        
 	        try {
	        	JDepend.main(args);
	        } catch(Exception e) {
	        	assertTrue(errContent.toString().contains("Invalid argument: "+args[1]));
	        }
	    }
	    
	    public void testMainExample9() throws Exception {
	        String[] args = new String[2];	
	        args[0] = getSampleDir();
	        args[1] = "-file report.txt";
 	        try {
	        	JDepend.main(args);
	        } catch(Exception e) {
	        	assertTrue(errContent.toString().contains("Invalid argument: "+args[1]));
	        }
	    }
	    
	    public void testMainExample10() throws Exception {
	        String[] args = new String[1];	
	        args[0] = getBuildDir();

	        JDepend.main(args);

	    }
	    
	    
}
