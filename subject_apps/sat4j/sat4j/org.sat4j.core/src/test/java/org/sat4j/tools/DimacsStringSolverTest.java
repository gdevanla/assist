package org.sat4j.tools;

import java.io.PrintWriter;
import java.io.StringWriter;

import junit.framework.TestCase;

import org.sat4j.core.Vec;
import org.sat4j.core.VecInt;
import org.sat4j.specs.IConstr;
import org.sat4j.specs.IVec;
import org.sat4j.specs.IVecInt;

public class DimacsStringSolverTest extends TestCase{
	private DimacsStringSolver dimacsStringSolver;
    
    public DimacsStringSolverTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.dimacsStringSolver = new DimacsStringSolver();
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testNewVarExample1() throws Exception {
    	DimacsOutputSolver solver = new DimacsOutputSolver();
    	assertEquals(0,dimacsStringSolver.newVar());
    	assertEquals(0, solver.newVar());
    }
    
    public void testNewVarExample2() throws Exception {
    	assertEquals(5,dimacsStringSolver.newVar(5));
    }
    
    public void testSetExpectedNumberOfClauses() throws Exception {
    	dimacsStringSolver.setExpectedNumberOfClauses(5);
    	assertTrue(dimacsStringSolver.fixedNbClauses);
    	assertEquals(5, dimacsStringSolver.nbclauses);
    }
    
    public void testAddClauseExample1() throws Exception {
    	IVecInt literals = new VecInt();
    	IConstr expected = dimacsStringSolver.addClause(literals);
    	
    	assertNull(expected);
    }
    
    public void testAddClauseExample2() throws Exception {
    	dimacsStringSolver.firstConstr = false;
    	IVecInt literals = new VecInt();
    	IConstr expected = dimacsStringSolver.addClause(literals);
    	
    	assertNull(expected);
    }
    
    public void testAddClauseExample3() throws Exception {
    	dimacsStringSolver.firstConstr = true;
    	dimacsStringSolver.fixedNbClauses = true;
    	IVecInt literals = new VecInt();
    	IConstr expected = dimacsStringSolver.addClause(literals);
    	
    	assertNull(expected);
    }
    
    public void testAddClauseExample4() throws Exception {
    	dimacsStringSolver.firstConstr = true;
    	dimacsStringSolver.fixedNbClauses = true;
    	IVecInt literals = new VecInt();
    	for(int i=0;i<100;i++)
    		literals.push(i);
    	IConstr expected = dimacsStringSolver.addClause(literals);
    	
    	assertNull(expected);
    }
    
    public void testAddAtMostExample1() throws Exception {
    	IVecInt literals = new VecInt();
    	IConstr expected = dimacsStringSolver.addAtMost(literals,1);
    	
    	assertNull(expected);
    }
    
    public void testAddAtMostExample2() throws Exception {
    	dimacsStringSolver.firstConstr = false;
    	IVecInt literals = new VecInt();
    	IConstr expected = dimacsStringSolver.addAtMost(literals,1);
    	
    	assertNull(expected);
    }
    
    public void testAddAtMostExample3() throws Exception {
    	dimacsStringSolver.firstConstr = true;
    	dimacsStringSolver.fixedNbClauses = true;
    	IVecInt literals = new VecInt();
    	IConstr expected = dimacsStringSolver.addAtMost(literals,1);
    	
    	assertNull(expected);
    }
    
    public void testAddAtMostExample4() throws Exception {
    	dimacsStringSolver.firstConstr = true;
    	dimacsStringSolver.fixedNbClauses = true;
    	IVecInt literals = new VecInt();
    	for(int i=0;i<100;i++)
    		literals.push(i);
    	IConstr expected = dimacsStringSolver.addAtMost(literals,1);
    	
    	assertNull(expected);
    }
    
    public void testAddAtMostExample5() throws Exception {
    	IVecInt literals = new VecInt();
    	try {
    		dimacsStringSolver.addAtMost(literals,5);
    	} catch(Exception e){
    		assertEquals("Not a clausal problem! degree 5",e.getMessage());
    	}
    	
    }
    
    public void testAddAtMostExample6() throws Exception {
    	dimacsStringSolver.firstConstr = true;
    	dimacsStringSolver.fixedNbClauses = false;
    	IVecInt literals = new VecInt();
    	for(int i=0;i<100;i++)
    		literals.push(i);
    	IConstr expected = dimacsStringSolver.addAtMost(literals,1);
    	
    	assertNull(expected);
    }
    
    public void testAddAtLeastExample1() throws Exception {
    	IVecInt literals = new VecInt();
    	try {
    		dimacsStringSolver.addAtLeast(literals,5);
    	} catch(Exception e){
    		assertEquals("Not a clausal problem! degree 5",e.getMessage());
    	}   	
    }
    
    public void testAddAtLeastExample2() throws Exception {
    	IVecInt literals = new VecInt();
    	IConstr expected = dimacsStringSolver.addAtLeast(literals,1);
    	
    	assertNull(expected);
    }
    
    public void testAddExactlyExample1() throws Exception {
    	IVecInt literals = new VecInt();
    	try {
    		dimacsStringSolver.addExactly(literals,5);
    	} catch(Exception e){
    		assertEquals("Not a clausal problem! degree 5",e.getMessage());
    	}   	
    }
    
    public void testAddExactlyExample2() throws Exception {
    	IVecInt literals = new VecInt();
    	IConstr expected = dimacsStringSolver.addExactly(literals,1);
    	
    	assertNull(expected);
    }
    
    public void testReset() throws Exception {
    	dimacsStringSolver.reset();
    	assertFalse(dimacsStringSolver.fixedNbClauses);
    	assertTrue(dimacsStringSolver.firstConstr);
    }
    
    public void testToString() throws Exception {
    	assertEquals("Dimacs output solver",dimacsStringSolver.toString("prefix"));
    }
    
    public void testnConstraints() throws Exception {
    	assertEquals(0, dimacsStringSolver.nConstraints());
    }
    
    public void testnVars() throws Exception {
    	assertEquals(0, dimacsStringSolver.nVars());
    }
    
    public void testNextFreeVarId() throws Exception {
    	assertEquals(1, dimacsStringSolver.nextFreeVarId(true));
    	assertEquals(2, dimacsStringSolver.nextFreeVarId(false));
    }
    
    public void testModelWithInternalVariables() throws Exception {
    	try {
    		dimacsStringSolver.modelWithInternalVariables();
    	} catch (Exception e) {
    		
    	}
    }
    
    public void testRealNumberOfVariables() throws Exception {
    	assertEquals(0, dimacsStringSolver.realNumberOfVariables());
    }
    
    public void testRegisterLiteral() throws Exception {
    	try {
    		dimacsStringSolver.registerLiteral(0);
    	} catch (Exception e) {
    		
    	}
    }
    
    public void testPrimeImplicant() throws Exception {
    	try {
    		dimacsStringSolver.primeImplicant(5);
    	} catch (Exception e) {
    		
    	}
    }
    
    public void testPrintStat() throws Exception {
    	StringWriter strw = new StringWriter();
    	PrintWriter out = new PrintWriter(strw);
    	try {
    		dimacsStringSolver.printStat(out);
    	} catch (Exception e) {
    		
    	}
    }
    
    public void testPrintInfos() throws Exception {
    	StringWriter strw = new StringWriter();
    	PrintWriter out = new PrintWriter(strw);
    	try {
    		dimacsStringSolver.printInfos(out);
    	} catch (Exception e) {
    		
    	}
    }
    
    public void testOut() throws Exception {
    	assertEquals("", dimacsStringSolver.getOut().toString());
    }
    
    public void testToStringNoArgs() throws Exception {
    	assertTrue(dimacsStringSolver.toString().contains("p cnf"));
    }
    
}
