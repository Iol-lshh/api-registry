package org.lshh.skeleton.sample.infrastructure.resource;

import lombok.RequiredArgsConstructor;
import org.lshh.skeleton.core.resource.resourcer.ResourcerRepository;
import org.lshh.skeleton.core.resource.resourcer.implement.ResourcerContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ResourcerRepositoryImplement implements ResourcerRepository {
    private final ResourcerJpaRepository repository;

    @Override
    public Optional<ResourcerContext> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<ResourcerContext> findAll() {
        return repository.findAll();
    }

    @Override
    public ResourcerContext create(ResourcerContext newOne) {
        return repository.save(newOne);
    }

    @Override
    public ResourcerContext update(ResourcerContext renewal) {
        return repository.save(renewal);
    }
}
