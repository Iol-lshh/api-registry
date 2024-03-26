package org.lshh.skeleton.core.task;

import org.lshh.skeleton.core.task.exception.TaskException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

public abstract class AbstractTask implements Task{
    protected Map<String, Object> args = new HashMap<>();
    protected Map<String, Object> results = new HashMap<>();

    @Override
    public abstract Future<Map<String, Object>> execute();

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
                execute().get();
            } catch (Exception e) { // todo - InterruptedException, ExecutionException
                throw TaskException.of("Task execute failed");
            }
        }
        return results;
    }
}
