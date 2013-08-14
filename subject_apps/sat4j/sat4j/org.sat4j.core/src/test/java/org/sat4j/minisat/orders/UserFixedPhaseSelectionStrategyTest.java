package org.sat4j.minisat.orders;

import junit.framework.TestCase;

public class UserFixedPhaseSelectionStrategyTest extends TestCase{
private UserFixedPhaseSelectionStrategy strategy;
	
	public UserFixedPhaseSelectionStrategyTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        this.strategy = new UserFixedPhaseSelectionStrategy();
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testToString() throws Exception {
    	assertEquals("Fixed selection strategy.", strategy.toString());
    }
    
    public void testAssignLiteral() throws Exception {
    	strategy.assignLiteral(5);
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
