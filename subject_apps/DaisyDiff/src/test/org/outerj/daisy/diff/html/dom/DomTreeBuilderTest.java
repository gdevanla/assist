package org.outerj.daisy.diff.html.dom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.xml.sax.helpers.AttributesImpl;

/**
 * author @icewariya
 */

public class DomTreeBuilderTest
{
	@Test
	public void testStartDocument() throws Exception
	{

		DomTreeBuilder tree = new DomTreeBuilder();
		List<TextNode> textNodes = new ArrayList<TextNode>();

		assertEquals("<body>", tree.getBodyNode().toString());
		assertEquals(textNodes, tree.getTextNodes());
		assertFalse(tree.documentStarted);

		tree.startDocument();
		assertTrue(tree.documentStarted);

	}
	
	@Test(expected = IllegalStateException.class)
	public void testStartDocumentException() throws Exception
	{

		DomTreeBuilder tree = new DomTreeBuilder();
		tree.startDocument();
		assertTrue(tree.documentStarted);
		
		try {
			tree.startDocument();
		} catch (IllegalStateException ex) {
			assertEquals("This Handler only accepts one document", ex.getMessage());
			throw ex;
		}

	}
	
	@Test
	public void testEndDocument() throws Exception
	{

		DomTreeBuilder tree = new DomTreeBuilder();
		List<TextNode> textNodes = new ArrayList<TextNode>();

		tree.startDocument();
		assertEquals("<body>", tree.getBodyNode().toString());
		assertEquals(textNodes, tree.getTextNodes());
		assertFalse(tree.documentEnded);

		tree.endDocument();
		assertTrue(tree.documentEnded);

	}
	
	@Test(expected = IllegalStateException.class)
	public void testEndDocumentException1() throws Exception
	{

		DomTreeBuilder tree = new DomTreeBuilder();
		try {
			tree.endDocument();
		} catch (IllegalStateException ex) {
			throw ex;
		}

	}
	
	@Test(expected = IllegalStateException.class)
	public void testEndDocumentException2() throws Exception
	{

		DomTreeBuilder tree = new DomTreeBuilder();
		tree.startDocument();
		tree.endDocument();
		try {
			tree.endDocument();
		} catch (IllegalStateException ex) {
			throw ex;
		}

	}
	
	@Test
	public void testStartElementExample1() throws Exception
	{
		AttributesImpl attrs = new AttributesImpl();
		attrs.addAttribute("", "class", "class", "class", "diff");
		
		DomTreeBuilder tree = new DomTreeBuilder();
		tree.startDocument();
		tree.bodyStarted = true;
		tree.startElement("", "p", "p", attrs);
		
		assertEquals("<p class=\"diff\">",tree.getBodyNode().getChild(0).toString());
	}
	
	@Test
	public void testStartElementExample2() throws Exception
	{
		AttributesImpl attrs = new AttributesImpl();
		attrs.addAttribute("", "class", "class", "class", "diff");
		
		DomTreeBuilder tree = new DomTreeBuilder();
		tree.startDocument();
		tree.bodyStarted = true;
		tree.bodyEnded = true;
		tree.startElement("", "p", "p", attrs);
		
		assertEquals(0, tree.getBodyNode().getNbChildren());
	}
	
	@Test
	public void testStartElementExample3() throws Exception
	{
		AttributesImpl attrs = new AttributesImpl();
		attrs.addAttribute("", "class", "class", "class", "diff");
		
		DomTreeBuilder tree = new DomTreeBuilder();
		tree.startDocument();
		tree.bodyStarted = false;
		tree.bodyEnded = true;
		tree.startElement("", "p", "p", attrs);
		
		assertEquals(0,tree.getBodyNode().getNbChildren());
	}
	
	@Test
	public void testStartElementExample4() throws Exception
	{
		AttributesImpl attrs = new AttributesImpl();
		attrs.addAttribute("", "class", "class", "class", "diff");
		
		DomTreeBuilder tree = new DomTreeBuilder();
		tree.startDocument();
		tree.bodyStarted = false;
		tree.bodyEnded = false;
		tree.startElement("", "p", "p", attrs);
		
		assertEquals(0,tree.getBodyNode().getNbChildren());
	}
	
	@Test(expected = IllegalStateException.class)
	public void testStartElementExample5() throws Exception
	{
		AttributesImpl attrs = new AttributesImpl();
		attrs.addAttribute("", "class", "class", "class", "diff");
		
		DomTreeBuilder tree = new DomTreeBuilder();
		tree.bodyStarted = false;
		tree.bodyEnded = false;
		try {
			tree.startElement("", "p", "p", attrs);
		} catch(IllegalStateException ex) {
			throw ex;
		}
		
	}
	
	@Test(expected = IllegalStateException.class)
	public void testStartElementExample6() throws Exception
	{
		AttributesImpl attrs = new AttributesImpl();
		attrs.addAttribute("", "class", "class", "class", "diff");
		
		DomTreeBuilder tree = new DomTreeBuilder();
		tree.startDocument();
		tree.endDocument();
		
		tree.bodyStarted = false;
		tree.bodyEnded = false;
		try {
			tree.startElement("", "p", "p", attrs);
		} catch(IllegalStateException ex) {
			throw ex;
		}
		
	}
	
	@Test
	public void testStartElementExample7() throws Exception
	{
		AttributesImpl attrs = new AttributesImpl();
		attrs.addAttribute("", "class", "class", "class", "diff");
		
		DomTreeBuilder tree = new DomTreeBuilder();
		tree.startDocument();

		tree.startElement("", "body", "body", attrs);
		
		assertEquals("<body>", tree.getBodyNode().toString());
	}
	
	@Test
	public void testStartElementExample8() throws Exception
	{
		AttributesImpl attrs = new AttributesImpl();
		attrs.addAttribute("", "class", "class", "class", "diff");
		
		DomTreeBuilder tree = new DomTreeBuilder();
		tree.startDocument();
		tree.bodyStarted = true;
		tree.startElement("", "pre", "pre", attrs);
		
		assertEquals("<pre class=\"diff\">",tree.getBodyNode().getChild(0).toString());
	}

	@Test
	public void testEndElementExample1() throws Exception
	{
		AttributesImpl attrs = new AttributesImpl();
		attrs.addAttribute("", "class", "class", "class", "diff");
		
		DomTreeBuilder tree = new DomTreeBuilder();
		tree.startDocument();
		tree.bodyStarted = true;
		tree.bodyEnded = true;
		tree.startElement("", "p", "p", attrs);
		tree.endElement("", "p", "p");
		
		assertEquals(0, tree.getBodyNode().getNbChildren());
	}

	@Test
	public void testEndElementExample2() throws Exception
	{
		AttributesImpl attrs = new AttributesImpl();
		attrs.addAttribute("", "class", "class", "class", "diff");
		
		DomTreeBuilder tree = new DomTreeBuilder();
		tree.startDocument();
		tree.bodyStarted = false;
		tree.bodyEnded = true;
		tree.startElement("", "p", "p", attrs);
		tree.endElement("", "p", "p");
		
		assertEquals(0,tree.getBodyNode().getNbChildren());
	}
	
	@Test
	public void testEndElementExample3() throws Exception
	{
		AttributesImpl attrs = new AttributesImpl();
		attrs.addAttribute("", "class", "class", "class", "diff");
		
		DomTreeBuilder tree = new DomTreeBuilder();
		tree.startDocument();
		tree.bodyStarted = false;
		tree.bodyEnded = false;
		tree.startElement("", "p", "p", attrs);
		tree.endElement("", "p", "p");
		
		assertEquals(0,tree.getBodyNode().getNbChildren());
	}
	
	@Test(expected = IllegalStateException.class)
	public void testEndElementExample4() throws Exception
	{
		AttributesImpl attrs = new AttributesImpl();
		attrs.addAttribute("", "class", "class", "class", "diff");
		
		DomTreeBuilder tree = new DomTreeBuilder();
		tree.bodyStarted = false;
		tree.bodyEnded = false;
		try {
			tree.endElement("", "p", "p");
		} catch(IllegalStateException ex) {
			throw ex;
		}
		
	}
	
	@Test(expected = IllegalStateException.class)
	public void testEndElementExample5() throws Exception
	{
		AttributesImpl attrs = new AttributesImpl();
		attrs.addAttribute("", "class", "class", "class", "diff");
		
		DomTreeBuilder tree = new DomTreeBuilder();
		tree.startDocument();
		tree.endDocument();
		
		tree.bodyStarted = false;
		tree.bodyEnded = false;
		try {
			tree.endElement("", "p", "p");
		} catch(IllegalStateException ex) {
			throw ex;
		}
		
	}
	
	@Test
	public void testEndElementExample6() throws Exception
	{
		AttributesImpl attrs = new AttributesImpl();
		attrs.addAttribute("", "class", "class", "class", "diff");
		
		DomTreeBuilder tree = new DomTreeBuilder();
		tree.startDocument();

		tree.startElement("", "body", "body", attrs);
		tree.endElement("", "body", "body");
		
		assertEquals("<body>", tree.getBodyNode().toString());
	}
	
	@Test
	public void testEndElementExample7() throws Exception
	{
		AttributesImpl attrs = new AttributesImpl();
		attrs.addAttribute("", "src", "src", "src", "Image path");
		
		DomTreeBuilder tree = new DomTreeBuilder();
		tree.startDocument();
		tree.bodyStarted = true;
		tree.startElement("", "img", "img", attrs);
		tree.endElement("", "img", "img");
		
		assertEquals("<img src=\"Image path\">",tree.getBodyNode().getChild(0).toString());
	}
	
	@Test
	public void testEndElementExample8() throws Exception
	{
		AttributesImpl attrs = new AttributesImpl();
		attrs.addAttribute("", "class", "class", "class", "diff");
		
		DomTreeBuilder tree = new DomTreeBuilder();
		tree.startDocument();
		tree.bodyStarted = true;
		tree.startElement("", "pre", "pre", attrs);
		tree.endElement("", "pre", "pre");
		
		assertEquals("<pre class=\"diff\">",tree.getBodyNode().getChild(0).toString());
	}
	
	@Test
	public void testCharacters() throws Exception
	{
		AttributesImpl attrs = new AttributesImpl();
		attrs.addAttribute("", "class", "class", "class", "diff");
		
		DomTreeBuilder tree = new DomTreeBuilder();
		tree.startDocument();
		tree.bodyStarted = true;
		tree.startElement("", "p", "p", attrs);
		tree.endElement("", "p", "p");
		
		char[] ch = {'/','e','s','\t',' ','h','+','r','a','c','t','\n','r'};
		tree.characters(ch, 0, 12);
		
		assertEquals("<p class=\"diff\">",tree.getBodyNode().getChild(0).toString());
	}
	
	@Test(expected = IllegalStateException.class)
	public void testCharactersException() throws Exception
	{
		AttributesImpl attrs = new AttributesImpl();
		attrs.addAttribute("", "class", "class", "class", "diff");
		
		DomTreeBuilder tree = new DomTreeBuilder();
		
		tree.documentStarted = false;
		tree.documentEnded = true;
		char[] ch = {'/','e','s','\t',' ','h','+','r','a','c','t','\n','r'};
		
		tree.bodyStarted = false;
		tree.bodyEnded = true;
		try {
			tree.characters(ch, 0, 4);
		} catch(IllegalStateException ex) {
			throw ex;
		}
		
	}
}
