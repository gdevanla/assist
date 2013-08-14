package org.sat4j.minisat.core;

import junit.framework.TestCase;

public class SimplicationTypeTest extends TestCase{
	public SimplicationTypeTest(String arg0) {
        super(arg0);
    }
	/*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {

    }
    
	/*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
	
    public void testSimplicationType() {
		assertEquals("NO_SIMPLIFICATION", SimplificationType.NO_SIMPLIFICATION.toString());
		assertEquals("EXPENSIVE_SIMPLIFICATION", SimplificationType.EXPENSIVE_SIMPLIFICATION.toString());
		assertEquals("SIMPLE_SIMPLIFICATION", SimplificationType.SIMPLE_SIMPLIFICATION.toString());
	}
	
	public void testValues() {
		assertEquals(3, SimplificationType.values().length);		
	}
	
	public void testValueOf() {
		assertEquals(SimplificationType.NO_SIMPLIFICATION, SimplificationType.valueOf("NO_SIMPLIFICATION"));
		assertEquals(SimplificationType.EXPENSIVE_SIMPLIFICATION, SimplificationType.valueOf("EXPENSIVE_SIMPLIFICATION"));
		assertEquals(SimplificationType.SIMPLE_SIMPLIFICATION, SimplificationType.valueOf("SIMPLE_SIMPLIFICATION"));
	}
}
