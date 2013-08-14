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

public class LBDTracingTest extends TestCase{
	private LBDTracing lbdTracing;
	
	private IVisualizationTool visuTool;
    
    public LBDTracingTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.visuTool = mock(IVisualizationTool.class);
        this.lbdTracing = new LBDTracing(visuTool);
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
    	lbdTracing.conflictFound(expected, 1, 1);

		Field f = lbdTracing.getClass().getDeclaredField( "counter" );
		f.setAccessible( true );
		int counter = (Integer) f.get( lbdTracing );
	
		assertEquals(0, counter);
    }
    
    public void testEndExample1() throws Exception {
    	lbdTracing.end(Lbool.TRUE);
    	assertEquals(Integer.MIN_VALUE,visuTool.NOTGOOD.intValue());
    }
    
    public void testEndExample2() throws Exception {
    	lbdTracing.end(Lbool.FALSE);
    	assertEquals(Integer.MIN_VALUE,visuTool.NOTGOOD.intValue());
    }
    
    public void testStart() throws Exception {
    	lbdTracing.start();
    	Field f = lbdTracing.getClass().getDeclaredField( "counter" );
		f.setAccessible( true );
		int counter = (Integer) f.get( lbdTracing );
	
		assertEquals(0, counter);
    }
}
