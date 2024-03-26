package org.lshh.skeleton.core.router.implement;

import lombok.RequiredArgsConstructor;
import org.lshh.skeleton.core.router.Router;
import org.lshh.skeleton.core.router.RouterManager;
import org.lshh.skeleton.core.router.RouterProvider;
import org.lshh.skeleton.core.router.dto.command.RouterCreateCommand;
import org.lshh.skeleton.core.router.dto.command.RouterUpdateCommand;
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
        if(isCached(path)){
            return routerMap.get(path);
        }

        // repository
        Optional<Router> mayRouter = provider.findByPath(path);
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

    @Override
    public void clearCache() {
        routerMap.clear();
    }

    @Override
    public boolean isCached(String path) {
        return routerMap.containsKey(path);
    }
}
