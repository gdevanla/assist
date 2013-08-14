package org.sat4j.opt;

import java.lang.reflect.Field;

import junit.framework.TestCase;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.constraints.MixedDataStructureDanielWL;
import org.sat4j.minisat.core.DataStructureFactory;
import org.sat4j.minisat.core.SearchParams;
import org.sat4j.minisat.core.Solver;
import org.sat4j.minisat.learning.LimitedLearning;
import org.sat4j.minisat.learning.PercentLengthLearning;
import org.sat4j.minisat.orders.VarOrderHeap;
import org.sat4j.minisat.restarts.MiniSATRestarts;
import org.sat4j.specs.IConstr;
import org.sat4j.specs.IVecInt;

public class MaxSatDecoratorTest extends TestCase{
	private MaxSatDecorator maxSatDecorator;
	private LimitedLearning<DataStructureFactory> learning = new PercentLengthLearning<DataStructureFactory>(
        10);
	private Solver<DataStructureFactory> solver = new Solver<DataStructureFactory>(
        learning, new MixedDataStructureDanielWL(), new SearchParams(
                1000), new VarOrderHeap(), new MiniSATRestarts());
    
    public MaxSatDecoratorTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        this.maxSatDecorator = new MaxSatDecorator(solver);
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testSetExpectedNumberOfClauses() throws Exception {
    	maxSatDecorator.setExpectedNumberOfClauses(50);
    	
    	assertEquals(50, maxSatDecorator.getExpectedNumberOfClauses());
    }
    
    public void testAddClauseExample1() throws Exception {
    	IVecInt ps = new VecInt();
    	for(int i=0;i<100;i++)
    		ps.push(10);
    	IConstr expected = maxSatDecorator.addClause(ps);
    	
    	assertEquals(2, expected.size());
    	
    }
    
    public void testAddClauseExample2() throws Exception {
    	MaxSatDecorator decorator = new MaxSatDecorator(solver, true);
    	IVecInt ps = new VecInt();
    	for(int i=0;i<100;i++)
    		ps.push(10);
    	IConstr expected = decorator.addClause(ps);
    	
    	assertEquals(101, expected.size());  	
    }
    
    public void testReset() throws Exception {
    	maxSatDecorator.reset();
    	
    	Field f = maxSatDecorator.getClass().getDeclaredField("prevConstr");
		f.setAccessible( true );
		IConstr prevConstr = (IConstr) f.get( maxSatDecorator );
		
    	assertNull(prevConstr);
    }
    
    public void testHasNoObjectiveFunction() throws Exception {
    	assertFalse(maxSatDecorator.hasNoObjectiveFunction());
    }
    
    public void testNonOptimalMeansSatisfiable() throws Exception {
    	assertFalse(maxSatDecorator.nonOptimalMeansSatisfiable());
    }
    
    public void testSetTimeoutForFindingBetterSolution() throws Exception {
    	try {
    		maxSatDecorator.setTimeoutForFindingBetterSolution(30);
    	} catch(Exception e) {
    		assertEquals("No implemented yet", e.getMessage());
    	}
    }
    
    public void testAdmitABetterSolutionExample1() throws Exception {
    	IVecInt ps = new VecInt();
    	for(int i=0;i<100;i++)
    		ps.push(10);
    	
    	assertTrue(maxSatDecorator.admitABetterSolution(ps));
    }
    
    public void testAdmitABetterSolutionExample2() throws Exception {
    	assertTrue(maxSatDecorator.admitABetterSolution());
    }
    
    public void testIsSatisfiable() throws Exception {
    	IVecInt ps = new VecInt();
    	for(int i=0;i<100;i++)
    		ps.push(10);
    	
    	assertTrue(maxSatDecorator.isSatisfiable());
    	assertTrue(maxSatDecorator.isSatisfiable(true));
    	assertTrue(maxSatDecorator.isSatisfiable(ps, false));
    }
    
    public void testDiscardExample1() throws Exception {
    	IVecInt ps = new VecInt();
    	for(int i=0;i<100;i++)
    		ps.push(10);
    	
    	maxSatDecorator.prevfullmodel = ps.toArray();
    	maxSatDecorator.calculateObjective();
    	maxSatDecorator.addClause(ps);
		
    	maxSatDecorator.discard();
    	
    	Field f = maxSatDecorator.getClass().getDeclaredField("prevConstr");
		f.setAccessible( true );
		IConstr prevConstr = (IConstr) f.get( maxSatDecorator );
		
		assertNotNull(prevConstr);
		
    }
    
    public void testDiscardExample2() throws Exception {
    	IVecInt ps = new VecInt();
    	for(int i=0;i<100;i++)
    		ps.push(10);
    	
    	maxSatDecorator.prevfullmodel = ps.toArray();
    	maxSatDecorator.calculateObjective();
    	maxSatDecorator.addClause(ps);
		
    	maxSatDecorator.discard();
    	maxSatDecorator.discard();
    	
    	Field f = maxSatDecorator.getClass().getDeclaredField("prevConstr");
		f.setAccessible( true );
		IConstr prevConstr = (IConstr) f.get( maxSatDecorator );
		
		assertNotNull(prevConstr);
		
    }
    
    public void testDiscardExample3() throws Exception {
    	try {
    		maxSatDecorator.discard();
    	} catch(Exception e) {
    		
    	}
    }
    
    public void testGetObjectiveValue() throws Exception {
    	assertEquals(0, maxSatDecorator.getObjectiveValue());
    }
    
    public void testForceObjectiveValueTo() throws Exception {
    	maxSatDecorator.forceObjectiveValueTo(23);
    	assertEquals(0, maxSatDecorator.getObjectiveValue());
    }
    
    public void testCalculateObjective() throws Exception {
    	IVecInt ps = new VecInt();
    	for(int i=0;i<100;i++)
    		ps.push(10);
    	
    	maxSatDecorator.prevfullmodel = ps.toArray();
    	assertEquals(100, maxSatDecorator.calculateObjective());
    }
    
    public void testModelExample1() throws Exception {
    	IVecInt ps = new VecInt();
    	for(int i=0;i<100;i++)
    		ps.push(10);
    	
    	maxSatDecorator.admitABetterSolution(ps);
    	
    	int[] prevmodel = maxSatDecorator.model();
    	
    	assertEquals(1, prevmodel.length);
    }
    
    public void testModelExample2() throws Exception {
    	IVecInt ps = new VecInt();
    	for(int i=0;i<100;i++)
    		ps.push(10);
    	
    	maxSatDecorator.admitABetterSolution(ps);
    	
    	assertFalse(maxSatDecorator.model(1));
    }
    
    public void testIsOptimal() throws Exception {
    	assertFalse(maxSatDecorator.isOptimal());
    }
 
}

