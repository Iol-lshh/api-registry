package org.lshh.skeleton.sample.infrastructure.task;

import org.lshh.skeleton.core.task.implement.TaskContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TaskJpaRepository extends JpaRepository<TaskContext, Long>{

    @Query(nativeQuery = true, value = "select 1")
    List<TaskContext> findByStartId(Long startContextId);

    @Query(nativeQuery = true, value = "select 1")
    List<TaskContext> findAllRoute();

    @Query(nativeQuery = true, value = "select 1")
    Optional<TaskContext> findByTreeId(String treeId);
}
