package org.sat4j.tools.encoding;

import junit.framework.TestCase;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.IConstr;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.IVecInt;

public class BinaryTest extends TestCase{
	private Binary binary;
	
	 public BinaryTest(String arg0) {
	        super(arg0);
	    }

	    /*
	     * @see TestCase#setUp()
	     */
	    @Override
	    protected void setUp() throws Exception {
	        this.binary = new Binary();
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
	    	
	    	IConstr expected = binary.addAtMostOne(solver, literals);
	    	assertEquals(672, expected.size());
	    }
	    
	    public void testAddAtMostOneExample2() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<50;i++)
	    		literals.push(3);
	    	
	    	IConstr expected = binary.addAtMostOne(solver, literals);
	    	assertEquals(286, expected.size());
	    }
	    
	    public void testAddAtMostOneExample3() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	literals.push(-1);
	    	literals.push(1);
	    	literals.push(2);
	    	
	    	IConstr expected = binary.addAtMostOne(solver, literals);
	    	assertEquals(5, expected.size());
	    }
	    
	    public void testAddAtMostExample1() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<100;i++)
	    		literals.push(i+1);
	    	
	    	IConstr expected = binary.addAtMost(solver, literals, 50);
	    	assertEquals(17950, expected.size());
	    }
	    
	    public void testAddAtMostExample2() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<10;i++)
	    		literals.push(-3);
	    	
	    	IConstr expected = binary.addAtMost(solver, literals, 50);
	    	assertEquals(10, expected.size());
	    }
	    
	    public void testAddAtMostExample3() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<10;i++)
	    		literals.push(-3);
	    	
	    	IConstr expected = binary.addAtMost(solver, literals, 5);
	    	assertEquals(130, expected.size());
	    }
	    
	    public void testAddAtMostExample4() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	literals.push(-1);
	    	literals.push(1);
	    	literals.push(2);
	    	
	    	IConstr expected = binary.addAtMost(solver, literals, 2);
	    	assertEquals(11, expected.size());
	    }
}
