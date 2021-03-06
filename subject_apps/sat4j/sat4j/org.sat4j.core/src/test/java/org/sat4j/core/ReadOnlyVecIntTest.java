/*******************************************************************************
 * SAT4J: a SATisfiability library for Java Copyright (C) 2004, 2012 Artois University and CNRS
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU Lesser General Public License Version 2.1 or later (the
 * "LGPL"), in which case the provisions of the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of the LGPL, and not to allow others to use your version of
 * this file under the terms of the EPL, indicate your decision by deleting
 * the provisions above and replace them with the notice and other provisions
 * required by the LGPL. If you do not delete the provisions above, a recipient
 * may use your version of this file under the terms of the EPL or the LGPL.
 *
 * Based on the original MiniSat specification from:
 *
 * An extensible SAT solver. Niklas Een and Niklas Sorensson. Proceedings of the
 * Sixth International Conference on Theory and Applications of Satisfiability
 * Testing, LNCS 2919, pp 502-518, 2003.
 *
 * See www.minisat.se for the original solver in C++.
 *
 * Contributors:
 *   CRIL - initial API and implementation
 *******************************************************************************/
package org.sat4j.core;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import junit.framework.TestCase;

import org.sat4j.specs.IVec;
import org.sat4j.specs.IteratorInt;

/**
 * @author icewariya
 * 
 */
public class ReadOnlyVecIntTest extends TestCase {

	private ReadOnlyVecInt myvec;
    /**
     * Constructor for VecTest.
     * 
     * @param arg0
     */
    public ReadOnlyVecIntTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        VecInt vec = new VecInt();
        for (int i = 0; i < 100; i++) {
            vec.push(new Integer(i));
        }
        this.myvec = new ReadOnlyVecInt(vec);
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testSize() {
        assertEquals(100, this.myvec.size());
    }

    public void testShrink() {
        assertEquals(100, this.myvec.size());
        try {
        	this.myvec.shrink(10);
        } catch (UnsupportedOperationException ex) {
        	
        }
        assertEquals(100, this.myvec.size());
    }

    public void testShrinkTo() {
        assertEquals(100, this.myvec.size());
        try {
        	this.myvec.shrinkTo(10);
        } catch (UnsupportedOperationException ex) {
        	
        }
        assertEquals(100, this.myvec.size());
    }

    public void testPop() {
        try {
        	this.myvec.pop();
        } catch (UnsupportedOperationException ex) {
        	
        }
        assertEquals(100, this.myvec.size());
    }

    public void testGrowToint() {
       
        Integer douze = new Integer(12);
        try {         
            this.myvec.growTo(12, douze);
        } catch(UnsupportedOperationException ex){
        	
        }
        assertEquals(100, this.myvec.size());
    }

    public void testPush() {
        try {
        	this.myvec.push(new Integer(0));
        } catch (UnsupportedOperationException ex) {
        	
        }
 
        assertEquals(100, this.myvec.size());
    }

    public void testClear() {
        try {
        	this.myvec.clear();
        } catch(UnsupportedOperationException ex) {
        	
        }
        assertEquals(100, this.myvec.size());
    }

    public void testLast() {
    	assertEquals(99, this.myvec.last());
    }

    public void testGet() {
    	assertEquals(50, this.myvec.get(50));	
    }

    public void testCopyTo() {
        VecInt nvec = new VecInt();
        this.myvec.copyTo(nvec);
        assertEquals(100, nvec.size());
        assertEquals(100, this.myvec.size());
        assertEquals(this.myvec.last(), nvec.last());
    }

    public void testCopyToInt() {
        int[] nvec = new int[100];
        this.myvec.copyTo(nvec);
        assertEquals(100, nvec.length);
        assertEquals(100, this.myvec.size());
        assertEquals(this.myvec.last(), nvec[99]);
    }
    
    public void testMoveTo() {
        VecInt nvec = new VecInt();
        try {
        	this.myvec.moveTo(nvec);
        } catch (UnsupportedOperationException ex) {
        	
        }
        assertEquals(0, nvec.size());
    }

    public void testMoveTo2() {
        VecInt nvec = new VecInt();
        try {
        	this.myvec.moveTo2(nvec);
        } catch (UnsupportedOperationException ex) {
        	
        }
        assertEquals(0, nvec.size());
    }
    
    public void testMoveToSrcDest() {
    	try {
    		this.myvec.moveTo(0, 5);
    	} catch (UnsupportedOperationException ex) {
    		
    	}  	 
    	assertEquals(100, this.myvec.size());
    }

    public void testMoveToInt() {
        int[] nvec = new int[100];
        try {
        	this.myvec.moveTo(nvec);
        } catch (UnsupportedOperationException ex) {
        	
        }
        assertEquals(100, nvec.length);
    }
    
    
    public void testSort() {
        try {
        	this.myvec.sort();
        } catch (UnsupportedOperationException ex) {
        	
        }
        assertEquals(100, this.myvec.size());
    }

    public void testSortUnique() {
        try {
        	this.myvec.sortUnique();
        } catch (UnsupportedOperationException ex) {
        	
        }
        assertEquals(100, this.myvec.size());
    }

    public void testDelete() { 
        try {
        	this.myvec.delete(10);
        } catch(UnsupportedOperationException ex) {
        	
        }
        assertEquals(100, this.myvec.size());
    }

    public void testRemove() {
        Integer toRemove = this.myvec.get(10);
        try {
        	this.myvec.remove(toRemove);
        } catch (UnsupportedOperationException ex) {
        	
        }
        assertEquals(100, this.myvec.size());
    }

    public void testEquals() {       
        IVec<Integer> example1 = new Vec<Integer>();
        for (int i = 0; i < 50; i++) {
            example1.push(new Integer(i));
        }
        ReadOnlyVec<Integer> vec1 = new ReadOnlyVec<Integer>(example1);

        assertEquals(this.myvec, this.myvec);
        assertFalse(this.myvec.equals(vec1));
        assertTrue(vec1.equals(vec1));
    }

    public void testIterator() {
        IteratorInt it = this.myvec.iterator();
        assertTrue(it.hasNext());
        assertEquals(0, it.next());
    }
    
    public void testEnsure() {
    	try {
    		this.myvec.ensure(0);
    	} catch (UnsupportedOperationException ex) {
    		
    	}
    	assertEquals(100, this.myvec.size());
    }
    
    public void testVecArray() {
        VecInt destination = new VecInt();
        this.myvec.copyTo(destination);
        
        assertEquals(100, destination.size());
    }
    
    public void testUnsafePush() {       
        try {
            this.myvec.unsafePush(new Integer(0));
        } catch (UnsupportedOperationException ex) {
        	
        }
        assertEquals(100, this.myvec.size());
    }
    
    public void testIsEmpty() {
    	IVec<Integer> example1 = new Vec<Integer>();
        ReadOnlyVec<Integer> readOnlyVec = new ReadOnlyVec<Integer>(example1);
    	assertTrue(readOnlyVec.isEmpty());
    }
    
    public void testIsNotEmpty() {
    	assertFalse(this.myvec.isEmpty());
    }
    
    
    public void testInsertFirst(){ 	
    	try {
    		this.myvec.insertFirst(new Integer(1));
    	} catch(UnsupportedOperationException ex) {
    		
    	}
    	assertEquals(100, this.myvec.size());
    }
    
    public void testSet() {
    	try {
    		this.myvec.set(5, new Integer(5));
    	} catch(UnsupportedOperationException ex) {
    		
    	}
    	assertEquals(100, this.myvec.size());
    }
    
    public void testCopyToDest() {
    	VecInt destination = new VecInt();
        this.myvec.copyTo(destination);
        assertEquals(100, destination.size());
        assertEquals(100, this.myvec.size());
        assertEquals(this.myvec.last(), destination.last());
    }
    
    public void testToArray() {
    	try {
    		this.myvec.toArray();
    	} catch (UnsupportedOperationException ex) {
    		
    	}
    }
    
    public void testToStringExample() {
    	String expected = this.myvec.toString();	
    	assertEquals(289, expected.length());
    }
    
    public void testNotEquals() {
        Integer[] destination = new Integer[15];
        assertFalse(this.myvec.equals(destination));
    }
    
    public void testHashCode() {
    	assertEquals(49, this.myvec.hashCode());
    }
    
    public void testContains() {    	
    	assertTrue(this.myvec.contains(new Integer(5)));
    	assertFalse(this.myvec.contains(new Integer(200)));
    }
    
    public void testContainsAt() {    	
    	assertEquals(5, this.myvec.containsAt(new Integer(5)));
    	assertEquals(-1, this.myvec.containsAt(new Integer(200), 50));
    }
    
    public void testIndexOf() {
    	assertEquals(14, this.myvec.indexOf(new Integer(14)));
    	assertEquals(-1, this.myvec.indexOf(new Integer(200)));
    }
    
    public void testUnsafeGet() {
    	assertEquals(50, this.myvec.unsafeGet(50));	
    }

    public void testMoveToStartingIndex() {
    	int[] dest = new int[100];
    	try {
    		this.myvec.moveTo(4, dest);
    	} catch(UnsupportedOperationException ex) {
    		
    	}
    }
    
    public void testSubset() {
    	assertNull(this.myvec.subset(2));
    }
}
