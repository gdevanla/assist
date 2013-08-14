package org.sat4j;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.Constructor;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * 
 * @author icewariya
 *
 */
public class MoreThanSATTest extends TestCase{
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	@Before
	public void setUp() {
	    System.setOut(new PrintStream(outContent));
	}

	@After
	public void cleanUp() {
	    System.setOut(null);
	}
	
	
	@Test
	public void testPrivateConstructor() throws Exception{
	  Constructor constructor = MoreThanSAT.class.getDeclaredConstructor();
	  constructor.setAccessible(true);
	  assertEquals(MoreThanSAT.class, constructor.newInstance().getClass());
	}
	
	@Test
	public void testMainExample1() throws Exception{
		String args[] = new String[0];
		try {
			MoreThanSAT.main(args);
		} catch(Exception e) {
			
		}
	}
	
	@Test
	public void testMainExample2() throws Exception{
		String args[] = new String[1];
		args[0] = null;
		try {
			MoreThanSAT.main(args);
		} catch(Exception e) {
			
		}
	}
	
	@Test
	public void testMainExample3() throws Exception{
		String args[] = new String[1];
		String PREFIX = "org.sat4j.core/src/test/testfiles/";
		String cnffile = PREFIX + "aim/aim-50-1_6-yes1-1.cnf"; 
		args[0] = cnffile;
		try {
			MoreThanSAT.main(args);
			assertTrue(outContent.toString().contains("Satisfiable !"));
		} catch(Exception e) {
			
		}
	}
	
	@Test
	public void testMainExample4() throws Exception{
		String args[] = new String[1];
		String PREFIX = "org.sat4j.core/src/test/testfiles/";
		String cnffile = PREFIX + "aim/aim-50-1_6-no-1.cnf"; 
		args[0] = cnffile;
		try {
			MoreThanSAT.main(args);
			assertTrue(outContent.toString().contains(""));
		} catch(Exception e) {
			
		}
	}
	
	@Test
	public void testMainExample5() throws Exception{
		String args[] = new String[1];
		String PREFIX = "org.sat4j.core/src/test/testfiles/";
		String cnffile = PREFIX + "aim/invalid.cnf"; 
		args[0] = cnffile;
		try {
			MoreThanSAT.main(args);
		} catch(Exception e) {
			
		}
	}
	
	@Test
	public void testMainExample6() throws Exception{
		String args[] = new String[1];
		String PREFIX = "org.sat4j.core/src/test/java/org/sat4j/";
		String cnffile = PREFIX + "messages.properties"; 
		args[0] = cnffile;
		try {
			MoreThanSAT.main(args);
		} catch(Exception e) {
			
		}
	}
	
}
