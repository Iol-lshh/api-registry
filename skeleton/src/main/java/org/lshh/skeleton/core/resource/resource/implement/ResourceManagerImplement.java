package org.lshh.skeleton.core.resource.resource.implement;

import org.lshh.skeleton.core.resource.resource.Resource;
import org.lshh.skeleton.core.resource.resource.ResourceManager;
import org.springframework.stereotype.Component;

@Component
public class ResourceManagerImplement implements ResourceManager {

    @Override
    public Resource find(Long resourceId) {
        return null;
    }

    @Override
    public boolean isExist(Long resourceId) {
        return false;
    }
}
