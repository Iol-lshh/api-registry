package org.lshh.skeleton.domain.task;

import java.util.Map;
import java.util.concurrent.Future;

public interface Task {
    Future<Map<String, Object>> execute();
    Task setArgs(String key, Object value) ;
    Task setArgs(Map<String, Object> args);
    Map<String, Object> getResults();
}
