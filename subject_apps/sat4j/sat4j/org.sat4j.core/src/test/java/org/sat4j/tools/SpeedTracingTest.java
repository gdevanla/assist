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

public class SpeedTracingTest extends TestCase{
	private SpeedTracing tracing;
	
	private IVisualizationTool visuTool;
    private IVisualizationTool cleanVisuTool;
    private IVisualizationTool restartVisuTool;
    
    public SpeedTracingTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.visuTool = mock(IVisualizationTool.class);
        this.cleanVisuTool = mock(IVisualizationTool.class);
        this.restartVisuTool = mock(IVisualizationTool.class);
        this.tracing = new SpeedTracing(visuTool, cleanVisuTool, restartVisuTool);
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testPropogating() throws Exception {
    	DimacsOutputSolver dimacsOutputSolver = new DimacsOutputSolver();
    	IVecInt literals = new VecInt();
    	for(int i=0;i<10;i++)
    		literals.push(i);
    	IConstr expected = dimacsOutputSolver.addClause(literals);
    	tracing.propagating(3, expected);

		Field f = tracing.getClass().getDeclaredField( "counter" );
		f.setAccessible( true );
		int counter = (Integer) f.get( tracing );
	
		assertEquals(1, counter);
    }
    
    public void testEndExample1() throws Exception {
    	tracing.end(Lbool.TRUE);
    	assertEquals(Integer.MIN_VALUE,visuTool.NOTGOOD.intValue());
    }
    
    public void testEndExample2() throws Exception {
    	tracing.end(Lbool.FALSE);
    	assertEquals(Integer.MIN_VALUE,visuTool.NOTGOOD.intValue());
    }
    
    public void testStart() throws Exception {
    	tracing.start();
    	Field f = tracing.getClass().getDeclaredField( "counter" );
		f.setAccessible( true );
		int counter = (Integer) f.get( tracing );
	
		assertEquals(0, counter);
    }
    
    public void testRestarting() throws Exception {
    	try {
    		tracing.restarting();
    	} catch (Exception e) {
    		
    	}
    	
    }
    
    public void testInit() throws Exception {
    	ISolverService solverService = mock(ISolverService.class);
    	tracing.init(solverService);
    	
    	Field f = tracing.getClass().getDeclaredField( "nVar" );
		f.setAccessible( true );
		int nVar = (Integer) f.get( tracing );
		
		assertEquals(nVar, solverService.nVars());
    }
    
    public void testCleaning() throws Exception {
    	try {
    		tracing.cleaning();
    	} catch (Exception e) {
    		
    	}
    }
}
