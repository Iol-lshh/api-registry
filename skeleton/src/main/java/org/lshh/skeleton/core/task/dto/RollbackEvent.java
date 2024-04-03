package org.lshh.skeleton.core.task.dto;

import lombok.Getter;

import java.util.Map;

@Getter
public class RollbackEvent {
    Long taskId;
    Map<String, Object> args;

    public RollbackEvent(Long rollbackTaskId, Map<String, Object> args) {
        this.taskId = rollbackTaskId;
        this.args = args;
    }

}
