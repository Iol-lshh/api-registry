package org.lshh.skeleton.core.task.implement;

import jakarta.persistence.*;
import lombok.Getter;
import org.lshh.skeleton.core.task.Task.TaskType;
import org.lshh.skeleton.core.task.dto.TaskCreateCommand;
import org.lshh.skeleton.core.task.dto.TaskUpdateCommand;
import org.lshh.skeleton.core.task.exception.TaskContextException;

@Getter
@Entity
@Table(name = "task")
public class TaskContext {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String sortId;  // 2자리 문자열
    String treeId;  // treeId = parentTreeId + sortId (unique, changeable)
    TaskType type;  // QUERY, PIPELINE, PARALLEL, LOCK

    Long queryId;
    Long resourcerId;

    Long rollbackTaskId;    // rollback의 엔트리 task id

    public static TaskContext of(TaskCreateCommand command) {
        TaskContext context = new TaskContext();
        context.type = command.getType();
        context.queryId = command.getQueryId();
        context.resourcerId = command.getResourcerId();
        context.sortId = command.getSortId();
        context.setParentTreeId(command.getParentTreeId());
        context.rollbackTaskId = command.getRollbackTaskId();
        return context;
    }

    public static TaskContext of(TaskUpdateCommand command) {
        TaskContext context = new TaskContext();
        context.id = command.getId();
        context.type = command.getType();
        context.queryId = command.getQueryId();
        context.resourcerId = command.getResourcerId();
        context.sortId = command.getSortId();
        context.setParentTreeId(command.getParentTreeId());
        context.rollbackTaskId = command.getRollbackTaskId();
        return context;
    }

    public TaskContext setParentTreeId(String parentTreeId){
        if(parentTreeId == null){
            parentTreeId = "";
        } else if(parentTreeId.length() % 2 != 0) {
            throw new TaskContextException("Invalid parentTreeId");
        }

        this.treeId = parentTreeId + this.sortId;
        return this;
    }
    public TaskContext setSortId(String sortId){
        if(sortId == null || sortId.length() != 2){
            throw new TaskContextException("Invalid sortId");
        }
        this.sortId = sortId;
        if(treeId != null && treeId.length() > 2){
            this.setParentTreeId(this.treeId.substring(0, this.treeId.length() - this.sortId.length()));
        }else{
            this.treeId = this.sortId;
        }
        return this;
    }
    public TaskContext setTreeId(String treeId){
        if(treeId != null && treeId.length() % 2 == 0){
            this.treeId = treeId;
            this.sortId = treeId.substring(treeId.length() - 2);
        }else{
            throw new TaskContextException("Invalid treeId");
        }
        return this;
    }

    public String getParentTreeId() {
        return treeId.substring(0, treeId.length() - 2);
    }

    public TaskContext setId(Long id) {
        this.id = id;
        return this;
    }
}
