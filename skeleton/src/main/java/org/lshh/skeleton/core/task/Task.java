package org.lshh.skeleton.core.task;

import org.lshh.skeleton.core.resource.argument.ArgumentsMap;

import java.util.Map;

public interface Task {
    ArgumentsMap<String, Object> execute();
    Task setArgs(String key, Object value) ;
    Task setArgs(Map<String, Object> args);
    Map<String, Object> getResults();
}
