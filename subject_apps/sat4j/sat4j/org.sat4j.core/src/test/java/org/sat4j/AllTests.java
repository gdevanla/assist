package org.sat4j;

import org.sat4j.*;
import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {
	  public static Test suite() {
		  TestSuite suite = new TestSuite("Test for org.sat4j");
		  
		  suite.addTestSuite(ExitCodeTest.class);
		  suite.addTestSuite(LightFactoryTest.class);
		  suite.addTestSuite(MessagesTest.class);
		  suite.addTestSuite(MoreThanSATTest.class);
		  suite.addTestSuite(BasicLauncherTest.class);
		  suite.addTestSuite(MUSLauncherTest.class);
		  
		  return suite;
	  }
	
}
