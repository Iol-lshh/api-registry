package org.lshh.skeleton.infrastructure.resource;

import lombok.RequiredArgsConstructor;
import org.lshh.skeleton.core.resource.query.QueryRepository;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class QueryRepositoryImplement implements QueryRepository {
    private final QueryJpaRepository repository;
}
