package org.lshh.skeleton.core.resource.resource;

public interface ResourceManager {
    Resource find(Long resourceId);
    boolean isExist(Long resourceId);
    // gateway 버전
}
