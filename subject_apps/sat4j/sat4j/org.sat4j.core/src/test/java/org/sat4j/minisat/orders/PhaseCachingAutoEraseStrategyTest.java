package org.sat4j.minisat.orders;

import junit.framework.TestCase;

public class PhaseCachingAutoEraseStrategyTest extends TestCase{
	private PhaseCachingAutoEraseStrategy strategy;
	
	public PhaseCachingAutoEraseStrategyTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        this.strategy = new PhaseCachingAutoEraseStrategy();
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testToString() throws Exception {
    	assertEquals("Phase caching with auto forget feature", strategy.toString());
    }
    
    public void testAssignLiteral() throws Exception {
    	strategy.init(100);
    	strategy.assignLiteral(5);
    	
    	assertEquals(5, strategy.select(2));
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
    
    public void testUpdate() throws Exception {
    	strategy.init(100);
    	strategy.init(0,3);
    	
    	assertEquals(21, strategy.select(10));
    }
    
    public void testUpdateVarAtDecisionLevel() throws Exception {
    	strategy.updateVarAtDecisionLevel(5);
    }

    public void testInitExample1() throws Exception {
    	strategy.init(10);
    	strategy.init(5);
    	
    	assertEquals(10, strategy.phase.length);

    }
    
    public void testInitExample2() throws Exception {
    	strategy.init(10);
    	strategy.init(5);
    	
    	assertEquals(10, strategy.phase.length);

    }
}
