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
public class TagAtomTest {

	@Test
	public void testTagAtomIdentifiers() throws Exception {
		String sample = "<b id=\"sample\">";
		String example = "<p>";
		TagAtom sampleAtom = new TagAtom(sample);
		TagAtom exampleAtom = new TagAtom(example);
		assertEquals("p", exampleAtom.getIdentifier());
		assertEquals("id=\"sample\"", sampleAtom.getInternalIdentifiers());
	}
	
	@Test
	public void testTagAtomHasInternalIdentifier() throws Exception {
		String sample = "<b id=\"sample\">";
		String example = "<p>";
		TagAtom sampleAtom = new TagAtom(sample);
		TagAtom exampleAtom = new TagAtom(example);
		assertTrue(sampleAtom.hasInternalIdentifiers());
		assertFalse(exampleAtom.hasInternalIdentifiers());
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testTagAtomException() throws Exception {
		String example = "<p><b>Hello</b></p>";
		try {
			new TagAtom(example);
		} catch(IllegalArgumentException ex) {
			assertEquals("The given string is not a valid tag", ex.getMessage());
			throw ex;
		}
	}
	
	@Test
	public void testTagAtomGetFullText() throws Exception {
		String sample = "<b id=\"sample\">";
		String example = "<p>";
		TagAtom sampleAtom = new TagAtom(sample);
		TagAtom exampleAtom = new TagAtom(example);
		assertEquals(sample, sampleAtom.getFullText());
		assertEquals(example, exampleAtom.getFullText());
	}
	

	@Test
	public void testToString() throws Exception {
		String sample = "<b id=\"sample\">";
		String output = "TagAtom: <b id=\"sample\">";
		TagAtom sampleAtom = new TagAtom(sample);
		assertEquals(output, sampleAtom.toString());
		
	}
	
	@Test
	public void testEqualsIdentifier() throws Exception {
		String sample = "<b id=\"sample\">";
		String example = "<p>";
		TagAtom sampleAtom = new TagAtom(sample);
		TagAtom exampleAtom = new TagAtom(example);
		assertFalse(sampleAtom.equalsIdentifier(exampleAtom));
		assertTrue(exampleAtom.equalsIdentifier(exampleAtom));
		
	}
}
