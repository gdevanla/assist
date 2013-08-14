package org.sat4j.minisat.restarts;

import org.sat4j.minisat.core.SearchParams;

import junit.framework.TestCase;

public class NoRestartsTest extends TestCase{
	private NoRestarts noRestarts;
    
    public NoRestartsTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.noRestarts = new NoRestarts();
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testNextRestartNumberOfConflict() throws Exception {
    	assertEquals(Long.MAX_VALUE, noRestarts.nextRestartNumberOfConflict());
    }
    
    public void testInit() throws Exception {
    	SearchParams params = new SearchParams();
    	noRestarts.init(params); //does nothing
    }
    
    public void testShouldRestart() throws Exception {
    	assertFalse(noRestarts.shouldRestart());
    }
    
    public void testToString() throws Exception {
    	assertEquals("NoRestarts",noRestarts.toString());
    }
    
    public void testGetSearchParam() throws Exception {
    	assertEquals(SearchParams.class,noRestarts.getSearchParams().getClass());
    }
    
    public void testOnRestart() throws Exception {
    	noRestarts.onRestart(); //does nothing
    }
    
    public void testReset() throws Exception {
    	noRestarts.reset(); //does nothing
    }
    
    public void testNewConflict() throws Exception {
    	noRestarts.newConflict(); //does nothing
    }
    
    public void testOnBackjumpToRootLevel() throws Exception {
    	noRestarts.onBackjumpToRootLevel(); //does nothing
    }
    
    public void testNewLearnedClause() throws Exception {
    	noRestarts.newLearnedClause(null, 0); //does nothing
    }
    
}
