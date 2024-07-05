package org.lshh.skeleton.library.resource.resourcer.implement;

import com.zaxxer.hikari.HikariDataSource;
import org.lshh.skeleton.library.resource.query.implement.JdbcTemplateWrapper;
import org.lshh.skeleton.library.resource.resourcer.RdbmsResourcer;
import org.lshh.skeleton.library.resource.resourcer.Resourcer;

import javax.sql.DataSource;

public class SimpleRdbmsResourcer implements RdbmsResourcer {

    private final ResourcerContext context;
    private final DataSource dataSource;

    public SimpleRdbmsResourcer(ResourcerContext context, DataSource dataSource) {
        this.context = context;
        this.dataSource = dataSource;
    }

    public static Resourcer of(ResourcerContext context) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(context.getEndpoint());
        dataSource.setUsername(context.getUserName());
        dataSource.setPassword(context.getPassword());
        return new SimpleRdbmsResourcer(context, dataSource);
    }

    @Override
    public DataSource getDataSource() {
        return this.dataSource;
    }

    @Override
    public Long getId() {
        return this.context.getId();
    }

    @Override
    public boolean isReady() {
        return JdbcTemplateWrapper.of(this.dataSource).isReady();
    }

    @Override
    public String getName() {
        return this.context.getName();
    }
}
