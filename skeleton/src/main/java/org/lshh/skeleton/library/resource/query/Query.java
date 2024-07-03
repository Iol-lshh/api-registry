package org.lshh.skeleton.library.resource.query;

import org.lshh.skeleton.library.resource.query.implement.QueryContext;
import org.lshh.skeleton.library.resource.query.implement.SimpleQuery;
import org.lshh.skeleton.library.resource.argument.ArgumentsMap;

import javax.sql.DataSource;
import java.util.Map;

public interface Query {
    static Query of(QueryContext queryContext, DataSource dataSource) {
        return new SimpleQuery(queryContext, dataSource);
    }
    ArgumentsMap<String, Object> query(Map<String, Object> args);
}
