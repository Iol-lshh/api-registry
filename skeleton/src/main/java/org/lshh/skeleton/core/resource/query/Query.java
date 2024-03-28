package org.lshh.skeleton.core.resource.query;

import org.lshh.skeleton.core.resource.argument.ArgumentsMap;
import org.lshh.skeleton.core.resource.query.implement.QueryContext;
import org.lshh.skeleton.core.resource.query.implement.SimpleQuery;

import javax.sql.DataSource;
import java.util.Map;

public interface Query {
    static Query of(QueryContext queryContext, DataSource dataSource) {
        return new SimpleQuery(queryContext, dataSource);
    }
    ArgumentsMap<String, Object> query(Map<String, Object> args);
}
