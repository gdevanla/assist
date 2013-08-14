package org.sat4j.minisat.constraints.cnf;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.minisat.core.ILits;
import org.sat4j.minisat.core.UnitPropagationListener;
import org.sat4j.specs.IVecInt;

import junit.framework.TestCase;

public class LearntHTClauseTest extends TestCase{
	private LearntHTClause learntHTClause;
    private IVecInt ps = new VecInt();
    private ILits voc = new Lits();
    private UnitPropagationListener s = (UnitPropagationListener) SolverFactory.newDefault();
    
    public LearntHTClauseTest(String arg0) {
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
        this.learntHTClause = new LearntHTClause(ps, voc);
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testLearnt() throws Exception {
    	learntHTClause.setLearnt(); // does nothing
    	assertTrue(learntHTClause.learnt());
    }
    
    public void testIncActivity() throws Exception {
    	double claInc = 5.0;
    	learntHTClause.incActivity(claInc);
    	
    	learntHTClause.forwardActivity(claInc); // does nothing
    	
    	assertEquals(claInc, learntHTClause.getActivity());
    }
    
    public void testSetActivity() throws Exception {
    	double d = 2.3;
    	learntHTClause.setActivity(d);
    	
    	assertEquals(d, learntHTClause.getActivity());
    }
    
    public void testCalcReasonExample1() throws Exception {
    	IVecInt outReason = new VecInt();
    	for(int i=0;i<100;i++)
    		outReason.push(i);
    
    	learntHTClause.calcReason(2, outReason);
    	assertEquals(100, outReason.size());
    }
    
    public void testCalcReasonExample2() throws Exception {
    	IVecInt outReason = new VecInt();
    	for(int i=0;i<100;i++) {
    		outReason.push(i);
    		voc.forgets(i);
    	}
    	learntHTClause.calcReason(2, outReason);
    	assertEquals(200, outReason.size());
    }
    
    public void testSimplifyExample1() throws Exception {
    	assertFalse(learntHTClause.simplify());
    }
 
    public void testSimplifyExample2() throws Exception {
    	for(int i=0;i<100;i++)
    		voc.forgets(i);
    	assertTrue(learntHTClause.simplify());
    }
    
    public void testGet() throws Exception {
    	assertEquals(0, learntHTClause.get(0));
    	assertEquals(1, learntHTClause.get(1));
    	assertEquals(99, learntHTClause.get(99));
    }
    
    public void testSize() throws Exception {
    	assertEquals(100, learntHTClause.size());
    }
    
    public void testRescaleBy() throws Exception {
    	learntHTClause.rescaleBy(5.4);
    	assertEquals(0.0, learntHTClause.getActivity());
    }
    
    public void testVoc() throws Exception {
    	assertEquals(voc, learntHTClause.getVocabulary());
    }
    
    public void testLits() throws Exception {
    	assertEquals(100, learntHTClause.getLits().length);
    }
    
    public void testEqualsExample1() throws Exception {
    	assertFalse(learntHTClause.equals(null));
    }
    
    public void testEqualsExample2() throws Exception {
    	assertFalse(learntHTClause.equals(voc));
    }
    
    public void testEqualsExample3() throws Exception {
    	assertTrue(learntHTClause.equals(learntHTClause));
    }
    
    public void testEqualsExample4() throws Exception {
    	IVecInt argu1 = new VecInt();
        ILits argu2 = new Lits();
        
        argu1.push(10);
        argu1.push(20);
        
        LearntHTClause clause = new LearntHTClause(argu1, argu2);

    	assertFalse(learntHTClause.equals(clause));
    }

    
    public void testHashCode() throws Exception {
    	assertEquals(50, learntHTClause.hashCode());
    }
    
    public void testRegisterExample1() throws Exception {
    	learntHTClause.register();
    	
    	assertEquals(200, voc.nVars());
    }
    
    public void testRegisterExample2() throws Exception {
    	for(int i=0;i<100;i++)
    		voc.setLevel(5, 7);
    	
    	learntHTClause.register();
    	
    	assertEquals(200, voc.nVars());
    }
    
    public void testCanBePropogatedMultipleTimes() throws Exception {
    	assertFalse(learntHTClause.canBePropagatedMultipleTimes());
    }
    
    public void testToConstraint() throws Exception {
    	assertEquals(learntHTClause, learntHTClause.toConstraint());
    }
    
    public void testCalcReasonOnTheFly() throws Exception {
    	IVecInt outReason = new VecInt();
    	for(int i=0;i<100;i++)
    		outReason.push(i);
    	
    	learntHTClause.calcReasonOnTheFly(2, outReason, outReason);
    	
    	assertEquals(100, outReason.size());
    }
    
    public void testAssertConstraintExample1() throws Exception {
    	learntHTClause.assertConstraint(s);
    }
    
    public void testAssertConstraintExample2() throws Exception {
    	for(int i=0;i<100;i++)
    		voc.forgets(i);
    	learntHTClause.assertConstraint(s);
    }
    
    public void testToString() throws Exception {
    	assertTrue(learntHTClause.toString().contains("-2"));
    }
    
    public void testLockedExample1() throws Exception {
    	assertFalse(learntHTClause.locked());
    }
    
    public void testLockedExample2() throws Exception {    	
    	voc.setReason(learntHTClause.head, learntHTClause);
    	assertTrue(learntHTClause.locked());
    }
    
    public void testLockedExample3() throws Exception {    	
    	voc.setReason(learntHTClause.tail, learntHTClause);
    	assertTrue(learntHTClause.locked());
    }
    
    public void testLockedExample4() throws Exception {    	
    	voc.setReason(learntHTClause.head, learntHTClause);
    	voc.setReason(learntHTClause.tail, learntHTClause);
    	assertTrue(learntHTClause.locked());
    }
    
    public void testPropogateExample1() throws Exception {    
    	assertTrue(learntHTClause.propagate(s, 5));
    }
       
    public void testPropogateExample2() throws Exception {
    	assertTrue(learntHTClause.propagate(s, 4));
    }
    
    public void testPropogateExample3() throws Exception {
    	assertTrue(learntHTClause.propagate(s, 1));
    }
}
