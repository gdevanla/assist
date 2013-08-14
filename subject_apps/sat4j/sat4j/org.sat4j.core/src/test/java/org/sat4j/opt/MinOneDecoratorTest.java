package org.sat4j.opt;

import java.lang.reflect.Field;

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

import junit.framework.TestCase;

public class MinOneDecoratorTest extends TestCase{
	private MinOneDecorator minOneDecorator;
	private LimitedLearning<DataStructureFactory> learning = new PercentLengthLearning<DataStructureFactory>(
        10);
	private Solver<DataStructureFactory> solver = new Solver<DataStructureFactory>(
        learning, new MixedDataStructureDanielWL(), new SearchParams(
                1000), new VarOrderHeap(), new MiniSATRestarts());
    
    public MinOneDecoratorTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        this.minOneDecorator = new MinOneDecorator(solver);
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testAddClauseExample1() throws Exception {
    	IVecInt ps = new VecInt();
    	for(int i=0;i<100;i++)
    		ps.push(10);
    	IConstr expected = minOneDecorator.addClause(ps);
    	
    	assertEquals(1, expected.size());
    	
    }
    
    public void testReset() throws Exception {
    	minOneDecorator.reset();
    	
    	Field f = minOneDecorator.getClass().getDeclaredField("previousConstr");
		f.setAccessible( true );
		IConstr prevConstr = (IConstr) f.get( minOneDecorator );
		
    	assertNull(prevConstr);
    }
    
    public void testHasNoObjectiveFunction() throws Exception {
    	assertFalse(minOneDecorator.hasNoObjectiveFunction());
    }
    
    public void testNonOptimalMeansSatisfiable() throws Exception {
    	assertTrue(minOneDecorator.nonOptimalMeansSatisfiable());
    }
    
    public void testSetTimeoutForFindingBetterSolution() throws Exception {
    	try {
    		minOneDecorator.setTimeoutForFindingBetterSolution(30);
    	} catch(Exception e) {
    		assertEquals("No implemented yet", e.getMessage());
    	}
    }
    
    public void testAdmitABetterSolutionExample1() throws Exception {
    	IVecInt ps = new VecInt();
    	for(int i=0;i<100;i++)
    		ps.push(10);
    	
    	assertTrue(minOneDecorator.admitABetterSolution(ps));
    }
    
    public void testAdmitABetterSolutionExample2() throws Exception {
    	assertTrue(minOneDecorator.admitABetterSolution());
    }
    
    public void testIsSatisfiable() throws Exception {
    	IVecInt ps = new VecInt();
    	for(int i=0;i<100;i++)
    		ps.push(10);
    	
    	assertTrue(minOneDecorator.isSatisfiable());
    	assertTrue(minOneDecorator.isSatisfiable(true));
    	assertTrue(minOneDecorator.isSatisfiable(ps, false));
    }
    
    public void testDiscardExample1() throws Exception {
    	IVecInt ps = new VecInt();
    	for(int i=0;i<100;i++)
    		ps.push(i+1);
    
    	minOneDecorator.admitABetterSolution(ps);
    	minOneDecorator.calculateObjective();
    	minOneDecorator.addClause(ps);
		
    	minOneDecorator.discard();
    	
    	Field f = minOneDecorator.getClass().getDeclaredField("previousConstr");
		f.setAccessible( true );
		IConstr prevConstr = (IConstr) f.get( minOneDecorator );
		
		assertNotNull(prevConstr);
		
    }
    
    public void testDiscardExample2() throws Exception {
    	IVecInt ps = new VecInt();
    	for(int i=0;i<100;i++)
    		ps.push(i+1);
    	
    	minOneDecorator.admitABetterSolution(ps);
    	minOneDecorator.calculateObjective();
    	minOneDecorator.addClause(ps);
    	
    	minOneDecorator.discard();
    	minOneDecorator.discard();
    	
    	Field f = minOneDecorator.getClass().getDeclaredField("previousConstr");
		f.setAccessible( true );
		IConstr prevConstr = (IConstr) f.get( minOneDecorator );
		
		assertNotNull(prevConstr);
		
    }
    
    public void testDiscardExample3() throws Exception {
    	try {
    		minOneDecorator.discard();
    	} catch(Exception e) {
    		
    	}
    }
    
    public void testGetObjectiveValue() throws Exception {
    	assertEquals(0, minOneDecorator.getObjectiveValue());
    }
    
    public void testForceObjectiveValueToExample1() throws Exception {
    	minOneDecorator.forceObjectiveValueTo(23);
    	assertEquals(0, minOneDecorator.getObjectiveValue());
    }
    
    public void testForceObjectiveValueToExample2() throws Exception {
    	minOneDecorator.reset();
    	
    	try {
    		minOneDecorator.forceObjectiveValueTo(-1);
    	} catch (Exception e) {
    		
    	}
    	
    }
    
    public void testCalculateObjective() throws Exception {
    	IVecInt ps = new VecInt();
    	for(int i=0;i<100;i++)
    		ps.push(10);
    	
    	minOneDecorator.admitABetterSolution(ps);
    	assertEquals(1, minOneDecorator.calculateObjective());
    }
    
    public void testModelExample1() throws Exception {
    	IVecInt ps = new VecInt();
    	for(int i=0;i<100;i++)
    		ps.push(10);
    	
    	minOneDecorator.admitABetterSolution(ps);
    	
    	int[] prevmodel = minOneDecorator.model();
    	
    	assertEquals(1, prevmodel.length);
    }
    
    public void testModelExample2() throws Exception {
    	IVecInt ps = new VecInt();
    	for(int i=0;i<100;i++)
    		ps.push(10);
    	
    	minOneDecorator.admitABetterSolution(ps);
    	
    	assertFalse(minOneDecorator.model(1));
    }
    
    public void testIsOptimal() throws Exception {
    	assertFalse(minOneDecorator.isOptimal());
    }
}
