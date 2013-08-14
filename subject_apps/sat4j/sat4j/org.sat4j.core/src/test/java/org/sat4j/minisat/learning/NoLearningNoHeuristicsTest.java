package org.sat4j.minisat.learning;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.constraints.MixedDataStructureDanielWL;
import org.sat4j.minisat.constraints.card.AtLeast;
import org.sat4j.minisat.constraints.cnf.Lits;
import org.sat4j.minisat.core.Constr;
import org.sat4j.minisat.core.DataStructureFactory;
import org.sat4j.minisat.core.ILits;
import org.sat4j.minisat.core.SearchParams;
import org.sat4j.minisat.core.Solver;
import org.sat4j.minisat.orders.VarOrderHeap;
import org.sat4j.minisat.restarts.MiniSATRestarts;
import org.sat4j.specs.IVecInt;

import junit.framework.TestCase;

public class NoLearningNoHeuristicsTest extends TestCase{
private NoLearningNoHeuristics noLearningNoHeuristics;
    
    public NoLearningNoHeuristicsTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.noLearningNoHeuristics = new NoLearningNoHeuristics();
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testLearns() throws Exception {
    	IVecInt ps = new VecInt();
        ILits voc = new Lits();
    	ps.push(new Integer(0));
    	ps.push(new Integer(1));
    	AtLeast atLeast = new AtLeast(voc, ps, 0);
    	
    	Constr expected = atLeast.toConstraint();
    	noLearningNoHeuristics.learns(expected); //does nothing
    }
}
