package org.lshh.skeleton.core.task.implement;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.lshh.skeleton.core.task.Task.TaskType;

import java.util.List;

@Getter
@Entity
@Table(name = "task")
public class TaskContext {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    TaskType type;

    Long queryId;
    Long resourceId;

    @ManyToOne
    @JoinColumn(name = "parent_task_id")
    TaskContext parentTask;

    @OneToMany(mappedBy = "parentTask")
    List<TaskContext> subTasks;

    public static TaskContext of(TaskType type, @Nullable Long queryId, @Nullable Long resourceId) {
        TaskContext context = new TaskContext();
        context.type = type;
        context.queryId = queryId;
        context.resourceId = resourceId;
        return context;
    }

    public static TaskContext of(TaskType type, @Nullable TaskContext parentTask, @Nullable List<TaskContext> subTasks) {
        TaskContext context = new TaskContext();
        context.type = type;
        context.parentTask = parentTask;
        context.subTasks = subTasks;
        return context;
    }
}
