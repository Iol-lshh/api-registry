package org.lshh.skeleton.library.variable.unit;

public interface Unit {
    static <T> Unit of(T unit) {
        return UnitVariable.of(unit);
    }
    int act();
    boolean isReady();
}
