package org.outerj.daisy.diff;

import static org.junit.Assert.assertEquals;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;

import org.junit.Test;
import org.xml.sax.ContentHandler;

public class XslFilterTest {
	@Test
    public void testXsl() throws Exception {
		XslFilter filter = new XslFilter();
		SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
		TransformerHandler result = tf.newTransformerHandler();
		ContentHandler postProcess = result;
		ContentHandler cleanupFilter = filter.xsl(postProcess,
                "org/outerj/daisy/diff/cleanup.xsl");
		assertEquals("class com.sun.org.apache.xalan.internal.xsltc.trax.TransformerHandlerImpl",cleanupFilter.getClass().toString());
	}
	/*
	@Test(expected = IllegalStateException.class)
    public void testXslTransformerConfigurationException() throws Exception {
		try {
		XslFilter filter = new XslFilter();
		SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
		TransformerHandler result = tf.newTransformerHandler();
		ContentHandler postProcess = result;
		ContentHandler cleanupFilter = filter.xsl(postProcess,
                "exception");
		} catch(TransformerConfigurationException ex) {
			assertEquals ("Can't transform xml.",ex.getMessage());
			throw ex;
		}
	}*/
	
}
