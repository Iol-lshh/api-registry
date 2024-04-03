package org.lshh.skeleton.core.task.implement;

import org.lshh.skeleton.core.task.Task;
import org.lshh.skeleton.core.task.TaskManager;
import org.lshh.skeleton.core.task.TaskProvider;
import org.lshh.skeleton.core.task.dto.RollbackEvent;
import org.springframework.context.event.EventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TaskManagerImplement implements TaskManager {

    private final Map<Long, Task> emptyTaskCacheMap = new HashMap<>();
    private TaskProvider provider;

    public TaskManagerImplement(TaskProvider provider){
        this.provider = provider;
    }

    @Override
    public Task generate(Long startTaskId) {
        // cache hit
        if(emptyTaskCacheMap.containsKey(startTaskId)){
            return emptyTaskCacheMap.get(startTaskId).copy();
        }

        //

        return null;
    }

    @Override
    @EventListener
    public void handleRollback(RollbackEvent event) {
        System.out.println("Rollback event handled - " + event.getTaskId());
        Optional<Task> mayTask = provider.find(event.getTaskId());
        if(mayTask.isPresent()){
            Task task = mayTask.get();
            task.setArgs(event.getArgs());
            task.execute();
        }
    }
}
