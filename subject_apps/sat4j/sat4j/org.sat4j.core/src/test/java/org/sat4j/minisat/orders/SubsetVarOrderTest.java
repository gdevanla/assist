package org.sat4j.minisat.orders;

import java.lang.reflect.Field;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.constraints.cnf.Lits;
import org.sat4j.minisat.core.ILits;
import org.sat4j.specs.IVecInt;

import junit.framework.TestCase;

public class SubsetVarOrderTest extends TestCase {
	private SubsetVarOrder subsetVarOrder;
	
	public SubsetVarOrderTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
    	IVecInt lits = new VecInt();
    	lits.push(0);
    	int[] varsToTest = lits.toArray();
        this.subsetVarOrder = new SubsetVarOrder(varsToTest);
        ILits sample = new Lits();
    	subsetVarOrder.setLits(sample);
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testInitExample1() throws Exception {
    	subsetVarOrder.init();
    
    	assertEquals(0, subsetVarOrder.heap.size());
    }
    
    public void testInitExample2() throws Exception {
    	subsetVarOrder.activity = new double[0];
    	subsetVarOrder.init();
    	assertEquals(0, subsetVarOrder.heap.size());
    }
    
    public void testInitExample3() throws Exception {
    	subsetVarOrder.activity = new double[5];
    	subsetVarOrder.activity = null;
    	subsetVarOrder.init();
    	assertEquals(0, subsetVarOrder.heap.size());
    }
    
    public void testInitExample4() throws Exception {
    	subsetVarOrder.activity = new double[5];
    	for(int i=0;i<5;i++)
    		subsetVarOrder.activity[i] = i;
    	subsetVarOrder.init();
    	assertEquals(0, subsetVarOrder.heap.size());
    }
    
    public void testInitExample5() throws Exception {
    	subsetVarOrder.activity = new double[5];
    	for(int i=0;i<5;i++)
    		subsetVarOrder.activity[i] = i;
    	
    	subsetVarOrder.lits.getFromPool(0);
    	
    	subsetVarOrder.init();
    	assertEquals(5, subsetVarOrder.heap.size());
    }

}
