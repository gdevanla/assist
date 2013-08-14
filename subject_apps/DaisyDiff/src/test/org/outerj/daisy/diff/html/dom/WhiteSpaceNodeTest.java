package org.outerj.daisy.diff.html.dom;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.xml.sax.helpers.AttributesImpl;


/**
 * author @icewariya
 */

public class WhiteSpaceNodeTest
{
    @Test
    public void testWhiteSpaceNode() throws Exception
    {
    	TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TextNode textNode = new TextNode(root,"Content of the root node");
    	Node exceptionNode = root;
    	
    	WhiteSpaceNode whiteSpaceNode = new WhiteSpaceNode(root,"root",textNode);
    	WhiteSpaceNode whiteSpaceNodeNullPointer = new WhiteSpaceNode(null,"root",null);
    	WhiteSpaceNode whiteSpaceNodeClassCast = new WhiteSpaceNode(root,"root", exceptionNode);
    	
    	assertEquals(root, whiteSpaceNode.parent);
    	assertEquals(null, whiteSpaceNodeNullPointer.parent);
    	assertEquals(root, whiteSpaceNodeClassCast.parent);
    	assertTrue(whiteSpaceNode.isSameText(whiteSpaceNodeClassCast));
    	
    	
    }
    
    @Test
    public void testIsWhiteSpace() throws Exception
    {
    	assertEquals(false, WhiteSpaceNode.isWhiteSpace('a'));
    	assertEquals(true, WhiteSpaceNode.isWhiteSpace('\n'));
    	assertEquals(true, WhiteSpaceNode.isWhiteSpace(' '));
    	assertEquals(true, WhiteSpaceNode.isWhiteSpace('\t'));
    	assertEquals(true, WhiteSpaceNode.isWhiteSpace('\r'));
    }
    
}
