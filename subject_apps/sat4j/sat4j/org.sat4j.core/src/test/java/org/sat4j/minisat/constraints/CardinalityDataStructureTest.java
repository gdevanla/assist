package org.sat4j.minisat.constraints;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.minisat.core.Constr;
import org.sat4j.minisat.core.UnitPropagationListener;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.IVecInt;

import junit.framework.TestCase;

import org.sat4j.minisat.core.UnitPropagationListener;
public class CardinalityDataStructureTest extends TestCase{
	private CardinalityDataStructure cardDataStructure;

	public CardinalityDataStructureTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.cardDataStructure = new CardinalityDataStructure();
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testCreateUnregisteredClause() {
    	VecInt myvec = new VecInt();
        for (int i = 0; i < 15; i++) {
            myvec.push(new Integer(i));
        }        
        Constr expected = cardDataStructure.createUnregisteredClause(myvec);
        
        assertEquals(15, expected.size());
    }
    
    public void testCreateCardinalityConstraint() throws Exception{
    	VecInt myvec = new VecInt();
        for (int i = 0; i < 15; i++) {
            myvec.push(new Integer(i));
        } 
        Constr expected = cardDataStructure.createCardinalityConstraint(myvec, 0);
        
        assertFalse(expected.canBePropagatedMultipleTimes());
        assertFalse(expected.simplify());
    }
    
    public void testCreateUnregisteredCardinalityConstraint() throws Exception{
    	VecInt myvec = new VecInt();
        for (int i = 0; i < 15; i++) {
            myvec.push(new Integer(i));
        } 
        
        Constr expected = cardDataStructure.createUnregisteredCardinalityConstraint(myvec, 0);
        assertTrue(expected.canBePropagatedMultipleTimes());
        assertFalse(expected.simplify());
    }
    
    public void testCreateClauseExample1() throws Exception{
    	VecInt myvec = new VecInt();
        for (int i = 0; i < 15; i++) {
            myvec.push(new Integer(i));
        } 
        try {
        	cardDataStructure.createClause(myvec.EMPTY);
        } catch(ContradictionException e) {
        	assertEquals(15, myvec.size());
        }
        
    }
    
    public void testCreateClauseExample2() throws Exception{
    	IVecInt myvec = new VecInt();
        for (int i = 0; i < 15; i++) {
            myvec.push(new Integer(i));
        }
        try {
        	cardDataStructure.createClause(myvec);
        } catch(Exception e) {
        	assertEquals(15, myvec.size());
        }
    }
    
    public void testCreateClauseExample3() throws Exception{
    	IVecInt myvec = new VecInt();
        for (int i = 0; i < 15; i++) {
            myvec.push(new Integer(i));
        }
        Constr expected = cardDataStructure.createClause(myvec);
        assertEquals(15, expected.size());
      
    }
}
