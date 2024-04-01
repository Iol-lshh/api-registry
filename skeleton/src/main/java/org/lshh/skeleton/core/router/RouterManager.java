package org.lshh.skeleton.core.router;

import org.lshh.skeleton.core.router.dto.RouterCreateCommand;
import org.lshh.skeleton.core.router.dto.RouterUpdateCommand;

import java.util.List;

public interface RouterManager {
    // 캐싱
    // 영속성

    Router findByPath(String path);
    List<Router> findAll();
    Router create(RouterCreateCommand command);
    Router update(RouterUpdateCommand command);
    void clearCache();
    boolean isCached(String path);
}
