package org.lshh.skeleton.library.core.variable.data;

import java.util.List;

public class ConstantImplement implements Constant {
    Object value;

    public ConstantImplement(Object value) {
        this.value = value;
    }

    public static Constant of(Object value) {
        return new ConstantImplement(value);
    }

    @Override
    public Object getValue() {
        return this.value;
    }
}
