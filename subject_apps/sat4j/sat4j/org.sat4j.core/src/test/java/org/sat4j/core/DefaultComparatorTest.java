package org.sat4j.core;

import java.util.Comparator;

import org.sat4j.specs.IVec;

import junit.framework.TestCase;

public class DefaultComparatorTest extends TestCase{
	
	 private DefaultComparator comp;
	private Object myarray;

	public DefaultComparatorTest(String arg0) {
	        super(arg0);
	    }

	    /*
	     * @see TestCase#setUp()
	     */
	    @Override
	    protected void setUp() throws Exception {
	        super.setUp();
	        this.comp = new DefaultComparator();
	    }

	    /*
	     * @see TestCase#tearDown()
	     */
	    @Override
	    protected void tearDown() throws Exception {
	        super.tearDown();
	    }

	    public void testCompare() {
	    	Vec<Integer> nvec1 = new Vec<Integer>();
	        for (int i = 101; i > 0; i--) {
	            nvec1.push(new Integer(i));
	        }
	        Object[] comp1 = nvec1.toArray();
	        
	     	Vec<Integer> nvec2 = new Vec<Integer>();
	     	nvec1.copyTo(nvec2);
	     	
	        Object[] comp2 = nvec2.toArray();
	        
	        assertEquals(0, comp.compare(comp1[0], comp2[0]));
	        assertEquals(1, comp.compare(comp1[3], comp2[5]));
	    }
	    
}
