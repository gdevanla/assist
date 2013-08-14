package org.sat4j;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sat4j.reader.GroupedCNFReader;
import org.sat4j.reader.LecteurDimacs;
import org.sat4j.reader.Reader;
import org.sat4j.specs.ISolver;
import org.sat4j.tools.xplain.Explainer;
/**
 * 
 * @author icewariya
 *
 */
public class MUSLauncherTest extends TestCase{
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private MUSLauncher launcher;
	@Before
	public void setUp() throws Exception {
		super.setUp();
	    System.setOut(new PrintStream(outContent));
	    System.setSecurityManager(new NoExitSecurityManager());
	    launcher = new MUSLauncher();
	}

	@After
	public void cleanUp() throws Exception{
		super.tearDown();
	    System.setOut(null);
	    System.setSecurityManager(null);
	}
	
	@Test
    public void testUsage() throws Exception {		
		launcher.usage();
		assertTrue(outContent.toString().contains("java -jar sat4j-mus.jar [Insertion|Deletion|QuickXplain] <cnffile>|<gcnffile>"));
	}
	
	@Test
    public void testCreateReaderExample1() throws Exception {
		String[] args= new String[2];
		String PREFIX = "org.sat4j.core/src/test/testfiles/";
		String cnffile = PREFIX + "aim/aim-50-1_6-yes1-1.cnf";
		args[0] = "Default";
		args[1]= cnffile;

		ISolver solver = launcher.configureSolver(args);
		Reader reader = launcher.createReader(solver, "Sat");
		
		assertEquals(LecteurDimacs.class, reader.getClass());
	}
	
	@Test
    public void testCreateReaderExample2() throws Exception {
		String[] args= new String[2];
		String PREFIX = "org.sat4j.core/src/test/testfiles/";
		String cnffile = PREFIX + "aim/aim-50-1_6-yes1-1.gcnf";
		args[0] = "Default";
		args[1]= cnffile;

		ISolver solver = launcher.configureSolver(args);
		Reader reader = launcher.createReader(solver, "Sat");
		
		assertEquals(GroupedCNFReader.class, reader.getClass());
	}
	@Test
    public void testGetInstanceNameExample1() throws Exception {
		String[] args= new String[0];
		
		assertEquals(null, launcher.getInstanceName(args));
	}
	
	@Test
    public void testGetInstanceNameExample2() throws Exception {
		String[] args= new String[1];
		String PREFIX = "org.sat4j.core/src/test/testfiles/";
		String cnffile = PREFIX + "aim/aim-50-1_6-yes1-1.cnf"; 
		args[0]= cnffile;
		
		assertEquals(args[0], launcher.getInstanceName(args));
	}
	
	@Test
    public void testConfigureSolverExample1() throws Exception {
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
		String[] args= new String[2];
		String PREFIX = "org.sat4j.core/src/test/testfiles/";
		String cnffile = PREFIX + "aim/aim-50-1_6-yes1-1.gcnf";
		args[0] = "Default";
		args[1]= cnffile;

		ISolver solver = launcher.configureSolver(args);
		assertEquals(Integer.MAX_VALUE, solver.getTimeout());
		assertTrue(solver.isDBSimplificationAllowed());
		
	}
	
	@Test
	public void testMainExample1() throws Exception {
		String[] args = new String[0];
		
		MUSLauncher.main(args);
		assertTrue(outContent.toString().contains("java -jar sat4j-mus.jar [Insertion|Deletion|QuickXplain] <cnffile>|<gcnffile>"));
	}
	
	@Test
	public void testMainExample2() throws Exception {
		String[] args = new String[4];
		
		MUSLauncher.main(args);
		assertTrue(outContent.toString().contains("java -jar sat4j-mus.jar [Insertion|Deletion|QuickXplain] <cnffile>|<gcnffile>"));
	}
	
	@Test
	public void testMainExample3() throws Exception {
		String[] args= new String[1];
		String PREFIX = "org.sat4j.core/src/test/testfiles/";
		String cnffile = PREFIX + "aim/aim-50-1_6-yes1-1.cnf"; 
		args[0]= cnffile;
		try {
			MUSLauncher.main(args);
		} catch(ExitException e) {
			assertEquals(10, e.status);
		}
	}
	
	@Test
    public void testDisplayResultExample1() throws Exception {
		String[] args= new String[1];
		String PREFIX = "org.sat4j.core/src/test/testfiles/";
		String cnffile = PREFIX + "aim/aim-50-1_6-yes1-1.cnf"; 
		args[0]= cnffile;	
		
		ISolver solver = launcher.configureSolver(args);
		launcher.solver = solver;
		
		launcher.displayResult();
		assertTrue(outContent.toString().contains("Total wall clock time (in seconds)"));
	}
	
	@Test
    public void testDisplayResultExample2() throws Exception {
		String[] args= new String[1];
		String PREFIX = "org.sat4j.core/src/test/testfiles/";
		String cnffile = PREFIX + "aim/aim-50-1_6-yes1-1.cnf"; 
		args[0]= cnffile;	
		ExitCode exitCode = ExitCode.UNSATISFIABLE;
		
		ISolver solver = launcher.configureSolver(args);
		launcher.solver = solver;
		launcher.setExitCode(exitCode);
		
		launcher.displayResult();
		assertTrue(outContent.toString().contains("Total wall clock time (in seconds)"));
	}
	
	@Test
    public void testDisplayResultExample3() throws Exception {
		String[] args= new String[1];
		String PREFIX = "org.sat4j.core/src/test/testfiles/";
		String cnffile = PREFIX + "aim/aim-50-1_6-yes1-1.cnf"; 
		args[0]= cnffile;	
		ExitCode exitCode = ExitCode.SATISFIABLE;
		
		launcher.setExitCode(exitCode);
		ISolver solver = launcher.configureSolver(args);
		launcher.solver = solver;
		launcher.solve(solver);
		
		try {		
			launcher.displayResult();
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testRunExample1() throws Exception {
		String[] args= new String[1];
		String PREFIX = "org.sat4j.core/src/test/testfiles/";
		String cnffile = PREFIX + "aim/aim-200-2_0-no-4.cnf"; 
		args[0]= cnffile;	
		ExitCode exitCode = ExitCode.UNSATISFIABLE;
		
		launcher.setExitCode(exitCode);
		launcher.run(args);
		
		assertTrue(outContent.toString().contains("Unsat detection wall clock time (in seconds)"));
		
		launcher.displayResult();
	}
	
	@Test
    public void testDisplayLicense() throws Exception {
		launcher.displayLicense();
		assertTrue(outContent.toString().contains("SAT4J: a SATisfiability library for Java (c) 2004-2012 Artois University and CNRS"));
	}
	
	@Test
    public void testSetMethodsAbstractLauncher() throws Exception {
		launcher.setLauncherMode(ILauncherMode.OPTIMIZATION);
		launcher.setIncomplete(true);
		launcher.setDisplaySolutionLine(true);
		launcher.setSilent(true);
		
		assertEquals(true, launcher.silent);
		
		launcher.log("");
	}
	
	@Test
	public void testGetBeginTime() throws Exception {
		assertEquals(0, launcher.getBeginTime());
	}
	
	@Test
	public void testGetReader() throws Exception {
		assertEquals(null, launcher.getReader());
	}
	
}


