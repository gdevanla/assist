package com.ser.assist.oraclefinder;

import soot.Local;
import soot.Unit;

public class LocalWrapper {
    final public Local local;
    final public Unit unit;

    public LocalWrapper(Local v, Unit u) {
        local = v;
        unit = u;
    }
}