package org.outerj.daisy.diff.html;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Locale;

import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import org.outerj.daisy.diff.helper.NekoHtmlParser;
import org.outerj.daisy.diff.html.dom.DomTreeBuilder;
import org.outerj.daisy.diff.html.dom.ImageNode;
import org.outerj.daisy.diff.html.dom.TagNode;
import org.outerj.daisy.diff.html.dom.TextNode;
import org.outerj.daisy.diff.html.modification.Modification;
import org.outerj.daisy.diff.html.modification.ModificationType;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.AttributesImpl;

/**
 * 
 * @author icewariya
 *
 */
public class HtmlSaxDiffOutputTest {
	StringWriter finalResult = new StringWriter();
	private HtmlSaxDiffOutput getOutput() throws Exception{
		
		SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
		TransformerHandler result = tf.newTransformerHandler();
		result.setResult(new StreamResult(finalResult));
		ContentHandler postProcess = result;
	
		String prefix = "diff";

		HtmlSaxDiffOutput output = new HtmlSaxDiffOutput(postProcess, prefix);
		
		return output;
	}
	
	//private method testing
	private Object executeMethod( Object instance, String name, Object[] params ) throws Exception
	{
		Class c 	= instance.getClass();
		Class[] types 	= new Class[params.length];

		for ( int i = 0; i < params.length; i++ )
			types[i] = params[i].getClass();
		Method method = c.getDeclaredMethod(name, types );
		method.setAccessible( true );
		method.invoke(instance, params);
		
		if(name=="addAttributes")
			return params[1];
		else
			return params[0];
	}
	
	//
	private String diff(String first, String second, HtmlSaxDiffOutput output) throws Exception{
		NekoHtmlParser cleaner = new NekoHtmlParser();
		Locale locale = Locale.getDefault();
        InputSource oldSource = new InputSource(new StringReader(
        		first));
        InputSource newSource = new InputSource(new StringReader(
        		second));

        DomTreeBuilder oldHandler = new DomTreeBuilder();
        cleaner.parse(oldSource, oldHandler);
        TextNodeComparator leftComparator = new TextNodeComparator(
                oldHandler, locale);

        DomTreeBuilder newHandler = new DomTreeBuilder();
        cleaner.parse(newSource, newHandler);
        TextNodeComparator rightComparator = new TextNodeComparator(
                newHandler, locale);
        
        HTMLDiffer differ = new HTMLDiffer(output);
        differ.diff(leftComparator, rightComparator);
        
        return finalResult.toString();
	}
	@Test
	public void testGenerateOutputExample1() throws Exception
	{
		AttributesImpl attrs = new AttributesImpl();
		TagNode html = new TagNode(null, "html", attrs);
    	TagNode body = new TagNode(html, "body", attrs);
    	html.addChild(body);
    	TagNode img = new TagNode(body, "img", attrs);
    	body.addChild(img);
    	TextNode textImage = new TextNode(img, "contents of image node");
    	img.addChild(textImage);
    	
    	HtmlSaxDiffOutput output = getOutput();
		output.generateOutput(html);
		
		String oldText = "<p> This is a blue book</p>";
		String newText = "<p> This is a blue book</p>";
		String result = diff(oldText, newText, output);
		
		assertTrue(result.contains(textImage.getText()));
		assertTrue(result.contains(newText));
		
	}
	
	@Test
	public void testGenerateOutputExample2() throws Exception
	{
		AttributesImpl attrs = new AttributesImpl();
        attrs.addAttribute("", "span", "span", "CDATA", "diff-tag-html");
        
		TagNode html = new TagNode(null, "html", attrs);
    	TextNode textImage = new TextNode(html, "contents of html page");
    	html.addChild(textImage);

    	Modification m = new Modification(ModificationType.ADDED, ModificationType.ADDED);
    	m.setFirstOfID(true);
    	textImage.setModification(m);
    	
    	HtmlSaxDiffOutput output = getOutput();
		output.generateOutput(html);
		
		String oldText = "<p> This is a blue book</p>";
		String newText = "<p> This is a big blue book</p>";
		String result = diff(oldText, newText, output);

		assertTrue(result.contains("span=\"diff-tag-html\""));
		assertTrue(result.contains("<span class=\"diff-html-added\""));
		assertTrue(result.contains("contents of html page"));
		
	}

	
	@Test
	public void testGenerateOutputExample3() throws Exception
	{
		
		TagNode html = new TagNode(null, "html", new AttributesImpl());
    	TextNode textImage = new TextNode(html, "contents of html page");
    	html.addChild(textImage);
    	
    	Modification m = new Modification(ModificationType.CONFLICT, ModificationType.CONFLICT);
    	m.setFirstOfID(true);
    	textImage.setModification(m);
    	
    	HtmlSaxDiffOutput output = getOutput();
    	output.generateOutput(html);
    	
    	String oldText = "<p> This is a blue book</p>";
		String newText = "<p> This is a big blue book</p>";
		String result = diff(oldText, newText, output);
		
		assertTrue(result.contains("contents of html page"));
		assertTrue(result.contains("<span class=\"diff-html-conflict\""));
		assertTrue(result.contains("<span class=\"diff-html-added\""));
	}
	
	@Test
	public void testGenerateOutputExample4() throws Exception
	{

		TagNode html = new TagNode(null, "html", new AttributesImpl());
    	TextNode textImage = new TextNode(html, "contents of html page");
    	html.addChild(textImage);
    	
    	Modification m = new Modification(ModificationType.REMOVED, ModificationType.REMOVED);
    	m.setFirstOfID(true);
    	textImage.setModification(m);
    	
    	HtmlSaxDiffOutput output = getOutput();
		output.generateOutput(html);
		
		String oldText = "<p> This is a big blue book</p>";
		String newText = "<p> This is a blue book</p>";
		String result = diff(oldText, newText, output);
		
		assertTrue(result.contains("contents of html page"));
		assertTrue(result.contains("<span class=\"diff-html-removed\" id=\"removed-diff--1\" previous=\"first-diff\" changeId=\"removed-diff--1\" next=\"last-diff\""));
	}
	
	
	@Test
	public void testGenerateOutputExample5() throws Exception
	{
		TagNode html = new TagNode(null, "html", new AttributesImpl());
    	TextNode textImage = new TextNode(html, "contents of html page");
    	html.addChild(textImage);
    	
    	Modification m = new Modification(ModificationType.CHANGED, ModificationType.CONFLICT);
    	textImage.setModification(m);
    	
    	HtmlSaxDiffOutput output = getOutput();
		output.generateOutput(html);

		String oldText = "<p> This is a blue book</p>";
		String newText = "<p id=\"sample\"> This is a blue book</p>";
		String result = diff(oldText, newText, output);
		
		assertTrue(result.contains("contents of html page"));
		assertTrue(result.contains("<span class=\"diff-html-changed\""));
	}
	
	
	@Test
	public void testGenerateOutputExample6() throws Exception
	{
		AttributesImpl attrs = new AttributesImpl();
        attrs.addAttribute("", "src", "src", "CDATA", "diff-tag-html");
        
		TagNode html = new TagNode(null, "html", new AttributesImpl());
    	TextNode textImage = new ImageNode(html, attrs);
    	html.addChild(textImage);
    	
    	Modification m = new Modification(ModificationType.CHANGED, ModificationType.REMOVED);
    	textImage.setModification(m);
    	
    	HtmlSaxDiffOutput output = getOutput();
		output.generateOutput(html);
		
		String oldText = "<p> This is a blue book</p>";
		String newText = "<p id=\"sample\"> This is a blue book</p>";
		String result = diff(oldText, newText, output);
		
		assertTrue(result.contains("<img src=\"diff-tag-html\""));
		assertTrue(result.contains("<span class=\"diff-html-removed\""));
		assertTrue(result.contains("<span class=\"diff-html-changed\""));
		assertTrue(result.contains("<p id=\"sample\">"));

	}
	
	@Test
	public void testGenerateOutputExample7() throws Exception
	{
		AttributesImpl attrs = new AttributesImpl();
        attrs.addAttribute("", "src", "src", "CDATA", "diff-tag-html");
        
		TagNode html = new TagNode(null, "html", new AttributesImpl());
    	TextNode textImage = new ImageNode(html, attrs);
    	html.addChild(textImage);
    	
    	Modification m = new Modification(ModificationType.NONE, ModificationType.NONE);
    	textImage.setModification(m);
    	
    	HtmlSaxDiffOutput output = getOutput();
		output.generateOutput(html);
		
		String oldText = "<p> This is a blue book</p>";
		String newText = "<p> This is a blue book</p>";
		String result = diff(oldText, newText, output);
		
		assertTrue(result.contains("<img src=\"diff-tag-html\""));
		assertTrue(result.contains(oldText));

	}
	
	@Test
	public void testGenerateOutputExample8() throws Exception
	{
		AttributesImpl attrs = new AttributesImpl();
        attrs.addAttribute("", "src", "src", "CDATA", "diff-tag-html");
        
		TagNode html = new TagNode(null, "html", new AttributesImpl());
    	TextNode textImage = new ImageNode(html, attrs);
    	html.addChild(textImage);
    	
    	Modification m = new Modification(ModificationType.ADDED, ModificationType.CONFLICT);
    	textImage.setModification(m);
    	
    	HtmlSaxDiffOutput output = getOutput();
		output.generateOutput(html);
		
		String oldText = "<p> This is a blue book</p>";
		String newText = "<p> This is a big blue book</p>";
		String result = diff(oldText, newText, output);
		
		assertTrue(result.contains("<img src=\"diff-tag-html\" changeType=\"diff-conflict-image\""));
		assertTrue(result.contains("<span class=\"diff-html-conflict"));
		assertTrue(result.contains("<span class=\"diff-html-added"));
	}
	
	
	@Test
	public void testGenerateOutputExample9() throws Exception
	{
		AttributesImpl attrs = new AttributesImpl();
        attrs.addAttribute("", "src", "src", "CDATA", "diff-tag-html");
        
		TagNode html = new TagNode(null, "html", new AttributesImpl());
    	TextNode textImage = new ImageNode(html, attrs);
    	html.addChild(textImage);
    	
    	Modification previous = new Modification(ModificationType.REMOVED, ModificationType.REMOVED);
    	Modification m = new Modification(ModificationType.ADDED, ModificationType.ADDED);
    	m.setPrevious(previous);
    	textImage.setModification(m);
    	
    	HtmlSaxDiffOutput output = getOutput();
		output.generateOutput(html);
		
		String oldText = "<p> This is a blue book</p>";
		String newText = "<p> This is a big book</p>";
		String result = diff(oldText, newText, output);
		
		assertTrue(result.contains("<img src=\"diff-tag-html\" changeType=\"diff-added-image\""));
		assertTrue(result.contains("<span class=\"diff-html-added"));
		assertTrue(result.contains("<span class=\"diff-html-removed"));

	}
	
	
	@Test
	public void testGenerateOutputExample10() throws Exception
	{
		AttributesImpl attrs = new AttributesImpl();
        attrs.addAttribute("", "src", "src", "CDATA", "diff-tag-html");
        
		TagNode html = new TagNode(null, "html", new AttributesImpl());
    	TextNode textImage = new ImageNode(html, attrs);
    	html.addChild(textImage);
    	
    	Modification next = new Modification(ModificationType.REMOVED, ModificationType.REMOVED);
    	Modification m = new Modification(ModificationType.ADDED, ModificationType.ADDED);
    	m.setNext(next);
    	textImage.setModification(m);
    	
    	HtmlSaxDiffOutput output = getOutput();
		output.generateOutput(html);
		
		String oldText = "<p> This is a blue book</p>";
		String newText = "<p> This is a big book</p>";
		String result = diff(oldText, newText, output);
		
		assertTrue(result.contains("<img src=\"diff-tag-html\" changeType=\"diff-added-image\""));
		assertTrue(result.contains("<span class=\"diff-html-added"));
		assertTrue(result.contains("<span class=\"diff-html-removed"));


	}
	
	@Test
	public void testAddAttributeExample1() throws Exception
	{
		AttributesImpl attrs = new AttributesImpl();
        attrs.addAttribute("", "src", "src", "CDATA", "diff-tag-html");
    	
    	Modification m = new Modification(ModificationType.CHANGED, ModificationType.CHANGED);
    	
    	HtmlSaxDiffOutput output = getOutput();
		
    	// Reflection to test private method
    	attrs = (AttributesImpl) executeMethod(output, "addAttributes", new Object[] { m, attrs });
		
		assertEquals(0, attrs.getIndex("src"));
		assertEquals(1, attrs.getIndex("changes"));
	}
	
	@Test
	public void testAddAttributeExample2() throws Exception
	{
		AttributesImpl attrs = new AttributesImpl();
        attrs.addAttribute("", "src", "src", "CDATA", "diff-tag-html");
    	
    	Modification m = new Modification(ModificationType.ADDED, ModificationType.ADDED);
    	
    	HtmlSaxDiffOutput output = getOutput();
		
    	// Reflection to test private method
    	attrs = (AttributesImpl) executeMethod(output, "addAttributes", new Object[] { m, attrs });
		
		assertEquals(0, attrs.getIndex("src"));
		assertEquals(1, attrs.getIndex("previous"));
	}
	
	@Test
	public void testAddAttributeExample3() throws Exception
	{
		AttributesImpl attrs = new AttributesImpl();
        attrs.addAttribute("", "src", "src", "CDATA", "diff-tag-html");
    	
    	Modification m = new Modification(ModificationType.REMOVED, ModificationType.REMOVED);
    	Modification next = new Modification(ModificationType.ADDED, ModificationType.ADDED);
    	m.setPrevious(next);
    	
    	HtmlSaxDiffOutput output = getOutput();
		
    	// Reflection to test private method
    	attrs = (AttributesImpl) executeMethod(output, "addAttributes", new Object[] { m, attrs });
		
		assertEquals(0, attrs.getIndex("src"));
		assertEquals(1, attrs.getIndex("previous"));
	}
	
	@Test
	public void testAddAttributeExample4() throws Exception
	{
		AttributesImpl attrs = new AttributesImpl();
        attrs.addAttribute("", "src", "src", "CDATA", "diff-tag-html");
    	
    	Modification m = new Modification(ModificationType.REMOVED, ModificationType.REMOVED);
    	Modification next = new Modification(ModificationType.ADDED, ModificationType.ADDED);
    	m.setNext(next);
    	
    	HtmlSaxDiffOutput output = getOutput();
    	// Reflection to test private method
    	attrs = (AttributesImpl) executeMethod(output, "addAttributes", new Object[] { m, attrs });
	
		assertEquals(0, attrs.getIndex("src"));
		assertEquals(1, attrs.getIndex("previous"));
		assertEquals(2, attrs.getIndex("changeId"));
		assertEquals(3, attrs.getIndex("next"));
	}
	
	@Test
	public void testWriteImageExample1() throws Exception
	{
		AttributesImpl attrs = new AttributesImpl();
        attrs.addAttribute("", "src", "src", "CDATA", "diff-tag-html");
    	
        TagNode html = new TagNode(null, "html", new AttributesImpl());
    	ImageNode textImage = new ImageNode(html, attrs);
    	Modification m = new Modification(ModificationType.ADDED, ModificationType.ADDED);
    	textImage.setModification(m);
    	html.addChild(textImage);

    	HtmlSaxDiffOutput output = getOutput();
    	// Reflection to test private method
    	textImage = (ImageNode) executeMethod(output, "writeImage", new Object[] { textImage });
    	attrs = textImage.getAttributes();
    	
    	assertEquals("diff-added-image",attrs.getValue(1));
	}
	
	@Test
	public void testWriteImageExample2() throws Exception
	{
		AttributesImpl attrs = new AttributesImpl();
        attrs.addAttribute("", "src", "src", "CDATA", "diff-tag-html");
    	
        TagNode html = new TagNode(null, "html", new AttributesImpl());
    	ImageNode textImage = new ImageNode(html, attrs);
    	Modification m = new Modification(ModificationType.REMOVED, ModificationType.REMOVED);
    	textImage.setModification(m);
    	html.addChild(textImage);

    	HtmlSaxDiffOutput output = getOutput();
    	// Reflection to test private method
    	textImage = (ImageNode) executeMethod(output, "writeImage", new Object[] { textImage });
    	attrs = textImage.getAttributes();
    	
    	assertEquals("diff-removed-image",attrs.getValue(1));
	}
	
	@Test
	public void testWriteImageExample3() throws Exception
	{
		AttributesImpl attrs = new AttributesImpl();
        attrs.addAttribute("", "src", "src", "CDATA", "diff-tag-html");
    	
        TagNode html = new TagNode(null, "html", new AttributesImpl());
    	ImageNode textImage = new ImageNode(html, attrs);
    	Modification m = new Modification(ModificationType.CONFLICT, ModificationType.CONFLICT);
    	textImage.setModification(m);
    	html.addChild(textImage);

    	HtmlSaxDiffOutput output = getOutput();
    	// Reflection to test private method
    	textImage = (ImageNode) executeMethod(output, "writeImage", new Object[] { textImage });
    	attrs = textImage.getAttributes();
    	
    	assertEquals("diff-conflict-image",attrs.getValue(1));
	}
	
	@Test
	public void testWriteImageExample4() throws Exception
	{
		AttributesImpl attrs = new AttributesImpl();
        attrs.addAttribute("", "src", "src", "CDATA", "diff-tag-html");
    	
        TagNode html = new TagNode(null, "html", new AttributesImpl());
    	ImageNode textImage = new ImageNode(html, attrs);
    	Modification m = new Modification(ModificationType.NONE, ModificationType.NONE);
    	textImage.setModification(m);
    	html.addChild(textImage);

    	HtmlSaxDiffOutput output = getOutput();
    	// Reflection to test private method
    	textImage = (ImageNode) executeMethod(output, "writeImage", new Object[] { textImage });
    	attrs = textImage.getAttributes();
    	
    	assertEquals(null,attrs.getValue(1));
	}
	
	
}
