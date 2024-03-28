package org.lshh.skeleton.core.resource.resourcer.implement;

import org.lshh.skeleton.core.resource.resourcer.Resourcer;
import org.lshh.skeleton.core.resource.resourcer.ResourcerManager;
import org.lshh.skeleton.core.resource.resourcer.ResourcerProvider;
import org.lshh.skeleton.core.router.Router;

import java.util.HashMap;
import java.util.Map;

public class ResourcerManagerImplement implements ResourcerManager {
    private final Map<String, Router> cacheMap = new HashMap<>();
    private final ResourcerProvider provider;

    private ResourcerManagerImplement(ResourcerProvider provider) {
        this.provider = provider;
    }
    public static ResourcerManager of(ResourcerProvider provider) {
        return new ResourcerManagerImplement(provider);
    }


    @Override
    public Resourcer find(Long resourceId) {
        return null;
    }

}
