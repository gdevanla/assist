package org.sat4j.minisat.constraints;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.core.Constr;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.IVecInt;

import junit.framework.TestCase;

public class ClausalDataStructureWLTest extends TestCase{
	private ClausalDataStructureWL clausalDataStructure;

	public ClausalDataStructureWLTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.clausalDataStructure = new ClausalDataStructureWL();
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
        
        Constr expected = clausalDataStructure.createUnregisteredClause(myvec);
        assertEquals(15, expected.size());
    }
    
    public void testCreateClauseExample1() throws Exception{
    	VecInt myvec = new VecInt();
        for (int i = 0; i < 15; i++) {
            myvec.push(new Integer(i));
        } 
        try {
        	clausalDataStructure.createClause(myvec.EMPTY);
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
        	clausalDataStructure.createClause(myvec);
        } catch(Exception e) {
        	assertEquals(15, myvec.size());
        }
    }

    public void testCreateClauseExample3() throws Exception{
    	IVecInt myvec = new VecInt();
        for (int i = 0; i < 15; i++) {
            myvec.push(new Integer(i));
        }
        Constr expected = clausalDataStructure.createClause(myvec);
        assertNull(expected);
      
    }
    
    public void testCreateClauseExample4() throws Exception{
    	IVecInt myvec = new VecInt();
    	myvec.push(new Integer(67));
    	myvec.push(new Integer(70));
    	
        Constr expected = clausalDataStructure.createClause(myvec);
        assertEquals(2, expected.size());
      
    }
   
    public void testCreateClauseExample5() throws Exception{
    	IVecInt myvec = new VecInt();
    	myvec.push(new Integer(77));
    	myvec.push(new Integer(67));
    	myvec.push(new Integer(90));
    	
        Constr expected = clausalDataStructure.createClause(myvec);
        assertEquals(3, expected.size());
      
    }
    
    public void testCreateCardinalityConstraint() throws Exception{
       	VecInt myvec = new VecInt();
        myvec.push(0);
        myvec.push(1);
        try {
        	clausalDataStructure.createCardinalityConstraint(myvec, 0);
        } catch(UnsupportedOperationException ex ) {
        	
        }      
    }
    
    public void testCreateUnregisteredCardinalityConstraint() throws Exception{
    	VecInt myvec = new VecInt();
    	myvec.push(0);
        myvec.push(1);
        
        try {
        	clausalDataStructure.createUnregisteredCardinalityConstraint(myvec, 0);
        } catch(UnsupportedOperationException ex) {
        	
        }
    }
   
}
