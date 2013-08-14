package org.sat4j.specs;

import junit.framework.TestCase;

public class TimeoutExceptionTest extends TestCase{
	private TimeoutException timeoutException;
    
    public TimeoutExceptionTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.timeoutException = new TimeoutException();
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testConstructorExample1() throws Exception {
    	assertEquals(null,timeoutException.getMessage());
    }
    
    public void testConstructorExample2() throws Exception {
    	String message = "Timeout exception thrown";
    	TimeoutException exception = new TimeoutException(message);
    	
    	assertEquals(message,exception.getMessage());
    }
    
    public void testConstructorExample3() throws Exception {
    	Throwable cause = new Throwable();
    	TimeoutException exception = new TimeoutException(cause);
    	
    	assertEquals(cause,exception.getCause());
    }
    
    public void testConstructorExample4() throws Exception {
    	String message = "Timeout exception thrown";
    	Throwable cause = new Throwable();
    	TimeoutException exception = new TimeoutException(message, cause);
    	
    	assertEquals(message,exception.getMessage());
    	assertEquals(cause,exception.getCause());
    }
}
