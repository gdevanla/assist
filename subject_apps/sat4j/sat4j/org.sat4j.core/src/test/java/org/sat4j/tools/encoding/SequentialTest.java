package org.sat4j.tools.encoding;

import junit.framework.TestCase;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.IConstr;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.IVecInt;

public class SequentialTest extends TestCase{
	private Sequential sequential;
	
	 public SequentialTest(String arg0) {
	        super(arg0);
	    }

	    /*
	     * @see TestCase#setUp()
	     */
	    @Override
	    protected void setUp() throws Exception {
	        this.sequential = new Sequential();
	    }

	    /*
	     * @see TestCase#tearDown()
	     */
	    @Override
	    protected void tearDown() throws Exception {
	        super.tearDown();
	    }
	    
	    public void testAddAtMostExample1() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<100;i++)
	    		literals.push(i+1);
	    	
	    	IConstr expected = sequential.addAtMost(solver, literals, 50);
	    	assertEquals(9949, expected.size());
	    }
	    
	    public void testAddAtMostExample2() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<10;i++)
	    		literals.push(-3);
	    	
	    	IConstr expected = sequential.addAtMost(solver, literals, 50);
	    	assertEquals(859, expected.size());
	    }
	    
	    public void testAddAtMostExample3() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<10;i++)
	    		literals.push(-3);
	    	
	    	IConstr expected = sequential.addAtMost(solver, literals, 5);
	    	assertEquals(94, expected.size());
	    }
	    
	    public void testAddAtMostExample4() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	literals.push(-1);
	    	literals.push(1);
	    	literals.push(2);
	    	
	    	IConstr expected = sequential.addAtMost(solver, literals, 2);
	    	assertEquals(8, expected.size());
	    }
}
