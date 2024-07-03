package org.lshh.skeleton.infrastructure.resource;

import org.lshh.skeleton.library.resource.resourcer.implement.ResourcerContext;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourcerJpaRepository extends JpaRepository<ResourcerContext, Long>{
}
