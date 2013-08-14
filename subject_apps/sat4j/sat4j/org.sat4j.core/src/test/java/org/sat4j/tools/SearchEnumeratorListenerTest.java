package org.sat4j.tools;

import static org.mockito.Mockito.mock;

import java.lang.reflect.Field;

import junit.framework.TestCase;

import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.ISolverService;
import org.sat4j.specs.Lbool;

public class SearchEnumeratorListenerTest extends TestCase{
	private SearchEnumeratorListener listener;
	private SolutionFoundListener arg;
	
 	public SearchEnumeratorListenerTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.arg = SolutionFoundListener.VOID;
        this.listener = new SearchEnumeratorListener(arg);
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testInit() throws Exception {
    	ISolverService solverService = mock(ISolverService.class);
    	listener.init(solverService);
    	
		Field f = listener.getClass().getDeclaredField( "solverService" );
		f.setAccessible( true );
		ISolverService expected = (ISolverService) f.get( listener );
		
		assertEquals(expected, solverService);
    }
    
    public void testSolutionFoundExample1() throws Exception {
    	ISolverService solverService = (ISolverService) SolverFactory.newDefault();
    	listener.init(solverService);
    	
    	int[] model = new int[5];
    	for(int i=0;i<5;i++)
    		model[i] = i;
    	try {
    		listener.solutionFound(model);
    	} catch (Exception e) {
    		
    	}
    }
    
    public void testSolutionFoundExample2() throws Exception {
    	ISolverService solverService = (ISolverService) SolverFactory.newDefault();
    	listener.init(solverService);
    	
    	int[] model = new int[0];
    	try {
    		listener.solutionFound(model);
    	} catch (Exception e) {
    		
    	}
    }
    public void testEndExample1() throws Exception {
    	listener.end(Lbool.TRUE);
    	
    }
    
    public void testEndExample2() throws Exception {
    	listener.end(Lbool.FALSE);
    }
    
    public void testEndExample3() throws Exception {
    	listener.end(Lbool.UNDEFINED);
    }
    
    public void testGetNumberOfSolutionFound() throws Exception {
    	Field f = listener.getClass().getDeclaredField( "nbsolutions" );
		f.setAccessible( true );
		int expected = (Integer) f.get( listener );
		
		assertEquals(0, listener.getNumberOfSolutionFound());
    }
}
