package org.outerj.daisy.diff.html.dom;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import org.xml.sax.helpers.AttributesImpl;

/**
 * author @icewariya
 */

public class TagNodeTest
{
	@Test
    public void testAddChild() throws Exception
    {
    	TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TagNode intermediate = new TagNode(root, "middle", new AttributesImpl());
    	root.addChild(intermediate);
    	assertEquals(intermediate, root.getChild(0));  	
    }
    
    @Test(expected = NullPointerException.class)
    public void testAddChildNullException() throws Exception
    {
    	TagNode root = new TagNode(null, "root", new AttributesImpl());
    	try {
    		root.addChild(null);
    	} catch (NullPointerException ex) {
    		assertEquals(null, ex.getMessage());
    		throw ex;
    	}
    }
    
    @Test(expected = IllegalStateException.class)
    public void testAddChildException() throws Exception
    {
    	TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TagNode errorRoot = new TagNode(null, "errorRoot", new AttributesImpl());
    	TagNode intermediate = new TagNode(errorRoot, "middle", new AttributesImpl());
    	try {
    	    root.addChild(intermediate);
    	} catch(IllegalStateException ex) {
    		assertEquals("The new child must have this node as a parent.", ex.getMessage());
            throw ex;
    		
    	}
    }
    
    @Test
    public void testSetRoot() throws Exception
    {
    	TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TagNode intermediate = new TagNode(root, "middle", new AttributesImpl());
    	TagNode leaf = new TagNode(intermediate, "leaf", new AttributesImpl());
        intermediate.addChild(leaf);
    	intermediate.setRoot(root);
    	assertEquals(root, intermediate.getRoot());
    }
    
    @Test
    public void testGetIndexOf() throws Exception
    {
    	TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TagNode intermediate = new TagNode(root, "middle", new AttributesImpl());
    	root.addChild(intermediate);
    	TagNode leaf1 = new TagNode(intermediate, "leaf1", new AttributesImpl());
        intermediate.addChild(leaf1);
        TagNode leaf2 = new TagNode(intermediate, "leaf2", new AttributesImpl());
        intermediate.addChild(leaf2);
        assertEquals(-1,root.getIndexOf(leaf1));
        assertEquals(2,intermediate.getIndexOf(leaf2));
    	
    }
    
    @Test
    public void testAddChildIndex() throws Exception
    {
    	TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TagNode intermediate = new TagNode(root, "middle", new AttributesImpl());
    	root.addChild(0,intermediate);
    	TagNode leaf = new TagNode(root, "leaf", new AttributesImpl());
    	root.addChild(1,leaf);
    	try {
    	root.addChild(4,null);
    	} catch(NullPointerException ex) {
    		assertEquals(null, ex.getMessage());
    	}
    	assertEquals(leaf, root.getChild(1));
    }
    
    @Test(expected = NullPointerException.class)
    public void testAddChildIndexNullException() throws Exception
    {
    	TagNode root = new TagNode(null, "root", new AttributesImpl());
    	try {
    	root.addChild(1,null);
    	} catch(NullPointerException ex) {
    		assertEquals(null, ex.getMessage());
    		throw ex;
    	}
    }
    
    @Test(expected = IllegalStateException.class)
    public void testAddChildIndexException() throws Exception
    {
    	TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TagNode errorRoot = new TagNode(null, "errorRoot", new AttributesImpl());
    	TagNode intermediate = new TagNode(errorRoot, "middle", new AttributesImpl());
    	try {
    	root.addChild(0,intermediate);
    	} catch(IllegalStateException ex) {
    		assertEquals("The new child must have this node as a parent.", ex.getMessage());
            throw ex;
    	}
    }
    
    @Test
    public void testGetChild() throws Exception
    {
    	TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TagNode intermediate = new TagNode(root, "middle", new AttributesImpl());
    	root.addChild(intermediate);
    	TagNode leaf1 = new TagNode(intermediate, "leaf1", new AttributesImpl());
        intermediate.addChild(leaf1);
        TagNode leaf2 = new TagNode(intermediate, "leaf2", new AttributesImpl());
        intermediate.addChild(leaf2);
        assertEquals(leaf2,intermediate.getChild(2));
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetChildException() throws Exception
    {
    	int i = 5;
    	TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TagNode intermediate = new TagNode(root, "middle", new AttributesImpl());
    	root.addChild(intermediate);
    	try {
    		root.getChild(i);
    	} catch(IndexOutOfBoundsException ex) {
    		String errorMsg = "Index: "+i+", Size: 2";
    		assertEquals(errorMsg,ex.getMessage()); 
    		throw ex;
    	}
    }
    
    @Test
    public void testGetNbChildren() throws Exception
    {
    	TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TagNode intermediate = new TagNode(root, "middle", new AttributesImpl());
    	root.addChild(intermediate);
    	TagNode leaf = new TagNode(root, "leaf", new AttributesImpl());
        root.addChild(leaf);
        assertEquals(4,root.getNbChildren());
        assertEquals(0,leaf.getNbChildren());
    }
    
    @Test(expected = NullPointerException.class)
    public void testGetNbChildrenException() throws Exception
    {
    	TagNode root = null;
    	try {
    		root.getNbChildren();
    	} catch (NullPointerException ex) {
    		throw ex;
    	}
    }

    @Test
    public void testIterator() throws Exception
    {
    	TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TagNode intermediate = new TagNode(root, "middle", new AttributesImpl());
    	root.addChild(intermediate);
    	TagNode leaf = new TagNode(root, "leaf", new AttributesImpl());
        root.addChild(leaf);
        assertEquals(intermediate,root.iterator().next());


    }
    
    @Test(expected = NullPointerException.class)
    public void testIteratorException() throws Exception
    {
    	TagNode root = null;
    	try {
    		root.iterator().next();
    	} catch (NullPointerException ex) {
    		throw ex;
    	}

    }
    
    @Test
    public void testGetQName() throws Exception
    {
    	TagNode root = new TagNode(null, "root", new AttributesImpl());
    	assertEquals("root",root.getQName());
    }
    
    @Test
    public void testIsSameTag() throws Exception
    {
    	TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TagNode compareRoot = new TagNode(null, "root", new AttributesImpl());
    	TagNode intermediate = new TagNode(root, "middle", new AttributesImpl());
    	TagNode compareIntermediate = new TagNode(compareRoot, "middle", new AttributesImpl());
    	
    	root.addChild(intermediate);
    	compareRoot.addChild(compareIntermediate);

    	assertEquals(true, root.isSameTag(root));
    	assertEquals(false, root.isSameTag(null));
    	assertEquals(true, root.isSameTag(compareRoot));
    	assertEquals(false, root.isSameTag(compareIntermediate));
    	assertEquals(true, intermediate.isSameTag(compareIntermediate));
    
    }
    
    @Test
    public void testIsSimilarTag() throws Exception
    {
    	TagNode root1 = new TagNode(null, "root", new AttributesImpl());
    	TagNode root2 = new TagNode(null, "root", new AttributesImpl());
    	TagNode intermediate = new TagNode(null, "middle", new AttributesImpl());
    	TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TextNode textNode = new TextNode(root,"Content of the root node");
    	WhiteSpaceNode whiteSpaceNode = new WhiteSpaceNode(root,"root",textNode);
    	
    	assertEquals(false, root2.isSimilarTag(whiteSpaceNode));
    	assertEquals(true,root1.isSimilarTag(root2));
    	assertEquals(false,intermediate.isSimilarTag(root2));
    
    }
    
    @Test
    public void testGetOpeningTag() throws Exception
    {
    	String html = "<table width=\"500\" height=\"75\">";
    	AttributesImpl nodeAttrs = new AttributesImpl();
        nodeAttrs.addAttribute("", "", "width", "width", "500");
        nodeAttrs.addAttribute("", "", "height", "height", "75");
        
    	TagNode root = new TagNode(null, "table", nodeAttrs);
    	TagNode intermediate = new TagNode(null, "middle", new AttributesImpl());
    	assertEquals("<middle>",intermediate.getOpeningTag());
    	assertEquals(html, root.getOpeningTag());

    }
    
    @Test
    public void testGetEndTag() throws Exception
    {
    	TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TagNode intermediate = new TagNode(root, "middle", new AttributesImpl());
    	root.addChild(intermediate);
    	assertEquals("</middle>",intermediate.getEndTag());
    
    }
    
    @Test
    public void testHashCode() throws Exception
    {
    	TagNode root = new TagNode(null, "root", new AttributesImpl());
    	int hashCode = root.hashCode();
    	assertEquals(hashCode,root.hashCode());
    
    }
    
    @Test
    public void testIsBlockLevel() throws Exception
    {
    	TagNode root = new TagNode(null, "html", new AttributesImpl());
    	TagNode intermediate = new TagNode(root, "middle", new AttributesImpl());
    	root.addChild(intermediate);
    	assertEquals(false,intermediate.isBlockLevel());
    	assertEquals(true,root.isBlockLevel());
    }
    
    @Test
    public void testIsInline() throws Exception
    {
    	TagNode root = new TagNode(null, "ul", new AttributesImpl());
    	TagNode intermediate = new TagNode(root, "middle", new AttributesImpl());
    	root.addChild(intermediate);
    	assertEquals(true,intermediate.isInline());
    	assertEquals(false,root.isInline());
    	assertEquals(false,root.isInline(root.getQName()));
    	assertEquals(true,root.isInline(intermediate.getQName()));
    }
    
    @Test
    public void testCopyTree() throws Exception
    {
    	TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TagNode intermediate = new TagNode(root, "middle", new AttributesImpl());
    	root.addChild(intermediate);
    	TagNode leaf1 = new TagNode(intermediate, "leaf1", new AttributesImpl());
    	intermediate.addChild(leaf1);
    	TagNode leaf2 = new TagNode(intermediate, "leaf2", new AttributesImpl());
    	intermediate.addChild(leaf2);
    	assertEquals(root, root.copyTree());
    }
    
    @Test
    public void testGetMatchRatio() throws Exception
    {
    	TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TextNode textNode = new TextNode(root,"Content of the root node");
    	TagNode intermediate = new TagNode(root, "root", new AttributesImpl());
    	TextNode text = new TextNode(intermediate,"Content of the intermdeiate node");
    	assertEquals(0.25, root.getMatchRatio(intermediate),0.1);
    }
    
    @Test
    public void testGetLeftRightMostChild() throws Exception
    {
    	TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TagNode intermediate = new TagNode(root, "middle", new AttributesImpl());
    	root.addChild(intermediate);
    	TagNode leaf1 = new TagNode(intermediate, "leaf1", new AttributesImpl());
    	intermediate.addChild(leaf1);
    	TagNode leaf2 = new TagNode(intermediate, "leaf2", new AttributesImpl());
    	intermediate.addChild(leaf2);
    	assertEquals(leaf2, root.getRightMostChild());
    	assertEquals(leaf1, root.getLeftMostChild());
    }
    
    @Test
    public void testIsPre() throws Exception
    {
    	TagNode root = new TagNode(null, "pre", new AttributesImpl());
    	TagNode intermediate = new TagNode(root, "middle", new AttributesImpl());
    	assertEquals(true, root.isPre());
    	assertEquals(false, intermediate.isPre());
    
    }
    
}
