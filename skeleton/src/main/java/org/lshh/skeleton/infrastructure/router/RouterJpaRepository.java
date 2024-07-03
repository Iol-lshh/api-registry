package org.lshh.skeleton.infrastructure.router;

import org.lshh.skeleton.library.router.implement.RouterContext;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RouterJpaRepository extends JpaRepository<RouterContext, Long>{

    Optional<RouterContext> findByPath(String path);
}
