package org.sat4j.minisat.orders;

import junit.framework.TestCase;

public class PhaseInLastLearnedClauseSelectionStrategyTest extends TestCase{
private PhaseInLastLearnedClauseSelectionStrategy strategy;
	
	public PhaseInLastLearnedClauseSelectionStrategyTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        this.strategy = new PhaseInLastLearnedClauseSelectionStrategy();
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testToString() throws Exception {
    	assertEquals("phase appearing in latest learned clause", strategy.toString());
    }
    
    public void testUpdateLiteral() throws Exception {
    	strategy.init(100);
    	strategy.updateVar(10);
    	
    	assertEquals(10, strategy.select(5));
    }
    
    public void testUpdateVar() throws Exception {
    	strategy.init(10);
    	strategy.updateVar(0);
    	
    	assertEquals(3, strategy.select(1));
    }
    
    public void testUpdateVarAtDecisionLevel() throws Exception {
    	strategy.updateVarAtDecisionLevel(5);
    }

    public void testAssignLiteral() throws Exception {
    	strategy.assignLiteral(5);
    }
}
