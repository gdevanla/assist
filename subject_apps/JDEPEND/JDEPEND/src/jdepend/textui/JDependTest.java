package jdepend.textui;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import jdepend.framework.FileManager;
import jdepend.framework.JavaPackage;
import jdepend.framework.PackageFilter;



/**
 * 
 * @author icewariya
 *
 */
public class JDependTest extends JDependTestCase {
	private JDepend jdepend;
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	  public JDependTest(String name) {
	        super(name);
	    }

	    protected void setUp() {
	        super.setUp();
	        jdepend = new JDepend();
	        jdepend.analyzeInnerClasses(false);
	        System.setSecurityManager(new NoExitSecurityManager());
	        System.setErr(new PrintStream(errContent));
	    }

	    protected void tearDown() {
	    	System.setSecurityManager(null);
	    	System.setErr(null);
	        super.tearDown();
	    }
	    
	    public void testSetFilterExample() throws Exception {
	        
	    	// this filter is not being used anywhere else in the file.
	        PackageFilter filter = new PackageFilter();
	        jdepend.setFilter(filter);
	        
	        assertEquals(PrintWriter.class, jdepend.getWriter().getClass());
	 	        
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
	    
	    public void testAddDirectoryExample() throws Exception {
	        
	    	jdepend.addDirectory(getBuildDir());
	    	
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
	    
	    public void testPrintPackagesExample() throws Exception {
	        
	    	StringWriter stringWriter = new StringWriter();  
	    	PrintWriter writer = new PrintWriter(stringWriter);
	    	
	    	jdepend.setWriter(writer);
	    	
	    	jdepend.addDirectory(getBuildDir());
	    	
	    	jdepend.analyze();
	    	
	    	Field required = jdepend.getClass().getDeclaredField("analyzer");
	    	required.setAccessible(true);
	    	jdepend.framework.JDepend framework = (jdepend.framework.JDepend) required.get(jdepend);
	    	
	    	Field comp = framework.getClass().getDeclaredField("packages");
	    	comp.setAccessible(true);
	    	HashMap packages = (HashMap) comp.get(framework);
	    	Collection values = packages.values();
	    
	    	// testing printNoStats
	    	String expectedNoStatistics = "No stats available: package referenced, but not analyzed.";
	    	assertTrue(stringWriter.toString().contains(expectedNoStatistics));
	    	
	    	// testing printPackageHeader 
	    	String expectedPackageHeader = "Package: jdepend.framework";
	    	assertTrue(stringWriter.toString().contains(expectedPackageHeader));
	    	
	    	// testing printStatistics
	    	String expectedStats = "Stats";
	    	String expectedTotalClasses = "Total Classes: 16";
	    	String expectedAbstractClasses = "Abstract Classes: 4";
	    	String expectedConcreteClasses = "Concrete Classes: 12";
	    	String expectedCa = "Ca: 3";
	    	String expectedCe = "Ce: 11";
	    	String expectedA = "A: 0.25";
	    	String expectedI = "I: 0.79";
	    	String expectedD = "D: 0.04";
	    	
	    	assertTrue(stringWriter.toString().contains(expectedStats));
	    	assertTrue(stringWriter.toString().contains(expectedTotalClasses));
	    	assertTrue(stringWriter.toString().contains(expectedAbstractClasses));
	    	assertTrue(stringWriter.toString().contains(expectedConcreteClasses));
	    	assertTrue(stringWriter.toString().contains(expectedCa));
	    	assertTrue(stringWriter.toString().contains(expectedCe));
	    	assertTrue(stringWriter.toString().contains(expectedA));
	    	assertTrue(stringWriter.toString().contains(expectedI));
	    	assertTrue(stringWriter.toString().contains(expectedD));
	    	
	    	//testing printAbstractClasses
	    	String abstractClassHeader = "Abstract Classes:";
	    	String abstractClassContent = "jdepend.framework.AbstractParser";
	    	
	    	assertTrue(stringWriter.toString().contains(abstractClassHeader));
	    	assertTrue(stringWriter.toString().contains(abstractClassContent));
	    	
	    	//testing printConcreteClasses
	    	String concreteClassHeader = "Abstract Classes:";
	    	String concreteClassContent = "jdepend.framework.JDepend";
	    	
	    	assertTrue(stringWriter.toString().contains(concreteClassHeader));
	    	assertTrue(stringWriter.toString().contains(concreteClassContent));
	    	
	    	//testing printEfferents
	    	String printEfferentsHeader = "Depends Upon:";
	    	String printEfferentsContent = "java.io";
	    	
	    	assertTrue(stringWriter.toString().contains(printEfferentsHeader));
	    	assertTrue(stringWriter.toString().contains(printEfferentsContent));
	    	
	    	//testing printAfferents
	    	String printAfferentsHeader = "Used By:";
	    	String printAfferentsContent = "jdepend.swingui";
	    	String printAfferentsError = "Not used by any packages.";
	    	
	    	assertTrue(stringWriter.toString().contains(printAfferentsHeader));
	    	assertTrue(stringWriter.toString().contains(printAfferentsContent));
	    	assertTrue(stringWriter.toString().contains(printAfferentsError));
	    }
	    
	    public void testPrintEfferentsError() throws Exception {
	        
	    	StringWriter stringWriter = new StringWriter();  
	    	PrintWriter writer = new PrintWriter(stringWriter);
	    	
	    	jdepend.setWriter(writer);
	    	
	    	jdepend.printEfferentsError();
	    	
	    	String printEfferentsError = "Not dependent on any packages.";
	    	assertTrue(stringWriter.toString().contains(printEfferentsError));
	    	
	    }
	    
	    public void testPrintCyclesExample1() throws Exception {
	    	
	    	StringWriter stringWriter = new StringWriter();  
	    	PrintWriter writer = new PrintWriter(stringWriter);
	    	
	    	jdepend.setWriter(writer);
	    	jdepend.addDirectory(getBuildDir());
	    	jdepend.analyze();
	    	
	    	String printCyclesError = "Package Dependency Cycles:";
	    	assertTrue(stringWriter.toString().contains(printCyclesError));
	         
	    }
	    
	    public void testPrintCyclesExample2() throws Exception {
	    	
	    	StringWriter stringWriter = new StringWriter();  
	    	PrintWriter writer = new PrintWriter(stringWriter);
	    	
	    	jdepend.setWriter(writer);
	    	jdepend.addDirectory(getBuildDir());
	    	jdepend.analyze();
	    	
	    	JavaPackage a = new JavaPackage("java.math");
	        JavaPackage b = new JavaPackage("java.io");

	        a.dependsUpon(b);
	        b.dependsUpon(a);
	    	
	    	Field required = jdepend.getClass().getDeclaredField("analyzer");
	    	required.setAccessible(true);
	    	jdepend.framework.JDepend framework = (jdepend.framework.JDepend) required.get(jdepend);
	    	
	    	framework.addPackage(a);
	        framework.addPackage(b);
	        
	        jdepend.printCycle(a);
	        
	    	String printCyclesError = "Package Dependency Cycles";
	    	assertTrue(stringWriter.toString().contains(printCyclesError));
	    }
	    
	    public void testPrintSummary() throws Exception {
	    	
	    	StringWriter stringWriter = new StringWriter();  
	    	PrintWriter writer = new PrintWriter(stringWriter);
	    	
	    	jdepend.setWriter(writer);
	    	jdepend.addDirectory(getBuildDir());
	    	jdepend.analyze();
	        
	    	String printSummary = "Summary:";
	    	assertTrue(stringWriter.toString().contains(printSummary));
	    }
	    
	    public void testTabExample1() throws Exception {
	    
	    	assertEquals("                    ", jdepend.tab(5));
	    	assertEquals("", jdepend.tab(0));
	     
	    }
	    
	    public void testTabExample2() throws Exception {
	    	assertEquals("", jdepend.tab(-5));
	     
	    }
	    
	    public void testUsageExample1() throws Exception {
	    	String message="Message";
	    	try {
	    		jdepend.usage(message);
	    	} catch (ExitException e) 
	    	{
	    		assertTrue(errContent.toString().contains("Message"));
	    		assertEquals(1, e.status);
	    	}
	    }
	    
	    public void testUsageExample2() throws Exception {
	    	String message=null;
	    	try {
	    		jdepend.usage(message);
	    	} catch (ExitException e) 
	    	{
	    		assertEquals(1, e.status);
	    	}
	    }
	    
	    public void testMainExample1() throws Exception {
	        String[] args = new String[0];	        
	        try {
	        	jdepend.instanceMain(args);
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
	        	jdepend.instanceMain(args);
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
	        	jdepend.instanceMain(args);
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
	        	jdepend.instanceMain(args);
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
	        	jdepend.instanceMain(args);
	        } catch(ExitException e) {
	        	assertTrue(errContent.toString().contains("Components not specified."));
	        	assertEquals(1, e.status);
	        }
	    }
	    
	    public void testMainExample6() throws Exception {
	        String[] args = new String[2];	
	        
	        args[0] = getHomeDir();
	        args[1] = getBuildDir();

	       	jdepend.instanceMain(args);
	    	Field required = jdepend.getClass().getDeclaredField("analyzer");
	    	required.setAccessible(true);
	    	jdepend.framework.JDepend framework = (jdepend.framework.JDepend) required.get(jdepend);
	    	
	    	Field comp = framework.getClass().getDeclaredField("fileManager");
	    	comp.setAccessible(true);
	    	FileManager expected = (FileManager) comp.get(framework);
	    	
	    	Field inter = expected.getClass().getDeclaredField("directories");
	    	inter.setAccessible(true);
	    	ArrayList directories = (ArrayList) inter.get(expected);

	    	assertEquals(2, directories.size());
	        
	    }
	    
	    public void testMainExample7() throws Exception {
	        String[] args = new String[2];	
	        
	        args[0] = getHomeDir();
	        args[1] = getBuildDir();

	       	JDepend.main(args);
	       	
	    	Field required = jdepend.getClass().getDeclaredField("analyzer");
	    	required.setAccessible(true);
	    	jdepend.framework.JDepend framework = (jdepend.framework.JDepend) required.get(jdepend);
	    	
	    	Field comp = framework.getClass().getDeclaredField("fileManager");
	    	comp.setAccessible(true);
	    	FileManager expected = (FileManager) comp.get(framework);
	    	
	    	Field inter = expected.getClass().getDeclaredField("directories");
	    	inter.setAccessible(true);
	    	ArrayList directories = (ArrayList) inter.get(expected);

	    	assertEquals(0, directories.size());
	        
	    }
}
