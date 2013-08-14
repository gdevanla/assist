package org.sat4j.specs;

import junit.framework.TestCase;

public class ContradictionExceptionTest extends TestCase{
	private ContradictionException contradictionException;
    
    public ContradictionExceptionTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.contradictionException = new ContradictionException();
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testConstructorExample1() throws Exception {
    	assertEquals(null,contradictionException.getMessage());
    }
    
    public void testConstructorExample2() throws Exception {
    	String message = "Contradiction exception thrown";
    	ContradictionException exception = new ContradictionException(message);
    	
    	assertEquals(message,exception.getMessage());
    }
    
    public void testConstructorExample3() throws Exception {
    	Throwable cause = new Throwable();
    	ContradictionException exception = new ContradictionException(cause);
    	
    	assertEquals(cause,exception.getCause());
    }
    
    public void testConstructorExample4() throws Exception {
    	String message = "Contradiction exception thrown";
    	Throwable cause = new Throwable();
    	ContradictionException exception = new ContradictionException(message, cause);
    	
    	assertEquals(message,exception.getMessage());
    	assertEquals(cause,exception.getCause());
    }
}
