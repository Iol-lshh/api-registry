package org.lshh.skeleton.core.router;

import org.lshh.skeleton.core.router.command.RouterCreateCommand;
import org.lshh.skeleton.core.router.command.RouterUpdateCommand;
import org.lshh.skeleton.core.router.implement.RouterContext;
import org.lshh.skeleton.core.router.implement.RouterImplement;

import java.util.List;
import java.util.Optional;

public interface RouterProvider {
    // 영속성 제공

    Optional<Router> findByPath(String path);

    List<Router> findAll();

    Router create(RouterCreateCommand command);

    Router update(RouterUpdateCommand command);

    class Routers{
        public static Router of(RouterContext context){
            return RouterImplement.of(context);
        }
    }
}