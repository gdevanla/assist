package org.outerj.daisy.diff.html.ancestor.tagtostring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;
import org.outerj.daisy.diff.html.ancestor.ChangeText;
import org.outerj.daisy.diff.html.ancestor.TagChangeSematic;
import org.outerj.daisy.diff.html.dom.TagNode;
import org.xml.sax.helpers.AttributesImpl;

/*
 * @author icewariya
 */

public class TagToStringTest {
	
	@Test
	public void testDiffs() throws Exception {
		 
		TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TagNode intermediate = new TagNode(root, "middle", new AttributesImpl());
    	root.addChild(intermediate);
    	
    	ResourceBundle bundle = ResourceBundle.getBundle("org/outerj/daisy/diff/html/ancestor/tagtostring/messages", Locale.ENGLISH);
		
    	TagToString tagToString = new TagToString(root, TagChangeSematic.STYLE, bundle);
    	
    	assertEquals("Moved to",tagToString.getMovedTo());
    	assertEquals("Style added",tagToString.getStyleAdded());
    	assertEquals("Added",tagToString.getAdded());
    	assertEquals("Moved out of",tagToString.getMovedOutOf());
    	assertEquals("Style removed",tagToString.getStyleRemoved());
    	assertEquals("Removed",tagToString.getRemoved());
    	assertEquals("Removed",tagToString.getRemoved());
    	assertEquals("!diff-root!", tagToString.getDescription());
	}

	@Test
	public void testGetRemovedDescription() throws Exception {
		 
		TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TagNode intermediate = new TagNode(root, "middle", new AttributesImpl());
    	root.addChild(intermediate);
    	
    	ResourceBundle bundle = ResourceBundle.getBundle("org/outerj/daisy/diff/html/ancestor/tagtostring/messages", Locale.ENGLISH);
		
    	TagToString tagMoved = new TagToString(root, TagChangeSematic.MOVED, bundle);
    	TagToString tagStyle = new TagToString(root, TagChangeSematic.STYLE, bundle);
    	TagToString tagUnknown = new TagToString(root, TagChangeSematic.UNKNOWN, bundle);
    	
    	ChangeText changeText = new ChangeText(10);
    	String newText = "<a href=\"\">Click here</a>";
    	changeText.addText(newText);
    	
    	tagMoved.getRemovedDescription(changeText);
    	tagStyle.getRemovedDescription(changeText);
    	tagUnknown.getRemovedDescription(changeText);
 
    	assertEquals(TagChangeSematic.MOVED.toString(), tagMoved.sem.toString());
    	assertEquals("<root>", tagMoved.getHtmlLayoutChange().getOpeningTag());
    	
    	assertTrue(changeText.toString().contains("!diff-root"));
    	assertTrue(changeText.toString().contains("moved"));
    	assertTrue(changeText.toString().contains("style"));
    	assertTrue(changeText.toString().contains("removed"));
	}

	@Test
	public void testGetAddedDescription() throws Exception {
		 
		TagNode root = new TagNode(null, "html", new AttributesImpl());
    	TagNode intermediate = new TagNode(root, "body", new AttributesImpl());
    	root.addChild(intermediate);
    	
    	ResourceBundle bundle = ResourceBundle.getBundle("org/outerj/daisy/diff/html/ancestor/tagtostring/messages", Locale.ENGLISH);
		
    	TagToString tagMoved = new TagToString(root, TagChangeSematic.MOVED, bundle);
    	TagToString tagStyle = new TagToString(root, TagChangeSematic.STYLE, bundle);
    	TagToString tagUnknown = new TagToString(root, TagChangeSematic.UNKNOWN, bundle);
    	
    	ChangeText changeText = new ChangeText(10);
    	String newText = "<a href=\"\">Click here</a>";
    	changeText.addText(newText);
    	
    	tagMoved.getAddedDescription(changeText);
    	tagStyle.getAddedDescription(changeText);
    	tagUnknown.getAddedDescription(changeText);
    	
    	assertEquals(TagChangeSematic.MOVED.toString(), tagMoved.sem.toString());
    	assertEquals("</html>", tagStyle.getHtmlLayoutChange().getEndingTag());
    	
    	assertTrue(changeText.toString().contains("Moved"));
    	assertTrue(changeText.toString().contains("style"));
    	assertTrue(changeText.toString().contains("added"));
	}
	
	@Test
	public void testAddAttributes() throws Exception {
		 
		AttributesImpl attrs = new AttributesImpl();
        attrs.addAttribute("", "src", "src", "CDATA", "source");
        attrs.addAttribute("", "width", "width", "CDATA", "width");
        attrs.addAttribute("", "height", "height", "CDATA", "height");
        attrs.addAttribute("", "class", "class", "CDATA", "height");
        
		TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TagNode intermediate = new TagNode(root, "middle", new AttributesImpl());
    	root.addChild(intermediate);
    	
    	ResourceBundle bundle = ResourceBundle.getBundle("org/outerj/daisy/diff/html/ancestor/tagtostring/messages", Locale.ENGLISH);
		
    	TagToString tagMoved = new TagToString(root, TagChangeSematic.MOVED, bundle);
    	
    	ChangeText changeText = new ChangeText(10);
    	String newText = "<a href=\"\">Click here</a>";
    	changeText.addText(newText);
    	
    	tagMoved.addAttributes(changeText, attrs);
    	
    	assertEquals(TagChangeSematic.MOVED.toString(), tagMoved.sem.toString());
    	assertEquals(root, tagMoved.node);
    	
    	assertTrue(changeText.toString().contains("source"));
    	assertTrue(changeText.toString().contains("height"));
    	assertTrue(changeText.toString().contains("width"));
    	
	}

}
