package org.lshh.skeleton.library.resource.resourcer.implement;

import org.lshh.skeleton.library.resource.resourcer.RdbmsResourcer;

import javax.sql.DataSource;

public class SimpleRdbmsResourcer implements RdbmsResourcer {

    ResourcerContext context;
    DataSource dataSource;

    private SimpleRdbmsResourcer() {
        RdbmsResourcer resourcer = new SimpleRdbmsResourcer();

    }
    public static RdbmsResourcer of(ResourcerContext context) {
        return new SimpleRdbmsResourcer();
    }

//    public createDataSource(){
//        HikariConfig config = new HikariConfig();
//
//    }

    @Override
    public DataSource getDataSource() {
        return this.dataSource;
    }

    @Override
    public Long getId() {
        return null;
    }
}
