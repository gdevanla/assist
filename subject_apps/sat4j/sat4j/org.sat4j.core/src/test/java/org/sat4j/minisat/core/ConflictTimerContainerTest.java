package org.sat4j.minisat.core;

import junit.framework.TestCase;

public class ConflictTimerContainerTest extends TestCase{
	private ConflictTimerContainer conflictTimer;
	
	public ConflictTimerContainerTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        this.conflictTimer = new ConflictTimerContainer();
    }
    
    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testRemove() throws Exception {
    	ConflictTimerContainer arg1 = new ConflictTimerContainer();
    	ConflictTimerContainer arg2 = new ConflictTimerContainer();
    	
    	this.conflictTimer.add(arg1);
    	this.conflictTimer.add(arg2);
    	
    	this.conflictTimer = this.conflictTimer.remove(arg1);
    }
    
    public void testReset() throws Exception {
    	ConflictTimerContainer arg1 = new ConflictTimerContainer();
    	ConflictTimerContainer arg2 = new ConflictTimerContainer();
    	
    	this.conflictTimer.add(arg1);
    	this.conflictTimer.add(arg2);
    	
    	this.conflictTimer.reset();
    }
}
