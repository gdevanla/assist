package com.ser.assist.testgenerator

import com.ser.assist.MethodTrace
import com.ser.assist.oraclefinder.Oracle
import com.ser.assist.oraclefinder.Oracle.OraclePattern
import org.codehaus.groovy.ast.Variable
import soot.Local
import soot.SootClass
import soot.SootMethod
import soot.toolkits.scalar.Pair

class GenerateTestModel1 {

    String loadStatement = "def  %s = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState(\"%s\")"


    def getStateFileName(counter, kind, type){
        return String.format("state.%d.%s.%s.xml", counter, kind, type)
    }

    def generateTestSnippets(List<Pair<MethodTrace, Oracle>> mtpairs){

        def local_to_varname = new HashMap<Local,String>();
        //create map of all defs to new variable-names
        mtpairs.each() { it.o2.units.each() { it.getUseBoxes().each()
                { local_to_varname.put (it.value.toString(), "TODO")}}}


        //// def all variables in all oracles and create a map

        //// load the state for first def from first pair

        /// for all mtpairs
        /// set first o.mut with  =  state variable
        /// play oracle everything else should set itself up







       /* def s1 = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state.this.mut.xml");
        def s2 = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state.return.mut.xml");

        def param1 = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state.param1.mut.xml");
        def param2 = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("state.param2.mut.xml");
        def r1 = s1.invokeMethod(mut, param1, param2);
        */
    }

    def generate(HashMap<Pair<SootClass,SootMethod>, Oracle> oracles, mts)
    {
        //TODO : move this out of here along with all the methods to a higher level
        List mt = partitionSequence(getChunkedSequencesAtSameLevel(getMinLevel(mts), mts))
        def testSnippets = mt.each() { generateTestSnippets(oracles, it) }


    }

    def getMinLevel(methodTraces){
        methodTraces.inject(Integer.MAX_VALUE) { acc, mt -> if (acc > mt.level ) {mt.level} else {acc}}
    }

    def getChunkedSequencesAtSameLevel(level,
                                          methodTraces){
        methodTraces.findAll() {it->it.level == level}
    }

    def partitionSequence(methodTraces, f={i, j -> i.objectId == j.objectId}){
           if (!methodTraces) {return []}

           methodTraces.drop(1).inject([[methodTraces.first()]]) {List acc, m ->
               if ( f(acc.last().last(), m ) ) {acc.last() << m} else { acc << [m]}
               acc
           }

    }

}
