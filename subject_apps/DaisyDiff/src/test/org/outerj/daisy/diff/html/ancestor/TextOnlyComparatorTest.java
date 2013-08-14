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

import org.eclipse.compare.internal.LCSSettings;
import org.eclipse.compare.rangedifferencer.IRangeComparator;
import org.eclipse.compare.rangedifferencer.RangeDifference;
import org.eclipse.compare.rangedifferencer.RangeDifferencer;
import org.junit.Test;
import org.outerj.daisy.diff.html.dom.Node;
import org.outerj.daisy.diff.html.dom.TagNode;
import org.outerj.daisy.diff.html.dom.TextNode;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author icewariya
 */
public class TextOnlyComparatorTest {
	
	@Test
    public void testAddRecursive() throws Exception
    {
		TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TagNode intermediate = new TagNode(root, "middle", new AttributesImpl());
    	root.addChild(intermediate);
    	TextNode textRoot = new TextNode(root, "contents of root node");
    	root.addChild(textRoot);
    	
    	TextOnlyComparator tagComp = new TextOnlyComparator(root);
    	
    	assertEquals(TextOnlyComparator.class, tagComp.getClass());
    }
	
	@Test
    public void testGetRangeCount() throws Exception
    {
		TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TagNode intermediate = new TagNode(root, "middle", new AttributesImpl());
    	root.addChild(intermediate);
    	TextNode textRoot = new TextNode(root, "contents of root node");
    	root.addChild(textRoot);
    	
    	TextOnlyComparator tagComp = new TextOnlyComparator(root);
    	
    	assertEquals(2, tagComp.getRangeCount());
    	
    }
	
	@Test(expected = NullPointerException.class)
    public void testGetRangeCountNull() throws Exception
    {
    	TextOnlyComparator nullComp = new TextOnlyComparator(null);
    	try {
    		nullComp.getRangeCount();
    	} catch(NullPointerException ex) {
    		throw ex;
    	}
    }
	

	@Test
	public void testRangesEqual() throws Exception {

		TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TagNode intermediate = new TagNode(root, "middle", new AttributesImpl());
    	root.addChild(intermediate);
    	TextNode textRoot = new TextNode(root, "contents of root node");
    	root.addChild(textRoot);

		IRangeComparator comp =  new TextOnlyComparator(root);

		assertTrue(comp.rangesEqual(0, comp, 0));

	}
	
	@Test
	public void testSkipRangeComparison() throws Exception {

		TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TagNode intermediate = new TagNode(root, "middle", new AttributesImpl());
    	root.addChild(intermediate);
   
		IRangeComparator comp =  new TextOnlyComparator(root);
		
		assertFalse(comp.skipRangeComparison(0,1,comp));

	}
	
	 @Test
	 public void testGetMatchRatio() throws Exception
	 {
		TagNode root = new TagNode(null, "root", new AttributesImpl());
		TagNode intermediate = new TagNode(root, "middle", new AttributesImpl());
		root.addChild(intermediate);
		TextNode textInter = new TextNode(intermediate, "contents of inter node");
    	intermediate.addChild(textInter);
		TextNode textRoot = new TextNode(root, "contents of root node");
    	root.addChild(textRoot);
	   	
	    TextOnlyComparator rootComp = new TextOnlyComparator(root);
	    TextOnlyComparator interComp = new TextOnlyComparator(intermediate);
	    
	   	assertEquals(0.0, rootComp.getMatchRatio(rootComp),0.1);
	   	assertEquals(0.33, rootComp.getMatchRatio(interComp),0.1);
    }
   
}
