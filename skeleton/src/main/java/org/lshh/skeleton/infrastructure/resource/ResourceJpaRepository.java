package org.lshh.skeleton.infrastructure.resource;

import org.lshh.skeleton.core.resource.resource.implement.ResourceContext;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceJpaRepository extends JpaRepository<ResourceContext, Long>{
}
