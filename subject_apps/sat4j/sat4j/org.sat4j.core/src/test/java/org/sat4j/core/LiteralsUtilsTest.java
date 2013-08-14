package org.sat4j.core;

import java.lang.reflect.Constructor;

import org.sat4j.Messages;

import junit.framework.TestCase;

public class LiteralsUtilsTest extends TestCase{
	
    public LiteralsUtilsTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
	public void testPrivateConstructor() throws Exception{
		  Constructor constructor = LiteralsUtils.class.getDeclaredConstructor();
		  constructor.setAccessible(true);
		  assertEquals(LiteralsUtils.class, constructor.newInstance().getClass());
		}
	
	public void testVarExample1() {
		assertEquals(2, LiteralsUtils.var(5));
	}
	
	public void testVarExample2() {
		assertEquals(0, LiteralsUtils.var(0));
	}

	public void testVarExample3() {
		assertEquals(-3, LiteralsUtils.var(-6));
	}
	
	public void testNegExample1() {
		assertEquals(4, LiteralsUtils.neg(5));
	}
	
	public void testNegExample2() {
		assertEquals(1, LiteralsUtils.neg(0));
	}
	
	public void testNegExample3() {
		assertEquals(-4, LiteralsUtils.neg(-3));
	}
	
	public void testPosLitExample1() {
		assertEquals(10, LiteralsUtils.posLit(5));
	}
	
	public void testPosLitExample2() {
		assertEquals(0, LiteralsUtils.posLit(0));
	}
	
	public void testPosLitExample3() {
		assertEquals(-4, LiteralsUtils.posLit(-2));
	}
	
	public void testNegLitExample1() {
		assertEquals(11, LiteralsUtils.negLit(5));
	}
	
	public void testNegLitExample2() {
		assertEquals(1, LiteralsUtils.negLit(0));
	}
	
	public void testNegLitExample3() {
		assertEquals(-3, LiteralsUtils.negLit(-2));
	}
	
	public void testToDimacsExample1() {
		assertEquals(-2, LiteralsUtils.toDimacs(5));
	}
	
	public void testToDimacsExample2() {
		assertEquals(0, LiteralsUtils.toDimacs(0));
	}
	
	public void testToDimacsExample3() {
		assertEquals(-1, LiteralsUtils.toDimacs(-2));
	}
	
	public void testToInternalExample1() {
		assertEquals(10, LiteralsUtils.toInternal(5));
	}
	
	public void testToInternalExample2() {
		assertEquals(0, LiteralsUtils.toInternal(0));
	}
	
	public void testToInternalExample3() {
		assertEquals(5, LiteralsUtils.toInternal(-2));
	}
}
