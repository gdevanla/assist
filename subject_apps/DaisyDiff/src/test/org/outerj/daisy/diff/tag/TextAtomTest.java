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

import org.junit.Test;

/**
 * @author icewariya
 */
public class TextAtomTest {
	@Test
	public void testisValidAtom() throws Exception {
		String input = "<p>This is a blue book</p>";
		String empty = new String();
		String lengthOne = "(";
		
		TextAtom atom = new TextAtom(input);
		
		assertEquals(true, atom.isValidAtom(input));
		assertEquals(false, atom.isValidAtom(empty));
		assertEquals(true, atom.isValidAtom(lengthOne));

	}
	
	@Test
	public void testIsValidAtom() throws Exception {
		String input = "<p>This is a blue book</p>";
		String delimInput = "&";
		String empty = new String();
		String nullString = null;
		
		TextAtom atom = new TextAtom("' '");
		assertEquals(true, atom.isValidAtom(input));
		assertEquals(true, atom.isValidAtom(delimInput));
		assertEquals(false, atom.isValidAtom(empty));
		assertEquals(false, atom.isValidAtom(nullString));
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIsValidAtomException() throws Exception {
		try { 		
			new TextAtom(null);
		} catch(IllegalArgumentException ex) {
			assertEquals("The given String is not a valid Text Atom", ex.getMessage());
			throw ex;
		}
	}
	
	@Test
	public void testEqualsIdentifier() throws Exception {
		String input = "<p>This is a blue book</p>";
		Atom atom = new TextAtom(input);
		
		String matchInput = "<p>This is a blue book</p>";
		Atom matchAtom = new TextAtom(matchInput);
		
		String empty = " ";
		Atom emptyAtom = new TextAtom(empty);
		
		String differentInput = "<b>This is a different \n input</b>";
		Atom differentAtom = new TextAtom(differentInput);
		
		assertEquals(true, atom.equalsIdentifier(matchAtom));
		assertEquals(false, atom.equalsIdentifier(emptyAtom));
		assertEquals(false, matchAtom.equalsIdentifier(differentAtom));
		
	}
	
	@Test
	public void testToString() throws Exception {
		String input = "~";
		Atom atom = new TextAtom(input);
		
		assertEquals("TextAtom: ~", atom.toString());
		
	}
	
	@Test
	public void testTagAtomIdentifiers() throws Exception {
		String example = "<p>";
		TextAtom exampleAtom = new TextAtom(example);
		assertEquals("<p>", exampleAtom.getIdentifier());
	}
	
	@Test(expected =IllegalStateException.class)
	public void testTagAtomInternalIdentifiers() throws Exception {
		String example = "<p id=\"sample\">Hello</p>";
		TextAtom exampleAtom = new TextAtom(example);
		assertEquals(false, exampleAtom.hasInternalIdentifiers());
		try {
			exampleAtom.getInternalIdentifiers();
		} catch(IllegalStateException ex) {
			assertEquals("This Atom has no internal identifiers", ex.getMessage());
			throw ex;
		}
	}
   
}
