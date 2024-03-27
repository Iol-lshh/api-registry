package org.lshh.skeleton.infrastructure.resource;

import lombok.RequiredArgsConstructor;
import org.lshh.skeleton.core.resource.resource.ResourceRepository;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ResourceRepositoryImplement implements ResourceRepository {
    private final ResourceJpaRepository resourceJpaRepository;

}
