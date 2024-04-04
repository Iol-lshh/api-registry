package org.lshh.skeleton.core.resource.query.implement;

import org.lshh.skeleton.core.resource.query.Query;
import org.lshh.skeleton.core.task.QueryTask;
import org.lshh.skeleton.core.task.implement.TaskContext;

import java.util.Map;

public class SimpleQueryTask extends QueryTaskImplement implements QueryTask {

    SimpleQueryTask(TaskContext context, Query query, Map<String, Object> args){
        super(context, query, args);
    }

    public static QueryTask of(TaskContext context, Query query, Map<String, Object> args) {
        return new SimpleQueryTask(context, query, args);
    }

    @Override
    public QueryTask copy() {
        return SimpleQueryTask.of(context, query, args);
    }

}
