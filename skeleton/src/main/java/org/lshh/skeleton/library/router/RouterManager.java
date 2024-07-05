package org.lshh.skeleton.library.router;

import org.lshh.skeleton.library.router.dto.command.RouterCreateCommand;
import org.lshh.skeleton.library.router.dto.command.RouterUpdateCommand;

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
