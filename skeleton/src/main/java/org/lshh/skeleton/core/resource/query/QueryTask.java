package org.lshh.skeleton.core.resource.query;

import org.lshh.skeleton.core.task.AbstractTask;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

public class QueryTask extends AbstractTask implements Query {

    @Override
    public Future<Map<String, Object>> execute() {
        return null;
    }

    @Override
    public QueryTask setArgs(String key, Object val) {
        return null;
    }

    @Override
    public QueryTask setArgs(Map<String, Object> args) {
        this.args.putAll(args);
        return this;
    }

    @Override
    public QueryTask setQuery(String query) {
        return null;
    }

    @Override
    public List<Map<String, Object>> query() throws Exception {
        return null;
    }
}
