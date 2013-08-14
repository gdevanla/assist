package org.sat4j.tools;

import junit.framework.TestCase;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.IVecInt;
import org.sat4j.specs.SearchListener;

public class Minimal4CardinalityModelTest extends TestCase{
	private Minimal4CardinalityModel model;
	private ISolver solver;
    
    public Minimal4CardinalityModelTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.solver = SolverFactory.newDefault();
        this.solver.findModel();
        this.model = new Minimal4CardinalityModel(solver);
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testModelExample1() throws Exception {
    	int[] expected = model.model();
    	assertEquals(0, expected.length);
    }
    
    public void testModelExample2() throws Exception {
    	IVecInt literals = new VecInt();
    	for(int i=0;i<10;i++)
    		literals.push(i+1);
    	model.addClause(literals);
    	int[] expected = model.model();
    	assertEquals(0, expected.length);
    }
    
    public void testModelExample3() throws Exception {
    	IVecInt literals = new VecInt();
    	for(int i=0;i<10;i++)
    		literals.push(-(i+1));
    	model.addClause(literals);
    	int[] expected = model.model();
    	assertEquals(0, expected.length);
    }
}
