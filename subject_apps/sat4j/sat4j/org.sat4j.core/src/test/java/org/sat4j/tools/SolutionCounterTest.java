package org.sat4j.tools;

import static org.mockito.Mockito.mock;

import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.ISolver;

import junit.framework.TestCase;

public class SolutionCounterTest extends TestCase{
	private SolutionCounter counter;
	private ISolver solver = SolverFactory.newDefault();
	
 	public SolutionCounterTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.counter = new SolutionCounter(solver);
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testLowerBound() throws Exception {
    	assertEquals(0, counter.lowerBound());
    }
    
    public void testCountSolutions() throws Exception {
    	assertEquals(1, counter.countSolutions());
    }
}
