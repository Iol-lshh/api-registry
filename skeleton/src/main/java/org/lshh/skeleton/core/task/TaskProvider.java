package org.lshh.skeleton.core.task;

import org.lshh.skeleton.core.task.dto.TaskCreateCommand;
import org.lshh.skeleton.core.task.dto.TaskUpdateCommand;
import org.lshh.skeleton.core.task.implement.TaskContext;

import java.util.List;
import java.util.Optional;

public interface TaskProvider {
    default Optional<Task> find(Long taskId) {
        Optional<TaskContext> mayContext = findContext(taskId);
        if(mayContext.isEmpty()){
            return Optional.empty();
        }
        TaskContext context = mayContext.get();
        // 한번에 디비에서 다 찾아와놓기
        List<TaskContext> list = findChildContextList(taskId);
        return Optional.of(generate(context, list));
    }
    default Optional<Task> findByTreeId(String treeId) {
        Optional<TaskContext> mayContext = findContextByTreeId(treeId);
        if(mayContext.isEmpty()){
            return Optional.empty();
        }
        TaskContext context = mayContext.get();
        List<TaskContext> list = findChildContextList(context.getId());
        return Optional.of(generate(context, list));
    }
    default List<Task> findAll() {
        List<TaskContext> list = findAllRouteContext();
        return list.stream()
                .map(context -> generate(context, findChildContextList(context.getId())))
                .toList();
    }

    Optional<TaskContext> findContext(Long contextId);
    Optional<TaskContext> findContextByTreeId(String treeId);
    List<TaskContext> findChildContextList(Long startContextId);
    List<TaskContext> findAllRouteContext();

    default Task generate(TaskContext context, List<TaskContext> list){
        return switch(context.getType()){
            case QUERY -> generateQueryTask(context);
            case PIPELINE -> generatePipelineTask(context, list);
            case LOCK -> generateLockTask(context);
            case PARALLEL -> generateParallelTask(context, list);
            default -> null;
        };
    }

    QueryTask generateQueryTask(TaskContext context);
    PipelineTask generatePipelineTask(TaskContext context, List<TaskContext> list);
    ParallelTask generateParallelTask(TaskContext context, List<TaskContext> list);
    LockTask generateLockTask(TaskContext context);

    List<TaskContext> filterChildren(TaskContext parent, List<TaskContext> list);

    TaskContext create(TaskCreateCommand command);
    TaskContext update(TaskUpdateCommand command);
}
