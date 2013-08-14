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
import java.math.BigInteger;

import junit.framework.TestCase;

import org.sat4j.specs.ISolver;
import org.sat4j.specs.IVecInt;

public class EfficientScannerTest extends TestCase {
	 private static final char EOF = (char) -1;
	 public EfficientScannerTest(String arg0) {
	        super(arg0);
	    }

	    /*
	     * @see TestCase#setUp()
	     */
	    @Override
	    protected void setUp() throws Exception {
	        super.setUp();
	    }

	    /*
	     * @see TestCase#tearDown()
	     */
	    @Override
	    protected void tearDown() throws Exception {
	        super.tearDown();
	    }
	    
	    public void testEOF() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test6.dimacs");
	    	EfficientScanner scanner = new EfficientScanner(in, 'c');
	    	
	    	assertFalse(scanner.eof());
	    	scanner.next();
	    	
	    	assertTrue(scanner.eof());
	    }
	    
	    public void testCurrentChar() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/invalid.dimacs");
	    	EfficientScanner scanner = new EfficientScanner(in);
	    	assertEquals('p', scanner.currentChar());
	    }
	    
	    public void testSkipRestOfLine() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test7.dimacs");
	    	EfficientScanner scanner = new EfficientScanner(in);
	    	while(!scanner.eof()) 
	    		scanner.skipRestOfLine();
	    	
	    	assertTrue(scanner.eof());
	    }
	    
	    public void testNextLine() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test7.dimacs");
	    	EfficientScanner scanner = new EfficientScanner(in);
	    	
	    	assertEquals("c test 3 clauses unitaires et deux clauses binaires\n", scanner.nextLine());
	    	
	    	while(!scanner.eof())
	    		scanner.nextLine();
	    	
	    	assertTrue(scanner.eof());
	    }
	    
	    public void testSkipSpaces() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test7.dimacs");
	    	EfficientScanner scanner = new EfficientScanner(in);
	    	char expected = 'c';
	    	assertEquals(expected, scanner.skipSpaces());
	    	
	    	while(!scanner.eof())
	    		scanner.skipSpaces();
	    	
	    	assertTrue(scanner.eof());
	    }
	    
	    public void testNext() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test7.dimacs");
	    	EfficientScanner scanner = new EfficientScanner(in);
	    	
	    	assertEquals("c", scanner.next());
	    	
	    	while(!scanner.eof())
	    		scanner.next();
	    	
	    	assertTrue(scanner.eof());
	    }
	    
	    public void testSkipComments() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/testcomments.dimacs");
	    	EfficientScanner scanner = new EfficientScanner(in);
	    	char expected = 'p';
	    	
	    	scanner.skipComments();
	    	
	    	assertEquals(expected, scanner.currentChar());
	    }
	    
	    public void testNextBigIntegerExample1() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/bug001.cnf");
	    	EfficientScanner scanner = new EfficientScanner(in);
	    	try {
	    		scanner.nextBigInteger();
	    	} catch (Exception e) {
	    		assertEquals("Parsing ErrorUnknown character c", e.getMessage());
	    	}
	    }
	    
	    public void testNextBigIntegerExample2() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/bug001.cnf");
	    	EfficientScanner scanner = new EfficientScanner(in);
	    	for(int i=0;i<13;i++)
	    		scanner.nextLine();
	    	
	    	assertEquals(BigInteger.valueOf(11), scanner.nextBigInteger());

	    }
	    
	    public void testNextBigIntegerExample3() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/bug001.cnf");
	    	EfficientScanner scanner = new EfficientScanner(in);
	    	for(int i=0;i<15;i++)
	    		scanner.nextLine();
	    	
	    	assertEquals(BigInteger.valueOf(-13), scanner.nextBigInteger());

	    }
	    
	    public void testNextBigIntegerExample4() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test1.aag");
	    	EfficientScanner scanner = new EfficientScanner(in);
	    	
	    	scanner.nextLine();
	    	
	    	assertEquals(BigInteger.valueOf(1), scanner.nextBigInteger());

	    }
	    
	    public void testNextBigIntegerExample5() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test7.dimacs");
	    	EfficientScanner scanner = new EfficientScanner(in);
	    	for(int i=0;i<3;i++)
	    	scanner.nextLine();
	    	
	    	assertEquals(BigInteger.valueOf(0), scanner.nextBigInteger());

	    }
	    
	    public void testNextIntExample1() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/bug001.cnf");
	    	EfficientScanner scanner = new EfficientScanner(in);
	    	try {
	    		scanner.nextInt();
	    	} catch (Exception e) {
	    		assertEquals("Parsing ErrorUnknown character c", e.getMessage());
	    	}
	    }
	    
	    public void testNextIntExample2() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/bug001.cnf");
	    	EfficientScanner scanner = new EfficientScanner(in);
	    	for(int i=0;i<13;i++)
	    		scanner.nextLine();
	    	
	    	assertEquals(11, scanner.nextInt());

	    }
	    
	    public void testNextIntExample3() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/bug001.cnf");
	    	EfficientScanner scanner = new EfficientScanner(in);
	    	for(int i=0;i<15;i++)
	    		scanner.nextLine();
	    	
	    	assertEquals(-13, scanner.nextInt());

	    }
	    
	    public void testNextIntExample4() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test1.aag");
	    	EfficientScanner scanner = new EfficientScanner(in);
	    	
	    	scanner.nextLine();
	    	
	    	assertEquals(1, scanner.nextInt());

	    }
	    
	    public void testNextIntExample5() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test7.dimacs");
	    	EfficientScanner scanner = new EfficientScanner(in);
	    	for(int i=0;i<3;i++)
	    	scanner.nextLine();
	    	
	    	assertEquals(0, scanner.nextInt());

	    }
	    
	    public void testClose() throws Exception {
	    	InputStream in  = new FileInputStream("org.sat4j.core/src/test/java/test7.dimacs");
	    	EfficientScanner scanner = new EfficientScanner(in);
	    	scanner.close();
	    	
	    	try {
	    		scanner.currentChar();
	    	}catch (Exception e) {
	    		
	    	}
	    }
	    		    
}
