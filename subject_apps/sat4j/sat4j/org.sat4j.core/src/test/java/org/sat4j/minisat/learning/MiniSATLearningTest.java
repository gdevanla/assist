package org.sat4j.minisat.learning;

import java.lang.reflect.Field;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.constraints.MixedDataStructureDanielWL;
import org.sat4j.minisat.constraints.card.AtLeast;
import org.sat4j.minisat.constraints.card.MaxWatchCard;
import org.sat4j.minisat.constraints.cnf.Lits;
import org.sat4j.minisat.core.Constr;
import org.sat4j.minisat.core.DataStructureFactory;
import org.sat4j.minisat.core.ILits;
import org.sat4j.minisat.core.IOrder;
import org.sat4j.minisat.core.SearchParams;
import org.sat4j.minisat.core.Solver;
import org.sat4j.minisat.orders.VarOrderHeap;
import org.sat4j.minisat.restarts.MiniSATRestarts;
import org.sat4j.specs.IVecInt;

import junit.framework.TestCase;

public class MiniSATLearningTest extends TestCase{
	private MiniSATLearning miniSATLearning;

    public MiniSATLearningTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();        
        this.miniSATLearning = new MiniSATLearning();
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testDSF() throws Exception {
    	DataStructureFactory dsf = new MixedDataStructureDanielWL();
    	miniSATLearning.setDataStructureFactory(dsf);
    	
    	Field f = miniSATLearning.getClass().getDeclaredField("dsf");
		f.setAccessible( true );
		DataStructureFactory expected = (DataStructureFactory) f.get( miniSATLearning );
		
		assertEquals(dsf, expected);
    }
    
    public void testSolver() throws Exception {
    	LimitedLearning<DataStructureFactory> learning = new PercentLengthLearning<DataStructureFactory>(
                10);
    	Solver<DataStructureFactory> solver = new Solver<DataStructureFactory>(
                learning, new MixedDataStructureDanielWL(), new SearchParams(
                        1000), new VarOrderHeap(), new MiniSATRestarts());
    	
    	miniSATLearning.setSolver(solver);
    	
    	Field f = miniSATLearning.getClass().getDeclaredField("dsf");
		f.setAccessible( true );
		DataStructureFactory expected = (DataStructureFactory) f.get( miniSATLearning );
		
		assertEquals(expected, solver.getDSFactory());
    }
    
    public void testToString() throws Exception {
    	assertEquals("Learn all clauses as in MiniSAT", miniSATLearning.toString());
    }

}
