package org.outerj.daisy.diff.html.ancestor.tagtostring;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.Test;
import org.outerj.daisy.diff.html.ancestor.TagChangeSematic;
import org.outerj.daisy.diff.html.dom.TagNode;
import org.xml.sax.helpers.AttributesImpl;

public class TagToStringFactoryTest {
	@Test
	public void testCreate() throws Exception {
		TagToStringFactory factory = new TagToStringFactory();

		TagNode root = new TagNode(null, "html", new AttributesImpl());
		TagNode link = new TagNode(null, "img", new AttributesImpl());
		TagNode image = new TagNode(null, "a", new AttributesImpl());

		assertEquals("Html page", factory.create(root, Locale.ENGLISH).getDescription().toString());
		assertEquals("Image", factory.create(link, Locale.ENGLISH).getDescription().toString());
		assertEquals("Link", factory.create(image, Locale.ENGLISH).getDescription().toString());
		
	}
	
	@Test
	public void testGetChangeSemantic() throws Exception {
		TagToStringFactory factory = new TagToStringFactory();

		assertEquals(TagChangeSematic.MOVED, factory.getChangeSemantic("table"));
		assertEquals(TagChangeSematic.STYLE,factory.getChangeSemantic("big"));
		assertEquals(TagChangeSematic.UNKNOWN,factory.getChangeSemantic("unknown"));

	}
}
