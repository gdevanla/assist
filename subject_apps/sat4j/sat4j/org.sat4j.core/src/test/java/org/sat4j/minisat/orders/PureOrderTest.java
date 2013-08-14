package org.sat4j.minisat.orders;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.constraints.cnf.Lits;
import org.sat4j.minisat.core.ILits;
import org.sat4j.specs.IVecInt;

import junit.framework.TestCase;

public class PureOrderTest extends TestCase{
	private PureOrder order;
	
	public PureOrderTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        this.order = new PureOrder();
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testPeriod() throws Exception {
    	assertEquals(20, order.getPeriod());
    	
    	order.setPeriod(100);
    	assertEquals(100, order.getPeriod());   	
    }
    
    public void testSelectExample1() throws Exception {
    	ILits literals = new Lits();
    	order.setLits(literals);
    	order.init();
    	
    	assertEquals(-1, order.select());
    }
    
    public void testSelectExample2() throws Exception {
    	ILits literals = new Lits();
    	order.setLits(literals);
    	order.init();
    	
    	assertEquals(-1, order.select());   	
    	assertEquals(-1, order.select());
    }
    
    public void testSelectExample3() throws Exception {
    	ILits literals = new Lits();
    	order.setLits(literals);
    	order.init();
    	order.heap.insert(0);
    	
    	assertEquals(0, order.select());   	
    }

    public void testSelectExample4() throws Exception {
    	ILits literals = new Lits();
    	literals.ensurePool(200);
    	order.setLits(literals);
    	order.init();
    	for(int i=0;i<10;i++)
    		order.heap.insert(0);
    	
       	try {
       		order.select();
       	} catch(Exception e) {
       		
       	}
    }
    public void testToString() throws Exception {
    	assertEquals("tries to first branch on a single phase watched unassigned variable (pure literal if using a CB data structure) else VSIDS from MiniSAT", order.toString());
    }
}
