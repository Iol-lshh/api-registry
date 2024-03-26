package org.lshh.skeleton.infrastructure.resource;

import lombok.RequiredArgsConstructor;
import org.lshh.skeleton.core.resource.query.QueryRepository;
import org.lshh.skeleton.core.resource.query.implement.QueryContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class QueryRepositoryImplement implements QueryRepository {
    private final QueryJpaRepository repository;

    @Override
    public Optional<QueryContext> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public QueryContext create(QueryContext newOne) {
        return repository.save(newOne);
    }

    @Override
    public QueryContext update(QueryContext renewal) {
        return repository.save(renewal);
    }
}
