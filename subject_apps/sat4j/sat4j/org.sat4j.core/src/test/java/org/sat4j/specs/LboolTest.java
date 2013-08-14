package org.sat4j.specs;

import java.lang.reflect.Constructor;

import junit.framework.TestCase;

public class LboolTest extends TestCase{
	private Lbool lboolF;
	private Lbool lboolT;
	private Lbool lboolU;
	
    public LboolTest(String arg0) {
        super(arg0);
        lboolF = Lbool.FALSE;
        lboolT = Lbool.TRUE;
        lboolU = Lbool.UNDEFINED;
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
    
    public void testFalse() throws Exception {
    	assertEquals("F",lboolF.toString());
    	assertEquals(lboolT, lboolF.not());
    }
    
    public void testTrue() throws Exception {
    	assertEquals("T",lboolT.toString());
    	assertEquals(lboolF, lboolT.not());
    }
    
    public void testUndefined() throws Exception {
    	assertEquals("U",lboolU.toString());
    	assertEquals(lboolU, lboolU.not());
    }
}
