package org.lshh.skeleton.core.task.implement;

import org.lshh.skeleton.core.resource.argument.ArgumentsMap;
import org.lshh.skeleton.core.task.PipelineTask;
import org.lshh.skeleton.core.task.implement.AbstractTask;
import org.lshh.skeleton.core.task.Task;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SimplePipelineTask extends AbstractTask implements PipelineTask {
    Queue<Task> subTasks = new ConcurrentLinkedQueue<>();

    @Override
    public ArgumentsMap<String, Object> execute() {
        Task task = subTasks.poll();

        while(task != null) {
            Map<String, Object> result = task.getResults();
            Task nextTask = subTasks.poll();
            if(nextTask != null) {
                nextTask.setArgs(result);
            }else{
                super.results.putAll(result);
            }
            task = nextTask;
        }
        return null;
    }

    @Override
    public Task add(Task subTask) {
        subTasks.add(subTask);
        return this;
    }

    @Override
    public List<Task> getSubTasks() {
        return subTasks.stream().toList();
    }
}
