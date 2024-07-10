package org.lshh.skeleton.library.resource.query;

import org.lshh.skeleton.library.variable.data.Constant;
import org.lshh.skeleton.library.variable.data.DataSet;
import org.lshh.skeleton.library.variable.data.DataVariable;
import org.lshh.skeleton.library.resource.query.implement.JdbcTemplateWrapper;
import org.lshh.skeleton.library.resource.query.implement.QueryContext;
import org.lshh.skeleton.library.resource.query.implement.SimpleQuery;

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
