package org.sat4j.minisat.restarts;

import java.lang.reflect.Field;

import org.sat4j.minisat.core.SearchParams;

import junit.framework.TestCase;

public class LubyRestartsTest extends TestCase{
	private LubyRestarts lubyRestarts;
    
    public LubyRestartsTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.lubyRestarts = new LubyRestarts();
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testReset() throws Exception {
    	lubyRestarts.reset();
    	
    	Field f = lubyRestarts.getClass().getDeclaredField("conflictcount");
		f.setAccessible( true );
		int conflictcount = (Integer) f.get( lubyRestarts );
		
		assertEquals(0, conflictcount);
    }
    
    
    public void testNewConflict() throws Exception {
    	lubyRestarts.newConflict();
    	
    	Field f = lubyRestarts.getClass().getDeclaredField("conflictcount");
		f.setAccessible( true );
		int conflictcount = (Integer) f.get( lubyRestarts );
		
		assertEquals(1, conflictcount);
    }
    
    public void testInit() throws Exception {
    	SearchParams params = new SearchParams();
    	lubyRestarts.init(params);
    	
    	Field f = lubyRestarts.getClass().getDeclaredField("conflictcount");
		f.setAccessible( true );
		int conflictcount = (Integer) f.get( lubyRestarts );
		
		assertEquals(0, conflictcount);
    }
    
    public void testNextRestartNumberOfConflicts() throws Exception {
    	assertEquals(0, lubyRestarts.nextRestartNumberOfConflict());
    }
    
    public void testOnRestart() throws Exception {
    	lubyRestarts.onRestart(); 
    	
    	Field f = lubyRestarts.getClass().getDeclaredField("conflictcount");
		f.setAccessible( true );
		int conflictcount = (Integer) f.get( lubyRestarts );
		
		assertEquals(0, conflictcount);
    }
    
    public void testOnBackjumpToRootLevel() throws Exception {
    	lubyRestarts.onBackjumpToRootLevel();
    	
    	Field f = lubyRestarts.getClass().getDeclaredField("conflictcount");
		f.setAccessible( true );
		int conflictcount = (Integer) f.get( lubyRestarts );
		
		assertEquals(0, conflictcount);
    }
    
    public void testGetSearchParams() throws Exception {
    	assertNotNull(lubyRestarts.getSearchParams());
    }
    
    public void testNewLearnedClause() throws Exception {
    	lubyRestarts.newLearnedClause(null, 0); //does nothing
    }
    
    public void testToString() throws Exception {
    	assertEquals("luby style (SATZ_rand, TiniSAT) restarts strategy with factor 32", lubyRestarts.toString());
    }
    
    public void testShouldRestartExample1() throws Exception {
    	assertTrue(lubyRestarts.shouldRestart());
    }
    
    public void testShouldRestartExample2() throws Exception {
    	SearchParams params = new SearchParams();
    	lubyRestarts.init(params);
    	
    	assertFalse(lubyRestarts.shouldRestart());
    }
    
    public void testFactor() throws Exception {
    	lubyRestarts.setFactor(2);
    	
    	assertEquals(2, lubyRestarts.getFactor());
    }
    
    public void testLuby() throws Exception {
    	assertEquals(1, lubyRestarts.luby());
    }

    public void testNextLuby() throws Exception {
    	assertEquals(1, lubyRestarts.nextLuby());
    	assertEquals(2, lubyRestarts.nextLuby());
    }
    
    public void testLubyRestarts() throws Exception {
    	LubyRestarts luby = new LubyRestarts(3);
    	
    	assertEquals(3, luby.getFactor());
    }
    
}
