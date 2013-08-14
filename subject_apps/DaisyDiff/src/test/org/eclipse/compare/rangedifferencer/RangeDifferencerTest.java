package org.eclipse.compare.rangedifferencer;

import static org.junit.Assert.assertEquals;

import org.eclipse.compare.internal.LCSSettings;
import org.eclipse.core.runtime.IProgressMonitor;
import org.junit.Test;
import org.outerj.daisy.diff.tag.TagComparator;

/**
 * 
 * @author icewariya
 *
 */
public class RangeDifferencerTest {
	@Test
    public void testFindDifferenceExample1() throws Exception
    {
		String oldText = "<p> This is a green book about food</p>";
		String newText = "<p> This is a <b>big</b> blue book</p>";
		TagComparator left = new TagComparator(oldText);
		TagComparator right = new TagComparator(newText);
		
		RangeDifference[] diff = RangeDifferencer.findDifferences(left, right);
		assertEquals(3, diff.length);
		assertEquals("Left: (8, 1) Right: (8, 3)", diff[0].toString());
		assertEquals("Left: (10, 0) Right: (12, 2)", diff[1].toString());
    }
	
	@Test
    public void testFindDifferenceExample2() throws Exception
    {
		String oldText = "<p> This is a green book about food</p>";
		String newText = "<p> This is a <b>big</b> blue book</p>";
		TagComparator left = new TagComparator(oldText);
		TagComparator right = new TagComparator(newText);
		LCSSettings lcs = new LCSSettings();
		
		RangeDifference[] diff = RangeDifferencer.findDifferences(lcs, left, right);
		assertEquals(3, diff.length);
		assertEquals("Left: (8, 1) Right: (8, 3)", diff[0].toString());
		assertEquals("Left: (10, 0) Right: (12, 2)", diff[1].toString());
    }
	
	@Test
    public void testFindDifferenceExample3() throws Exception
    {
		String oldText = "<p> This is a green book about food</p>";
		String newText = "<p> This is a <b>big</b> blue book</p>";
		TagComparator left = new TagComparator(oldText);
		TagComparator right = new TagComparator(newText);
		LCSSettings lcs = new LCSSettings();
		lcs.setUseGreedyMethod(true);
		
		RangeDifference[] diff = RangeDifferencer.findDifferences(null, lcs, left, right);
		assertEquals(2, diff.length);
		assertEquals("Left: (8, 1) Right: (8, 5)", diff[0].toString());
		assertEquals("Left: (11, 4) Right: (15, 0)", diff[1].toString());
    }
	
	@Test
    public void testFindDifferenceExample4() throws Exception
    {
		String ancestor = "<p> This is a book </p>";
		String oldText = "<p> This is a green book about food</p>";
		String newText = "<p> This is a <b>big</b> blue book</p>";
		
		TagComparator left = new TagComparator(oldText);
		TagComparator right = new TagComparator(newText);
		TagComparator ancestorTag = new TagComparator(ancestor);
		
		LCSSettings lcs = new LCSSettings();
		lcs.setUseGreedyMethod(true);
		
		RangeDifference[] diff = RangeDifferencer.findDifferences(lcs, ancestorTag, left, right);
		assertEquals(2, diff.length);
		assertEquals("Left: (8, 2) Right: (8, 6) Ancestor: (8, 0)", diff[0].toString());
		assertEquals("Left: (11, 4) Right: (15, 0) Ancestor: (9, 1)", diff[1].toString());
    }
	
	@Test
    public void testFindDifferenceExample5() throws Exception
    {
		String oldText = "<p> This is a <b>big</b> blue book</p>";
		String newText = "<p> This is a <b>big</b> blue book</p>";
		
		TagComparator left = new TagComparator(oldText);
		TagComparator right = new TagComparator(newText);
		
		LCSSettings lcs = new LCSSettings();
		lcs.setUseGreedyMethod(true);
		
		RangeDifference[] diff = RangeDifferencer.findDifferences(null, lcs, null, left, right);
		assertEquals(0, diff.length);
    }
	
	@Test
    public void testFindDifferenceExample6() throws Exception
    {
		String oldText = "<p> This is a green book about food</p>";
		String newText = "<p> This is a <b>big</b> blue book</p>";
		
		TagComparator left = new TagComparator(oldText);
		TagComparator right = new TagComparator(newText);
		
		LCSSettings lcs = new LCSSettings();
		lcs.setUseGreedyMethod(true);
		
		RangeDifference[] diff = RangeDifferencer.findRanges(lcs, left, right);
		assertEquals(5, diff.length);
		assertEquals("Left: (0, 8) Right: (0, 8)", diff[0].toString());
		assertEquals("Left: (8, 1) Right: (8, 5)", diff[1].toString());
    }
	
	@Test
    public void testFindDifferenceExample7() throws Exception
    {
		String oldText = "<p> This is a green book about food</p>";
		String newText = "<p> This is a <b>big</b> blue book</p>";
		
		TagComparator left = new TagComparator(oldText);
		TagComparator right = new TagComparator(newText);
		
		LCSSettings lcs = new LCSSettings();
		lcs.setUseGreedyMethod(true);
		
		RangeDifference[] diff = RangeDifferencer.findRanges(null, lcs, left, right);
		assertEquals(5, diff.length);
		assertEquals("Left: (0, 8) Right: (0, 8)", diff[0].toString());
		assertEquals("Left: (8, 1) Right: (8, 5)", diff[1].toString());
    }
	
	@Test
    public void testFindDifferenceExample8() throws Exception
    {
		String ancestor = "<p> This is a book </p>";
		String oldText = "<p> This is a green book about food</p>";
		String newText = "<p> This is a <b>big</b> blue book</p>";
		
		TagComparator left = new TagComparator(oldText);
		TagComparator right = new TagComparator(newText);
		TagComparator ancestorTag = new TagComparator(ancestor);
		
		LCSSettings lcs = new LCSSettings();
		lcs.setUseGreedyMethod(true);
		
		RangeDifference[] diff = RangeDifferencer.findRanges(lcs, ancestorTag, left, right);
		assertEquals(5, diff.length);
		assertEquals("Left: (0, 8) Right: (0, 8) Ancestor: (0, 8)", diff[0].toString());
		assertEquals("Left: (8, 2) Right: (8, 6) Ancestor: (8, 0)", diff[1].toString());
    }
	
	@Test
    public void testFindDifferenceExample9() throws Exception
    {
		String oldText = "<p> This is a green book about food</p>";
		String newText = "<p> This is a <b>big</b> blue book</p>";
		
		TagComparator left = new TagComparator(oldText);
		TagComparator right = new TagComparator(newText);
		
		LCSSettings lcs = new LCSSettings();
		lcs.setUseGreedyMethod(true);
		
		RangeDifference[] diff = RangeDifferencer.findRanges(null, lcs, null, left, right);
		assertEquals(5, diff.length);
		assertEquals("Left: (0, 8) Right: (0, 8)", diff[0].toString());
		assertEquals("Left: (8, 1) Right: (8, 5)", diff[1].toString());
    }
}
