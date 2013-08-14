package org.sat4j.tools;

import static org.mockito.Mockito.mock;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.IVecInt;

import junit.framework.TestCase;

public class LexicoDecoratorTest extends TestCase{
	private LexicoDecorator decorator;
	
	private ISolver solver;
    
    public LexicoDecoratorTest(String arg0) {
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
        this.decorator = new LexicoDecorator(solver);
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testAddCriterion() throws Exception {
    	IVecInt assumps = new VecInt();
    	for(int i=0;i<10;i++)
    		assumps.push(i);
    	
    	decorator.addCriterion(assumps);
    	
    	assertEquals(1, decorator.criteria.size());
    }
    
    public void testnumberOfCriteria() throws Exception {
    	assertEquals(0, decorator.numberOfCriteria());
    }
    
    public void testHasNoObjectiveFunction() throws Exception {
    	assertFalse(decorator.hasNoObjectiveFunction());
    }
    
    public void testNonOptimalMeansSatisfiable() throws Exception {
    	assertTrue(decorator.nonOptimalMeansSatisfiable());
    }
    
    public void testGetObjectiveValue() throws Exception {
    	assertEquals(-1, decorator.getObjectiveValue());
    }
    
    public void testForceObjectiveValue() throws Exception {
    	try {
    		decorator.forceObjectiveValueTo(10);
    	} catch (Exception e) {
    		
    	}
    }
}
