package org.sat4j;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.ResourceBundle;

import junit.framework.TestCase;

import org.junit.Test;
/**
 * 
 * @author icewariya
 *
 */
public class MessagesTest extends TestCase{
	@Test
    public void testMissingResource() throws Exception {
		String key = "org.sat4j.messages";
		String expected = "!org.sat4j.messages!";
		
		assertEquals(expected, Messages.getString(key));
	}
	
	@Test
    public void testResource() throws Exception {
		
		Field f = Messages.class.getDeclaredField( "RESOURCE_BUNDLE" );
		f.setAccessible( true );
		ResourceBundle bundle = (ResourceBundle) f.get( Messages.class );
		
		assertTrue(Messages.getString("MoreThanSAT.0").contains(""));
		assertTrue(Messages.getString("MoreThanSAT.3").contains("Number of solutions"));
	}
	
	@Test
	public void testPrivateConstructor() throws Exception{
	  Constructor constructor = Messages.class.getDeclaredConstructor();
	  constructor.setAccessible(true);
	  assertEquals(Messages.class, constructor.newInstance().getClass());
	}
}
