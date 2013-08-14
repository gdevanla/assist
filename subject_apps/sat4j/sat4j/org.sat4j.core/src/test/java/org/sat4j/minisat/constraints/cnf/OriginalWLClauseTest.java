package org.sat4j.minisat.constraints.cnf;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.minisat.core.ILits;
import org.sat4j.minisat.core.UnitPropagationListener;
import org.sat4j.specs.IVecInt;

import junit.framework.TestCase;

public class OriginalWLClauseTest extends TestCase{
	private OriginalWLClause originalWLClause;
    private IVecInt ps = new VecInt();
    private ILits voc = new Lits();
    private UnitPropagationListener s = (UnitPropagationListener) SolverFactory.newDefault();
    
    public OriginalWLClauseTest(String arg0) {
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
        this.originalWLClause = new OriginalWLClause(ps, voc);
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testLearnt() throws Exception {
    	originalWLClause.setLearnt(); // does nothing
    	assertFalse(originalWLClause.learnt());
    }
    
    public void testIncActivity() throws Exception {
    	double claInc = 5.0;
    	originalWLClause.incActivity(claInc);
    	
    	originalWLClause.forwardActivity(claInc); // does nothing
    	
    	assertEquals(claInc, originalWLClause.getActivity());
    }
    
    public void testSetActivity() throws Exception {
    	double d = 2.3;
    	originalWLClause.setActivity(d);
    	
    	assertEquals(d, originalWLClause.getActivity());
    }
    
    public void testCalcReasonExample1() throws Exception {
    	IVecInt outReason = new VecInt();
    	for(int i=0;i<100;i++)
    		outReason.push(i);
    
    	originalWLClause.calcReason(2, outReason);
    	assertEquals(199, outReason.size());
    }
    
    public void testCalcReasonExample2() throws Exception {
    	IVecInt outReason = new VecInt();
    	for(int i=0;i<100;i++) {
    		outReason.push(i);
    		voc.forgets(i);
    	}
    	originalWLClause.calcReason(2, outReason);
    	assertEquals(199, outReason.size());
    }
    
    public void testSimplifyExample1() throws Exception {
    	assertFalse(originalWLClause.simplify());
    }
 
    public void testSimplifyExample2() throws Exception {
    	for(int i=0;i<100;i++)
    		voc.forgets(i);
    	assertTrue(originalWLClause.simplify());
    }
    
    public void testGet() throws Exception {
    	assertEquals(0, originalWLClause.get(0));
    	assertEquals(1, originalWLClause.get(1));
    	assertEquals(99, originalWLClause.get(99));
    }
    
    public void testSize() throws Exception {
    	assertEquals(100, originalWLClause.size());
    }
    
    public void testRescaleBy() throws Exception {
    	originalWLClause.rescaleBy(5.4);
    	assertEquals(0.0, originalWLClause.getActivity());
    }
    
    public void testVoc() throws Exception {
    	assertEquals(voc, originalWLClause.getVocabulary());
    }
    
    public void testLits() throws Exception {
    	assertEquals(100, originalWLClause.getLits().length);
    }
    
    public void testEqualsExample1() throws Exception {
    	assertFalse(originalWLClause.equals(null));
    }
    
    public void testEqualsExample2() throws Exception {
    	assertFalse(originalWLClause.equals(voc));
    }
    
    public void testEqualsExample3() throws Exception {
    	assertTrue(originalWLClause.equals(originalWLClause));
    }
    
    public void testEqualsExample4() throws Exception {
    	IVecInt argu1 = new VecInt();
        ILits argu2 = new Lits();
        
        argu1.push(10);
        argu1.push(20);
        
        OriginalWLClause clause = new OriginalWLClause(argu1, argu2);

    	assertFalse(originalWLClause.equals(clause));
    }

    
    public void testHashCode() throws Exception {
    	assertEquals(49, originalWLClause.hashCode());
    }
    
    public void testRegisterExample1() throws Exception {
    	originalWLClause.register();
    	
    	assertEquals(200, voc.nVars());
    }
    
    public void testRegisterExample2() throws Exception {
    	for(int i=0;i<100;i++)
    		voc.setLevel(5, 7);
    	
    	originalWLClause.register();
    	
    	assertEquals(200, voc.nVars());
    }
    
    public void testCanBePropogatedMultipleTimes() throws Exception {
    	assertFalse(originalWLClause.canBePropagatedMultipleTimes());
    }
    
    public void testToConstraint() throws Exception {
    	assertEquals(originalWLClause, originalWLClause.toConstraint());
    }
    
    public void testCalcReasonOnTheFly() throws Exception {
    	IVecInt outReason = new VecInt();
    	for(int i=0;i<100;i++)
    		outReason.push(i);
    	
    	originalWLClause.calcReasonOnTheFly(2, outReason, outReason);
    	
    	assertEquals(199, outReason.size());
    }
    
    public void testAssertConstraintExample1() throws Exception {
    	originalWLClause.assertConstraint(s);
    }
    
    public void testAssertConstraintExample2() throws Exception {
    	for(int i=0;i<100;i++)
    		voc.forgets(i);
    	originalWLClause.assertConstraint(s);
    }
    
    public void testToString() throws Exception {
    	assertTrue(originalWLClause.toString().contains("-2"));
    }
    
    public void testLockedExample1() throws Exception {
    	assertFalse(originalWLClause.locked());
    }
    
    public void testLockedExample2() throws Exception {
    	voc.setReason(ps.get(0), originalWLClause);
    	assertTrue(originalWLClause.locked());
    }
    
    public void testPropogateExample1() throws Exception {    
    	assertTrue(originalWLClause.propagate(s, 5));
    }
       
    public void testPropogateExample2() throws Exception {
    	assertTrue(originalWLClause.propagate(s, 4));
    }
    
    public void testPropogateExample3() throws Exception {
    	for(int i=0;i<100;i++)
    		voc.forgets(i);
    	assertTrue(originalWLClause.propagate(s, 1));
    }
    
    public void testBrandNewClause() throws Exception {
    	IVecInt literals = new VecInt();
        
        for(int i=0;i<100;i++) 
        	literals.push(i);
        
        OriginalWLClause clause = OriginalWLClause.brandNewClause(s, voc, literals);
    	
    	assertFalse(clause.learnt());
    }
}
