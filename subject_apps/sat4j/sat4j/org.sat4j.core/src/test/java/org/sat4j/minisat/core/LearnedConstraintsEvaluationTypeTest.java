package org.sat4j.minisat.core;

import junit.framework.TestCase;

public class LearnedConstraintsEvaluationTypeTest extends TestCase{
	public LearnedConstraintsEvaluationTypeTest(String arg0) {
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
    
	public void testLearnedConstraintsEvaluationType() {
		assertEquals("ACTIVITY", LearnedConstraintsEvaluationType.ACTIVITY.toString());
		assertEquals("LBD", LearnedConstraintsEvaluationType.LBD.toString());
		assertEquals("LBD2", LearnedConstraintsEvaluationType.LBD2.toString());
	}
	
	public void testValues() {
		assertEquals(3, LearnedConstraintsEvaluationType.values().length);		
	}
	
	public void testValueOf() {
		assertEquals(LearnedConstraintsEvaluationType.ACTIVITY, LearnedConstraintsEvaluationType.valueOf("ACTIVITY"));
		assertEquals(LearnedConstraintsEvaluationType.LBD, LearnedConstraintsEvaluationType.valueOf("LBD"));
		assertEquals(LearnedConstraintsEvaluationType.LBD2, LearnedConstraintsEvaluationType.valueOf("LBD2"));
	}
}
