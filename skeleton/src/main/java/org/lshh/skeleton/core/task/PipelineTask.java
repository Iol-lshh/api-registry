package org.lshh.skeleton.core.task;

import java.util.List;

public interface PipelineTask extends Task {

    // 묶음 열어 순차적으로 처리(직렬화)
    Task add(Task subTask);
    List<Task> getSubTasks();
}