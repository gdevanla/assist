package jdepend.xmlui;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;

import jdepend.framework.FileManager;
import jdepend.framework.JavaClass;
import jdepend.framework.JavaPackage;




/**
 * 
 * @author icewariya
 *
 */
public class JDependTest extends JDependTestCase {
	
	private JDepend jdepend;
	  public JDependTest(String name) {
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
	    
	    public void testPrintHeader() throws Exception {
	    	StringWriter stringWriter = new StringWriter();  
	    	PrintWriter writer = new PrintWriter(stringWriter);
	    	
	    	jdepend.setWriter(writer);
	        jdepend.printHeader();
	        
	        String expected = "<JDepend>";
	        assertTrue(stringWriter.toString().contains(expected));    
	    }
	    
	    public void testPrintFooter() throws Exception {
	    	StringWriter stringWriter = new StringWriter();  
	    	PrintWriter writer = new PrintWriter(stringWriter);
	    	
	    	jdepend.setWriter(writer);
	        jdepend.printFooter();
	        
	        String expected = "</JDepend>";
	        assertTrue(stringWriter.toString().contains(expected));    
	    }
	    
	    public void testPrintPackagesHeader() throws Exception {
	    	StringWriter stringWriter = new StringWriter();  
	    	PrintWriter writer = new PrintWriter(stringWriter);
	    	
	    	jdepend.setWriter(writer);
	        jdepend.printPackagesHeader();
	        
	        String expected = "<Packages>";
	        assertTrue(stringWriter.toString().contains(expected));    
	    }
	    
	    public void testPrintPackagesFooter() throws Exception {
	    	StringWriter stringWriter = new StringWriter();  
	    	PrintWriter writer = new PrintWriter(stringWriter);
	    	
	    	jdepend.setWriter(writer);
	        jdepend.printPackagesFooter();
	        
	        String expected = "</Packages>";
	        assertTrue(stringWriter.toString().contains(expected));    
	    }
	    
	    public void testPrintPackageHeader() throws Exception {
	    	StringWriter stringWriter = new StringWriter();  
	    	PrintWriter writer = new PrintWriter(stringWriter);
	    	
	    	JavaPackage A = new JavaPackage("A");
	    	jdepend.setWriter(writer);
	        jdepend.printPackageHeader(A);
	        
	        String expected = "<Package name=\"A\">";
	        assertTrue(stringWriter.toString().contains(expected));    
	    }
	    
	    public void testPrintPackageFooter() throws Exception {
	    	StringWriter stringWriter = new StringWriter();  
	    	PrintWriter writer = new PrintWriter(stringWriter);

	    	JavaPackage A = new JavaPackage("A");
	    	jdepend.setWriter(writer);
	        jdepend.printPackageFooter(A);
	        
	        String expected = "</Package>";
	        assertTrue(stringWriter.toString().contains(expected));    
	    }
	    
	    public void testPrintNoStats() throws Exception {
	    	StringWriter stringWriter = new StringWriter();  
	    	PrintWriter writer = new PrintWriter(stringWriter);

	    	JavaPackage A = new JavaPackage("A");
	    	jdepend.setWriter(writer);
	        jdepend.printNoStats();
	        
	        String expected = "<error>No stats available: "
                        + "package referenced, but not analyzed.</error>";
	        assertTrue(stringWriter.toString().contains(expected));    
	    }
	    
	    
	    public void testPrintStatistics() throws Exception {
	    
	    	StringWriter stringWriter = new StringWriter();  
	    	PrintWriter writer = new PrintWriter(stringWriter);

	    	jdepend.setWriter(writer);
	    	jdepend.analyze();
	        JavaPackage pack = new JavaPackage("Sample");
	        
	        jdepend.printStatistics(pack);
	    	String expectedStats = "<Stats>";
	    	String expectedAbstract = "<AbstractClasses>";
	    	String expectedConcrete = "<ConcreteClasses>";
	    	
	    	assertTrue(stringWriter.toString().contains(expectedStats));
	    	assertTrue(stringWriter.toString().contains(expectedAbstract));
	    	assertTrue(stringWriter.toString().contains(expectedConcrete));
	    	
	    }
	    
	    public void testPrintClassName() throws Exception {
	    	StringWriter stringWriter = new StringWriter();  
	    	PrintWriter writer = new PrintWriter(stringWriter);

	    	jdepend.setWriter(writer);
	    	
	        JavaClass clazz = new JavaClass("ExampleInterface.class");
	    	jdepend.printClassName(clazz);
	    	
	    	String expected = "<Class sourceFile=\"Unknown\">";

	    	assertTrue(stringWriter.toString().contains(expected));
	    	
	    }
	    
	    public void testPrintPackageName() throws Exception {
	    	StringWriter stringWriter = new StringWriter();  
	    	PrintWriter writer = new PrintWriter(stringWriter);

	    	jdepend.setWriter(writer);
	    	
	    	JavaPackage a = new JavaPackage("A");
	    	jdepend.printPackageName(a);
	    	
	    	String expected = "<Package>A</Package>";

	    	assertTrue(stringWriter.toString().contains(expected));
	    	
	    }
	    
	    public void testPrintAbstractClasses() throws Exception {
	    	StringWriter stringWriter = new StringWriter();  
	    	PrintWriter writer = new PrintWriter(stringWriter);

	    	jdepend.setWriter(writer);
	    	
	    	jdepend.printAbstractClassesHeader();
	    	jdepend.printAbstractClassesFooter();
	    	
	    	String expectedHeader = "<AbstractClasses>";
	    	String expectedFooter = "</AbstractClasses>";

	    	assertTrue(stringWriter.toString().contains(expectedHeader));
	    	assertTrue(stringWriter.toString().contains(expectedFooter));
	    	
	    }
	    
	    public void testPrintConcreteClasses() throws Exception {
	    	StringWriter stringWriter = new StringWriter();  
	    	PrintWriter writer = new PrintWriter(stringWriter);

	    	jdepend.setWriter(writer);
	    	
	    	jdepend.printConcreteClassesHeader();
	    	jdepend.printConcreteClassesFooter();
	    	
	    	String expectedHeader = "<ConcreteClasses>";
	    	String expectedFooter = "</ConcreteClasses>";

	    	assertTrue(stringWriter.toString().contains(expectedHeader));
	    	assertTrue(stringWriter.toString().contains(expectedFooter));
	    	
	    }
	    
	    public void testPrintEfferents() throws Exception {
	    	StringWriter stringWriter = new StringWriter();  
	    	PrintWriter writer = new PrintWriter(stringWriter);

	    	jdepend.setWriter(writer);
	    	
	    	jdepend.printEfferentsHeader();
	    	jdepend.printEfferentsFooter();
	    	
	    	String expectedHeader = "<DependsUpon>";
	    	String expectedFooter = "</DependsUpon>";

	    	assertTrue(stringWriter.toString().contains(expectedHeader));
	    	assertTrue(stringWriter.toString().contains(expectedFooter));
	    	
	    }
	    
	    public void testPrintAfferents() throws Exception {
	    	StringWriter stringWriter = new StringWriter();  
	    	PrintWriter writer = new PrintWriter(stringWriter);

	    	jdepend.setWriter(writer);
	    	
	    	jdepend.printAfferentsHeader();
	    	jdepend.printAfferentsFooter();
	    	
	    	String expectedHeader = "<UsedBy>";
	    	String expectedFooter = "</UsedBy>";

	    	assertTrue(stringWriter.toString().contains(expectedHeader));
	    	assertTrue(stringWriter.toString().contains(expectedFooter));
	    	
	    }
	    
	    public void testPrintCycles() throws Exception {
	    	StringWriter stringWriter = new StringWriter();  
	    	PrintWriter writer = new PrintWriter(stringWriter);

	    	jdepend.setWriter(writer);
	    	
	    	jdepend.printCyclesHeader();
	    	jdepend.printCyclesFooter();
	    	
	    	String expectedHeader = "<Cycles>";
	    	String expectedFooter = "</Cycles>";

	    	assertTrue(stringWriter.toString().contains(expectedHeader));
	    	assertTrue(stringWriter.toString().contains(expectedFooter));
	    	
	    }
	    
	    public void testPrintCyclePackage() throws Exception {
	    	StringWriter stringWriter = new StringWriter();  
	    	PrintWriter writer = new PrintWriter(stringWriter);

	    	jdepend.setWriter(writer);
	    	
	    	JavaPackage a = new JavaPackage("A");
	    	JavaPackage b = new JavaPackage("B");
	    	
	    	a.dependsUpon(b);
	    	b.dependsUpon(b);
	    	
	    	jdepend.printCycleHeader(a);
	    	jdepend.printCycleFooter();
	    	
	    	String expectedHeader = "<Package Name=\"A\">";
	    	String expectedFooter = "</Package>";

	    	assertTrue(stringWriter.toString().contains(expectedHeader));
	    	assertTrue(stringWriter.toString().contains(expectedFooter));
	    	
	    }
	    
	    public void testPrintCycleTarget() throws Exception {
	    	StringWriter stringWriter = new StringWriter();  
	    	PrintWriter writer = new PrintWriter(stringWriter);

	    	jdepend.setWriter(writer);
	    	
	    	JavaPackage a = new JavaPackage("A");
	    	
	    	jdepend.printCycleTarget(a);
	    	
	    	String expected = "<Package>A</Package>";
	    	
	    	assertTrue(stringWriter.toString().contains(expected));	
	    }
	    
	    public void testPrintCycleContributor() throws Exception {
	    	StringWriter stringWriter = new StringWriter();  
	    	PrintWriter writer = new PrintWriter(stringWriter);

	    	jdepend.setWriter(writer);
	    	
	    	JavaPackage a = new JavaPackage("A");
	    	
	    	jdepend.printCycleContributor(a);
	    	
	    	String expected = "<Package>A</Package>";
	    	
	    	assertTrue(stringWriter.toString().contains(expected));
	    	
	    }
	    
	    
}
