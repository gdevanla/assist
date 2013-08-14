package org.sat4j.tools.encoding;

import java.lang.reflect.Method;

import junit.framework.TestCase;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.IConstr;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.IVecInt;

public class PolicyTest extends TestCase{
	private Policy policy;
	
	 public PolicyTest(String arg0) {
	        super(arg0);
	    }

	    /*
	     * @see TestCase#setUp()
	     */
	    @Override
	    protected void setUp() throws Exception {
	        this.policy = new Policy();
	    }

	    /*
	     * @see TestCase#tearDown()
	     */
	    @Override
	    protected void tearDown() throws Exception {
	        super.tearDown();
	    }
	    
	    private Object executeMethod( Object instance, String name, Object[] params ) throws Exception
		{
			Class[] types 	= new Class[params.length];
			for ( int i = 0; i < params.length; i++ )
				types[i] = params[i].getClass();

			Method m        = instance.getClass().getDeclaredMethod( name, types );
			
			m.setAccessible( true );

			return m.invoke( instance, params );
		}
	    
	    public void testGetAdapterFromEncodingNameExample1() throws Exception {
	    	Object[] params = new Object[1];
	    	params[0] = EncodingStrategy.BINOMIAL;

			EncodingStrategyAdapter strategyAdapter = (EncodingStrategyAdapter) executeMethod(policy, "getAdapterFromEncodingName", params);
			
			assertEquals(Binomial.class, strategyAdapter.getClass());
	    }
	    
	    public void testGetAdapterFromEncodingNameExample2() throws Exception {
	    	Object[] params = new Object[1];
	    	params[0] = EncodingStrategy.BINARY;

			EncodingStrategyAdapter strategyAdapter = (EncodingStrategyAdapter) executeMethod(policy, "getAdapterFromEncodingName", params);
			
			assertEquals(Binary.class, strategyAdapter.getClass());
	    }
	    
	    public void testGetAdapterFromEncodingNameExample3() throws Exception {
	    	Object[] params = new Object[1];
	    	params[0] = EncodingStrategy.COMMANDER;

			EncodingStrategyAdapter strategyAdapter = (EncodingStrategyAdapter) executeMethod(policy, "getAdapterFromEncodingName", params);
			
			assertEquals(Commander.class, strategyAdapter.getClass());
	    }

	    public void testGetAdapterFromEncodingNameExample4() throws Exception {
	    	Object[] params = new Object[1];
	    	params[0] = EncodingStrategy.LADDER;

			EncodingStrategyAdapter strategyAdapter = (EncodingStrategyAdapter) executeMethod(policy, "getAdapterFromEncodingName", params);
			
			assertEquals(Ladder.class, strategyAdapter.getClass());
	    }
	    
	    public void testGetAdapterFromEncodingNameExample5() throws Exception {
	    	Object[] params = new Object[1];
	    	params[0] = EncodingStrategy.PRODUCT;

			EncodingStrategyAdapter strategyAdapter = (EncodingStrategyAdapter) executeMethod(policy, "getAdapterFromEncodingName", params);
			
			assertEquals(Product.class, strategyAdapter.getClass());
	    }
	    
	    public void testGetAdapterFromEncodingNameExample6() throws Exception {
	    	Object[] params = new Object[1];
	    	params[0] = EncodingStrategy.SEQUENTIAL;

			EncodingStrategyAdapter strategyAdapter = (EncodingStrategyAdapter) executeMethod(policy, "getAdapterFromEncodingName", params);
			
			assertEquals(Sequential.class, strategyAdapter.getClass());
	    }
	    
	    public void testGetAdapterFromEncodingNameExample7() throws Exception {
	    	Object[] params = new Object[1];
	    	params[0] = EncodingStrategy.NATIVE;

			EncodingStrategyAdapter strategyAdapter = (EncodingStrategyAdapter) executeMethod(policy, "getAdapterFromEncodingName", params);
			
			assertNull(strategyAdapter);
	    }
	    
	    public void testGetEncodingTypeFromAdapterExample1() throws Exception {
	    	Binary binary = new Binary();
	    	
	    	Object[] params = new Object[1];
	    	params[0] = binary;

	    	Class[] types 	= new Class[params.length];
			types[0] = EncodingStrategyAdapter.class;
			
			Method m = policy.getClass().getDeclaredMethod( "getEncodingTypeFromAdapter", types );
			m.setAccessible( true );
			EncodingStrategy strategy = (EncodingStrategy) m.invoke( policy, params );
			
			assertEquals(EncodingStrategy.BINARY, strategy);
	    }
	    
	    public void testGetEncodingTypeFromAdapterExample2() throws Exception {
	    	Binomial binomial = new Binomial();
	    	
	    	Object[] params = new Object[1];
	    	params[0] = binomial;

	    	Class[] types 	= new Class[params.length];
			types[0] = EncodingStrategyAdapter.class;
			
			Method m = policy.getClass().getDeclaredMethod( "getEncodingTypeFromAdapter", types );
			m.setAccessible( true );
			EncodingStrategy strategy = (EncodingStrategy) m.invoke( policy, params );;
			
			assertEquals(EncodingStrategy.BINOMIAL, strategy);
	    }
	    	    
	    public void testGetEncodingTypeFromAdapterExample3() throws Exception {
	    	Ladder ladder = new Ladder();
	    	
	    	Object[] params = new Object[1];
	    	params[0] = ladder;

	    	Class[] types 	= new Class[params.length];
			types[0] = EncodingStrategyAdapter.class;
			
			Method m = policy.getClass().getDeclaredMethod( "getEncodingTypeFromAdapter", types );
			m.setAccessible( true );
			EncodingStrategy strategy = (EncodingStrategy) m.invoke( policy, params );;
			
			assertEquals(EncodingStrategy.LADDER, strategy);
	    }
	    	    
	    public void testGetEncodingTypeFromAdapterExample4() throws Exception {
	    	Commander commander = new Commander();
	    	
	    	Object[] params = new Object[1];
	    	params[0] = commander;

	    	Class[] types 	= new Class[params.length];
			types[0] = EncodingStrategyAdapter.class;
			
			Method m = policy.getClass().getDeclaredMethod( "getEncodingTypeFromAdapter", types );
			m.setAccessible( true );
			EncodingStrategy strategy = (EncodingStrategy) m.invoke( policy, params );;
			
			assertEquals(EncodingStrategy.COMMANDER, strategy);
	    }
	    
	    public void testGetEncodingTypeFromAdapterExample5() throws Exception {
	    	Product product = new Product();
	    	
	    	Object[] params = new Object[1];
	    	params[0] = product;

	    	Class[] types 	= new Class[params.length];
			types[0] = EncodingStrategyAdapter.class;
			
			Method m = policy.getClass().getDeclaredMethod( "getEncodingTypeFromAdapter", types );
			m.setAccessible( true );
			EncodingStrategy strategy = (EncodingStrategy) m.invoke( policy, params );;
			
			assertEquals(EncodingStrategy.PRODUCT, strategy);
	    }
	    
	    public void testGetEncodingTypeFromAdapterExample6() throws Exception {
	    	Sequential sequential = new Sequential();
	    	
	    	Object[] params = new Object[1];
	    	params[0] = sequential;

	    	Class[] types 	= new Class[params.length];
			types[0] = EncodingStrategyAdapter.class;
			
			Method m = policy.getClass().getDeclaredMethod( "getEncodingTypeFromAdapter", types );
			m.setAccessible( true );
			EncodingStrategy strategy = (EncodingStrategy) m.invoke( policy, params );;
			
			assertEquals(EncodingStrategy.SEQUENTIAL, strategy);
	    }
	    
	    public void testGetEncodingTypeFromAdapterExample7() throws Exception {
	    	EncodingStrategyAdapter strategyAdapter = null;
	    	Object[] params = new Object[1];
	    	params[0] = strategyAdapter;

	    	Class[] types 	= new Class[params.length];
			types[0] = EncodingStrategyAdapter.class;
			
			Method m = policy.getClass().getDeclaredMethod( "getEncodingTypeFromAdapter", types );
			m.setAccessible( true );
			EncodingStrategy strategy = (EncodingStrategy) m.invoke( policy, params );;
			
			assertEquals(EncodingStrategy.NATIVE, strategy);
	    }
	    
	    public void testGetAtMostOneEncodingExample1() throws Exception {
	    	Object[] params = new Object[1];
	    	params[0] = EncodingStrategy.BINOMIAL;

			EncodingStrategyAdapter strategyAdapter = (EncodingStrategyAdapter) executeMethod(policy, "getAdapterFromEncodingName", params);
			
			policy.setAtMostOneEncoding(strategyAdapter);
			assertEquals(strategyAdapter, policy.getAtMostOneEncoding());
	    }
	    
	    public void testGetAtMostOneEncodingExample2() throws Exception {
	    	Object[] params = new Object[1];
	    	params[0] = EncodingStrategy.BINOMIAL;

			EncodingStrategyAdapter strategyAdapter = (EncodingStrategyAdapter) executeMethod(policy, "getAdapterFromEncodingName", params);
	    	
			policy.setAtMostOneEncoding(EncodingStrategy.BINOMIAL);
			assertEquals(strategyAdapter, policy.getAtMostOneEncoding());
	    }
	    
	    public void testSetAtMostKEncodingExample1() throws Exception {
	    	Object[] params = new Object[1];
	    	params[0] = EncodingStrategy.BINOMIAL;

			EncodingStrategyAdapter strategyAdapter = (EncodingStrategyAdapter) executeMethod(policy, "getAdapterFromEncodingName", params);
			
			policy.setAtMostKEncoding(strategyAdapter);
			assertEquals(strategyAdapter, policy.getAtMostKEncoding());
	    }
	    
	    public void testSetAtMostKEncodingExample2() throws Exception {
	    	Object[] params = new Object[1];
	    	params[0] = EncodingStrategy.BINOMIAL;

			EncodingStrategyAdapter strategyAdapter = (EncodingStrategyAdapter) executeMethod(policy, "getAdapterFromEncodingName", params);
	    	
			policy.setAtMostKEncoding(EncodingStrategy.BINOMIAL);
			assertEquals(strategyAdapter, policy.getAtMostKEncoding());
	    }
	    
	    public void testGetExactlyOneEncodingExample1() throws Exception {
	    	Object[] params = new Object[1];
	    	params[0] = EncodingStrategy.BINOMIAL;

			EncodingStrategyAdapter strategyAdapter = (EncodingStrategyAdapter) executeMethod(policy, "getAdapterFromEncodingName", params);
			
			policy.setExactlyOneEncoding(strategyAdapter);
			assertEquals(strategyAdapter, policy.getExactlyOneEncoding());
	    }
	    
	    public void testGetExactlyOneEncodingExample2() throws Exception {
	    	Object[] params = new Object[1];
	    	params[0] = EncodingStrategy.BINOMIAL;

			EncodingStrategyAdapter strategyAdapter = (EncodingStrategyAdapter) executeMethod(policy, "getAdapterFromEncodingName", params);
	    	
			policy.setExactlyOneEncoding(EncodingStrategy.BINOMIAL);
			assertEquals(strategyAdapter, policy.getExactlyOneEncoding());
	    }
	    
	    public void testGetExactlyKEncodingExample1() throws Exception {
	    	Object[] params = new Object[1];
	    	params[0] = EncodingStrategy.BINOMIAL;

			EncodingStrategyAdapter strategyAdapter = (EncodingStrategyAdapter) executeMethod(policy, "getAdapterFromEncodingName", params);
			
			policy.setExactlyKEncoding(strategyAdapter);
			assertEquals(strategyAdapter, policy.getExactlyKEncoding());
	    }
	    
	    public void testGetExactlyKEncodingExample2() throws Exception {
	    	Object[] params = new Object[1];
	    	params[0] = EncodingStrategy.BINOMIAL;

			EncodingStrategyAdapter strategyAdapter = (EncodingStrategyAdapter) executeMethod(policy, "getAdapterFromEncodingName", params);
	    	
			policy.setExactlyKEncoding(EncodingStrategy.BINOMIAL);
			assertEquals(strategyAdapter, policy.getExactlyKEncoding());
	    }
	    
	    public void testGetAtLeastOneEncodingExample1() throws Exception {
	    	Object[] params = new Object[1];
	    	params[0] = EncodingStrategy.BINOMIAL;

			EncodingStrategyAdapter strategyAdapter = (EncodingStrategyAdapter) executeMethod(policy, "getAdapterFromEncodingName", params);
			
			policy.setAtLeastOneEncoding(strategyAdapter);
			assertEquals(strategyAdapter, policy.getAtLeastOneEncoding());
	    }
	    
	    public void testGetAtLeastOneEncodingExample2() throws Exception {
	    	Object[] params = new Object[1];
	    	params[0] = EncodingStrategy.BINOMIAL;

			EncodingStrategyAdapter strategyAdapter = (EncodingStrategyAdapter) executeMethod(policy, "getAdapterFromEncodingName", params);
	    	
			policy.setAtLeastOneEncoding(EncodingStrategy.BINOMIAL);
			assertEquals(strategyAdapter, policy.getAtLeastOneEncoding());
	    }
	    
	    
	    public void testGetAtLeastKEncodingExample1() throws Exception {
	    	Object[] params = new Object[1];
	    	params[0] = EncodingStrategy.BINOMIAL;

			EncodingStrategyAdapter strategyAdapter = (EncodingStrategyAdapter) executeMethod(policy, "getAdapterFromEncodingName", params);
			
			policy.setAtLeastKEncoding(strategyAdapter);
			assertEquals(strategyAdapter, policy.getAtLeastKEncoding());
	    }
	    
	    public void testGetAtLeastKEncodingExample2() throws Exception {
	    	Object[] params = new Object[1];
	    	params[0] = EncodingStrategy.BINOMIAL;

			EncodingStrategyAdapter strategyAdapter = (EncodingStrategyAdapter) executeMethod(policy, "getAdapterFromEncodingName", params);
	    	
			policy.setAtLeastKEncoding(EncodingStrategy.BINOMIAL);
			assertEquals(strategyAdapter, policy.getAtLeastKEncoding());
	    }
	    
	    public void testAddAtMostExample1() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	
	    	for(int i=0;i<100;i++)
	    		literals.push(i+1);
	    	try {
	    		policy.addAtMost(solver, literals, 0);
	    	} catch(Exception e) {
	    		
	    	}
	    	
	    }
	    
	    public void testAddAtMostExample2() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	literals.push(5);
	    	
	    	try {
	    		policy.addAtMost(solver, literals, 0);
	    	} catch(Exception e) {
	    		
	    	}
	    }
	    
	    public void testAddAtMostExample3() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<100;i++)
	    		literals.push(8);
	    	
	    	try {
	    		policy.addAtMost(solver, literals, 2);
	    	} catch(Exception e) {
	    		
	    	}
	    }
	    
	    public void testAddAtMostExample4() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	literals.push(6);
	    	
	    	try {
	    		policy.addAtMost(solver, literals, 2);
	    	} catch(Exception e) {
	    		
	    	}
	    }
	    
	    public void testAddAtMostExample5() throws Exception {
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	
	    	try {
	    		policy.addAtMost(solver, literals, 2);
	    	} catch(Exception e) {
	    		assertEquals("requires at least 2 literals",e.getMessage());
	    	}
	    }
	    
	    public void testAddAtMostExample6() throws Exception {
	    	EncodingStrategyAdapter adapter = null;
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<100;i++)
	    		literals.push(8);
	    	policy.setAtMostOneEncoding(adapter);
	    	
	    	try {
	    		policy.addAtMost(solver, literals, 1);
	    	} catch(Exception e) {
	    		
	    	}
	    }
	    
	    public void testAddAtMostExample7() throws Exception {
	    	EncodingStrategyAdapter adapter = new Binary();
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<100;i++)
	    		literals.push(8);
	    	policy.setAtMostOneEncoding(adapter);
	    	
	    	try {
	    		policy.addAtMost(solver, literals, 1);
	    	} catch(Exception e) {
	    		
	    	}
	    }
	    
	    public void testAddAtMostExample8() throws Exception {
	    	EncodingStrategyAdapter adapter = null;
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<100;i++)
	    		literals.push(8);
	    	
	    	policy.setAtMostOneEncoding(adapter);
	    	
	    	try {
	    		policy.addAtMost(solver, literals, 8);
	    	} catch(Exception e) {
	    		
	    	}
	    }
	    
	    public void testAddAtMostExample9() throws Exception {
	    	EncodingStrategyAdapter adapter = new Binary();
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<100;i++)
	    		literals.push(8);
	    	
	    	policy.setAtMostOneEncoding(adapter);
	    	
	    	try {
	    		policy.addAtMost(solver, literals, 8);
	    	} catch(Exception e) {
	    		
	    	}
	    }
	    
	    public void testAddAtMostExample10() throws Exception {
	    	EncodingStrategyAdapter adapter = null;
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<100;i++)
	    		literals.push(8);
	    	
	    	policy.setAtMostKEncoding(adapter);
	    	
	    	try {
	    		policy.addAtMost(solver, literals, 8);
	    	} catch(Exception e) {
	    		
	    	}
	    }
	    
	    public void testAddExactlyExample1() throws Exception {
	    	EncodingStrategyAdapter adapter = new Binary();
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<100;i++)
	    		literals.push(i+1);
	    	policy.setExactlyOneEncoding(adapter);
	   
	    	IConstr expected = policy.addExactly(solver, literals, 1);
	    	assertEquals(2, expected.size());
	    }
	    
	    public void testAddExactlyExample2() throws Exception {
	    	EncodingStrategyAdapter adapter = new Binary();
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<100;i++)
	    		literals.push(i+1);
	    	policy.setExactlyOneEncoding(adapter);
	   
	    	IConstr expected = policy.addExactly(solver, literals, 2);
	    	assertEquals(2, expected.size());
	    }
	    
	    public void testAddExactlyExample3() throws Exception {
	    	EncodingStrategyAdapter adapter = null;
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<100;i++)
	    		literals.push(i+1);
	    	policy.setExactlyOneEncoding(adapter);
	   
	    	IConstr expected = policy.addExactly(solver, literals, 1);
	    	assertEquals(2, expected.size());
	    }
	    
	    public void testAddExactlyExample4() throws Exception {
	    	EncodingStrategyAdapter adapter = null;
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<100;i++)
	    		literals.push(i+1);
	    	policy.setExactlyOneEncoding(adapter);
	    	policy.setExactlyKEncoding(adapter);
	   
	    	IConstr expected = policy.addExactly(solver, literals, 1);
	    	assertEquals(2, expected.size());
	    }
	    
	    public void testAddAtLeastExample1() throws Exception {
	    	EncodingStrategyAdapter adapter = new Binary();
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<100;i++)
	    		literals.push(i+1);
	    	policy.setAtLeastOneEncoding(adapter);
	   
	    	IConstr expected = policy.addAtLeast(solver, literals, 1);
	    	assertEquals(100, expected.size());
	    }
	    
	    public void testAddAtLeastExample2() throws Exception {
	    	EncodingStrategyAdapter adapter = new Binary();
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<100;i++)
	    		literals.push(i+1);
	    	policy.setAtLeastOneEncoding(adapter);
	   
	    	IConstr expected = policy.addAtLeast(solver, literals, 2);
	    	assertEquals(19405, expected.size());
	    }
	    
	    public void testAddAtLeastExample3() throws Exception {
	    	EncodingStrategyAdapter adapter = null;
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<100;i++)
	    		literals.push(i+1);
	    	policy.setAtLeastOneEncoding(adapter);
	   
	    	IConstr expected = policy.addAtLeast(solver, literals, 1);
	    	assertEquals(19602, expected.size());
	    }
	    
	    public void testAddAtLeastExample4() throws Exception {
	    	EncodingStrategyAdapter adapter = null;
	    	ISolver solver = SolverFactory.newDefault();
	    	IVecInt literals = new VecInt();
	    	for(int i=0;i<100;i++)
	    		literals.push(i+1);
	    	policy.setAtLeastOneEncoding(adapter);
	    	policy.setAtLeastKEncoding(adapter);
	   
	    	IConstr expected = policy.addAtLeast(solver, literals, 8);
	    	assertEquals(18223, expected.size());
	    }
	    
	    public void testToString() throws Exception {
	    	EncodingStrategyAdapter adapter = new Binary();
	    	policy.setAtMostOneEncoding(adapter);
	    	
	    	assertEquals("Policy = [At most K: Sequential, at most 1: Binary, exactly K: Sequential, exactly 1: Ladder]", policy.toString());
	    }
	    
}
