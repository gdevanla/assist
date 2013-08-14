package org.sat4j.minisat.orders;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.sat4j.minisat.constraints.cnf.Lits;
import org.sat4j.minisat.core.ILits;
import org.sat4j.minisat.core.IPhaseSelectionStrategy;

import junit.framework.TestCase;

public class VarOrderHeapTest extends TestCase{
	private VarOrderHeap order;
	private ILits literals;

	public VarOrderHeapTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
    	this.order = new VarOrderHeap();
    	
    	this.literals = new Lits();
    	literals.ensurePool(200);
    	
    	this.order.setLits(literals);
    	this.order.init();
    	for(int i=0;i<10;i++)
    		this.order.heap.insert(0);
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    public void testVoc() throws Exception {
    	assertEquals(literals, order.getVocabulary());
    }
    public void testAssignLiteral() throws Exception {
    	
    	order.assignLiteral(4);
		
		assertEquals(10, order.heap.size());
    }
    
    public void testPhaseSelectionStrategy() throws Exception {
    	IPhaseSelectionStrategy strategy = order.getPhaseSelectionStrategy();
    	assertEquals(PhaseInLastLearnedClauseSelectionStrategy.class, strategy.getClass());
    	
    	order.setPhaseSelectionStrategy(new PositiveLiteralSelectionStrategy());
    	assertEquals(PhaseInLastLearnedClauseSelectionStrategy.class, strategy.getClass());
    }
    
    public void testInit() throws Exception {
    	order.init();
		
		assertEquals(0, order.heap.size());
    }
    
    public void testPrintStat() throws Exception {
    	StringWriter stwr = new StringWriter();
    	PrintWriter out = new PrintWriter(stwr);
    	order.printStat(out, "sample");
    	
    	assertTrue(stwr.toString().contains("sample"));
    	
    }
    
    public void testSelectExample1() throws Exception {
    	order.init();
    	assertEquals(-1, order.select());
    }
    
    public void testSelectExample2() throws Exception {
    	RandomWalkDecorator dec = new RandomWalkDecorator(order, 1.0);
    	dec.init();
    	assertEquals(-1, dec.select());   	
    }

    public void testSelectExample3() throws Exception {
    	order.init();
    	ILits literals = new Lits();
    	order.setLits(literals);
    	
    	for(int i=0;i<50;i++)
    		order.undo(i);

    	assertEquals(3, order.select()); 
    }
    
    public void testLits() throws Exception {
    	ILits literals = new Lits();
    	order.setLits(literals);
		
		assertEquals(order.lits, literals);
    }
    
    public void testVarDecay() throws Exception {
    	order.setVarDecay(0.8);
		
		Field field = order.getClass().getDeclaredField( "varDecay" );
		field.setAccessible( true );
		double varDecay = (Double)field.get( order );
		
		assertEquals(0.8, varDecay);
    }
    
    public void testUndoExample1() throws Exception {
    	order.init();
    	order.undo(1);
    	order.undo(1);
		assertEquals(1, order.heap.size());
    }
    
    public void testUndoExample2() throws Exception {
    	order.init();
    	order.undo(1);
		
		assertEquals(1, order.heap.size());
    }
    
    public void testUpdateVarExample1() throws Exception {
    	order.init();
    	order.updateVar(50);
		
		assertEquals(0, order.heap.size());
    }
    
    public void testUpdateVarExample2() throws Exception {
    	order.init();
    	order.undo(1);
    	order.updateVar(2);
		
		assertEquals(1, order.heap.size());
    }
    
    public void testVarRescaleActivity() throws Exception {
    	Method m        = order.getClass().getDeclaredMethod( "varRescaleActivity" );
    	m.setAccessible( true );
    	m.invoke( order);
    	
    	Field f = order.getClass().getDeclaredField( "varInc" );
		f.setAccessible( true );
		double varInc = (Double)f.get( order );
		
		assertEquals(1.0E-100, varInc);
	
    }
    public void testUpdateVarAtDecisionLevel() throws Exception {
    	order.init();
    	order.updateVarAtDecisionLevel(50);
		
		assertEquals(0, order.heap.size());
    }
    
    public void testVarActivity() throws Exception {
    	order.varActivity(2);
		
		assertEquals(201, order.activity.length);
    }
    
    public void testNumberOfInterestingVariablesExample1() throws Exception {
    	assertEquals(0, order.numberOfInterestingVariables());
    }
    
    public void testNumberOfInterestingVariablesExample2() throws Exception {
    	order.activity = new double[5];
    	for(int i=0;i<5;i++)
    		order.activity[i] = i;
    	assertEquals(3, order.numberOfInterestingVariables());
    }
    
    public void testVarDecayActivity() throws Exception {
    	order.varDecayActivity();
		
		Field field = order.getClass().getDeclaredField( "varInc" );
		field.setAccessible( true );
		double varInc = (Double)field.get( order );
		
		assertEquals(1.0, varInc);
    }
    
    public void testToString() throws Exception {
    	assertTrue(order.toString().contains("VSIDS like heuristics from MiniSAT using a heap"));
    }
    
    public void testVariableHeuristics() throws Exception {
    	double[] vars = order.getVariableHeuristics();
    	assertEquals(201, vars.length);
    }
    
    public void testInitExample1() throws Exception {
    	order.init();
    
    	assertEquals(0, order.heap.size());
    }
    
    public void testInitExample2() throws Exception {
    	order.activity = new double[0];
    	order.init();
    	assertEquals(0, order.heap.size());
    }
    
    public void testInitExample3() throws Exception {
    	order.activity = new double[5];
    	order.activity = null;
    	order.init();
    	assertEquals(0, order.heap.size());
    }
    
    public void testInitExample4() throws Exception {
    	order.activity = new double[5];
    	for(int i=0;i<5;i++)
    		order.activity[i] = i;
    	order.init();
    	assertEquals(0, order.heap.size());
    }
    
    public void testInitExample5() throws Exception {
    	order.activity = new double[5];
    	for(int i=0;i<5;i++)
    		order.activity[i] = i;
    	
    	order.lits.getFromPool(1);
    	order.init();
    	
    	assertEquals(1, order.heap.size());
    }
}
