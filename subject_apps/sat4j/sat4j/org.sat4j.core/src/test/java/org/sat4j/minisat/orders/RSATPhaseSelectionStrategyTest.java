package org.sat4j.minisat.orders;

import junit.framework.TestCase;

public class RSATPhaseSelectionStrategyTest extends TestCase{
private RSATPhaseSelectionStrategy strategy;
	
	public RSATPhaseSelectionStrategyTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        this.strategy = new RSATPhaseSelectionStrategy();
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testToString() throws Exception {
    	assertEquals("lightweight component caching from RSAT", strategy.toString());
    }
    
    public void testAssignLiteral() throws Exception {
    	strategy.init(100);
    	strategy.assignLiteral(5);
    	
    	assertEquals(5, strategy.select(2));
    }
    
    public void testUpdateLiteral() throws Exception {
    	strategy.init(100);
    	strategy.updateVarAtDecisionLevel(10);
    	
    	assertEquals(11, strategy.select(5));
    }
    
    public void testUpdateVarAtDecisionLevel() throws Exception {
    	strategy.updateVarAtDecisionLevel(0);
    }
    
    public void testUpdateVar() throws Exception {
    	strategy.updateVar(5);
    }
}
