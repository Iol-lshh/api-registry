package org.lshh.skeleton.core.resource.query;

import org.lshh.skeleton.core.resource.query.implement.QueryContext;
import org.lshh.skeleton.core.resource.query.implement.QueryImplement;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

public interface Query {
    public static Query of(QueryContext queryContext, DataSource dataSource) {
        return new QueryImplement(queryContext, dataSource);
    }
    List<Map<String, Object>> query(Map<String, Object> args);
}
