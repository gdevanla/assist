package net.n3.nanoxml;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.Hashtable;

import org.junit.Before;
import org.junit.Test;


public class XMLEntityResolverTest {
	private XMLEntityResolver target=null;
	
	@Before
	public void setUp()
	{
		target=new XMLEntityResolver();
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
	public void testEntrySize() throws Exception
	{
		Hashtable entries=(Hashtable)getField(target,"entities");
		assertEquals(5,entries.size());
	}
	
	@Test
	public void testAddInternalEntity() throws Exception
	{
		target.addInternalEntity("hash","&#35;");
		Hashtable entries=(Hashtable)getField(target,"entities");
		assertEquals(6,entries.size());
	}
	
	@Test
	public void testBadAddInternalEntity() throws Exception
	{
		target.addInternalEntity("amp","&#35;");
		Hashtable entries=(Hashtable)getField(target,"entities");
		assertEquals(5,entries.size());
	}
	
	@Test
	public void testAddExternalEntity() throws Exception
	{
		target.addExternalEntity("externalEntity",null,"file:sample.xml");
		Hashtable entries=(Hashtable)getField(target,"entities");
		assertEquals(6,entries.size());
	}
	
	@Test
	public void testBadAddExternalEntity() throws Exception
	{
		target.addExternalEntity("externalEntity",null,"file:sample.xml");
		Hashtable entries=(Hashtable)getField(target,"entities");
		assertEquals(6,entries.size());
		target.addExternalEntity("externalEntity","publicID","file:sample.xml");
		assertEquals(6,entries.size());
		
	}
	
	@Test
	public void testGetEntity() throws Exception
	{
		IXMLReader reader = StdXMLReader.fileReader("TestInputFiles/test.xml");
		Reader result=target.getEntity(reader,"amp");
		assertTrue(result instanceof StringReader);
	}
	
	@Test
	public void testNullObjectGetEntity() throws Exception
	{
		IXMLReader reader = StdXMLReader.fileReader("TestInputFiles/test.xml");
		Reader result=target.getEntity(reader,"hash");
		assertNull(result);
	}
	@Test
	public void testReturnExternalEntity() throws Exception
	{
		target.addExternalEntity("externalEntity",null,"file:TestInputFiles/test.xml");
		IXMLReader reader = StdXMLReader.fileReader("TestInputFiles/test.xml");
		Reader result=target.getEntity(reader,"externalEntity");
		assertTrue(result instanceof InputStreamReader);
	}
	
	@Test
	public void testIsNotExternalEntity() throws Exception
	{
		assertFalse(target.isExternalEntity("amp"));
	}
	
	@Test
	public void testIsExternalEntity() throws Exception
	{
		target.addExternalEntity("externalEntity",null,"file:test.xml");
		assertTrue(target.isExternalEntity("externalEntity"));
	}
	
	@Test(expected=XMLParseException.class)
	public void testThrowException() throws Exception
	{
		target.addExternalEntity("externalEntity",null,"file:sample.xml");
		IXMLReader reader = StdXMLReader.fileReader("TestInputFiles/test.xml");
		Reader result=target.getEntity(reader,"externalEntity");
		assertTrue(result instanceof InputStreamReader);
	
	}
}
