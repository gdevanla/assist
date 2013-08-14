package org.outerj.daisy.diff.html.dom;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.xml.sax.helpers.AttributesImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * author @icewariya
 */

public class BodyNodeTest
{
    @Test
    public void testCopyTreeWithNoChildren() throws Exception
    {
    	BodyNode body = new BodyNode();
        assertEquals(body, body.copyTree());     
    }
    
    @Test
    public void testCopyTreeWithChildren() throws Exception
    {
    	BodyNode body = new BodyNode();
    	TagNode  intermediate = new TagNode(body, "middle", new AttributesImpl());
        body.addChild(intermediate);
        TagNode leaf = new TagNode(intermediate, "leaf", new AttributesImpl());
        intermediate.addChild(leaf);
        assertEquals(body, body.copyTree());     
    }
    
    @Test
    public void testGetMinimalDeletedSet() throws Exception
    {
    	BodyNode body = new BodyNode();
    	TagNode  intermediate = new TagNode(body, "middle", new AttributesImpl());
        body.addChild(intermediate);
        TagNode leaf = new TagNode(intermediate, "leaf", new AttributesImpl());
        intermediate.addChild(leaf);
        
        List<Node> nodes = new ArrayList<Node>();
        assertEquals(nodes, body.getMinimalDeletedSet(0));
        assertEquals(nodes, leaf.getMinimalDeletedSet(0));
    }
    

}
