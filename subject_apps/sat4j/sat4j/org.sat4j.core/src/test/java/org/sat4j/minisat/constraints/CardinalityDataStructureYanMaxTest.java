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
public class CardinalityDataStructureYanMaxTest extends TestCase{
	private CardinalityDataStructureYanMax cardDataStructure;

	public CardinalityDataStructureYanMaxTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.cardDataStructure = new CardinalityDataStructureYanMax();
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
        myvec.push(0);
        myvec.push(1);
        
        Constr expected = cardDataStructure.createCardinalityConstraint(myvec, 0);
        
        assertNull(expected);
    }
    
    public void testCreateUnregisteredCardinalityConstraint() throws Exception{
    	VecInt myvec = new VecInt();
    	myvec.push(0);
        myvec.push(1);
        
        Constr expected = cardDataStructure.createUnregisteredCardinalityConstraint(myvec, 0);
        
        assertNotNull(expected);
    }
    
    public void testCreateClauseExample1() throws Exception{
    	VecInt myvec = new VecInt();
        for (int i = 0; i < 15; i++) {
            myvec.push(new Integer(i));
        } 
        try {
        	cardDataStructure.createClause(myvec.EMPTY);
        } catch(ContradictionException e) {
        	
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
        	
        }
    }
    
    public void testCreateClauseExample3() throws Exception{
    	VecInt myvec = new VecInt();
    	myvec.push(0);
        myvec.push(1);
        
        Constr expected = cardDataStructure.createClause(myvec);
        
        assertNull(expected);
    }
   
}
