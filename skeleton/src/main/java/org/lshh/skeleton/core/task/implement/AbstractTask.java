package org.lshh.skeleton.core.task.implement;

import org.lshh.skeleton.core.resource.argument.ArgumentsHashMap;
import org.lshh.skeleton.core.resource.argument.ArgumentsMap;
import org.lshh.skeleton.core.task.Task;
import org.lshh.skeleton.core.task.exception.TaskException;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTask implements Task {
    protected ArgumentsHashMap<String, Object> args = new ArgumentsHashMap<String, Object>();
    protected Map<String, Object> results = new HashMap<>();
    protected TaskContext context;

    protected AbstractTask(TaskContext context, Map<String, Object> args){
        this.context = context;
        setArgs(args);
    }

    @Override
    public abstract ArgumentsMap<String, Object> execute();

    @Override
    public Task setArgs(String key, Object value) {
        this.args.put(key, value);
        return this;
    }

    @Override
    public Task setArgs(Map<String, Object> args) {
        this.args.putAll(args);
        return this;
    }

    @Override
    public Map<String, Object> getResults(){
        if(results.isEmpty()){
            try {
                setArgs(execute());
            } catch (Exception e) {
                throw new TaskException("Task execute failed - " + e.getMessage());
            }
        }
        return results;
    }

    @Override
    public Long rollbackTaskId() {
        return context.getRollbackTaskId();
    }

    @Override
    public Long getId() {
        return context.getId();
    }
    @Override
    public String getTreeId() {
        return context.getTreeId();
    }
}