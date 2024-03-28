package org.lshh.skeleton.infrastructure.resource;

import lombok.RequiredArgsConstructor;
import org.lshh.skeleton.core.resource.resourcer.ResourcerRepository;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ResourcerRepositoryImplement implements ResourcerRepository {
    private final ResourcerJpaRepository resourceJpaRepository;

}
