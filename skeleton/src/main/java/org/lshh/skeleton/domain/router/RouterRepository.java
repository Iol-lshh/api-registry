package org.lshh.skeleton.domain.router;

import org.lshh.skeleton.domain.router.implement.RouterContext;

import java.util.List;
import java.util.Optional;

public interface RouterRepository {
    Optional<RouterContext> findByPath(String path);

    List<RouterContext> findAll();

    RouterContext create(RouterContext newOne);

    RouterContext update(RouterContext renewal);
}
