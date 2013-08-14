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
package org.outerj.daisy.diff.html.dom.helper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.outerj.daisy.diff.html.dom.TagNode;
import org.xml.sax.helpers.AttributesImpl;


/**
 * @author icewariya
 */
public class LastCommonParentResultTest {
	@Test
	public void testSetLastCommonParent() throws Exception {
		
		TagNode root = new TagNode(null, "root", new AttributesImpl());
        
        LastCommonParentResult common = new LastCommonParentResult();
        common.setLastCommonParent(root);
        assertEquals(root, common.getLastCommonParent());
        
        common.setLastCommonParent(null);
        assertEquals(null, common.getLastCommonParent());
	}
	
	@Test
	public void testIsSplittingNeeded() throws Exception {
		
        LastCommonParentResult common = new LastCommonParentResult();
        
        assertEquals(false, common.isSplittingNeeded());
        common.setSplittingNeeded();
        assertEquals(true, common.isSplittingNeeded());
	}
	
	@Test
	public void testLastCommonParentDepth() throws Exception {
	
        LastCommonParentResult common = new LastCommonParentResult();
       
        assertEquals(-1, common.getLastCommonParentDepth());
        common.setLastCommonParentDepth(2);
        assertEquals(2, common.getLastCommonParentDepth());
        
	}
	
	@Test
	public void testIndexInLastCommonParentDepth() throws Exception {
	
        LastCommonParentResult common = new LastCommonParentResult();
       
        assertEquals(-1, common.getIndexInLastCommonParent());
        common.setIndexInLastCommonParent(2);
        assertEquals(2, common.getIndexInLastCommonParent());
	}
   
}
