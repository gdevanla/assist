package org.sat4j.minisat.constraints.card;

import java.lang.reflect.Field;
import java.math.BigInteger;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.minisat.constraints.cnf.Lits;
import org.sat4j.minisat.constraints.cnf.UnitClause;
import org.sat4j.minisat.constraints.cnf.UnitClauses;
import org.sat4j.minisat.core.Constr;
import org.sat4j.minisat.core.ILits;
import org.sat4j.minisat.core.UnitPropagationListener;
import org.sat4j.specs.IVecInt;

import junit.framework.TestCase;

public class MaxWatchCardTest extends TestCase{
	private MaxWatchCard maxWatchCard;
    private IVecInt ps = new VecInt();
    private ILits voc = new Lits();
	private UnitPropagationListener s = (UnitPropagationListener) SolverFactory.newDefault();
	
	public MaxWatchCardTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        for(int i=0;i<100;i++)
    		ps.push(5);
        voc.ensurePool(200);
        this.maxWatchCard = new MaxWatchCard(voc, ps, true, 0);
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testCalcReasonExample1() throws Exception{
    	IVecInt reason = new VecInt();
    	for(int i=67;i<100;i++)
    		reason.push(i);
    	voc.satisfies(5);
    	maxWatchCard.calcReason(0, reason);
    	
    	assertEquals(33, reason.size());
    }
    
    public void testCalcReasonExample2() throws Exception{
    	IVecInt trail = new VecInt();
    	for(int i=0;i<15;i++) 
    		trail.push(i);
    	voc.forgets(2);
    	maxWatchCard.calcReason(ILits.UNDEFINED, trail);
    	assertEquals(115, trail.size());
    }

    public void testCalcReasonExample3() throws Exception{   	
    	IVecInt trail = new VecInt();
    	trail.push(0);
    	trail.push(1);
    	
    	MaxWatchCard example = new MaxWatchCard(voc, trail, true, 0);

    	example.calcReason(ILits.UNDEFINED, trail);
    	
    	assertEquals(0, trail.size());
    }
    
    public void testCalcReasonExample4() throws Exception{
    	IVecInt literals = new VecInt();
    	for(int i=0;i<100;i++)
    		literals.push(i);
    	
    	IVecInt trail = new VecInt();
    	trail.push(0);
    	trail.push(1);
    	
    	voc.ensurePool(200);
    	MaxWatchCard example = new MaxWatchCard(voc, literals, false, 0);
    	
    	example.calcReason(ILits.UNDEFINED, trail);
    	
    	assertEquals(2, trail.size());
    }
      
    public void testCalcReasonOnTheFlyExample1() throws Exception{
    	IVecInt trail = new VecInt();
    	for(int i=0;i<15;i++)
    		trail.push(i);
    	
    	IVecInt reason = new VecInt();
    	for(int i=67;i<100;i++)
    		reason.push(i);
    	try {
    		maxWatchCard.calcReasonOnTheFly(0, trail, reason);
    	} catch (UnsupportedOperationException e) {
    		assertEquals("Not implemented yet!", e.getMessage());
    	}
    	
    	assertEquals(33, reason.size());
    }
    
    public void testCalcReasonOnTheFlyExample2() throws Exception{
    	IVecInt trail = new VecInt();
    	for(int i=0;i<15;i++)
    		trail.push(i);
    	try {
    		maxWatchCard.calcReasonOnTheFly(ILits.UNDEFINED, trail, trail);
    	} catch (UnsupportedOperationException e) {
    		assertEquals("Not implemented yet!", e.getMessage());
    	}
    	assertEquals(15, trail.size());
    }

    public void testCalcReasonOnTheFlyExample3() throws Exception {
    	IVecInt trail = new VecInt();
    	trail.push(0);
    	trail.push(1);
    	
    	voc.satisfies(0);
    	voc.satisfies(1);
    	MaxWatchCard example = new MaxWatchCard(voc, ps, true, 0);
    	
    	try {
    	example.calcReasonOnTheFly(ILits.UNDEFINED, trail, trail);
    	} catch (UnsupportedOperationException e) {
    		assertEquals("Not implemented yet!", e.getMessage());
    	}
    	assertEquals(2, trail.size());
    }
    
    public void testGetActivity() throws Exception{
    	assertEquals(0.0, maxWatchCard.getActivity());
    }
    
    public void testLearnt() throws Exception{
    	assertFalse(maxWatchCard.learnt());
    }
    
    public void testLocked() throws Exception{
    	assertTrue(maxWatchCard.locked());
    }
    
    public void testMaxWatchCardNewExample1() throws Exception{
    	ps.clear();
    	ps.push(0);
    	ps.push(1);
    	
    	Constr expected = MaxWatchCard.maxWatchCardNew(s, voc, ps, true, 0);
    	assertNull(expected); 
    }
    
    public void testMaxWatchCardNewExample2() throws Exception{
    	ps.clear();
    	ps.push(0);
    	ps.push(1);

    	try {
    		MaxWatchCard.maxWatchCardNew(s, voc, ps, false, 0);
    	} catch (Exception e){
    		
    	}
    }
    
    public void testMaxWatchCardNewExample3() throws Exception{
    	ps.clear();
    	ps.push(0);
    	ps.push(1);

    	try {
    		MaxWatchCard.maxWatchCardNew(s, voc, ps, false, 2);
    	} catch (Exception e) {
    		assertEquals("Contradiction with implied literal", e.getMessage());
    	}
    
    }
    
    public void testMaxWatchCardNewExample4() throws Exception{
    	ps.clear();
    	ps.push(0);
    	ps.push(1);

    	try {
    		MaxWatchCard.maxWatchCardNew(s, voc, ps, false, 6);
    	} catch (Exception e) {
    		assertEquals("Creating trivially inconsistent constraint", e.getMessage());
    	}
    
    }
    
    public void testMaxWatchCardNewExample5() throws Exception{
    	ps.clear();

    	Constr expected = MaxWatchCard.maxWatchCardNew(s, voc, ps, true, 0);

    	assertEquals(UnitClauses.class, expected.getClass());
    
    }
    
    
    public void testSimplify() throws Exception{
    	assertFalse(maxWatchCard.simplify());
    }
    
    public void testToStringExample1() throws Exception{
    	assertTrue(maxWatchCard.toString().contains("+ -2"));
    }
    
    public void testToStringExample2() throws Exception{
    	IVecInt literals = new VecInt();
    	literals.push(0);
    	literals.push(1);
    	
    	MaxWatchCard card = new MaxWatchCard(voc, literals, true, 0);
    	assertEquals("", card.toString());
    }
    
    public void testGet() throws Exception {
    	assertEquals(5, maxWatchCard.get(0));
    }
    public void testSetLearnt() throws Exception{
    	try {
    		maxWatchCard.setLearnt();
    	} catch(UnsupportedOperationException ex) {
    		
    	}
    }
    
    public void testRegister() throws Exception{
    	try {
    		maxWatchCard.register();
    	} catch(UnsupportedOperationException ex) {
    		
    	}
    }
    
    public void testAssertConstraint() throws Exception{
    	try {
    		maxWatchCard.assertConstraint(s);
    	} catch(UnsupportedOperationException ex) {
    		
    	}
    }
    
    public void testSize() throws Exception{
    	assertEquals(100, maxWatchCard.size());
    }
    
    public void testGetCoef() throws Exception{
    	assertEquals(BigInteger.valueOf(1), maxWatchCard.getCoef(4));
    }
    
    public void testDegree() throws Exception{
    	assertEquals(BigInteger.valueOf(0), maxWatchCard.getDegree());
    }
    
    public void testVocabulary() throws Exception{
    	ILits expected = maxWatchCard.getVocabulary();
    	
    	assertEquals(expected, voc);
    }
    
    public void testCanBePropogatedMultipleTimes() throws Exception{
    	assertTrue(maxWatchCard.canBePropagatedMultipleTimes());
    }
    
    public void testContraint() throws Exception {
    	Constr expected = maxWatchCard.toConstraint();
    	assertEquals(100, expected.size());
    }
    
    public void testNormalize() throws Exception {
    	IVecInt argu1 = new VecInt();
        ILits argu2 = new Lits();
        
        for(int i=0;i<100;i++)
        	argu1.push(5);
        argu2.ensurePool(200);
        MaxWatchCard card = new MaxWatchCard(argu2, argu1, false, 3);
        
    	card.normalize();
    	
    	assertEquals(BigInteger.valueOf(97), card.getDegree());
    }
    
    public void testPropogateExample1() throws Exception{
    	IVecInt argu1 = new VecInt();
        ILits argu2 = new Lits();
        
        for(int i=0;i<100;i++)
        	argu1.push(5);
        argu2.ensurePool(200);
        MaxWatchCard card = new MaxWatchCard(argu2, argu1, true, 100);
      
    	assertFalse(card.propagate(s, 67));

    }
    
    public void testPropogateExample2() throws Exception{
    	IVecInt argu1 = new VecInt();
        ILits argu2 = new Lits();
        
        for(int i=0;i<100;i++)
        	argu1.push(5);

        argu2.ensurePool(200);
        MaxWatchCard card = new MaxWatchCard(argu2, argu1, true, 99);
    	assertTrue(card.propagate(s, 67));

    }
    
    public void testPropogateExample3() throws Exception{
    	assertTrue(maxWatchCard.propagate(s, 67));
    }

    public void testUndo() throws Exception {
    	Field f = maxWatchCard.getClass().getDeclaredField( "watchCumul" );
		f.setAccessible( true );
		int watchCumul = (Integer)f.get( maxWatchCard );
		
		assertEquals(100, watchCumul);
	
		maxWatchCard.undo(67);
		
		Field field = maxWatchCard.getClass().getDeclaredField( "watchCumul" );
		field.setAccessible( true );
		int watchCumulNew = (Integer)field.get( maxWatchCard );
		
		assertEquals(101, watchCumulNew);
    }
}
