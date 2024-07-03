package org.lshh.skeleton.library.resource.resourcer;

import javax.sql.DataSource;

public interface RdbmsResourcer extends Resourcer{

    @Override
    default ResourcerType getType(){
        return ResourcerType.RDBMS;
    }
    DataSource getDataSource();
}
