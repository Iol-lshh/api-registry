package org.lshh.skeleton.core.resource.resourcer;

public interface ResourcerManager {
    Resourcer find(Long resourceId);
    Resourcer create(Resourcer resourcer);
    Resourcer update(Resourcer resourcer);
    void clearCache();
    boolean isCached(Long id);
}
