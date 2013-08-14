package org.eclipse.compare.rangedifferencer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.compare.internal.CompareMessages;
import org.eclipse.compare.internal.LCSSettings;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubMonitor;
import org.junit.Test;
import org.outerj.daisy.diff.tag.TagComparator;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author icewariya
 */
public class RangeComparatorLCSTest {
	@Test
    public void testDifferencesIterator() throws Exception
    {
		String oldText = "<p> This is a blue book</p>";
		String newText = "<p> This is a <b>big</b> blue book</p>";
		TagComparator left = new TagComparator(oldText);
		TagComparator right = new TagComparator(newText);
		LCSSettings settings = new LCSSettings();
		
		RangeDifference[] rangeDifference = RangeComparatorLCS.findDifferences(null, settings, left, right);
		assertEquals("Left: (8, 0) Right: (8, 4)", rangeDifference[0].toString()); 
    }
	
	@Test
    public void testLength() throws Exception
    {
		String oldText = "<p> This is a blue book</p>";
		String newText = "<p> This is a <b>big</b> blue book</p>";
		TagComparator left = new TagComparator(oldText);
		TagComparator right = new TagComparator(newText);
		
		RangeComparatorLCS comp = new RangeComparatorLCS(left, right);
		assertEquals(0, comp.getLength());
		assertEquals(12, comp.getLength1());
		assertEquals(16, comp.getLength2());
    }
	
	@Test
    public void testLengthEmptyExample1() throws Exception
    {
		String oldText = "";
		String newText = "<p> This is a <b>big</b> blue book</p>";
		TagComparator left = new TagComparator(oldText);
		TagComparator right = new TagComparator(newText);
		
		RangeComparatorLCS comp = new RangeComparatorLCS(left, right);
		assertEquals(0, comp.getLength());
		assertEquals(0, comp.getLength1());
		assertEquals(16, comp.getLength2());
    }
	
	@Test
    public void testLengthEmptyExample2() throws Exception
    {
		String oldText = "";
		String newText = "";
		TagComparator left = new TagComparator(oldText);
		TagComparator right = new TagComparator(newText);
		
		RangeComparatorLCS comp = new RangeComparatorLCS(left, right);
		assertEquals(0, comp.getLength());
		assertEquals(0, comp.getLength1());
		assertEquals(0, comp.getLength2());
    }
	
	@Test
    public void testLengthEmptyExample3() throws Exception
    {
		String oldText = "<p> This is a blue book</p>";
		String newText = "";
		TagComparator left = new TagComparator(oldText);
		TagComparator right = new TagComparator(newText);
		
		RangeComparatorLCS comp = new RangeComparatorLCS(left, right);
		assertEquals(0, comp.getLength());
		assertEquals(12, comp.getLength1());
		assertEquals(0, comp.getLength2());
    }
	
	@Test
    public void testInitializeLCS() throws Exception
    {
		String oldText = "<p> This is a blue book</p>";
		String newText = "<p> This is a <b>big</b> blue book</p>";
		TagComparator left = new TagComparator(oldText);
		TagComparator right = new TagComparator(newText);
		
		RangeComparatorLCS comp = new RangeComparatorLCS(left, right);
		comp.initializeLcs(20);
		
		//Reflection for accessing private variable
		// Retrieve the field with the specified name
		Field f = comp.getClass().getDeclaredField("lcs");

		// make sure the field is accessible, even if it
		// would be private or protected
		f.setAccessible( true );

		// Return the value of the field for the instance
		int[][] lcs	= (int[][]) f.get( comp );
		
		assertEquals(2, lcs.length);
		assertEquals(20, lcs[0].length);
		
    }
	
	@Test
    public void testInitializeLCSZero() throws Exception
    {
		String oldText = "<p> This is a blue book</p>";
		String newText = "<p> This is a <b>big</b> blue book</p>";
		TagComparator left = new TagComparator(oldText);
		TagComparator right = new TagComparator(newText);
		
		RangeComparatorLCS comp = new RangeComparatorLCS(left, right);
		comp.initializeLcs(0);
		
		//Reflection for accessing private variable
		// Retrieve the field with the specified name
		Field f = comp.getClass().getDeclaredField("lcs");

		// make sure the field is accessible, even if it
		// would be private or protected
		f.setAccessible( true );

		// Return the value of the field for the instance
		int[][] lcs	= (int[][]) f.get( comp );
		
		assertEquals(2, lcs.length);
		assertEquals(0, lcs[0].length);
		
    }
	
	@Test
    public void testIsRangeEqual() throws Exception
    {
		String oldText = "<p> This is a blue book</p>";
		String newText = "<p> This is a <b>big</b> blue book</p>";
		TagComparator left = new TagComparator(oldText);
		TagComparator right = new TagComparator(newText);
		
		RangeComparatorLCS comp = new RangeComparatorLCS(left, right);
		
		assertTrue(comp.isRangeEqual(0, 0));
		assertFalse(comp.isRangeEqual(0, 3));
		
    }
	
	@Test
    public void testGetDifferencesExample1() throws Exception
    {
		String oldText = "<p> This is a blue book</p> \n <div style=\"example\">This book is about food</div>";
		String newText = "<p> This is a <b>big</b> blue book</p>";
		TagComparator left = new TagComparator(oldText);
		TagComparator right = new TagComparator(newText);
		
		RangeComparatorLCS comp = new RangeComparatorLCS(left, right);
		SubMonitor monitor = SubMonitor.convert(null, CompareMessages.RangeComparatorLCS_0, 100);
		
		RangeDifference[] rangeDifference = comp.getDifferences(monitor);
		
		assertEquals(1,rangeDifference.length);
		assertEquals("Left: (0, 26) Right: (0, 16)",rangeDifference[0].toString());
		
    }
	
	@Test
    public void testGetDifferencesExample2() throws Exception
    {
		String oldText = "<div style=\"example\">This book is about food</div>";
		String newText = "<p> This is a <b>big</b> blue book</p>";
		TagComparator left = new TagComparator(oldText);
		TagComparator right = new TagComparator(newText);
		
		RangeComparatorLCS comp = new RangeComparatorLCS(left, right);
		SubMonitor monitor = SubMonitor.convert(null, CompareMessages.RangeComparatorLCS_0, 100);
		
		RangeDifference[] rangeDifference = comp.getDifferences(monitor);
		
		assertEquals(1,rangeDifference.length);
		assertEquals("Left: (0, 11) Right: (0, 16)",rangeDifference[0].toString());
		
    }
	
	@Test
    public void testGetDifferencesExample3() throws Exception
    {
		String newText = "<p> This is a <b>big</b> blue book</p>";
		TagComparator right = new TagComparator(newText);
		
		RangeComparatorLCS comp = new RangeComparatorLCS(right, right);
		SubMonitor monitor = SubMonitor.convert(null, CompareMessages.RangeComparatorLCS_0, 100);
		
		RangeDifference[] rangeDifference = comp.getDifferences(monitor);
		
		assertEquals(1,rangeDifference.length);
		assertEquals("Left: (0, 16) Right: (0, 16)",rangeDifference[0].toString());
		
    }
	
	@Test
    public void testWorked() throws Exception
    {
		String newText = "<p> This is a <b>big</b> blue book</p>";
		TagComparator right = new TagComparator(newText);
		
		RangeComparatorLCS comp = new RangeComparatorLCS(right, right);
		SubMonitor monitor = SubMonitor.convert(null, CompareMessages.RangeComparatorLCS_0, 100);
		
		Object[] params = new Object[2];
		params[0] = monitor;
		params[1] = 2;
		//Reflection to access private method
		Class[] types = new Class[2];
		types[0] = SubMonitor.class;
		types[1] = int.class;
		
		Method m = comp.getClass().getDeclaredMethod("worked", types);
		m.setAccessible( true );

		m.invoke(comp, params);
		
    }
	
	@Test(expected = Exception.class)
    public void testWorkedSubMonitorCancelled() throws Exception
    {
		String newText = "<p> This is a <b>big</b> blue book</p>";
		TagComparator right = new TagComparator(newText);
		
		RangeComparatorLCS comp = new RangeComparatorLCS(right, right);
		SubMonitor monitor = SubMonitor.convert(null, CompareMessages.RangeComparatorLCS_0, 100);
		monitor.setCanceled(true);
		
		Object[] params = new Object[2];
		params[0] = monitor;
		params[1] = 2;
		
		//Reflection to access private method
		Class[] types = new Class[2];
		types[0] = SubMonitor.class;
		types[1] = int.class;
		
		Method m = comp.getClass().getDeclaredMethod("worked", types);
		m.setAccessible( true );
		try {
			m.invoke(comp, params);
		} catch(Exception ex) {
			throw ex;
		}
		
    }
}
