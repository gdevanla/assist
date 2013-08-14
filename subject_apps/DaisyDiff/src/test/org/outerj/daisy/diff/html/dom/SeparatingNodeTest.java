package org.outerj.daisy.diff.html.dom;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import org.xml.sax.helpers.AttributesImpl;


/**
 * author @icewariya
 */

public class SeparatingNodeTest
{
    @Test
    public void testEquals() throws Exception
    {
    	TagNode root = new TagNode(null, "root", new AttributesImpl());
    	SeparatingNode body = new SeparatingNode(root);
        assertEquals(false, body.equals(null));
        assertEquals(true, body.equals(body));
    }
    

}
