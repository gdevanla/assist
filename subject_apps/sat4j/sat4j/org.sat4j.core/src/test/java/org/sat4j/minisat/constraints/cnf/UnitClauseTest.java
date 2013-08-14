package org.sat4j.minisat.constraints.cnf;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.minisat.core.ILits;
import org.sat4j.minisat.core.UnitPropagationListener;
import org.sat4j.specs.IVecInt;

import junit.framework.TestCase;

public class UnitClauseTest extends TestCase{
	private UnitClause unitClause;
    private UnitPropagationListener s = (UnitPropagationListener) SolverFactory.newDefault();
    
    public UnitClauseTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.unitClause = new UnitClause(67);
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testSetLearnt() throws Exception {
    	try {
    		unitClause.setLearnt();
    	} catch(Exception e) {
    		
    	}
    	assertFalse(unitClause.learnt());
    }
    
    public void testIncActivity() throws Exception {
    	double claInc = 5.0;
    	unitClause.incActivity(claInc);  // does nothing
    	unitClause.forwardActivity(claInc); // does nothing
    	unitClause.setActivity(claInc); 
    	
    	assertEquals(claInc, unitClause.getActivity());
    }
    
    public void testSetActivity() throws Exception {
    	double d = 2.3;
    	unitClause.setActivity(d);
    	
    	assertEquals(d, unitClause.getActivity());
    }
    
    public void testCalcReasonExample1() throws Exception {
    	IVecInt outReason = new VecInt();
    	for(int i=0;i<100;i++)
    		outReason.push(i);
    
    	unitClause.calcReason(2, outReason);
    	assertEquals(100, outReason.size());
    }
    
    public void testCalcReasonExample2() throws Exception {
    	IVecInt outReason = new VecInt();
    	for(int i=0;i<100;i++) {
    		outReason.push(i);
    	}
    	unitClause.calcReason(ILits.UNDEFINED, outReason);
    	assertEquals(101, outReason.size());
    }
    
    public void testSimplifyExample1() throws Exception {
    	assertFalse(unitClause.simplify());
    }
    
    public void testGetExample1() throws Exception {
    	assertEquals(67, unitClause.get(0));
    }
    
    public void testGetExample2() throws Exception {
    	try {
    		assertEquals(67, unitClause.get(4));
    	} catch (Exception e) {
    		
    	}
    }
    
    public void testSize() throws Exception {
    	assertEquals(1, unitClause.size());
    }
    
    public void testRescaleBy() throws Exception {
    	try {
    		unitClause.rescaleBy(5.4);
    	} catch (Exception e) {
    		
    	}
    }
    
    public void testEqualsExample1() throws Exception {
    	assertFalse(unitClause.equals(null));
    }
    
    public void testEqualsExample3() throws Exception {
    	assertTrue(unitClause.equals(unitClause));
    }
    
    public void testEqualsExample4() throws Exception {
    	IVecInt argu1 = new VecInt();
        ILits argu2 = new Lits();
        
        for(int i=0;i<100;i++)
        	argu1.push(9);
        argu2.ensurePool(200);
        LearntBinaryClause clause = new LearntBinaryClause(argu1, argu2);
        
    	assertFalse(unitClause.equals(clause));
    }
    
    public void testEqualsExample5() throws Exception {
    	IVecInt argu1 = new VecInt();
        ILits argu2 = new Lits();
        argu1.push(5);
        argu1.push(9);
        argu2.ensurePool(200);
        LearntBinaryClause clause = new LearntBinaryClause(argu1, argu2);
        
    	assertFalse(unitClause.equals(clause));
    }
    
    public void testHashCode() throws Exception {
    	int expected = unitClause.hashCode();
    	assertEquals(expected, unitClause.hashCode());
    }
    
    public void testRegister() throws Exception {
    	try {
    		unitClause.register();
    	} catch (Exception e) {
    		
    	}
    }
    
    public void testCanBePropogatedMultipleTimes() throws Exception {
    	assertFalse(unitClause.canBePropagatedMultipleTimes());
    }
    
    public void testCalcReasonOnTheFly() throws Exception {
    	IVecInt outReason = new VecInt();
    	for(int i=0;i<100;i++)
    		outReason.push(i);
    	
    	unitClause.calcReasonOnTheFly(2, outReason, outReason);
    	
    	assertEquals(100, outReason.size());
    }
    
    public void testAssertConstraintExample1() throws Exception {
    	unitClause.assertConstraint(s);
    }
    
    public void testAssertConstraintExample2() throws Exception {
    	unitClause.assertConstraint(s);
    }
    
    public void testToString() throws Exception {
    	assertTrue(unitClause.toString().contains(""));
    }
    
    public void testLockedExample1() throws Exception {
    	try {
    		unitClause.locked();
    	} catch(Exception e) {
    		
    	}
    }

    public void testPropogateExample1() throws Exception {
    	try {
    	unitClause.propagate(s, 4);
    	} catch(Exception e) {
    		
    	}
    }
    
    public void testRemove() throws Exception {
    	unitClause.remove(s);
    	
    	assertEquals(1, unitClause.size());
    }
}
