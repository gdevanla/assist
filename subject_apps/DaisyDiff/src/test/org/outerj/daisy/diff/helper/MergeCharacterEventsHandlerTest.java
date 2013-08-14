package org.outerj.daisy.diff.helper;

import static org.junit.Assert.assertEquals;

import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.Locator;

import org.junit.Test;
import org.xml.sax.ContentHandler;
import org.xml.sax.helpers.AttributesImpl;

public class MergeCharacterEventsHandlerTest {
	char[] chara = {'t', 'e', 's', 't', 'c', 'h', 'a', 'r'};
	private MergeCharacterEventsHandler getHandler() throws Exception{
		StringWriter finalResult = new StringWriter();
		
		SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
		TransformerHandler result = tf.newTransformerHandler();
		result.setResult(new StreamResult(finalResult));
		ContentHandler postProcess = result;
		
		MergeCharacterEventsHandler handler = new MergeCharacterEventsHandler(postProcess);
		
		return handler;
	}
	
	private char[] getField(MergeCharacterEventsHandler handler) throws Exception {
		Field f = handler.getClass().getDeclaredField("ch");

		// make sure the field is accessible, even if it
		// would be private or protected
		f.setAccessible( true );

		// Return the value of the field for the instance
		char[] privateCh	= (char[]) f.get( handler );
		
		return privateCh;
	}
	
	@Test
    public void testCharacters() throws Exception {
		MergeCharacterEventsHandler handler = getHandler();
		
		handler.characters(chara, 0, 4);
		
		char[] privateCh	= getField(handler);
		
		assertEquals("test",new String(privateCh));
	}
	
	@Test
    public void testCharactersNull() throws Exception {
		MergeCharacterEventsHandler handler = getHandler();
		
		handler.characters(chara, 0, 4);
		handler.characters(chara, 0, 4);
		
		char[] privateCh	= getField(handler);
		
		assertEquals("testtest",new String(privateCh));
	}
	
	@Test
    public void testFlushCharacters() throws Exception {
		MergeCharacterEventsHandler handler = getHandler();
		
		handler.characters(chara, 0, 4);
		
		Class c 	= handler.getClass();
		Method method = c.getDeclaredMethod("flushCharacters");
		method.setAccessible( true );
		method.invoke(handler);
		
		Field start = handler.getClass().getDeclaredField("start");
		start.setAccessible(true);
		char[] privateCh	= getField( handler );
		int privateStart = (Integer) start.get(handler);
		
		assertEquals(null, privateCh);
		assertEquals(0, privateStart);
		
	}
	
	@Test
    public void testFlushCharactersNull() throws Exception {
		MergeCharacterEventsHandler handler = getHandler();
		
		Class c 	= handler.getClass();
		Method method = c.getDeclaredMethod("flushCharacters");
		method.setAccessible( true );
		method.invoke(handler);
		
		Field start = handler.getClass().getDeclaredField("start");
		start.setAccessible(true);
		char[] privateCh	= getField( handler );
		int privateStart = (Integer) start.get(handler);
		
		assertEquals(null, privateCh);
		assertEquals(0, privateStart);
		
	}
	
	@Test
    public void testEndDocument() throws Exception {
		MergeCharacterEventsHandler handler = getHandler();

		handler.characters(chara, 0, 4);
		
		char[] ch	= getField( handler );
		assertEquals("test",new String(ch));
		
		handler.endDocument();
		
		char[] charAfterStart	= getField( handler );
		assertEquals(null,charAfterStart);
		
	}
	
	@Test
    public void testStartDocument() throws Exception {
		MergeCharacterEventsHandler handler = getHandler();
		
		handler.characters(chara, 0, 4);
		
		char[] ch	= getField( handler );
		assertEquals("test",new String(ch));
		
		handler.startDocument();
		
		char[] charAfterStart	= getField( handler );
		assertEquals(null,charAfterStart);
		
	}
	
	@Test
    public void testIgnorableWhitespace() throws Exception {
		MergeCharacterEventsHandler handler = getHandler();
		
		handler.characters(chara, 0, 4);
		
		char[] ch	= getField( handler );
		assertEquals("test",new String(ch));
		
		handler.ignorableWhitespace(ch, 0, 4);
		
		char[] charAfterWhiteSpace	= getField( handler );
		assertEquals(null,charAfterWhiteSpace);
		
	}
	
	@Test
    public void testEndPrefixMapping() throws Exception {
		MergeCharacterEventsHandler handler = getHandler();

		handler.characters(chara, 0, 4);

		char[] ch	= getField( handler );
		assertEquals("test",new String(ch));
		
		handler.endPrefixMapping("diff");
		
		char[] charAfterPrefixMapping	= getField( handler );
		assertEquals(null,charAfterPrefixMapping);
		
	}
	
	@Test
    public void testSkippedEntity() throws Exception {
		MergeCharacterEventsHandler handler = getHandler();

		handler.characters(chara, 0, 4);
		
		char[] ch	= getField( handler );
		assertEquals("test",new String(ch));
		
		handler.skippedEntity("diff");
		
		char[] charSkippedEntity	= getField( handler );
		assertEquals(null,charSkippedEntity);
		
	}
	
	@Test
    public void testSetDocumentLocator() throws Exception {
		MergeCharacterEventsHandler handler = getHandler();

		handler.characters(chara, 0, 4);
		
		char[] ch	= getField( handler );
		assertEquals("test",new String(ch));
		
		handler.setDocumentLocator(null);
		
		char[] charSkippedEntity	= getField( handler );
		assertEquals("test",new String(charSkippedEntity));
		
	}
	
	@Test
    public void testProcessingInstruction() throws Exception {
		MergeCharacterEventsHandler handler = getHandler();

		handler.characters(chara, 0, 4);
		
		char[] ch	= getField( handler );
		assertEquals("test",new String(ch));
		
		handler.processingInstruction("target", "data");
		
		char[] charPI	= getField( handler );
		assertEquals(null,charPI);
		
	}
	
	@Test
    public void testStartPrefixMapping() throws Exception {
		MergeCharacterEventsHandler handler = getHandler();

		handler.characters(chara, 0, 4);
		
		char[] ch	= getField( handler );
		assertEquals("test",new String(ch));
		
		handler.startPrefixMapping("prefix", "uri");
		
		char[] charStartPrefixMapping	= getField( handler );
		assertEquals(null,charStartPrefixMapping);
		
	}
	
	@Test
    public void testEndElement() throws Exception {
		MergeCharacterEventsHandler handler = getHandler();

		handler.characters(chara, 0, 4);
		
		char[] ch	= getField( handler );
		assertEquals("test",new String(ch));
		
		handler.endElement("namespaceURI", "localName", "qName");
		
		char[] charEndElement	= getField( handler );
		assertEquals(null,charEndElement);
		
	}
	
	@Test
    public void testStartElement() throws Exception {
		MergeCharacterEventsHandler handler = getHandler();

		handler.characters(chara, 0, 4);
		
		char[] ch	= getField( handler );
		assertEquals("test",new String(ch));
		
		handler.startElement("namespaceURI", "localName", "qName", new AttributesImpl());
		
		char[] charEndElement	= getField( handler );
		assertEquals(null,charEndElement);
		
	}
}
