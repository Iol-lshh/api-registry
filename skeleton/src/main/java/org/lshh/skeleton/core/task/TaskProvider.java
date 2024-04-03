package org.lshh.skeleton.core.task;

import org.lshh.skeleton.core.task.dto.TaskCreateCommand;
import org.lshh.skeleton.core.task.dto.TaskUpdateCommand;
import org.lshh.skeleton.core.task.implement.TaskContext;

import java.util.List;
import java.util.Optional;

public interface TaskProvider {
    Optional<TaskContext> findContext(Long contextId);

    List<TaskContext> findContextList(Long startContextId);

    Task generate(TaskContext context, List<TaskContext> list);

    QueryTask generateQueryTask(TaskContext context);

    PipelineTask generatePipelineTask(TaskContext context, List<TaskContext> list);

    ParallelTask generateParallelTask(TaskContext context, List<TaskContext> list);

    LockTask generateLockTask(TaskContext context);

    Optional<Task> find(Long taskId);

    List<Task> findAll();

    List<TaskContext> findAllRouteContext();

    Task create(TaskCreateCommand command);

    Task update(TaskUpdateCommand command);

    Optional<Task> findByTreeId(String treeId);
    // find , generate => 하위 탐색 => find, generate 트리 탐색 어떻게? dfs? bfs?

    // db 구성
    // taskId
    // sortId
    // treeId

}
