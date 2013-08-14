package org.sat4j.core;

import org.sat4j.*;
import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {
	  public static Test suite() {
		  TestSuite suite = new TestSuite("Test for org.sat4j.core");
		  
		  suite.addTestSuite(VecTest.class);
		  suite.addTestSuite(VecIntTest.class);
		  suite.addTestSuite(DefaultComparatorTest.class);
		  suite.addTestSuite(TestConstrGroup.class);
		  suite.addTestSuite(ReadOnlyVecTest.class);
		  suite.addTestSuite(ReadOnlyVecIntTest.class);
		  suite.addTestSuite(LiteralsUtilsTest.class);
		  
		  return suite;
	  }
	
}
