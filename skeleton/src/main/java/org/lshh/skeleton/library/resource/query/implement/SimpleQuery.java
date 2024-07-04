package org.lshh.skeleton.library.resource.query.implement;

import org.lshh.skeleton.library.core.variable.data.DataSet;
import org.lshh.skeleton.library.resource.query.Query;
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
    public DataSet query(Map<String, Object> args) {
        List<Map<String, Object>> list = JDBC_TEMPLATE.queryForList(QUERY_BODY, args);
        List<String> columns = list.isEmpty() ? List.of() : List.copyOf(list.get(0).keySet());
        List<List<Object>> rows = list.stream().map(map -> List.copyOf(map.values())).toList();
        return DataSet.of(columns, rows);
    }
}
