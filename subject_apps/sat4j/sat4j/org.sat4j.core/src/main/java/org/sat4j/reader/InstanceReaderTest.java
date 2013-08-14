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
import org.sat4j.specs.IGroupSolver;
import org.sat4j.specs.IProblem;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.IVecInt;

public class InstanceReaderTest extends TestCase {
	private InstanceReader instanceReader;
	private ISolver solver = SolverFactory.newDefault();
	
	 public InstanceReaderTest(String arg0) {
	        super(arg0);
	    }

	    /*
	     * @see TestCase#setUp()
	     */
	    @Override
	    protected void setUp() throws Exception {
	        super.setUp();
	        this.instanceReader = new InstanceReader(solver);
	    }

	    /*
	     * @see TestCase#tearDown()
	     */
	    @Override
	    protected void tearDown() throws Exception {
	        super.tearDown();
	    }
	    
	    private Object executeMethod( Object instance, String name ) throws Exception
		{
			Class c 	= instance.getClass();
			Method m        = c.getDeclaredMethod( name );
			m.setAccessible( true );

			return m.invoke( instance );
		}
	    
	    public void testGetDefaultSATReader() throws Exception {
	    	LecteurDimacs dimacs = (LecteurDimacs) this.executeMethod(instanceReader, "getDefaultSATReader");
	    	assertFalse(dimacs.isVerbose());
	    	
	    	LecteurDimacs expected = (LecteurDimacs) this.executeMethod(instanceReader, "getDefaultSATReader");
	    	assertFalse(expected.isVerbose());
	    }
	    
	    public void testGetEZSATReader() throws Exception {
	    	DimacsReader ezdimacs = (DimacsReader) this.executeMethod(instanceReader, "getEZSATReader");
	    	assertFalse(ezdimacs.isVerbose());
	    	
	    	DimacsReader expected = (DimacsReader) this.executeMethod(instanceReader, "getEZSATReader");
	    	assertFalse(expected.isVerbose());
	    }
	    
	    public void testGetAIGReader() throws Exception {
	    	AIGReader aig = (AIGReader) this.executeMethod(instanceReader, "getAIGReader");
	    	assertFalse(aig.isVerbose());
	    	
	    	AIGReader expected = (AIGReader) this.executeMethod(instanceReader, "getAIGReader");
	    	assertFalse(expected.isVerbose());
	    }
	    
	    public void testGetAAGReader() throws Exception {
	    	AAGReader aag = (AAGReader) this.executeMethod(instanceReader, "getAAGReader");
	    	assertFalse(aag.isVerbose());
	    	
	    	AAGReader expected = (AAGReader) this.executeMethod(instanceReader, "getAAGReader");
	    	assertFalse(expected.isVerbose());
	    }
	    
	    public void testParseInstanceExampleInputStream() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test1.dimacs");
	    	try {
	    		instanceReader.parseInstance(in);
	    	} catch (Exception e) {
	    		assertEquals("Use a domain specific Reader (LecteurDimacs, AIGReader, etc.) for stream input ", e.getMessage());
	    	}
	    }
	    
	    public void testHandleFileNameExample1() throws Exception {
	    	String filename = "org.sat4j.core/src/test/java/test1.dimacs";
	    	Reader expected = instanceReader.handleFileName(filename, "dimacs");
	    	assertFalse(expected.isVerbose());
	    }
	    
	    public void testHandleFileNameExample2() throws Exception {
	    	String filename = "org.sat4j.core/src/test/java/test1.aag";
	    	Reader expected = instanceReader.handleFileName(filename, "aag");
	    	assertFalse(expected.isVerbose());
	    }
	    
	    public void testHandleFileNameExample3() throws Exception {
	    	String filename = "org.sat4j.core/src/test/java/test5.aig";
	    	Reader expected = instanceReader.handleFileName(filename, "aig");
	    	assertFalse(expected.isVerbose());
	    }
	    
	    public void testHandleFileNameExample4() throws Exception {
	    	String filename = "org.sat4j.core/src/test/java/test1.dimacs";
	    	Reader expected = instanceReader.handleFileName(filename, "EZCNF");
	    	assertFalse(expected.isVerbose());
	    }
	    
	    public void testParseInstanceExample1() throws Exception {
	    	String filename = "http://www.jroller.com/CoBraLorD/entry/junit_testing_private_fields_and";
	    	try {
	    		instanceReader.parseInstance(filename);
	    	} catch (Exception e) {
	    		
	    	}
	    }
	    
	    public void testParseInstanceExample2() throws Exception {
	    	String filename = "ezcnf:org.sat4j.core/src/test/java/test1.dimacs";
	    	IProblem expected =	instanceReader.parseInstance(filename);
	    	assertEquals(4, expected.nVars());
	    }
	    
	    public void testParseInstanceExample3() throws Exception {
	    	String filename = "org.sat4j.core/src/test/java/test1.gz";
	    	try {
	    		instanceReader.parseInstance(filename);
	    	} catch (Exception e) {
	    		
	    	}
	    }
	    
	    public void testDecodeExample1() throws Exception {
	    	String filename = "org.sat4j.core/src/test/java/test1.dimacs";
	    	instanceReader.parseInstance(filename);
	    	
	    	int[] model = new int[20];
	    	for(int i=0;i<20;i++)
	    		model[i] = i;
	    	
	    	assertEquals("0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 0", instanceReader.decode(model));
	  
	    }
	    
	    public void testDecodeExample2() throws Exception {
	    	String filename = "org.sat4j.core/src/test/java/test2.dimacs";
	    	instanceReader.parseInstance(filename);
	    	
	    	StringWriter stwr = new StringWriter();
	    	PrintWriter out = new PrintWriter(stwr);
	    	int[] model = new int[20];
	    	for(int i=0;i<20;i++)
	    		model[i] = i;
	    	
	    	instanceReader.decode(model, out);
	    	assertEquals("0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 0", stwr.toString());
	  
	    }
}


