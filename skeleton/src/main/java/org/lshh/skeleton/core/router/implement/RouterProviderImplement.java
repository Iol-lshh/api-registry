package org.lshh.skeleton.core.router.implement;

import org.lshh.skeleton.core.router.Router;
import org.lshh.skeleton.core.router.RouterProvider;
import org.lshh.skeleton.core.router.RouterRepository;
import org.lshh.skeleton.core.router.dto.RouterCreateCommand;
import org.lshh.skeleton.core.router.dto.RouterUpdateCommand;

import java.util.List;
import java.util.Optional;

public class RouterProviderImplement implements RouterProvider {
    private final RouterRepository repository;

    private RouterProviderImplement(RouterRepository repository) {
        this.repository = repository;
    }
    public static RouterProvider of(RouterRepository repository) {
        return new RouterProviderImplement(repository);
    }

    @Override
    public Optional<Router> findByPath(String path){
        return repository.findByPath(path)
                .map(Routers::of);
    }

    @Override
    public List<Router> findAll() {
        return repository.findAll()
                .stream()
                .map(Routers::of)
                .toList();
    }

    @Override
    public Router create(RouterCreateCommand command) {
        RouterContext newOne = RouterContext.of(command);
        newOne = repository.create(newOne);
        return Routers.of(newOne);
    }

    @Override
    public Router update(RouterUpdateCommand command) {
        RouterContext renewal = RouterContext.of(command);
        renewal = repository.update(renewal);
        return Routers.of(renewal);
    }
}