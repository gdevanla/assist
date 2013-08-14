package net.n3.nanoxml;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.Stack;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;


public class StdXMLBuilderTest {

	private StdXMLBuilder target=null;
	
	private XMLElement element=null;
	
	@Before
	public void setUp()
	{
		element=new XMLElement("note","file:insert.xml",1);
		target=new StdXMLBuilder(element);
		
	}
	private Object getField( Object instance, String fieldName ) throws Exception
	{
		Class c = instance.getClass();

		// Retrieve the field with the specified name
		Field f = c.getDeclaredField( fieldName );
		f.setAccessible( true );

		// Return the value of the field for the instance
		return f.get( instance );
	}
	
	@Test
	public void testStdBuilderNullElement() throws Exception
	{
		target=new StdXMLBuilder();
		Stack stackVar=(Stack)getField(target,"stack");
		assertNull(stackVar);
		IXMLElement root=(IXMLElement)getField(target,"root");
		assertNull(root);
		IXMLElement prototype=(IXMLElement)getField(target,"prototype");
		assertNull(prototype.getName());
	}
	
	@Test
	public void testStdBuilderElement() throws Exception
	{
		
		Stack stackVar=(Stack)getField(target,"stack");
		assertNull(stackVar);
		IXMLElement root=(IXMLElement)getField(target,"root");
		assertNull(root);
		IXMLElement prototype=(IXMLElement)getField(target,"prototype");
		assertEquals("note",prototype.getName());
	}
	
	@Test
	public void testStartBuilding() throws Exception
	{
		target.startBuilding("file:insert.xml",1);
		Stack stackVar=(Stack)getField(target,"stack");
		assertNotNull(stackVar);
		IXMLElement root=(IXMLElement)getField(target,"root");
		assertNull(root);
		
	}
	
	@Test
	public void testStartElementStackEmpty() throws Exception
	{
		target.startBuilding("file:sample.xml",1);
		Stack stackVar=(Stack)getField(target,"stack");
		assertTrue(stackVar.empty());
		target.startElement("book","f","http://www.w3c.org/html4","file:sample.xml",1);
		
		IXMLElement stackTop=(IXMLElement) stackVar.peek();
		assertEquals("book",stackTop.getName());
		assertFalse(stackVar.empty());
		IXMLElement root=(IXMLElement)getField(target,"root");
		assertEquals("book",root.getName());
		IXMLElement prototype=(IXMLElement)getField(target,"prototype");
		assertFalse(prototype.hasChildren());
		assertEquals(root,target.getResult());
	}
	
	
	// insert a new element 
	//when stack is not empty
	// so new element becomes the new stack Top
	//and checks whether new element is the child of the old stack top
	@Test
	public void testStartElementStackNotEmpty() throws Exception
	{
		target.startBuilding("file:sample.xml",1);
		target.startElement("book","f","http://www.w3c.org/html4","file:sample.xml",1);
		
		IXMLElement root=(IXMLElement)getField(target,"root");
		assertEquals("book",root.getName());
		
		Stack stackVar=(Stack)getField(target,"stack");
		IXMLElement stackTop=(IXMLElement) stackVar.peek();
		assertEquals("book",stackTop.getName());
		assertEquals("f:book",stackTop.getFullName());
		
		target.startElement("noteBook","f","http://www.w3c.org/html4","file:sample.xml",1);
		IXMLElement newStackTop=(IXMLElement) stackVar.peek();
		assertEquals("noteBook",newStackTop.getName());
		assertFalse(stackVar.empty());
		assertTrue(stackTop.hasChildren());
		assertEquals("f:noteBook",newStackTop.getFullName());
		Vector children=stackTop.getChildren();
		assertTrue(children.contains(newStackTop));
	}
	
	@Test
	public void testEndElement() throws Exception
	{
		
		target.startBuilding("file:sample.xml",1);
		target.startElement("note","f","http://www.w3c.org/html4","file:sample.xml",1);
		target.startElement("book","f","http://www.w3c.org/html4","file:sample.xml",1);
		target.startElement(null,"f","http://www.w3c.org/html4","file:sample.xml",1);
		Stack stackVar=(Stack)getField(target,"stack");
		target.endElement("noteBook1", "f:","http://www.w3c.org/html4");
		IXMLElement stackTop=(IXMLElement)stackVar.peek();
		target.endElement("noteBook2", "f:","http://www.w3c.org/html4");
		assertEquals(1,stackTop.getChildrenCount());
		
	}
	
	//checks without Adding Attribute and adding attribute
	@Test
	public void testAddAtrributeWithNameSpace() throws Exception
	{
		target.startBuilding("file:sample.xml",1);
		target.startElement("note","f","http://www.w3c.org/html4","file:sample.xml",1);
		Stack stackVar=(Stack)getField(target,"stack");
		IXMLElement stackTop=(IXMLElement)stackVar.peek();
		assertFalse(stackTop.hasAttribute("f:name"));
		target.addAttribute("name","f","http://www.w3c.org/html4","Software Testing","String");
		stackTop=(IXMLElement)stackVar.peek();
		assertTrue(stackTop.hasAttribute("f:name"));
		
	}
	
	@Test
	public void testAddAtrributeWithOutNameSpace() throws Exception
	{
		target.startBuilding("file:sample.xml",1);
		target.startElement("note",null,null,"file:sample.xml",1);
		Stack stackVar=(Stack)getField(target,"stack");
		IXMLElement stackTop=(IXMLElement)stackVar.peek();
		assertFalse(stackTop.hasAttribute("f:name"));
		target.addAttribute("name",null,null,"Software Testing","String");
		stackTop=(IXMLElement)stackVar.peek();
		assertTrue(stackTop.hasAttribute("name"));
		
	}
	
	@Test(expected=XMLParseException.class)
	public void testAddAtrribute() throws Exception
	{
		target.startBuilding("file:sample.xml",1);
		target.startElement("note",null,null,"file:sample.xml",1);
		Stack stackVar=(Stack)getField(target,"stack");
		IXMLElement stackTop=(IXMLElement)stackVar.peek();
		assertFalse(stackTop.hasAttribute("f:name"));
		target.addAttribute("name",null,null,"Software Testing","String");
		target.addAttribute("name",null,null,"Software Testing","String");
		fail("should throw XMLParseException");
	}
}
