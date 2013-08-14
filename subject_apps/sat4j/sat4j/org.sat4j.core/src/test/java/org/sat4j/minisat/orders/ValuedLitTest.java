package org.sat4j.minisat.orders;

import java.lang.reflect.Field;

import org.sat4j.minisat.constraints.cnf.Lits;
import org.sat4j.minisat.core.ILits;

import junit.framework.TestCase;

public class ValuedLitTest extends TestCase{
	private ValuedLit valuedLit;
	private final VarOrderHeap order = new VarOrderHeap();
	
	
	public ValuedLitTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        this.valuedLit = new ValuedLit(1,50);
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testCompareExample1() throws Exception {
    	assertEquals(0, valuedLit.compareTo(valuedLit));
    }
    
    public void testCompareExample2() throws Exception {
    	ValuedLit lit = new ValuedLit(1,0);
    	assertEquals(Integer.MAX_VALUE, lit.compareTo(valuedLit));
    }
    
    public void testCompareExample3() throws Exception {
    	ValuedLit lit = new ValuedLit(1,0);
    	assertEquals(-1, valuedLit.compareTo(lit));
    }
    
    public void testEqualsExample1() throws Exception {
    	assertFalse(valuedLit.equals(null));
    }
    
    public void testEqualsExample2() throws Exception {
    	assertTrue(valuedLit.equals(valuedLit));
    }
    
    public void testEqualsExample3() throws Exception {
    	VarOrderHeap order= new VarOrderHeap();
    	assertFalse(valuedLit.equals(order));
    }
    
    public void testCompareExample4() throws Exception {
    	ValuedLit lit = new ValuedLit(1,0);
    	assertFalse(valuedLit.equals(lit));
    }
    
    public void testHashCode() throws Exception {
    	assertEquals(1, valuedLit.hashCode());
    }
    
    public void testToString() throws Exception {
    	assertEquals("1(50)", valuedLit.toString());
    }
}
