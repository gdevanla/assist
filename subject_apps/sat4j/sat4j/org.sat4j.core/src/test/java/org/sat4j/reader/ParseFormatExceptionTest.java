package org.sat4j.reader;

import junit.framework.TestCase;

public class ParseFormatExceptionTest extends TestCase{
	private ParseFormatException parseFormatException;
    
    public ParseFormatExceptionTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.parseFormatException = new ParseFormatException();
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testConstructorExample1() throws Exception {
    	assertEquals("Parsing Error",parseFormatException.getMessage());
    }
    
    public void testConstructorExample2() throws Exception {
    	String message = "Parse Format exception thrown";
    	ParseFormatException exception = new ParseFormatException(message);
    	
    	assertEquals("Parsing Error"+message,exception.getMessage());
    }
    
    public void testConstructorExample3() throws Exception {
    	Throwable cause = new Throwable();
    	ParseFormatException exception = new ParseFormatException(cause);
    	
    	assertEquals(cause,exception.getCause());
    }
    
    public void testConstructorExample4() throws Exception {
    	String message = "Parse Format exception thrown";
    	Throwable cause = new Throwable();
    	ParseFormatException exception = new ParseFormatException(message, cause);
    	
    	assertEquals("Parsing Error"+message,exception.getMessage());
    	assertEquals(cause,exception.getCause());
    }
}
