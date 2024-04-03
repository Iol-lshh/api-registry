package org.lshh.skeleton.core.task;

import org.lshh.skeleton.core.resource.argument.ArgumentsMap;

import java.util.Map;

public interface Task {
    ArgumentsMap<String, Object> execute();
    Long rollbackTaskId();

    Task setArgs(String key, Object value);
    Task setArgs(Map<String, Object> args);
    TaskType getType();
    Map<String, Object> getResults();
    Task copy();

    default  <K extends Task> K as(Class<K> subClass){
        if(!subClass.isInstance(this)){
            throw new IllegalArgumentException("Task is not instance of " + subClass.getName());
        }
        return subClass.cast(this);
    }

    Long getId();

    String getTreeId();

    enum TaskType{
        QUERY,
        PIPELINE,
        PARALLEL,
        LOCK
    }
}
