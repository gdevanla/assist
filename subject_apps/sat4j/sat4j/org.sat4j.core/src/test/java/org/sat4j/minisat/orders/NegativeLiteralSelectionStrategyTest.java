package org.sat4j.minisat.orders;

import junit.framework.TestCase;

import org.sat4j.tools.xplain.DeletionStrategy;

public class NegativeLiteralSelectionStrategyTest extends TestCase{
	private NegativeLiteralSelectionStrategy strategy;
	
	public NegativeLiteralSelectionStrategyTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        this.strategy = new NegativeLiteralSelectionStrategy();
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testSelectExample1() throws Exception {
    	assertEquals(7, strategy.select(3));
    }
    
    public void testSelectExample2() throws Exception {
    	assertEquals(1, strategy.select(0));
    }
    
    public void testSelectExample3() throws Exception {
    	assertEquals(-3, strategy.select(-2));
    }
    
    public void testToString() throws Exception {
    	assertEquals("negative phase selection", strategy.toString());
    }
    
    public void testMethodsForCoverage() throws Exception {
    	strategy.assignLiteral(5);
    	strategy.init(3);
    	strategy.init(0, 1);
    	strategy.updateVar(7);
    	strategy.updateVarAtDecisionLevel(10);
    }
}
