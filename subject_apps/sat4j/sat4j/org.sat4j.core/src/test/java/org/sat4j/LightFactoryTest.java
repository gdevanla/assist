package org.sat4j;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sat4j.specs.ISolver;
/**
 * 
 * @author icewariya
 *
 */
public class LightFactoryTest extends TestCase{
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	@Before
	public void setUp() {
	    System.setOut(new PrintStream(outContent));
	    System.setSecurityManager(new NoExitSecurityManager());
	}

	@After
	public void cleanUp() {
	    System.setOut(null);
	    System.setSecurityManager(null);
	}
	
	@Test
    public void testInstanceNull() throws Exception {
		Method m = LightFactory.class.getDeclaredMethod("instance");
		m.setAccessible( true );
		LightFactory factory = (LightFactory) m.invoke(LightFactory.class);
		
		Field f = LightFactory.class.getDeclaredField( "instance" );
		f.setAccessible( true );
		LightFactory instance = (LightFactory) f.get( LightFactory.class );
		
		assertEquals(instance, factory);
	}
	
	@Test
    public void testCreateInstanceNull() throws Exception {
		Method m = LightFactory.class.getDeclaredMethod("createInstance");
		m.setAccessible( true );
		m.invoke(LightFactory.class);
		
		Field f = LightFactory.class.getDeclaredField( "instance" );
		f.setAccessible( true );
		LightFactory instance = (LightFactory) f.get( LightFactory.class );
		
		assertEquals(LightFactory.instance(), instance);
	}
	
	@Test
    public void testCreateInstanceNotNull() throws Exception {
		Method m = LightFactory.class.getDeclaredMethod("createInstance");
		m.setAccessible( true );
		m.invoke(LightFactory.class);
		
		Field f = LightFactory.class.getDeclaredField( "instance" );
		f.setAccessible( true );
		LightFactory instance = (LightFactory) f.get( LightFactory.class );
		
		assertEquals(LightFactory.instance(), instance);

		Method again = LightFactory.class.getDeclaredMethod("createInstance");
		again.setAccessible( true );
		again.invoke(LightFactory.class);
		
		assertEquals(LightFactory.instance(), instance);
	}
	
	@Test
    public void testInstanceNotNull() throws Exception {
		Method m = LightFactory.class.getDeclaredMethod("instance");
		m.setAccessible( true );
		LightFactory factory = (LightFactory) m.invoke(LightFactory.class);
		
		Field f = LightFactory.class.getDeclaredField( "instance" );
		f.setAccessible( true );
		LightFactory instance = (LightFactory) f.get( LightFactory.class );
		
		assertEquals(instance, factory);
	}

	@Test
    public void testDefaultSolver() throws Exception {
		Field f = LightFactory.class.getDeclaredField( "instance" );
		f.setAccessible( true );
		LightFactory instance = (LightFactory) f.get( LightFactory.class );
		
		ISolver solver = instance.defaultSolver();
		
		assertEquals(2147483, solver.getTimeout());
	}
	
	@Test
    public void testLightSolver() throws Exception {
		Field f = LightFactory.class.getDeclaredField( "instance" );
		f.setAccessible( true );
		LightFactory instance = (LightFactory) f.get( LightFactory.class );
		
		ISolver solver = instance.lightSolver();
		
		assertEquals(2147483, solver.getTimeout());
	}
	
	@Test
    public void testMainExample1() throws Exception {
		String[] args= new String[0];
		LightFactory.main(args);
		
		assertTrue(outContent.toString().contains("java -jar org.sat4j.core.jar <cnffile>"));
	}
	
	@Test
    public void testMainExample2() throws Exception {
		String[] args= new String[1];
		try {
			LightFactory.main(args);
		} catch (ExitException e) 
    	{
			assertEquals(0, e.status);
    	}
		
	}
	
	@Test
    public void testMainExample3() throws Exception {
		String[] args= new String[2];
		String PREFIX = "org.sat4j.core/src/test/testfiles/";
		String cnffile = PREFIX + "aim/aim-50-1_6-yes1-1.cnf";
		args[0] = "Default";
		args[1]= cnffile;
		try {
			LightFactory.main(args);
		} catch (ExitException e) 
    	{
			assertEquals(0, e.status);
    	}
		
	}
	

}
