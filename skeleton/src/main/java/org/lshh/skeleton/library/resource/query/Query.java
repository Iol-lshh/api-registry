package org.lshh.skeleton.library.resource.query;

import org.lshh.skeleton.library.core.variable.Variable;
import org.lshh.skeleton.library.core.variable.data.Constant;
import org.lshh.skeleton.library.core.variable.data.DataSet;
import org.lshh.skeleton.library.core.variable.data.DataVariable;
import org.lshh.skeleton.library.resource.query.implement.JdbcTemplateWrapper;
import org.lshh.skeleton.library.resource.query.implement.QueryContext;
import org.lshh.skeleton.library.resource.query.implement.SimpleQuery;

import java.util.Map;

public interface Query {
    static Query of(QueryContext queryContext, JdbcTemplateWrapper jdbcTemplate) {
        return new SimpleQuery(queryContext, jdbcTemplate);
    }

    Query query();

    boolean isReady();

    Query setInput(String key, DataSet value);

    Query setInput(String key, Constant value);

    DataVariable getOutput();

}
