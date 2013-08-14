package org.sat4j.tools;
import static org.mockito.Mockito.mock;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;

import junit.framework.TestCase;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.constraints.card.AtLeast;
import org.sat4j.minisat.constraints.cnf.Lits;
import org.sat4j.minisat.core.ILits;
import org.sat4j.specs.IConstr;
import org.sat4j.specs.ISolverService;
import org.sat4j.specs.IVecInt;
import org.sat4j.specs.Lbool;

public class LearnedClausesSizeTracingTest extends TestCase{
	private LearnedClausesSizeTracing tracing;
	
	private IVisualizationTool visuTool;
    private IVisualizationTool restartTool;
    private IVisualizationTool cleanTool;
    
    public LearnedClausesSizeTracingTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.visuTool = mock(IVisualizationTool.class);
        this.restartTool = mock(IVisualizationTool.class);
        this.cleanTool = mock(IVisualizationTool.class);
        this.tracing = new LearnedClausesSizeTracing(visuTool, restartTool, cleanTool);
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
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
    	tracing.restarting();
    	assertEquals(Integer.MIN_VALUE,visuTool.NOTGOOD.intValue());
    }
    
    public void testCleaning() throws Exception {
    	tracing.cleaning();
    	assertEquals(Integer.MIN_VALUE,visuTool.NOTGOOD.intValue());
    }
    
    public void testLearnExample1() throws Exception {
    	IVecInt ps = new VecInt();
        ILits voc = new Lits();
        ps.push(67);
        ps.push(70);

    	IConstr expected = new AtLeast(voc, ps, 0);
    	tracing.learn(expected);

		Field f = tracing.getClass().getDeclaredField( "counter" );
		f.setAccessible( true );
		int counter = (Integer) f.get( tracing );
	
		assertEquals(1, counter);
    }
    
    
    public void testLearnExample2() throws Exception {
    	IVecInt ps = new VecInt();
        ILits voc = new Lits();

    	IConstr expected = new AtLeast(voc, ps, 0);
    	tracing.learn(expected);

		Field f = tracing.getClass().getDeclaredField( "counter" );
		f.setAccessible( true );
		int counter = (Integer) f.get( tracing );
	
		assertEquals(1, counter);
    }
}
