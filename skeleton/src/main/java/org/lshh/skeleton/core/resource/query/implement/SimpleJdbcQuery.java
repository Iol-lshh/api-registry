package org.lshh.skeleton.core.resource.query.implement;

import org.lshh.skeleton.core.resource.argument.ArgumentsHashMap;
import org.lshh.skeleton.core.resource.argument.ArgumentsMap;
import org.lshh.skeleton.core.resource.query.Query;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

public class SimpleJdbcQuery implements Query {
    private final QueryContext CONTEXT;
    private final NamedParameterJdbcTemplate JDBC_TEMPLATE;

    public SimpleJdbcQuery(QueryContext context, DataSource dataSource){
        CONTEXT = context;
        JDBC_TEMPLATE = new NamedParameterJdbcTemplate(dataSource);
    }

    public static Query of(QueryContext context, DataSource dataSource){
        return new SimpleJdbcQuery(context, dataSource);
    }

    @Override
    public Long getId() {
        return CONTEXT.getId();
    }

    @Override
    public String getTitle() {
        return CONTEXT.getTitle();
    }

    @Override
    public String getBody() {
        return CONTEXT.getBody();
    }

    @Override
    public Long getResourcerId() {
        return CONTEXT.getResourcerId();
    }

    @Override
    public ArgumentsMap<String, Object> query(Map<String, Object> args) {
        List<Map<String, Object>> list = JDBC_TEMPLATE.queryForList(CONTEXT.getBody(), args);
        ArgumentsMap<String, Object> result = new ArgumentsHashMap<>();
        result.put(getTitle()+getId(), list);
        return result;
    }
}
