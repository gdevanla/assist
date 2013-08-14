package org.sat4j.minisat.constraints.cnf;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.minisat.constraints.card.MinWatchCard;
import org.sat4j.minisat.core.ILits;
import org.sat4j.minisat.core.UnitPropagationListener;
import org.sat4j.specs.IVecInt;

import junit.framework.TestCase;

public class LearntBinaryClauseTest extends TestCase{
	private LearntBinaryClause learntBinaryClause;
    private IVecInt ps = new VecInt();
    private ILits voc = new Lits();
    private UnitPropagationListener s = (UnitPropagationListener) SolverFactory.newDefault();
    
    public LearntBinaryClauseTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        for(int i=0;i<100;i++)
    		ps.push(5);
        voc.ensurePool(200);
        this.learntBinaryClause = new LearntBinaryClause(ps, voc);
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testLearnt() throws Exception {
    	learntBinaryClause.setLearnt(); // does nothing
    	assertTrue(learntBinaryClause.learnt());
    }
    
    public void testIncActivity() throws Exception {
    	double claInc = 5.0;
    	learntBinaryClause.incActivity(claInc);
    	
    	learntBinaryClause.forwardActivity(claInc); // does nothing
    	
    	assertEquals(claInc, learntBinaryClause.getActivity());
    }
    
    public void testSetActivity() throws Exception {
    	double d = 2.3;
    	learntBinaryClause.setActivity(d);
    	
    	assertEquals(d, learntBinaryClause.getActivity());
    }
    
    public void testCalcReasonExample1() throws Exception {
    	IVecInt outReason = new VecInt();
    	for(int i=0;i<100;i++)
    		outReason.push(i);
    
    	learntBinaryClause.calcReason(2, outReason);
    	assertEquals(100, outReason.size());
    }
    
    public void testCalcReasonExample2() throws Exception {
    	IVecInt outReason = new VecInt();
    	for(int i=0;i<100;i++) {
    		outReason.push(i);
    		voc.forgets(i);
    	}
    	learntBinaryClause.calcReason(2, outReason);
    	assertEquals(102, outReason.size());
    }
    
    public void testSimplifyExample1() throws Exception {
    	assertFalse(learntBinaryClause.simplify());
    }
 
    public void testSimplifyExample2() throws Exception {
    	for(int i=0;i<100;i++)
    		voc.forgets(i);
    	assertTrue(learntBinaryClause.simplify());
    }
    
    public void testGet() throws Exception {
    	assertEquals(5, learntBinaryClause.get(0));
    	assertEquals(5, learntBinaryClause.get(1));
    	assertEquals(5, learntBinaryClause.get(95));
    }
    
    public void testSize() throws Exception {
    	assertEquals(2, learntBinaryClause.size());
    }
    
    public void testRescaleBy() throws Exception {
    	learntBinaryClause.rescaleBy(5.4);
    	assertEquals(0.0, learntBinaryClause.getActivity());
    }
    
    public void testVoc() throws Exception {
    	assertEquals(voc, learntBinaryClause.getVocabulary());
    }
    
    public void testLits() throws Exception {
    	assertEquals(2, learntBinaryClause.getLits().length);
    }
    
    public void testEqualsExample1() throws Exception {
    	assertFalse(learntBinaryClause.equals(null));
    }
    
    public void testEqualsExample2() throws Exception {
    	assertFalse(learntBinaryClause.equals(voc));
    }
    
    public void testEqualsExample3() throws Exception {
    	assertTrue(learntBinaryClause.equals(learntBinaryClause));
    }
    
    public void testEqualsExample4() throws Exception {
    	IVecInt argu1 = new VecInt();
        ILits argu2 = new Lits();
        
        for(int i=0;i<100;i++)
        	argu1.push(9);
        argu2.ensurePool(200);
        LearntBinaryClause clause = new LearntBinaryClause(argu1, argu2);
        
    	assertFalse(learntBinaryClause.equals(clause));
    }
    
    public void testEqualsExample5() throws Exception {
    	IVecInt argu1 = new VecInt();
        ILits argu2 = new Lits();
        argu1.push(5);
        argu1.push(9);
        argu2.ensurePool(200);
        LearntBinaryClause clause = new LearntBinaryClause(argu1, argu2);
        
    	assertFalse(learntBinaryClause.equals(clause));
    }
    
    public void testHashCode() throws Exception {
    	assertEquals(5, learntBinaryClause.hashCode());
    }
    
    public void testRegister() throws Exception {
    	learntBinaryClause.register();
    	
    	assertEquals(200, voc.nVars());
    }
    
    public void testCanBePropogatedMultipleTimes() throws Exception {
    	assertFalse(learntBinaryClause.canBePropagatedMultipleTimes());
    }
    
    public void testToConstraint() throws Exception {
    	assertEquals(learntBinaryClause, learntBinaryClause.toConstraint());
    }
    
    public void testCalcReasonOnTheFly() throws Exception {
    	IVecInt outReason = new VecInt();
    	for(int i=0;i<100;i++)
    		outReason.push(i);
    	
    	learntBinaryClause.calcReasonOnTheFly(2, outReason, outReason);
    	
    	assertEquals(100, outReason.size());
    }
    
    public void testAssertConstraintExample1() throws Exception {
    	learntBinaryClause.assertConstraint(s);
    }
    
    public void testAssertConstraintExample2() throws Exception {
    	for(int i=0;i<100;i++)
    		voc.forgets(i);
    	learntBinaryClause.assertConstraint(s);
    }
    
    public void testToString() throws Exception {
    	assertTrue(learntBinaryClause.toString().contains("-2"));
    }
    
    public void testLockedExample1() throws Exception {
    	assertFalse(learntBinaryClause.locked());
    }
    
    public void testLockedExample2() throws Exception {    	
    	voc.setReason(learntBinaryClause.head, learntBinaryClause);
    	assertTrue(learntBinaryClause.locked());
    }
    
    public void testLockedExample3() throws Exception {    	
    	voc.setReason(learntBinaryClause.tail, learntBinaryClause);
    	assertTrue(learntBinaryClause.locked());
    }
    
    public void testLockedExample4() throws Exception {    	
    	voc.setReason(learntBinaryClause.head, learntBinaryClause);
    	voc.setReason(learntBinaryClause.tail, learntBinaryClause);
    	assertTrue(learntBinaryClause.locked());
    }
    
    public void testPropogateExample1() throws Exception {    
    	assertTrue(learntBinaryClause.propagate(s, 5));
    }
       
    public void testPropogateExample2() throws Exception {
    	assertTrue(learntBinaryClause.propagate(s, 4));
    }
}

