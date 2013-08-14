package org.eclipse.compare.rangedifferencer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * 
 * @author icewariya
 *
 */
public class RangeDifferenceTest {
	@Test
    public void testRangeDifferenceExample1() throws Exception
    {
		RangeDifference difference = new RangeDifference(RangeDifference.LEFT);
		assertEquals(3, difference.fKind);
    }
	
	@Test
    public void testRangeDifferenceExample2() throws Exception
    {
		RangeDifference difference = new RangeDifference(RangeDifference.RIGHT, 0, 12, 0, 16);
		
		assertEquals(2, difference.fKind);
		assertEquals(16, difference.fLeftLength);
		assertEquals(0, difference.fLeftStart);
		assertEquals(12, difference.fRightLength);
		assertEquals(0, difference.fRightStart);
    }
	
	@Test
    public void testRangeDifferenceExample3() throws Exception
    {
		RangeDifference difference = new RangeDifference(RangeDifference.CONFLICT, 0, 12, 0, 16, 0, 0);
		
		assertEquals(1, difference.fKind);
		assertEquals(16, difference.fLeftLength);
		assertEquals(0, difference.fLeftStart);
		assertEquals(12, difference.fRightLength);
		assertEquals(0, difference.fRightStart);
		assertEquals(0, difference.lAncestorStart);
		assertEquals(0, difference.lAncestorLength);
    }
	
	@Test
    public void testKind() throws Exception
    {
		RangeDifference difference = new RangeDifference(RangeDifference.ANCESTOR);
	
		assertEquals(4, difference.kind());
    }
	
	@Test
    public void testAncestorStart() throws Exception
    {
		RangeDifference difference = new RangeDifference(RangeDifference.NOCHANGE, 0, 12, 0, 16, 10, 0);
	
		assertEquals(10, difference.ancestorStart());
    }
	
	@Test
    public void testAncestorLength() throws Exception
    {
		RangeDifference difference = new RangeDifference(RangeDifference.CHANGE, 0, 12, 0, 16, 10, 32);
	
		assertEquals(32, difference.ancestorLength());
    }
	
	@Test
    public void testAncestorEnd() throws Exception
    {
		RangeDifference difference = new RangeDifference(RangeDifference.ERROR, 0, 12, 0, 16, 10, 32);
	
		assertEquals(42, difference.ancestorEnd());
    }
	
	@Test
    public void testRightStart() throws Exception
    {
		RangeDifference difference = new RangeDifference(RangeDifference.LEFT, 0, 12, 0, 16, 10, 32);
	
		assertEquals(0, difference.rightStart());
    }
	
	@Test
    public void testRightLength() throws Exception
    {
		RangeDifference difference = new RangeDifference(RangeDifference.RIGHT, 0, 12, 0, 16, 10, 32);
	
		assertEquals(12, difference.rightLength());
    }
	
	@Test
    public void testRightEnd() throws Exception
    {
		RangeDifference difference = new RangeDifference(RangeDifference.CONFLICT, 0, 12, 0, 16, 10, 32);
	
		assertEquals(12, difference.rightEnd());
    }
	
	@Test
    public void testLeftStart() throws Exception
    {
		RangeDifference difference = new RangeDifference(RangeDifference.ERROR, 0, 12, 0, 16, 10, 32);
	
		assertEquals(0, difference.leftStart());
    }
	
	@Test
    public void testLeftLength() throws Exception
    {
		RangeDifference difference = new RangeDifference(RangeDifference.NOCHANGE, 0, 12, 0, 16, 10, 32);
	
		assertEquals(16, difference.leftLength());
    }
	
	@Test
    public void testLeftEnd() throws Exception
    {
		RangeDifference difference = new RangeDifference(RangeDifference.CHANGE, 0, 12, 0, 16, 10, 32);
	
		assertEquals(16, difference.leftEnd());
    }
	
	@Test
    public void testMaxLength() throws Exception
    {
		RangeDifference difference = new RangeDifference(RangeDifference.CHANGE, 0, 12, 0, 16, 10, 32);
	
		assertEquals(32, difference.maxLength());
    }
	
	@Test
    public void testEquals() throws Exception
    {
		RangeDifference difference1 = new RangeDifference(RangeDifference.CHANGE, 0, 12, 0, 16, 10, 32);
		RangeDifference difference2 = new RangeDifference(RangeDifference.CONFLICT, 1, 10, 2, 26, 10, 3);
		
		assertFalse(difference1.equals(difference2));
		assertTrue(difference1.equals(difference1));
    }
	
	@Test
    public void testToString() throws Exception
    {
		RangeDifference difference1 = new RangeDifference(RangeDifference.CHANGE, 0, 12, 0, 16, 10, 32);
		RangeDifference difference2 = new RangeDifference(RangeDifference.ANCESTOR, 0, 12, 0, 16, 0, 10);
		RangeDifference difference3 = new RangeDifference(RangeDifference.CONFLICT, 0, 12, 0, 16, 0, 0);
		
		assertEquals("Left: (0, 16) Right: (0, 12) Ancestor: (10, 32)",difference1.toString());
		assertEquals("Left: (0, 16) Right: (0, 12) Ancestor: (0, 10)",difference2.toString());
		assertEquals("Left: (0, 16) Right: (0, 12)",difference3.toString());
    }
}
