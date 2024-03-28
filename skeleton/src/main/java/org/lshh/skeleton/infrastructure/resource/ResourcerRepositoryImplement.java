package org.lshh.skeleton.infrastructure.resource;

import lombok.RequiredArgsConstructor;
import org.lshh.skeleton.core.resource.resourcer.Resourcer;
import org.lshh.skeleton.core.resource.resourcer.ResourcerRepository;
import org.lshh.skeleton.core.resource.resourcer.implement.ResourcerContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ResourcerRepositoryImplement implements ResourcerRepository {
    private final ResourcerJpaRepository resourceJpaRepository;

    @Override
    public Optional<Resourcer> findById(Long resourceId) {
        return Optional.empty();
    }

    @Override
    public List<Resourcer> findAll() {
        return null;
    }

    @Override
    public ResourcerContext create(ResourcerContext newOne) {
        return null;
    }

    @Override
    public ResourcerContext update(ResourcerContext renewal) {
        return null;
    }
}
