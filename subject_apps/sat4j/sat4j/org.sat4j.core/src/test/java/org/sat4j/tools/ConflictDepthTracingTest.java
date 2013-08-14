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

public class ConflictDepthTracingTest extends TestCase{
	private ConflictDepthTracing conflictDepthTracing;
	
	private IVisualizationTool conflictDepthVisu;
    private IVisualizationTool conflictDepthRestartVisu;
    private IVisualizationTool conflictDepthCleanVisu;
    
    public ConflictDepthTracingTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.conflictDepthCleanVisu = mock(IVisualizationTool.class);
        this.conflictDepthRestartVisu = mock(IVisualizationTool.class);
        this.conflictDepthVisu = mock(IVisualizationTool.class);
        this.conflictDepthTracing = new ConflictDepthTracing(conflictDepthVisu, conflictDepthRestartVisu, conflictDepthCleanVisu);
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testConflictFound() throws Exception {
    	DimacsOutputSolver dimacsOutputSolver = new DimacsOutputSolver();
    	IVecInt literals = new VecInt();
    	for(int i=0;i<10;i++)
    		literals.push(i);
    	IConstr expected = dimacsOutputSolver.addClause(literals);
    	conflictDepthTracing.conflictFound(expected, 1, 1);

		Field f = conflictDepthTracing.getClass().getDeclaredField( "counter" );
		f.setAccessible( true );
		int counter = (Integer) f.get( conflictDepthTracing );
	
		assertEquals(1, counter);
    }
    
    public void testEndExample1() throws Exception {
    	conflictDepthTracing.end(Lbool.TRUE);
    	assertEquals(Integer.MIN_VALUE,conflictDepthVisu.NOTGOOD.intValue());
    }
    
    public void testEndExample2() throws Exception {
    	conflictDepthTracing.end(Lbool.FALSE);
    	assertEquals(Integer.MIN_VALUE,conflictDepthVisu.NOTGOOD.intValue());
    }
    
    public void testStart() throws Exception {
    	conflictDepthTracing.start();
    	Field f = conflictDepthTracing.getClass().getDeclaredField( "counter" );
		f.setAccessible( true );
		int counter = (Integer) f.get( conflictDepthTracing );
	
		assertEquals(0, counter);
    }
    
    public void testRestarting() throws Exception {
    	conflictDepthTracing.restarting();
    	assertEquals(Integer.MIN_VALUE,conflictDepthVisu.NOTGOOD.intValue());
    }
    
    public void testInit() throws Exception {
    	ISolverService solverService = mock(ISolverService.class);
    	conflictDepthTracing.init(solverService);
    	
    	Field f = conflictDepthTracing.getClass().getDeclaredField( "nVar" );
		f.setAccessible( true );
		int nVar = (Integer) f.get( conflictDepthTracing );
		
		assertEquals(nVar, solverService.nVars());
    }
    
    public void testCleaning() throws Exception {
    	conflictDepthTracing.cleaning();
    	assertEquals(Integer.MIN_VALUE,conflictDepthVisu.NOTGOOD.intValue());
    }
}
