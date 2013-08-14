package org.sat4j.minisat.restarts;

import java.lang.reflect.Field;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.constraints.MixedDataStructureDanielWL;
import org.sat4j.minisat.constraints.card.AtLeast;
import org.sat4j.minisat.constraints.cnf.Lits;
import org.sat4j.minisat.core.CircularBuffer;
import org.sat4j.minisat.core.Constr;
import org.sat4j.minisat.core.DataStructureFactory;
import org.sat4j.minisat.core.ILits;
import org.sat4j.minisat.core.SearchParams;
import org.sat4j.minisat.core.Solver;
import org.sat4j.minisat.core.SolverStats;
import org.sat4j.minisat.learning.LimitedLearning;
import org.sat4j.minisat.learning.PercentLengthLearning;
import org.sat4j.minisat.orders.VarOrderHeap;
import org.sat4j.specs.IVecInt;

import junit.framework.TestCase;

public class Glucose21RestartsTest extends TestCase{
	private Glucose21Restarts glucose21Restarts;
    private SolverStats stats = new SolverStats();
    
    public Glucose21RestartsTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.glucose21Restarts = new Glucose21Restarts(stats);
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testNewLearnedClause() throws Exception {
    	IVecInt ps = new VecInt();
        ILits voc = new Lits();
    	ps.push(new Integer(0));
    	ps.push(new Integer(1));
    	AtLeast atLeast = new AtLeast(voc, ps, 0);   	
    	Constr expected = atLeast.toConstraint();
    	
    	glucose21Restarts.newLearnedClause(expected, 0); 
    	assertEquals(0, glucose21Restarts.nextRestartNumberOfConflict());
    }
    
    public void testInit() throws Exception {
    	SearchParams params = new SearchParams();
    	glucose21Restarts.init(params);
    	
    	Field f = glucose21Restarts.getClass().getDeclaredField("params");
		f.setAccessible( true );
		SearchParams expected = (SearchParams) f.get( glucose21Restarts );
		
		assertEquals(expected, params);
    }
    
    public void testNextRestartNumberOfConflict() throws Exception {
    	assertEquals(0, glucose21Restarts.nextRestartNumberOfConflict());
    }
    
    public void testShouldRestartExample1() throws Exception {
    	assertFalse(glucose21Restarts.shouldRestart());
    }
    
    public void testShouldRestartExample2() throws Exception {
    	IVecInt ps = new VecInt();
        ILits voc = new Lits();
    	ps.push(new Integer(0));
    	ps.push(new Integer(1));
    	AtLeast atLeast = new AtLeast(voc, ps, 0);   	
    	Constr expected = atLeast.toConstraint();
    	stats.conflicts = 5;
    	for(int i=0;i<50;i++)
    		glucose21Restarts.newLearnedClause(expected, 2);
    	assertFalse(glucose21Restarts.shouldRestart());
    }

    public void testOnRestart() throws Exception {
    	glucose21Restarts.onRestart();
    	
    	Field f = glucose21Restarts.getClass().getDeclaredField("bufferLBD");
		f.setAccessible( true );
		CircularBuffer expected = (CircularBuffer) f.get( glucose21Restarts );
		
		assertFalse(expected.isFull());
    }
    
    public void testParams() throws Exception {
    	Field f = glucose21Restarts.getClass().getDeclaredField("params");
		f.setAccessible( true );
		SearchParams expected = (SearchParams) f.get( glucose21Restarts );
		
		assertEquals(expected, glucose21Restarts.getSearchParams());
    }
    
    public void testToString() throws Exception {
    	assertEquals("Glucose 2.1 dynamic restart strategy",glucose21Restarts.toString());
    }
    
    public void testReset() throws Exception {
    	glucose21Restarts.reset(); //does nothing
    }
    
    public void testNewConflict() throws Exception {
    	glucose21Restarts.newConflict(); //does nothing
    }
    
    public void testOnBackjumpToRootLevel() throws Exception {
    	glucose21Restarts.onBackjumpToRootLevel(); //does nothing
    }
}
