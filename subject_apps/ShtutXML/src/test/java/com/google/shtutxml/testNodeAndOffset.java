package com.google.shtutxml;

import static org.junit.Assert.assertEquals;

import java.beans.XMLDecoder;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.UnexpectedException;
import java.sql.Savepoint;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.shtutxml.Example;
import com.google.shtutxml.StrXML;

import static org.junit.Assert.*;
public class testNodeAndOffset {
	Document xmlDocument;
	StrXML demo;
	public testNodeAndOffset()
	{
		 try {
		xmlDocument = parse(Example.class.getResourceAsStream("test.xml"));
		 xmlDocument.getTextContent();
        demo = new StrXML(xmlDocument);
		 } catch (ParserConfigurationException ex) {
             Logger.getLogger(Example.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SAXException ex) {
             Logger.getLogger(Example.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(Example.class.getName()).log(Level.SEVERE, null, ex);
         }
	}
	
	@Test
	public void getTextGlobalTest()
	{
		NodeAndOffset nao=demo.getOffset(40);
		assertEquals(40,nao.getTextGlobalOffset());
	}
	
	@Test
	public void getTextContentTest()
	{
		NodeAndOffset nao=demo.getOffset(40);
		assertEquals("visual computing features",nao.getTextContent());
	}
	
	
	public static Document parse(InputStream in) throws ParserConfigurationException, SAXException, IOException {
    	//assertEquals("File doesnt exist",1,1);
    	//InputStream in=Example.class.getResourceAsStream("test.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        

        return db.parse(in);
    }
   
}
