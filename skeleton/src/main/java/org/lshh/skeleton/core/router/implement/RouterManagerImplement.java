package org.lshh.skeleton.core.router.implement;

import org.lshh.skeleton.core.router.Router;
import org.lshh.skeleton.core.router.RouterManager;
import org.lshh.skeleton.core.router.RouterProvider;
import org.lshh.skeleton.core.router.dto.command.RouterCreateCommand;
import org.lshh.skeleton.core.router.dto.command.RouterUpdateCommand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RouterManagerImplement implements RouterManager {
    private final Map<String, Router> cacheMap = new HashMap<>();
    private final RouterProvider provider;

    private RouterManagerImplement(RouterProvider provider){
        this.provider = provider;
    }

    public static RouterManager of(RouterProvider provider){
        return new RouterManagerImplement(provider);
    }

    public Router findByPath(String path){
        // cache
        if(isCached(path)){
            return cacheMap.get(path);
        }

        // repository
        Optional<Router> mayRouter = provider.findByPath(path);
        if(mayRouter.isPresent()){
            cacheMap.put(path, mayRouter.get());
            return mayRouter.get();
        }

        // fail
        return null;
    }

    @Override
    public List<Router> findAll() {
        List<Router> routerList = provider.findAll();
        routerList.forEach(router -> cacheMap.put(router.getPath(), router));
        return routerList;
    }

    @Override
    public Router create(RouterCreateCommand command) {
        Router router = provider.create(command);
        cacheMap.put(router.getPath(), router);
        return router;
    }

    @Override
    public Router update(RouterUpdateCommand command) {
        Router router = provider.update(command);
        cacheMap.put(router.getPath(), router);
        return router;
    }

    @Override
    public void clearCache() {
        cacheMap.clear();
    }

    @Override
    public boolean isCached(String path) {
        return cacheMap.containsKey(path);
    }
}
