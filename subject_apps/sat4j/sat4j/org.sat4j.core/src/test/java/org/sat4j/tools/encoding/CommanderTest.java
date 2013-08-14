package org.sat4j.tools.encoding;

import java.lang.reflect.Method;

import junit.framework.TestCase;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.IConstr;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.IVecInt;

public class CommanderTest extends TestCase{
	private Commander commander;
	
	 public CommanderTest(String arg0) {
	        super(arg0);
	    }

	    /*
	     * @see TestCase#setUp()
	     */
	    @Override
	    protected void setUp() throws Exception {
	        this.commander = new Commander();
	    }

	    /*
	     * @see TestCase#tearDown()
	     */
	    @Override
	    protected void tearDown() throws Exception {
	        super.tearDown();
	    }
	    
	    public void testAddAtMostOneExample1() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<100;i++)
	    		literals.push(i+1);
	    	
	    	IConstr expected = commander.addAtMostOne(solver, literals);
	    	assertEquals(234, expected.size());
	    }
	   
	    public void testAddAtMostOneExample2() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	literals.push(1);
	    	literals.push(2);
	    	
	    	IConstr expected = commander.addAtMostOne(solver, literals);
	    	assertEquals(1, expected.size());
	    }

	    public void testAddAtMostExample1() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<100;i++)
	    		literals.push(i+1);
	    	
	    	IConstr expected = commander.addAtMost(solver, literals, 2);
	    	assertEquals(100, expected.size());
	    }
	    
	    public void testAddAtMostExample2() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<50;i++)
	    		literals.push(i+1);
	    	
	    	IConstr expected = commander.addAtMost(solver, literals, 1);
	    	assertEquals(50, expected.size());
	    }
	    
	    
	    public void testAddAtMostExample3() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<10;i++)
	    		literals.push(i+1);

	    	Object[] params = new Object[4];
	    	params[0] = solver;
	    	params[1] = literals;
	    	params[2] = 1;
	    	params[3] = 20;

			Class[] types 	= new Class[params.length];

			types[0] = ISolver.class;
			types[1] = IVecInt.class;
			types[2] = int.class;
			types[3] = int.class;

			Method m        = commander.getClass().getDeclaredMethod( "addAtMost", types );

			m.setAccessible( true );

			IConstr expected = (IConstr) m.invoke( commander, params );
		
	    	assertEquals(45, expected.size());
	    }
	    
	    
	    public void testAddAtMostExample4() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<10;i++)
	    		literals.push(i+1);

	    	Object[] params = new Object[4];
	    	params[0] = solver;
	    	params[1] = literals;
	    	params[2] = 0;
	    	params[3] = 1;

			Class[] types 	= new Class[params.length];

			types[0] = ISolver.class;
			types[1] = IVecInt.class;
			types[2] = int.class;
			types[3] = int.class;

			Method m        = commander.getClass().getDeclaredMethod( "addAtMost", types );

			m.setAccessible( true );

			IConstr expected = (IConstr) m.invoke( commander, params );
		
	    	assertEquals(21, expected.size());
	    }

}
