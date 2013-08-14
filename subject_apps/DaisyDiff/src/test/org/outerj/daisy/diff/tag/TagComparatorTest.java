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

/**
 * @author icewariya
 */
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TagComparatorTest {
	@Test
	public void testGenerateAtoms() throws Exception {
		String input = "<p>This is a blue book</p>";
		TagComparator comparator = new TagComparator(input);
		assertEquals("TagAtom: <p>", comparator.getAtom(0).toString());
		assertEquals("DelimiterAtom:  ", comparator.getAtom(8).toString());
		assertEquals("TextAtom: book", comparator.getAtom(9).toString());
		assertEquals("TagAtom: </p>", comparator.getAtom(10).toString());
	}
	
	@Test(expected = NullPointerException.class)
	public void testGenerateAtomsNull() throws Exception {
		String input = null;
		TagComparator comparator = new TagComparator(input);
		try {
			comparator.getAtom(4).toString();
		} catch (NullPointerException ex) {
			throw ex;
		}	
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGenerateAtomsIndexOutOfBounds() throws Exception {
		String input = "<p> This is a blue book</p>";
		TagComparator comparator = new TagComparator(input);
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
		TagComparator comparator = new TagComparator(input);
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
		TagComparator comparator = new TagComparator(input);
		assertEquals(11, comparator.getRangeCount());
	}
	
	@Test
	public void testGetRangeCountZero() throws Exception {
		String input = new String();
		TagComparator comparator = new TagComparator(input);
		assertEquals(0, comparator.getRangeCount());
	}
	
	@Test(expected = NullPointerException.class)
	public void testGetRangeCountNull() throws Exception {
		String input = null;
		TagComparator comparator = new TagComparator(input);
		try {
			comparator.getRangeCount();
		} catch (NullPointerException ex) {
			throw ex;
		}	
	}
	
	@Test
	public void testGetSubstring() throws Exception {
		String input = "<p>This is a blue book</p>";
		TagComparator comparator = new TagComparator(input);
		assertEquals("a blue book</p>", comparator.substring(5));
		assertEquals("This is a blue ", comparator.substring(1,9));
		assertEquals("", comparator.substring(9,9));
	}
	
	@Test
	public void testRangesEqual() throws Exception {
		String input = "<p>This is a blue book</p>";
		TagComparator comparator = new TagComparator(input);
		assertEquals(false, comparator.rangesEqual(0, comparator, 10));
		assertEquals(false, comparator.rangesEqual(5, comparator, 10));
		assertEquals(true, comparator.rangesEqual(5, comparator, 5));
	}
	
	@Test
	public void testSkipRangeComparison() throws Exception {
		String input = "<p>This is a blue book</p>";
		TagComparator comparator = new TagComparator(input);
		assertEquals(false, comparator.skipRangeComparison(0, 5, comparator));
	}

    

}
