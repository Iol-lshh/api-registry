package org.lshh.skeleton.core.resource.query.implement;

import org.lshh.skeleton.core.resource.argument.ArgumentsMap;
import org.lshh.skeleton.core.resource.query.Query;
import org.lshh.skeleton.core.task.implement.AbstractTask;
import org.lshh.skeleton.core.task.QueryTask;

import java.util.Map;

public abstract class QueryTaskImplement extends AbstractTask implements QueryTask {

    protected final Query query;

    QueryTaskImplement(Query query, Map<String, Object> args){
        this.query = query;
        setArgs(args);
    }

    @Override
    public ArgumentsMap<String, Object> execute() {
        return query.query(args);
    }
}
