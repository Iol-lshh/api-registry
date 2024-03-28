package org.lshh.skeleton.core.router.implement;

import org.lshh.skeleton.core.router.Router;
import org.lshh.skeleton.core.router.RouterProvider;
import org.lshh.skeleton.core.router.RouterRepository;
import org.lshh.skeleton.core.router.dto.command.RouterCreateCommand;
import org.lshh.skeleton.core.router.dto.command.RouterUpdateCommand;

import java.util.List;
import java.util.Optional;

public class RouterProviderImplement implements RouterProvider {
    private final RouterRepository routerRepository;

    private RouterProviderImplement(RouterRepository routerRepository) {
        this.routerRepository = routerRepository;
    }
    public static RouterProvider of(RouterRepository routerRepository) {
        return new RouterProviderImplement(routerRepository);
    }

    @Override
    public Optional<Router> findByPath(String path){
        return routerRepository.findByPath(path)
                .map(Routers::of);
    }

    @Override
    public List<Router> findAll() {
        return routerRepository.findAll()
                .stream()
                .map(Routers::of)
                .toList();
    }

    @Override
    public Router create(RouterCreateCommand command) {
        RouterContext newOne = RouterContext.of(command);
        newOne = routerRepository.create(newOne);
        return Routers.of(newOne);
    }

    @Override
    public Router update(RouterUpdateCommand command) {
        RouterContext renewal = RouterContext.of(command);
        renewal = routerRepository.update(renewal);
        return Routers.of(renewal);
    }
}