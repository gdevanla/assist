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

public class LearnedClauseSizeTracingTest extends TestCase{
	private LearnedClauseSizeTracing tracing;
	
	private IVisualizationTool visuTool;
    
    public LearnedClauseSizeTracingTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.visuTool = mock(IVisualizationTool.class);
        this.tracing = new LearnedClauseSizeTracing(visuTool);
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testConflictFound() throws Exception {
    	IVecInt ps = new VecInt();
        ILits voc = new Lits();
        ps.push(67);
        ps.push(70);

    	IConstr expected = new AtLeast(voc, ps, 0);
    	tracing.conflictFound(expected, 1, 1);

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
}
