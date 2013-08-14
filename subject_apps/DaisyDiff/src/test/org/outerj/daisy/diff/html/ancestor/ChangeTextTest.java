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
package org.outerj.daisy.diff.html.ancestor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author icewariya
 */

public class ChangeTextTest {
	
	@Test
	public void testAddText() throws Exception {
		ChangeText text = new ChangeText(10);
		String html = "<ul class='changelist'><li>Moved out of a <b>html page</b>.</li><li>Moved out of a <b>html document</b>.</li><li><b>!diff-root!</b> added.</li></ul>";
		String textInput = "Moved out of a html page. Moved out of a html document";
		
		ChangeText changeText = new ChangeText(10);
		String newText = "content";
	
		
		text.addText(html);
		text.addText(textInput);
		changeText.addText(newText);
		
		assertEquals(ChangeText.class, text.getClass());
		assertEquals(newText, changeText.toString());
	}
	
	@Test
	public void testAddHtml() throws Exception {
		ChangeText text = new ChangeText(10);
		String html = "<ul class='changelist'><li>Moved out of a <b>html page</b>.</li><li>Moved out of a <b>html document</b>.</li><li><b>!diff-root!</b> added.</li></ul>";
		String content = "<ol><li>Moved out of a html page.</li><li>Moved out of a html document.</li></ol>";
		
		text.addHtml(html);
		text.addHtml(content);

		assertEquals(ChangeText.class, text.getClass());

	}
	
	@Test
	public void testAddNewLine() throws Exception {
		ChangeText text = new ChangeText(5);
		
		text.addNewLine();
		
		assertEquals("<br/>", text.toString());
		assertEquals(ChangeText.class, text.getClass());

	}
   
}
