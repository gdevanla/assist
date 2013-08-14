package org.sat4j.tools;
import static org.mockito.Mockito.mock;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;

import junit.framework.TestCase;

import org.sat4j.core.VecInt;
import org.sat4j.specs.IConstr;
import org.sat4j.specs.ISolverService;
import org.sat4j.specs.IVecInt;
import org.sat4j.specs.Lbool;

public class ConflictLevelTracingTest extends TestCase{
	private ConflictLevelTracing conflictLevelTracing;
	
	private IVisualizationTool visuTool;
    private IVisualizationTool restartVisuTool;
    private IVisualizationTool cleanTool;
    
    public ConflictLevelTracingTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.visuTool = mock(IVisualizationTool.class);
        this.restartVisuTool = mock(IVisualizationTool.class);
        this.cleanTool = mock(IVisualizationTool.class);
        this.conflictLevelTracing = new ConflictLevelTracing(visuTool, restartVisuTool, cleanTool);
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testConflictFoundExample1() throws Exception {
    	DimacsOutputSolver dimacsOutputSolver = new DimacsOutputSolver();
    	IVecInt literals = new VecInt();
    	for(int i=0;i<10;i++)
    		literals.push(i);
    	IConstr expected = dimacsOutputSolver.addClause(literals);
    	conflictLevelTracing.conflictFound(expected, 1, 1);

		Field f = conflictLevelTracing.getClass().getDeclaredField( "counter" );
		f.setAccessible( true );
		int counter = (Integer) f.get( conflictLevelTracing );
	
		assertEquals(2, counter);
    }
    
    
    public void testConflictFoundExample2() throws Exception {
    	DimacsOutputSolver dimacsOutputSolver = new DimacsOutputSolver();
    	IVecInt literals = new VecInt();
    	for(int i=0;i<10;i++)
    		literals.push(i);
    	IConstr expected = dimacsOutputSolver.addClause(literals);
    	conflictLevelTracing.conflictFound(expected, -1, 1);

		Field f = conflictLevelTracing.getClass().getDeclaredField( "counter" );
		f.setAccessible( true );
		int counter = (Integer) f.get( conflictLevelTracing );
	
		assertEquals(2, counter);
    }
    
    public void testEndExample1() throws Exception {
    	conflictLevelTracing.end(Lbool.TRUE);
    	assertEquals(Integer.MIN_VALUE,visuTool.NOTGOOD.intValue());
    }
    
    public void testEndExample2() throws Exception {
    	conflictLevelTracing.end(Lbool.FALSE);
    	assertEquals(Integer.MIN_VALUE,visuTool.NOTGOOD.intValue());
    }
    
    public void testStart() throws Exception {
    	conflictLevelTracing.start();
    	Field f = conflictLevelTracing.getClass().getDeclaredField( "counter" );
		f.setAccessible( true );
		int counter = (Integer) f.get( conflictLevelTracing );
	
		assertEquals(1, counter);
    }
    
    public void testRestarting() throws Exception {
    	conflictLevelTracing.restarting();
    	assertEquals(Integer.MIN_VALUE,visuTool.NOTGOOD.intValue());
    }
    
    public void testInit() throws Exception {
    	ISolverService solverService = mock(ISolverService.class);
    	conflictLevelTracing.init(solverService);
    	
    	Field f = conflictLevelTracing.getClass().getDeclaredField( "nVar" );
		f.setAccessible( true );
		int nVar = (Integer) f.get( conflictLevelTracing );
		
		assertEquals(nVar, solverService.nVars());
    }
    
    public void testCleaning() throws Exception {
    	conflictLevelTracing.cleaning();
    	assertEquals(Integer.MIN_VALUE,visuTool.NOTGOOD.intValue());
    }
}
