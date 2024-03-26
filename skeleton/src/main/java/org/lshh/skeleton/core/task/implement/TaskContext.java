package org.lshh.skeleton.core.task.implement;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "task")
public class TaskContext {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
}
