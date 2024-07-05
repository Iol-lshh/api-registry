package org.lshh.skeleton.library.core.variable.data;

public interface Constant extends DataVariable {
    static Constant of(Object value) {
        return ConstantImplement.of(value);
    }

    Object getValue();
}
