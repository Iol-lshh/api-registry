package org.lshh.skeleton.sample.infrastructure.router;

import org.lshh.skeleton.core.router.implement.RouterContext;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RouterJpaRepository extends JpaRepository<RouterContext, Long>{

    Optional<RouterContext> findByPath(String path);
}
