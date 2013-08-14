package net.n3.nanoxml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;


public class XMLExceptionTest {

	private XMLException target=null;
	@Before
	public void setUp()
	{
		target=new XMLException("Exception message");
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
	public void testXMLExceptionWithMessage() throws Exception
	{
		target=new XMLException("Exception message");
		String message=(String)getField(target,"msg");
		String sysID=(String)getField(target,"systemID");
		Exception ex=(Exception)getField(target,"encapsulatedException");
		assertEquals("Exception message",message);
		assertNull(sysID);
	    assertNull(ex);
	 }
	
	@Test
	public void testIllegalAccessException() throws Exception
	{
		IllegalArgumentException e=new IllegalArgumentException();
		target=new XMLException(e);
		String message=(String)getField(target,"msg");
		String sysID=(String)getField(target,"systemID");
		Exception ex=(Exception)getField(target,"encapsulatedException");
		assertEquals("Nested Exception",message);
		assertEquals("java.lang.IllegalArgumentException",e.toString());
		
	 }
	@Test
	public void testClassNotFound() throws Exception
	{
		ClassNotFoundException e=new ClassNotFoundException();
		target=new XMLException(e);
		String message=(String)getField(target,"msg");
		String sysID=(String)getField(target,"systemID");
		Exception ex=(Exception)getField(target,"encapsulatedException");
		assertEquals("Nested Exception",message);
		assertEquals("java.lang.ClassNotFoundException",e.toString());
	}
	
	@Test
	public void testFileNotFoundException() throws Exception
	{
		FileNotFoundException e=new FileNotFoundException();
		target=new XMLException(e);
		assertEquals("Nested Exception",target.toString());
		assertEquals("java.io.FileNotFoundException",e.toString());
		
	 }
	
	@Test
	public void testXMLIllegalArgumentException() throws Exception
	{
		Exception e=new IllegalArgumentException();
		target=new XMLException("main.dtd",8,e,"child cannot be null",true);
		assertEquals("child cannot be null, SystemID='main.dtd', Line=8, Exception: java.lang.IllegalArgumentException",target.toString());
		assertEquals("java.lang.IllegalArgumentException",target.getException().toString());
	}
}
