package org.lshh.skeleton.core.task;

import org.lshh.skeleton.core.task.dto.RollbackEvent;
import org.lshh.skeleton.core.task.dto.TaskCreateCommand;
import org.lshh.skeleton.core.task.dto.TaskUpdateCommand;

import java.util.List;

public interface TaskManager {
    void handleRollback(RollbackEvent event);

    Task find(Long startTaskId);
    List<Task> findAll();
    Integer create(TaskCreateCommand command);
    Integer update(TaskUpdateCommand command);

    Task refresh(String treeId);
    void clearCache();
    boolean isCached(String path);
}
