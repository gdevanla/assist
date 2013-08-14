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

public class DecisionLevelTracingTest extends TestCase{
	private DecisionLevelTracing decisionLevelTracing;
	
	private IVisualizationTool visuTool;
    
    public DecisionLevelTracingTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.visuTool = mock(IVisualizationTool.class);
        this.decisionLevelTracing = new DecisionLevelTracing(visuTool);
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
    	decisionLevelTracing.conflictFound(expected, 1, 1);

		Field f = decisionLevelTracing.getClass().getDeclaredField( "counter" );
		f.setAccessible( true );
		int counter = (Integer) f.get( decisionLevelTracing );
	
		assertEquals(1, counter);
    }
    
    
    public void testConflictFoundExample2() throws Exception {
    	DimacsOutputSolver dimacsOutputSolver = new DimacsOutputSolver();
    	IVecInt literals = new VecInt();
    	for(int i=0;i<10;i++)
    		literals.push(i);
    	IConstr expected = dimacsOutputSolver.addClause(literals);
    	decisionLevelTracing.conflictFound(expected, -1, 1);

		Field f = decisionLevelTracing.getClass().getDeclaredField( "counter" );
		f.setAccessible( true );
		int counter = (Integer) f.get( decisionLevelTracing );
	
		assertEquals(1, counter);
    }
    
    public void testEndExample1() throws Exception {
    	decisionLevelTracing.end(Lbool.TRUE);
    	assertEquals(Integer.MIN_VALUE,visuTool.NOTGOOD.intValue());
    }
    
    public void testEndExample2() throws Exception {
    	decisionLevelTracing.end(Lbool.FALSE);
    	assertEquals(Integer.MIN_VALUE,visuTool.NOTGOOD.intValue());
    }
    
    public void testStart() throws Exception {
    	decisionLevelTracing.start();
    	Field f = decisionLevelTracing.getClass().getDeclaredField( "counter" );
		f.setAccessible( true );
		int counter = (Integer) f.get( decisionLevelTracing );
	
		assertEquals(0, counter);
    }
    
    public void testRestarting() throws Exception {
    	decisionLevelTracing.backjump(4);
    	assertEquals(Integer.MIN_VALUE,visuTool.NOTGOOD.intValue());
    }
    
}
