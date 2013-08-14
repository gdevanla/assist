package org.sat4j.minisat.orders;

import junit.framework.TestCase;

public class RSATLastLearnedClausesPhaseSelectionStrategyTest extends TestCase{
	private RSATLastLearnedClausesPhaseSelectionStrategy strategy;
	
	public RSATLastLearnedClausesPhaseSelectionStrategyTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        this.strategy = new RSATLastLearnedClausesPhaseSelectionStrategy();
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testToString() throws Exception {
    	assertEquals("lightweight component caching from RSAT inverting phase for variables at conflict decision level", strategy.toString());
    }
    
    public void testAssignLiteral() throws Exception {
    	strategy.init(100);
    	strategy.assignLiteral(5);
    	
    	assertEquals(5, strategy.select(2));
    }
    
    public void testUpdateLiteral() throws Exception {
    	strategy.init(100);
    	strategy.updateVarAtDecisionLevel(10);
    	
    	assertEquals(10, strategy.select(5));
    }
    
    public void testUpdateVarAtDecisionLevel() throws Exception {
    	strategy.init(10);
    	strategy.updateVarAtDecisionLevel(0);
    	
    	assertEquals(3, strategy.select(1));
    }
    
    public void testUpdateVar() throws Exception {
    	strategy.updateVar(5);
    }
}
