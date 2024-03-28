package org.lshh.skeleton.core.resource.resourcer;

import org.lshh.skeleton.core.resource.resourcer.implement.ResourcerContext;

import javax.sql.DataSource;

public interface RdbmsResourcer extends Resourcer{

    @Override
    default ResourcerType getType(){
        return ResourcerType.RDBMS;
    }
    DataSource getDataSource();
}
