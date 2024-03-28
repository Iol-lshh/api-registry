package org.lshh.skeleton.core.resource.resourcer.implement;

import org.lshh.skeleton.core.resource.resourcer.RdbmsResourcer;
import org.lshh.skeleton.core.resource.resourcer.Resourcer;

import javax.sql.DataSource;

public class SimpleRdbmsResourcer implements RdbmsResourcer {

    public static Resourcer of(ResourcerContext context) {
        return new SimpleRdbmsResourcer();
    }

    @Override
    public DataSource getDataSource() {
        return null;
    }

    @Override
    public Long getId() {
        return null;
    }
}
