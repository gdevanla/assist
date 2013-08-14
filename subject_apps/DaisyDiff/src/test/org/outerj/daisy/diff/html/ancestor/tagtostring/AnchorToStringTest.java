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

public class AnchorToStringTest {
	@Test
	public void testAnchor() throws Exception {
        
		TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TagNode intermediate = new TagNode(root, "middle", new AttributesImpl());
    	root.addChild(intermediate);
    	
    	ResourceBundle bundle = ResourceBundle.getBundle("org/outerj/daisy/diff/html/ancestor/tagtostring/messages", Locale.ENGLISH);
		
    	AnchorToString anchor = new AnchorToString(root, TagChangeSematic.STYLE, bundle);

		assertEquals("!diff-root!", anchor.getDescription().toString());
		
	}
	
	@Test
	public void testAddAttributes() throws Exception {
	    
		AttributesImpl attrs = new AttributesImpl();
        attrs.addAttribute("", "class", "class", "CDATA", "diff-tag-html");
        attrs.addAttribute("", "href", "href", "CDATA", "diff-withdestination");
        
		TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TagNode intermediate = new TagNode(root, "middle", new AttributesImpl());
    	root.addChild(intermediate);
    	
    	ResourceBundle bundle = ResourceBundle.getBundle("org/outerj/daisy/diff/html/ancestor/tagtostring/messages", Locale.ENGLISH);
		
    	AnchorToString anchor = new AnchorToString(root, TagChangeSematic.STYLE, bundle);
		
    	ChangeText changeText = new ChangeText(10);
    	String newText = "<a href=\"\">Click here</a>";
    	changeText.addText(newText);
    	
		anchor.addAttributes(changeText, attrs);
		
		assertEquals("Added",anchor.getAdded());
		assertTrue(changeText.toString().contains("href"));
		assertTrue(changeText.toString().contains("class"));
	}
}
