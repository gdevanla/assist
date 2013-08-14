package org.sat4j.minisat.constraints;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.core.Constr;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.IVecInt;

import junit.framework.TestCase;

public class MixedDataStructureDanielHTTest extends TestCase{
	private MixedDataStructureDanielHT mixedDataStructure;

	public MixedDataStructureDanielHTTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.mixedDataStructure = new MixedDataStructureDanielHT();
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testCreateUnregisteredClauseExample1() {
    	VecInt myvec = new VecInt();
        for (int i = 0; i < 15; i++) {
            myvec.push(new Integer(i));
        }        
        Constr expected = mixedDataStructure.createUnregisteredClause(myvec);
        
        assertEquals(15, expected.size());
    }
    
    public void testCreateUnregisteredClauseExample2() throws Exception{
    	IVecInt myvec = new VecInt();
    	myvec.push(new Integer(67));
    	myvec.push(new Integer(70));
    	
        Constr expected = mixedDataStructure.createUnregisteredClause(myvec);
        assertEquals(2, expected.size());
      
    }
    
    public void testCreateUnregisteredClauseExample3() throws Exception{
    	IVecInt myvec = new VecInt();
    	myvec.push(new Integer(67));
    	
        Constr expected = mixedDataStructure.createUnregisteredClause(myvec);
        assertEquals(1, expected.size());
      
    }
    
    public void testCreateCardinalityConstraint() throws Exception{
    	VecInt myvec = new VecInt();
        for (int i = 0; i < 15; i++) {
            myvec.push(new Integer(i));
        } 
        Constr expected = mixedDataStructure.createCardinalityConstraint(myvec, 0);
        
        assertFalse(expected.canBePropagatedMultipleTimes());
        assertFalse(expected.simplify());
    }
    
    public void testCreateUnregisteredCardinalityConstraint() throws Exception{
    	VecInt myvec = new VecInt();
        for (int i = 0; i < 15; i++) {
            myvec.push(new Integer(i));
        } 
        
        Constr expected = mixedDataStructure.createUnregisteredCardinalityConstraint(myvec, 0);
        assertTrue(expected.canBePropagatedMultipleTimes());
        assertFalse(expected.simplify());
    }
    
    public void testCreateClauseExample1() throws Exception{
    	VecInt myvec = new VecInt();
        for (int i = 0; i < 15; i++) {
            myvec.push(new Integer(i));
        } 
        try {
        	mixedDataStructure.createClause(myvec.EMPTY);
        } catch(ContradictionException e) {
        	
        }
    }
    
    public void testCreateClauseExample2() throws Exception{
    	IVecInt myvec = new VecInt();
        for (int i = 0; i < 15; i++) {
            myvec.push(new Integer(i));
        }
        try {
        	mixedDataStructure.createClause(myvec);
        } catch(Exception e) {
        	
        }
    }
    
    public void testCreateClauseExample3() throws Exception{
    	IVecInt myvec = new VecInt();
        for (int i = 0; i < 15; i++) {
            myvec.push(new Integer(i));
        }
        Constr expected = mixedDataStructure.createClause(myvec);
        assertNull(expected);
      
    }
    
    public void testCreateClauseExample4() throws Exception{
    	IVecInt myvec = new VecInt();
    	myvec.push(new Integer(67));
    	myvec.push(new Integer(70));
    	
        Constr expected = mixedDataStructure.createClause(myvec);
        assertEquals(2, expected.size());
      
    }
    
    public void testCreateClauseExample5() throws Exception{
    	IVecInt myvec = new VecInt();
    	myvec.push(new Integer(77));
    	myvec.push(new Integer(67));
    	myvec.push(new Integer(90));
    	
        Constr expected = mixedDataStructure.createClause(myvec);
        assertEquals(3, expected.size());
      
    }
    
}
