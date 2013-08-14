package org.outerj.daisy.diff.html.dom;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.xml.sax.helpers.AttributesImpl;

import java.util.Arrays;

/**
 * Tests (a tiny part of the) functionality of {@link Node}.
 */

public class NodeTest
{
    @Test
    public void testGetParentTree() throws Exception
    {
        TagNode root = new TagNode(null, "root", new AttributesImpl());
        TagNode intermediate = new TagNode(root, "middle", new AttributesImpl());
        root.addChild(intermediate);
        TagNode leaf = new TagNode(intermediate, "leaf", new AttributesImpl());
        intermediate.addChild(leaf);
        assertEquals(Arrays.asList(root, intermediate), leaf.getParentTree());
    }

    /**
     * 	
     * author @icewariya
     */
    
    @Test
    public void testGetRoot() throws Exception
    {
        TagNode root = new TagNode(null, "root", new AttributesImpl());
        TagNode intermediate = new TagNode(root, "middle", new AttributesImpl());
        root.addChild(intermediate);
        assertEquals(root, intermediate.getRoot());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNullGetLastCommonParent() throws Exception
    {
        TagNode root = new TagNode(null, "root", new AttributesImpl());
        TagNode intermediate = new TagNode(root, "middle", new AttributesImpl());
        root.addChild(intermediate);
        try {
        	root.getLastCommonParent(null);
        } catch (IllegalArgumentException ex) {
            assertEquals("The given TextNode is null", ex.getMessage());
            throw ex;
        }
    }
    
    @Test
    public void testGetLastCommonParent() throws Exception
    {
        TagNode root = new TagNode(null, "root", new AttributesImpl());
        TagNode intermediate = new TagNode(root, "intermediate", new AttributesImpl());
        root.addChild(intermediate);
        TagNode leaf1 = new TagNode(intermediate, "leaf", new AttributesImpl());
        intermediate.addChild(leaf1);
        TagNode leaf2 = new TagNode(intermediate, "leaf", new AttributesImpl());
        intermediate.addChild(leaf2);
        
        TagNode parent = new TagNode(null, "parent", new AttributesImpl());
        TagNode middle = new TagNode(parent, "middle", new AttributesImpl());
        parent.addChild(middle);
        TagNode leafNode = new TagNode(middle, "leaf", new AttributesImpl());
        middle.addChild(leafNode);
        
        assertEquals(intermediate, leaf1.getLastCommonParent(leaf2).getLastCommonParent());
        assertEquals(1, leaf1.getLastCommonParent(leaf2).getLastCommonParentDepth());
        assertEquals(0, leaf1.getLastCommonParent(leaf2).getIndexInLastCommonParent());
        assertEquals(intermediate, leaf1.getLastCommonParent(leaf1).getLastCommonParent());
        assertEquals(parent,leafNode.getLastCommonParent(intermediate).getLastCommonParent());
        assertEquals(root,leaf2.getLastCommonParent(middle).getLastCommonParent());
        assertEquals(parent,leafNode.getLastCommonParent(leaf2).getLastCommonParent());
        
    }
    
    @Test
    public void testSetParentRoot() throws Exception
    {
    	TagNode root = new TagNode(null, "root", new AttributesImpl());
        TagNode middle = new TagNode(root, "middle", new AttributesImpl());
        middle.setRoot(root);
        TagNode leaf = new TagNode(root, "leaf", new AttributesImpl());
        leaf.setParent(middle);
        assertEquals(leaf.getParent(),middle);
        leaf.setParent(null);
        assertEquals(leaf.getParent(),null);
    	
    }
    
    @Test
    public void testInPre() throws Exception
    {
        TagNode preRoot = new TagNode(null, "pre", new AttributesImpl());
        TagNode intermediate = new TagNode(preRoot, "intermediate", new AttributesImpl());
        preRoot.addChild(intermediate);
        TagNode leaf = new TagNode(intermediate, "leaf", new AttributesImpl());
        intermediate.addChild(leaf);
        assertEquals(true, leaf.inPre());
        
        TagNode root = new TagNode(null, "root", new AttributesImpl());
        TagNode middle = new TagNode(root, "middle", new AttributesImpl());
        root.addChild(middle);
        TagNode leafNode = new TagNode(middle, "leaf", new AttributesImpl());
        middle.addChild(leafNode);
        assertEquals(false, leafNode.inPre());

    }
    
    @Test
    public void testIsWhiteBeforeAfter() throws Exception
    {
        TagNode root = new TagNode(null, "root", new AttributesImpl());
        TagNode intermediate = new TagNode(root, "middle", new AttributesImpl());
        root.addChild(intermediate);

        assertEquals(false, root.isWhiteBefore());
        intermediate.setWhiteBefore(true);
        assertEquals(true, intermediate.isWhiteBefore());
        root.setWhiteAfter(true);
        assertEquals(true, root.isWhiteAfter());
        
    }
}
