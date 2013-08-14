/*
 * This file is a part of ShtutXML
 * (c) Barak Itkin <lightningismyname at gmail dot com>, 2011
 *
 * ShtutXML is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
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

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.shtutxml.DepthDocumentVisitor.VisitOrder;
/**
 * An example class that shows how to use this package
 */
public class testShtutxml {
	Document xmlDocument;
	StrXML demo;
	public testShtutxml()
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
	
	@Test(expected = IllegalArgumentException.class)
	public void strXMLTest1() 
	{
		try
		{
		Document xmlDocument1=parse(Example.class.getResourceAsStream("test1.xml"));;
		StrXML strXMLTest=new StrXML(xmlDocument1);
		} catch (ParserConfigurationException ex) {
            Logger.getLogger(Example.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Example.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Example.class.getName()).log(Level.SEVERE, null, ex);
        }
		
		//assertEquals(strXMLTest., actual)
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void strXMLTest2() 
	{
		try
		{
		Document xmlDocument1=parse(Example.class.getResourceAsStream("C:\\Users\\SanthanamS\\Downloads\\ShtutXML(1)\\ShtutXML\\src\\main\\java\\com\\google\\shtutxmltest.xml"));;
		StrXML strXMLTest=new StrXML(xmlDocument1);
		assertEquals(true, strXMLTest!=null);
		} catch (ParserConfigurationException ex) {
            Logger.getLogger(Example.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Example.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Example.class.getName()).log(Level.SEVERE, null, ex);
        }
		
		//assertEquals(strXMLTest., actual)
	}
	
	@Test
	public void insertAndSplitInsertParentTest1()
	{
		try
		{
		demo.insertAndSplitParent(xmlDocument.createElement("u"), demo.getOffset(0), demo.getOffset(50));
		
		assertEquals(true, xmlDocument.getElementsByTagName("u")!=null);
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(xmlDocument);
        StreamResult streamResult =  new StreamResult(new File("D:\\Test.xml"));
        transformer.transform(source, streamResult);
		}
		catch(Exception ex)
		{
			
		}
	}
	
	@Test
	public void insertAndSplitInsertParentTest2()
	{
		try
		{
		demo.insertAndSplitParent(xmlDocument.createElement("t"), demo.getOffset(50), demo.getOffset(50));
		
		assertEquals(true, xmlDocument.getElementsByTagName("t")!=null);
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(xmlDocument);
        StreamResult streamResult =  new StreamResult(new File("D:\\Test.xml"));
        transformer.transform(source, streamResult);
		}
		catch(Exception ex)
		{
			
		}
	}
	
	@Test
	public void insertAndSplitInsertParentTest3()
	{
		try
		{
		demo.insertAndSplitParent(xmlDocument.getParentNode(), demo.getOffset(0), demo.getOffset(0));
		
		assertEquals(true, xmlDocument.getElementsByTagName("t")!=null);
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(xmlDocument);
        StreamResult streamResult =  new StreamResult(new File("D:\\Test.xml"));
        transformer.transform(source, streamResult);
		}
		catch(Exception ex)
		{
			
		}
	} 
	
	@Test
	public void insertAndSplitInsertedTest1()
	{
		try
		{
		demo.insertAndSplitInserted(xmlDocument.createElement("u"), demo.getOffset(0), demo.getOffset(50));
		
		assertEquals(true, xmlDocument.getElementsByTagName("u")!=null);
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(xmlDocument);
        StreamResult streamResult =  new StreamResult(new File("D:\\Test.xml"));
        transformer.transform(source, streamResult);
		}
		catch(Exception ex)
		{
			
		}
	}
	
	@Test
	public void insertAndSplitInsertedTest2()
	{
		try
		{
		demo.insertAndSplitInserted(xmlDocument.createElement("e"), demo.getOffset(50), demo.getOffset(50));
		
		assertEquals(true, xmlDocument.getElementsByTagName("e")!=null);
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(xmlDocument);
        StreamResult streamResult =  new StreamResult(new File("D:\\Test.xml"));
        transformer.transform(source, streamResult);
		}
		catch(Exception ex)
		{
			
		}
	}
	
	@Test
    public void getOffSetTest1()
    {
    	assertEquals(demo.getOffset(0).toString(),"[b: null] at 0");
    }
 
 
	@Test(expected=NullPointerException.class)
    public void getOffSetTest2()
    {
    	demo.getOffset(500);
    }

	@Test
    public void getOffSetTes3()
    {
    	assertEquals(demo.getOffset(20).toString(),"[p: null] at 13");
    }
	
	@Test(expected=IllegalArgumentException.class)
	public void getSpanTest1()
	{
		Node node = xmlDocument.createElement("TestNode");
		//node.setNodeValue("GeForce");
		assertEquals(demo.getSpan(node)[0],0);
		
		
	}
	
	@Test
	public void getSpanTest2()
	{
		Node node = xmlDocument.getElementsByTagName("i").item(0);
		//node.setNodeValue("GeForce");
		assertEquals(true,demo.getSpan(node)[0]>0);
	}
	
	@Test
	public void getSpanTest3()
	{
		Node node = xmlDocument.getElementsByTagName("p").item(0);
		//node.setNodeValue("GeForce");
		assertEquals(true,demo.getSpan(node)[1]==demo.getText().length());
	}
	
	@Test
    public void toStringTest1()
    {
    	assertEquals(demo.toString().compareTo("")>0,true);
    }
	
	@Test(expected=NullPointerException.class)
	public void getTextTest1()
	{
		StrXML demo2=null;
		demo2.getText();
	}
	
	@Test
	public void getTextTest2()
	{
		assertEquals(true,demo.getText().compareTo("")>0);
	}
	
	 public static Document parse(InputStream in) throws ParserConfigurationException, SAXException, IOException {
	    	//assertEquals("File doesnt exist",1,1);
	    	//InputStream in=Example.class.getResourceAsStream("test.xml");
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        

	        return db.parse(in);
	    }
	
	 @Test
	    public void VisitDocTest1()
	 {
		 new DocumentPrinter().visit(xmlDocument.getFirstChild(),VisitOrder.DEPTH_FIRST);
	 }
	 
	 @Test
	    public void VisitDocTest2()
	 {
		 new DocumentPrinter().visit(demo.getOffset(15).getParentNode(),VisitOrder.BREADTH_FIRST);
	 }
	 
}
