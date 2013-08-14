package org.sat4j.tools;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BackboneTest.class, ConflictDepthTracingTest.class,
		ConflictLevelTracingTest.class, DecisionLevelTracingTest.class,
		DecisionTracingTest.class, DimacsArrayReaderTest.class,
		DimacsOutputSolverTest.class, DimacsStringSolverTest.class,
		ExtendedDimacsArrayReaderTest.class,
		FileBasedVisualizationToolTest.class, HeuristicsTracingTest.class,
		LBDTracingTest.class, LearnedClauseSizeTracingTest.class,
		LearnedClausesSizeTracingTest.class, LearnedTracingTest.class,
		LexicoDecoratorTest.class, Minimal4CardinalityModelTest.class,
		MultiTracingTest.class, NegationDecoratorTest.class,
		OptToSatAdapterTest.class, RemiUtilsTest.class,
		SearchEnumeratorListenerTest.class, SearchMinOneListenerTest.class,
		SingleSolutionDetectorTest.class, SolutionCounterTest.class,
		SpeedTracingTest.class, StatisticsSolverTest.class, TestAllMUSes.class,
		TestAllMUSesAndCheckTest.class, TestAllMUSesGroupTest.class,
		TestCheckItIsAMUS.class, TestClausalCardinalitiesBinaryEncoding.class,
		TestClausalCardinalitiesBinomialEncoding.class,
		TestClausalCardinalitiesCommanderEncoding.class,
		TestClausalCardinalitiesLadderEncoding.class,
		TestClausalCardinalitiesProductEncoding.class,
		TestClausalCardinalitiesSequentialEncoding.class,
		TestGateTranslator.class, TextOutputTracingTest.class })
public class AllTestsTools {

}
