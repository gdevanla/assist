package org.sat4j.minisat.constraints.cnf;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.minisat.core.ILits;
import org.sat4j.minisat.core.UnitPropagationListener;
import org.sat4j.specs.IVecInt;

import junit.framework.TestCase;

public class OriginalBinaryClauseTest extends TestCase{
	private OriginalBinaryClause originalBinaryClause;
    private IVecInt ps = new VecInt();
    private ILits voc = new Lits();
    private UnitPropagationListener s = (UnitPropagationListener) SolverFactory.newDefault();
    
    public OriginalBinaryClauseTest(String arg0) {
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
        this.originalBinaryClause = new OriginalBinaryClause(ps, voc);
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testLearnt() throws Exception {
    	originalBinaryClause.setLearnt(); // does nothing
    	assertFalse(originalBinaryClause.learnt());
    }
    
    public void testIncActivity() throws Exception {
    	double claInc = 5.0;
    	originalBinaryClause.incActivity(claInc);
    	originalBinaryClause.setActivity(claInc); // does nothing
    	originalBinaryClause.forwardActivity(claInc); // does nothing
    	
    	assertEquals(claInc, originalBinaryClause.getActivity());
    }
    
    public void testCalcReasonExample1() throws Exception {
    	IVecInt outReason = new VecInt();
    	for(int i=0;i<100;i++)
    		outReason.push(i);
    
    	originalBinaryClause.calcReason(2, outReason);
    	assertEquals(100, outReason.size());
    }
    
    public void testCalcReasonExample2() throws Exception {
    	IVecInt outReason = new VecInt();
    	for(int i=0;i<100;i++) {
    		outReason.push(i);
    		voc.forgets(i);
    	}
    	originalBinaryClause.calcReason(2, outReason);
    	assertEquals(102, outReason.size());
    }
    
    public void testSimplifyExample1() throws Exception {
    	assertFalse(originalBinaryClause.simplify());
    }
 
    public void testSimplifyExample2() throws Exception {
    	for(int i=0;i<100;i++)
    		voc.forgets(i);
    	assertTrue(originalBinaryClause.simplify());
    }
    
    public void testGet() throws Exception {
    	assertEquals(5, originalBinaryClause.get(0));
    	assertEquals(5, originalBinaryClause.get(1));
    	assertEquals(5, originalBinaryClause.get(95));
    }
    
    public void testSize() throws Exception {
    	assertEquals(2, originalBinaryClause.size());
    }
    
    public void testRescaleBy() throws Exception {
    	originalBinaryClause.rescaleBy(5.4);
    	assertEquals(0.0, originalBinaryClause.getActivity());
    }
    
    public void testVoc() throws Exception {
    	assertEquals(voc, originalBinaryClause.getVocabulary());
    }
    
    public void testLits() throws Exception {
    	assertEquals(2, originalBinaryClause.getLits().length);
    }
    
    public void testEqualsExample1() throws Exception {
    	assertFalse(originalBinaryClause.equals(null));
    }
    
    public void testEqualsExample2() throws Exception {
    	assertFalse(originalBinaryClause.equals(voc));
    }
    
    public void testEqualsExample3() throws Exception {
    	assertTrue(originalBinaryClause.equals(originalBinaryClause));
    }
    
    public void testEqualsExample4() throws Exception {
    	IVecInt argu1 = new VecInt();
        ILits argu2 = new Lits();
        
        for(int i=0;i<100;i++)
        	argu1.push(9);
        argu2.ensurePool(200);
        OriginalBinaryClause clause = new OriginalBinaryClause(argu1, argu2);
        
    	assertFalse(originalBinaryClause.equals(clause));
    }
    
    public void testEqualsExample5() throws Exception {
    	IVecInt argu1 = new VecInt();
        ILits argu2 = new Lits();
        argu1.push(5);
        argu1.push(9);
        argu2.ensurePool(200);
        OriginalBinaryClause clause = new OriginalBinaryClause(argu1, argu2);
        
    	assertFalse(originalBinaryClause.equals(clause));
    }
    
    public void testHashCode() throws Exception {
    	assertEquals(5, originalBinaryClause.hashCode());
    }
    
    public void testRegister() throws Exception {
    	originalBinaryClause.register();
    	
    	assertEquals(200, voc.nVars());
    }
    
    public void testCanBePropogatedMultipleTimes() throws Exception {
    	assertFalse(originalBinaryClause.canBePropagatedMultipleTimes());
    }
    
    public void testToConstraint() throws Exception {
    	assertEquals(originalBinaryClause, originalBinaryClause.toConstraint());
    }
    
    public void testCalcReasonOnTheFly() throws Exception {
    	IVecInt outReason = new VecInt();
    	for(int i=0;i<100;i++)
    		outReason.push(i);
    	
    	originalBinaryClause.calcReasonOnTheFly(2, outReason, outReason);
    	
    	assertEquals(100, outReason.size());
    }
    
    public void testAssertConstraintExample1() throws Exception {
    	originalBinaryClause.assertConstraint(s);
    }
    
    public void testAssertConstraintExample2() throws Exception {
    	for(int i=0;i<100;i++)
    		voc.forgets(i);
    	originalBinaryClause.assertConstraint(s);
    }
    
    public void testToString() throws Exception {
    	assertTrue(originalBinaryClause.toString().contains("-2"));
    }
    
    public void testLockedExample1() throws Exception {
    	assertFalse(originalBinaryClause.locked());
    }
    
    public void testLockedExample2() throws Exception {    	
    	voc.setReason(originalBinaryClause.head, originalBinaryClause);
    	assertTrue(originalBinaryClause.locked());
    }
    
    public void testLockedExample3() throws Exception {    	
    	voc.setReason(originalBinaryClause.tail, originalBinaryClause);
    	assertTrue(originalBinaryClause.locked());
    }
    
    public void testLockedExample4() throws Exception {    	
    	voc.setReason(originalBinaryClause.head, originalBinaryClause);
    	voc.setReason(originalBinaryClause.tail, originalBinaryClause);
    	assertTrue(originalBinaryClause.locked());
    }
    
    public void testPropogateExample1() throws Exception {    
    	assertTrue(originalBinaryClause.propagate(s, 5));
    }
       
    public void testPropogateExample2() throws Exception {
    	assertTrue(originalBinaryClause.propagate(s, 4));
    }
    
    public void testBrandNewClause() throws Exception {
    	IVecInt literals = new VecInt();
        
        for(int i=0;i<100;i++) 
        	literals.push(i);
        
    	OriginalBinaryClause clause = OriginalBinaryClause.brandNewClause(s, voc, literals);
    	
    	assertFalse(clause.learnt());
    }
}
