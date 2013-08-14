package org.sat4j.minisat.core;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.minisat.constraints.card.AtLeast;
import org.sat4j.minisat.constraints.cnf.Lits;
import org.sat4j.minisat.constraints.cnf.UnitClause;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.IConstr;
import org.sat4j.specs.IVecInt;

import junit.framework.TestCase;

public class ActivityComparatorTest extends TestCase{
	private ActivityComparator comp;
	
	public ActivityComparatorTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        this.comp = new ActivityComparator();
    }
    
    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testCompareExample1() throws Exception {
    	IVecInt ps = new VecInt();
        ILits voc = new Lits();
    	ps.push(67);
        ps.push(70);
    	Constr arg1 = new AtLeast(voc, ps, 0);
    	
    	assertEquals(0, comp.compare(arg1, arg1));
    }
    
    public void testCompareExample2() throws Exception {
    	Constr arg1 = new UnitClause(67);
    	Constr arg2 = new UnitClause(68);   	
    	arg1.setActivity(2.89);
    	arg2.setActivity(3.56);
    	assertEquals(-1, comp.compare(arg1, arg2));
    }
}
