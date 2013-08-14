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
import java.util.Locale;

import org.eclipse.compare.rangedifferencer.IRangeComparator;
import org.junit.Test;
import org.outerj.daisy.diff.html.dom.TagNode;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author icewariya
 */
public class AncestorComparatorTest {


	@Test
	public void testGetRangeCount() throws Exception {
		TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TagNode intermediate = new TagNode(null, "middle", new AttributesImpl());

		List<TagNode> ancestors = new ArrayList<TagNode>();;

		ancestors.add(root);
		ancestors.add(intermediate);

		AncestorComparator comp =  new AncestorComparator(ancestors);

		assertEquals(2, comp.getRangeCount());
	}

	@Test
	public void testRangesEqual() throws Exception {

		TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TagNode intermediate = new TagNode(null, "middle", new AttributesImpl());

    	List<TagNode> ancestors = new ArrayList<TagNode>();

		ancestors.add(root);
		ancestors.add(intermediate);

		IRangeComparator comp =  new AncestorComparator(ancestors);
		assertFalse(comp.rangesEqual(0, comp, 1));
		assertTrue(comp.rangesEqual(0, comp, 0));

	}
	
	@Test
	public void testSkipRangeComparison() throws Exception {

		TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TagNode intermediate = new TagNode(null, "middle", new AttributesImpl());

    	List<TagNode> ancestors = new ArrayList<TagNode>();

		ancestors.add(root);
		ancestors.add(intermediate);

		IRangeComparator comp =  new AncestorComparator(ancestors);
		assertFalse(comp.skipRangeComparison(0,1,comp));

	}
	
	@Test
	public void testGetAncestor() throws Exception {

		TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TagNode intermediate = new TagNode(null, "middle", new AttributesImpl());

    	List<TagNode> ancestors = new ArrayList<TagNode>();

		ancestors.add(root);
		ancestors.add(intermediate);

		assertEquals(root, ancestors.get(0));

	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetAncestorException() throws Exception {

		TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TagNode intermediate = new TagNode(null, "middle", new AttributesImpl());

    	List<TagNode> ancestors = new ArrayList<TagNode>();

		ancestors.add(root);
		ancestors.add(intermediate);

		try {
		ancestors.get(3);
		} catch(IndexOutOfBoundsException ex) {
			throw ex;
		}

	}


	@Test
	public void testGetCompareTxt() throws Exception {

		TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TagNode intermediate = new TagNode(null, "middle", new AttributesImpl());

    	List<TagNode> ancestors = new ArrayList<TagNode>();

		ancestors.add(root);
		ancestors.add(intermediate);
	
		AncestorComparator comp =  new AncestorComparator(ancestors);
		assertEquals("", comp.getCompareTxt());

	}
	
	@Test
	public void testGetResult() throws Exception {

		TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TagNode intermediate = new TagNode(null, "middle", new AttributesImpl());

    	List<TagNode> firstNodeList = new ArrayList<TagNode>();
    	firstNodeList.add(root);
    	firstNodeList.add(intermediate);
    	
    	TagNode html = new TagNode(null, "html", new AttributesImpl());
    	TagNode body = new TagNode(null, "body", new AttributesImpl());

    	List<TagNode> secondNodeList = new ArrayList<TagNode>();
    	secondNodeList.add(html);
    	secondNodeList.add(body);
	
		AncestorComparator comp =  new AncestorComparator(firstNodeList);
		AncestorComparator other =  new AncestorComparator(secondNodeList);
		
		assertFalse(comp.getResult(comp, Locale.ENGLISH).isChanged());
		assertTrue(comp.getResult(other, Locale.ENGLISH).isChanged());

	}


}
