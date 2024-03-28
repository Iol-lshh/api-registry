package org.lshh.skeleton.core.resource.resourcer;

import org.lshh.skeleton.core.resource.resourcer.dto.ResourcerCreateCommand;
import org.lshh.skeleton.core.resource.resourcer.dto.ResourcerUpdateCommand;

import java.util.List;

public interface ResourcerManager {
    Resourcer find(Long resourceId);
    List<Resourcer> findAll();
    Resourcer create(ResourcerCreateCommand command);
    Resourcer update(ResourcerUpdateCommand command);
    void clearCache();
    boolean isCached(Long id);
}
