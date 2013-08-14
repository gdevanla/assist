package test.jdepend;

import jdepend.ClassFileParser;
import jdepend.JavaClass;
import jdepend.JavaPackage;

import java.io.File;
import java.util.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Tests the file JavaPackage.java
 * @author <b>Mike Clark</b> 
 * @author Clarkware Consulting, Inc.
 */
 
public class CollectAllCyclesTest extends JDependTestCase {

	public CollectAllCyclesTest(String name) {
		super(name);
	}
	
	public void testNoCycles() {
	
		JavaPackage a = new JavaPackage("A");
		JavaPackage b = new JavaPackage("B");
				
		a.dependsUpon(b);
						
		List aCycles = new ArrayList();
		assertEquals(false, a.containsCycle());
		assertEquals(false, a.collectAllCycles(aCycles));
		assertListEquals(aCycles, new String[] {});	
	
		List bCycles = new ArrayList();
		assertEquals(false, b.containsCycle());
		assertEquals(false, b.collectAllCycles(bCycles));
		assertListEquals(bCycles, new String[] {});	
	}
	
	public void test2Node1BranchCycle() {
	
		JavaPackage a = new JavaPackage("A");
		JavaPackage b = new JavaPackage("B");
		
		a.dependsUpon(b);
		b.dependsUpon(a);
						
		List aCycles = new ArrayList();
		assertEquals(true, a.containsCycle());
		assertEquals(true, a.collectAllCycles(aCycles));
		assertListEquals(aCycles, new String[] {"A", "B", "A"});	
	
		List bCycles = new ArrayList();
		assertEquals(true, b.containsCycle());
		assertEquals(true, b.collectAllCycles(bCycles));
		assertListEquals(bCycles, new String[] {"B", "A", "B"});	
	}
	
	public void test3Node1BranchCycle() {
	
		JavaPackage a = new JavaPackage("A");
		JavaPackage b = new JavaPackage("B");
		JavaPackage c = new JavaPackage("C");
				
		a.dependsUpon(b);
		b.dependsUpon(c);
		c.dependsUpon(a);
		
				
		List aCycles = new ArrayList();
		assertEquals(true, a.containsCycle());
		assertEquals(true, a.collectAllCycles(aCycles));
		assertListEquals(aCycles, new String[] {"A", "B", "C", "A"});	
		
		List bCycles = new ArrayList();
		assertEquals(true, b.containsCycle());
		assertEquals(true, b.collectAllCycles(bCycles));
		assertListEquals(bCycles, new String[] {"B", "C", "A", "B"});
		
		List cCycles = new ArrayList();
		assertEquals(true, c.containsCycle());
		assertEquals(true, c.collectAllCycles(cCycles));
		assertListEquals(cCycles, new String[] {"C", "A", "B", "C"});
	}
	
	public void test3Node1BranchSubCycle() {
	
		JavaPackage a = new JavaPackage("A");
		JavaPackage b = new JavaPackage("B");
		JavaPackage c = new JavaPackage("C");
				
		a.dependsUpon(b);
		b.dependsUpon(c);
		c.dependsUpon(b);
		
		List aCycles = new ArrayList();
		assertEquals(true, a.containsCycle());
		assertEquals(true, a.collectAllCycles(aCycles));
		assertListEquals(aCycles, new String[] {"A", "B", "C", "B"});
	
		List bCycles = new ArrayList();
		assertEquals(true, b.containsCycle());
		assertEquals(true, b.collectAllCycles(bCycles));
		assertListEquals(bCycles, new String[] {"B", "C", "B"});
		
		List cCycles = new ArrayList();
		assertEquals(true, c.containsCycle());
		assertEquals(true, c.collectAllCycles(cCycles));
		assertListEquals(cCycles, new String[] {"C", "B", "C"});
	}

	public void test3Node2BranchCycle() {
	
		JavaPackage a = new JavaPackage("A");
		JavaPackage b = new JavaPackage("B");
		JavaPackage c = new JavaPackage("C");
		
		a.dependsUpon(b);
		b.dependsUpon(a);		
		
		a.dependsUpon(c);
		c.dependsUpon(a);
						
		List aCycles = new ArrayList();
		assertEquals(true, a.containsCycle());
		assertEquals(true, a.collectAllCycles(aCycles));
		assertListEquals(aCycles, new String[] {"A", "B", "A", "C", "A"});	
	
		List bCycles = new ArrayList();
		assertEquals(true, b.containsCycle());
		assertEquals(true, b.collectAllCycles(bCycles));
		assertListEquals(bCycles, new String[] {"B", "A", "B", "C", "A"});	
		
		List cCycles = new ArrayList();
		assertEquals(true, c.containsCycle());
		assertEquals(true, c.collectAllCycles(cCycles));
		assertListEquals(cCycles, new String[] {"C", "A", "B", "A", "C"});
	}
	
	public void test5Node2BranchCycle() {
	
		JavaPackage a = new JavaPackage("A");
		JavaPackage b = new JavaPackage("B");
		JavaPackage c = new JavaPackage("C");
		JavaPackage d = new JavaPackage("D");
		JavaPackage e = new JavaPackage("E");
		
		a.dependsUpon(b);
		b.dependsUpon(c);
		c.dependsUpon(a);		
		
		a.dependsUpon(d);
		d.dependsUpon(e);
		e.dependsUpon(a);
				
		List aCycles = new ArrayList();
		assertEquals(true, a.containsCycle());
		assertEquals(true, a.collectAllCycles(aCycles));
		assertListEquals(aCycles, new String[] {"A", "B", "C", "A", "D", "E", "A"});	
		
		List bCycles = new ArrayList();
		assertEquals(true, b.containsCycle());
		assertEquals(true, b.collectAllCycles(bCycles));
		assertListEquals(bCycles, new String[] {"B", "C", "A", "B", "D", "E", "A"});
		
		List cCycles = new ArrayList();
		assertEquals(true, c.containsCycle());
		assertEquals(true, c.collectAllCycles(cCycles));
		assertListEquals(cCycles, new String[] {"C", "A", "B", "C", "D", "E", "A"});
		
		List dCycles = new ArrayList();
		assertEquals(true, d.containsCycle());
		assertEquals(true, d.collectAllCycles(dCycles));
		assertListEquals(dCycles, new String[] {"D", "E", "A", "B", "C", "A" , "D"});
		
		List eCycles = new ArrayList();
		assertEquals(true, e.containsCycle());
		assertEquals(true, e.collectAllCycles(eCycles));
		assertListEquals(eCycles, new String[] {"E", "A", "B", "C", "A", "D", "E"});
	}
	
	protected void assertListEquals(List list, String names[]) {
	
		assertEquals(names.length, list.size());
		
		for (int i=0; i < names.length; i++) {
			assertEquals(names[i], ((JavaPackage)list.get(i)).getName());
		}
	}
	/**
	 * @author icewariya
	 */
	public void testVolatility() {
		JavaPackage a = new JavaPackage("A", 1);
		assertEquals(1, a.getVolatility());
	}
	
	public void testAddClass() throws Exception{
		JavaPackage a = new JavaPackage("A", 1);
		
		 File f = new File(getBuildDir() + getPackageSubDir() +
                 "ExampleAbstractClass.class");
		 ClassFileParser parser = new ClassFileParser();
		 JavaClass clazz = parser.parse(f);
		 
		 a.addClass(clazz);
		 Collection collection = a.getClasses();
		 
		 assertTrue(collection.contains(clazz));
	}
	
	public void testGetClassCount() throws Exception{
		JavaPackage a = new JavaPackage("A", 1);
		
		 File f = new File(getBuildDir() + getPackageSubDir() +
                 "ExampleConcreteClass.class");
		 ClassFileParser parser = new ClassFileParser();
		 JavaClass clazz = parser.parse(f);
		 
		 a.addClass(clazz);
		 
		 assertEquals(1, a.getClassCount());
	}
	
	public void testGetAbstractClassCount() throws Exception{
		JavaPackage a = new JavaPackage("A", 1);
		
		ClassFileParser parser = new ClassFileParser();
		 File f1 = new File(getBuildDir() + getPackageSubDir() +
                 "ExampleConcreteClass.class");
		 JavaClass clazz1 = parser.parse(f1);
		 
		 File f2 = new File(getBuildDir() + getPackageSubDir() +
                 "ExampleAbstractClass.class");
		 JavaClass clazz2 = parser.parse(f2);
		 
		 a.addClass(clazz1);
		 a.addClass(clazz2);
		 
		 assertEquals(1, a.getAbstractClassCount());
		 assertEquals(1, a.getConcreteClassCount());
	}
	
	public void testAddAfferents() throws Exception{
		JavaPackage a = new JavaPackage("A");
		JavaPackage b = new JavaPackage("B");
		
		a.addAfferent(b);
		a.addAfferent(a);
		a.addAfferent(b);
		
		assertEquals(1, a.getAfferents().size());
		assertEquals(0, b.getAfferents().size());
		
	}
	
	public void testSetAfferents() throws Exception{
		JavaPackage a = new JavaPackage("A");
		JavaPackage b = new JavaPackage("B");
		
		List afferents = new ArrayList();
		afferents.add(a);
		afferents.add(b);
		
		a.setAfferents(afferents);
		
		assertEquals(2, a.getAfferents().size());
		assertEquals(0, b.getAfferents().size());
		
	}
	
	public void testAddEfferents() throws Exception{
		JavaPackage a = new JavaPackage("A");
		JavaPackage b = new JavaPackage("B");
		
		a.addEfferent(b);
		a.addEfferent(a);
		a.addEfferent(b);
		
		assertEquals(1, a.getEfferents().size());
		assertEquals(0, b.getEfferents().size());
		
	}
	
	public void testSetEfferents() throws Exception{
		JavaPackage a = new JavaPackage("A");
		JavaPackage b = new JavaPackage("B");
		
		List efferents = new ArrayList();
		efferents.add(a);
		efferents.add(b);
		
		a.setEfferents(efferents);
		
		assertEquals(2, a.getEfferents().size());
		assertEquals(0, b.getEfferents().size());
		
	}
	
	public void testAfferentCoupling() throws Exception{
		JavaPackage a = new JavaPackage("A");
		JavaPackage b = new JavaPackage("B");
		
		List afferents = new ArrayList();
		afferents.add(a);
		afferents.add(b);
		
		a.setAfferents(afferents);
		
		assertEquals(2, a.afferentCoupling());
		assertEquals(0, b.afferentCoupling());
		assertEquals(0, a.efferentCoupling());
		
	}
	
	public void testEfferentCoupling() throws Exception{
		JavaPackage a = new JavaPackage("A");
		JavaPackage b = new JavaPackage("B");
		
		List efferents = new ArrayList();
		efferents.add(a);
		efferents.add(b);
		
		a.setEfferents(efferents);
		
		assertEquals(2, a.efferentCoupling());
		assertEquals(0, b.efferentCoupling());
		assertEquals(0, a.afferentCoupling());
		
	}
	
	public void testInstabilityExample1() throws Exception{
		JavaPackage a = new JavaPackage("A");
		JavaPackage b = new JavaPackage("B");
		float expectedInstability = (float) 1.0;
		
		List efferents = new ArrayList();
		efferents.add(a);
		efferents.add(b);
		
		a.setEfferents(efferents);
		
		assertEquals(expectedInstability, a.instability());
	}
	
	public void testInstabilityExample2() throws Exception{
		JavaPackage a = new JavaPackage("A");
		JavaPackage b = new JavaPackage("B");
		float expectedInstability = (float) 0.5;
		
		List efferents = new ArrayList();
		efferents.add(a);
		efferents.add(b);
		a.setEfferents(efferents);
		
		List afferents = new ArrayList();
		afferents.add(a);
		afferents.add(b);
		a.setAfferents(afferents);
		
		assertEquals(expectedInstability, a.instability());
	}
	
	public void testInstabilityExample3() throws Exception{
		JavaPackage a = new JavaPackage("A");
		JavaPackage b = new JavaPackage("B");
		float expectedInstability = (float) 0;
		
		assertEquals(expectedInstability, a.instability());
	}
	
	public void testAbstractnessExample1() throws Exception{
		JavaPackage a = new JavaPackage("A", 1);
		
		ClassFileParser parser = new ClassFileParser();
		 File f1 = new File(getBuildDir() + getPackageSubDir() +
                 "ExampleConcreteClass.class");
		 JavaClass clazz1 = parser.parse(f1);
		 
		 File f2 = new File(getBuildDir() + getPackageSubDir() +
                 "ExampleAbstractClass.class");
		 JavaClass clazz2 = parser.parse(f2);
		 
		 a.addClass(clazz1);
		 a.addClass(clazz2);
		 
		 assertEquals((float)0.5, a.abstractness());
	}
	
	public void testAbstractnessExample2() throws Exception{
		JavaPackage a = new JavaPackage("A");
		 
		 assertEquals((float)0.0, a.abstractness());
	}
	
	public void testDistanceExample1() throws Exception{
		JavaPackage a = new JavaPackage("A", 1);
		
		ClassFileParser parser = new ClassFileParser();
		 File f1 = new File(getBuildDir() + getPackageSubDir() +
                 "ExampleConcreteClass.class");
		 JavaClass clazz1 = parser.parse(f1);
		 
		 File f2 = new File(getBuildDir() + getPackageSubDir() +
                 "ExampleAbstractClass.class");
		 JavaClass clazz2 = parser.parse(f2);
		 
		 a.addClass(clazz1);
		 a.addClass(clazz2);
		 
		 assertEquals((float)0.5, a.distance());
	}
	
	public void testDistanceExample2() throws Exception{
		JavaPackage a = new JavaPackage("C");
		 
		 assertEquals((float)1.0, a.distance());
	}
	
	public void testEquals() throws Exception{
		JavaPackage a = new JavaPackage("A");
		ClassFileParser cp = new ClassFileParser();
		assertFalse(a.equals(cp));
		assertTrue(a.equals(a));
	}
	
	public void testHashCode() throws Exception{
		JavaPackage a = new JavaPackage("A");
		int hashcode = a.hashCode();
		assertEquals(hashcode, a.hashCode());
	}
}
