package org.lshh.skeleton.infrastructure.resource;

import org.lshh.skeleton.core.resource.query.implement.QueryContext;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueryJpaRepository extends JpaRepository<QueryContext, Long> {
}
