package org.lshh.skeleton.core.resource.resourcer;

import org.lshh.skeleton.core.resource.resourcer.implement.ResourcerContext;

import java.util.List;
import java.util.Optional;

public interface ResourcerRepository {
    Optional<ResourcerContext> findById(Long resourceId);

    List<ResourcerContext> findAll();

    ResourcerContext create(ResourcerContext newOne);

    ResourcerContext update(ResourcerContext renewal);
}
