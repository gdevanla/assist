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
public class testTextSection {
	Document xmlDocument;
	StrXML demo;
	public testTextSection()
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
	
	@Test(expected=IllegalArgumentException.class)
	public void splitSectionUpTest1()
	{
		NodeAndOffset nao=demo.getOffset(40);
		TextSection testTextSection=new TextSection(nao.textSection, nao.getParentNode());
		testTextSection.splitSectionUp(42, xmlDocument.getParentNode());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void splitSectionUpTest2()
	{
		//demo.GetOffset(2), demo.GetOffset(2).getParentNode().getParentNode()
		NodeAndOffset nao=demo.getOffset(40);
		TextSection testTextSection=new TextSection(nao.textSection, nao.getParentNode());
		testTextSection.splitSectionUp(2,demo.getOffset(2).getParentNode());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void splitSectionUpNotIncludingTest()
	{
		NodeAndOffset nao=demo.getOffset(40);
		TextSection testTextSection=new TextSection(nao.textSection, nao.getParentNode());
		testTextSection.splitSectionUpNotIncluding(42, xmlDocument.getParentNode());
	}
	
	/*@Test
	public void splitSectionUpNotIncludingTest1()
	{
		NodeAndOffset nao=demo.getOffset(40);
		TextSection testTextSection=new TextSection(nao.textSection, nao.getParentNode());
		testTextSection.splitSectionUpNotIncluding(42, nao.getParentNode());
	}
	*/
	@Test
	public void TextSectionisNodeBeginTest()
	{
		//Node node=xmlDocument.getFirstChild();
		NodeAndOffset nao=demo.getOffset(0);
		TextSection testTextSection=new TextSection(null, xmlDocument.getFirstChild());
		assertEquals(true,testTextSection.isNodeBegin());
	}
	
	@Test
	public void isNodeEndTest1()
	{
		NodeAndOffset nao=demo.getOffset(3);
		TextSection testTextSection=new TextSection(nao.textSection, xmlDocument.getFirstChild());
		assertEquals(true,testTextSection.isNodeEnd());
		
	}
	
	@Test
	public void isNodeEndTest2()
	{
		NodeAndOffset nao=demo.getOffset(0);
		TextSection testTextSection=new TextSection(nao.textSection, xmlDocument.getFirstChild());
		assertEquals(true,testTextSection.isNodeEnd());
		
	}
	
	@Test
	public void isChildOfTest1()
	{
		Node node=xmlDocument.getFirstChild();
		NodeAndOffset nao=demo.getOffset(0);
		TextSection testTextSection=new TextSection(nao.textSection, xmlDocument.getFirstChild());
		assertEquals(false,testTextSection.isChildOf(node));
	}
	
	@Test
	public void wrapWithTest1()
	{
		NodeAndOffset nao=demo.getOffset(40);
		TextSection testTextSection=new TextSection(nao.textSection, nao.getParentNode());
		testTextSection.wrapWith(xmlDocument.createElement("test"));
		assertEquals("test",testTextSection.getParentNode().getNodeName());
	}
	

	@Test
	public void toStringTest1()
	{
		NodeAndOffset nao=demo.getOffset(40);
		TextSection testTextSection=new TextSection(nao.textSection, nao.getParentNode());
		assertEquals("visual computing features (child of [p: null])",testTextSection.toString());
	}
	

	//@Test(expected=IllegalArgumentException.class)
	public void isNodeBeginTest1()
	{
		NodeAndOffset nao=demo.getOffset(5);
		TextSection testTextSection=new TextSection(nao.textSection, xmlDocument.getFirstChild());
		assertEquals(true,testTextSection.isNodeBegin());
	}
	
	@Test
	public void isNodeBeginTest2()
	{
		//NodeAndOffset nao=demo.getOffset(-1);
		TextSection testTextSection=new TextSection(null, xmlDocument.getFirstChild());
		assertEquals(true,testTextSection.isNodeBegin());
	}
	
	
	public static Document parse(InputStream in) throws ParserConfigurationException, SAXException, IOException {
    	//assertEquals("File doesnt exist",1,1);
    	//InputStream in=Example.class.getResourceAsStream("test.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        

        return db.parse(in);
    }
}
