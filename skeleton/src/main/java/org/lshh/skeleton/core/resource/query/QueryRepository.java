package org.lshh.skeleton.core.resource.query;

import org.lshh.skeleton.core.resource.query.implement.QueryContext;

import java.util.Optional;

public interface QueryRepository {
    Optional<QueryContext> findById(Long id);

    QueryContext create(QueryContext newOne);

    QueryContext update(QueryContext renewal);
}
