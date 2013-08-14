package org.sat4j.minisat.constraints.card;

import java.lang.reflect.Field;

import junit.framework.TestCase;

import org.sat4j.core.Vec;
import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.minisat.constraints.cnf.Lits;
import org.sat4j.minisat.constraints.cnf.UnitClauses;
import org.sat4j.minisat.core.Constr;
import org.sat4j.minisat.core.ILits;
import org.sat4j.minisat.core.Propagatable;
import org.sat4j.minisat.core.UnitPropagationListener;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.IVec;
import org.sat4j.specs.IVecInt;
import org.sat4j.specs.IteratorInt;

public class AtLeastTest extends TestCase  {
	private AtLeast atLeast;
    private IVecInt ps = new VecInt();
    private ILits voc = new Lits();
	private UnitPropagationListener s = (UnitPropagationListener) SolverFactory.newDefault();
	
	public AtLeastTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        ps.push(67);
        ps.push(70);
        this.atLeast = new AtLeast(voc, ps, 0);
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testNiceParametersExample1() throws Exception{
        for(int i=0;i<15;i++) 
        	ps.push(i);
        
    	int expected = AtLeast.niceParameters(null, voc, ps, 0);
        assertEquals(0, expected);
    }
    
    public void testNiceParametersExample2() throws Exception{
        for(int i=0;i<15;i++) 
        	ps.push(i);
    	try {
    		AtLeast.niceParameters(null, voc, ps, 50);
    	} catch (ContradictionException ex) {
    		
    	}
        
    }
    
    public void testNiceParametersExample3() throws Exception{
    	int expected = AtLeast.niceParameters(null, voc, ps, 0);
        assertEquals(0, expected);
    }
    
   
    public void testNiceParametersExample4() throws Exception{
    	for(int i=67;i<91;i++) 
    		ps.push(i);
    	int expected = AtLeast.niceParameters(null, voc, ps, 0);
    	assertEquals(0, expected); 
    }

    
    public void testNiceParametersExample5() throws Exception{
    	try {
    		AtLeast.niceParameters(null, voc, VecInt.EMPTY, 0);
    	} catch(ContradictionException ex) {
    		
    	}

    }
    
    public void testNiceParametersExample6() throws Exception{
    	ps.clear();
    	ps.push(new Integer(67));
    	ps.push(new Integer(70));

    	voc.satisfies(ps.get(0));
    	
    	int expected = AtLeast.niceParameters(s, voc, ps, 0);
    	assertEquals(-1, expected);
    }
    
    public void testNiceParametersExample7() throws Exception{
  	  for(int i=67;i<91;i++) 
    		s.enqueue(i);
  	try {
  		AtLeast.niceParameters(s, voc, VecInt.EMPTY, 1);
  	} catch(ContradictionException ex) {
  		
  	}
    }
    
    public void testAtLeastNewExample1() throws Exception{
    	for(int i=67;i<91;i++) 
    		ps.push(i);
    	
    	Constr expected = AtLeast.atLeastNew(null, voc, ps, 0);
    	assertEquals(UnitClauses.class, expected.getClass()); 
    }
    
    public void testAtLeastNewExample2() throws Exception{
    	ps.clear();
    	ps.push(new Integer(67));
    	ps.push(new Integer(70));

    	voc.satisfies(ps.get(0));
    	
    	Constr expected = AtLeast.atLeastNew(s, voc, ps, 0);
    	assertEquals(1, expected.size());
    }
    
    public void testSimplify() throws Exception{
    	assertFalse(atLeast.simplify());
    }
    
    public void testLearnt() throws Exception{
    	assertFalse(atLeast.learnt());
    }
    
    public void testGetActivity() throws Exception{
    	assertEquals(0.0, atLeast.getActivity());
    }
    
    public void testLocked() throws Exception{
    	assertTrue(atLeast.locked());
    }
    
    public void testSize() throws Exception{
    	assertEquals(2, atLeast.size());

    	ps.push(new Integer(70));

    	assertEquals(2, atLeast.size());
    }
  
    public void testUndo() throws Exception{
    	atLeast.undo(67);
    }
    
    public void testSetLearnt() throws Exception{
    	try {
    		atLeast.setLearnt();
    	} catch(UnsupportedOperationException ex) {
    		
    	}
    }
    
    public void testGet() throws Exception{
    	assertEquals(67, atLeast.get(0));
    }
    
    public void testRescaleBy() throws Exception{
    	try {
    		atLeast.rescaleBy(9.0);
    	} catch(UnsupportedOperationException ex) {
    		
    	}
    }
    
    public void testAssertConstraint() throws Exception{
    	try {
    		atLeast.assertConstraint(s);
    	} catch(UnsupportedOperationException ex) {
    		
    	}
    }
    
    public void testToString() throws Exception{
    	assertEquals("Card (2) :  + -33[?@0]   + 35[?@0]  >= 0", atLeast.toString());
    }
    
    public void testCanBePropogatedMultipleTimes() throws Exception{
    	assertTrue(atLeast.canBePropagatedMultipleTimes());
    }
    
    public void testToConstraint() throws Exception{
    	Constr expected = atLeast.toConstraint();
    	
    	assertEquals(2, expected.size());
    }
    
    public void testCalcReasonOnTheFlyExample1() throws Exception{
    	IVecInt trail = new VecInt();
    	for(int i=0;i<15;i++)
    		trail.push(i);
    	
    	IVecInt reason = new VecInt();
    	for(int i=67;i<100;i++)
    		reason.push(i);
    	
    	atLeast.calcReasonOnTheFly(0, trail, reason);
    	
    	assertEquals(33, reason.size());
    }
    
    public void testCalcReasonOnTheFlyExample2() throws Exception{
    	IVecInt trail = new VecInt();
    	for(int i=0;i<15;i++)
    		trail.push(i);

    	atLeast.calcReasonOnTheFly(ILits.UNDEFINED, trail, trail);
    	assertEquals(15, trail.size());
    }

    public void testCalcReasonOnTheFlyExample3() throws Exception{
    	IVecInt literals = new VecInt();
    	literals.push(66);
    	literals.push(67);
    	
    	IVecInt trail = new VecInt();
    	trail.push(66);
    	trail.push(67);
    	
    	AtLeast example = new AtLeast(voc, literals, 0);
    	
    	example.calcReasonOnTheFly(ILits.UNDEFINED, trail, trail);
    	
    	assertEquals(6, trail.size());
    }
    
    public void testCalcReasonExample1() throws Exception{
    	IVecInt reason = new VecInt();
    	for(int i=67;i<100;i++)
    		reason.push(i);
    	
    	atLeast.calcReason(0, reason);
    	
    	assertEquals(33, reason.size());
    }
    
    public void testCalcReasonExample2() throws Exception{
    	IVecInt trail = new VecInt();
    	for(int i=0;i<15;i++)
    		trail.push(i);

    	atLeast.calcReason(ILits.UNDEFINED, trail);
    	assertEquals(15, trail.size());
    }

    public void testCalcReasonExample3() throws Exception{
    	IVecInt literals = new VecInt();
    	literals.push(66);
    	literals.push(67);
    	
    	IVecInt trail = new VecInt();
    	trail.push(66);
    	trail.push(67);
    	
    	AtLeast example = new AtLeast(voc, literals, 0);
    	voc.satisfies(66);
    	voc.satisfies(67);
    	
    	example.calcReason(ILits.UNDEFINED, trail);
    	
    	assertEquals(3, trail.size());
    }
    
    public void testCalcReasonExample4() throws Exception{
    	IVecInt literals = new VecInt();
    	literals.push(66);
    	literals.push(67);
    	
    	IVecInt trail = new VecInt();
    	trail.push(66);
    	trail.push(67);
    	
    	AtLeast example = new AtLeast(voc, literals, 0);
    	voc.satisfies(66);
    	voc.satisfies(67);
    	example.maxUnsatisfied = -20;
    	
    	example.calcReason(ILits.UNDEFINED, trail);
    	
    	assertEquals(3, trail.size());
    }
   
    public void testRegisterExample1() throws Exception{
    	atLeast.register();

		Field f = atLeast.getClass().getDeclaredField( "counter" );
		f.setAccessible( true );
		int counter = (Integer)f.get( atLeast );
		
    	assertEquals(0, counter);
    }
    
    public void testRegisterExample2() throws Exception{
    	IVecInt literals = new VecInt();
    	literals.push(66);
    	literals.push(67);
    	
    	AtLeast example = new AtLeast(voc, literals, 0);
    	voc.satisfies(66);
    	voc.satisfies(67);
    	
    	example.register();
    	 
		Field f = example.getClass().getDeclaredField( "counter" );
		f.setAccessible( true );
		int counter = (Integer)f.get( atLeast );
		
    	assertEquals(0, counter);
    }
    
    public void testPropogateExample1() throws Exception{
    	atLeast.maxUnsatisfied = 0;
    	
    	assertFalse(atLeast.propagate(s, 67));    	
    }
    
    public void testPropogateExample2() throws Exception{
    	assertTrue(atLeast.propagate(s, 0));
    }
    
    public void testPropogateExample3() throws Exception{
    	atLeast.maxUnsatisfied = 1;
    	assertTrue(atLeast.propagate(s, 0));
    }
    
    public void testPropogateExample4() throws Exception{
    	IVecInt literals = new VecInt();
    	literals.push(66);
    	literals.push(67);
    	
    	AtLeast example = new AtLeast(voc, literals, 0);
    	voc.unassign(66);
    	voc.unassign(67);
    	
    	example.maxUnsatisfied = 1;
    	assertFalse(example.propagate(s, 0));
    }
}

