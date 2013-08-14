package org.sat4j.reader;

import static org.mockito.Mockito.mock;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import junit.framework.TestCase;

import org.sat4j.specs.ISolver;
import org.sat4j.specs.IVecInt;

public class AAGReaderTest extends TestCase {
	private AAGReader aagReader;
	private ISolver solver;
	
	 public AAGReaderTest(String arg0) {
	        super(arg0);
	    }

	    /*
	     * @see TestCase#setUp()
	     */
	    @Override
	    protected void setUp() throws Exception {
	        super.setUp();
	        solver = mock(ISolver.class);
	        this.aagReader = new AAGReader(solver);
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
	    		aagReader.parseInstance(in);
	    	} catch(Exception e) {
	    		assertEquals("Parsing ErrorAAG format only!", e.getMessage());
	    	}
	    }
	    
	    public void testParseInstanceExample2() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test1.aag");
	    	aagReader.parseInstance(in);

	    	
	    	Field f = aagReader.getClass().getDeclaredField("nbinputs");
			f.setAccessible( true );
			int inner = (Integer) f.get( aagReader );
			
			assertEquals(5, inner);
	    }
	    
	    public void testParseInstanceExample3() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test2.aag");
	    	try {
	    		aagReader.parseInstance(in);
	    	} catch(Exception e) {
	    		assertEquals("Parsing ErrorCNF conversion allowed for single output circuit only!", e.getMessage());
	    	}

	    }
	    
	    public void testParseInstanceExample4() throws Exception {
	    	String filename = "http://www.jroller.com/CoBraLorD/entry/junit_testing_private_fields_and";
	    	try {
	    		aagReader.parseInstance(filename);
	    	} catch (Exception e) {
	    		
	    	}
	    }
	    
	    public void testParseInstanceExample5() throws Exception {
	    	String filename = "org.sat4j.core/src/test/java/test1.gz";
	    	try {
	    		aagReader.parseInstance(filename);
	    	} catch (Exception e) {
	    		
	    	}
	    }
	    
	    public void testParseInstanceExample6() throws Exception {
	    	String filename = "org.sat4j.core/src/test/java/test1.bz2";
	    	try {
	    		aagReader.parseInstance(filename);
	    	} catch (Exception e) {
	    		
	    	}
	    }
	    
	    public void testParseInstanceExample7() throws Exception {
	    	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	    	try {
	    		aagReader.parseInstance(reader);
	    	} catch (Exception e) {
	    		assertEquals("Use #parseInstance(InputStream) instead",e.getMessage());
	    	}
	    }
	    
	    public void testDecodeExample1() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test1.aag");
	    	aagReader.parseInstance(in);
	    	
	    	int[] model = new int[20];
	    	for(int i=0;i<20;i++)
	    		model[i] = i;
	    	
	    	assertEquals("01111", aagReader.decode(model));
	  
	    }
	    
	    public void testDecodeExample2() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test1.aag");
	    	aagReader.parseInstance(in);
	    	
	    	StringWriter stwr = new StringWriter();
	    	PrintWriter out = new PrintWriter(stwr);
	    	int[] model = new int[20];
	    	for(int i=0;i<20;i++)
	    		model[i] = i;
	    	
	    	aagReader.decode(model, out);
	    	assertEquals("01111", stwr.toString());
	  
	    }
	    
	    public void testReadAnd() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test3.aag");
	    	EfficientScanner scanner = new EfficientScanner(in);

	       	Object[] params = new Object[3];
	    	params[0] = 1;
	    	params[1] = 1;
	    	params[2] = scanner;
	    	
	    	Class[] types 	= new Class[params.length];
	    	types[0] = int.class;
	    	types[1] = int.class;
	    	types[2] = scanner.getClass();
	    	
	    	Method m        = aagReader.getClass().getDeclaredMethod( "readAnd", types );
	    	m.setAccessible( true );
	    	m.invoke( aagReader, params );
	    	
	    	assertFalse(solver.isSatisfiable());
	    }
	    
	    public void testReadOutput() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test3.aag");
	    	EfficientScanner scanner = new EfficientScanner(in);
	    	
	    	Object[] params = new Object[2];
	    	params[0] = 2;
	    	params[1] = scanner;
	    	
	    	Class[] types 	= new Class[params.length];
	    	types[0] = int.class;
	    	types[1] = scanner.getClass();
	    	
	    	Method m        = aagReader.getClass().getDeclaredMethod( "readOutput", types );
	    	m.setAccessible( true );
	    	int expected = (Integer) m.invoke( aagReader, params );
	    	
	    	assertEquals(4, expected);
	    }
	    
	    public void testReadInput() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test3.aag");
	    	EfficientScanner scanner = new EfficientScanner(in);
	    	
	    	Object[] params = new Object[2];
	    	params[0] = 3;
	    	params[1] = scanner;
	    	
	    	Class[] types 	= new Class[params.length];
	    	types[0] = int.class;
	    	types[1] = scanner.getClass();
	    	
	    	Method m        = aagReader.getClass().getDeclaredMethod( "readInput", types );
	    	m.setAccessible( true );
	    	IVecInt expected = (IVecInt) m.invoke( aagReader, params );
	    	
	    	assertEquals(3, expected.size());
	    }
	    
	    public void testToDimacsExample1() throws Exception {
	    	Object[] params = new Object[1];
	    	params[0] = 0;
	    	
	    	Class[] types 	= new Class[params.length];
	    	types[0] = int.class;
	    	
	    	Method m        = aagReader.getClass().getDeclaredMethod( "toDimacs", types );
	    	m.setAccessible( true );
	    	int expected = (Integer) m.invoke( aagReader, params );
	    	
	    	assertEquals(-1, expected);
	    }
	    
	    public void testToDimacsExample2() throws Exception {
	    	Object[] params = new Object[1];
	    	params[0] = 1;
	    	
	    	Class[] types 	= new Class[params.length];
	    	types[0] = int.class;
	    	
	    	Method m        = aagReader.getClass().getDeclaredMethod( "toDimacs", types );
	    	m.setAccessible( true );
	    	int expected = (Integer) m.invoke( aagReader, params );
	    	
	    	assertEquals(1, expected);
	    }
	    
	    public void testVerbosity() throws Exception {
	    	aagReader.setVerbosity(true);
	    	assertTrue(aagReader.isVerbose());
	    }
}
