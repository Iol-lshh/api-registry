package org.lshh.skeleton.core.resource.resourcer.implement;

import org.lshh.skeleton.core.resource.resourcer.ResourcerProvider;
import org.lshh.skeleton.core.resource.resourcer.ResourcerRepository;

public class ResourcerProviderImplement implements ResourcerProvider {
    ResourcerRepository repository;
    private ResourcerProviderImplement(ResourcerRepository repository) {
        this.repository = repository;
    }
    public static ResourcerProvider of(ResourcerRepository repository) {
        return new ResourcerProviderImplement(repository);
    }

}
