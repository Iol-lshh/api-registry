package org.lshh.skeleton.core.task.implement;

import org.lshh.skeleton.core.resource.query.Query;
import org.lshh.skeleton.core.resource.query.QueryManager;
import org.lshh.skeleton.core.resource.query.implement.SimpleQueryTask;
import org.lshh.skeleton.core.resource.resourcer.JdbcResourcer;
import org.lshh.skeleton.core.resource.resourcer.ResourcerManager;
import org.lshh.skeleton.core.task.*;
import org.lshh.skeleton.core.task.dto.TaskCreateCommand;
import org.lshh.skeleton.core.task.dto.TaskUpdateCommand;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TaskProviderImplement implements TaskProvider {

    private final TaskRepository repository;
    private final ResourcerManager resourcerManager;
    private final QueryManager queryManager;


    private TaskProviderImplement(TaskRepository repository, ResourcerManager resourcerManager, QueryManager queryManager){
        this.repository = repository;
        this.resourcerManager = resourcerManager;
        this.queryManager = queryManager;
    }
    public static TaskProvider of(TaskRepository repository, ResourcerManager resourcerManager, QueryManager queryManager){
        return new TaskProviderImplement(repository, resourcerManager, queryManager);
    }
    @Override
    public Optional<TaskContext> findContext(Long contextId) {
        return repository.findContext(contextId);
    }
    @Override
    public List<TaskContext> findChildContextList(Long startContextId){
        return  repository.findByStartId(startContextId);
    }
    @Override
    public Optional<TaskContext> findContextByTreeId(String treeId) {
        return repository.findByTreeId(treeId);
    }
    @Override
    public QueryTask generateQueryTask(TaskContext context){
        JdbcResourcer resourcer = resourcerManager.find(context.getResourcerId()).as(JdbcResourcer.class);
        Query query = queryManager.find(context.getQueryId(), resourcer.getDataSource());
        return SimpleQueryTask.of(context, query, Map.of());
    }
    @Override
    public PipelineTask generatePipelineTask(TaskContext context, List<TaskContext> list){
        PipelineTask pipelineTask = SimplePipelineTask.of(context, Map.of());
        // list에서 자식 태스크를 필터링
        List<TaskContext> children = filterChildren(context, list);
        // 자식 태스크 순회 (DFS)
        for(TaskContext childContext : children){
            // 재귀 조회로 자식 태스크를 생성하고 추가
            // - 디비 조회할 필요 없게, 리스트 재활용
            Task childTask = generate(childContext, list);
            pipelineTask.add(childTask);
        }
        return pipelineTask;
    }
    @Override
    public ParallelTask generateParallelTask(TaskContext context, List<TaskContext> list){
        // todo 병렬(경합?) 테스크...
        return null;
    }
    @Override
    public LockTask generateLockTask(TaskContext context){
        // todo 락 두 종류로... 비관 낙관
        return null;
    }
    @Override
    public List<TaskContext> findAllRouteContext(){
        // todo 스타트 컨텍스트만 가져온다. (조건: treeId == sortId)
        return repository.findAllRoute();
    }
    @Override
    public List<TaskContext> filterChildren(TaskContext parent, List<TaskContext> list){
        return list.stream()
                .filter(child -> child.getParentTreeId().equals(parent.getTreeId()))
                .toList();
    }
    @Override
    public TaskContext create(TaskCreateCommand command) {
        TaskContext newOne = TaskContext.of(command);
        return repository.create(newOne);
    }
    @Override
    public TaskContext update(TaskUpdateCommand command) {
        TaskContext renewal = TaskContext.of(command);
        return repository.update(renewal);
    }
}
