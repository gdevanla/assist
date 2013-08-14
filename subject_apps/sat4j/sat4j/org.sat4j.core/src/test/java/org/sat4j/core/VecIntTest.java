package org.sat4j.core;

import java.util.Iterator;
import java.util.NoSuchElementException;

import junit.framework.TestCase;

import org.sat4j.specs.IVec;
import org.sat4j.specs.IVecInt;
import org.sat4j.specs.IteratorInt;

public class VecIntTest extends TestCase{

	private VecInt myvec;

    public VecIntTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.myvec = new VecInt();
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testVec() {
        VecInt vec = new VecInt();
        assertEquals(0, vec.size());
    }

    /*
     * Test pour void Vec(int)
     */
    public void testVecint() {
        VecInt vec = new VecInt(10, new Integer(0));
        assertEquals(new Integer(0), (Integer)vec.last());
        assertEquals(10, vec.size());
    }

    /*
     * Test pour void Vec(int, Object)
     */
    public void testVecintObject() {
        Integer pad = new Integer(10);
        VecInt vec = new VecInt(10, pad);
        assertEquals(pad, (Integer)vec.last());
        assertEquals(10, vec.size());

    }

    public void testSize() {
        assertEquals(0, this.myvec.size());
        this.myvec.push(new Integer(3));
        assertEquals(1, this.myvec.size());
        this.myvec.push(new Integer(5));
        assertEquals(2, this.myvec.size());
        this.myvec.pop();
        assertEquals(1, this.myvec.size());
        this.myvec.pop();
        assertEquals(0, this.myvec.size());
    }

    public void testShrink() {
        for (int i = 0; i < 15; i++) {
            this.myvec.push(new Integer(i));
        }
        assertEquals(15, this.myvec.size());
        this.myvec.shrink(10);
        assertEquals(5, this.myvec.size());
        assertEquals(new Integer(4), (Integer) this.myvec.last());
        this.myvec.shrink(0);
        assertEquals(5, this.myvec.size());
        assertEquals(new Integer(4), (Integer) this.myvec.last());
    }

    public void testShrinkTo() {
        for (int i = 0; i < 15; i++) {
            this.myvec.push(new Integer(i));
        }
        assertEquals(15, this.myvec.size());
        this.myvec.shrinkTo(10);
        assertEquals(10, this.myvec.size());
        assertEquals(new Integer(9), (Integer) this.myvec.last());
        this.myvec.shrinkTo(10);
        assertEquals(10, this.myvec.size());
        assertEquals(new Integer(9), (Integer) this.myvec.last());

    }

    public void testPop() {
        for (int i = 0; i < 15; i++) {
            this.myvec.push(new Integer(i));
        }
        assertEquals(15, this.myvec.size());
        this.myvec.pop();
        assertEquals(14, this.myvec.size());
        assertEquals(new Integer(13), (Integer) this.myvec.last());
    }

    /*
     * Test pour void growTo(int)
     */
    public void testGrowToint() {
        assertEquals(0, this.myvec.size());
        this.myvec.growTo(12, (Integer) new Integer(0));
        assertEquals(12, this.myvec.size());
        assertEquals(0, this.myvec.last());
        this.myvec.growTo(12, (Integer) new Integer(5));
        assertEquals(24, this.myvec.size());
        assertEquals(5, this.myvec.last());
    }

    /*
     * Test pour void growTo(int, Object)
     */
    public void testGrowTointObject() {
        assertEquals(0, this.myvec.size());
        Integer douze = new Integer(12);
        this.myvec.growTo(12, douze);
        assertEquals(12, this.myvec.size());
        assertEquals(douze, (Integer) this.myvec.last());
        Integer treize = new Integer(13);
        this.myvec.growTo(12, treize);
        assertEquals(24, this.myvec.size());
        assertEquals(treize, (Integer) this.myvec.last());
    }

    /*
     * Test pour void push()
     */
    public void testPush() {
        assertEquals(0, this.myvec.size());
        for (int i = 0; i < 10; i++) {
            this.myvec.push(new Integer(0));
        }
        assertEquals(10, this.myvec.size());
        assertEquals(new Integer(0), (Integer) this.myvec.last());
    }

    /*
     * Test pour void push(Object)
     */
    public void testPushObject() {
        Integer deux = new Integer(2);
        assertEquals(0, this.myvec.size());
        for (int i = 0; i < 10; i++) {
            this.myvec.push(deux);
        }
        assertEquals(10, this.myvec.size());
        assertEquals(deux, (Integer) this.myvec.last());
    }

    public void testClear() {
        this.myvec.push(new Integer(2));
        this.myvec.push(new Integer(2));
        this.myvec.clear();
        assertEquals(0, this.myvec.size());
    }

    public void testLast() {
        for (int i = 0; i < 10; i++) {
            Integer myint = new Integer(i);
            this.myvec.push(myint);
            assertEquals(myint, (Integer)this.myvec.last());
        }
    }

    public void testGet() {
        for (int i = 0; i < 10; i++) {
            Integer myint = new Integer(i);
            this.myvec.push(myint);
            assertEquals(myint, (Integer) this.myvec.get(i));
        }
    }

    public void testUnsafeGet() {
        for (int i = 0; i < 10; i++) {
            Integer myint = new Integer(i);
            this.myvec.push(myint);
            assertEquals(myint, (Integer) this.myvec.unsafeGet(i));
        }
    }
    
    public void testCopyTo() {
        VecInt nvec = new VecInt();
        this.myvec.growTo(15, new Integer(15));
        this.myvec.copyTo(nvec);
        assertEquals(15, nvec.size());
        assertEquals(15, this.myvec.size());
        assertEquals(this.myvec.last(), nvec.last());

    }

    public void testMoveTo() {
    	VecInt nvec = new VecInt();
        this.myvec.growTo(15, new Integer(15));
        this.myvec.moveTo(nvec);
        assertEquals(15, nvec.size());
        assertEquals(0, this.myvec.size());
        assertEquals(new Integer(15), (Integer) nvec.last());
    }

    public void testSelectionSort() {
        VecInt nvec = new VecInt();
        for (int i = 30; i > 0; i--) {
            nvec.push(new Integer(i));
        }
        nvec.selectionSort(0, 30);
        for (int i = 1; i <= 30; i++) {
            assertEquals(i, nvec.get(i - 1));
        }
    }

    public void testSort() {
    	VecInt nvec = new VecInt();
        for (int i = 101; i > 0; i--) {
            nvec.push(new Integer(i));
        }
        nvec.push(new Integer(30));
        nvec.push(new Integer(40));

        nvec.sort();
        for (int i = 1; i <= 30; i++) {
            assertEquals(i, nvec.get(i - 1));
        }
    }


    public void testSortUnique() {
    	VecInt nvec = new VecInt();
        for (int i = 101; i > 0; i--) {
            nvec.push(new Integer(i));
        }
        nvec.push(new Integer(30));
        nvec.push(new Integer(40));
        nvec.push(new Integer(50));
        nvec.push(new Integer(55));
        nvec.push(new Integer(60));

        nvec.sortUnique();
        for (int i = 1; i <= 101; i++) {
            assertEquals(i, nvec.get(i - 1));
        }
    }
    
    public void testDelete() {
    	VecInt nvec = new VecInt();
        for (int i = 0; i < 100; i++) {
            nvec.push(new Integer(i));
        }
        assertEquals(10, nvec.delete(10));
        assertEquals(99, nvec.get(10));
        nvec.clear();
        nvec.push(1);
        assertEquals(1, nvec.delete(0));
    }

    public void testRemove() {
        VecInt nvec = new VecInt();
        for (int i = 0; i < 100; i++) {
            nvec.push(new Integer(i));
        }
        Integer toRemove = nvec.get(10);
        nvec.remove(toRemove);
        assertEquals(99, nvec.size());
        assertEquals(11, nvec.get(10));
        nvec.clear();
        toRemove = new Integer(1);
        nvec.push(toRemove);
        assertEquals(1, nvec.size());
        nvec.remove(toRemove);
        assertEquals(0, nvec.size());
    }

    public void testEquals() {
        VecInt nvec = new VecInt(3, new Integer(2));
        VecInt vect = new VecInt(3, new Integer(2));
        VecInt vecf = new VecInt(4, new Integer(2));
        VecInt vecf2 = new VecInt(2, new Integer(2));
        VecInt vecf3 = new VecInt(3, new Integer(3));
        assertEquals(nvec, vect);
        assertFalse(nvec.equals(vecf));
        assertFalse(nvec.equals(vecf2));
        assertFalse(nvec.equals(vecf3));

    }

    public void testIteratorExample1() {
        IteratorInt it = myvec.iterator();
        assertFalse(it.hasNext());
    }

    public void testIteratorExample2() {
        for (int i = 0; i < 100; i++) {
        	myvec.push(new Integer(i));
        }
        IteratorInt it = myvec.iterator();
        assertTrue(it.hasNext());
    }
    
    public void testIteratorExample3() {
    	for (int i = 0; i < 5; i++) {
        	myvec.push(new Integer(i));
        }
    	
        IteratorInt it = myvec.iterator();
        int i= 0;
        while(it.hasNext()) {
        	assertEquals(i, it.next());
        	i++;
        }
        
        try {
        	it.next();
        } catch(NoSuchElementException e) {
        	
        }
    }
    
    public void testNoSuchElementException() {
        Vec<String> str = new Vec<String>();
        Iterator<String> it = str.iterator();
        assertFalse(it.hasNext());
        try {
            it.next();
            fail();
        } catch (NoSuchElementException e) {
            // ok
        }
    }
    
    /**
     * @author icewariya
     */
   
    public void testEmptyVectorMethods() {
    	int[] is= new int[5];
    	
    	VecInt.EMPTY.shrink(0);
    	VecInt.EMPTY.shrinkTo(0);
    	VecInt.EMPTY.growTo(0,5);
    	VecInt.EMPTY.ensure(0);
    	VecInt.EMPTY.clear();
    	VecInt.EMPTY.copyTo(is);
    	VecInt.EMPTY.moveTo(is);
    	VecInt.EMPTY.moveTo(myvec);
    	VecInt.EMPTY.moveTo2(myvec);
    	VecInt.EMPTY.sort();
    	VecInt.EMPTY.sortUnique();
    }
    
    public void testEmptyVectorSize() {
    	assertEquals(0, myvec.EMPTY.size());
    }
    
    public void testEmptyVectorPop() {
    	try {
    		VecInt.EMPTY.pop();
    	} catch(UnsupportedOperationException e) {
    		
    	}
    }
    
    public void testEmptyVectorPush() {
    	try {
    		VecInt.EMPTY.push(new Integer(2));
    	} catch(UnsupportedOperationException e) {
    		
    	}
    }
    
    public void testEmptyVectorUnsafePush() {
    	try {
    		VecInt.EMPTY.unsafePush(2);
    	} catch(UnsupportedOperationException e) {
    		
    	}
    }
    
    public void testEmptyVectorLast() {
    	try {
    		VecInt.EMPTY.last();
    	} catch(UnsupportedOperationException e) {
    		
    	}
    }
    
    public void testEmptyVectorGet() {
    	try {
    		VecInt.EMPTY.get(2);
    	} catch(UnsupportedOperationException e) {
    		
    	}
    }
    
    public void testEmptyVectorUnsafeGet() {
    	try {
    		VecInt.EMPTY.unsafeGet(2);
    	} catch(UnsupportedOperationException e) {
    		
    	}
    }
    
    public void testEmptyVectorSet() {
    	try {
    		VecInt.EMPTY.set(2, 6);
    	} catch(UnsupportedOperationException e) {
    		
    	}
    }
    
    public void testEmptyVectorInsertFirst() {
    	try {
    		VecInt.EMPTY.insertFirst(2);
    	} catch(UnsupportedOperationException e) {
    		
    	}
    }
    
    public void testEmptyVectorRemove() {
    	try {
    		VecInt.EMPTY.remove(2);
    	} catch(UnsupportedOperationException e) {
    		
    	}
    }
    
    public void testEmptyVectorDelete() {
    	try {
    		VecInt.EMPTY.delete(2);
    	} catch(UnsupportedOperationException e) {
    		
    	}
    }
    
    public void testEmptyVectorContains() {
    	assertFalse(VecInt.EMPTY.contains(4));
    }
    
    public void testEmptyVectorContainsAt() {
    	try {
    		VecInt.EMPTY.containsAt(4);
    	} catch (UnsupportedOperationException e) {
    		
    	}
    }
    
    public void testEmptyVectorContainsAt2() {
    	try {
    		VecInt.EMPTY.containsAt(4,7);
    	} catch (UnsupportedOperationException e) {
    		
    	}
    }
    
    public void testEmptyVectorMoveTo() {
    	try {
    		VecInt.EMPTY.moveTo(4,7);
    	} catch (UnsupportedOperationException e) {
    		
    	}
    }
   
    public void testEmptyVectorIsEmpty() {
    	assertTrue(VecInt.EMPTY.isEmpty());
    }
    
    public void testEmptyVectorIterator() {
    	IteratorInt it = VecInt.EMPTY.iterator();
    	assertFalse(it.hasNext());
    	try {
    		it.next();
    	} catch(UnsupportedOperationException e) {
    		
    	}
    }
    
    public void testEmptyVectortoArray() {
    	try {
    		VecInt.EMPTY.toArray();
    	} catch(UnsupportedOperationException e) {
    		
    	}
    }
    
    public void testEmptyVectorIndexOf() {
    	assertEquals(-1, VecInt.EMPTY.indexOf(4));
    }
    
    public void testEmptyVectorToString() {
    	assertEquals("[]", VecInt.EMPTY.toString());
    }
    
    public void testEmptyVectorMoveTo2() {
    	int[] dest = new int[3];
    	try {
    		VecInt.EMPTY.moveTo(0, dest);
    	} catch(UnsupportedOperationException e) {
    		
    	}
    }
    
    public void testEmptyVectorSubset() {
    	IVecInt[] expected = VecInt.EMPTY.subset(2);
    	assertEquals(0, expected.length);
    }
    
    public void testEmptyVectorEquals() {
    	IVecInt[] expected = VecInt.EMPTY.subset(2);
    	assertFalse(VecInt.EMPTY.equals(expected));
    	assertTrue(VecInt.EMPTY.equals(VecInt.EMPTY));
    }
   
    public void testEmptyVectorHashCode() {
    	assertEquals(0, VecInt.EMPTY.hashCode());
    }
    
    public void testVecArray() {
        for (int i = 0; i < 15; i++) {
            this.myvec.push(new Integer(i));
        }
        int[] destination = new int[15];
        this.myvec.copyTo(destination);
        
        VecInt vec = new VecInt(destination);
        
        assertEquals(15, vec.size());
    }
    
    public void testUnsafePush() {
        assertEquals(0, this.myvec.size());
        for (int i = 0; i < 5; i++) {
            this.myvec.unsafePush(new Integer(0));
        }
        assertEquals(5, this.myvec.size());
        assertEquals(new Integer(0), (Integer) this.myvec.last());
    }
    
    public void testIsEmpty() {
    	assertTrue(this.myvec.isEmpty());
    }
    
    public void testIsNotEmpty() {
    	for (int i = 0; i < 5; i++) {
            this.myvec.push(new Integer(0));
        }
    	assertFalse(this.myvec.isEmpty());
    }
    
    
    public void testInsertFirstNE(){
    	for (int i = 0; i < 10; i++) {
            this.myvec.push(new Integer(0));
        }   	
    	this.myvec.insertFirst(new Integer(1));
    	assertEquals(new Integer(1), (Integer) this.myvec.get(0));
    }
    
    public void testInsertFirstEmpty() {
    	this.myvec.insertFirst(new Integer(0));
    	assertEquals(new Integer(0), (Integer) this.myvec.get(0));
    }
    
    public void testSetExample1() {
    	for (int i = 0; i < 10; i++) {
            this.myvec.push(new Integer(0));
        } 
    	
    	this.myvec.set(5, new Integer(5));
    	assertEquals(new Integer(5), (Integer) this.myvec.get(5));
    }
    
    
    public void testSetExample2() {
    	this.myvec.set(0, new Integer(0));
    	assertEquals(new Integer(0), (Integer) this.myvec.get(0));
    }
    
    public void testSetExample3() {
    	for (int i = 0; i < 10; i++) {
            this.myvec.push(new Integer(0));
        } 
    	
    	this.myvec.set(12, new Integer(5));
    	assertEquals(new Integer(0), (Integer) this.myvec.get(0));
    }
    
    public void testRemoveInvalid() {
        IVec<Integer> nvec = new Vec<Integer>();
        for (int i = 0; i < 100; i++) {
            nvec.push(new Integer(i));
        }
        Integer toRemove = new Integer(200);
        try {
        	nvec.remove(toRemove);
        } catch(NoSuchElementException ex) {
        	
        }
    }
    
    public void testCopyToDest() {
        for (int i = 0; i < 15; i++) {
            this.myvec.push(new Integer(i));
        }
        int[] destination = new int[15];
        this.myvec.copyTo(destination);
        assertEquals(15, destination.length);
        assertEquals(15, this.myvec.size());
        assertEquals(this.myvec.last(), destination[14]);

    }
    
    public void testMoveToSrcDest() {
    	 for (int i = 0; i < 15; i++) {
             this.myvec.push(new Integer(i));
         }
        this.myvec.growTo(5, new Integer(15));
        this.myvec.moveTo(0, 5);
        this.myvec.moveTo(0, 0);
        
        assertEquals(20, this.myvec.size());
    }
    
    public void testToArray() {
    	for (int i = 0; i < 15; i++) {
            this.myvec.push(new Integer(i));
        }
    int[] expected = this.myvec.toArray();
    
    assertEquals(26, expected.length);
    }
    
    public void testToStringExample1() {
    	for (int i = 0; i < 15; i++) {
            this.myvec.push(new Integer(i));
        }
    String expected = this.myvec.toString();
    
    assertEquals(34, expected.length());
    }
    
    public void testToStringExample2() {
    String expected = this.myvec.toString();
    
    assertEquals(0, expected.length());
    }
    
    public void testSortUniqueEmpty() {
        this.myvec.sortUnique();
        assertEquals(0, this.myvec.size());

    }
    
    public void testNotEquals() {
        VecInt nvec = new VecInt(3, new Integer(2));
        Integer[] destination = new Integer[15];

        assertFalse(nvec.equals(destination));

    }
    
    public void testHashCode() {
    	for (int i = 0; i < 15; i++) {
            this.myvec.push(new Integer(i));
        }
    	
    	assertEquals(7, this.myvec.hashCode());
    }
    
    public void testIteratorRemove() {
        Vec<String> str = new Vec<String>();
        str.push("titi");
        str.push("toto");
        str.push("tata");
        Iterator<String> it = str.iterator();
        try {
        	it.remove();
        } catch(UnsupportedOperationException ex) {
        	
        }
    }
    
    public void testContains() {
    	for (int i = 0; i < 15; i++) {
            this.myvec.push(new Integer(i));
        }
    	
    	assertTrue(this.myvec.contains(new Integer(5)));
    	assertFalse(this.myvec.contains(new Integer(100)));
    }
    
    public void testContainsAt() {
    	for (int i = 0; i < 15; i++) {
            this.myvec.push(new Integer(i));
        }
    	
    	assertEquals(5,this.myvec.containsAt(5));
    	assertEquals(-1, this.myvec.containsAt(100));
    }
    
    public void testIndexOf() {
    	for (int i = 0; i < 15; i++) {
            this.myvec.push(new Integer(i));
        }
    	
    	assertEquals(14, this.myvec.indexOf(new Integer(14)));
    	assertEquals(-1, this.myvec.indexOf(new Integer(100)));
    }
    
    public void testMoveTo2() {
    	VecInt nvec = new VecInt();
        this.myvec.growTo(15, new Integer(15));
        this.myvec.moveTo2(nvec);
        assertEquals(15, nvec.size());
        assertEquals(0, this.myvec.size());
        assertEquals(new Integer(15), (Integer) nvec.last());
    }
    
    public void testMoveToExample2() {
    	int[] nvec = new int[15];
        this.myvec.growTo(15, new Integer(15));
        this.myvec.moveTo(nvec);
        assertEquals(15, nvec.length);
        assertEquals(0, this.myvec.size());
        assertEquals(new Integer(15), (Integer) nvec[14]);
    }
    
    public void testMoveToExample3() {
    	int[] nvec = new int[9];
        this.myvec.growTo(15, new Integer(15));
        this.myvec.moveTo(6, nvec);
        assertEquals(9, nvec.length);
        assertEquals(0, this.myvec.size());
        assertEquals(new Integer(15), (Integer) nvec[8]);
    }
   
    public void testPushAll() {
    	for (int i = 0; i < 15; i++) {
            this.myvec.push(new Integer(i));
        }
    	
    	VecInt copy = new VecInt();
    	for (int i = 15; i < 30; i++) {
            copy.push(new Integer(i));
        }
    	
    	this.myvec.pushAll(copy);
        assertEquals(30, myvec.size());

    }
    
    
    public void testIsSubsetOf() {
    	for (int i = 0; i < 15; i++) {
            this.myvec.push(new Integer(i));
        }
    	
    	VecInt notSubset = new VecInt();
    	for (int i = 15; i < 30; i++) {
    		notSubset.push(new Integer(i));
        }
    	
    	VecInt subset = new VecInt();
    	for (int i = 10; i < 15; i++) {
    		subset.push(new Integer(i));
        }
        
    	assertTrue(subset.isSubsetOf(myvec));
    	assertFalse(notSubset.isSubsetOf(myvec));
    }
    
    public void testSubset() {
    	for (int i = 0; i < 30; i++) {
            this.myvec.push(new Integer(i));
        }
        
    	IVecInt[] myVect = myvec.subset(4);
    	
    	assertEquals(0, myVect[0].get(0));
    }
}
