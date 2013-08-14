package org.sat4j.minisat.restarts;

import java.lang.reflect.Field;

import org.sat4j.minisat.core.SearchParams;

import junit.framework.TestCase;

public class FixedPeriodRestartsTest extends TestCase{
	private FixedPeriodRestarts fixedPeriodRestarts;
    
    public FixedPeriodRestartsTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.fixedPeriodRestarts = new FixedPeriodRestarts();
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testReset() throws Exception {
    	fixedPeriodRestarts.reset();
    	
    	Field f = fixedPeriodRestarts.getClass().getDeclaredField("conflictCount");
		f.setAccessible( true );
		long conflictcount = (Long) f.get( fixedPeriodRestarts );
		
		assertEquals(0, conflictcount);
    }
    
    
    public void testNewConflict() throws Exception {
    	fixedPeriodRestarts.newConflict();
    	
    	Field f = fixedPeriodRestarts.getClass().getDeclaredField("conflictCount");
		f.setAccessible( true );
		long conflictcount = (Long) f.get( fixedPeriodRestarts );
		
		assertEquals(1, conflictcount);
    }
    
    public void testInit() throws Exception {
    	SearchParams params = new SearchParams();
    	fixedPeriodRestarts.init(params);
    	
    	Field f = fixedPeriodRestarts.getClass().getDeclaredField("conflictCount");
		f.setAccessible( true );
		long conflictcount = (Long) f.get( fixedPeriodRestarts );
		
		assertEquals(0, conflictcount);
    }
    
    public void testNextRestartNumberOfConflicts() throws Exception {
    	assertEquals(0, fixedPeriodRestarts.nextRestartNumberOfConflict());
    }
    
    public void testOnRestart() throws Exception {
    	fixedPeriodRestarts.onRestart(); //does nothing
    }
    
    public void testOnBackjumpToRootLevel() throws Exception {
    	fixedPeriodRestarts.onBackjumpToRootLevel(); //does nothing
    }
    
    public void testGetSearchParams() throws Exception {
    	assertNull(fixedPeriodRestarts.getSearchParams());
    }
    
    public void testNewLearnedClause() throws Exception {
    	fixedPeriodRestarts.newLearnedClause(null, 0); //does nothing
    }
    
    public void testSetPeriod() throws Exception {
    	fixedPeriodRestarts.setPeriod(5);
    	
    	assertEquals(5, fixedPeriodRestarts.getPeriod());
    }
    
    public void testToString() throws Exception {
    	assertEquals("constant restarts strategy every 0 conflicts", fixedPeriodRestarts.toString());
    }
    
    public void testShouldRestartExample1() throws Exception {
    	assertTrue(fixedPeriodRestarts.shouldRestart());
    }
    
    public void testShouldRestartExample2() throws Exception {
    	fixedPeriodRestarts.setPeriod(5);
    	assertFalse(fixedPeriodRestarts.shouldRestart());
    }
}
