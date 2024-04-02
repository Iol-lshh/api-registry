package org.lshh.skeleton.core.resource.resourcer.implement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.lshh.skeleton.core.resource.resourcer.JdbcResourcer;

import javax.sql.DataSource;

public class SimpleJdbcResourcer implements JdbcResourcer {
    private DataSource dataSource;
    private final ResourcerContext CONTEXT;

    public SimpleJdbcResourcer(ResourcerContext context) {
        CONTEXT = context;
        initDataSource();
    }
    public static JdbcResourcer of(ResourcerContext context) {
        return new SimpleJdbcResourcer(context);
    }

    @Override
    public void initDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(CONTEXT.getUrl());
        config.setUsername(CONTEXT.getUsername());
        config.setPassword(CONTEXT.getPassword());
        config.setDriverClassName(CONTEXT.getAdaptorName());
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setConnectionTimeout(30000);
        config.setIdleTimeout(600000);
        config.setMaxLifetime(1800000);
        dataSource = new HikariDataSource(config);
    }

    @Override
    public DataSource getDataSource() {
        return this.dataSource;
    }

    @Override
    public JdbcResourcer setConectionPool(int maxPoolSize, int minIdle, long connectionTimeout, long idleTimeout, long maxLifetime) {
        HikariDataSource hikariDataSource = (HikariDataSource) this.dataSource;
        hikariDataSource.setMaximumPoolSize(maxPoolSize);
        hikariDataSource.setMinimumIdle(minIdle);
        hikariDataSource.setConnectionTimeout(connectionTimeout);
        hikariDataSource.setIdleTimeout(idleTimeout);
        hikariDataSource.setMaxLifetime(maxLifetime);
        return this;
    }

    @Override
    public Long getId() {
        return CONTEXT.getId();
    }

    @Override
    public String getName() {
        return CONTEXT.getName();
    }
}
