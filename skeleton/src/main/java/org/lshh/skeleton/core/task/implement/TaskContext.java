package org.lshh.skeleton.core.task.implement;

import jakarta.persistence.*;
import lombok.Getter;
import org.lshh.skeleton.core.task.Task.TaskType;

@Getter
@Entity
@Table(name = "task")
public class TaskContext {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    TaskType type;
}
