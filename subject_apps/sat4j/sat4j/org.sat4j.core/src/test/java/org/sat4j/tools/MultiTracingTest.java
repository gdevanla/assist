package org.sat4j.tools;

import static org.mockito.Mockito.mock;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.minisat.constraints.card.AtLeast;
import org.sat4j.minisat.constraints.cnf.Lits;
import org.sat4j.minisat.core.ILits;
import org.sat4j.specs.IConstr;
import org.sat4j.specs.ISolverService;
import org.sat4j.specs.IVecInt;
import org.sat4j.specs.Lbool;
import org.sat4j.specs.SearchListener;

import junit.framework.TestCase;

public class MultiTracingTest extends TestCase{
	private MultiTracing tracing;
	private SearchListener listeners;
    
    public MultiTracingTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        SolutionFoundListener sfl = SolutionFoundListener.VOID; 
        this.listeners = new SearchEnumeratorListener(sfl);
        this.tracing = new MultiTracing(listeners);
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    private Object getField(Object instance, String name)  throws Exception{
    	Class c = instance.getClass();

		// Retrieve the field with the specified name
		Field f = c.getDeclaredField( name );

		// *MAGIC* make sure the field is accessible, even if it
		// would be private or protected
		f.setAccessible( true );

		// Return the value of the field for the instance
		return f.get( instance );
    }
    
    public void testAdding() throws Exception {
    	for(int i=0;i<10;i++)
    	tracing.adding(i);
    	
    	Collection<SearchListener> listeners = (Collection<SearchListener>) getField(tracing, "listeners");
    	assertEquals(1, listeners.size());
    }
    
    public void testAssuming() throws Exception {
    	tracing.assuming(5);
    	
    	Collection<SearchListener> listeners = (Collection<SearchListener>) getField(tracing, "listeners");
    	assertEquals(1, listeners.size());
    }
    
    public void testPropagating() throws Exception {
    	ILits voc = new Lits();
    	IVecInt ps = new VecInt();
    	ps.push(67);
        ps.push(70);
    	IConstr arg = new AtLeast(voc, ps, 0);
    	tracing.propagating(0, arg);
    	
    	Collection<SearchListener> listeners = (Collection<SearchListener>) getField(tracing, "listeners");
    	assertEquals(1, listeners.size());
    }
    
    public void testBackTracking() throws Exception {
    	tracing.backtracking(1);
    	
    	Collection<SearchListener> listeners = (Collection<SearchListener>) getField(tracing, "listeners");
    	assertEquals(1, listeners.size());
    }
    
    public void testLearn() throws Exception {
    	ILits voc = new Lits();
    	IVecInt ps = new VecInt();
    	ps.push(67);
        ps.push(70);
    	IConstr arg = new AtLeast(voc, ps, 0);
    	tracing.learn(arg);
    	
    	Collection<SearchListener> listeners = (Collection<SearchListener>) getField(tracing, "listeners");
    	assertEquals(1, listeners.size());
    }
    
    public void testDelete() throws Exception {
    	int[] clause = new int[1];
    	clause[0] = 8;
    	tracing.delete(clause);
    	
    	Collection<SearchListener> listeners = (Collection<SearchListener>) getField(tracing, "listeners");
    	assertEquals(1, listeners.size());
    }
    
    public void testConflictFoundExample1() throws Exception {
    	ILits voc = new Lits();
    	IVecInt ps = new VecInt();
    	ps.push(67);
        ps.push(70);
    	IConstr arg = new AtLeast(voc, ps, 0);
    	tracing.conflictFound(arg, 0, 0);
    	
    	Collection<SearchListener> listeners = (Collection<SearchListener>) getField(tracing, "listeners");
    	assertEquals(1, listeners.size());
    }
    
    public void testConflictFoundExample2() throws Exception {
    	tracing.conflictFound(0);
    	
    	Collection<SearchListener> listeners = (Collection<SearchListener>) getField(tracing, "listeners");
    	assertEquals(1, listeners.size());
    }
    
    public void testSolutionFound() throws Exception {
    	ISolverService sol = (ISolverService) SolverFactory.newDefault();
    	tracing.init(sol);
    	int[] clause = new int[1];
    	clause[0] = 8;
    	try {
    	tracing.solutionFound(clause);
    	} catch (Exception e) {
    		
    	}
    }
    
    public void testBeginLoop() throws Exception {
    	tracing.beginLoop();
    	
    	Collection<SearchListener> listeners = (Collection<SearchListener>) getField(tracing, "listeners");
    	assertEquals(1, listeners.size());
    }
    
    public void testStart() throws Exception {
    	tracing.start();
    	
    	Collection<SearchListener> listeners = (Collection<SearchListener>) getField(tracing, "listeners");
    	assertEquals(1, listeners.size());
    }
    
    public void testEnd() throws Exception {
    	tracing.end(Lbool.TRUE);
    	
    	Collection<SearchListener> listeners = (Collection<SearchListener>) getField(tracing, "listeners");
    	assertEquals(1, listeners.size());
    }
    
    public void testRestarting() throws Exception {
    	tracing.restarting();
    	
    	Collection<SearchListener> listeners = (Collection<SearchListener>) getField(tracing, "listeners");
    	assertEquals(1, listeners.size());
    }
    
    public void testBackjump() throws Exception {
    	tracing.backjump(0);
    	
    	Collection<SearchListener> listeners = (Collection<SearchListener>) getField(tracing, "listeners");
    	assertEquals(1, listeners.size());
    }
    
    public void testCleaning() throws Exception {
    	tracing.cleaning();
    	
    	Collection<SearchListener> listeners = (Collection<SearchListener>) getField(tracing, "listeners");
    	assertEquals(1, listeners.size());
    }
    
    public void testInit() throws Exception {
    	ISolverService sol = (ISolverService) SolverFactory.newDefault();
    	tracing.init(sol);
    	
    	Collection<SearchListener> listeners = (Collection<SearchListener>) getField(tracing, "listeners");
    	assertEquals(1, listeners.size());
    }
}
