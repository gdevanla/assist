package org.sat4j.minisat.constraints.cnf;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.minisat.core.ILits;
import org.sat4j.minisat.core.UnitPropagationListener;
import org.sat4j.specs.IVecInt;

import junit.framework.TestCase;

public class LearntWLClauseTest extends TestCase{
	private LearntWLClause learntWLClause;
    private IVecInt ps = new VecInt();
    private ILits voc = new Lits();
    private UnitPropagationListener s = (UnitPropagationListener) SolverFactory.newDefault();
    
    public LearntWLClauseTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        for(int i=0;i<100;i++)
    		ps.push(i);
        voc.ensurePool(200);
        this.learntWLClause = new LearntWLClause(ps, voc);
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testLearnt() throws Exception {
    	learntWLClause.setLearnt(); // does nothing
    	assertTrue(learntWLClause.learnt());
    }
    
    public void testIncActivity() throws Exception {
    	double claInc = 5.0;
    	learntWLClause.incActivity(claInc);
    	
    	learntWLClause.forwardActivity(claInc); // does nothing
    	
    	assertEquals(claInc, learntWLClause.getActivity());
    }
    
    public void testSetActivity() throws Exception {
    	double d = 2.3;
    	learntWLClause.setActivity(d);
    	
    	assertEquals(d, learntWLClause.getActivity());
    }
    
    public void testCalcReasonExample1() throws Exception {
    	IVecInt outReason = new VecInt();
    	for(int i=0;i<100;i++)
    		outReason.push(i);
    
    	learntWLClause.calcReason(2, outReason);
    	assertEquals(199, outReason.size());
    }
    
    public void testCalcReasonExample2() throws Exception {
    	IVecInt outReason = new VecInt();
    	for(int i=0;i<100;i++) {
    		outReason.push(i);
    		voc.forgets(i);
    	}
    	learntWLClause.calcReason(2, outReason);
    	assertEquals(199, outReason.size());
    }
    
    public void testSimplifyExample1() throws Exception {
    	assertFalse(learntWLClause.simplify());
    }
 
    public void testSimplifyExample2() throws Exception {
    	for(int i=0;i<100;i++)
    		voc.forgets(i);
    	assertTrue(learntWLClause.simplify());
    }
    
    public void testGet() throws Exception {
    	assertEquals(0, learntWLClause.get(0));
    	assertEquals(1, learntWLClause.get(1));
    	assertEquals(99, learntWLClause.get(99));
    }
    
    public void testSize() throws Exception {
    	assertEquals(100, learntWLClause.size());
    }
    
    public void testRescaleBy() throws Exception {
    	learntWLClause.rescaleBy(5.4);
    	assertEquals(0.0, learntWLClause.getActivity());
    }
    
    public void testVoc() throws Exception {
    	assertEquals(voc, learntWLClause.getVocabulary());
    }
    
    public void testLits() throws Exception {
    	assertEquals(100, learntWLClause.getLits().length);
    }
    
    public void testEqualsExample1() throws Exception {
    	assertFalse(learntWLClause.equals(null));
    }
    
    public void testEqualsExample2() throws Exception {
    	assertFalse(learntWLClause.equals(voc));
    }
    
    public void testEqualsExample3() throws Exception {
    	assertTrue(learntWLClause.equals(learntWLClause));
    }
    
    public void testEqualsExample4() throws Exception {
    	IVecInt argu1 = new VecInt();
        ILits argu2 = new Lits();
        
        argu1.push(10);
        argu1.push(20);
        
        LearntWLClause clause = new LearntWLClause(argu1, argu2);

    	assertFalse(learntWLClause.equals(clause));
    }

    
    public void testHashCode() throws Exception {
    	assertEquals(49, learntWLClause.hashCode());
    }
    
    public void testRegisterExample1() throws Exception {
    	learntWLClause.register();
    	
    	assertEquals(200, voc.nVars());
    }
    
    public void testRegisterExample2() throws Exception {
    	for(int i=0;i<100;i++)
    		voc.setLevel(5, 7);
    	
    	learntWLClause.register();
    	
    	assertEquals(200, voc.nVars());
    }
    
    public void testCanBePropogatedMultipleTimes() throws Exception {
    	assertFalse(learntWLClause.canBePropagatedMultipleTimes());
    }
    
    public void testToConstraint() throws Exception {
    	assertEquals(learntWLClause, learntWLClause.toConstraint());
    }
    
    public void testCalcReasonOnTheFly() throws Exception {
    	IVecInt outReason = new VecInt();
    	for(int i=0;i<100;i++)
    		outReason.push(i);
    	
    	learntWLClause.calcReasonOnTheFly(2, outReason, outReason);
    	
    	assertEquals(199, outReason.size());
    }
    
    public void testAssertConstraintExample1() throws Exception {
    	learntWLClause.assertConstraint(s);
    }
    
    public void testAssertConstraintExample2() throws Exception {
    	for(int i=0;i<100;i++)
    		voc.forgets(i);
    	learntWLClause.assertConstraint(s);
    }
    
    public void testToString() throws Exception {
    	assertTrue(learntWLClause.toString().contains("-2"));
    }
    
    public void testLockedExample1() throws Exception {
    	assertFalse(learntWLClause.locked());
    }
    
    public void testLockedExample2() throws Exception {
    	voc.setReason(ps.get(0), learntWLClause);
    	assertTrue(learntWLClause.locked());
    }
    
    public void testPropogateExample1() throws Exception {    
    	assertTrue(learntWLClause.propagate(s, 5));
    }
       
    public void testPropogateExample2() throws Exception {
    	assertTrue(learntWLClause.propagate(s, 4));
    }
    
    public void testPropogateExample3() throws Exception {
    	for(int i=0;i<100;i++)
    		voc.forgets(i);
    	assertTrue(learntWLClause.propagate(s, 1));
    }
}
