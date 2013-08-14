package org.sat4j.tools.encoding;

import junit.framework.TestCase;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.IConstr;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.IVecInt;

public class BinomialTest extends TestCase{
	private Binomial binomial;
	
	 public BinomialTest(String arg0) {
	        super(arg0);
	    }

	    /*
	     * @see TestCase#setUp()
	     */
	    @Override
	    protected void setUp() throws Exception {
	        this.binomial = new Binomial();
	    }

	    /*
	     * @see TestCase#tearDown()
	     */
	    @Override
	    protected void tearDown() throws Exception {
	        super.tearDown();
	    }
	    
	    public void testAddAtMostOneExample1() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<100;i++)
	    		literals.push(i+1);
	    	
	    	IConstr expected = binomial.addAtMostOne(solver, literals);
	    	assertEquals(4950, expected.size());
	    }
	   
	    public void testAddAtMostOneExample2() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	literals.push(1);
	    	literals.push(2);
	    	
	    	IConstr expected = binomial.addAtMostOne(solver, literals);
	    	assertEquals(1, expected.size());
	    }
	    
	    public void testAddAtMostExample1() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<10;i++)
	    		literals.push(i+1);
	    	
	    	IConstr expected = binomial.addAtMost(solver, literals, 2);
	    	assertEquals(120, expected.size());
	    }
	    
	    public void testAddAtMostExample2() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<50;i++)
	    		literals.push(i+1);
	    	
	    	IConstr expected = binomial.addAtMost(solver, literals, 1);
	    	assertEquals(1225, expected.size());
	    }
	    
}
