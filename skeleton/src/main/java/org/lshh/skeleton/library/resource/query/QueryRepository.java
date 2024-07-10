package org.lshh.skeleton.library.resource.query;

import org.lshh.skeleton.library.ContextRepository;
import org.lshh.skeleton.library.resource.query.implement.QueryContext;

import java.util.Optional;

public interface QueryRepository extends ContextRepository<QueryContext> {
    Optional<QueryContext> findById(Long id);

    QueryContext create(QueryContext newOne);

    QueryContext update(QueryContext renewal);
}
