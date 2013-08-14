package org.outerj.daisy.diff.html.ancestor.tagtostring;

import static org.junit.Assert.assertEquals;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;
import org.outerj.daisy.diff.html.ancestor.ChangeText;
import org.outerj.daisy.diff.html.ancestor.TagChangeSematic;
import org.outerj.daisy.diff.html.dom.TagNode;
import org.xml.sax.helpers.AttributesImpl;

public class NoContentTagToStringTest {
	@Test
	public void testGetRemovedDescription() throws Exception {
		 
		TagNode div = new TagNode(null, "div", new AttributesImpl());
    	
    	ResourceBundle bundle = ResourceBundle.getBundle("org/outerj/daisy/diff/html/ancestor/tagtostring/messages", Locale.ENGLISH);
    	NoContentTagToString tagMoved = new NoContentTagToString(div, TagChangeSematic.MOVED, bundle);
    	
    	ChangeText changeText = new ChangeText(100);
    	String newText = "Div tag to add styles";
    	changeText.addText(newText);
    	
    	tagMoved.getRemovedDescription(changeText);
 
    	assertEquals(TagChangeSematic.MOVED.toString(), tagMoved.sem.toString());
    	assertEquals("Division", tagMoved.getDescription());

	}

	@Test
	public void testGetAddedDescription() throws Exception {
		 
		TagNode form = new TagNode(null, "form", new AttributesImpl());
    	
    	ResourceBundle bundle = ResourceBundle.getBundle("org/outerj/daisy/diff/html/ancestor/tagtostring/messages", Locale.ENGLISH);
		
    	NoContentTagToString tagMoved = new NoContentTagToString(form, TagChangeSematic.STYLE, bundle);
    	
    	ChangeText changeText = new ChangeText(100);
    	String newText = "Form to collect data";
    	changeText.addText(newText);
    	
    	tagMoved.getAddedDescription(changeText);
    	
    	assertEquals(TagChangeSematic.STYLE.toString(), tagMoved.sem.toString());
    	assertEquals("Form", tagMoved.getDescription());

	}
}
