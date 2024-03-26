package org.lshh.skeleton.core.task;

import java.util.Map;

public interface Task {
    Map<String, Object> execute();
    Task setArgs(String key, Object value) ;
    Task setArgs(Map<String, Object> args);
    Map<String, Object> getResults();
}
