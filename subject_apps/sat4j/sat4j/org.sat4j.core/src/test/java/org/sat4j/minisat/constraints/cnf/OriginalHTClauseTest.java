package org.sat4j.minisat.constraints.cnf;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.minisat.core.ILits;
import org.sat4j.minisat.core.UnitPropagationListener;
import org.sat4j.specs.IVecInt;

import junit.framework.TestCase;

public class OriginalHTClauseTest extends TestCase{
	private OriginalHTClause originalHTClause;
    private IVecInt ps = new VecInt();
    private ILits voc = new Lits();
    private UnitPropagationListener s = (UnitPropagationListener) SolverFactory.newDefault();
    
    public OriginalHTClauseTest(String arg0) {
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
        this.originalHTClause = new OriginalHTClause(ps, voc);
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testLearnt() throws Exception {
    	originalHTClause.setLearnt(); // does nothing
    	assertFalse(originalHTClause.learnt());
    }
    
    public void testIncActivity() throws Exception {
    	double claInc = 5.0;
    	originalHTClause.incActivity(claInc);
    	originalHTClause.setActivity(claInc);// does nothing
    	originalHTClause.forwardActivity(claInc); // does nothing
    	
    	assertEquals(claInc, originalHTClause.getActivity());
    }
    
    public void testCalcReasonExample1() throws Exception {
    	IVecInt outReason = new VecInt();
    	for(int i=0;i<100;i++)
    		outReason.push(i);
    
    	originalHTClause.calcReason(2, outReason);
    	assertEquals(100, outReason.size());
    }
    
    public void testCalcReasonExample2() throws Exception {
    	IVecInt outReason = new VecInt();
    	for(int i=0;i<100;i++) {
    		outReason.push(i);
    		voc.forgets(i);
    	}
    	originalHTClause.calcReason(2, outReason);
    	assertEquals(200, outReason.size());
    }
    
    public void testSimplifyExample1() throws Exception {
    	assertFalse(originalHTClause.simplify());
    }
 
    public void testSimplifyExample2() throws Exception {
    	for(int i=0;i<100;i++)
    		voc.forgets(i);
    	assertTrue(originalHTClause.simplify());
    }
    
    public void testGet() throws Exception {
    	assertEquals(0, originalHTClause.get(0));
    	assertEquals(1, originalHTClause.get(1));
    	assertEquals(99, originalHTClause.get(99));
    }
    
    public void testSize() throws Exception {
    	assertEquals(100, originalHTClause.size());
    }
    
    public void testRescaleBy() throws Exception {
    	originalHTClause.rescaleBy(5.4);
    	assertEquals(0.0, originalHTClause.getActivity());
    }
    
    public void testVoc() throws Exception {
    	assertEquals(voc, originalHTClause.getVocabulary());
    }
    
    public void testLits() throws Exception {
    	assertEquals(100, originalHTClause.getLits().length);
    }
    
    public void testEqualsExample1() throws Exception {
    	assertFalse(originalHTClause.equals(null));
    }
    
    public void testEqualsExample2() throws Exception {
    	assertFalse(originalHTClause.equals(voc));
    }
    
    public void testEqualsExample3() throws Exception {
    	assertTrue(originalHTClause.equals(originalHTClause));
    }
    
    public void testEqualsExample4() throws Exception {
    	IVecInt argu1 = new VecInt();
        ILits argu2 = new Lits();
        
        argu1.push(10);
        argu1.push(20);
        
        OriginalHTClause clause = new OriginalHTClause(argu1, argu2);

    	assertFalse(originalHTClause.equals(clause));
    }

    
    public void testHashCode() throws Exception {
    	assertEquals(50, originalHTClause.hashCode());
    }
    
    public void testRegisterExample1() throws Exception {
    	originalHTClause.register();
    	
    	assertEquals(200, voc.nVars());
    }
    
    public void testRegisterExample2() throws Exception {
    	for(int i=0;i<100;i++)
    		voc.setLevel(5, 7);
    	
    	originalHTClause.register();
    	
    	assertEquals(200, voc.nVars());
    }
    
    public void testCanBePropogatedMultipleTimes() throws Exception {
    	assertFalse(originalHTClause.canBePropagatedMultipleTimes());
    }
    
    public void testToConstraint() throws Exception {
    	assertEquals(originalHTClause, originalHTClause.toConstraint());
    }
    
    public void testCalcReasonOnTheFly() throws Exception {
    	IVecInt outReason = new VecInt();
    	for(int i=0;i<100;i++)
    		outReason.push(i);
    	
    	originalHTClause.calcReasonOnTheFly(2, outReason, outReason);
    	
    	assertEquals(100, outReason.size());
    }
    
    public void testAssertConstraintExample1() throws Exception {
    	originalHTClause.assertConstraint(s);
    }
    
    public void testAssertConstraintExample2() throws Exception {
    	for(int i=0;i<100;i++)
    		voc.forgets(i);
    	originalHTClause.assertConstraint(s);
    }
    
    public void testToString() throws Exception {
    	assertTrue(originalHTClause.toString().contains("-2"));
    }
    
    public void testLockedExample1() throws Exception {
    	assertFalse(originalHTClause.locked());
    }
    
    public void testLockedExample2() throws Exception {    	
    	voc.setReason(originalHTClause.head, originalHTClause);
    	assertTrue(originalHTClause.locked());
    }
    
    public void testLockedExample3() throws Exception {    	
    	voc.setReason(originalHTClause.tail, originalHTClause);
    	assertTrue(originalHTClause.locked());
    }
    
    public void testLockedExample4() throws Exception {    	
    	voc.setReason(originalHTClause.head, originalHTClause);
    	voc.setReason(originalHTClause.tail, originalHTClause);
    	assertTrue(originalHTClause.locked());
    }
    
    public void testPropogateExample1() throws Exception {    
    	assertTrue(originalHTClause.propagate(s, 5));
    }
       
    public void testPropogateExample2() throws Exception {
    	assertTrue(originalHTClause.propagate(s, 4));
    }
    
    public void testPropogateExample3() throws Exception {
    	assertTrue(originalHTClause.propagate(s, 1));
    }
    
    public void testBrandNewClause() throws Exception {
    	IVecInt literals = new VecInt();
        
        for(int i=0;i<100;i++) 
        	literals.push(i);
        
    	OriginalHTClause clause = OriginalHTClause.brandNewClause(s, voc, literals);
    	
    	assertFalse(clause.learnt());
    }
}
