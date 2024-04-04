package org.lshh.skeleton.core.task;

import org.lshh.skeleton.core.task.implement.TaskContext;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    Optional<TaskContext> findContext(Long contextId);

    List<TaskContext> findByStartId(Long startContextId);

    List<TaskContext> findAllRoute();

    Optional<TaskContext> findByTreeId(String treeId);

    TaskContext create(TaskContext newOne);

    TaskContext update(TaskContext renewal);
}
