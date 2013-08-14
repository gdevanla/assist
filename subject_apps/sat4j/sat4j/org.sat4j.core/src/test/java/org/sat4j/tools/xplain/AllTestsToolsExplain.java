package org.sat4j.tools.xplain;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ SingleSolutionTest.class, TestConstrGroup.class,
		TestDeletionXplain.class, TestFreeId.class, TestInsertionXplain.class,
		TestQuickXplain.class, TestQuickXplain2001.class, TestSatAssumps.class,
		TestSolverEngine.class })
public class AllTestsToolsExplain {

}
