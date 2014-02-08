package com.ser.assist.testgenerator

import soot.Body
import soot.SootMethod
import soot.Unit

class OracleReplayInfo {
    //int counter = 4;    //TODO: get rid of this
    Body testBody = null;
    SootMethod method;
    Unit mut;
    List<Unit> replayUnits = new ArrayList<>();
}
