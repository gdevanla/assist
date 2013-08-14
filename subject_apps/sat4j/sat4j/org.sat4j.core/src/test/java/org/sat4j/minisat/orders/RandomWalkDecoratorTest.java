package org.sat4j.minisat.orders;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;

import org.sat4j.minisat.constraints.cnf.Lits;
import org.sat4j.minisat.core.ILits;
import org.sat4j.minisat.core.IPhaseSelectionStrategy;

import junit.framework.TestCase;

public class RandomWalkDecoratorTest extends TestCase{
	private RandomWalkDecorator decorator;
	private final VarOrderHeap order = new VarOrderHeap();
	
	
	public RandomWalkDecoratorTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
    	ILits literals = new Lits();
    	literals.ensurePool(200);
    	order.setLits(literals);
    	order.init();
    	for(int i=0;i<10;i++)
    		order.heap.insert(0);
        this.decorator = new RandomWalkDecorator(order);
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testAssignLiteral() throws Exception {
    	decorator.assignLiteral(4);
    	
    	Field f = decorator.getClass().getDeclaredField( "decorated" );
		f.setAccessible( true );
		VarOrderHeap voc = (VarOrderHeap)f.get( decorator );
		
		assertEquals(10, voc.heap.size());
    }
    
    public void testProbability() throws Exception {
    	assertEquals(0.01, decorator.getProbability());
    	decorator.setProbability(0.5);
    	assertEquals(0.5, decorator.getProbability());
    }
    
    public void testPhaseSelectionStrategy() throws Exception {
    	IPhaseSelectionStrategy strategy = decorator.getPhaseSelectionStrategy();
    	assertEquals(PhaseInLastLearnedClauseSelectionStrategy.class, strategy.getClass());
    	
    	decorator.setPhaseSelectionStrategy(new PositiveLiteralSelectionStrategy());
    	assertEquals(PhaseInLastLearnedClauseSelectionStrategy.class, strategy.getClass());
    }
    
    public void testInit() throws Exception {
    	decorator.init();
    	
    	Field f = decorator.getClass().getDeclaredField( "decorated" );
		f.setAccessible( true );
		VarOrderHeap voc = (VarOrderHeap)f.get( decorator );
		
		assertEquals(0, voc.heap.size());
    }
    
    public void testPrintStat() throws Exception {
    	StringWriter stwr = new StringWriter();
    	PrintWriter out = new PrintWriter(stwr);
    	decorator.printStat(out, "sample");
    	
    	assertTrue(stwr.toString().contains("sample"));
    	
    }
    
    public void testSelectExample1() throws Exception {
    	decorator.init();
    	assertEquals(-1, decorator.select());
    }
    
    public void testSelectExample2() throws Exception {
    	RandomWalkDecorator dec = new RandomWalkDecorator(order, 1.0);
    	dec.init();
    	assertEquals(-1, dec.select());   	
    }

    public void testSelectExample3() throws Exception {
    	decorator.init();
    	ILits literals = new Lits();
    	decorator.setLits(literals);
    	
    	for(int i=0;i<50;i++)
    		decorator.undo(i);
    	decorator.setProbability(1.0);

    	assertEquals(65, decorator.select()); 
    }
    
    public void testLits() throws Exception {
    	ILits literals = new Lits();
    	decorator.setLits(literals);

		Field f = decorator.getClass().getDeclaredField( "voc" );
		f.setAccessible( true );
		ILits voc = (ILits)f.get( decorator );
		
		assertEquals(voc, literals);
    }
    
    public void testVarDecay() throws Exception {
    	decorator.setVarDecay(0.8);
    	
    	Field f = decorator.getClass().getDeclaredField( "decorated" );
		f.setAccessible( true );
		VarOrderHeap voc = (VarOrderHeap)f.get( decorator );
		
		Field field = voc.getClass().getDeclaredField( "varDecay" );
		field.setAccessible( true );
		double varDecay = (Double)field.get( voc );
		
		assertEquals(0.8, varDecay);
    }
    
    public void testUndo() throws Exception {
    	decorator.init();
    	decorator.undo(1);

    	Field f = decorator.getClass().getDeclaredField( "decorated" );
		f.setAccessible( true );
		VarOrderHeap voc = (VarOrderHeap)f.get( decorator );
		
		assertEquals(1, voc.heap.size());
    }
    
    public void testUpdateVar() throws Exception {
    	decorator.init();
    	decorator.updateVar(50);
    	
    	Field f = decorator.getClass().getDeclaredField( "decorated" );
		f.setAccessible( true );
		VarOrderHeap voc = (VarOrderHeap)f.get( decorator );
		
		assertEquals(0, voc.heap.size());
    }
    
    public void testUpdateVarAtDecisionLevel() throws Exception {
    	decorator.init();
    	decorator.updateVarAtDecisionLevel(50);
    	
    	Field f = decorator.getClass().getDeclaredField( "decorated" );
		f.setAccessible( true );
		VarOrderHeap voc = (VarOrderHeap)f.get( decorator );
		
		assertEquals(0, voc.heap.size());
    }
    
    public void testVarActivity() throws Exception {
    	decorator.varActivity(2);
    	
    	Field f = decorator.getClass().getDeclaredField( "decorated" );
		f.setAccessible( true );
		VarOrderHeap voc = (VarOrderHeap)f.get( decorator );
		
		assertEquals(201, voc.activity.length);
    }
    
    public void testVarDecayActivity() throws Exception {
    	decorator.varDecayActivity();
    	
    	Field f = decorator.getClass().getDeclaredField( "decorated" );
		f.setAccessible( true );
		VarOrderHeap voc = (VarOrderHeap)f.get( decorator );
		
		Field field = voc.getClass().getDeclaredField( "varInc" );
		field.setAccessible( true );
		double varInc = (Double)field.get( voc );
		
		assertEquals(1.0, varInc);
    }
    
    public void testToString() throws Exception {
    	assertTrue(decorator.toString().contains("with random walks"));
    }
    
    public void testVariableHeuristics() throws Exception {
    	double[] vars = decorator.getVariableHeuristics();
    	assertEquals(201, vars.length);
    }
}
