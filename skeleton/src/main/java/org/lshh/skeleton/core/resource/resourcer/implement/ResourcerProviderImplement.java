package org.lshh.skeleton.core.resource.resourcer.implement;

import org.lshh.skeleton.core.resource.resourcer.Resourcer;
import org.lshh.skeleton.core.resource.resourcer.ResourcerProvider;
import org.lshh.skeleton.core.resource.resourcer.ResourcerRepository;
import org.lshh.skeleton.core.resource.resourcer.dto.ResourcerCreateCommand;
import org.lshh.skeleton.core.resource.resourcer.dto.ResourcerUpdateCommand;

import java.util.List;
import java.util.Optional;

public class ResourcerProviderImplement implements ResourcerProvider {
    ResourcerRepository repository;
    private ResourcerProviderImplement(ResourcerRepository repository) {
        this.repository = repository;
    }
    public static ResourcerProvider of(ResourcerRepository repository) {
        return new ResourcerProviderImplement(repository);
    }

    @Override
    public Optional<Resourcer> find(Long resourceId) {
        return repository.findById(resourceId)
                .map(Resourcers::of);
    }

    @Override
    public List<Resourcer> findAll() {
        return repository.findAll()
                .stream()
                .map(Resourcers::of)
                .toList();
    }

    @Override
    public Resourcer create(ResourcerCreateCommand command) {
        ResourcerContext newOne = ResourcerContext.of(command);
        newOne = repository.create(newOne);
        return Resourcers.of(newOne);
    }

    @Override
    public Resourcer update(ResourcerUpdateCommand command) {
        ResourcerContext renewal = ResourcerContext.of(command);
        renewal = repository.update(renewal);
        return Resourcers.of(renewal);
    }
}
