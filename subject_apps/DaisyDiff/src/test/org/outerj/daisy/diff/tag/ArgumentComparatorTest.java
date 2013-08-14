/*
 * Copyright 2007 Guy Van den Broeck
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.outerj.daisy.diff.tag;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author icewariya
 */

public class ArgumentComparatorTest {
	
	@Test
	public void testGenerateAtoms() throws Exception {
		String input = "<p>This is a blue book</p>";
		ArgumentComparator comparator = new ArgumentComparator(input);
		assertEquals("TextAtom: <", comparator.getAtom(0).toString());
		assertEquals("DelimiterAtom:  ", comparator.getAtom(8).toString());
		assertEquals("TextAtom: blue", comparator.getAtom(9).toString());
		assertEquals("TextAtom: >", comparator.getAtom(15).toString());
	}
	
	@Test(expected = NullPointerException.class)
	public void testGenerateAtomsNull() throws Exception {
		String input = null;
		ArgumentComparator comparator = new ArgumentComparator(input);
		try {
			comparator.getAtom(4).toString();
		} catch (NullPointerException ex) {
			throw ex;
		}	
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGenerateAtomsIndexOutOfBounds() throws Exception {
		String input = "<p> This is a blue book</p>";
		ArgumentComparator comparator = new ArgumentComparator(input);
		try {
			comparator.getAtom(20);
		} catch(IndexOutOfBoundsException ex) {
			assertEquals("There is no Atom with index 20", ex.getMessage());
			throw ex;
		}
	
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGenerateAtomsNegativeIndexOutOfBounds() throws Exception {
		String input = "<p> This is a blue book</p>";
		ArgumentComparator comparator = new ArgumentComparator(input);
		try {
			comparator.getAtom(-1);
		} catch(IndexOutOfBoundsException ex) {
			assertEquals("There is no Atom with index -1", ex.getMessage());
			throw ex;
		}
	
	}
	
	@Test
	public void testGetRangeCount() throws Exception {
		String input = "<p>This is a blue book</p>";
		ArgumentComparator comparator = new ArgumentComparator(input);
		assertEquals(16, comparator.getRangeCount());
	}
	
	@Test
	public void testGetRangeCountZero() throws Exception {
		String input = new String();
		ArgumentComparator comparator = new ArgumentComparator(input);
		assertEquals(0, comparator.getRangeCount());
	}
	
	@Test(expected = NullPointerException.class)
	public void testGetRangeCountNull() throws Exception {
		String input = null;
		ArgumentComparator comparator = new ArgumentComparator(input);
		try {
			comparator.getRangeCount();
		} catch (NullPointerException ex) {
			throw ex;
		}	
	}
	
	@Test
	public void testGetSubstring() throws Exception {
		String input = "<p>This is a blue book</p>";
		ArgumentComparator comparator = new ArgumentComparator(input);
		assertEquals("is a blue book</p>", comparator.substring(5));
		assertEquals("blue book", comparator.substring(9,12));
		assertEquals("", comparator.substring(9,9));
	}
	
	@Test
	public void testRangesEqual() throws Exception {
		String input = "<p>This is a blue book</p>";
		ArgumentComparator comparator = new ArgumentComparator(input);
		assertTrue(comparator.rangesEqual(0, comparator, 12));
		assertFalse(comparator.rangesEqual(5, comparator, 12));
		assertTrue(comparator.rangesEqual(5, comparator, 5));
	}
	
	@Test
	public void testSkipRangeComparison() throws Exception {
		String input = "<p>This is a blue book</p>";
		ArgumentComparator comparator = new ArgumentComparator(input);
		assertFalse(comparator.skipRangeComparison(0, 5, comparator));
	}

}
