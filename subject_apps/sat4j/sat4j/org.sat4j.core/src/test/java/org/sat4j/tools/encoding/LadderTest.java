package org.sat4j.tools.encoding;

import junit.framework.TestCase;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.IConstr;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.IVecInt;

public class LadderTest extends TestCase{
	private Ladder ladder;
	
	 public LadderTest(String arg0) {
	        super(arg0);
	    }

	    /*
	     * @see TestCase#setUp()
	     */
	    @Override
	    protected void setUp() throws Exception {
	        this.ladder = new Ladder();
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
	    	
	    	IConstr expected = ladder.addAtMostOne(solver, literals);
	    	assertEquals(400, expected.size());
	    }
	    
	    public void testAddAtMostOneExample2() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<50;i++)
	    		literals.push(3);
	    	
	    	IConstr expected = ladder.addAtMostOne(solver, literals);
	    	assertEquals(200, expected.size());
	    }
	    
	    public void testAddAtMostOneExample3() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	literals.push(-1);
	    	literals.push(1);
	    	literals.push(2);
	    	
	    	IConstr expected = ladder.addAtMostOne(solver, literals);
	    	assertEquals(12, expected.size());
	    }
	    
	    public void testAddExactlyOneExample1() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<10;i++)
	    		literals.push(-3);
	    	
	    	IConstr expected = ladder.addExactlyOne(solver, literals);
	    	assertEquals(36, expected.size());
	    }
	    
	    public void testAddExactlyOneExample2() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<10;i++)
	    		literals.push(-3);
	    	
	    	IConstr expected = ladder.addExactlyOne(solver, literals);
	    	assertEquals(36, expected.size());
	    }
	    
	    public void testAddExactlyOneExample3() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	literals.push(-1);
	    	literals.push(1);
	    	literals.push(2);
	    	
	    	IConstr expected = ladder.addExactlyOne(solver, literals);
	    	assertEquals(8, expected.size());
	    }
}
