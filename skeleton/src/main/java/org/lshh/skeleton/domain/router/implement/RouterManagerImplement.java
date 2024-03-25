package org.lshh.skeleton.domain.router.implement;

import lombok.RequiredArgsConstructor;
import org.lshh.skeleton.domain.router.Router;
import org.lshh.skeleton.domain.router.RouterManager;
import org.lshh.skeleton.domain.router.RouterProvider;
import org.lshh.skeleton.domain.router.command.RouterCreateCommand;
import org.lshh.skeleton.domain.router.command.RouterUpdateCommand;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class RouterManagerImplement implements RouterManager {
    private final Map<String, Router> routerMap = new HashMap<>();
    private final RouterProvider provider;


    public Router findByPath(String path){
        // cache
        Optional<Router> mayRouter = Optional.ofNullable(routerMap.get(path));
        if(mayRouter.isPresent()){
            return mayRouter.get();
        }

        // repository
        mayRouter = provider.findByPath(path);
        if(mayRouter.isPresent()){
            routerMap.put(path, mayRouter.get());
            return mayRouter.get();
        }

        // fail
        return null;
    }

    @Override
    public List<Router> findAll() {
        List<Router> routerList = provider.findAll();
        routerList.forEach(router -> routerMap.put(router.getPath(), router));
        return routerList;
    }

    @Override
    public Router create(RouterCreateCommand command) {
        Router router = provider.create(command);
        routerMap.put(router.getPath(), router);
        return router;
    }

    @Override
    public Router update(RouterUpdateCommand command) {
        Router router = provider.update(command);
        routerMap.put(router.getPath(), router);
        return router;
    }
}
