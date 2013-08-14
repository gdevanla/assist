package net.n3.nanoxml;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;


public class XMLAttributeTest {
	
	private XMLAttribute target;
	@Before
	public void setUp()
	{
		target=new XMLAttribute("type","type","nameSpace","123","int");
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

	/**
	 * Executes a method on an object instance.  The name and parameters of
	 * the method are specified.  The method will be executed and the value
	 * of it returned, even if the method would have private or protected access.
	 */
	private Object executeMethod( Object instance, String name, Object[] params ) throws Exception
	{
		Class c 	= instance.getClass();

		// Fetch the Class types of all method parameters
		Class[] types 	= new Class[params.length];

		for ( int i = 0; i < params.length; i++ )
			types[i] = params[i].getClass();
		Method m        = c.getDeclaredMethod( name, types );
		m.setAccessible( true );
		return m.invoke( instance, params );
	}
	
	@Test
	public void testFullName() throws Exception
	{
		String fullName= (String) executeMethod(target, "getFullName", new String[] { } );
		assertEquals("type",fullName);
	}
	
	@Test
	public void testName() throws Exception
	{
		String name= (String) executeMethod(target, "getName", new String[] { } );
		assertEquals("type",name);
	}
	
	@Test
	public void testNameSpace() throws Exception
	{
		String nameSpace= (String) executeMethod(target, "getNamespace", new String[] { } );
		assertEquals("nameSpace",nameSpace);
	}
	
	@Test
	public void testValue() throws Exception
	{
		String value= (String) executeMethod(target, "getValue", new String[] { } );
		assertEquals("123",value);
	}
	
	@Test
	public void testGetType() throws Exception
	{
		String type= (String) executeMethod(target, "getType", new String[] { } );
		assertEquals("int",type);
	}
}
