package org.sat4j.reader;

import static org.mockito.Mockito.mock;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import junit.framework.TestCase;

import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.IProblem;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.IVecInt;

public class LecteurDimacsTest extends TestCase {
	private LecteurDimacs lecteurDimacs;
	private ISolver solver = SolverFactory.newDefault();
	
	 public LecteurDimacsTest(String arg0) {
	        super(arg0);
	    }

	    /*
	     * @see TestCase#setUp()
	     */
	    @Override
	    protected void setUp() throws Exception {
	        super.setUp();
	        this.lecteurDimacs = new LecteurDimacs(solver);
	    }

	    /*
	     * @see TestCase#tearDown()
	     */
	    @Override
	    protected void tearDown() throws Exception {
	        super.tearDown();
	    }
	    
	    public void testParseInstanceExample1() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test1.dimacs");
	    	IProblem expected = lecteurDimacs.parseInstance(in);	
	    	
	    	assertEquals(4, expected.nVars());
	    }
	    
	    public void testParseInstanceExample2() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test2.dimacs");
	    	IProblem expected = lecteurDimacs.parseInstance(in);	  
	    	assertEquals(4, expected.nVars());
	    }
	    
	    public void testParseInstanceExample3() throws Exception {
	    	ISolver s =  mock(ISolver.class);
	    	LecteurDimacs dReader = new LecteurDimacs(s);
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test3.dimacs");
	    	IProblem expected = dReader.parseInstance(in);	
	    	assertEquals(0, expected.nVars());
	    }
	    
	    public void testParseInstanceExample4() throws Exception {
	    	ISolver s =  mock(ISolver.class);
	    	LecteurDimacs dReader = new LecteurDimacs(s);
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/testcomments.dimacs");
	    	try {
	    		dReader.parseInstance(in);
	    	} catch (Exception e) {
	    		assertEquals("Parsing ErrorUnknown character c", e.getMessage());
	    	}
	    }
	    
	    public void testParseInstanceExample5() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test1.aag");
	    	try {
	    		lecteurDimacs.parseInstance(in);
	    	} catch(Exception e) {
	    		assertEquals("Parsing ErrorDIMACS error: wrong max number of variables", e.getMessage());
	    	}
	    }

	    public void testParseInstanceExample6() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/invalid.dimacs");
	    	try {
	    		lecteurDimacs.parseInstance(in);
	    	} catch (Exception e) {
	    		assertEquals("Parsing ErrorDIMACS error: wrong max number of variables", e.getMessage());
	    	}	    	
	    }
	    
	    public void testParseInstanceExample7() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test4.dimacs");
	    	solver.setVerbose(true);
	    	IProblem expected = lecteurDimacs.parseInstance(in);	   
	    	assertEquals(0, expected.nVars());
	    }
	    
	    
	    public void testParseInstanceExample8() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/testcomments.dimacs");
	    	in.close();
	    	try {
	    		lecteurDimacs.parseInstance(in);
	    	} catch (Exception e) {
	    		assertEquals("Bad file descriptor", e.getMessage());
	    	}
	    	
	    }
	    
	    public void testParseInstanceExample10() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test5.dimacs");
	    	try {
	    		lecteurDimacs.parseInstance(in);
	    	} catch (Exception e) {
	    		assertEquals("Creating Empty clause ?", e.getMessage());
	    	}
	    }
	    
	    public void testParseInstanceExample11() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test4.dimacs");
	    	solver.setVerbose(false);
	    	IProblem expected = lecteurDimacs.parseInstance(in);	   
	    	assertEquals(0, expected.nVars());
	    }
	    
	    public void testParseInstanceExample12() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test7.dimacs");
	    	solver.setVerbose(false);
	    	try{
	    		lecteurDimacs.parseInstance(in);	   
	    	} catch (Exception e) {
	    		assertEquals("Creating Empty clause ?", e.getMessage());
	    	}
	    	
	    }
	    
	    public void testParseInstanceExample13() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/error.dimacs");
	    	
	    	try{
	    		lecteurDimacs.parseInstance(in);	   
	    	} catch (Exception e) {
	    		assertEquals("Parsing ErrorDIMACS error: the clauses are missing", e.getMessage());
	    	}
	    	
	    }
	    
	    public void testDecodeExample1() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test1.dimacs");
	    	lecteurDimacs.parseInstance(in);
	    	
	    	int[] model = new int[20];
	    	for(int i=0;i<20;i++)
	    		model[i] = i;
	    	
	    	assertEquals("0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 0", lecteurDimacs.decode(model));
	  
	    }
	    
	    public void testDecodeExample2() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test2.dimacs");
	    	lecteurDimacs.parseInstance(in);
	    	
	    	StringWriter stwr = new StringWriter();
	    	PrintWriter out = new PrintWriter(stwr);
	    	int[] model = new int[20];
	    	for(int i=0;i<20;i++)
	    		model[i] = i;
	    	
	    	lecteurDimacs.decode(model, out);
	    	assertEquals("0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 0", stwr.toString());
	  
	    }

}
