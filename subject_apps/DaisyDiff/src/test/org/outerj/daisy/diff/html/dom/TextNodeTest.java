package org.outerj.daisy.diff.html.dom;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import org.xml.sax.helpers.AttributesImpl;


/**
 * author @icewariya
 */

public class TextNodeTest
{
	 @Test
	 public void testCopyTree() throws Exception
     {
    	TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TextNode textRoot = new TextNode(root, "contents of root node");
    	TextNode copyRoot = (TextNode) textRoot.copyTree();
    	
    	assertEquals(textRoot.getText(), copyRoot.getText());
	 }
	 
	 @Test
	 public void testGetLeftRightMostChild() throws Exception
     { 
		TagNode tagRoot = new TagNode(null, "root", new AttributesImpl());
	    TextNode textRoot = new TextNode(tagRoot, "root");
	    
	    assertEquals(textRoot, textRoot.getRightMostChild());
	    assertEquals(textRoot, textRoot.getLeftMostChild());     	
	 }
	 
	 @Test
	 public void testGetModificationText() throws Exception
     {
    	TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TextNode textRoot = new TextNode(root, "root");
    	textRoot.setModification(null);
    	
	    assertEquals(null, textRoot.getModification());
	 }
	 
	 @Test
	 public void testGetText() throws Exception
     {
    	TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TextNode textRoot = new TextNode(root, "root");
    	
	    assertEquals("root", textRoot.getText());
	 }
	 
	 @Test
	 public void testIsSameText() throws Exception
     {
    	TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TextNode textRoot = new TextNode(root, "root");
    	TextNode textBody = new TextNode(root, "root");
    	
    	assertEquals(true, textRoot.isSameText(textBody));
    	assertEquals(false, textRoot.isSameText(null));     	
	 }
	 
	 @Test
	 public void testGetMinimalDeletedSet() throws Exception
     {
    	TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TextNode textRoot = new TextNode(root, "contents of root node");
    	TagNode intermediate = new TagNode(root, "intermediate", new AttributesImpl());
    	TextNode textIntermediate = new TextNode(root, "contents of intermediate node");
    	List<Node> emptySet = new ArrayList<Node>();
    	
    	assertEquals(emptySet, textRoot.getMinimalDeletedSet(0));  
	 }
	 
}
