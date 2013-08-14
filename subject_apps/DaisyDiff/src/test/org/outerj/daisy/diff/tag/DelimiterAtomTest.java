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
public class DelimiterAtomTest {

	@Test
	public void testIsValidDelimiter() throws Exception {
		String delimInput = "&<p>This is a blue book</p>";
		String input = "<p>This is a blue book</p>";
		String empty = new String();
		String lengthOne = "(";
		char ch = '!';
		char notDelim = '~';
		
		assertTrue(DelimiterAtom.isValidDelimiter(ch));
		assertFalse(DelimiterAtom.isValidDelimiter(delimInput));
		assertFalse(DelimiterAtom.isValidDelimiter(input));
		assertFalse(DelimiterAtom.isValidDelimiter(empty));
		assertTrue(DelimiterAtom.isValidDelimiter(lengthOne));
		assertFalse(DelimiterAtom.isValidDelimiter(notDelim));
	}
	
	@Test
	public void testIsValidAtom() throws Exception {
		String input = "<p>This is a blue book</p>";
		String delimInput = "&";
		String empty = new String();
		String nullString = null;
		
		DelimiterAtom delimiterAtom = new DelimiterAtom(' ');
		assertFalse(delimiterAtom.isValidAtom(input));
		assertTrue(delimiterAtom.isValidAtom(delimInput));
		assertFalse(delimiterAtom.isValidAtom(empty));
		assertFalse(delimiterAtom.isValidAtom(nullString));
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIsValidAtomException() throws Exception {
		try { 		
			new DelimiterAtom('~');
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
		
		assertTrue(atom.equalsIdentifier(matchAtom));
		assertFalse(atom.equalsIdentifier(emptyAtom));
		assertFalse(matchAtom.equalsIdentifier(differentAtom));
		
	}
	
	@Test
	public void testToString() throws Exception {
		String input = "~";
		Atom atom = new TextAtom(input);
		
		assertEquals("TextAtom: ~", atom.toString());
		
	}
}
