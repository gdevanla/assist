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

import org.sat4j.specs.IGroupSolver;
import org.sat4j.specs.IProblem;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.IVecInt;

public class GroupedCNFReaderTest extends TestCase {
	private GroupedCNFReader groupedCNFReader;
	private IGroupSolver solver;
	
	 public GroupedCNFReaderTest(String arg0) {
	        super(arg0);
	    }

	    /*
	     * @see TestCase#setUp()
	     */
	    @Override
	    protected void setUp() throws Exception {
	        super.setUp();
	        solver = mock(IGroupSolver.class);
	        this.groupedCNFReader = new GroupedCNFReader(solver);
	    }

	    /*
	     * @see TestCase#tearDown()
	     */
	    @Override
	    protected void tearDown() throws Exception {
	        super.tearDown();
	    }
	    
	    public void testParseInstanceExample1() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test1.gcnf");
	    	try {
	    		groupedCNFReader.parseInstance(in);
	    	} catch (Exception e) {
	    		assertEquals("Parsing Errorproblem line expected (p gcnf ...)", e.getMessage());
	    	}
	    	
	    }
	    
	    public void testParseInstanceExample2() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test2.gcnf");
	    	try {
	    		groupedCNFReader.parseInstance(in);
	    	} catch (Exception e) {
	    		assertEquals("Parsing ErrorComponent index required at the beginning of the clause", e.getMessage());
	    	}

	    }
	    
	    public void testParseInstanceExample3() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test3.gcnf");
	    	try {
	    		groupedCNFReader.parseInstance(in);
	    	} catch (Exception e){
	    		assertEquals("Parsing Errorwrong component index: -2", e.getMessage());
	    	}
	    	
	    }
	    
	    public void testParseInstanceExample4() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test4.gcnf");
	    	IProblem s = groupedCNFReader.parseInstance(in);
	    	assertEquals(0, s.nVars());
	    }

	    public void testParseInstanceExample5() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/invalid.gcnf");
	    	try {
	    		groupedCNFReader.parseInstance(in);
	    	} catch (Exception e) {
	    		assertEquals("Parsing Errorinteger value expected ", e.getMessage());
	    	}	    	
	    }
	    
	    public void testParseInstanceExample6() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test4.gcnf");
	    	in.close();
	    	try {
	    		groupedCNFReader.parseInstance(in);
	    	} catch (Exception e) {
	    		assertEquals("java.io.IOException: Bad file descriptor", e.getMessage());
	    	}
	    	
	    }
	    
	    public void testParseInstanceExample7() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test5.gcnf");
	    	IProblem s = groupedCNFReader.parseInstance(in);
	    	assertEquals(0, s.nVars());
	    }
	    
	    public void testParseInstanceExample8() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test6.gcnf");
	    	try {
	    		groupedCNFReader.parseInstance(in);
	    	} catch (Exception e) {
	    		assertEquals("Parsing Errorwrong component index: 5", e.getMessage());
	    	}
	    	
	    }
	    
	    public void testParseInstanceExample9() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test7.gcnf");
	    	IProblem s = groupedCNFReader.parseInstance(in);
	    	assertEquals(0, s.nVars());
	    }
	    
	    public void testParseInstanceExample10() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/invalid1.gcnf");
	    	try {
	    		groupedCNFReader.parseInstance(in);
	    	} catch(Exception e) {
	    		assertEquals("Parsing Errorproblem line expected (p gcnf ...)", e.getMessage());
	    	}
	    }
	    
}

