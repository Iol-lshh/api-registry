package org.lshh.skeleton.core.resource.resourcer;

import javax.sql.DataSource;

public interface RdbmsResourcer extends Resourcer{
    DataSource getDataSource();
}
