package org.lshh.skeleton.core.task.implement;

import org.lshh.skeleton.core.resource.argument.ArgumentsMap;
import org.lshh.skeleton.core.task.PipelineTask;
import org.lshh.skeleton.core.task.Task;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SimplePipelineTask extends AbstractTask implements PipelineTask {
    Queue<Task> subTasks = new ConcurrentLinkedQueue<>();

    protected SimplePipelineTask(TaskContext context, Map<String, Object> args) {
        super(context, args);
    }

    public static PipelineTask of(TaskContext context, Map<String, Object> args) {
        return new SimplePipelineTask(context, args);
    }

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
    public Task copy() {
        PipelineTask task = SimplePipelineTask.of(context, args);
        subTasks.forEach(subtask -> task.add(subtask.copy()));
        return task;
    }

    @Override
    public Long getId() {
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
