package org.sat4j.tools;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.minisat.constraints.card.AtLeast;
import org.sat4j.minisat.constraints.cnf.Lits;
import org.sat4j.minisat.core.ILits;
import org.sat4j.specs.IConstr;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.ISolverService;
import org.sat4j.specs.IVecInt;
import org.sat4j.specs.Lbool;
import org.sat4j.specs.SearchListener;

public class TextOutputTracingTest extends TestCase{
	private TextOutputTracing tracing;
	private Map<Integer, ISolver> mapping;
    
    public TextOutputTracingTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.mapping = new HashMap<Integer, ISolver>();
        mapping.put(1, SolverFactory.newDefault());
        this.tracing = new TextOutputTracing(mapping);
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
    	
    	Map<Integer, Integer> mapping = (Map<Integer, Integer>) getField(tracing, "mapping");
    	assertEquals(1, mapping.size());
    }
    
    public void testAssuming() throws Exception {
    	tracing.assuming(5);
    	
    	Map<Integer, Integer> mapping = (Map<Integer, Integer>) getField(tracing, "mapping");
    	assertEquals(1, mapping.size());
    }
    
    public void testPropagating() throws Exception {
    	ILits voc = new Lits();
    	IVecInt ps = new VecInt();
    	ps.push(67);
        ps.push(70);
    	IConstr arg = new AtLeast(voc, ps, 0);
    	tracing.propagating(0, arg);
    	
    	Map<Integer, Integer> mapping = (Map<Integer, Integer>) getField(tracing, "mapping");
    	assertEquals(1, mapping.size());
    }
    
    public void testBackTracking() throws Exception {
    	tracing.backtracking(-1);
    	
    	Map<Integer, Integer> mapping = (Map<Integer, Integer>) getField(tracing, "mapping");
    	assertEquals(1, mapping.size());
    }
    
    public void testLearn() throws Exception {
    	ILits voc = new Lits();
    	IVecInt ps = new VecInt();
    	ps.push(67);
        ps.push(70);
    	IConstr arg = new AtLeast(voc, ps, 0);
    	tracing.learn(arg);
    	
    	Map<Integer, Integer> mapping = (Map<Integer, Integer>) getField(tracing, "mapping");
    	assertEquals(1, mapping.size());
    }
    
    public void testDelete() throws Exception {
    	int[] clause = new int[1];
    	clause[0] = 8;
    	tracing.delete(clause);
    	
    	Map<Integer, Integer> mapping = (Map<Integer, Integer>) getField(tracing, "mapping");
    	assertEquals(1, mapping.size());
    }
    
    public void testConflictFoundExample1() throws Exception {
    	ILits voc = new Lits();
    	IVecInt ps = new VecInt();
    	ps.push(67);
        ps.push(70);
    	IConstr arg = new AtLeast(voc, ps, 0);
    	tracing.conflictFound(arg, 0, 0);
    	
    	Map<Integer, Integer> mapping = (Map<Integer, Integer>) getField(tracing, "mapping");
    	assertEquals(1, mapping.size());
    }
    
    public void testConflictFoundExample2() throws Exception {
    	tracing.conflictFound(-2);
    	
    	Map<Integer, Integer> mapping = (Map<Integer, Integer>) getField(tracing, "mapping");
    	assertEquals(1, mapping.size());
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
    	
    	Map<Integer, Integer> mapping = (Map<Integer, Integer>) getField(tracing, "mapping");
    	assertEquals(1, mapping.size());
    }
    
    public void testStart() throws Exception {
    	tracing.start();
    	
    	Map<Integer, Integer> mapping = (Map<Integer, Integer>) getField(tracing, "mapping");
    	assertEquals(1, mapping.size());
    }
    
    public void testEnd() throws Exception {
    	tracing.end(Lbool.TRUE);
    	
    	Map<Integer, Integer> mapping = (Map<Integer, Integer>) getField(tracing, "mapping");
    	assertEquals(1, mapping.size());
    }
    
    public void testRestarting() throws Exception {
    	tracing.restarting();
    	
    	Map<Integer, Integer> mapping = (Map<Integer, Integer>) getField(tracing, "mapping");
    	assertEquals(1, mapping.size());
    }
    
    public void testBackjump() throws Exception {
    	tracing.backjump(0);
    	
    	Map<Integer, Integer> mapping = (Map<Integer, Integer>) getField(tracing, "mapping");
    	assertEquals(1, mapping.size());
    }
    
    public void testCleaning() throws Exception {
    	tracing.cleaning();
    	
    	Map<Integer, Integer> mapping = (Map<Integer, Integer>) getField(tracing, "mapping");
    	assertEquals(1, mapping.size());
    }
    
    public void testInit() throws Exception {
    	ISolverService sol = (ISolverService) SolverFactory.newDefault();
    	tracing.init(sol);
    	
    	Map<Integer, Integer> mapping = (Map<Integer, Integer>) getField(tracing, "mapping");
    	assertEquals(1, mapping.size());
    }
}
