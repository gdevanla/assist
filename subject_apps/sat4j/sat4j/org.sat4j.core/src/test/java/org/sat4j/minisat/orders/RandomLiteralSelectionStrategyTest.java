package org.sat4j.minisat.orders;

import junit.framework.TestCase;

public class RandomLiteralSelectionStrategyTest extends TestCase{
private RandomLiteralSelectionStrategy strategy;
	
	public RandomLiteralSelectionStrategyTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        this.strategy = new RandomLiteralSelectionStrategy();
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testSelectExample1() throws Exception {
    	assertEquals(6, strategy.select(3));
    }
    
    public void testSelectExample2() throws Exception {
    	assertEquals(0, strategy.select(0));
    }
    
    public void testSelectExample3() throws Exception {
    	assertEquals(-3, strategy.select(-2));
    }
    
    public void testToString() throws Exception {
    	assertEquals("random phase selection", strategy.toString());
    }
    
    public void testMethodsForCoverage() throws Exception {
    	strategy.init(3);
    	strategy.init(0, 1);
    	strategy.updateVar(7);
    	strategy.updateVarAtDecisionLevel(10);
    	strategy.assignLiteral(5);
    }
}
