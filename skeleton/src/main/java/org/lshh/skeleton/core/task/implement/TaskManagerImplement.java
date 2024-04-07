package org.lshh.skeleton.core.task.implement;

import org.lshh.skeleton.core.task.Task;
import org.lshh.skeleton.core.task.TaskManager;
import org.lshh.skeleton.core.task.TaskProvider;
import org.lshh.skeleton.core.task.dto.RollbackEvent;
import org.lshh.skeleton.core.task.dto.TaskCreateCommand;
import org.lshh.skeleton.core.task.dto.TaskUpdateCommand;
import org.springframework.context.event.EventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TaskManagerImplement implements TaskManager {

    private final Map<Long, Task> emptyTaskCacheMap = new HashMap<>();
    private final TaskProvider provider;

    public TaskManagerImplement(TaskProvider provider){
        this.provider = provider;
    }

    @Override
    public Task find(Long startTaskId) {
        // cache hit - 프로토타입 패턴: 미리 태스크를 만들어두고, 카피해서 사용
        if(emptyTaskCacheMap.containsKey(startTaskId)){
            return emptyTaskCacheMap.get(startTaskId).copy();
        }

        // cache miss - DB에서 태스크를 가져와서 캐시에 저장
        Optional<Task> mayTask = provider.find(startTaskId);
        if(mayTask.isPresent()){
            Task task = mayTask.get();
            emptyTaskCacheMap.put(startTaskId, task);
            return task.copy();
        }

        // not found
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

    @Override
    public List<Task> findAll(){
        List<Task> list = provider.findAll();
        list.forEach(task -> emptyTaskCacheMap.put(task.getId(), task));
        return list;
    }

    @Override
    public Integer create(TaskCreateCommand command){
        TaskContext context = provider.create(command);
        String rootTreeId = context.getTreeId().substring(0, 2);
        refresh(rootTreeId);
        return 1;
    }

    @Override
    public Integer update(TaskUpdateCommand command){
        TaskContext context = provider.update(command);
        String rootTreeId = context.getTreeId().substring(0, 2);
        refresh(rootTreeId);
        return 1;
    }

    @Override
    public Task refresh(String treeId){
        Optional<Task> mayTask = provider.findByTreeId(treeId);
        if(mayTask.isPresent()){
            Task task = mayTask.get();
            emptyTaskCacheMap.put(task.getId(), task);
            return task.copy();
        }
        return null;
    }


    @Override
    public void clearCache() {
        emptyTaskCacheMap.clear();
    }

    @Override
    public boolean isCached(Long id) {
        return emptyTaskCacheMap.containsKey(id);
    }
}
