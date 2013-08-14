package org.outerj.daisy.diff.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;

import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;

import org.junit.Test;
import org.outerj.daisy.diff.helper.NekoHtmlParser.RemoveNamespacesHandler;
import org.outerj.daisy.diff.html.dom.DomTreeBuilder;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class NekoHtmlParserTest {
	@Test
	public void testParseInput() throws Exception
	{
		String html1 = "<html>  \n  <body>  \n  Hello world  \n  </body>  \n  </html>";
		InputSource is = new InputSource(new StringReader(html1));
		NekoHtmlParser parser = new NekoHtmlParser();
		
		assertEquals("  \n    \n  Hello world  \n    \n  ", parser.parse(is).toString());
	}
	
	@Test
	public void testParseInvalidInput() throws Exception
	{
		String html1 = "  \n    \n    \n    \n  ";
		InputSource is = new InputSource(new StringReader(html1));
		NekoHtmlParser parser = new NekoHtmlParser();
		
		assertEquals("", parser.parse(is).toString());
	}
	
	@Test
	public void testParse() throws Exception
	{
		String html1 = "<html>  \n  <body>  \n  Hello world  \n  </body>  \n  </html>";
		InputSource is = new InputSource(new StringReader(html1));
		NekoHtmlParser parser = new NekoHtmlParser();
		DomTreeBuilder contentHandler = new DomTreeBuilder();
		
		parser.parse(is,contentHandler);

		assertEquals("", parser.parse(is).toString());
		
	}
	
	@Test(expected = NullPointerException.class)
	public void testParseNull() throws Exception
	{
		InputSource is = null;
		NekoHtmlParser parser = new NekoHtmlParser();
		DomTreeBuilder contentHandler = new DomTreeBuilder();
		
		try {
			parser.parse(is,contentHandler);
		} catch(NullPointerException ex) {
			assertEquals("is argument is required.", ex.getMessage());
			throw ex;
		}		
	}
	
	@Test(expected = Exception.class)
	public void testEndDocument() throws Exception
	{
		RemoveNamespacesHandler handler = new RemoveNamespacesHandler(null);
		try {
			handler.endDocument();
		} catch(Exception ex) {
			throw ex;
		}		
	}
	
	@Test(expected = Exception.class)
	public void testStartDocument() throws Exception
	{
		RemoveNamespacesHandler handler = new RemoveNamespacesHandler(null);
		try {
			handler.startDocument();
		} catch(Exception ex) {
			throw ex;
		}		
	}
	
	@Test(expected = IllegalStateException.class)
	public void testCharactersException() throws Exception
	{
		DomTreeBuilder contentHandler = new DomTreeBuilder();
		RemoveNamespacesHandler handler = new RemoveNamespacesHandler(contentHandler);
		char[] ch = {'a','b','c','d','e'};
		try {
		handler.characters(ch, 0, 1);
		} catch(IllegalStateException ex) {
			throw ex;
		}
	}
	
	@Test
	public void testCharacters() throws Exception
	{
		SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
		TransformerHandler result = tf.newTransformerHandler();
		ContentHandler postProcess = result;
		
		RemoveNamespacesHandler handler = new RemoveNamespacesHandler(postProcess);
		char[] ch = {'a','b','c','d','e'};
		
		handler.characters(ch, 0, 1);
		assertEquals(RemoveNamespacesHandler.class, handler.getClass());

	}
	
	@Test
	public void testWhitespace() throws Exception
	{
		SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
		TransformerHandler result = tf.newTransformerHandler();
		ContentHandler postProcess = result;
		
		RemoveNamespacesHandler handler = new RemoveNamespacesHandler(postProcess);
		char[] ch = {'a','b','c','d','e'};
		
		handler.ignorableWhitespace(ch, 2, 3);
		assertEquals(RemoveNamespacesHandler.class, handler.getClass());

	}
	
	@Test
	public void testStartElement() throws Exception
	{
		SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
		TransformerHandler result = tf.newTransformerHandler();
		ContentHandler postProcess = result;
		
		RemoveNamespacesHandler handler = new RemoveNamespacesHandler(postProcess);
		AttributesImpl attrs = new AttributesImpl();
	    attrs.addAttribute("", "class", "class", "CDATA", "diff-tag-added");
	    attrs.addAttribute("", "id", "id", "CDATA", "added");
		handler.startElement("namespaceURI", "localName", "qName", attrs);
		
		assertEquals(RemoveNamespacesHandler.class, handler.getClass());

	}
}
