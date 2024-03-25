package org.lshh.skeleton.domain.router;

import org.lshh.skeleton.domain.router.command.RouterCreateCommand;
import org.lshh.skeleton.domain.router.command.RouterUpdateCommand;
import org.lshh.skeleton.domain.router.implement.RouterContext;
import org.lshh.skeleton.domain.router.implement.RouterImplement;

import java.util.List;
import java.util.Optional;

public interface RouterProvider {

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