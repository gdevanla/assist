package org.outerj.daisy.diff.html.dom.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.AttributesImpl;
/**
 * @author icewariya
 */
public class AttributesMapTest {

	@Test
	public void testAttributesMap() throws Exception {
		AttributesImpl nodeAttrs = new AttributesImpl();
        nodeAttrs.addAttribute("", "src", "src", "src", "location of image tag");
        nodeAttrs.addAttribute("", "color", "color", "color", "color for the text");
        
        AttributesMap attrsMap = new AttributesMap(nodeAttrs);
        assertTrue(attrsMap.containsKey("src"));
        assertTrue(attrsMap.containsKey("color"));
        assertFalse(attrsMap.containsKey("font-family"));
        
	}
	
	@Test
	public void testHasSameAttributes() throws Exception {
		AttributesImpl nodeAttrs = new AttributesImpl();
        nodeAttrs.addAttribute("", "src", "src", "src", "location of image tag");
        
        AttributesImpl attrs = new AttributesImpl();
        attrs.addAttribute("", "color", "CLASS_ATTR", "color", "color for the text");
        
        AttributesImpl styleAttrs = new AttributesImpl();
        styleAttrs.addAttribute("", "style", "STYLE_ATTR", "style", "style for the text");
        styleAttrs.addAttribute("", "color", "CLASS_ATTR", "color", "color for the text");
        
        AttributesImpl nullAttrs = new AttributesImpl();
        nullAttrs.addAttribute("", "", "", "", "");
        
        AttributesMap nodeAttrsMap = new AttributesMap(nodeAttrs);
        AttributesMap attrsMap = new AttributesMap(attrs);
        AttributesMap nullAttrsMap = new AttributesMap(nullAttrs);
       
        assertFalse(attrsMap.hasSameAttributes(nodeAttrs));
        assertFalse(attrsMap.hasSameAttributes(styleAttrs));
        assertFalse(nullAttrsMap.hasSameAttributes(attrs));
        assertTrue(nodeAttrsMap.hasSameAttributes(nodeAttrs));

	}
	
	@Test
	public void testEquals() throws Exception {
		AttributesImpl nodeAttrs = new AttributesImpl();
        nodeAttrs.addAttribute("", "src", "src", "src", "location of image tag");
        
        AttributesImpl attrs = new AttributesImpl();
        attrs.addAttribute("", "color", "class", "color", "color for the text");
        
        AttributesImpl styleAttrs = new AttributesImpl();
        styleAttrs.addAttribute("", "style", "style", "style", "style for the text");
        styleAttrs.addAttribute("", "color", "color", "color", "color for the text");
        
        AttributesImpl nullAttrs = new AttributesImpl();
        nullAttrs.addAttribute("", "", "", "", "");
        
        AttributesMap nodeAttrsMap = new AttributesMap(nodeAttrs);
        AttributesMap attrsMap = new AttributesMap(attrs);
        AttributesMap styleAttrsMap = new AttributesMap(styleAttrs);
        AttributesMap nullAttrsMap = new AttributesMap(nullAttrs);
       
        assertFalse(attrsMap.equals(nodeAttrsMap));
        assertFalse(attrsMap.equals(styleAttrsMap));
        assertFalse(nullAttrsMap.equals(attrs));
        assertTrue(nodeAttrsMap.equals(nodeAttrsMap));
        assertFalse(styleAttrsMap.equals(attrs));

	}
	
	@Test
	public void testHashCode() throws Exception {
		AttributesImpl nodeAttrs = new AttributesImpl();
        nodeAttrs.addAttribute("", "src", "src", "src", "location of image");
        nodeAttrs.addAttribute("", "images", "class", "class", "css format for content");
        nodeAttrs.addAttribute("", "color", "style", "color", "color of content");
 
        AttributesMap nodeAttrsMap = new AttributesMap(nodeAttrs);
        int hashCode = nodeAttrsMap.hashCode();
        
        assertEquals(hashCode, nodeAttrsMap.hashCode());
	}
	
	@Test
	public void testEquivalentStyles() throws Exception {
		String s1 = "margin-left:50px;font-size:16pt;";
		String s2 = "    font-size  :  16pt    ;  ;   ;  ; margin-left  : 50px   ";
		String s3 = s1;
		assertTrue(AttributesMap.equivalentStyles(s1, s2));
		assertTrue(AttributesMap.equivalentStyles(s1, s3));
		assertTrue(AttributesMap.equivalentStyles(null, null));
		assertFalse(AttributesMap.equivalentStyles(s1, null));
		assertFalse(AttributesMap.equivalentStyles(null, s2));
	}
	
	@Test
	public void testClassSet() throws Exception {
		String s1 = "margin-left:50px;font-size:16pt;";
		String s2 = "    font-size  :  16pt    ;  ;   ;  ; margin-left  : 50px   ";
		String s3 = s1;
		assertFalse(AttributesMap.sameClassSet(s1, s2));
		assertTrue(AttributesMap.sameClassSet(s1, s3));
		assertTrue(AttributesMap.sameClassSet(null, null));
		assertFalse(AttributesMap.sameClassSet(s1, null));
		assertFalse(AttributesMap.sameClassSet(null, s2));
	}
	
	@Test
	public void testNormalizeStyleString() throws Exception {
		String s1 = "margin-left:50px;font-size:16pt;";
		String s2 = "    font-size  :  16pt    ;  ;   ;  ; margin-left  : 50px   ";
		
		assertEquals("font-size:16pt; margin-left:50px", AttributesMap.normalizeStyleString(s1));
		assertEquals("font-size:16pt; margin-left:50px", AttributesMap.normalizeStyleString(s2));
		assertEquals(null, AttributesMap.normalizeStyleString(null));
		assertEquals("", AttributesMap.normalizeStyleString(""));
		assertEquals("", AttributesMap.normalizeStyleString("       "));
	}
	
	@Test
	public void testNormalizeClassString() throws Exception {
		String s1 = "margin-left:50px;font-size:16pt;";
		String s2 = "    font-size  :  16pt    ;  ;   ;  ; margin-left  : 50px   ";
		
		assertEquals("margin-left:50px;font-size:16pt;", AttributesMap.normalizeClassString(s1));
		assertEquals("16pt 50px : : ; ; ; ; font-size margin-left", AttributesMap.normalizeClassString(s2));
		assertEquals(null, AttributesMap.normalizeClassString(null));
		assertEquals("", AttributesMap.normalizeClassString(""));
		assertEquals("", AttributesMap.normalizeClassString("       "));
	}
}
