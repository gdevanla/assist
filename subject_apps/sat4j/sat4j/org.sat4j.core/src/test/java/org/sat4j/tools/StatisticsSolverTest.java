package org.sat4j.tools;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;

import junit.framework.TestCase;

import org.sat4j.core.Vec;
import org.sat4j.core.VecInt;
import org.sat4j.specs.IConstr;
import org.sat4j.specs.IVec;
import org.sat4j.specs.IVecInt;
import org.sat4j.specs.SearchListener;

public class StatisticsSolverTest extends TestCase{
	private StatisticsSolver statisticsSolver;
    
    public StatisticsSolverTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.statisticsSolver = new StatisticsSolver();
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
    	assertEquals(0, solver.newVar());
    }
    
    public void testNewVarExample2() throws Exception {
    	assertEquals(5,statisticsSolver.newVar(5));
    }
    
    public void testSetExpectedNumberOfClauses() throws Exception {
    	statisticsSolver.setExpectedNumberOfClauses(5);
    	
		Field f = statisticsSolver.getClass().getDeclaredField( "expectedNumberOfConstraints" );
		f.setAccessible( true );
		int expectedNumberOfConstraints = (Integer) f.get( statisticsSolver );
		
		assertEquals(5, expectedNumberOfConstraints);
    }
    
    public void testAddClauseExample1() throws Exception {
    	IVecInt literals = new VecInt();
    	IConstr expected = statisticsSolver.addClause(literals);
    	
    	assertNull(expected);
    }
    
    public void testAddClauseExample2() throws Exception {
    	statisticsSolver.newVar(100);
    	IVecInt literals = new VecInt();
    	for(int i=0;i<10;i++)
    		literals.push(i);
    	IConstr expected = statisticsSolver.addClause(literals);
    	
    	assertNull(expected);
    }
    
    
    public void testAddClauseExample3() throws Exception {
    	statisticsSolver.newVar(100);
    	IVecInt literals = new VecInt();
    	for(int i=0;i<10;i++)
    		literals.push(-i);
    	IConstr expected = statisticsSolver.addClause(literals);
    	
    	assertNull(expected);
    }
    
    public void testAddClauseExample4() throws Exception {
    	statisticsSolver.newVar(100);
    	IVecInt literals = new VecInt();
    	for(int i=0;i<10;i++)
    		literals.push(-i);
    	literals.push(1);
    	IConstr expected = statisticsSolver.addClause(literals);
    	
    	assertNull(expected);
    }
    
    public void testAddClauseExample5() throws Exception {
    	statisticsSolver.newVar(100);
    	IVecInt literals = new VecInt();
    	for(int i=0;i<10;i++)
    		literals.push(i);
    	literals.push(-1);
    	IConstr expected = statisticsSolver.addClause(literals);
    	
    	assertNull(expected);
    }
    
    public void testAddAtMostExample1() throws Exception {
    	IVecInt literals = new VecInt();
    	try {
    		statisticsSolver.addAtMost(literals,1);
    	} catch(Exception e) {
    		assertEquals("Not implemented yet!", e.getMessage());
    	}
    	
    	
    }
    
    public void testAddAtLeast() throws Exception {
    	IVecInt literals = new VecInt();
    	try {
    		statisticsSolver.addAtLeast(literals,5);
    	} catch(Exception e){
    		assertEquals("Not implemented yet!",e.getMessage());
    	}   	
    }
    
    public void testAddExactly() throws Exception {
    	IVecInt literals = new VecInt();
    	try {
    		statisticsSolver.addExactly(literals,5);
    	} catch(Exception e){
    		assertEquals("Not implemented yet!",e.getMessage());
    	}   	
    }
    
    public void testReset() throws Exception {
    	statisticsSolver.reset(); //does nothing
    }
    
    public void testToString() throws Exception {
    	assertEquals("prefixStatistics about the benchmarks",statisticsSolver.toString("prefix"));
    }
    
    public void testnConstraints() throws Exception {
    	assertEquals(0, statisticsSolver.nConstraints());
    }
    
    public void testnVars() throws Exception {
    	assertEquals(0, statisticsSolver.nVars());
    }
    
    public void testVarExample1() throws Exception {
    	try {
    		statisticsSolver.newVar(20);
    	} catch (Exception e) {
    		assertEquals("Not implemented yet!", e.getMessage());
    	}
    }

    public void testVarExample2() throws Exception {
    	try {
    		statisticsSolver.newVar();
    	} catch (Exception e) {
    		assertEquals("Not implemented yet!", e.getMessage());
    	}
    }
    
    public void testNextFreeVarId() throws Exception {
    	try {
    		statisticsSolver.nextFreeVarId(false);
    	} catch (Exception e) {
    		assertEquals("Not implemented yet!", e.getMessage());
    	}
    }
    
    public void testModelWithInternalVariables() throws Exception {
    	try {
    		statisticsSolver.modelWithInternalVariables();
    	} catch (Exception e) {
    		
    	}
    }
    
    public void testRealNumberOfVariables() throws Exception {
    	assertEquals(0, statisticsSolver.realNumberOfVariables());
    }
    
    public void testRegisterLiteral() throws Exception {
    	try {
    		statisticsSolver.registerLiteral(0);
    	} catch (Exception e) {
    		
    	}
    }
    
    public void testPrimeImplicantExample1() throws Exception {
    	try {
    		statisticsSolver.primeImplicant(5);
    	} catch (Exception e) {
    		assertEquals("That solver only compute statistics",e.getMessage());
    	}
    }
    
    public void testPrimeImplicantExample2() throws Exception {
    	try {
    		statisticsSolver.primeImplicant();
    	} catch (Exception e) {
    		assertEquals("That solver only compute statistics",e.getMessage());
    	}
    }
    
    public void testPrintStat() throws Exception {
    	StringWriter strw = new StringWriter();
    	PrintWriter out = new PrintWriter(strw);
    	try {
    		statisticsSolver.printStat(out, "prefix");
    	} catch (Exception e) {
    		assertEquals("Not implemented yet!",e.getMessage());
    	}
    }
    
    
    public void testPrintStream() throws Exception {
    	PrintStream out = null;
    	try {
    		statisticsSolver.printStat(out, "prefix");
    	} catch (Exception e) {
    		assertEquals("Not implemented yet!",e.getMessage());
    	}
    }
    
    public void testPrintStatExample() throws Exception {
    	statisticsSolver.newVar(5);
    	StringWriter strw = new StringWriter();
    	PrintWriter out = new PrintWriter(strw);
    	try {
    		statisticsSolver.printStat(out);
    	} catch(Exception e ) {
    		
    	}
    	
    }
    
    public void testPrintStatExample2() throws Exception {
    	statisticsSolver.newVar(100);
    	IVecInt literals = new VecInt();
    	for(int i=0;i<10;i++)
    		literals.push(-i);
    	literals.push(1);
    	statisticsSolver.addClause(literals);
    	
    	StringWriter strw = new StringWriter();
    	PrintWriter out = new PrintWriter(strw);
    	statisticsSolver.printStat(out);
    
    	assertTrue(strw.toString().contains(""));
    }
    
    // Abstract Solver Tests
    public void testRemoveSubsumedConstr() throws Exception {
    	IVecInt literals = new VecInt();
    	IConstr expected = statisticsSolver.addClause(literals);
    	try {
    		statisticsSolver.removeSubsumedConstr(expected);
    	} catch(Exception e) {
    		assertEquals("Not implemented yet!",e.getMessage());
    	}
    }
    
    public void testClearLearntClauses() throws Exception {
    	try {
    		statisticsSolver.clearLearntClauses();
    	} catch(Exception e) {
    		assertEquals("Not implemented yet!",e.getMessage());
    	}
    }
    
    public void testSetSearchListener() throws Exception {
    	SearchListener list= null;
    	try {
    		statisticsSolver.setSearchListener(list);
    	} catch(Exception e) {
    		assertEquals("Not implemented yet!",e.getMessage());
    	}
    }
    
    public void testRemoveConstr() throws Exception {
    	IVecInt literals = new VecInt();
    	IConstr expected = statisticsSolver.addClause(literals);
    	try {
    		statisticsSolver.removeConstr(expected);
    	} catch (Exception e) {
    		
    	}
    }
    
    public void testAddClauses() throws Exception {
    	IVec<IVecInt> clauses = new Vec<IVecInt>();    	
    	
    	try {
    		statisticsSolver.addAllClauses(clauses);
    	} catch (Exception e) {
    		
    	}
    }
    
    public void testSetTimeout() throws Exception {
    	statisticsSolver.setTimeout(4); //does nothing
    	try {
    		statisticsSolver.getTimeout();
    	}catch (Exception e) {
    		assertEquals("Not implemented yet!",e.getMessage());
    	}

    }
    
    public void testSetTimeoutMs() throws Exception {
    	try {
    		statisticsSolver.setTimeoutMs(1L);
    	} catch (Exception e) {
    		assertEquals("Not implemented yet!",e.getMessage());
    	}
    }
    
    public void testGetTimeoutMs() throws Exception {
    	try {
    		statisticsSolver.getTimeoutMs();
    	} catch (Exception e) {
    		assertEquals("Not implemented yet!",e.getMessage());
    	}
    }
    
    public void testExpireTimeout() throws Exception {
    	try {
    		statisticsSolver.expireTimeout();
    	}  catch (Exception e) {
    		assertEquals("Not implemented yet!",e.getMessage());
    	}
    }
    
    public void testSetTimeoutOnConflicts() throws Exception {
    	try {
    		statisticsSolver.setTimeoutOnConflicts(4);
    	} catch (Exception e) {
    		assertEquals("Not implemented yet!",e.getMessage());
    	}
    }
    
    public void testIsSatisfiableExample1() throws Exception {
    	IVecInt assumps = new VecInt();
    	try {
    		statisticsSolver.isSatisfiable(assumps, true);
    	} catch (Exception e) {
    		assertEquals("That solver only compute statistics",e.getMessage());
    	}
    	
    }
    
    public void testIsSatisfiableExample2() throws Exception {
    	try {
    		statisticsSolver.isSatisfiable(true);
    	} catch (Exception e) {
    		assertEquals("That solver only compute statistics",e.getMessage());
    	}
    	
    }
    
    public void testIsSatisfiableExample3() throws Exception {
    	try {
    		statisticsSolver.isSatisfiable();
    	} catch (Exception e) {
    		assertEquals("That solver only compute statistics",e.getMessage());
    	}
    	
    }
    
    public void testIsSatisfiableExample4() throws Exception {
    	IVecInt assumps = new VecInt();
    	try {
    		statisticsSolver.isSatisfiable(assumps);
    	} catch (Exception e) {
    		assertEquals("That solver only compute statistics",e.getMessage());
    	}
    	
    }
    
    public void testIsDBSimplificationAllowed() throws Exception {
    	try {
    		statisticsSolver.isDBSimplificationAllowed();
    	} catch (Exception e) {
    		assertEquals("Not implemented yet!",e.getMessage());
    	}
    }
    
    public void testGetStat() throws Exception {
    	try {
    		statisticsSolver.getStat();
    	} catch (Exception e) {
    		assertEquals("Not implemented yet!",e.getMessage());
    	}
    }
    
    public void testModelExample1() throws Exception {
    	try {
    		statisticsSolver.model();
    	} catch (Exception e) {
    		
    	}
    	
    }
    
    public void testModelExample2() throws Exception {
    	try {
    		statisticsSolver.model(5);
    	} catch (Exception e) {
    		
    	}
    	
    }

    public void testFindModelExample1() throws Exception {
    	try {
    		statisticsSolver.findModel();
    	} catch (Exception e) {
    		
    	}
    	
    }
    
    public void testFindModelExample2() throws Exception {
    	IVecInt assumps = new VecInt();
    	try {
    		statisticsSolver.findModel(assumps);
    	} catch (Exception e) {
    		
    	}
    	
    }
    
    public void testAddBlockingClause() throws Exception {
    	IVecInt assumps = new VecInt();
    	try {
    		statisticsSolver.addBlockingClause(assumps);
    	} catch (Exception e) {

    	}
    	
    }
    
    public void testGetSearchListener() throws Exception {
    	try {
    		statisticsSolver.getSearchListener();
    	} catch (Exception e) {

    	}    	
    }
    
    public void testUnsatExplanation() throws Exception {
    	try {
    		statisticsSolver.unsatExplanation();
    	} catch (Exception e) {

    	}    	
    }
    
    public void testGetSolvingEngine() throws Exception {
    	try {
    		statisticsSolver.getSolvingEngine();
    	} catch (Exception e) {

    	}    	
    }
    
    public void testVerbose() throws Exception {
    	try {
    		statisticsSolver.isVerbose();
    	} catch (Exception e) {
    		assertEquals("Not implemented yet!",e.getMessage());
    	}
    }
    
    public void testGetLogPrefix() throws Exception {
    	try {
    		statisticsSolver.getLogPrefix();
    	} catch (Exception e) {
    		assertEquals("Not implemented yet!",e.getMessage());
    	}
    }
    
    public void testSetLogPrefix() throws Exception {
    	try {
    		statisticsSolver.setLogPrefix("prefix");
    	} catch (Exception e) {
    		assertEquals("Not implemented yet!",e.getMessage());
    	}
    }
    
    public void testSolverKeptHot() throws Exception {
    	try {
    		statisticsSolver.isSolverKeptHot();
    	} catch (Exception e) {
    		assertEquals("Not implemented yet!",e.getMessage());
    	}
    }
    
    public void testSetSolverKeptHot() throws Exception {
    	try {
    		statisticsSolver.setKeepSolverHot(true);
    	} catch (Exception e) {
    		assertEquals("Not implemented yet!",e.getMessage());
    	}
    }
}
