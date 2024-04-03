package org.lshh.skeleton.core.task;

import java.util.Map;

import static org.lshh.skeleton.core.task.Task.TaskType.QUERY;

public interface QueryTask extends Task {
    @Override
    default TaskType getType(){
        return QUERY;
    }
}
