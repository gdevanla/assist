/*
 * Copyright 1999-2004 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.outerj.daisy.diff.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.outerj.daisy.diff.helper.SaxBuffer.SaxBit;
import org.outerj.daisy.diff.helper.SaxBuffer.SkippedEntity;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.AttributesImpl;

/**
 * Tests for the class that can record SAX events and replay them later.
 * 
 * @author icewariya
 *
 */
public class SaxBufferTest {

	@Test
    public void testSkippedEntity() throws Exception
    {
		SaxBuffer saxBuff = new SaxBuffer();
		char[] ch = {'n','a','m','e','o','u','i'};
		saxBuff.characters(ch, 0, 4);
		SaxBuffer copySaxBuff = new SaxBuffer(saxBuff);

		copySaxBuff.skippedEntity("game");

		assertEquals("name", saxBuff.toString());
		assertFalse(copySaxBuff.isEmpty());
    }
	
	@Test
    public void testIgnorableWhitespaceCharacters() throws Exception
    {
		SaxBuffer saxBuff = new SaxBuffer();
		char[] ch = {'a',' ',' ','d','e'};
		saxBuff.characters(ch, 0, 5);
		saxBuff.ignorableWhitespace(ch, 1, 3);

		assertEquals("a  de",saxBuff.toString());	
    }
	
	@Test
    public void testProcessingInstruction() throws Exception
    {
		SaxBuffer saxBuff = new SaxBuffer();
		saxBuff.processingInstruction("target", "data");
		
		assertEquals(SaxBuffer.class, saxBuff.getClass());
    }
	
	@Test
    public void testStartDocument() throws Exception
    {
		SaxBuffer saxBuff = new SaxBuffer();
		char[] ch = {'a','d','e'};
		saxBuff.characters(ch, 0, 3);
		saxBuff.startDocument();
		
		assertEquals("ade",saxBuff.toString());
		
    }
	
	@Test
    public void testStartElement() throws Exception
    {
		Attributes atts = new AttributesImpl();
		SaxBuffer saxBuff = new SaxBuffer();
		char[] ch = {'a','d','e'};
		saxBuff.characters(ch, 0, 3);
		saxBuff.startElement("namespaceURI", "localName", "qName", atts);
    }
	
	@Test
    public void testEndDocumentElement() throws Exception
    {
		SaxBuffer saxBuff = new SaxBuffer();
		saxBuff.endDocument();
		saxBuff.endElement("namespaceURI", "localName", "qName");
    }
	
	@Test
    public void testEndPrefixMapping() throws Exception
    {
		SaxBuffer saxBuff = new SaxBuffer();
		saxBuff.endPrefixMapping("prefix");
    }
    
}