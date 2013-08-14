package net.n3.nanoxml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;


public class NonValidatorTest {
	private NonValidator target=null;
	private IXMLEntityResolver resolver=null;
	private String systemID;

	@Before
	public void setUp()
	{
		target=new NonValidator();
		resolver=new XMLEntityResolver();
		target.setParameterEntityResolver(resolver);
		
	}
	
	/**
	 * This is a utility function to get the private variables of a class
	 * @param instance
	 * @param fieldName
	 * @return
	 * @throws Exception
	 */
	private Object getField( Object instance, String fieldName ) throws Exception
	{
		Class c = instance.getClass();

		// Retrieve the field with the specified name
		Field f = c.getDeclaredField( fieldName );
		f.setAccessible( true );

		// Return the value of the field for the instance
		return f.get( instance );
	}

	
	/**
	 * This utility function skips the number of characters while reading
	 * from an input file
	 * 
	 * @param reader
	 * @param numberOfChars
	 * @throws Exception
	 */
	//skips the < , !, third Character (E or A) from the DTD 
	private void skipCharacters(IXMLReader reader,int numberOfChars) throws Exception
	{
		int i=0;
		while(i<numberOfChars)
		{
			String str = XMLUtil.read(reader, '%');
			char ch = str.charAt(0);
			i++;
		}
	}
	
	//checks whether the Entity in DTD is added to the hashTable.
	@Test
	public void testProcessEntity() throws Exception
	{
		IXMLReader reader=StdXMLReader.fileReader("TestInputFiles/example.dtd");
		skipCharacters(reader,3);
		Hashtable entities=(Hashtable)getField(resolver,"entities");
		assertNull(entities.get("p"));
		target.processEntity(reader,resolver );
		assertEquals("(#PCDATA)",entities.get("p"));
	}
	
	@Test
	public void testProcessEntitySystemID() throws Exception
	{
		IXMLReader reader=StdXMLReader.fileReader("TestInputFiles/ProcessSysIDEntity.dtd");
		skipCharacters(reader,3);
		Hashtable entities=(Hashtable)getField(resolver,"entities");
		target.processEntity(reader,resolver );
		String[] str=(String[])entities.get("student");
		assertNull((str[0]));
		assertEquals("example.dtd",(str[1]));
	}
	
	@Test
	public void testProcessEntityPublicID() throws Exception
	{
		IXMLReader reader=StdXMLReader.fileReader("TestInputFiles/ProcessPubIDEntity.dtd");
		skipCharacters(reader,3);
		Hashtable entities=(Hashtable)getField(resolver,"entities");
		target.processEntity(reader,resolver );
		String[] str=(String[])entities.get("student");
		assertEquals("pubID",(str[0]));
		assertEquals("example.dtd",(str[1]));
	}
	
	@Test
	public void testProcessAttListElement() throws Exception
	{
		IXMLReader reader=StdXMLReader.fileReader("TestInputFiles/ProcessAttList.dtd");
		skipCharacters(reader,3);
		target.processAttList(reader,resolver );
		Hashtable attributeValues=(Hashtable)getField(target,"attributeDefaultValues");
		Properties prop=(Properties)attributeValues.get("payment");
		assertTrue(prop.containsKey("type"));
		assertEquals("check",prop.get("type"));
		
	}

	@Test
	public void testProcessAttListDefaultValElement() throws Exception
	{
		IXMLReader reader=StdXMLReader.fileReader("TestInputFiles/ProcessAttListDefaultVal.dtd");
		skipCharacters(reader,3);
		target.processAttList(reader,resolver );
		Hashtable attributeValues=(Hashtable)getField(target,"attributeDefaultValues");
		Properties prop=(Properties)attributeValues.get("person");
		assertTrue(prop.containsKey("number"));
		assertEquals("12",prop.get("number"));
	}
	
	
	@Test
	public void testProcessElementWithEntity() throws Exception
	{
		IXMLReader reader=StdXMLReader.fileReader("TestInputFiles/example.dtd");
		skipCharacters(reader,1);
		Hashtable entities=(Hashtable)getField(resolver,"entities");
		assertNull(entities.get("p"));
		target.processElement(reader, resolver);
		assertEquals("(#PCDATA)",entities.get("p"));
	}
	
	@Test
	public void testProcessElementWithAttribute() throws Exception
	{
		IXMLReader reader=StdXMLReader.fileReader("TestInputFiles/ProcessAttList.dtd");
		skipCharacters(reader,1);
		target.processElement(reader, resolver);
		Hashtable attributeValues=(Hashtable)getField(target,"attributeDefaultValues");
		Properties prop=(Properties)attributeValues.get("payment");
		assertTrue(prop.containsKey("type"));
		assertEquals("check",prop.get("type"));
		
	}
	
	@Test
	public void testParseDTDEntity() throws Exception
	{
		IXMLReader reader=StdXMLReader.fileReader("TestInputFiles/example.dtd");
		target.parseDTD(null,reader, resolver,true);
		Hashtable entities=(Hashtable)getField(resolver,"entities");
		assertEquals("(#PCDATA)",entities.get("p"));
	}
	
	@Test
	public void testParseDTDEntityPublSysID() throws Exception
	{
		systemID="TestInputFiles/ProcessPubIDEntity.dtd";
		IXMLReader reader=StdXMLReader.fileReader(systemID);
		Hashtable entities=(Hashtable)getField(resolver,"entities");
		target.parseDTD(null,reader, resolver,true);
		String[] str=(String[])entities.get("student");
		assertEquals("pubID",(str[0]));
		assertEquals("example.dtd",(str[1]));
	}
	
	@Test
	public void testParseDTDAtListElement() throws Exception
	{
		systemID="TestInputFiles/ProcessAttList.dtd";
		IXMLReader reader=StdXMLReader.fileReader(systemID);
		target.parseDTD(null,reader, resolver,true);
		Hashtable attributeValues=(Hashtable)getField(target,"attributeDefaultValues");
		Properties prop=(Properties)attributeValues.get("payment");
		assertTrue(prop.containsKey("type"));
		assertEquals("check",prop.get("type"));
		
	}

	@Test
	public void testParseDTDAttListDefaultValElement() throws Exception
	{
		systemID="TestInputFiles/ProcessAttListDefaultVal.dtd";
		IXMLReader reader=StdXMLReader.fileReader(systemID);
		target.parseDTD(null,reader, resolver,true);
		Hashtable attributeValues=(Hashtable)getField(target,"attributeDefaultValues");
		Properties prop=(Properties)attributeValues.get("person");
		assertTrue(prop.containsKey("number"));
		assertEquals("12",prop.get("number"));
	}
	
	
	//checks whether the element is started while processing the DTD
	@Test
	public void testElementStarted() throws Exception
	{
		systemID="TestInputFiles/ProcessAttList.dtd";
		IXMLReader reader=StdXMLReader.fileReader(systemID);
		target.parseDTD(null,reader, resolver,true);
		Hashtable attributeValues=(Hashtable)getField(target,"attributeDefaultValues");
		Properties prop=(Properties)attributeValues.get("payment");
		target.elementStarted("payment",systemID,1);
		Stack currElements=(Stack)getField(target,"currentElements");
		Properties top=(Properties)currElements.peek();
		assertEquals(prop.get("type"),top.get("type"));
		
	}
	
	@Test
	public void testElementStartedAttributesNull() throws Exception
	{
		systemID="TestInputFiles/ProcessAttList.dtd";
		IXMLReader reader=StdXMLReader.fileReader(systemID);
		IXMLEntityResolver resolver=new XMLEntityResolver();
		target.setParameterEntityResolver(resolver);
		target.elementStarted("payment",systemID,1);
		Stack currElements=(Stack)getField(target,"currentElements");
		Properties top=(Properties)currElements.peek();
		assertNull(top.get("type"));
		
	}
	
	//checks whether the attributes and its values are processed
	@Test
	public void testElementAttributeProcess() throws Exception
	{
		systemID="TestInputFiles/ProcessAttList.dtd";
		IXMLReader reader=StdXMLReader.fileReader(systemID);
		target.parseDTD(null,reader, resolver,true);
		target.elementStarted("payment",systemID,1);
		Stack currElements=(Stack)getField(target,"currentElements");
		Properties top=(Properties)currElements.peek();
		Properties extraAttributes=new Properties();
		target.elementAttributesProcessed("payment",extraAttributes, systemID, 1);
		assertEquals(extraAttributes.get("type"),top.get("type"));
	
	}
}
