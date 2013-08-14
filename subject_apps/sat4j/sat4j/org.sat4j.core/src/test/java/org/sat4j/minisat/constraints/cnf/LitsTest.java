package org.sat4j.minisat.constraints.cnf;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.minisat.core.Constr;
import org.sat4j.minisat.core.ILits;
import org.sat4j.minisat.core.Propagatable;
import org.sat4j.minisat.core.Undoable;
import org.sat4j.minisat.core.UnitPropagationListener;
import org.sat4j.specs.IVec;
import org.sat4j.specs.IVecInt;

import junit.framework.TestCase;

public class LitsTest extends TestCase{
	private Lits lits;
    private UnitPropagationListener s = (UnitPropagationListener) SolverFactory.newDefault();
    
    public LitsTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.lits = new Lits();
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testInitExample1() throws Exception {
    	lits.init(200);
    	
    	assertEquals(0, lits.nVars());
    }
    
    public void testInitExample2() throws Exception {
    	lits.init(0);
    	
    	assertEquals(0, lits.nVars());
    }
    
    public void testGetFromPool() throws Exception {
    	assertEquals(0, lits.getFromPool(0));
    	assertEquals(18, lits.getFromPool(9));
    	assertEquals(600, lits.getFromPool(300));
    }
    
    public void testBelongsToPool() throws Exception {
    	assertFalse(lits.belongsToPool(700));
    	assertFalse(lits.belongsToPool(3));
    }
    
    public void testResetPool() throws Exception {
    	lits.resetPool();
    	
    	assertEquals(0, lits.nVars());
    }
    
    public void testEnsurePoolExample1() throws Exception {
    	lits.ensurePool(0);
    	
    	assertEquals(0, lits.nVars());
    }
    
    
    public void testEnsurePoolExample2() throws Exception {
    	lits.ensurePool(200);
    	
    	assertEquals(200, lits.nVars());
    }
    
    public void testUnassign() throws Exception {
    	for(int i=0;i<100;i++)
    		lits.unassign(i);
    	
    	assertTrue(lits.isUnassigned(6));
    	assertTrue(lits.isUnassigned(200));
    	
    }
    
    public void testSatisfies() throws Exception {
    	for(int i=0;i<100;i++)
    		lits.satisfies(i);
    	
    	assertFalse(lits.isSatisfied(6));
    }
    
    public void testForgets() throws Exception {
    	for(int i=0;i<100;i++)
    		lits.forgets(i);
    	
    	assertTrue(lits.isFalsified(6));
    }
    
    public void testValueToStringExample1() throws Exception {
    	assertEquals("?", lits.valueToString(67));
    }
    
    public void testValueToStringExample2() throws Exception {
    	lits.satisfies(34);
    	assertEquals("T", lits.valueToString(34));
    }
    
    public void testnVars() throws Exception {
    	assertEquals(0, lits.nVars());
    }
    
    public void testNot() throws Exception {
    	assertEquals(66, lits.not(67));
    }
    
    public void testToString() throws Exception {
    	assertEquals("-33", Lits.toString(67));
    	assertEquals("0", Lits.toString(0));
    }

    
    public void testGetLevel() throws Exception {
    	assertEquals(0, lits.getLevel(0));
    }
    
    public void testSetLevel() throws Exception {
    	lits.setLevel(0, 5);
    	assertEquals(5, lits.getLevel(0));
    }
    
    public void testGetReason() throws Exception {
    	Constr expected = lits.getReason(0);
    	assertNull(expected);
    }
    
    public void testSetReason() throws Exception {
    	Constr expected = null;
    	lits.setReason(0, expected);
    	
    	assertNull(lits.getReason(0));
    }
    
    public void testUndo() throws Exception {
    	IVec<Undoable> expected = lits.undos(0);
    	assertNull(expected);
    }
    
    public void testWatch() throws Exception {
    	IVecInt ps = new VecInt();
        ILits voc = new Lits();
        OriginalWLClause originalWLClause = new OriginalWLClause(ps, voc);
    	lits.watch(9, originalWLClause);
    	
    	IVec<Propagatable> expected = lits.watches(9);
    	assertNull(expected);
    	
    }
    
    public void testIsImplied() throws Exception {
    	assertFalse(lits.isImplied(9));
    }

    public void testRealnVars() throws Exception {
    	assertEquals(0, lits.realnVars());
    }
    
    public void testCapacity() throws Exception {
    	assertEquals(128, lits.capacity());
    }
    
    public void testNextFreeVarId() throws Exception {
    	assertEquals(1, lits.nextFreeVarId(true));
    	assertEquals(2, lits.nextFreeVarId(false));
    }
}
