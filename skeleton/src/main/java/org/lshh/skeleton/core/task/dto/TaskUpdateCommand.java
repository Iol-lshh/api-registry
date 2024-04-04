package org.lshh.skeleton.core.task.dto;

import lombok.Getter;
import org.lshh.skeleton.core.task.Task.TaskType;

@Getter
public class TaskUpdateCommand {
    Long id;
    String sortId;
    String parentTreeId;
    TaskType type;  // QUERY, PIPELINE, PARALLEL, LOCK

    Long queryId;
    Long resourcerId;

    Long rollbackTaskId;

    public static TaskUpdateCommand of(Long id, String sortId, String parentTreeId, TaskType type, Long queryId, Long resourcerId, Long rollbackTaskId) {
        TaskUpdateCommand command = new TaskUpdateCommand();
        command.id = id;
        command.sortId = sortId;
        command.parentTreeId = parentTreeId;
        command.type = type;
        command.queryId = queryId;
        command.resourcerId = resourcerId;
        command.rollbackTaskId = rollbackTaskId;
        return command;
    }
}
