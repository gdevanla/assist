package org.outerj.daisy.diff.html.dom;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.xml.sax.helpers.AttributesImpl;


/**
 * author @icewariya
 */

public class ImageNodeTest
{

	@Test
    public void testIsSameText() throws Exception
    {
		AttributesImpl nodeAttrs = new AttributesImpl();
        nodeAttrs.addAttribute("", "src", "src", "src", "location of image tag");
        AttributesImpl attrs = new AttributesImpl();
        attrs.addAttribute("", "src", "src", "src", "different location for this tag");
        
    	TagNode root = new TagNode(null, "root", nodeAttrs);
    	TagNode img = new TagNode(root, "img", nodeAttrs);
    	
    	ImageNode imgNode = new ImageNode(img, nodeAttrs);
    	ImageNode rootNode = new ImageNode(root, nodeAttrs);
    	ImageNode compareNode = new ImageNode(root, attrs);
    	
    	assertTrue(imgNode.isSameText(rootNode));
    	assertFalse(compareNode.isSameText(rootNode));
    	assertFalse(imgNode.isSameText(null));
    	assertFalse(rootNode.isSameText(root));
    	
    	assertEquals("src", imgNode.getAttributes().getQName(0));
    	
    	
    }
    
    
}
