package org.lshh.skeleton.library.resource.query;

import org.lshh.skeleton.library.resource.query.implement.QueryContext;

import java.util.Optional;

public interface QueryRepository {
    Optional<QueryContext> findById(Long id);

    QueryContext create(QueryContext newOne);

    QueryContext update(QueryContext renewal);
}
