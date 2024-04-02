package org.lshh.skeleton.core.resource.resourcer.implement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.lshh.skeleton.core.resource.resourcer.JdbcResourcer;

import javax.sql.DataSource;

public class SimpleJdbcResourcer implements JdbcResourcer {
    DataSource dataSource;
    ResourcerContext context;

    public SimpleJdbcResourcer(ResourcerContext context) {
        this.context = context;
        initDataSource();
    }
    public static JdbcResourcer of(ResourcerContext context) {
        return new SimpleJdbcResourcer(context);
    }

    public void initDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(context.getUrl());
        config.setUsername(context.getUsername());
        config.setPassword(context.getPassword());
        config.setDriverClassName(context.getAdaptorName());
        this.dataSource = new HikariDataSource(config);
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
    public String getName() {
        return this.context.getName();
    }
}
