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

public class HeuristicsTracingTest extends TestCase{
	private HeuristicsTracing heuristicsTracing;
	
	private IVisualizationTool visuTool;
    
    public HeuristicsTracingTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.visuTool = mock(IVisualizationTool.class);
        this.heuristicsTracing = new HeuristicsTracing(visuTool);
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testSolutionFound() throws Exception {
    	ISolverService solverService = mock(ISolverService.class);
    	heuristicsTracing.init(solverService);
    	
    	int[] model = new int[5];
    	for(int i=0;i<5;i++)
    		model[i] = i;
    	heuristicsTracing.solutionFound(model);
    }
    
    public void testRestarting() throws Exception {
    	ISolverService solverService = mock(ISolverService.class);
    	heuristicsTracing.init(solverService);
    	
    	heuristicsTracing.restarting();
    	assertEquals(Integer.MIN_VALUE,visuTool.NOTGOOD.intValue());
    }
    
    public void testInit() throws Exception {
    	ISolverService solverService = mock(ISolverService.class);
    	heuristicsTracing.init(solverService);
		
		Field field = heuristicsTracing.getClass().getDeclaredField( "solverService" );
		field.setAccessible( true );
		ISolverService expected = (ISolverService) field.get( heuristicsTracing );
		
		assertEquals(solverService, expected);
    }
    
}
