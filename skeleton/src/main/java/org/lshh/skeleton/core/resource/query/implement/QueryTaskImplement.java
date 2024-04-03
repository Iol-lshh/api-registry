package org.lshh.skeleton.core.resource.query.implement;

import org.lshh.skeleton.core.resource.argument.ArgumentsMap;
import org.lshh.skeleton.core.resource.query.Query;
import org.lshh.skeleton.core.task.implement.AbstractTask;
import org.lshh.skeleton.core.task.QueryTask;
import org.lshh.skeleton.core.task.implement.TaskContext;

import java.util.Map;

public abstract class QueryTaskImplement extends AbstractTask implements QueryTask {

    protected final Query query;


    QueryTaskImplement(TaskContext context, Query query, Map<String, Object> args){
        super(context, args);
        this.query = query;
        setArgs(args);
    }

    @Override
    public ArgumentsMap<String, Object> execute() {
        return query.query(args);
    }

}
