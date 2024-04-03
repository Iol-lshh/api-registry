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
    public List<TaskContext> findContextList(Long startContextId){
        return  repository.findByStartId(startContextId);
    }

    @Override
    public Task generate(TaskContext context, List<TaskContext> list){
        return switch(context.getType()){
            case QUERY -> generateQueryTask(context);
            case PIPELINE -> generatePipelineTask(context, list);
            case LOCK -> generateLockTask(context);
            case PARALLEL -> generateParallelTask(context, list);
            default -> null;
        };
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
        // 재귀 조회로 하위 태스크를 찾아서 추가
        for(TaskContext child : list){
            pipelineTask.add(generate(child, findContextList(child.getId())));
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
    public Optional<Task> find(Long taskId) {
        Optional<TaskContext> mayContext = findContext(taskId);
        if(mayContext.isEmpty()){
            return Optional.empty();
        }
        TaskContext context = mayContext.get();
        // 한번에 디비에서 다 찾아와놓기
        List<TaskContext> list = findContextList(taskId);

        return Optional.of(generate(context, list));
    }

    @Override
    public List<Task> findAll() {
        List<TaskContext> list = findAllRouteContext();
        return list.stream()
                .map(context -> generate(context, findContextList(context.getId())))
                .toList();
    }

    @Override
    public List<TaskContext> findAllRouteContext(){
        // todo 스타트 컨텍스트만 가져온다. (조건: treeId == sortId)
        return repository.findAllRoute();
    }

    @Override
    public Task create(TaskCreateCommand command) {
        // 캐싱하지 않는다.
        return repository.create(command);
    }

    @Override
    public Task update(TaskUpdateCommand command) {
        // todo 수정 후, 해당 루트 테스크를 초기화한다...

        return null;
    }

    @Override
    public Optional<Task> findByTreeId(String treeId) {
        return repository.findByTreeId(treeId);
    }
}
