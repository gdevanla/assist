package org.sat4j.tools;

import java.io.PrintWriter;
import java.io.StringWriter;

import junit.framework.TestCase;

import org.sat4j.core.Vec;
import org.sat4j.core.VecInt;
import org.sat4j.specs.IConstr;
import org.sat4j.specs.IVec;
import org.sat4j.specs.IVecInt;

public class DimacsOutputSolverTest extends TestCase{
	private DimacsOutputSolver dimacsOutputSolver;
	private StringWriter stwr = new StringWriter();
    private PrintWriter out = new PrintWriter(stwr);
    
    public DimacsOutputSolverTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.dimacsOutputSolver = new DimacsOutputSolver(out);
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
    	assertEquals(0,dimacsOutputSolver.newVar());
    	assertEquals(0, solver.newVar());
    }
    
    public void testNewVarExample2() throws Exception {
    	assertEquals(0,dimacsOutputSolver.newVar(5));
    }
    
    public void testSetExpectedNumberOfClauses() throws Exception {
    	dimacsOutputSolver.setExpectedNumberOfClauses(5);
    	assertTrue(stwr.toString().contains("5"));
    	assertTrue(dimacsOutputSolver.fixedNbClauses);
    	assertEquals(5, dimacsOutputSolver.nbclauses);
    }
    
    public void testAddClauseExample1() throws Exception {
    	IVecInt literals = new VecInt();
    	IConstr expected = dimacsOutputSolver.addClause(literals);
    	
    	assertNull(expected);
    }
    
    public void testAddClauseExample2() throws Exception {
    	dimacsOutputSolver.firstConstr = false;
    	IVecInt literals = new VecInt();
    	IConstr expected = dimacsOutputSolver.addClause(literals);
    	
    	assertNull(expected);
    }
    
    public void testAddClauseExample3() throws Exception {
    	dimacsOutputSolver.firstConstr = true;
    	dimacsOutputSolver.fixedNbClauses = true;
    	IVecInt literals = new VecInt();
    	IConstr expected = dimacsOutputSolver.addClause(literals);
    	
    	assertNull(expected);
    }
    
    public void testAddClauseExample4() throws Exception {
    	dimacsOutputSolver.firstConstr = true;
    	dimacsOutputSolver.fixedNbClauses = true;
    	IVecInt literals = new VecInt();
    	for(int i=0;i<100;i++)
    		literals.push(i);
    	IConstr expected = dimacsOutputSolver.addClause(literals);
    	
    	assertNull(expected);
    }
    
    public void testAddAtMostExample1() throws Exception {
    	IVecInt literals = new VecInt();
    	IConstr expected = dimacsOutputSolver.addAtMost(literals,1);
    	
    	assertNull(expected);
    }
    
    public void testAddAtMostExample2() throws Exception {
    	dimacsOutputSolver.firstConstr = false;
    	IVecInt literals = new VecInt();
    	IConstr expected = dimacsOutputSolver.addAtMost(literals,1);
    	
    	assertNull(expected);
    }
    
    public void testAddAtMostExample3() throws Exception {
    	dimacsOutputSolver.firstConstr = true;
    	dimacsOutputSolver.fixedNbClauses = true;
    	IVecInt literals = new VecInt();
    	IConstr expected = dimacsOutputSolver.addAtMost(literals,1);
    	
    	assertNull(expected);
    }
    
    public void testAddAtMostExample4() throws Exception {
    	dimacsOutputSolver.firstConstr = true;
    	dimacsOutputSolver.fixedNbClauses = true;
    	IVecInt literals = new VecInt();
    	for(int i=0;i<100;i++)
    		literals.push(i);
    	IConstr expected = dimacsOutputSolver.addAtMost(literals,1);
    	
    	assertNull(expected);
    }
    
    public void testAddAtMostExample5() throws Exception {
    	IVecInt literals = new VecInt();
    	try {
    		dimacsOutputSolver.addAtMost(literals,5);
    	} catch(Exception e){
    		assertEquals("Not a clausal problem! degree 5",e.getMessage());
    	}
    	
    }
    
    public void testAddAtLeastExample1() throws Exception {
    	IVecInt literals = new VecInt();
    	try {
    		dimacsOutputSolver.addAtLeast(literals,5);
    	} catch(Exception e){
    		assertEquals("Not a clausal problem! degree 5",e.getMessage());
    	}   	
    }
    
    public void testAddAtLeastExample2() throws Exception {
    	IVecInt literals = new VecInt();
    	IConstr expected = dimacsOutputSolver.addAtLeast(literals,1);
    	
    	assertNull(expected);
    }
    
    public void testAddExactlyExample1() throws Exception {
    	IVecInt literals = new VecInt();
    	try {
    		dimacsOutputSolver.addExactly(literals,5);
    	} catch(Exception e){
    		assertEquals("Not a clausal problem! degree 5",e.getMessage());
    	}   	
    }
    
    public void testAddExactlyExample2() throws Exception {
    	IVecInt literals = new VecInt();
    	IConstr expected = dimacsOutputSolver.addExactly(literals,1);
    	
    	assertNull(expected);
    }
    
    public void testReset() throws Exception {
    	dimacsOutputSolver.reset();
    	assertFalse(dimacsOutputSolver.fixedNbClauses);
    	assertTrue(dimacsOutputSolver.firstConstr);
    }
    
    public void testToString() throws Exception {
    	assertEquals("Dimacs output solver",dimacsOutputSolver.toString("prefix"));
    }
    
    public void testnConstraints() throws Exception {
    	assertEquals(0, dimacsOutputSolver.nConstraints());
    }
    
    public void testnVars() throws Exception {
    	assertEquals(0, dimacsOutputSolver.nVars());
    }
    
    public void testNextFreeVarId() throws Exception {
    	assertEquals(1, dimacsOutputSolver.nextFreeVarId(true));
    	assertEquals(2, dimacsOutputSolver.nextFreeVarId(false));
    }
    
    public void testModelWithInternalVariables() throws Exception {
    	try {
    		dimacsOutputSolver.modelWithInternalVariables();
    	} catch (Exception e) {
    		
    	}
    }
    
    public void testRealNumberOfVariables() throws Exception {
    	assertEquals(0, dimacsOutputSolver.realNumberOfVariables());
    }
    
    public void testRegisterLiteral() throws Exception {
    	try {
    		dimacsOutputSolver.registerLiteral(0);
    	} catch (Exception e) {
    		
    	}
    }
    
    public void testPrimeImplicant() throws Exception {
    	try {
    		dimacsOutputSolver.primeImplicant(5);
    	} catch (Exception e) {
    		
    	}
    }
    
    public void testPrintStat() throws Exception {
    	try {
    		dimacsOutputSolver.printStat(out);
    	} catch (Exception e) {
    		
    	}
    }
    
    public void testPrintInfos() throws Exception {
    	try {
    		dimacsOutputSolver.printInfos(out);
    	} catch (Exception e) {
    		
    	}
    }
    
    // Abstract Solver Tests
    public void testRemoveSubsumedConstr() throws Exception {
    	IVecInt literals = new VecInt();
    	IConstr expected = dimacsOutputSolver.addClause(literals);
    	assertFalse(dimacsOutputSolver.removeSubsumedConstr(expected));
    }
    
    public void testRemoveConstr() throws Exception {
    	IVecInt literals = new VecInt();
    	IConstr expected = dimacsOutputSolver.addClause(literals);
    	try {
    		dimacsOutputSolver.removeConstr(expected);
    	} catch (Exception e) {
    		
    	}
    }
    
    public void testAddClauses() throws Exception {
    	IVec<IVecInt> clauses = new Vec<IVecInt>();    	
    	
    	try {
    		dimacsOutputSolver.addAllClauses(clauses);
    	} catch (Exception e) {
    		
    	}
    }
    
    public void testTimeout() throws Exception {
    	dimacsOutputSolver.setTimeout(4);
    	dimacsOutputSolver.setTimeoutMs(1L);
    	dimacsOutputSolver.expireTimeout();
    	
    	assertEquals(0, dimacsOutputSolver.getTimeout());
    	assertEquals(0L, dimacsOutputSolver.getTimeoutMs());
    }
    
    public void testIsSatisfiableExample1() throws Exception {
    	IVecInt assumps = new VecInt();
    	try {
    		dimacsOutputSolver.isSatisfiable(assumps, true);
    	} catch (Exception e) {
    		assertEquals("There is no real solver behind!",e.getMessage());
    	}
    	
    }
    
    public void testIsSatisfiableExample2() throws Exception {
    	try {
    		dimacsOutputSolver.isSatisfiable(true);
    	} catch (Exception e) {
    		assertEquals("There is no real solver behind!",e.getMessage());
    	}
    	
    }
    
    public void testIsSatisfiableExample3() throws Exception {
    	try {
    		dimacsOutputSolver.isSatisfiable();
    	} catch (Exception e) {
    		assertEquals("There is no real solver behind!",e.getMessage());
    	}
    	
    }
    
    public void testIsSatisfiableExample4() throws Exception {
    	IVecInt assumps = new VecInt();
    	try {
    		dimacsOutputSolver.isSatisfiable(assumps);
    	} catch (Exception e) {
    		assertEquals("There is no real solver behind!",e.getMessage());
    	}
    	
    }
    
    public void testIsDBSimplificationAllowed() throws Exception {
    	assertFalse(dimacsOutputSolver.isDBSimplificationAllowed());
    }
    
    public void testGetStat() throws Exception {
    	assertNull(dimacsOutputSolver.getStat());
    }
    
    public void testModelExample1() throws Exception {
    	try {
    		dimacsOutputSolver.model();
    	} catch (Exception e) {
    		
    	}
    	
    }
    
    public void testModelExample2() throws Exception {
    	try {
    		dimacsOutputSolver.model(5);
    	} catch (Exception e) {
    		
    	}
    	
    }

    public void testFindModelExample1() throws Exception {
    	try {
    		dimacsOutputSolver.findModel();
    	} catch (Exception e) {
    		
    	}
    	
    }
    
    public void testFindModelExample2() throws Exception {
    	IVecInt assumps = new VecInt();
    	try {
    		dimacsOutputSolver.findModel(assumps);
    	} catch (Exception e) {
    		
    	}
    	
    }
    
    public void testAddBlockingClause() throws Exception {
    	IVecInt assumps = new VecInt();
    	try {
    		dimacsOutputSolver.addBlockingClause(assumps);
    	} catch (Exception e) {

    	}
    	
    }
    
    public void testGetSearchListener() throws Exception {
    	try {
    		dimacsOutputSolver.getSearchListener();
    	} catch (Exception e) {

    	}    	
    }
    
    public void testUnsatExplanation() throws Exception {
    	try {
    		dimacsOutputSolver.unsatExplanation();
    	} catch (Exception e) {

    	}    	
    }
    
    public void testGetSolvingEngine() throws Exception {
    	try {
    		dimacsOutputSolver.getSolvingEngine();
    	} catch (Exception e) {

    	}    	
    }
    
    public void testVerbose() throws Exception {
    	assertTrue(dimacsOutputSolver.isVerbose());
    }
    
    public void testGetLogPrefix() throws Exception {
    	assertEquals("", dimacsOutputSolver.getLogPrefix());
    }
    
    public void testSolverKeptHot() throws Exception {
    	assertFalse(dimacsOutputSolver.isSolverKeptHot());
    }
}
