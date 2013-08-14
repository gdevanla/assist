package test;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * 
 * @author icewariya
 *
 */
public class JDependAcceptanceTests {
	public static Test suite() {

        TestSuite suite = new TestSuite("JDepend Acceptance Tests");
        
        suite.addTestSuite(test.JDependPackageAcceptanceTests.class);
        suite.addTestSuite(test.JDependFrameworkAcceptanceTests.class);
        suite.addTestSuite(test.JDependTextuiAcceptanceTests.class);
        suite.addTestSuite(test.JDependXmluiAcceptanceTests.class);
        suite.addTestSuite(test.JDependSwinguiAcceptanceTests.class);
        
        return suite;
	}
}
