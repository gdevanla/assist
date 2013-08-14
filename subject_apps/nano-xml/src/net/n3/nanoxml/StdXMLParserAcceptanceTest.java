package net.n3.nanoxml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

public class StdXMLParserAcceptanceTest {
	private IXMLParser target = null;

	private StdXMLBuilder builder = null;
	private IXMLReader reader = null;
	private NonValidator validator=null;

	@Before
	public void setUp() throws Exception {

		target = XMLParserFactory.createDefaultXMLParser();
		reader = StdXMLReader.fileReader("AcceptanceInputFile/mainInternal.xml");
		target.setReader(reader);
		builder = new StdXMLBuilder();
		target.setBuilder(new StdXMLBuilder());
		validator=new NonValidator();
		target.setValidator(validator);
	}

	private Object getField(Object instance, String fieldName) throws Exception {
		Class c = instance.getClass();

		// Retrieve the field with the specified name
		Field f = c.getDeclaredField(fieldName);
		f.setAccessible(true);

		// Return the value of the field for the instance
		return f.get(instance);
	}

	@Test
	public void testprivateFields() throws Exception {
		IXMLBuilder builder = (IXMLBuilder) getField(target, "builder");
		IXMLReader reader = (IXMLReader) getField(target, "reader");
		IXMLEntityResolver entityResolver = (IXMLEntityResolver) getField(
				target, "entityResolver");
		IXMLValidator validator = (IXMLValidator) getField(target, "validator");
		Hashtable entities = (Hashtable) getField(entityResolver, "entities");
		assertEquals(5, entities.size());

	}

	@Test(expected = XMLParseException.class)
	public void testParserUnExpectedEntity() throws Exception {
		try {
			IXMLReader reader = StdXMLReader
					.fileReader("TestInputFiles/unexpectedEntityError.xml");
			target.setReader(reader);
			IXMLElement element = new XMLElement("note",
					"file:unexpectedEntityError.xml", 1);
			builder = new StdXMLBuilder(element);
			target.setBuilder(builder);
			target.setValidator(new NonValidator());
			target.parse();
		} catch (XMLParseException e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}

	@Test(expected = XMLParseException.class)
	public void testException() throws Exception {
		try {
			IXMLReader reader = StdXMLReader.fileReader("TestInputFiles/cData.xml");
			target.setReader(reader);
			target.parse();
			assertTrue(target.parse() instanceof XMLElement);
		} catch (XMLParseException e) {
			System.out.println(e.getMessage());
			throw e;
		}

	}

	@Test
	public void testParserWithOutNS() throws Exception {
		reader = StdXMLReader.fileReader("AcceptanceInputFile/main.xml");
		target.setReader(reader);
		IXMLElement xml = (IXMLElement) target.parse();
		assertEquals("file:AcceptanceInputFile/Note.dtd", xml.getSystemID());
		assertEquals("note", xml.getName());
		assertEquals("note", xml.getFullName());
		assertEquals(4, xml.getChildrenCount());
		assertEquals("to", xml.getChildAtIndex(0).getName());
		assertEquals("from", xml.getChildAtIndex(1).getName());
		assertEquals("heading", xml.getChildAtIndex(2).getName());
		assertEquals("body", xml.getChildAtIndex(3).getName());
		XMLWriter writer = new XMLWriter(System.out);
		writer.write(xml);
	}

	@Test
	public void testParserWithNS() throws Exception {
		IXMLElement children = null;
		reader = StdXMLReader.fileReader("AcceptanceInputFile/MainWithNS.xml");
		target.setReader(reader);
		IXMLElement xml = (IXMLElement) target.parse();
		assertEquals("file:AcceptanceInputFile/MainWithNS.xml", xml.getSystemID());
		assertEquals(2, xml.getChildrenCount());
		assertEquals("root", xml.getName());

		children = xml.getChildAtIndex(0);
		assertEquals("http://www.w3.org/TR/html4/", children.getNamespace());
		assertEquals("h:table", children.getFullName());

		assertEquals("h:tr", children.getChildAtIndex(0).getFullName());
		assertEquals("h:td", children.getChildAtIndex(0).getChildAtIndex(0)
				.getFullName());

		children = xml.getChildAtIndex(1);
		assertEquals("http://www.w3schools.com/furniture",
				children.getNamespace());
		assertEquals("f:table", children.getFullName());
		assertEquals("f:name", children.getChildAtIndex(0).getFullName());
		assertEquals("f:width", children.getChildAtIndex(1).getFullName());
		assertEquals("f:length", children.getChildAtIndex(2).getFullName());
		XMLWriter writer = new XMLWriter(System.out);
		writer.write(xml);
	}

	@Test
	public void testParserWithAttNoDTD() throws Exception {
		reader = StdXMLReader.fileReader("AcceptanceInputFile/AttNoDTD.xml");
		target.setReader(reader);
		IXMLElement xml = (IXMLElement) target.parse();
		assertEquals("person", xml.getName());
		assertEquals("female", xml.getAttribute("sex", "male"));
		Vector v = xml.getChildrenNamed("firstname");
		IXMLElement name = (IXMLElement) v.get(0);
		assertEquals("firstname", name.getName());
		XMLWriter writer = new XMLWriter(System.out);
		writer.write(xml);
	}

	@Test
	public void testParserWithAttDTD() throws Exception {
		IXMLElement children = null;
		reader = StdXMLReader.fileReader("AcceptanceInputFile/MainWithAtt.xml");
		target.setReader(reader);
		IXMLElement xml = (IXMLElement) target.parse();
		assertEquals("note", xml.getName());
		children = xml.getChildAtIndex(2);
		assertFalse(children.hasAttribute("tag"));
		assertEquals("h1", children.getAttribute("tag", "h1"));
		children = xml.getChildAtIndex(3);
		assertTrue(children.hasAttribute("tag"));
		assertEquals("content", children.getAttribute("tag", "h1"));
		assertNull(children.getAttributeNamespace("tag"));
		XMLWriter writer = new XMLWriter(System.out);
		writer.write(xml);
	}

	@Test
	public void testParseSkipComment() throws Exception {
		IXMLElement children = null;
		reader = StdXMLReader.fileReader("AcceptanceInputFile/MainWithAtt.xml");
		target.setReader(reader);
		IXMLElement xml = (IXMLElement) target.parse();
		assertEquals("note", xml.getName());
		children = xml.getChildAtIndex(0);
		// this is inside comment so it skips everything in the comment
		assertNotSame("content", children.getName());
		XMLWriter writer = new XMLWriter(System.out);
		writer.write(xml);
	}

	@Test
	public void testParseInternalDTD() throws Exception {

		reader = StdXMLReader.fileReader("AcceptanceInputFile/mainInternal.xml");
		target.setReader(reader);
		IXMLElement xml = (IXMLElement) target.parse();
		assertEquals("file:AcceptanceInputFile/mainInternal.xml", xml.getSystemID());
		assertEquals("note", xml.getName());
		assertEquals("note", xml.getFullName());
		assertEquals(5, xml.getChildrenCount());
		assertEquals("to", xml.getChildAtIndex(0).getName());
		assertEquals("from", xml.getChildAtIndex(1).getName());
		assertEquals("heading", xml.getChildAtIndex(2).getName());
		assertEquals("body", xml.getChildAtIndex(3).getName());
		XMLWriter writer = new XMLWriter(System.out);
		writer.write(xml);
	}

	@Test
	public void testParseInternalDTDSkipComment() throws Exception {

		IXMLElement children = null;
		reader = StdXMLReader.fileReader("AcceptanceInputFile/mainInternal.xml");
		target.setReader(reader);
		IXMLElement xml = (IXMLElement) target.parse();
		assertEquals("note", xml.getName());
		children = xml.getChildAtIndex(2);
		assertFalse(children.hasAttribute("tag"));
		// skips the ATTLIST in dtd since it is inside comment
		assertNotSame("h1", children.getAttribute("tag"));
		XMLWriter writer = new XMLWriter(System.out);
		writer.write(xml);
	}

	@Test
	public void testParseCDATA() throws Exception {

		IXMLElement children = null;
		reader = StdXMLReader.fileReader("AcceptanceInputFile/mainInternal.xml");
		target.setReader(reader);
		IXMLElement xml = (IXMLElement) target.parse();
		assertEquals("note", xml.getName());
		children = xml.getChildAtIndex(4);

		assertEquals("script", children.getName());
		assertFalse(children.hasChildren());
		XMLWriter writer = new XMLWriter(System.out);
		writer.write(xml);

	}

	@Test
	public void testParseIgnoreSection() throws Exception {

		IXMLElement children = null;
		reader = StdXMLReader.fileReader("AcceptanceInputFile/mainInternal.xml");
		target.setReader(reader);
		IXMLElement xml = (IXMLElement) target.parse();
		assertEquals("note", xml.getName());
		assertFalse(xml.hasAttribute("tag"));
		// skips the ATTLIST in dtd since it is inside Ignore Section
		assertNotSame("title", xml.getAttribute("tag"));
		XMLWriter writer = new XMLWriter(System.out);
		writer.write(xml);
	}

	@Test
	public void testParseIncludeSection() throws Exception {
		IXMLElement children = null;
		reader = StdXMLReader.fileReader("AcceptanceInputFile/mainInternal.xml");
		target.setReader(reader);
		IXMLElement xml = (IXMLElement) target.parse();
		assertEquals("note", xml.getName());

		// Include section has attribute for child 1

		children = xml.getChildAtIndex(1);
		assertEquals("yes", children.getAttribute("include"));
		XMLWriter writer = new XMLWriter(System.out);
		writer.write(xml);
	}

	// Entity Type decarations
	@Test
	public void testParseEntity() throws Exception {
		IXMLElement children = null;
		reader = StdXMLReader.fileReader("AcceptanceInputFile/mainInternal.xml");
		target.setReader(reader);
		IXMLElement xml = (IXMLElement) target.parse();
		// Child 3 body has entity type content defined in xml document
		children = xml.getChildAtIndex(3);
		assertEquals("this is content with proper text", children.getContent());
		XMLWriter writer = new XMLWriter(System.out);
		 writer.write(xml);
	}

	@Test
	public void testParseEntityIgnoreSection() throws Exception {
		IXMLElement children = null;
		IXMLElement xml = (IXMLElement) target.parse();
		assertEquals("note", xml.getName());
		children = xml.getChildAtIndex(0);

		// Entity has Ignore Section
		assertFalse(children.hasAttribute("tag"));
		assertNotSame("title", children.getAttribute("tag"));
		assertEquals("toAddress", children.getAttribute("title"));
		XMLWriter writer = new XMLWriter(System.out);
		 writer.write(xml);
	}

	// Public Identifier in the DocType
	@Test
	public void testPublicID() throws Exception {
		reader = StdXMLReader.fileReader("AcceptanceInputFile/MainWithAtt.xml");
		target.setReader(reader);
		IXMLElement xml = (IXMLElement) target.parse();
		assertEquals("file:AcceptanceInputFile/example.dtd", xml.getSystemID());
	}

	@Test
	public void testEndElement() throws Exception {
		reader = StdXMLReader.fileReader("AcceptanceInputFile/MainWithAtt.xml");
		target.setReader(reader);
		IXMLElement xml = (IXMLElement) target.parse();
		assertEquals(5, xml.getChildrenCount());
	}

	@Test(expected = XMLParseException.class)
	public void testImProperEndElement() throws Exception {
		try {
			reader = StdXMLReader.fileReader("AcceptanceInputFile/inValid.xml");
			target.setReader(reader);
			IXMLElement xml = (IXMLElement) target.parse();
		} catch (XMLParseException e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}

	@Test
	public void testParserInternalDocTypeWithNameSpace() throws Exception {
		reader = StdXMLReader.fileReader("AcceptanceInputFile/sample.xml");
		target.setReader(reader);
		IXMLElement xml = (IXMLElement) target.parse();
		assertEquals(1, xml.getChildrenCount());
		assertEquals("http://www.xmlpowercorp.com/dtds/", xml.getNamespace());
	}

	@Test(expected = XMLParseException.class)
	public void testParserWithInValidCloseTag() throws Exception {
		try {
			reader = StdXMLReader.fileReader("AcceptanceInputFile/ErrorInput.xml");
			target.setReader(reader);
			IXMLElement xml = (IXMLElement) target.parse();
		} catch (XMLParseException e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}

	@Test(expected = XMLParseException.class)
	public void testParserErrorCloseTag() throws Exception {
		try {
			reader = StdXMLReader.fileReader("AcceptanceInputFile/errorClose.xml");
			target.setReader(reader);
			IXMLElement xml = (IXMLElement) target.parse();
		} catch (XMLParseException e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}

	// Process the entity which has a xmlns as attribute
	@Test
	public void testParseWithAttXMLNS() throws Exception {
		reader = StdXMLReader.fileReader("AcceptanceInputFile/AttNoDTD.xml");
		target.setReader(reader);
		IXMLElement xml = (IXMLElement) target.parse();
		assertEquals("person", xml.getName());
		assertEquals("http://www.w3.org/TR/html4/", xml.getNamespace());
	}

	@Test
	public void testParseChildNull() throws Exception {
		reader = StdXMLReader.fileReader("AcceptanceInputFile/childNull.xml");
		target.setReader(reader);
		IXMLElement xml = (IXMLElement) target.parse();
		IXMLElement child = new XMLElement(null);
		xml.addChild(child);
		assertNull(child.getName());
		assertEquals("person", xml.getName());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testParseIllegalChild() throws Exception {
		reader = StdXMLReader.fileReader("AcceptanceInputFile/childNull.xml");
		target.setReader(reader);
		IXMLElement xml = (IXMLElement) target.parse();
		xml.addChild(null);
	}
	
	
	//parses an Fixed Attribute for an element
	@Test
	public void testParseFixedAttribute() throws Exception
	{
		reader = StdXMLReader.fileReader("AcceptanceInputFile/mainInternal.xml");
		target.setReader(reader);
		IXMLElement xml = (IXMLElement) target.parse();
		assertTrue(xml.hasAttribute("fixed"));
		assertEquals("name",xml.getAttribute("fixed"));
	}
	
	//parses an required Attribute for an element
	@Test
	public void testParseRequiredAttribute() throws Exception
	{
		reader = StdXMLReader.fileReader("AcceptanceInputFile/mainInternal.xml");
		target.setReader(reader);
		IXMLElement xml = (IXMLElement) target.parse();
		assertFalse(xml.hasAttribute("required"));
		assertEquals("yes",xml.getAttribute("required","yes"));
	}
	
	@Test
	public void testParseEnumeratedAttribute() throws Exception
	{
		reader = StdXMLReader.fileReader("AcceptanceInputFile/mainInternal.xml");
		target.setReader(reader);
		IXMLElement xml = (IXMLElement) target.parse();
		IXMLElement headingChild=xml.getChildAtIndex(2);
		assertEquals("h2",headingChild.getAttribute("markUp"));
		Enumeration attributeName=headingChild._enumerateAttributeNames();
		while(attributeName.hasMoreElements())
		{
			assertEquals("markUp",attributeName.nextElement());
		}
	}
	
	@Test
	public void testParseGetAllAttribute() throws Exception
	{
		reader = StdXMLReader.fileReader("AcceptanceInputFile/MainWithAtt.xml");
		target.setReader(reader);
		IXMLElement xml = (IXMLElement) target.parse();
		IXMLElement bodyChild=xml.getChildAtIndex(3);
		assertEquals("body",bodyChild.getName());
		Properties allAttributes=bodyChild.getAttributes();
		assertEquals(2,allAttributes.size());
		assertEquals("content",allAttributes.getProperty("tag"));
		assertEquals("main",allAttributes.getProperty("style"));
	}
	
	// Checks the validator public and system id
	@Test
	public void testParsePublicNonValidator() throws Exception
	{
		reader = StdXMLReader.fileReader("AcceptanceInputFile/ValidatorPubSys.xml");
		target.setReader(reader);
		IXMLElement xml = (IXMLElement) target.parse();
		assertEquals("file:AcceptanceInputFile/example.dtd",xml.getSystemID());
		Hashtable entities=(Hashtable)getField(validator.parameterEntityResolver,"entities");
		String[] str=(String[])entities.get("student");
		assertEquals("pubID",(str[0]));
		assertEquals("example.dtd",(str[1]));
	}
	
	
}
