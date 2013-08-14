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

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import static org.junit.Assert.*;

public class testManipulator {
	Document xmlDocument;
	StrXML demo;
	public testManipulator()
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
	
/*Test for constructPath function */
	
	//If we pass null to constructPath, then NULLPointerException is expected
	@Test(expected=NullPointerException.class)
	public void constructPathTest1(){                        
		NodeList n=xmlDocument.getElementsByTagName(null);
		Manipulator.constructPath(n.item(0));
	}
	
	//If we pass a node which doesnt exists in input xml to constructPath, then NULLPointerException is expected
	@Test(expected=NullPointerException.class)
	public void constructPathTest2(){                        
		NodeList n=xmlDocument.getElementsByTagName("c");
		Manipulator.constructPath(n.item(0));
	}
	
	//Input passed as parent and child,expected true
	@Test
	public void isChildOrSameTest1()
	{
		NodeList n1=xmlDocument.getElementsByTagName("b");
		NodeList n2=xmlDocument.getElementsByTagName("p");
		assertEquals(true,Manipulator.isChildOrSame(n2.item(0),n1.item(0)));
	}
	
	//Input passed are same node,expected true
	@Test
	public void isChildOrSameTest2()
	{
		NodeList n1=xmlDocument.getElementsByTagName("b");
		assertEquals(true,Manipulator.isChildOrSame(n1.item(0),n1.item(0)));
	}
	
	//Input passed are child and parent,expected false
	@Test
	public void isChildOrSameTest3()
	{
		NodeList n1=xmlDocument.getElementsByTagName("b");
		NodeList n2=xmlDocument.getElementsByTagName("p");
		assertEquals(false,Manipulator.isChildOrSame(n1.item(0),n2.item(0)));
	}
	
	//If we pass nodes whose parent are same, then parent node is expected
	@Test
	public void lowestCommonAncestorTest1()
	{
		NodeList n1=xmlDocument.getElementsByTagName("b");
		NodeList n2=xmlDocument.getElementsByTagName("i");
		assertEquals("p",Manipulator.lowestCommonAncesstor(n1.item(0),n2.item(0)).getNodeName());
	}

		//If we pass null to any one parameter lowestCommonAncestorTest, then NULLPointerException is expected
	@Test(expected=NullPointerException.class)
	public void lowestCommonAncestorTest2()
	{
		NodeList n1=xmlDocument.getElementsByTagName("c");
		NodeList n2=xmlDocument.getElementsByTagName("i");
		Manipulator.lowestCommonAncesstor(n1.item(0),n2.item(0));
	}
	
	//If we pass nodes whose parents are different, then parent node of input which comes first in xml is expected
	@Test
	public void lowestCommonAncestorTest3()
	{
		NodeList n1=xmlDocument.getElementsByTagName("b");
		NodeList n2=xmlDocument.getElementsByTagName("p");
		assertEquals("#document",Manipulator.lowestCommonAncesstor(n1.item(0),n2.item(0)).getNodeName());
	}

	 public static Document parse(InputStream in) throws ParserConfigurationException, SAXException, IOException {
	    	//assertEquals("File doesnt exist",1,1);
	    	//InputStream in=Example.class.getResourceAsStream("test.xml");
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        

	        return db.parse(in);
	    }
	   
	
}
