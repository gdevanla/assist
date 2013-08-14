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

public class DimacsReaderTest extends TestCase {
	private DimacsReader dimacsReader;
	private ISolver solver = SolverFactory.newDefault();
	
	 public DimacsReaderTest(String arg0) {
	        super(arg0);
	    }

	    /*
	     * @see TestCase#setUp()
	     */
	    @Override
	    protected void setUp() throws Exception {
	        super.setUp();
	        this.dimacsReader = new DimacsReader(solver);
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
	    	IProblem expected = dimacsReader.parseInstance(in);	
	    	
	    	assertEquals(4, expected.nVars());
	    }
	    
	    public void testParseInstanceExample2() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test2.dimacs");
	    	IProblem expected = dimacsReader.parseInstance(in);	  
	    	assertEquals(4, expected.nVars());
	    }
	    
	    public void testParseInstanceExample3() throws Exception {
	    	ISolver s =  mock(ISolver.class);
	    	DimacsReader dReader = new DimacsReader(s);
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test3.dimacs");
	    	IProblem expected = dReader.parseInstance(in);	
	    	assertEquals(0, expected.nVars());
	    }
	    
	    public void testParseInstanceExample4() throws Exception {
	    	ISolver s =  mock(ISolver.class);
	    	DimacsReader dReader = new DimacsReader(s);
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/testcomments.dimacs");
	    	IProblem expected = dReader.parseInstance(in);
	    	assertEquals(0, expected.nVars());
	    }
	    
	    public void testParseInstanceExample5() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test1.aag");
	    	try {
	    		dimacsReader.parseInstance(in);
	    	} catch(Exception e) {
	    		assertEquals("Parsing Errorproblem line expected (p cnf ...)", e.getMessage());
	    	}
	    }

	    public void testParseInstanceExample6() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/invalid.dimacs");
	    	try {
	    		dimacsReader.parseInstance(in);
	    	} catch (Exception e) {
	    		assertEquals("Parsing Errorinteger value expected ", e.getMessage());
	    	}	    	
	    }
	    
	    public void testParseInstanceExample7() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test4.dimacs");
	    	dimacsReader.expectedNbOfConstr=0;
	    	solver.setVerbose(true);
	    	IProblem expected = dimacsReader.parseInstance(in);	   
	    	assertEquals(0, expected.nVars());
	    }
	    
	    
	    public void testParseInstanceExample8() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/testcomments.dimacs");
	    	in.close();
	    	try {
	    		dimacsReader.parseInstance(in);
	    	} catch (Exception e) {
	    		assertEquals("java.io.IOException: Bad file descriptor", e.getMessage());
	    	}
	    	
	    }
	   
	    public void testParseInstanceExample9() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test3.dimacs");
	    	dimacsReader.disableNumberOfConstraintCheck();
	    	try {
	    		dimacsReader.parseInstance(in);
	    	} catch (Exception e) {
	    		
	    	}
	    	
	    }
	    
	    public void testParseInstanceExample10() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test5.dimacs");
	    	try {
	    		dimacsReader.parseInstance(in);
	    	} catch (Exception e) {
	    		assertEquals("Parsing Errorwrong nbclauses parameter. Found 0, 1 expected", e.getMessage());
	    	}
	    }
	    
	    public void testParseInstanceExample11() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test4.dimacs");
	    	dimacsReader.expectedNbOfConstr=0;
	    	solver.setVerbose(false);
	    	IProblem expected = dimacsReader.parseInstance(in);	   
	    	assertEquals(0, expected.nVars());
	    }
	    
	    
	    public void testDecodeExample1() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test1.dimacs");
	    	dimacsReader.parseInstance(in);
	    	
	    	int[] model = new int[20];
	    	for(int i=0;i<20;i++)
	    		model[i] = i;
	    	
	    	assertEquals("0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 0", dimacsReader.decode(model));
	  
	    }
	    
	    public void testDecodeExample2() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test2.dimacs");
	    	dimacsReader.parseInstance(in);
	    	
	    	StringWriter stwr = new StringWriter();
	    	PrintWriter out = new PrintWriter(stwr);
	    	int[] model = new int[20];
	    	for(int i=0;i<20;i++)
	    		model[i] = i;
	    	
	    	dimacsReader.decode(model, out);
	    	assertEquals("0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 0", stwr.toString());
	  
	    }
	    
	    public void testGetSolver() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test1.dimacs");
	    	IProblem solver = dimacsReader.parseInstance(in);	
	    	
	    	assertEquals(solver, dimacsReader.getSolver());
	    }

}
