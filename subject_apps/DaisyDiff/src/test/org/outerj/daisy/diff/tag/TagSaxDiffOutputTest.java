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
import org.xml.sax.ContentHandler;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author icewariya
 */
public class TagSaxDiffOutputTest {

	@Test
	public void testAddClearPart() throws Exception {
		String input = "\n<p id=\"sample\">This is a <span style=\"color:blue\">blue</span> book</p>";
		ContentHandler consumer = new DefaultHandler();
		int hashValue = consumer.hashCode();
		TagSaxDiffOutput saxDiffOutput = new TagSaxDiffOutput(consumer);

		saxDiffOutput.addClearPart(input);
		
		
	}
	
	@Test
	public void testAddRemovedPart() throws Exception {
		String input = "<p>This is a blue book</p>";
		ContentHandler consumer = new DefaultHandler();
		int hashValue = consumer.hashCode();
		TagSaxDiffOutput saxDiffOutput = new TagSaxDiffOutput(consumer);

		saxDiffOutput.addRemovedPart(input);
		assertEquals(hashValue, consumer.hashCode());
		
	}
	
	@Test
	public void testAddAddedPart() throws Exception {
		String input = "<p id=\"sample\">This is a blue book</p>";
		ContentHandler consumer = new DefaultHandler();
		int hashValue = consumer.hashCode();
		TagSaxDiffOutput saxDiffOutput = new TagSaxDiffOutput(consumer);

		saxDiffOutput.addAddedPart(input);
		assertEquals(hashValue, consumer.hashCode());
		
	}
}
