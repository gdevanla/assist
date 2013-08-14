package org;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ AllTestsTools.class, AllTestsToolsExplain.class,
		UnitTests.class })
public class AllUnitTests {

}
