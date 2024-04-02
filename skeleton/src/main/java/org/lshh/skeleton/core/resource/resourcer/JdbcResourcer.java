package org.lshh.skeleton.core.resource.resourcer;

import javax.sql.DataSource;

public interface JdbcResourcer extends Resourcer{

    @Override
    default ResourcerType getType(){
        return ResourcerType.RDBMS;
    }
    DataSource getDataSource();
}
