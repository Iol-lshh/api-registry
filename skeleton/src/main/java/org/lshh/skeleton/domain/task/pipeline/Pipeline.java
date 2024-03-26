package org.lshh.skeleton.domain.task.pipeline;

import org.lshh.skeleton.domain.task.Task;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public interface Pipeline extends Task {

    // 묶음 열어 순차적으로 처리(직렬화)
    Task add(Task subTask);
    List<Task> getSubTasks();
}
