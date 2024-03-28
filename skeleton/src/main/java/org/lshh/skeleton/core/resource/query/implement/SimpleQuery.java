package org.lshh.skeleton.core.resource.query.implement;

import org.lshh.skeleton.core.resource.argument.ArgumentsHashMap;
import org.lshh.skeleton.core.resource.argument.ArgumentsMap;
import org.lshh.skeleton.core.resource.query.Query;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

public class SimpleQuery implements Query {

    private final Long ID;
    private final String QUERY_BODY;
    private final DataSource DATA_SOURCE;
    private final NamedParameterJdbcTemplate JDBC_TEMPLATE;

    public SimpleQuery(QueryContext query, DataSource dataSource){
        ID = query.getId();
        QUERY_BODY = query.getBody();
        DATA_SOURCE = dataSource;
        JDBC_TEMPLATE = new NamedParameterJdbcTemplate(DATA_SOURCE);
    }

    @Override
    public ArgumentsMap<String, Object> query(Map<String, Object> args) {
        List<Map<String, Object>> list = JDBC_TEMPLATE.queryForList(QUERY_BODY, args);
        ArgumentsMap<String, Object> result = new ArgumentsHashMap<>();
        result.put("result", list);
        return result;
    }
}
