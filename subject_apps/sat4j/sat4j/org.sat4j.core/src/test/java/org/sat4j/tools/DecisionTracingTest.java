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

public class DecisionTracingTest extends TestCase{
	private DecisionTracing decisionTracing;
	
	private IVisualizationTool positiveVisu;
    private IVisualizationTool negativeVisu;
    private IVisualizationTool restartVisu;
    private IVisualizationTool cleanVisu;
    
    public DecisionTracingTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.positiveVisu = mock(IVisualizationTool.class);
        this.negativeVisu = mock(IVisualizationTool.class);
        this.restartVisu = mock(IVisualizationTool.class);
        this.cleanVisu = mock(IVisualizationTool.class);
        this.decisionTracing = new DecisionTracing(positiveVisu, negativeVisu, restartVisu, cleanVisu);
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testAssumingExample1() throws Exception {
    	decisionTracing.assuming(4);

		Field f = decisionTracing.getClass().getDeclaredField( "counter" );
		f.setAccessible( true );
		int counter = (Integer) f.get( decisionTracing );
	
		assertEquals(2, counter);
    }
    
    public void testAssumingExample2() throws Exception {
    	decisionTracing.assuming(-4);

		Field f = decisionTracing.getClass().getDeclaredField( "counter" );
		f.setAccessible( true );
		int counter = (Integer) f.get( decisionTracing );
	
		assertEquals(2, counter);
    }
    
    public void testEndExample1() throws Exception {
    	decisionTracing.end(Lbool.TRUE);
    	assertEquals(Integer.MIN_VALUE,positiveVisu.NOTGOOD.intValue());
    }
    
    public void testEndExample2() throws Exception {
    	decisionTracing.end(Lbool.FALSE);
    	assertEquals(Integer.MIN_VALUE,positiveVisu.NOTGOOD.intValue());
    }
    
    public void testStart() throws Exception {
    	decisionTracing.start();
    	Field f = decisionTracing.getClass().getDeclaredField( "counter" );
		f.setAccessible( true );
		int counter = (Integer) f.get( decisionTracing );
	
		assertEquals(1, counter);
    }
    
    public void testRestarting() throws Exception {
    	decisionTracing.restarting();
    	assertEquals(Integer.MIN_VALUE,positiveVisu.NOTGOOD.intValue());
    }
    
    public void testInit() throws Exception {
    	ISolverService solverService = mock(ISolverService.class);
    	decisionTracing.init(solverService);
    	
    	Field f = decisionTracing.getClass().getDeclaredField( "nVar" );
		f.setAccessible( true );
		int nVar = (Integer) f.get( decisionTracing );
		
		assertEquals(nVar, solverService.nVars());
    }
    
    public void testCleaning() throws Exception {
    	decisionTracing.cleaning();
    	assertEquals(Integer.MIN_VALUE,positiveVisu.NOTGOOD.intValue());
    }
}
