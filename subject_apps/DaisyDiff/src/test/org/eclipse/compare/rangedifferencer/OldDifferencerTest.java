package org.eclipse.compare.rangedifferencer;

import static org.junit.Assert.assertEquals;

import org.eclipse.core.runtime.IProgressMonitor;
import org.junit.Test;
import org.outerj.daisy.diff.tag.TagComparator;

/**
 * 
 * @author icewariya
 *
 */
public class OldDifferencerTest {
	@Test
    public void testFindDifferencesExample1() throws Exception
    {
		String oldText = "<p> This is a blue book</p>";
		String newText = "<p> This is a <b>big</b> blue book</p>";
		TagComparator left = new TagComparator(oldText);
		TagComparator right = new TagComparator(newText);
		
		RangeDifference[] differenceRanges = OldDifferencer.findDifferences(null, left, right);;
		assertEquals("Left: (8, 0) Right: (8, 4)",differenceRanges[0].toString());
    }
	
	@Test
    public void testFindDifferencesExample2() throws Exception
    {
		String oldText = "<p> This is a blue book</p>";
		String newText = "<p> This is a <b>big</b> blue book</p>";
		TagComparator left = new TagComparator(oldText);
		TagComparator right = new TagComparator(newText);
		
		RangeDifference[] differenceRanges = OldDifferencer.findDifferences(null, right, left);
		assertEquals("Left: (8, 4) Right: (8, 0)",differenceRanges[0].toString());
    }
	
	@Test
    public void testFindDifferencesExample3() throws Exception
    {
		String oldText = "<p> This is a blue book</p>";
		TagComparator left = new TagComparator(oldText);
		TagComparator right = new TagComparator(oldText);
		
		RangeDifference[] differenceRanges = OldDifferencer.findDifferences(null, right, left);
		
		assertEquals(0, differenceRanges.length);
    }
		
}
