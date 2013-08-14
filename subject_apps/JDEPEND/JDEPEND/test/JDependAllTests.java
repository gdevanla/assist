package test;
import junit.framework.Test;
import junit.framework.TestSuite;
import test.jdepend.*;
import test.jdepend.framework.*;
/**
 * @author <b>Mike Clark</b>
 * @author Clarkware Consulting, Inc.
 * @modified icewariya
 */

public class JDependAllTests {

    public static Test suite() {

        TestSuite suite = new TestSuite("JDepend Unit and Acceptance Tests");
        
        // Unit Tests
        // Jdepend Package
        suite.addTestSuite(test.jdepend.ClassFileParserTest.class);
        suite.addTestSuite(test.jdepend.CollectAllCyclesTest.class);
        suite.addTestSuite(test.jdepend.ComponentTest.class);
        suite.addTestSuite(test.jdepend.ConstraintTest.class);
        suite.addTestSuite(test.jdepend.CycleTest.class);
        suite.addTestSuite(test.jdepend.ExampleTest.class);
        suite.addTestSuite(test.jdepend.FileManagerTest.class);
        suite.addTestSuite(test.jdepend.FilterTest.class);
        suite.addTestSuite(test.jdepend.JarFileParserTest.class); 
        suite.addTestSuite(test.jdepend.JavaClassTest.class); 
        suite.addTestSuite(test.jdepend.MetricTest.class);
        suite.addTestSuite(test.jdepend.PackageComparatorTest.class);
        suite.addTestSuite(test.jdepend.PropertyConfiguratorTest.class);

        // Jdepend Framework Package
        suite.addTestSuite(test.jdepend.framework.ClassFileParserTest.class);
        suite.addTestSuite(test.jdepend.framework.CollectAllCyclesTest.class);
        suite.addTestSuite(test.jdepend.framework.ComponentTest.class);
        suite.addTestSuite(test.jdepend.framework.ConstraintTest.class);
        suite.addTestSuite(test.jdepend.framework.CycleTest.class);
        suite.addTestSuite(test.jdepend.framework.ExampleTest.class);
        suite.addTestSuite(test.jdepend.framework.FileManagerTest.class);
        suite.addTestSuite(test.jdepend.framework.FilterTest.class);
        suite.addTestSuite(test.jdepend.framework.JarFileParserTest.class); 
        suite.addTestSuite(test.jdepend.framework.JavaClassTest.class); 
        suite.addTestSuite(test.jdepend.framework.MetricTest.class);
        suite.addTestSuite(test.jdepend.framework.PackageComparatorTest.class);
        suite.addTestSuite(test.jdepend.framework.PropertyConfiguratorTest.class);

        // Jdepend textui package
        suite.addTestSuite(jdepend.textui.JDependTest.class);
        
        // Jdepend xmlui package
        suite.addTestSuite(jdepend.xmlui.JDependTest.class);
        
        // Jdepend swingui package
        suite.addTestSuite(jdepend.swingui.AfferentNodeTest.class);
        suite.addTestSuite(jdepend.swingui.EfferentNodeTest.class);
        suite.addTestSuite(jdepend.swingui.DependTreeTest.class);
        suite.addTestSuite(jdepend.swingui.DependTreeModelTest.class);
        suite.addTestSuite(jdepend.swingui.AboutDialogTest.class);
        suite.addTestSuite(jdepend.swingui.StatusPanelTest.class);
        suite.addTestSuite(jdepend.swingui.JDependTest.class);
        
        // Acceptance tests
        suite.addTestSuite(test.JDependPackageAcceptanceTests.class);
        suite.addTestSuite(test.JDependFrameworkAcceptanceTests.class);
        suite.addTestSuite(test.JDependTextuiAcceptanceTests.class);
        suite.addTestSuite(test.JDependXmluiAcceptanceTests.class);
        suite.addTestSuite(test.JDependSwinguiAcceptanceTests.class);
        
        return suite;
    }
}