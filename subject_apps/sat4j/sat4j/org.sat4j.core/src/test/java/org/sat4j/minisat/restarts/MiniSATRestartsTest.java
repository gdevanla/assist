package org.sat4j.minisat.restarts;

import java.lang.reflect.Field;

import org.sat4j.minisat.core.SearchParams;

import junit.framework.TestCase;

public class MiniSATRestartsTest extends TestCase{
	private MiniSATRestarts miniSATRestarts;
    
    public MiniSATRestartsTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.miniSATRestarts = new MiniSATRestarts();
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testReset() throws Exception {
    	miniSATRestarts.reset();
    	
    	Field f = miniSATRestarts.getClass().getDeclaredField("conflictcount");
		f.setAccessible( true );
		int conflictcount = (Integer) f.get( miniSATRestarts );
		
		assertEquals(0, conflictcount);
    }
    
    
    public void testNewConflict() throws Exception {
    	miniSATRestarts.newConflict();
    	
    	Field f = miniSATRestarts.getClass().getDeclaredField("conflictcount");
		f.setAccessible( true );
		int conflictcount = (Integer) f.get( miniSATRestarts );
		
		assertEquals(1, conflictcount);
    }
    
    public void testInit() throws Exception {
    	SearchParams params = new SearchParams();
    	miniSATRestarts.init(params);
    	
    	Field f = miniSATRestarts.getClass().getDeclaredField("conflictcount");
		f.setAccessible( true );
		int conflictcount = (Integer) f.get( miniSATRestarts );
		
		assertEquals(0, conflictcount);
    }
    
    public void testNextRestartNumberOfConflicts() throws Exception {
    	assertEquals(0, miniSATRestarts.nextRestartNumberOfConflict());
    }
    
    public void testOnRestart() throws Exception {
    	SearchParams params = new SearchParams();
    	
    	miniSATRestarts.init(params);
    	miniSATRestarts.onRestart(); 
    	
    	Field f = miniSATRestarts.getClass().getDeclaredField("nofConflicts");
		f.setAccessible( true );
		double nofConflicts = (Double) f.get( miniSATRestarts );
		
		assertEquals(150.0, nofConflicts);
    }
    
    public void testOnBackjumpToRootLevel() throws Exception {
    	miniSATRestarts.onBackjumpToRootLevel(); 
    	
    	Field f = miniSATRestarts.getClass().getDeclaredField("conflictcount");
		f.setAccessible( true );
		int conflictcount = (Integer) f.get( miniSATRestarts );
		
		assertEquals(0, conflictcount);
    }
    
    public void testGetSearchParams() throws Exception {
    	assertNull(miniSATRestarts.getSearchParams());
    }
    
    public void testNewLearnedClause() throws Exception {
    	miniSATRestarts.newLearnedClause(null, 0); //does nothing
    }
    
    public void testToString() throws Exception {
    	assertEquals("MiniSAT restarts strategy", miniSATRestarts.toString());
    }
    
    public void testShouldRestartExample1() throws Exception {
    	assertTrue(miniSATRestarts.shouldRestart());
    }
    
    public void testShouldRestartExample2() throws Exception {
    	SearchParams params = new SearchParams();
    	
    	miniSATRestarts.init(params);
    	miniSATRestarts.onRestart(); 
    	assertFalse(miniSATRestarts.shouldRestart());
    }

}
