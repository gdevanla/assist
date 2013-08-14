package org.sat4j.minisat.orders;

import junit.framework.TestCase;

public class PositiveLiteralSelectionStrategyTest extends TestCase{
	private PositiveLiteralSelectionStrategy strategy;
	
	public PositiveLiteralSelectionStrategyTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        this.strategy = new PositiveLiteralSelectionStrategy();
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
    	assertEquals(-4, strategy.select(-2));
    }
    
    public void testToString() throws Exception {
    	assertEquals("positive phase selection", strategy.toString());
    }
    
    public void testMethodsForCoverage() throws Exception {
    	strategy.assignLiteral(5);
    	strategy.init(3);
    	strategy.init(0, 1);
    	strategy.updateVar(7);
    	strategy.updateVarAtDecisionLevel(10);
    }
}
