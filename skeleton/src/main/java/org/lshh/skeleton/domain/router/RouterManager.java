package org.lshh.skeleton.domain.router;

import org.lshh.skeleton.domain.router.command.RouterCreateCommand;
import org.lshh.skeleton.domain.router.command.RouterUpdateCommand;

import java.util.List;

public interface RouterManager {
    Router findByPath(String path);
    List<Router> findAll();
    Router create(RouterCreateCommand command);
    Router update(RouterUpdateCommand command);
}
