package org;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.sat4j.BasicLauncherTest;
import org.sat4j.ExitCodeTest;
import org.sat4j.LightFactoryTest;
import org.sat4j.MUSLauncherTest;
import org.sat4j.MessagesTest;
import org.sat4j.MoreThanSATTest;
import org.sat4j.core.DefaultComparatorTest;
import org.sat4j.core.LiteralsUtilsTest;
import org.sat4j.core.ReadOnlyVecIntTest;
import org.sat4j.core.ReadOnlyVecTest;
import org.sat4j.core.TestConstrGroup;
import org.sat4j.core.VecIntTest;
import org.sat4j.core.VecTest;
import org.sat4j.minisat.GenericM2Test;
import org.sat4j.minisat.M2AtLeastTest;
import org.sat4j.minisat.M2BackjumpingTest;
import org.sat4j.minisat.M2CardMaxYannTest;
import org.sat4j.minisat.M2CardMinYannTest;
import org.sat4j.minisat.M2MiniLearningTest;
import org.sat4j.minisat.M2MiniSATTest;
import org.sat4j.minisat.TestAssertion;
import org.sat4j.minisat.TestsFonctionnels;
import org.sat4j.minisat.VarOrderTest;
import org.sat4j.minisat.constraints.CardinalityDataStructureTest;
import org.sat4j.minisat.constraints.CardinalityDataStructureYanMaxTest;
import org.sat4j.minisat.constraints.CardinalityDataStructureYanMinTest;
import org.sat4j.minisat.constraints.ClausalDataStructureWLTest;
import org.sat4j.minisat.constraints.LitTest;
import org.sat4j.minisat.constraints.MixedDataStructureDanielHTTest;
import org.sat4j.minisat.constraints.MixedDataStructureDanielWLTest;
import org.sat4j.minisat.constraints.MixedDataStructureSingleWLTest;
import org.sat4j.minisat.constraints.card.AtLeastTest;
import org.sat4j.minisat.constraints.card.MaxWatchCardTest;
import org.sat4j.minisat.constraints.card.MinWatchCardTest;
import org.sat4j.minisat.constraints.cnf.LearntBinaryClauseTest;
import org.sat4j.minisat.constraints.cnf.LearntHTClauseTest;
import org.sat4j.minisat.constraints.cnf.LearntWLClauseTest;
import org.sat4j.minisat.constraints.cnf.LitsTest;
import org.sat4j.minisat.constraints.cnf.OriginalBinaryClauseTest;
import org.sat4j.minisat.constraints.cnf.OriginalHTClauseTest;
import org.sat4j.minisat.constraints.cnf.OriginalWLClauseTest;
import org.sat4j.minisat.constraints.cnf.UnitClauseTest;
import org.sat4j.minisat.constraints.cnf.UnitClausesTest;
import org.sat4j.minisat.core.ActivityComparatorTest;
import org.sat4j.minisat.core.Bug275101;
import org.sat4j.minisat.core.BugFatih;
import org.sat4j.minisat.core.BugFatih2;
import org.sat4j.minisat.core.BugReset;
import org.sat4j.minisat.core.BugThomas;
import org.sat4j.minisat.core.BugTrivialAssumption;
import org.sat4j.minisat.core.CircularBufferTest;
import org.sat4j.minisat.core.ConflictTimerContainerTest;
import org.sat4j.minisat.core.HeapTest;
import org.sat4j.minisat.core.LboolTest;
import org.sat4j.minisat.core.LearnedConstraintsEvaluationTypeTest;
import org.sat4j.minisat.core.QueueTest;
import org.sat4j.minisat.core.SimplicationTypeTest;
import org.sat4j.minisat.core.TestAtMost;
import org.sat4j.minisat.core.TestGroupedTimeoutModelEnumeration;
import org.sat4j.minisat.core.TestPrimeComputation;
import org.sat4j.minisat.learning.ActiveLearningTest;
import org.sat4j.minisat.learning.ClauseOnlyLearningTest;
import org.sat4j.minisat.learning.FixedLengthLearningTest;
import org.sat4j.minisat.learning.MiniSATLearningTest;
import org.sat4j.minisat.learning.NoLearningNoHeuristicsTest;
import org.sat4j.minisat.learning.PercentLengthLearningTest;
import org.sat4j.minisat.orders.NegativeLiteralSelectionStrategyTest;
import org.sat4j.minisat.orders.PhaseCachingAutoEraseStrategyTest;
import org.sat4j.minisat.orders.PhaseInLastLearnedClauseSelectionStrategyTest;
import org.sat4j.minisat.orders.PositiveLiteralSelectionStrategyTest;
import org.sat4j.minisat.orders.PureOrderTest;
import org.sat4j.minisat.orders.RSATLastLearnedClausesPhaseSelectionStrategyTest;
import org.sat4j.minisat.orders.RSATPhaseSelectionStrategyTest;
import org.sat4j.minisat.orders.RandomLiteralSelectionStrategyTest;
import org.sat4j.minisat.orders.RandomWalkDecoratorTest;
import org.sat4j.minisat.orders.SubsetVarOrderTest;
import org.sat4j.minisat.orders.TabuListDecoratorTest;
import org.sat4j.minisat.orders.UserFixedPhaseSelectionStrategyTest;
import org.sat4j.minisat.orders.ValuedLitTest;
import org.sat4j.minisat.orders.VarOrderHeapTest;
import org.sat4j.minisat.restarts.ArminRestartsTest;
import org.sat4j.minisat.restarts.FixedPeriodRestartsTest;
import org.sat4j.minisat.restarts.Glucose21RestartsTest;
import org.sat4j.minisat.restarts.LubyRestartsTest;
import org.sat4j.minisat.restarts.MiniSATRestartsTest;
import org.sat4j.minisat.restarts.NoRestartsTest;
import org.sat4j.opt.MaxSatDecoratorTest;
import org.sat4j.opt.MinOneDecoratorTest;
import org.sat4j.reader.AAGReaderTest;
import org.sat4j.reader.AIGReaderTest;
import org.sat4j.reader.DimacsReaderTest;
import org.sat4j.reader.EfficientScannerTest;
import org.sat4j.reader.GroupedCNFReaderTest;
import org.sat4j.reader.InstanceReaderTest;
import org.sat4j.reader.JsonReaderTest;
import org.sat4j.reader.LecteurDimacsTest;
import org.sat4j.reader.ParseFormatExceptionTest;
import org.sat4j.specs.ContradictionExceptionTest;
import org.sat4j.specs.TimeoutExceptionTest;
import org.sat4j.tools.BackboneTest;
import org.sat4j.tools.encoding.BinaryTest;
import org.sat4j.tools.encoding.BinomialTest;
import org.sat4j.tools.encoding.CommanderTest;
import org.sat4j.tools.encoding.LadderTest;
import org.sat4j.tools.encoding.PolicyTest;
import org.sat4j.tools.encoding.ProductTest;
import org.sat4j.tools.encoding.SequentialTest;
import org.sat4j.tools.xplain.*;

public class UnitTests {
	public static Test suite() {
		TestSuite suite = new TestSuite("Unit Tests");

		// org.sat4j
		suite.addTestSuite(ExitCodeTest.class);
		suite.addTestSuite(LightFactoryTest.class);
		suite.addTestSuite(MessagesTest.class);
		suite.addTestSuite(MoreThanSATTest.class);
		suite.addTestSuite(BasicLauncherTest.class);
		suite.addTestSuite(MUSLauncherTest.class);

		// org.sat4j.core
		suite.addTestSuite(VecTest.class);
		suite.addTestSuite(VecIntTest.class);
		suite.addTestSuite(DefaultComparatorTest.class);
		suite.addTestSuite(TestConstrGroup.class);
		suite.addTestSuite(ReadOnlyVecTest.class);
		suite.addTestSuite(ReadOnlyVecIntTest.class);
		suite.addTestSuite(LiteralsUtilsTest.class);

		// org.sat4j.minisat
		suite.addTestSuite(TestAssertion.class);
		suite.addTestSuite(VarOrderTest.class);
		suite.addTestSuite(M2MiniSATTest.class);
		suite.addTestSuite(TestsFonctionnels.class);
		suite.addTestSuite(M2MiniLearningTest.class);
		suite.addTestSuite(M2CardMinYannTest.class);
		suite.addTestSuite(M2AtLeastTest.class);
		suite.addTestSuite(M2BackjumpingTest.class);
		suite.addTest(GenericM2Test.suite());
		suite.addTestSuite(M2CardMaxYannTest.class);

		// org.sat4j.minisat.constraints
		suite.addTestSuite(CardinalityDataStructureTest.class);
		suite.addTestSuite(CardinalityDataStructureYanMaxTest.class);
		suite.addTestSuite(CardinalityDataStructureYanMinTest.class);
		suite.addTestSuite(ClausalDataStructureWLTest.class);
		suite.addTestSuite(LitTest.class);
		suite.addTestSuite(MixedDataStructureDanielHTTest.class);
		suite.addTestSuite(MixedDataStructureDanielWLTest.class);
		suite.addTestSuite(MixedDataStructureSingleWLTest.class);
		
		// org.sat4j.minisat.constraints.card
		suite.addTestSuite(AtLeastTest.class);
		suite.addTestSuite(MaxWatchCardTest.class);
		suite.addTestSuite(MinWatchCardTest.class);
	        
		// org.sat4j.minisat.constraints.cnf
		suite.addTestSuite(LearntBinaryClauseTest.class);
        suite.addTestSuite(LearntHTClauseTest.class);
        suite.addTestSuite(LearntWLClauseTest.class);
        suite.addTestSuite(LitsTest.class);
        suite.addTestSuite(OriginalBinaryClauseTest.class);
        suite.addTestSuite(OriginalHTClauseTest.class);
        suite.addTestSuite(OriginalWLClauseTest.class);
        suite.addTestSuite(UnitClauseTest.class);
        suite.addTestSuite(UnitClausesTest.class);
        
        // org.sat4j.minisat.core
        suite.addTestSuite(Bug275101.class);
        suite.addTestSuite(BugFatih.class);
        suite.addTestSuite(BugFatih2.class);
        suite.addTestSuite(BugReset.class);
        suite.addTestSuite(BugThomas.class);
        suite.addTestSuite(BugTrivialAssumption.class);
        suite.addTestSuite(CircularBufferTest.class);
        suite.addTestSuite(HeapTest.class);
        suite.addTestSuite(LboolTest.class);
        suite.addTestSuite(QueueTest.class);
        suite.addTestSuite(TestAtMost.class);
        suite.addTestSuite(TestGroupedTimeoutModelEnumeration.class);
        suite.addTestSuite(TestPrimeComputation.class);
        suite.addTestSuite(LearnedConstraintsEvaluationTypeTest.class);
        suite.addTestSuite(SimplicationTypeTest.class);
        suite.addTestSuite(ActivityComparatorTest.class);
        suite.addTestSuite(ConflictTimerContainerTest.class);
        
        // org.sat4j.minisat.learning
        suite.addTestSuite(ActiveLearningTest.class);
        suite.addTestSuite(ClauseOnlyLearningTest.class);
        suite.addTestSuite(FixedLengthLearningTest.class);
        suite.addTestSuite(MiniSATLearningTest.class);
        suite.addTestSuite(NoLearningNoHeuristicsTest.class);
        suite.addTestSuite(PercentLengthLearningTest.class);
        
        // org.sat4j.minisat.orders
        suite.addTestSuite(NegativeLiteralSelectionStrategyTest.class);
        suite.addTestSuite(PhaseCachingAutoEraseStrategyTest.class);
        suite.addTestSuite(PhaseInLastLearnedClauseSelectionStrategyTest.class);
        suite.addTestSuite(PositiveLiteralSelectionStrategyTest.class);
        suite.addTestSuite(PureOrderTest.class);
        suite.addTestSuite(RandomLiteralSelectionStrategyTest.class);
        suite.addTestSuite(RandomWalkDecoratorTest.class);
        suite.addTestSuite(RSATLastLearnedClausesPhaseSelectionStrategyTest.class);
        suite.addTestSuite(RSATPhaseSelectionStrategyTest.class);
        suite.addTestSuite(SubsetVarOrderTest.class);
        suite.addTestSuite(TabuListDecoratorTest.class);
        suite.addTestSuite(UserFixedPhaseSelectionStrategyTest.class);
        suite.addTestSuite(ValuedLitTest.class);
        suite.addTestSuite(VarOrderHeapTest.class);
        
        // org.sat4j.minisat.restarts
        suite.addTestSuite(ArminRestartsTest.class);
        suite.addTestSuite(FixedPeriodRestartsTest.class);
        suite.addTestSuite(Glucose21RestartsTest.class);
        suite.addTestSuite(LubyRestartsTest.class);
        suite.addTestSuite(MiniSATRestartsTest.class);
        suite.addTestSuite(NoRestartsTest.class); 
        
        // org.sat4j.opt
        suite.addTestSuite(MaxSatDecoratorTest.class);
        suite.addTestSuite(MinOneDecoratorTest.class); 
        
        // org.sat4j.reader
        suite.addTestSuite(AAGReaderTest.class);
        suite.addTestSuite(AIGReaderTest.class);
        suite.addTestSuite(DimacsReaderTest.class);
        suite.addTestSuite(EfficientScannerTest.class);
        suite.addTestSuite(GroupedCNFReaderTest.class);
        suite.addTestSuite(InstanceReaderTest.class);
        suite.addTestSuite(JsonReaderTest.class);
        suite.addTestSuite(LecteurDimacsTest.class);
        suite.addTestSuite(ParseFormatExceptionTest.class);
        
        // org.sat4j.specs
        suite.addTestSuite(ContradictionExceptionTest.class);
        suite.addTestSuite(LboolTest.class); 
        suite.addTestSuite(TimeoutExceptionTest.class); 
        
        // org.sat4j.tools.encoding
        suite.addTestSuite(BinaryTest.class);
        suite.addTestSuite(BinomialTest.class);
        suite.addTestSuite(CommanderTest.class);
        suite.addTestSuite(LadderTest.class);
        suite.addTestSuite(PolicyTest.class);
        suite.addTestSuite(ProductTest.class);
        suite.addTestSuite(SequentialTest.class);
        
        return suite;
	}

}