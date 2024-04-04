package org.lshh.skeleton.core.task.dto;

import lombok.Getter;
import org.lshh.skeleton.core.task.Task.TaskType;
import org.lshh.skeleton.core.task.exception.TaskException;

@Getter
public class TaskCreateCommand {
    String sortId;
    String parentTreeId;
    TaskType type;  // QUERY, PIPELINE, PARALLEL, LOCK

    Long queryId;
    Long resourcerId;

    Long rollbackTaskId;

    public static TaskCreateCommand of(String sortId, String parentTreeId, TaskType type, Long rollbackTaskId){
        TaskCreateCommand command = new TaskCreateCommand();
        command.sortId = sortId;
        command.parentTreeId = parentTreeId;
        command.type = type;
        command.rollbackTaskId = rollbackTaskId;
        return command;
    }

    public static TaskCreateCommand of(String sortId, String parentTreeId, TaskType type, Long queryId, Long resourcerId, Long rollbackTaskId) {
        if(type != TaskType.QUERY) {
            throw new TaskException("Invalid type for queryId and resourcerId");
        }

        TaskCreateCommand command = new TaskCreateCommand();
        command.sortId = sortId;
        command.parentTreeId = parentTreeId;
        command.type = type;
        command.queryId = queryId;
        command.resourcerId = resourcerId;
        command.rollbackTaskId = rollbackTaskId;
        return command;
    }
}
