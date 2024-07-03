package org.lshh.skeleton.library.router;

import org.lshh.skeleton.library.router.dto.command.RouterCreateCommand;
import org.lshh.skeleton.library.router.dto.command.RouterUpdateCommand;
import org.lshh.skeleton.library.router.implement.RouterContext;
import org.lshh.skeleton.library.router.implement.SimpleRouter;

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
            return SimpleRouter.of(context);
        }
    }
}