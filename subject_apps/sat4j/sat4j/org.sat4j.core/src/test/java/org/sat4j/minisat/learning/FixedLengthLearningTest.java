package org.sat4j.minisat.learning;

import java.lang.reflect.Field;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.constraints.MixedDataStructureDanielWL;
import org.sat4j.minisat.constraints.card.AtLeast;
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

public class FixedLengthLearningTest extends TestCase{
	private FixedLengthLearning fixedLengthLearning;
    
    public FixedLengthLearningTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.fixedLengthLearning = new FixedLengthLearning();
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testActivityPercent() throws Exception {
    	assertEquals(3, fixedLengthLearning.getMaxLength());
    }
    
    public void testSetActivityPercent() throws Exception {
    	fixedLengthLearning.setMaxLength(6);
    	assertEquals(6, fixedLengthLearning.getMaxLength());
    }
    
    
    public void testActivityLearningConstructor() throws Exception {
    	FixedLengthLearning learning = new FixedLengthLearning(9);
    	assertEquals(9, learning.getMaxLength());   	
    }
    
    public void testBound() throws Exception {
    	fixedLengthLearning.setBound(5);
    	
    	Field f = fixedLengthLearning.getClass().getDeclaredField("bound");
		f.setAccessible( true );
		int bound = (Integer) f.get( fixedLengthLearning );
		
		assertEquals(5, bound);
    }

    public void testInit() throws Exception {
    	fixedLengthLearning.init();
    	
    	Field f = fixedLengthLearning.getClass().getDeclaredField("bound");
		f.setAccessible( true );
		int bound = (Integer) f.get( fixedLengthLearning );
		
		assertEquals(3, bound);
    }

    
    public void testToString() throws Exception {
    	assertEquals("Limit learning to clauses of size smaller or equal to 3", fixedLengthLearning.toString());
    }
    
    public void testLearningConditionExample1() throws Exception {  
    	LimitedLearning<DataStructureFactory> learning = new PercentLengthLearning<DataStructureFactory>(
                10);
    	Solver<DataStructureFactory> solver = new Solver<DataStructureFactory>(
                learning, new MixedDataStructureDanielWL(), new SearchParams(
                        1000), new VarOrderHeap(), new MiniSATRestarts());
    	fixedLengthLearning.setSolver(solver);
    	
    	IVecInt ps = new VecInt();
        ILits voc = new Lits();
    	ps.push(new Integer(0));
    	ps.push(new Integer(1));
    	AtLeast atLeast = new AtLeast(voc, ps, 0);
    	
    	Constr expected = atLeast.toConstraint();
    	assertFalse(fixedLengthLearning.learningCondition(expected));

    }
    
    public void testLearningCondition() throws Exception {  
    	fixedLengthLearning.setBound(8);
    	LimitedLearning<DataStructureFactory> learning = new PercentLengthLearning<DataStructureFactory>(
                10);
    	Solver<DataStructureFactory> solver = new Solver<DataStructureFactory>(
                learning, new MixedDataStructureDanielWL(), new SearchParams(
                        1000), new VarOrderHeap(), new MiniSATRestarts());
    	fixedLengthLearning.setSolver(solver);
    	
    	IVecInt ps = new VecInt();
        ILits voc = new Lits();
    	ps.push(new Integer(0));
    	ps.push(new Integer(1));
    	AtLeast atLeast = new AtLeast(voc, ps, 0);
    	
    	Constr expected = atLeast.toConstraint();
    	assertTrue(fixedLengthLearning.learningCondition(expected));

    }

}
