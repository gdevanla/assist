package org.sat4j.tools;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.IVecInt;

import junit.framework.TestCase;

public class SingleSolutionDetectorTest extends TestCase{
	private SingleSolutionDetector detector;
	private ISolver solver = SolverFactory.newDefault();
	
 	public SingleSolutionDetectorTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        solver.findModel();
        this.detector = new SingleSolutionDetector(solver);
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testHasASingleSolutionExample1() throws Exception {   	
    	assertTrue(detector.hasASingleSolution());
    }
    
    public void testHasASingleSolutionExample2() throws Exception {  
    	IVecInt assumps = new VecInt();
    	for(int i=0;i<10;i++)
    		assumps.push(i);
    	assertTrue(detector.hasASingleSolution(assumps));
    }
    
    public void testHasASingleSolutionExample3() throws Exception { 
    	IVecInt assumps = new VecInt();
    	for(int i=0;i<10;i++)
    		assumps.push(i);
    	assertTrue(detector.hasASingleSolution(assumps));
    }
}
