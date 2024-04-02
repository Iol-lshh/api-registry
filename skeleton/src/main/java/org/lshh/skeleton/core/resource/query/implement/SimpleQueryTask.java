package org.lshh.skeleton.core.resource.query.implement;

import org.lshh.skeleton.core.resource.query.Query;
import org.lshh.skeleton.core.task.QueryTask;

import java.util.Map;

public class SimpleQueryTask extends QueryTaskImplement implements QueryTask {

    SimpleQueryTask(Query query, Map<String, Object> args){
        super(query, args);
    }

    public static SimpleQueryTask of(Query query, Map<String, Object> args) {
        return new SimpleQueryTask(query, args);
    }
}
