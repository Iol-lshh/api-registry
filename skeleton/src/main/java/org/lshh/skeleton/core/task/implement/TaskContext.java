package org.lshh.skeleton.core.task.implement;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.lshh.skeleton.core.task.Task.TaskType;
import org.lshh.skeleton.core.task.exception.TaskException;

import java.util.List;

@Getter
@Entity
@Table(name = "task")
public class TaskContext {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String sortId;
    String treeId; // treeId = parentTreeId + sortId
    TaskType type;  // QUERY, PIPELINE, PARALLEL, LOCK

    Long queryId;
    Long resourcerId;

    Long rollbackTaskId;    // rollback의 엔트리 task id

    public TaskContext setTreeId(String parentTreeId){
        this.treeId = parentTreeId + this.sortId;
        return this;
    }
    public TaskContext setSortId(String sortId){
        this.sortId = sortId;
        if(treeId != null && treeId.length() >= sortId.length()){
            this.setTreeId(this.treeId.substring(0, this.treeId.length() - this.sortId.length()));
        }else{
            setTreeId("");
        }
        return this;
    }

    public static TaskContext of(TaskType type, Long queryId, Long resourcerId, String sortId, String parentTreeId, @Nullable Long rollbackTaskId) {
        if(type != TaskType.QUERY){
            throw new TaskException("TaskType is not QUERY");
        }
        TaskContext context = new TaskContext();
        context.type = type;
        context.queryId = queryId;
        context.resourcerId = resourcerId;
        context.rollbackTaskId = rollbackTaskId;
        context.sortId = sortId;
        context.setTreeId(parentTreeId);
        return context;
    }

    public static TaskContext of(TaskType type, String sortId, String parentTreeId, @Nullable Long rollbackTaskId){
        TaskContext context = new TaskContext();
        context.type = type;
        context.rollbackTaskId = rollbackTaskId;
        context.sortId = sortId;
        context.setTreeId(parentTreeId);
        return context;
    }
}
