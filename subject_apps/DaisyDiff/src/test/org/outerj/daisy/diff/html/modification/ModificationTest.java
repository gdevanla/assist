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
package org.outerj.daisy.diff.html.modification;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 
 * @author icewariya
 * 
 */

public class ModificationTest {

	@Test
	public void testClone() throws Exception {
		Modification newM = new Modification(ModificationType.ADDED, ModificationType.REMOVED);
		Modification clonedModification = newM.clone();
		
		assertFalse(newM.equals(clonedModification));
	}
	
	@Test
	public void testSetID() throws Exception {
		Modification newM = new Modification(ModificationType.ADDED, ModificationType.REMOVED);
		long ID = 123;
		newM.setID(ID);
		
		assertEquals(ID, newM.getID());
	}

	@Test
	public void testGetTypes() throws Exception {
		Modification newM = new Modification(ModificationType.ADDED, ModificationType.REMOVED);
		
		assertEquals(ModificationType.ADDED, newM.getType());
		assertEquals(ModificationType.REMOVED, newM.getOutputType());
	}
	
	@Test
	public void testGetPrevious() throws Exception {
		Modification newM = new Modification(ModificationType.ADDED, ModificationType.REMOVED);
		
		newM.setPrevious(newM);
		assertEquals(newM, newM.getPrevious());
		
		newM.setPrevious(null);
		assertEquals(null, newM.getPrevious());
	}
	
	@Test
	public void testGetNext() throws Exception {
		Modification newM = new Modification(ModificationType.ADDED, ModificationType.REMOVED);
		
		newM.setNext(newM);
		assertEquals(newM, newM.getNext());
		
		newM.setNext(null);
		assertEquals(null, newM.getNext());
	}
	
	@Test
	public void testGetChanges() throws Exception {
		Modification newM = new Modification(ModificationType.ADDED, ModificationType.REMOVED);
		String changes = "<b>UIC</b>";
		assertEquals(null, newM.getChanges());
		
		newM.setChanges(changes);
		assertEquals(changes, newM.getChanges());
		
	}
	
	@Test
	public void testIsFirstOfId() throws Exception {
		Modification newM = new Modification(ModificationType.ADDED, ModificationType.REMOVED);
		
		assertEquals(false, newM.isFirstOfID());
		
		newM.setFirstOfID(true);
		assertEquals(true, newM.isFirstOfID());
		
	}
	
	@Test
	public void testGetHtmlLayoutChanges() throws Exception {
		Modification newM = new Modification(ModificationType.ADDED, ModificationType.REMOVED);
		List<HtmlLayoutChange> htmlLayoutChanges = new ArrayList<HtmlLayoutChange>();
		
		assertEquals(null, newM.getHtmlLayoutChanges());
		
		htmlLayoutChanges.add(new HtmlLayoutChange());
		newM.setHtmlLayoutChanges(htmlLayoutChanges);
		assertEquals(htmlLayoutChanges, newM.getHtmlLayoutChanges());
		
	}

}
