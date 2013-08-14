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
package org.outerj.daisy.diff.html;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.eclipse.compare.rangedifferencer.IRangeComparator;
import org.junit.Test;
import org.outerj.daisy.diff.html.ancestor.AncestorComparator;
import org.outerj.daisy.diff.html.dom.DomTree;
import org.outerj.daisy.diff.html.dom.DomTreeBuilder;
import org.outerj.daisy.diff.html.dom.TagNode;
import org.outerj.daisy.diff.html.dom.TextNode;
import org.outerj.daisy.diff.html.modification.Modification;
import org.outerj.daisy.diff.html.modification.ModificationType;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author icewariya
 */
public class TextNodeComparatorTest {
	
	private List<TextNode> getTextNodes(TextNodeComparator comp) throws Exception {
		TagNode p = new TagNode(null, "p", new AttributesImpl());
    	TextNode text = new TextNode(p, "contents of p node");
    	
    	TagNode b = new TagNode(null, "b", new AttributesImpl());
    	TextNode boldText = new TextNode(b, "contents of bold node");
    	
    	Field f = comp.getClass().getDeclaredField("textNodes");
		f.setAccessible( true );
		
		List<TextNode> textNodes = (List<TextNode>) f.get( comp );
		textNodes.add(text);
		textNodes.add(boldText);
		
		return textNodes;
    	
	}
	
	@Test
	public void testTextNodeComparator() throws Exception
	{	
		TagNode p = new TagNode(null, "p", new AttributesImpl());
    	TextNode text = new TextNode(p, "contents of p node");
    	
		DomTreeBuilder tree = new DomTreeBuilder();
		TextNodeComparator comp = new TextNodeComparator(tree, Locale.ENGLISH);
		
		Field f = comp.getClass().getDeclaredField("textNodes");
		f.setAccessible( true );
		List<TextNode> textNodes = (List<TextNode>) f.get( comp );
		textNodes.add(text);
		
		assertEquals("<body>", comp.getBodyNode().toString());
		assertEquals("contents of p node", comp.getTextNode(0).toString());
		assertEquals(1, comp.getRangeCount());

	}
	
	@Test
	public void testMarkAsNewExample1() throws Exception
	{	
		DomTreeBuilder tree = new DomTreeBuilder();
		TextNodeComparator comp = new TextNodeComparator(tree, Locale.ENGLISH);
		List<TextNode> textNodes = getTextNodes(comp);
		
		comp.markAsNew(0, 1);
		
		List<Modification> lastModified = comp.getLastModified();
		assertEquals("added",lastModified.get(0).getOutputType().toString());
	}
	
	@Test
	public void testMarkAsNewExample2() throws Exception
	{	
    	
		DomTreeBuilder tree = new DomTreeBuilder();
		TextNodeComparator comp = new TextNodeComparator(tree, Locale.ENGLISH);
		List<TextNode> textNodes = getTextNodes(comp);
		
		comp.markAsNew(1, 0);
		
		List<Modification> lastModified = comp.getLastModified();
		assertEquals(0,lastModified.size());
	}
	
	@Test
	public void testMarkAsNewExample3() throws Exception
	{	
		DomTreeBuilder tree = new DomTreeBuilder();
		TextNodeComparator comp = new TextNodeComparator(tree, Locale.ENGLISH);
	
		List<TextNode> textNodes = getTextNodes(comp);
		
		comp.markAsNew(0, 1, ModificationType.CHANGED);
		
		List<Modification> lastModified = comp.getLastModified();
		assertEquals("changed",lastModified.get(0).getOutputType().toString());
	}
	
	@Test
	public void testMarkAsNewExample4() throws Exception
	{	
		DomTreeBuilder tree = new DomTreeBuilder();
		TextNodeComparator comp = new TextNodeComparator(tree, Locale.ENGLISH);
		
		List<TextNode> textNodes = getTextNodes(comp);
		
		List<Modification> lastModified = comp.getLastModified();
		Modification m = new Modification(ModificationType.REMOVED, ModificationType.REMOVED);
		lastModified.add(m);
		comp.setLastModified(lastModified);
		
		comp.markAsNew(0, 1);
		
		assertEquals("removed",lastModified.get(0).getOutputType().toString());
	}
	

	@Test
	public void testRangesEqual() throws Exception
	{
		DomTreeBuilder tree = new DomTreeBuilder();
		TextNodeComparator comp = new TextNodeComparator(tree, Locale.ENGLISH);
		List<TextNode> textNodes = getTextNodes(comp);
		
		assertFalse(comp.rangesEqual(0, comp, 1));
		assertTrue(comp.rangesEqual(0, comp, 0));
	}
	
	@Test
	public void testSkipRangeComparison() throws Exception
	{
		DomTreeBuilder tree = new DomTreeBuilder();
		TextNodeComparator comp = new TextNodeComparator(tree, Locale.ENGLISH);
		List<TextNode> textNodes = getTextNodes(comp);
		
		assertFalse(comp.skipRangeComparison(0,1,comp));
	}
	
	@Test
	public void testHandlePossibleChangedPart() throws Exception
	{
		DomTreeBuilder tree = new DomTreeBuilder();
		TextNodeComparator comp = new TextNodeComparator(tree, Locale.ENGLISH);
		List<TextNode> textNodes = getTextNodes(comp);
		
		List<Modification> lastModified = comp.getLastModified();
		Modification m = new Modification(ModificationType.CONFLICT, ModificationType.CONFLICT);
		lastModified.add(m);
		comp.setLastModified(lastModified);
		
		comp.handlePossibleChangedPart(0, 1, 1, 2, comp);
		
		assertEquals("conflict",lastModified.get(0).getOutputType().toString());
	}
	
	@Test
	public void testMarkAsDeletedExample1() throws Exception
	{	
		DomTreeBuilder tree = new DomTreeBuilder();
		TextNodeComparator comp = new TextNodeComparator(tree, Locale.ENGLISH);
		List<TextNode> textNodes = getTextNodes(comp);
		
		comp.markAsDeleted(0, 2, comp, 1);
		
		List<Modification> lastModified = comp.getLastModified();
		assertEquals("removed",lastModified.get(0).getOutputType().toString());
	}
	
	@Test
	public void testMarkAsDeletedExample2() throws Exception
	{	
    	
		DomTreeBuilder tree = new DomTreeBuilder();
		TextNodeComparator comp = new TextNodeComparator(tree, Locale.ENGLISH);
		List<TextNode> textNodes = getTextNodes(comp);
		
		comp.markAsDeleted(1, 0, comp, 2);
		
		List<Modification> lastModified = comp.getLastModified();
		assertEquals(0,lastModified.size());
	}
	
	@Test
	public void testMarkAsDeletedExample3() throws Exception
	{	
		DomTreeBuilder tree = new DomTreeBuilder();
		TextNodeComparator comp = new TextNodeComparator(tree, Locale.ENGLISH);
		
		List<TextNode> textNodes = getTextNodes(comp);
		
		List<Modification> lastModified = comp.getLastModified();
		Modification m = new Modification(ModificationType.REMOVED, ModificationType.REMOVED);
		lastModified.add(m);
		comp.setLastModified(lastModified);
		
		comp.markAsDeleted(0, 2, comp, 1);
		
		assertEquals("removed",lastModified.get(0).getOutputType().toString());
	}
	
	@Test
	public void testExpandWhiteSpace() throws Exception
	{	
		DomTreeBuilder tree = new DomTreeBuilder();
		TextNodeComparator comp = new TextNodeComparator(tree, Locale.ENGLISH);
		
		List<TextNode> textNodes = getTextNodes(comp);
		comp.expandWhiteSpace();
		
		List<Modification> lastModified = comp.getLastModified();
		assertEquals(0,lastModified.size());
		
	}
	
	@Test
	public void testIterator() throws Exception
	{	
		DomTreeBuilder tree = new DomTreeBuilder();
		TextNodeComparator comp = new TextNodeComparator(tree, Locale.ENGLISH);
		
		List<TextNode> textNodes = getTextNodes(comp);
		Iterator<TextNode> iterator = comp.iterator();
		
		assertTrue(iterator.hasNext());
		
	}
	
	@Test
	public void testSetStartDeletedID() throws Exception
	{	
		DomTreeBuilder tree = new DomTreeBuilder();
		TextNodeComparator comp = new TextNodeComparator(tree, Locale.ENGLISH);

		long aDeletedID = 456123;
		comp.setStartDeletedID(aDeletedID);
		
		assertEquals(aDeletedID, comp.getDeletedID());
		
	}
	
	@Test
	public void testSetStartNewID() throws Exception
	{	
		DomTreeBuilder tree = new DomTreeBuilder();
		TextNodeComparator comp = new TextNodeComparator(tree, Locale.ENGLISH);
		
		long aNewID = 456123;
		comp.setStartNewID(aNewID);
		
		assertEquals(aNewID, comp.getNewID());
		
	}
	
	@Test
	public void testSetStartChangedID() throws Exception
	{	
		DomTreeBuilder tree = new DomTreeBuilder();
		TextNodeComparator comp = new TextNodeComparator(tree, Locale.ENGLISH);
		
		long aChangedID = 456123;
		comp.setStartChangedID(aChangedID);
		
		assertEquals(aChangedID, comp.getChangedID());
		
	}
	
}