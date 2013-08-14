package org.eclipse.compare.rangedifferencer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.outerj.daisy.diff.tag.TagComparator;

/**
 * 
 * @author icewariya
 *
 */
public class DifferencesIteratorTest {
	
	private RangeDifference[] getDifferences() {
		
		String oldText = "<p> This is a blue book</p>";
		String newText = "<p> This is a <b>big</b> blue book</p>";
		TagComparator oldComp = new TagComparator(oldText);
		TagComparator newComp = new TagComparator(newText);
		
		return RangeDifferencer.findDifferences(oldComp, newComp);
	}
	@Test
    public void testDifferencesIterator() throws Exception
    {
		RangeDifference[] differenceRanges = getDifferences();
		DifferencesIterator iterator = new DifferencesIterator(differenceRanges);
		
		assertEquals("Left: (8, 0) Right: (8, 4)",differenceRanges[0].toString());
		assertEquals(1, iterator.fIndex);
    }
	
	@Test
    public void testDifferencesIteratorEmpty() throws Exception
    {
		RangeDifference[] differenceRanges = new RangeDifference[0];
		DifferencesIterator iterator = new DifferencesIterator(differenceRanges);
		
		assertEquals(0, iterator.fIndex);
    }
	
	@Test
    public void testGetCount() throws Exception
    {
		RangeDifference[] differenceRanges = getDifferences();
		DifferencesIterator iterator = new DifferencesIterator(differenceRanges);

		assertEquals(0, iterator.getCount());
    }
	
	@Test
    public void testGetCountEmpty() throws Exception
    {
		RangeDifference[] differenceRanges = new RangeDifference[0];
		DifferencesIterator iterator = new DifferencesIterator(differenceRanges);
		
		assertEquals(0, iterator.getCount());
    }
	
	@Test
    public void testNext() throws Exception
    {
		RangeDifference[] differenceRanges = getDifferences();
		DifferencesIterator iterator = new DifferencesIterator(differenceRanges);
		
		assertEquals("Left: (8, 0) Right: (8, 4)",differenceRanges[0].toString());
		
		String oldText = "<p> This is a green book about food</p>";
		String newText = "<p> This is a <b>big</b> blue book</p>";
		TagComparator oldComp = new TagComparator(oldText);
		TagComparator newComp = new TagComparator(newText);
		
		iterator.next();
		differenceRanges = RangeDifferencer.findDifferences(oldComp, newComp);
		iterator = new DifferencesIterator(differenceRanges);
		iterator.next();
		
		assertEquals("Left: (8, 1) Right: (8, 3)",differenceRanges[0].toString());
		
    }
	
	@Test
    public void testNextNull() throws Exception
    {
		RangeDifference[] differenceRanges = getDifferences();
		DifferencesIterator iterator = new DifferencesIterator(differenceRanges);
		
		assertEquals("Left: (8, 0) Right: (8, 4)",differenceRanges[0].toString());
		
		iterator.next();
		differenceRanges = new RangeDifference[0];
		iterator = new DifferencesIterator(differenceRanges);
		iterator.next();
		
		assertEquals(1, iterator.getCount());
		
    }
	
	@Test
    public void testOther() throws Exception
    {
		RangeDifference[] differenceRanges = getDifferences();
		DifferencesIterator left = new DifferencesIterator(differenceRanges);
		DifferencesIterator right = new DifferencesIterator(differenceRanges);
		
		assertEquals(right, left.other(right, left));
		assertEquals(left, right.other(right, left));
    }
	
	@Test
    public void testRemoveAll() throws Exception
    {
		RangeDifference[] differenceRanges = getDifferences();
		DifferencesIterator iterator = new DifferencesIterator(differenceRanges);

		iterator.removeAll();
		
		assertEquals(0, iterator.getCount());
    }
	
	@Test
    public void testRemoveAllEmpty() throws Exception
    {
		RangeDifference[] differenceRanges = new RangeDifference[0];
		DifferencesIterator iterator = new DifferencesIterator(differenceRanges);
		
		iterator.removeAll();
		
		assertEquals(0, iterator.getCount());
    }
}
