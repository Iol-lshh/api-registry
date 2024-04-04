package org.lshh.skeleton.sample.infrastructure.task;

import lombok.RequiredArgsConstructor;
import org.lshh.skeleton.core.task.TaskRepository;
import org.lshh.skeleton.core.task.implement.TaskContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class TaskRepositoryImplement implements TaskRepository {
    private final TaskJpaRepository repository;

    @Override
    public Optional<TaskContext> findContext(Long contextId) {
        return repository.findById(contextId);
    }

    @Override
    public List<TaskContext> findByStartId(Long startContextId) {
        return repository.findByStartId(startContextId);
    }

    @Override
    public List<TaskContext> findAllRoute() {
        return repository.findAllRoute();
    }

    @Override
    public Optional<TaskContext> findByTreeId(String treeId) {
        return repository.findByTreeId(treeId);
    }

    @Override
    public TaskContext create(TaskContext newOne) {
        return repository.save(newOne);
    }

    @Override
    public TaskContext update(TaskContext renewal) {
        return null;
    }
}
