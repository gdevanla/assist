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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.outerj.daisy.diff.html.modification.HtmlLayoutChange;
/*
 * @author icewariya
 */
public class AncestorComparatorResultTest {

	@Test
	public void testIsChanged() throws Exception {
		AncestorComparatorResult result =  new AncestorComparatorResult();
		assertFalse(result.isChanged());
	}
	
	@Test
	public void testSetChanged() throws Exception {
		AncestorComparatorResult result =  new AncestorComparatorResult();
		
		result.setChanged(true);
		assertTrue(result.isChanged());
	}

	@Test
	public void testGetChanges() throws Exception {
		AncestorComparatorResult result =  new AncestorComparatorResult();
		
		assertEquals(null, result.getChanges());
	}
	
	@Test
	public void testSetChanges() throws Exception {
		AncestorComparatorResult result =  new AncestorComparatorResult();
		
		result.setChanges("blue");
		assertEquals("blue", result.getChanges());
	}

	@Test
	public void testGetHtmlLayoutChanges() throws Exception {
		AncestorComparatorResult result =  new AncestorComparatorResult();
		
		List<HtmlLayoutChange> htmlLayoutChanges = new ArrayList<HtmlLayoutChange>();
		assertEquals(htmlLayoutChanges, result.getHtmlLayoutChanges());
	}
	
	@Test
	public void testSetHtmlLayoutChanges() throws Exception {
		AncestorComparatorResult result =  new AncestorComparatorResult();
		
		List<HtmlLayoutChange> htmlLayoutChanges = new ArrayList<HtmlLayoutChange>();
		HtmlLayoutChange e = new HtmlLayoutChange();
		htmlLayoutChanges.add(e);
		
		result.setHtmlLayoutChanges(htmlLayoutChanges);
		assertEquals(htmlLayoutChanges, result.getHtmlLayoutChanges());
	}

}
