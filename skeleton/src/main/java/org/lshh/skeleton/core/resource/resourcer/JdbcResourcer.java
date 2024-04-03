package org.lshh.skeleton.core.resource.resourcer;

import javax.sql.DataSource;

public interface JdbcResourcer extends Resourcer{
    @Override
    default ResourcerType getType(){
        return ResourcerType.RDBMS;
    }

    DataSource getDataSource();

    void initDataSource();

    // 커넥션 풀 관리 필요?
    JdbcResourcer setConectionPool(int maxPoolSize, int minIdle, long connectionTimeout, long idleTimeout, long maxLifetime);
}
