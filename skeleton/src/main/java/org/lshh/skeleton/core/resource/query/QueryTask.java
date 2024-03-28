package org.lshh.skeleton.core.resource.query;

import org.lshh.skeleton.core.resource.argument.ArgumentsMap;
import org.lshh.skeleton.core.task.implement.AbstractTask;

import java.util.Map;

public class QueryTask extends AbstractTask {

    private final Query query;

    QueryTask(Query query, Map<String, Object> args){
        this.query = query;
        setArgs(args);
    }

    public static QueryTask of(Query query, Map<String, Object> args) {
        return new QueryTask(query, args);
    }

    @Override
    public ArgumentsMap<String, Object> execute() {
        return query.query(args);
    }

    @Override
    public QueryTask setArgs(String key, Object value) {
        this.args.put(key, value);
        return this;
    }

    @Override
    public QueryTask setArgs(Map<String, Object> args) {
        this.args.putAll(args);
        return this;
    }
}
