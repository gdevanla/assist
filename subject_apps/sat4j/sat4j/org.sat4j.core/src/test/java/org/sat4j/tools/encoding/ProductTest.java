package org.sat4j.tools.encoding;

import junit.framework.TestCase;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.IConstr;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.IVecInt;

public class ProductTest extends TestCase{
	private Product product;
	
	 public ProductTest(String arg0) {
	        super(arg0);
	    }

	    /*
	     * @see TestCase#setUp()
	     */
	    @Override
	    protected void setUp() throws Exception {
	        this.product = new Product();
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
	    	
	    	IConstr expected = product.addAtMostOne(solver, literals);
	    	assertEquals(202, expected.size());
	    }
	    
	    public void testAddAtMostOneExample2() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<50;i++)
	    		literals.push(3);
	    	
	    	IConstr expected = product.addAtMostOne(solver, literals);
	    	assertEquals(102, expected.size());
	    }
	    public void testAddAtMostExample1() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<100;i++)
	    		literals.push(i+1);
	    	
	    	IConstr expected = product.addAtMost(solver, literals, 50);
	    	assertEquals(100, expected.size());
	    }
	    
	    public void testAddAtMostExample2() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<10;i++)
	    		literals.push(-3);
	    	
	    	IConstr expected = product.addAtMost(solver, literals, 50);
	    	assertEquals(1, expected.size());
	    }

	    public void testAddAtMostExample3() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	literals.push(-1);
	    	literals.push(1);
	    	literals.push(2);
	    	
	    	IConstr expected = product.addAtMost(solver, literals, 2);
	    	assertEquals(3, expected.size());
	    }
	    
	    public void testAddAtMostNonOptExample1() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<10;i++)
	    		literals.push(-3);
	    	
	    	IConstr expected = product.addAtMostNonOpt(solver, literals, 50);
	    	assertEquals(0, expected.size());
	    }

	    public void testAddAtMostNonOptExample2() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	literals.push(1);
	    	literals.push(2);
	    	
	    	IConstr expected = product.addAtMostNonOpt(solver, literals, 2);
	    	assertEquals(0, expected.size());
	    }
	    
	    public void testAddAtMostNonOptExample3() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	literals.push(1);
	    	literals.push(2);
	    	
	    	IConstr expected = product.addAtMostNonOpt(solver, literals, 1);
	    	assertEquals(1, expected.size());
	    }
	    
	    public void testAddAtMostNonOptExample4() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<50;i++)
	    		literals.push(i+1);
	    	
	    	try {
	    		product.addAtMostNonOpt(solver, literals, 7);
	    	} catch(Exception e) {
	    		
	    	}
	    }
	    
	    public void testAddAtLeast() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<100;i++)
	    		literals.push(i+1);
	   
	    	IConstr expected = product.addAtLeast(solver, literals, 1);
	    	assertEquals(100, expected.size());
	    }

	    public void testAddExactly() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<100;i++)
	    		literals.push(i+1);
	   
	    	IConstr expected = product.addExactly(solver, literals, 3);
	    	assertEquals(2, expected.size());
	    }
	    

	    public void testAddExactlyOne() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<100;i++)
	    		literals.push(i+1);
	   
	    	IConstr expected = product.addExactlyOne(solver, literals);
	    	assertEquals(2, expected.size());
	    }
}
