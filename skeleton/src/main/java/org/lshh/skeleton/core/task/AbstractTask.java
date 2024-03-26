package org.lshh.skeleton.core.task;

import org.lshh.skeleton.core.task.exception.TaskException;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTask implements Task{
    protected Map<String, Object> args = new HashMap<>();
    protected Map<String, Object> results = new HashMap<>();

    @Override
    public abstract Map<String, Object> execute();

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
                execute();
            } catch (Exception e) {
                throw new TaskException("Task execute failed");
            }
        }
        return results;
    }
}
