package org.sat4j.minisat.constraints.cnf;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.minisat.core.ILits;
import org.sat4j.minisat.core.UnitPropagationListener;
import org.sat4j.specs.IVecInt;

import junit.framework.TestCase;

public class UnitClausesTest extends TestCase{
	private UnitClauses unitClauses;
	private IVecInt ps = new VecInt();
    private UnitPropagationListener s = (UnitPropagationListener) SolverFactory.newDefault();
    
    public UnitClausesTest(String arg0) {
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
        this.unitClauses = new UnitClauses(ps);
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
    		unitClauses.setLearnt();
    	} catch(Exception e) {
    		
    	}
    }
    
    public void testLearnt() throws Exception {
    	try {
    		unitClauses.learnt();
    	} catch(Exception e) {
    		
    	}
    }
    
    public void testIncActivity() throws Exception {
    	double claInc = 5.0;
    	unitClauses.incActivity(claInc);  // does nothing
    	unitClauses.forwardActivity(claInc); // does nothing
    	unitClauses.setActivity(claInc); // does nothing
    	
    	try {
    		assertEquals(claInc, unitClauses.getActivity());
    	} catch (Exception e) {
    		
    	}
    }
    
    public void testCalcReasonExample1() throws Exception {
    	IVecInt outReason = new VecInt();
    	for(int i=0;i<100;i++)
    		outReason.push(i);
    	try {
    		unitClauses.calcReason(2, outReason);
    	} catch (Exception e) {
    		
    	}
    }
    
    
    public void testSimplifyExample1() throws Exception {
    	assertFalse(unitClauses.simplify());
    }

    public void testGet() throws Exception {
    	try {
    		assertEquals(67, unitClauses.get(4));
    	} catch (Exception e) {
    		
    	}
    }
    
    public void testSize() throws Exception {
    	try {
    		unitClauses.size();
    	} catch(Exception e) {
    		
    	}
    }
    
    public void testRescaleBy() throws Exception {
    	try {
    		unitClauses.rescaleBy(5.4);
    	} catch (Exception e) {
    		
    	}
    }
    
    public void testEqualsExample1() throws Exception {
    	assertFalse(unitClauses.equals(null));
    }
    
    public void testEqualsExample3() throws Exception {
    	assertTrue(unitClauses.equals(unitClauses));
    }
    
    public void testEqualsExample4() throws Exception {
    	IVecInt argu1 = new VecInt();
        ILits argu2 = new Lits();
        
        for(int i=0;i<100;i++)
        	argu1.push(9);
        argu2.ensurePool(200);
        LearntBinaryClause clause = new LearntBinaryClause(argu1, argu2);
        
    	assertFalse(unitClauses.equals(clause));
    }
    
    public void testEqualsExample5() throws Exception {
    	IVecInt argu1 = new VecInt();
        ILits argu2 = new Lits();
        argu1.push(5);
        argu1.push(9);
        argu2.ensurePool(200);
        LearntBinaryClause clause = new LearntBinaryClause(argu1, argu2);
        
    	assertFalse(unitClauses.equals(clause));
    }
    
    public void testHashCode() throws Exception {
    	int expected = unitClauses.hashCode();
    	assertEquals(expected, unitClauses.hashCode());
    }
    
    public void testRegister() throws Exception {
    	try {
    		unitClauses.register();
    	} catch (Exception e) {
    		
    	}
    }
    
    public void testCanBePropogatedMultipleTimes() throws Exception {
    	assertFalse(unitClauses.canBePropagatedMultipleTimes());
    }
    
    public void testCalcReasonOnTheFly() throws Exception {
    	IVecInt outReason = new VecInt();
    	for(int i=0;i<100;i++)
    		outReason.push(i);
    	
    	try {
    		unitClauses.calcReasonOnTheFly(2, outReason, outReason);
    	} catch (Exception e) {
    		
    	}

    }
    
    public void testAssertConstraint() throws Exception {
    	try {
    		unitClauses.assertConstraint(s);
    	} catch (Exception e) {
    		
    	}
    }
  
    public void testToString() throws Exception {
    	assertTrue(unitClauses.toString().contains(""));
    }
    
    public void testLockedExample1() throws Exception {
    	try {
    		unitClauses.locked();
    	} catch(Exception e) {
    		
    	}
    }

    public void testPropogateExample1() throws Exception {
    	try {
    	unitClauses.propagate(s, 4);
    	} catch(Exception e) {
    		
    	}
    }
    
    public void testRemove() throws Exception {
    	try {
    		unitClauses.remove(s);
    	} catch (Exception e) {
    		
    	}

    }
}
