package org.sat4j.minisat.constraints.card;

import java.lang.reflect.Field;
import java.math.BigInteger;

import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.minisat.constraints.cnf.Lits;
import org.sat4j.minisat.constraints.cnf.UnitClauses;
import org.sat4j.minisat.core.Constr;
import org.sat4j.minisat.core.ILits;
import org.sat4j.minisat.core.UnitPropagationListener;
import org.sat4j.specs.IVecInt;

import junit.framework.TestCase;

public class MinWatchCardTest extends TestCase{
	private MinWatchCard minWatchCard;
    private IVecInt ps = new VecInt();
    private ILits voc = new Lits();
	private UnitPropagationListener s = (UnitPropagationListener) SolverFactory.newDefault();
	
	public MinWatchCardTest(String arg0) {
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
        this.minWatchCard = new MinWatchCard(voc, ps, true, 0);
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testMinWatchCard() throws Exception {        
    	MinWatchCard card = new MinWatchCard(voc, ps, 0);
    	
    	assertEquals(0, card.getLits().length);
    }
    public void testCalcReasonExample1() throws Exception{
    	IVecInt reason = new VecInt();
    	for(int i=67;i<100;i++)
    		reason.push(i);
    	voc.satisfies(5);
    	minWatchCard.calcReason(0, reason);
    	
    	assertEquals(33, reason.size());
    }
    
    public void testCalcReasonExample2() throws Exception{
    	IVecInt trail = new VecInt();
    	for(int i=0;i<15;i++) 
    		trail.push(i);
    	voc.forgets(2);
    	minWatchCard.calcReason(ILits.UNDEFINED, trail);
    	assertEquals(115, trail.size());
    }

    public void testCalcReasonExample3() throws Exception{   	
    	IVecInt trail = new VecInt();
    	trail.push(0);
    	trail.push(1);
    	
    	MinWatchCard example = new MinWatchCard(voc, trail, true, 0);

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
    	MinWatchCard example = new MinWatchCard(voc, literals, false, 0);
    	
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
    		minWatchCard.calcReasonOnTheFly(0, trail, reason);
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
    		minWatchCard.calcReasonOnTheFly(ILits.UNDEFINED, trail, trail);
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
    	MinWatchCard example = new MinWatchCard(voc, ps, true, 0);
    	
    	try {
    	example.calcReasonOnTheFly(ILits.UNDEFINED, trail, trail);
    	} catch (UnsupportedOperationException e) {
    		assertEquals("Not implemented yet!", e.getMessage());
    	}
    	assertEquals(2, trail.size());
    }
    
    public void testGetActivity() throws Exception{
    	assertEquals(0.0, minWatchCard.getActivity());
    }
    
    public void testLearnt() throws Exception{
    	assertFalse(minWatchCard.learnt());
    }
    
    public void testLocked() throws Exception{
    	assertTrue(minWatchCard.locked());
    }
    
    public void testminWatchCardNewExample1() throws Exception{
    	ps.clear();
    	ps.push(0);
    	ps.push(1);
    	
    	Constr expected = minWatchCard.minWatchCardNew(s, voc, ps, true, 0);
    	assertNull(expected); 
    }
    
    public void testminWatchCardNewExample2() throws Exception{
    	ps.clear();
    	ps.push(0);
    	ps.push(1);

    	try {
    		minWatchCard.minWatchCardNew(s, voc, ps, false, 0);
    	} catch (Exception e){
    		
    	}
    }
    
    public void testminWatchCardNewExample3() throws Exception{
    	ps.clear();
    	ps.push(0);
    	ps.push(1);

    	try {
    		minWatchCard.minWatchCardNew(s, voc, ps, false, 2);
    	} catch (Exception e) {

    	}
    
    }
    
    public void testminWatchCardNewExample4() throws Exception{
    	ps.clear();
    	ps.push(0);
    	ps.push(1);

    	try {
    		minWatchCard.minWatchCardNew(s, voc, ps, false, 6);
    	} catch (Exception e) {

    	}
    
    }
    
    public void testminWatchCardNewExample5() throws Exception{
    	ps.clear();

    	Constr expected = minWatchCard.minWatchCardNew(s, voc, ps, true, 0);

    	assertEquals(UnitClauses.class, expected.getClass());
    
    }
    
    
    public void testSimplify() throws Exception{
    	assertFalse(minWatchCard.simplify());
    }
    
    public void testToStringExample1() throws Exception{
    	assertTrue(minWatchCard.toString().contains("+ -2"));
    }
    
    public void testToStringExample2() throws Exception{
    	IVecInt literals = new VecInt();
    	literals.push(0);
    	literals.push(1);
    	
    	MinWatchCard card = new MinWatchCard(voc, literals, true, 0);
    	assertEquals("Card (0) : ", card.toString());
    }
    
    public void testGet() throws Exception {
    	assertEquals(5, minWatchCard.get(0));
    }
    public void testSetLearnt() throws Exception{
    	try {
    		minWatchCard.setLearnt();
    	} catch(UnsupportedOperationException ex) {
    		
    	}
    }
    
    public void testRegister() throws Exception{
    	try {
    		minWatchCard.register();
    	} catch(UnsupportedOperationException ex) {
    		
    	}
    }
    
    public void testAssertConstraint() throws Exception{
    	try {
    		minWatchCard.assertConstraint(s);
    	} catch(UnsupportedOperationException ex) {
    		
    	}
    }
    
    public void testSize() throws Exception{
    	assertEquals(100, minWatchCard.size());
    }
    
    public void testGetLits() throws Exception{
    	assertEquals(100, minWatchCard.getLits().length);
    }
    
    public void testVocabulary() throws Exception{
    	ILits expected = minWatchCard.getVocabulary();
    	
    	assertEquals(expected, voc);
    }
    
    public void testCanBePropogatedMultipleTimes() throws Exception{
    	assertTrue(minWatchCard.canBePropagatedMultipleTimes());
    }
    
    public void testContraint() throws Exception {
    	Constr expected = minWatchCard.toConstraint();
    	assertEquals(100, expected.size());
    }
    
    public void testNormalize() throws Exception {
    	IVecInt argu1 = new VecInt();
        ILits argu2 = new Lits();
        
        for(int i=0;i<100;i++)
        	argu1.push(5);
        argu2.ensurePool(200);
        MinWatchCard card = new MinWatchCard(argu2, argu1, false, 3);
        
    	card.normalize();
    	
    	assertEquals(100, card.size());
    }
    
    public void testPropogateExample1() throws Exception{
    	IVecInt argu1 = new VecInt();
        ILits argu2 = new Lits();
        
        for(int i=0;i<10;i++)
        	argu1.push(10);
        argu2.ensurePool(500);
        
        MinWatchCard card = new MinWatchCard(argu2, argu1, 0);
      
    	assertFalse(card.propagate(s, 0));
    }
    
    public void testPropogateExample2() throws Exception{
      	IVecInt argu1 = new VecInt();
        ILits argu2 = new Lits();
        
        for(int i=0;i<10;i++)
        	argu1.push(1);
        
        argu2.ensurePool(20);
        
        MinWatchCard card = new MinWatchCard(argu2, argu1, 1);
      
    	assertTrue(card.propagate(s, 0));

    }

    public void testPropogateExample3() throws Exception{
      	IVecInt argu1 = new VecInt();
        ILits argu2 = new Lits();
        
        for(int i=0;i<20;i++)
        	argu1.push(i);
        
        argu2.ensurePool(20);
        
        MinWatchCard card = new MinWatchCard(argu2, argu1, 1);

    	assertTrue(card.propagate(s, 5));

    }
    
    public void testPropogateExample4() throws Exception{
      	IVecInt argu1 = new VecInt();
        ILits argu2 = new Lits();
        
        for(int i=0;i<20;i++)
        	argu1.push(i);
        
        argu2.ensurePool(20);
        
        MinWatchCard card = new MinWatchCard(argu2, argu1, 19);

    	assertFalse(card.propagate(s, 5));

    }
    
    public void testSimplifyExample1() throws Exception{
    	IVecInt argu1 = new VecInt();
        ILits argu2 = new Lits();
        
        for(int i=0;i<10;i++) {
        	argu1.push(1);
        	argu2.satisfies(i);
        }
        argu2.ensurePool(20);
        
        MinWatchCard card = new MinWatchCard(argu2, argu1, 1);
        
    	assertTrue(card.simplify());
    }
    
    public void testSimplifyExample2() throws Exception{
      	IVecInt argu1 = new VecInt();
        ILits argu2 = new Lits();
        
        for(int i=0;i<10;i++)
        	argu1.push(i);
        
        argu2.ensurePool(20);
        
        MinWatchCard card = new MinWatchCard(argu2, argu1, 1);
      
    	assertFalse(card.simplify());

    }
    
    public void testSimplifyExample3() throws Exception{
      	IVecInt argu1 = new VecInt();
        ILits argu2 = new Lits();
        
        for(int i=0;i<10;i++) {
        	argu1.push(i);
        	argu2.satisfies(i);
        }
        argu2.ensurePool(20);
        
        MinWatchCard card = new MinWatchCard(argu2, argu1, 0);
      
    	assertFalse(card.simplify());

    }
    
    public void testSimplifyExample4() throws Exception {
    	assertFalse(minWatchCard.simplify());
    }
    
    public void testComputeWatchesExample1() throws Exception{
    	IVecInt argu1 = new VecInt();
        ILits argu2 = new Lits();
        
        for(int i=0;i<10;i++) {
        	argu1.push(1);
        	argu2.satisfies(i);
        }
        argu2.ensurePool(20);
        
        MinWatchCard card = new MinWatchCard(argu2, argu1, 1);
        
    	card.computeWatches();
    }
    
    public void testComputeWatchesExample2() throws Exception{
      	IVecInt argu1 = new VecInt();
        ILits argu2 = new Lits();
        
        for(int i=0;i<10;i++)
        	argu1.push(i);
        
        argu2.ensurePool(20);
        
        MinWatchCard card = new MinWatchCard(argu2, argu1, 1);
      
        card.computeWatches();

    }
    
    public void testComputeWatchesExample3() throws Exception{
      	IVecInt argu1 = new VecInt();
        ILits argu2 = new Lits();
        
        for(int i=0;i<10;i++) {
        	argu1.push(i);
        	argu2.satisfies(i);
        }
        argu2.ensurePool(20);
        
        MinWatchCard card = new MinWatchCard(argu2, argu1, 0);
      
        card.computeWatches();

    }
    
    public void testComputeWatchesExample4() throws Exception {
    	minWatchCard.computeWatches();
    }
    
    public void testComputePropagationExample1() throws Exception{
    	IVecInt argu1 = new VecInt();
        ILits argu2 = new Lits();
        
        for(int i=0;i<10;i++) {
        	argu1.push(1);
        	argu2.satisfies(i);
        }
        argu2.ensurePool(20);
        
        MinWatchCard card = new MinWatchCard(argu2, argu1, 1);
        try {
        	card.computePropagation(s);
        } catch (Exception e) {
        	
        }
    }
    
    public void testComputePropagationExample2() throws Exception{
      	IVecInt argu1 = new VecInt();
        ILits argu2 = new Lits();
        
        for(int i=0;i<10;i++)
        	argu1.push(i);
        
        argu2.ensurePool(20);
        
        MinWatchCard card = new MinWatchCard(argu2, argu1, 1);
        try {
        	card.computePropagation(s);
        } catch(Exception e) {
        	
        }

    }
    
    public void testComputePropagationExample3() throws Exception{
      	IVecInt argu1 = new VecInt();
        ILits argu2 = new Lits();
        
        for(int i=0;i<10;i++) {
        	argu1.push(i);
        	argu2.satisfies(i);
        }
        argu2.ensurePool(20);
        
        MinWatchCard card = new MinWatchCard(argu2, argu1, 0);
        try {
        	card.computePropagation(s);
        } catch (Exception e){
        	
        }

    }
    
    public void testComputePropagationExample4() throws Exception {
    	minWatchCard.computePropagation(s);
    	minWatchCard.undo(67);
    	minWatchCard.computePropagation(s);
    }
    
    public void testUndo() throws Exception {
    	Field f = minWatchCard.getClass().getDeclaredField( "watchCumul" );
		f.setAccessible( true );
		int watchCumul = (Integer)f.get( minWatchCard );
		
		assertEquals(0, watchCumul);
	
		minWatchCard.undo(67);
		
		Field field = minWatchCard.getClass().getDeclaredField( "watchCumul" );
		field.setAccessible( true );
		int watchCumulNew = (Integer)field.get( minWatchCard );
		
		assertEquals(1, watchCumulNew);
    }
    
    public void testHashCode() throws Exception {
    	assertEquals(4, minWatchCard.hashCode());
    }
    
    public void testEqualsExample1() throws Exception {
    	IVecInt argu1 = new VecInt();
        ILits argu2 = new Lits();
        
        for(int i=0;i<100;i++)
        	argu1.push(5);

        argu2.ensurePool(200);
        MinWatchCard card = new MinWatchCard(argu2, argu1, true, 0);
        
        assertTrue(minWatchCard.equals(card));
    }
    
    public void testEqualsExample2() throws Exception {
    	IVecInt argu1 = new VecInt();
        
        for(int i=0;i<100;i++)
        	argu1.push(5);
        
        assertFalse(minWatchCard.equals(argu1));
    }
    
    public void testEqualsExample3() throws Exception {
       	IVecInt argu1 = new VecInt();
        ILits argu2 = new Lits();
        
        for(int i=0;i<100;i++)
        	argu1.push(5);

        argu2.ensurePool(200);
        MinWatchCard card = new MinWatchCard(argu2, argu1, true, 5);
        
        assertFalse(minWatchCard.equals(card));
    }
    
    public void testEqualsExample4() throws Exception {
       	IVecInt argu1 = new VecInt();
        ILits argu2 = new Lits();
        
        for(int i=0;i<50;i++)
        	argu1.push(5);

        argu2.ensurePool(200);
        MinWatchCard card = new MinWatchCard(argu2, argu1, true, 0);
        
        assertFalse(minWatchCard.equals(card));
    }
    
    public void testEqualsExample5() throws Exception {        
        assertFalse(minWatchCard.equals(null));
    }
    
    public void testEqualsExample6() throws Exception {
    	IVecInt argu1 = new VecInt();
        ILits argu2 = new Lits();
        
        for(int i=0;i<100;i++)
        	argu1.push(10);

        argu2.ensurePool(200);
        MinWatchCard card = new MinWatchCard(argu2, argu1, true, 0);
        
        assertFalse(minWatchCard.equals(card));
    }
    
    public void testLinearisationExample1() throws Exception {
    	IVecInt argu1 = new VecInt();
        ILits argu2 = new Lits();
        
        for(int i=0;i<100;i++)
        	argu1.push(10);
        
        assertEquals(0, MinWatchCard.linearisation(argu2, argu1));
       
    }
    
    public void testLinearisationExample2() throws Exception {
    	IVecInt argu1 = new VecInt();
        ILits argu2 = new Lits();
        
        for(int i=0;i<100;i++) {
        	argu1.push(10);
        	argu2.satisfies(i);
        }
        
        assertEquals(0, MinWatchCard.linearisation(argu2, argu1));
    }
    
    public void testLinearisationExample3() throws Exception {
    	IVecInt argu1 = new VecInt();
        ILits argu2 = new Lits();
        
        for(int i=0;i<100;i++) {
        	argu1.push(10);
        	argu2.forgets(i);
        }
        
        assertEquals(-100, MinWatchCard.linearisation(argu2, argu1));
    }
}
