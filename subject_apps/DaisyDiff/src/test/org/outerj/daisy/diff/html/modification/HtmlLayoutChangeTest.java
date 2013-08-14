/*
 * Copyright 2007-2009 Guy Van den Broeck
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
package org.outerj.daisy.diff.html.modification;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.outerj.daisy.diff.html.modification.HtmlLayoutChange.Type;

/**
 * 
 * @author icewariya
 *
 */
public class HtmlLayoutChangeTest {
	@Test
	public void testSetType() throws Exception {
		HtmlLayoutChange tagAdd = new HtmlLayoutChange();
		tagAdd.setType(Type.TAG_ADDED);
		
		HtmlLayoutChange tagRemove = new HtmlLayoutChange();
		tagRemove.setType(Type.TAG_REMOVED);
		
		HtmlLayoutChange nullTag = new HtmlLayoutChange();
		nullTag.setType(null);
		
		assertEquals(Type.TAG_ADDED, tagAdd.getType());
		assertEquals(Type.TAG_REMOVED, tagRemove.getType());
		assertEquals(null, nullTag.getType());

	}
	
	@Test
	public void testSetTag() throws Exception {
		HtmlLayoutChange tagAdd = new HtmlLayoutChange();
		tagAdd.setOpeningTag("<p>");
		tagAdd.setEndingTag("</p>");
		
		HtmlLayoutChange nullTag = new HtmlLayoutChange();
		nullTag.setOpeningTag(null);
		
		assertEquals("<p>", tagAdd.getOpeningTag());
		assertEquals("</p>", tagAdd.getEndingTag());
		assertEquals(null, nullTag.getOpeningTag());
		assertEquals("",nullTag.getEndingTag());
		
		nullTag.setEndingTag("");
		assertEquals("", nullTag.getEndingTag());

	}
	
}
