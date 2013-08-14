package org.sat4j;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sat4j.core.ASolverFactory;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.reader.Reader;
import org.sat4j.specs.ISolver;
/**
 * 
 * @author icewariya
 *
 */
public class BasicLauncherTest extends TestCase{

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
    public void testMainExample1() throws Exception {
		String[] args= new String[0];
		BasicLauncher.main(args);
		
		assertTrue(outContent.toString().contains("java -jar org.sat4j.core.jar <cnffile>"));
	}
	
	@Test
    public void testMainExample2() throws Exception {
		String[] args= new String[4];
		args[0] = "c java";
		args[1] = "-jar";
		args[2] = "org.sat4j.core.jar";
		args[3] = "cnffile";
		BasicLauncher.main(args);
		
		assertTrue(outContent.toString().contains("java -jar org.sat4j.core.jar <cnffile>"));
	}
	
	@Test
    public void testMainExample3() throws Exception {
		String[] args= new String[1];
		String PREFIX = "org.sat4j.core/src/test/testfiles/";
		String cnffile = PREFIX + "aim/aim-50-1_6-yes1-1.cnf"; 
		args[0]= cnffile;
		try {
			BasicLauncher.main(args);
		} catch (ExitException e) 
    	{
			assertEquals(10, e.status);
    	}
	}
	
	@Test
    public void testMainExample4() throws Exception {
		String[] args= new String[2];
		String PREFIX = "org.sat4j.core/src/test/testfiles/";
		String cnffile = PREFIX + "aim/aim-50-1_6-yes1-1.cnf";
		args[0] = "Default";
		args[1]= cnffile;
		try {
			BasicLauncher.main(args);
		} catch (ExitException e) 
    	{
			assertEquals(10, e.status);
    	}
	}
	
	
	@Test
    public void testConfigureSolverExample1() throws Exception {
		BasicLauncher<ISolver> launcher = new BasicLauncher<ISolver>(SolverFactory.instance());
		String[] args= new String[1];
		String PREFIX = "org.sat4j.core/src/test/testfiles/";
		String cnffile = PREFIX + "aim/aim-50-1_6-yes1-1.cnf"; 
		args[0]= cnffile;	
		
		ISolver solver = launcher.configureSolver(args);
		assertEquals(Integer.MAX_VALUE, solver.getTimeout());
		assertTrue(solver.isDBSimplificationAllowed());
	}
	
	@Test
    public void testConfigureSolverExample2() throws Exception {
		BasicLauncher<ISolver> launcher = new BasicLauncher<ISolver>(SolverFactory.instance());
		String[] args= new String[2];
		String PREFIX = "org.sat4j.core/src/test/testfiles/";
		String cnffile = PREFIX + "aim/aim-50-1_6-yes1-1.cnf";
		args[0] = "Default";
		args[1]= cnffile;

		ISolver solver = launcher.configureSolver(args);
		assertEquals(Integer.MAX_VALUE, solver.getTimeout());
		assertTrue(solver.isDBSimplificationAllowed());
	}
	
	@Test
    public void testConfigureSolverExample3() throws Exception {
		BasicLauncher<ISolver> launcher = new BasicLauncher<ISolver>(SolverFactory.instance());
		String[] args= new String[0];

		ISolver solver = launcher.configureSolver(args);
		assertEquals(Integer.MAX_VALUE, solver.getTimeout());
		assertTrue(solver.isDBSimplificationAllowed());
	}
	
	@Test
    public void testCreateReader() throws Exception {
		BasicLauncher<ISolver> launcher = new BasicLauncher<ISolver>(SolverFactory.instance());
		String[] args= new String[2];
		String PREFIX = "org.sat4j.core/src/test/testfiles/";
		String cnffile = PREFIX + "aim/aim-50-1_6-yes1-1.cnf";
		args[0] = "Default";
		args[1]= cnffile;

		ISolver solver = launcher.configureSolver(args);
		Reader reader = launcher.createReader(solver, "Sat");
		
		assertEquals(Integer.MAX_VALUE, solver.getTimeout());
		assertTrue(solver.isDBSimplificationAllowed());

	}
	
	@Test
    public void testUsage() throws Exception {
		BasicLauncher<ISolver> launcher = new BasicLauncher<ISolver>(SolverFactory.instance());
		launcher.usage();
		assertTrue(outContent.toString().contains("java -jar org.sat4j.core.jar <cnffile>"));

	}
	
	@Test
    public void testGetInstanceNameExample1() throws Exception {
		BasicLauncher<ISolver> launcher = new BasicLauncher<ISolver>(SolverFactory.instance());
		String[] args= new String[0];
		
		assertEquals(null, launcher.getInstanceName(args));
	}
	
	@Test
    public void testGetInstanceNameExample2() throws Exception {
		BasicLauncher<ISolver> launcher = new BasicLauncher<ISolver>(SolverFactory.instance());
		String[] args= new String[1];
		String PREFIX = "org.sat4j.core/src/test/testfiles/";
		String cnffile = PREFIX + "aim/aim-50-1_6-yes1-1.cnf"; 
		args[0]= cnffile;	
		
		assertEquals(cnffile, launcher.getInstanceName(args));
	}
	
	@Test
	public void testPrivateFieldVersionID() throws Exception {
		BasicLauncher<ISolver> launcher = new BasicLauncher<ISolver>(SolverFactory.instance());
		
		Field f = launcher.getClass().getDeclaredField( "serialVersionUID" );
		f.setAccessible( true );
		Long serialVersionUID = (Long) f.get( launcher );
		
		assertEquals(1L, (long) serialVersionUID);
	}
	
	@Test
	public void testPrivateFieldFactory() throws Exception {
		BasicLauncher<ISolver> launcher = new BasicLauncher<ISolver>(SolverFactory.instance());
		
		Field f = launcher.getClass().getDeclaredField( "factory" );
		f.setAccessible( true );
		ASolverFactory<ISolver> factory = (ASolverFactory<ISolver>) f.get( launcher );
		
		Field version = factory.getClass().getDeclaredField( "serialVersionUID" );
		version.setAccessible( true );
		Long serialVersionUID = (Long) version.get( factory );
		
		assertEquals(1L, (long) serialVersionUID);
	}
}
