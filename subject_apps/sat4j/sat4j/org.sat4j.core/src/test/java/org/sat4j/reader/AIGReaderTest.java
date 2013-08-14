package org.sat4j.reader;

import static org.mockito.Mockito.mock;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import junit.framework.TestCase;

import org.sat4j.specs.IProblem;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.IVecInt;

public class AIGReaderTest extends TestCase {
	private AIGReader aigReader;
	private ISolver solver;
	
	 public AIGReaderTest(String arg0) {
	        super(arg0);
	    }

	    /*
	     * @see TestCase#setUp()
	     */
	    @Override
	    protected void setUp() throws Exception {
	        super.setUp();
	        solver = mock(ISolver.class);
	        this.aigReader = new AIGReader(solver);
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
	    	try {
	    		aigReader.parseInstance(in);
	    	} catch(Exception e) {
	    		assertEquals("Parsing ErrorAIG format only!", e.getMessage());
	    	}
	    }
	    
	    public void testParseInstanceExample2() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test1.aig");
	    	try {
	    		aigReader.parseInstance(in);
	    	} catch (Exception e) {
	    		assertEquals("Parsing Errorexpected digit", e.getMessage());
	    	}
	    }
	    
	    public void testParseInstanceExample3() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test2.aig");
	    	try {
	    		aigReader.parseInstance(in);
	    	} catch (Exception e) {
	    		assertEquals("Parsing Errorunexpected character", e.getMessage());
	    	}


	    }
	    
	    public void testParseInstanceExample4() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test3.aig");
	    	try {
	    		aigReader.parseInstance(in);
	    	} catch(Exception e) {
	    		assertEquals("Parsing ErrorCNF conversion cannot handle latches!",e.getMessage());
	    	}
	    }
	    
	    public void testParseInstanceExample5() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test4.aig");
	    	try {
	    		aigReader.parseInstance(in);
	    	} catch(Exception e) {
	    		assertEquals("Parsing ErrorCNF conversion allowed for single output circuit only!",e.getMessage());
	    	}
	    }

	    public void testParseInstanceExample6() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test5.aig");

	    	IProblem solver = aigReader.parseInstance(in);
	    	assertEquals(0, solver.nVars());
	    }
	    
	    public void testParseInstanceExample7() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test6.aig");

	    	IProblem solver = aigReader.parseInstance(in);
	    	
	    	assertEquals(0, solver.nVars());
	    }
	    
	    public void testParseInstanceExample8() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test1.aag");
	    	try {
	    		aigReader.parseInstance(in);
	    	} catch(Exception e) {
	    		assertEquals("Parsing ErrorAIG format only!", e.getMessage());
	    	}
	    }
	    
	    public void testParseInstanceExample9() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test7.aig");
	    	try {
	    		aigReader.parseInstance(in);
	    	} catch(Exception e) {
	    		assertEquals("Parsing ErrorAIG format only!", e.getMessage());
	    	}
	    }
	    
	    public void testDecodeExample1() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test5.aig");
	    	aigReader.parseInstance(in);
	    	
	    	int[] model = new int[20];
	    	for(int i=0;i<20;i++)
	    		model[i] = i;
	    	
	    	assertEquals("01111", aigReader.decode(model));
	  
	    }
	    
	    public void testDecodeExample2() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test6.aig");
	    	aigReader.parseInstance(in);
	    	
	    	StringWriter stwr = new StringWriter();
	    	PrintWriter out = new PrintWriter(stwr);
	    	int[] model = new int[20];
	    	for(int i=0;i<20;i++)
	    		model[i] = i;
	    	
	    	aigReader.decode(model, out);
	    	assertEquals("01111", stwr.toString());
	  
	    }
	    
	    
	    public void testDecodeExample3() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/decode.aig");
	    	
	    	assertEquals(4108, AIGReader.decode(in));	    	
	    }
	    
	    public void testReadAnd() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test5.aig");

	       	Object[] params = new Object[4];
	    	params[0] = 1;
	    	params[1] = 1;
	    	params[2] = in;
	    	params[3] = 4;
	    	
	    	Class[] types 	= new Class[params.length];
	    	types[0] = int.class;
	    	types[1] = int.class;
	    	types[2] = InputStream.class;
	    	types[3] = int.class;
	    	
	    	Method m        = aigReader.getClass().getDeclaredMethod( "readAnd", types );
	    	m.setAccessible( true );
	    	m.invoke( aigReader, params );
	    	
	    	assertFalse(solver.isSatisfiable());
	    }
	    
	    public void testToDimacsExample1() throws Exception {
	    	Object[] params = new Object[1];
	    	params[0] = 0;
	    	
	    	Class[] types 	= new Class[params.length];
	    	types[0] = int.class;
	    	
	    	Method m        = aigReader.getClass().getDeclaredMethod( "toDimacs", types );
	    	m.setAccessible( true );
	    	int expected = (Integer) m.invoke( aigReader, params );
	    	
	    	assertEquals(-1, expected);
	    }
	    
	    public void testToDimacsExample2() throws Exception {
	    	Object[] params = new Object[1];
	    	params[0] = 1;
	    	
	    	Class[] types 	= new Class[params.length];
	    	types[0] = int.class;
	    	
	    	Method m        = aigReader.getClass().getDeclaredMethod( "toDimacs", types );
	    	m.setAccessible( true );
	    	int expected = (Integer) m.invoke( aigReader, params );
	    	
	    	assertEquals(1, expected);
	    }
	    
	    public void testSafeGetExample1() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test6.aig");
	    	assertEquals(97, AIGReader.safeGet(in));
	  
	    }
	    
	    public void testSafeGetExample2() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/safeget.aig");
	    	try {
	    		AIGReader.safeGet(in);
	    	} catch (Exception e) {
	    		assertEquals("Parsing ErrorAIG Error, EOF met too early", e.getMessage());
	    	}
	  
	    }

}
