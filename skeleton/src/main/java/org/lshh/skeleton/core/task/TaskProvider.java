package org.lshh.skeleton.core.task;

import org.lshh.skeleton.core.task.implement.TaskContext;

import java.util.Optional;

public interface TaskProvider {
    TaskContext findContext(Long contextId);
    Task generate(Long contextId);

    Optional<Task> find(Long taskId);
    // find , generate => 하위 탐색 => find, generate 트리 탐색 어떻게? dfs? bfs?

    // db 구성
    // taskId
    // sortId
    // treeId

}
