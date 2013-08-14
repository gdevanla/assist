package org.sat4j.tools;

import static org.mockito.Mockito.mock;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.opt.MinOneDecorator;
import org.sat4j.specs.IOptimizationProblem;
import org.sat4j.specs.IVecInt;

import junit.framework.TestCase;

public class OptToSatAdapterTest extends TestCase{
	private OptToSatAdapter adapter;
	private IOptimizationProblem problem;
    
    public OptToSatAdapterTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.problem = new MinOneDecorator(SolverFactory.newDefault());
        this.adapter = new OptToSatAdapter(problem);
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testIsSatisfiableExample1() throws Exception {
    	assertTrue(adapter.isSatisfiable());
    }
    
    public void testIsSatisfiableExample2() throws Exception {
    	assertTrue(adapter.isSatisfiable(false));
    }
    
    public void testIsSatisfiableExample3() throws Exception {
    	IVecInt assumps = new VecInt();
    	try {
    		adapter.isSatisfiable(assumps,false);
    	} catch (Exception e) {
    		
    	}
    }
    
    public void testIsSatisfiableExample4() throws Exception {
    	IVecInt assumps = new VecInt();
    	try {
    		adapter.isSatisfiable(assumps);
    	} catch (Exception e) {
    		
    	}
    }
    
    public void testReset() throws Exception {
    	adapter.reset();
    	assertFalse(adapter.optimalValueForced);
    }
    
    public void testModelExample1() throws Exception {
    	int[] expected  = adapter.model();
    	assertNull(expected);
    }
    
    public void testModelExample2() throws Exception {
    	adapter.modelComputed = true;
    	int[] expected  = adapter.model();
    	assertNull(expected);
    }
    
    public void testModelExample3() throws Exception {
    	adapter.optimalValueForced = false;
    	int[] expected  = adapter.model();
    	assertNull(expected);
    }
    
    public void testModelExample4() throws Exception {
    	adapter.optimalValueForced = true;
    	int[] expected  = adapter.model();
    	assertNull(expected);
    }

    public void testToString() throws Exception {
    	assertTrue(adapter.toString("prefix").contains("Optimization to SAT adapter\n"));
    }
    
    public void testIsOptimal() throws Exception {
    	assertFalse(adapter.isOptimal());
    }
    
}
