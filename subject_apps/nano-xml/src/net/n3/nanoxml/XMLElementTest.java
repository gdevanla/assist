package net.n3.nanoxml;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;


public class XMLElementTest {

	XMLElement target=null;
	@Before
	public void setUp()
	{
		target=new XMLElement();
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
	public void testNullFieldValues() throws Exception
	{
		target=new XMLElement();
		String fullName=(String)getField(target,"fullName");
		String nameSpace=(String)getField(target,"namespace");
		String systemId=(String)getField(target,"systemID");
		int lineNumber=(Integer)getField(target,"lineNr");
		assertNull(fullName);
		assertNull(nameSpace);
		assertNull(systemId);
		assertEquals(-1,lineNumber);
	}
	
	@Test
	public void testFieldValueFullName() throws Exception
	{
		target=new XMLElement("note");
		String fullName=(String)getField(target,"fullName");
		String nameSpace=(String)getField(target,"namespace");
		String systemId=(String)getField(target,"systemID");
		int lineNumber=(Integer)getField(target,"lineNr");
		assertEquals("note",fullName);
		assertNull(nameSpace);
		assertNull(systemId);
		assertEquals(-1,lineNumber);
	}
	
	@Test
	public void testFullNameSystemIDLineNum() throws Exception
	{
		target=new XMLElement("note","insert.xml",1);
		String fullName=(String)getField(target,"fullName");
		String nameSpace=(String)getField(target,"namespace");
		String systemId=(String)getField(target,"systemID");
		int lineNumber=(Integer)getField(target,"lineNr");
		assertEquals("note",fullName);
		assertNull(nameSpace);
		assertEquals("insert.xml",systemId);
		assertEquals(1,lineNumber);
	}
	
	@Test
	public void testFullNameandNameSpace() throws Exception
	{
		target=new XMLElement("note","http://www.w3.org/TR/html4/");
		String fullName=(String)getField(target,"fullName");
		String nameSpace=(String)getField(target,"namespace");
		String systemId=(String)getField(target,"systemID");
		int lineNumber=(Integer)getField(target,"lineNr");
		assertEquals("note",fullName);
		assertNull(systemId);
		assertEquals("http://www.w3.org/TR/html4/",nameSpace);
		assertEquals(-1,lineNumber);
	}
	
	@Test
	public void testValidFieldValues() throws Exception
	{
		target=new XMLElement("f:note","http://www.w3.org/TR/html4/","insert.xml",2);
		String fullName=(String)getField(target,"fullName");
		String nameSpace=(String)getField(target,"namespace");
		String systemId=(String)getField(target,"systemID");
		int lineNumber=(Integer)getField(target,"lineNr");
		assertEquals("f:note",fullName);
		assertEquals("insert.xml",systemId);
		assertEquals("http://www.w3.org/TR/html4/",nameSpace);
		assertEquals(2,lineNumber);
	}
	@Test
	public void testCreatePCDataElement() throws Exception
	{
		IXMLElement ele=target.createPCDataElement();
		String fullName=(String)getField(ele,"fullName");
		String nameSpace=(String)getField(ele,"namespace");
		String systemId=(String)getField(ele,"systemID");
		int lineNumber=(Integer)getField(ele,"lineNr");
		assertNull(fullName);
		assertNull(nameSpace);
		assertNull(systemId);
		assertEquals(-1,lineNumber);
	}
	
	@Test
	public void testCreateElementFullName() throws Exception
	{
		IXMLElement ele=target.createElement("book");
		String fullName=(String)getField(ele,"fullName");
		String nameSpace=(String)getField(ele,"namespace");
		String systemId=(String)getField(ele,"systemID");
		int lineNumber=(Integer)getField(ele,"lineNr");
		assertEquals("book",fullName);
		assertNull(nameSpace);
		assertNull(systemId);
		assertEquals(-1,lineNumber);
	}
	
	@Test
	public void testCreateElementFullNameandNameSpace() throws Exception
	{
		IXMLElement ele=target.createElement("note","http://www.w3.org/TR/html4/");
		String fullName=(String)getField(ele,"fullName");
		String nameSpace=(String)getField(ele,"namespace");
		String systemId=(String)getField(ele,"systemID");
		int lineNumber=(Integer)getField(ele,"lineNr");
		assertEquals("note",fullName);
		assertNull(systemId);
		assertEquals("http://www.w3.org/TR/html4/",nameSpace);
		assertEquals(-1,lineNumber);
	}
	
	@Test
	public void testCreateElementFullNameSystemIDLineNum() throws Exception
	{
		IXMLElement ele=target.createElement("note","insert.xml",1);
		String fullName=(String)getField(ele,"fullName");
		String nameSpace=(String)getField(ele,"namespace");
		String systemId=(String)getField(ele,"systemID");
		int lineNumber=(Integer)getField(ele,"lineNr");
		assertEquals("note",fullName);
		assertNull(nameSpace);
		assertEquals("insert.xml",systemId);
		assertEquals(1,lineNumber);
	}
	
	@Test
	public void testCreateElementAll() throws Exception
	{
		IXMLElement ele=target.createElement("f:note","http://www.w3.org/TR/html4/","insert.xml",2);
		String fullName=(String)getField(ele,"fullName");
		String nameSpace=(String)getField(ele,"namespace");
		String systemId=(String)getField(ele,"systemID");
		int lineNumber=(Integer)getField(ele,"lineNr");
		assertEquals("f:note",fullName);
		assertEquals("insert.xml",systemId);
		assertEquals("http://www.w3.org/TR/html4/",nameSpace);
		assertEquals(2,lineNumber);
		String root=(String)getField(ele,"parent");
		assertNull(root);
		
	}
	
	@Test
	public void testAllGetMethods()
	{
		IXMLElement ele=target.createElement("f:note","http://www.w3.org/TR/html4/","insert.xml",2);
		assertEquals("note",ele.getName());
		assertEquals("http://www.w3.org/TR/html4/",ele.getNamespace());
		assertEquals("insert.xml",ele.getSystemID());
		assertEquals(2,ele.getLineNr());
	}
	
	@Test
	public void testsetName() throws Exception
	{
		target.setName("book");
		String name=(String)getField(target,"name");
		String fullName=(String)getField(target,"fullName");
		String namespace=(String)getField(target,"namespace");
		assertEquals("book",name);
		assertEquals("book",fullName);
		assertNull(namespace);
	}
	
	@Test
	public void testsetNameWithArgs() throws Exception
	{
		target.setName("f:book","http://www.w3.org/TR/html4/");
		String name=(String)getField(target,"name");
		String fullName=(String)getField(target,"fullName");
		String namespace=(String)getField(target,"namespace");
		assertEquals("book",name);
		assertEquals("f:book",fullName);
		assertEquals("http://www.w3.org/TR/html4/",namespace);
		
		target.setName("book","http://www.w3.org/TR/html4/");
		name=(String)getField(target,"name");
		fullName=(String)getField(target,"fullName");
		namespace=(String)getField(target,"namespace");
		assertEquals("book",name);
		assertEquals("book",fullName);
		assertEquals("http://www.w3.org/TR/html4/",namespace);
	}
	
	@Test
	public void testAddChild()
	{
		target=new XMLElement("note","insert.xml",1);
		IXMLElement child=new XMLElement("to","insert.xml",2);
		target.addChild(child);
		assertEquals("note",child.getParent().getName());
		assertEquals(1,target.getChildrenCount());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddChildNull()
	{
		target=new XMLElement("note","insert.xml",1);
		target.addChild(null);
		fail("should throw illegalArgumentException");
	}
	
	@Test
	public void testAddChildNameNull()
	{
		target=new XMLElement("note","insert.xml",1);
		IXMLElement child=new XMLElement("to","insert.xml",2);
		target.addChild(child);
		IXMLElement child2=new XMLElement(null);
		assertNull(child2.getName());
		target.addChild(child2);
		assertEquals(2,target.getChildrenCount());
	}
	
	@Test
	public void testLastChildNameNull()
	{
		target=new XMLElement("note","insert.xml",1);
		IXMLElement child=new XMLElement(null);
		target.addChild(child);
		IXMLElement child2=new XMLElement(null);
		assertNull(child2.getName());
		target.addChild(child2);
		assertEquals(1,target.getChildrenCount());
	}
	
	@Test
	public void testInsertChild() throws Exception
	{
		target=new XMLElement("note","insert.xml",1);
		IXMLElement child=new XMLElement("from","insert.xml",2);
		target.addChild(child);
		IXMLElement child2=new XMLElement("to","insert.xml",1);
		target.insertChild(child2,1);
		Vector children=(Vector)getField(target,"children");
		assertTrue(children.contains(child2));
		assertEquals("note",child2.getParent().getName());
		assertEquals(2,target.getChildrenCount());
		
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testInsertNullChild() throws Exception
	{
		target=new XMLElement("note","insert.xml",1);
		IXMLElement child=new XMLElement("to","insert.xml",2);
		target.addChild(child);
		IXMLElement child2=null;
		target.insertChild(child2,1);
		
		
	}
	@Test
	public void testInsertChildNull() throws Exception
	{
		target=new XMLElement("note","insert.xml",1);
		IXMLElement child=new XMLElement(null);
		target.insertChild(child,0);
		IXMLElement child2=new XMLElement(null);
		assertNull(child2.getName());
		target.insertChild(child2,1);
		Vector children=(Vector)getField(target,"children");
		assertNull(child.getName());
		assertNull(child2.getName());
		assertEquals(1,target.getChildrenCount());
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRemoveNullChild() throws Exception
	{
		target=new XMLElement("note","insert.xml",1);
		target.removeChild(null);
	}
	
	@Test
	public void testRemoveChild() throws Exception
	{
		target=new XMLElement("note","insert.xml",1);
		IXMLElement child=new XMLElement("from","insert.xml",2);
		target.addChild(child);
		IXMLElement child2=new XMLElement("to","insert.xml",1);
		target.insertChild(child2,1);
		Vector children=(Vector)getField(target,"children");
		assertTrue(children.contains(child2));
		assertEquals("note",child2.getParent().getName());
		assertEquals(2,target.getChildrenCount());
		
		target.removeChild(child2);
		assertFalse(children.contains(child2));
		assertEquals(1,target.getChildrenCount());
		target.removeChild(child);
		assertFalse(children.contains(child2));
		assertEquals(0,target.getChildrenCount());
	}
	
	@Test
	public void testRemoveChildAtIndex() throws Exception
	{
		target=new XMLElement("note","insert.xml",1);
		IXMLElement child=new XMLElement("from","insert.xml",2);
		target.addChild(child);
		IXMLElement child2=new XMLElement("to","insert.xml",1);
		target.insertChild(child2,1);
		Vector children=(Vector)getField(target,"children");
		assertTrue(children.contains(child2));
		assertEquals("note",child2.getParent().getName());
		assertEquals(2,target.getChildrenCount());
		
		target.removeChildAtIndex(0);
		assertFalse(children.contains(child));
		assertEquals(1,target.getChildrenCount());	
		
	}
	
	@Test
	public void testIsLeaf() throws Exception
	{
		target=new XMLElement("note","insert.xml",1);
		assertTrue(target.isLeaf());
		IXMLElement child=new XMLElement("from","insert.xml",2);
		target.addChild(child);
		assertFalse(target.isLeaf());
		assertTrue(child.isLeaf());
	}
	
	@Test
	public void testHadChildren() throws Exception
	{
		target=new XMLElement("note","insert.xml",1);
		assertFalse(target.hasChildren());
		IXMLElement child=new XMLElement("from","insert.xml",2);
		target.addChild(child);
		assertTrue(target.hasChildren());
		assertFalse(child.hasChildren());
		
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testGetChildAtIndexOutOfBound()
	{
		target=new XMLElement("note","insert.xml",1);
		IXMLElement child=new XMLElement("from","insert.xml",2);
		target.addChild(child);
		IXMLElement child2=new XMLElement("to","insert.xml",1);
		target.insertChild(child2,1);
		target.getChildAtIndex(2);
		
	}
	@Test
	public void testGetChildAtIndex()
	{
		target=new XMLElement("note","insert.xml",1);
		IXMLElement child=new XMLElement("from","insert.xml",2);
		target.addChild(child);
		IXMLElement child2=new XMLElement("to","insert.xml",1);
		target.insertChild(child2,1);
		assertEquals("from",target.getChildAtIndex(0).getName());
		assertEquals("to",target.getChildAtIndex(1).getName());
	}
	
	@Test
	public void testGetFirstChildNames()
	{
		target=new XMLElement("note","insert.xml",1);
		IXMLElement child=new XMLElement("from","insert.xml",2);
		target.addChild(child);
		IXMLElement child2=new XMLElement("to","insert.xml",1);
		target.insertChild(child2,1);
		assertEquals("from",target.getFirstChildNamed("from").getName());
		assertNull(target.getFirstChildNamed("subject"));
	}
	
	@Test
	public void testGetFirstChildNameSpace()
	{
		target=new XMLElement("n:note","http://www.w3.org/TR/html4/","insert.xml",1);
		IXMLElement childP1=new XMLElement("n:subject","http://www.w3.org/TR/html4/","insert.xml",2);
		target.addChild(childP1);
		IXMLElement child2P1=new XMLElement("n:to","http://www.w3.org/TR/html4/","insert.xml",1);
		target.addChild(child2P1);
		
		target=new XMLElement("f:note","http://www.w3.org/TR/html5/","insert.xml",1);
		IXMLElement childP2=new XMLElement("f:from","http://www.w3.org/TR/html5/","insert.xml",2);
		target.addChild(childP2);
		IXMLElement child2P2=new XMLElement("f:to","http://www.w3.org/TR/html5/","insert.xml",1);
		target.addChild(child2P2);
		assertEquals("from",target.getFirstChildNamed("from","http://www.w3.org/TR/html5/").getName());
		assertNull(target.getFirstChildNamed("from","http://www.w3.org/TR/html4/"));
		assertNull(target.getFirstChildNamed("subject","http://www.w3.org/TR/html5/"));
	}
	
	@Test
	public void testGetAllChildNames()
	{
		target=new XMLElement("note","insert.xml",1);
		IXMLElement child=new XMLElement("from","insert.xml",2);
		target.addChild(child);
		IXMLElement child2=new XMLElement("to","insert.xml",1);
		target.addChild(child2);
		IXMLElement child3=new XMLElement("to","insert.xml",3);
		target.addChild(child3);
		Vector allChildren=target.getChildrenNamed("to");
		assertEquals(2,allChildren.size());
		allChildren=target.getChildrenNamed("subject");
		assertEquals(0,allChildren.size());
		
	}
	@Test
	public void testGellChildrenNamedNameSpace()
	{
		target=new XMLElement("f:note","http://www.w3.org/TR/html5/","insert.xml",1);
		IXMLElement childP2=new XMLElement("f:to","http://www.w3.org/TR/html5/","insert.xml",2);
		target.addChild(childP2);
		IXMLElement child2P2=new XMLElement("f:to","http://www.w3.org/TR/html5/","insert.xml",1);
		target.addChild(child2P2);
		IXMLElement child3P1=new XMLElement("n:to","http://www.w3.org/TR/html4/","insert.xml",1);
		target.addChild(child3P1);
		Vector allChildren=target.getChildrenNamed("to","http://www.w3.org/TR/html5/");
		assertEquals(2,allChildren.size());
		allChildren=target.getChildrenNamed("subject","http://www.w3.org/TR/html5/");
		assertEquals(0,allChildren.size());
	}
	
	@Test
	public void testGetSetAttribute()
	{
		target=new XMLElement("note","insert.xml",1);
		IXMLElement childP2=new XMLElement("to","insert.xml",2);
		childP2.setAttribute("type","book");
		target.addChild(childP2);
		target.setAttribute("address","email");
		String attributeValue=target.getAttribute("address");
		assertEquals("email",attributeValue);
		attributeValue=childP2.getAttribute("type");
		assertEquals("book",attributeValue);
	}
	
	@Test
	public void testNullAttribute()
	{
		target=new XMLElement("note","insert.xml",1);
		IXMLElement childP2=new XMLElement("to","insert.xml",2);
		assertEquals("book",childP2.getAttribute(null,"book"));
	}
	
	@Test
	public void testGetSetAttributeNameSpace()
	{
		target=new XMLElement("n:note","http://www.w3.org/TR/html4/","insert.xml",1);
		IXMLElement childP2=new XMLElement("f:to","http://www.w3.org/TR/html5/","insert.xml",2);
		childP2.setAttribute("type","http://www.w3.org/TR/html5/","book");
		target.addChild(childP2);
		target.setAttribute("type","http://www.w3.org/TR/html4/","email");
		String attributeValue=target.getAttribute("type","http://www.w3.org/TR/html4/","post");
		assertEquals("email",attributeValue);
		attributeValue=childP2.getAttribute("type","http://www.w3.org/TR/html5/","notebook");
		
		assertEquals("book",attributeValue);
		assertEquals("CDATA",target.getAttributeType("type"));
		assertEquals("http://www.w3.org/TR/html4/",target.getAttributeNamespace("type"));
		assertEquals("CDATA",target.getAttributeType("type","http://www.w3.org/TR/html4/"));
		
	}
	
	@Test
	public void testNullAttributeNameSpace()
	{
		target=new XMLElement("note","http://www.w3.org/TR/html4/","insert.xml",1);
		IXMLElement childP2=new XMLElement("n:to","http://www.w3.org/TR/html4/","insert.xml",2);
		assertEquals("book",childP2.getAttribute(null,"http://www.w3.org/TR/html4/","book"));
		assertNull(target.getAttributeType("type"));
		assertNull(target.getAttributeNamespace("type"));
		assertNull(target.getAttributeType("type","http://www.w3.org/TR/html4/"));
	}
	
	@Test
	public void testRemoveAttribute() throws Exception
	{
		
		target=new XMLElement("note","insert.xml",1);
		IXMLElement childP2=new XMLElement("to","insert.xml",2);
		childP2.setAttribute("type","book");
		target.addChild(childP2);
		target.setAttribute("address","email");
		String attributeValue=target.getAttribute("address");
		assertEquals("email",attributeValue);
		attributeValue=childP2.getAttribute("type");
		assertEquals("book",attributeValue);
		
		Vector attributes=(Vector)getField(target,"attributes");
		assertEquals(1,attributes.size());
		assertTrue(target.hasAttribute("address"));
		target.removeAttribute("address");		
		target.removeAttribute("email");
		assertEquals(0,attributes.size());
		assertFalse(target.hasAttribute("address"));
	}
	
	@Test
	public void testRemoveAttributeNameSpace() throws Exception
	{
		target=new XMLElement("n:note","http://www.w3.org/TR/html4/","insert.xml",1);
		IXMLElement childP2=new XMLElement("f:to","http://www.w3.org/TR/html5/","insert.xml",2);
		childP2.setAttribute("type","http://www.w3.org/TR/html5/","book");
		target.addChild(childP2);
		target.setAttribute("type","http://www.w3.org/TR/html4/","email");
		String attributeValue=target.getAttribute("type","http://www.w3.org/TR/html4/","post");
		assertEquals("email",attributeValue);
		attributeValue=childP2.getAttribute("type","http://www.w3.org/TR/html5/","notebook");
		
		Vector attributes=(Vector)getField(target,"attributes");
		assertEquals(1,attributes.size());
		assertTrue(target.hasAttribute("type","http://www.w3.org/TR/html4/"));
		target.removeAttribute("type","http://www.w3.org/TR/html4/");
		
		target.removeAttribute("email","http://www.w3.org/TR/html4/");
		assertEquals(0,attributes.size());
		assertFalse(target.hasAttribute("type","http://www.w3.org/TR/html4/"));
	}
	
	@Test
	public void testNotEqualsXMLElement() throws Exception
	{
		
		target=new XMLElement("note","insert.xml",1);
		IXMLElement childTarget=new XMLElement("to","insert.xml",1);
		target.addChild(childTarget);
		IXMLElement target1=new XMLElement("note","insert.xml",1);
		target.setAttribute("type","email");
		target.setAttribute("address","post");
		target1.setAttribute("type","email");
		target1.setAttribute("address","post");
		target1.setAttribute("phone","123456");
		assertFalse(target.equalsXMLElement(target1));
	}
	
	@Test
	public void testEqualsXMLElement() throws Exception
	{
		
		target=new XMLElement("note","insert.xml",1);
		IXMLElement childTarget=new XMLElement("to","insert.xml",1);
		target.addChild(childTarget);
		IXMLElement target1=new XMLElement("note","insert.xml",1);
		IXMLElement childTarget1=new XMLElement("to","insert.xml",1);
		target1.addChild(childTarget1);
		target.setAttribute("type","email");
		target.setAttribute("address","post");
		target1.setAttribute("type","email");
		target1.setAttribute("address","post");
		assertTrue(target.equalsXMLElement(target1));
	}
	
	@Test
	public void testEqualsThrowException()
	{
		target=new XMLElement("note","insert.xml",1);
		String target1="new Element";
		assertFalse(target1.equals(target));
				
	}
	
	
	
	
	
}
