package test.jdepend;

import java.lang.reflect.Field;

import jdepend.ClassFileParser;
import jdepend.FileManager;
import jdepend.PackageComparator;
/**
 * 
 * @author icewariya
 *
 */
public class PackageComparatorTest extends JDependTestCase{
    
	private PackageComparator packageComparator1;

	public PackageComparatorTest(String name) {
		super(name);
	}

	protected void setUp() {
		super.setUp();
		PackageComparator packageComparator2 = null;
		packageComparator1 = new PackageComparator(packageComparator2);
	}
	protected void tearDown() {
		super.tearDown();
	}
	
	public void testPackageCompByName() throws Exception{
		
		Field byName = packageComparator1.getClass().getDeclaredField("byName");
		byName.setAccessible(true);
		PackageComparator expectedComp = (PackageComparator) byName.get(packageComparator1);
		
		assertEquals(expectedComp, packageComparator1.byName());
	}
	
	public void testPackageCompByWhat() throws Exception{
		
		Field byWhat = packageComparator1.getClass().getDeclaredField("byWhat");
		byWhat.setAccessible(true);
		PackageComparator expectedComp = (PackageComparator) byWhat.get(packageComparator1);
		
		assertEquals(expectedComp, packageComparator1.byWhat());
	}
	
	public void testCompareExample1() throws Exception{
		
		Object p1 = new jdepend.JavaPackage("A");
		Object p2 = new jdepend.JavaPackage("B");
		
		assertEquals(0,packageComparator1.compare(p1, p2));
	}
	
	public void testCompareExample2() throws Exception{
		
		Field byName = packageComparator1.getClass().getDeclaredField("byName");
		byName.setAccessible(true);
		PackageComparator expectedComp = (PackageComparator) byName.get(packageComparator1);
		
		PackageComparator compareComp = new PackageComparator(expectedComp);
		Object p1 = new jdepend.JavaPackage("A");
		Object p2 = new jdepend.JavaPackage("B");
		
		assertEquals(-1,compareComp.compare(p1, p2));
		assertEquals(0,compareComp.compare(p1, p1));
	}
}
