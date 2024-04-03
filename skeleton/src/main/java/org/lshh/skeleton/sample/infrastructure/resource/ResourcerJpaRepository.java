package org.lshh.skeleton.sample.infrastructure.resource;

import org.lshh.skeleton.core.resource.resourcer.implement.ResourcerContext;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourcerJpaRepository extends JpaRepository<ResourcerContext, Long>{
}
