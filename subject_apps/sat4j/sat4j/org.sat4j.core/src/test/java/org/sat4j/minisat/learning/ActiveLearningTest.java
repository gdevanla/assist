package org.sat4j.minisat.learning;

import java.lang.reflect.Field;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.minisat.constraints.MixedDataStructureDanielWL;
import org.sat4j.minisat.constraints.card.AtLeast;
import org.sat4j.minisat.constraints.cnf.Lits;
import org.sat4j.minisat.core.Constr;
import org.sat4j.minisat.core.DataStructureFactory;
import org.sat4j.minisat.core.ILits;
import org.sat4j.minisat.core.IOrder;
import org.sat4j.minisat.core.SearchParams;
import org.sat4j.minisat.core.Solver;
import org.sat4j.minisat.core.UnitPropagationListener;
import org.sat4j.minisat.orders.VarOrderHeap;
import org.sat4j.minisat.restarts.MiniSATRestarts;
import org.sat4j.specs.IVecInt;

import junit.framework.TestCase;

public class ActiveLearningTest extends TestCase{
	private ActiveLearning activeLearning;
    
    public ActiveLearningTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.activeLearning = new ActiveLearning();
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testActivityPercent() throws Exception {
    	assertEquals(0.95, activeLearning.getActivityPercent());
    }
    
    public void testSetActivityPercent() throws Exception {
    	activeLearning.setActivityPercent(0.99);
    	assertEquals(0.99, activeLearning.getActivityPercent());
    }
    
    
    public void testActivityLearningConstructor() throws Exception {
    	ActiveLearning learning = new ActiveLearning(8.5);
    	assertEquals(8.5, learning.getActivityPercent());   	
    }
    
    public void testOrder() throws Exception {
    	LimitedLearning<DataStructureFactory> learning = new PercentLengthLearning<DataStructureFactory>(
                10);
    	Solver<DataStructureFactory> solver = new Solver<DataStructureFactory>(
                learning, new MixedDataStructureDanielWL(), new SearchParams(
                        1000), new VarOrderHeap(), new MiniSATRestarts());
    	activeLearning.setOrder(solver.getOrder());

		Field f = activeLearning.getClass().getDeclaredField("order");
		f.setAccessible( true );
		IOrder order = (IOrder) f.get( activeLearning );
		
		assertEquals(solver.getOrder(), order);
    }
    
    public void testSolver() throws Exception {
    	LimitedLearning<DataStructureFactory> learning = new PercentLengthLearning<DataStructureFactory>(
                10);
    	Solver<DataStructureFactory> solver = new Solver<DataStructureFactory>(
                learning, new MixedDataStructureDanielWL(), new SearchParams(
                        1000), new VarOrderHeap(), new MiniSATRestarts());
    	activeLearning.setSolver(solver);

		Field f = activeLearning.getClass().getDeclaredField("order");
		f.setAccessible( true );
		IOrder order = (IOrder) f.get( activeLearning );
		
		assertEquals(solver.getOrder(), order);
    }

    public void testLimit() throws Exception {
    	activeLearning.setLimit(34);    	
    	assertEquals(34, activeLearning.getLimit());
    }
    
    public void testToString() throws Exception {
    	assertEquals("Limit learning to clauses containing active literals (95.0%)", activeLearning.toString());
    }
    
    public void testLearningCondition() throws Exception {  
    	LimitedLearning<DataStructureFactory> learning = new PercentLengthLearning<DataStructureFactory>(
                10);
    	Solver<DataStructureFactory> solver = new Solver<DataStructureFactory>(
                learning, new MixedDataStructureDanielWL(), new SearchParams(
                        1000), new VarOrderHeap(), new MiniSATRestarts());
    	activeLearning.setSolver(solver);
    	
    	IVecInt ps = new VecInt();
        ILits voc = new Lits();
    	ps.push(new Integer(0));
    	ps.push(new Integer(1));
    	AtLeast atLeast = new AtLeast(voc, ps, 0);
    	
    	Constr expected = atLeast.toConstraint();
    	assertFalse(activeLearning.learningCondition(expected));

    }
    
    public void testInit() throws Exception {
    	activeLearning.init();
    	assertEquals(0, activeLearning.getLimit());
    }
    
}
