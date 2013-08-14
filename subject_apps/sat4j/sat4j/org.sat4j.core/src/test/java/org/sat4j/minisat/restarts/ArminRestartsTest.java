package org.sat4j.minisat.restarts;

import java.lang.reflect.Field;

import org.sat4j.minisat.core.IOrder;
import org.sat4j.minisat.core.SearchParams;
import org.sat4j.minisat.learning.ActiveLearning;

import junit.framework.TestCase;

public class ArminRestartsTest extends TestCase{
	private ArminRestarts arminRestarts;
    
    public ArminRestartsTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.arminRestarts = new ArminRestarts();
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testInit() throws Exception {
    	SearchParams params = new SearchParams();
    	
    	arminRestarts.init(params);
    	
    	Field f = arminRestarts.getClass().getDeclaredField("inner");
		f.setAccessible( true );
		double inner = (Double) f.get( arminRestarts );
		
		assertEquals(100.0, inner);
    }
    
    public void testNextRestartNumberOfConflict() throws Exception {
    	assertEquals(0, arminRestarts.nextRestartNumberOfConflict());
    }
    
    public void testToString() throws Exception {
    	assertEquals("Armin Biere (Picosat) restarts strategy", arminRestarts.toString());
    }
    
    public void testShouldRestartExample1() throws Exception {
    	assertTrue(arminRestarts.shouldRestart());
    }
    
    public void testShouldRestartExample2() throws Exception {
    	SearchParams params = new SearchParams();
    	params.setInitConflictBound(2);
    	arminRestarts.init(params);
    	
    	assertFalse(arminRestarts.shouldRestart());
    }
    
    public void testSearchParams() throws Exception {
    	assertNull(arminRestarts.getSearchParams());
    }
    
    public void testNewConflict() throws Exception {
    	arminRestarts.newConflict();
    	
    	Field f = arminRestarts.getClass().getDeclaredField("conflictcount");
		f.setAccessible( true );
		long conflictcount = (Long) f.get( arminRestarts );
		
		assertEquals(1,conflictcount);
    }
    
    public void testReset() throws Exception {
    	arminRestarts.reset();
    	
    	Field f = arminRestarts.getClass().getDeclaredField("conflictcount");
		f.setAccessible( true );
		long conflictcount = (Long) f.get( arminRestarts );
		
		assertEquals(0,conflictcount);
    }
    
    public void testOnJumpBackToRootLevel() throws Exception {
    	arminRestarts.onBackjumpToRootLevel();
    	
    	Field f = arminRestarts.getClass().getDeclaredField("conflictcount");
		f.setAccessible( true );
		long conflictcount = (Long) f.get( arminRestarts );
		
		assertEquals(0,conflictcount);
    }
    
    public void testOnRestartExample1() throws Exception {
    	SearchParams params = new SearchParams();
    	
    	arminRestarts.init(params);
    	arminRestarts.onRestart();
    	
    	Field f = arminRestarts.getClass().getDeclaredField("conflictcount");
		f.setAccessible( true );
		long conflictcount = (Long) f.get( arminRestarts );
		
		assertEquals(0,conflictcount);
		
		arminRestarts.onRestart();
    }
    
    public void testOnRestartExample2() throws Exception {
    	SearchParams params = new SearchParams();
    	
    	arminRestarts.init(params);
    	arminRestarts.onRestart();
    	arminRestarts.onRestart();
    	
    	Field f = arminRestarts.getClass().getDeclaredField("conflictcount");
		f.setAccessible( true );
		long conflictcount = (Long) f.get( arminRestarts );
		
		assertEquals(0,conflictcount);
				
    }
    
    public void testNewLearnedClause() throws Exception {
    	arminRestarts.newLearnedClause(null, 0); //does nothing
    }
}
