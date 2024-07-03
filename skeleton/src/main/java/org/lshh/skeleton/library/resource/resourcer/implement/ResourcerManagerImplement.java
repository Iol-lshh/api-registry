package org.lshh.skeleton.library.resource.resourcer.implement;

import org.lshh.skeleton.library.resource.resourcer.Resourcer;
import org.lshh.skeleton.library.resource.resourcer.ResourcerManager;
import org.lshh.skeleton.library.resource.resourcer.ResourcerProvider;
import org.lshh.skeleton.library.resource.resourcer.dto.ResourcerCreateCommand;
import org.lshh.skeleton.library.resource.resourcer.dto.ResourcerUpdateCommand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ResourcerManagerImplement implements ResourcerManager {
    private final Map<Long, Resourcer> cacheMap = new HashMap<>();
    private final ResourcerProvider provider;

    private ResourcerManagerImplement(ResourcerProvider provider) {
        this.provider = provider;
    }
    public static ResourcerManager of(ResourcerProvider provider) {
        return new ResourcerManagerImplement(provider);
    }

    @Override
    public Resourcer find(Long resourceId) {
        // cache
        if(isCached(resourceId)){
            return cacheMap.get(resourceId);
        }

        // repository
        Optional<Resourcer> mayResourcer = provider.find(resourceId);
        if(mayResourcer.isPresent()){
            cacheMap.put(resourceId, mayResourcer.get());
            return mayResourcer.get();
        }

        // fail
        return null;
    }

    @Override
    public List<Resourcer> findAll() {
        List<Resourcer> list = provider.findAll();
        list.forEach(resourcer -> cacheMap.put(resourcer.getId(), resourcer));
        return list;
    }

    @Override
    public Resourcer create(ResourcerCreateCommand command) {
        Resourcer newOne = provider.create(command);
        cacheMap.put(newOne.getId(), newOne);
        return newOne;
    }

    @Override
    public Resourcer update(ResourcerUpdateCommand command) {
        Resourcer renewal = provider.update(command);
        cacheMap.put(renewal.getId(), renewal);
        return renewal;
    }

    @Override
    public void clearCache() {
        cacheMap.clear();
    }

    @Override
    public boolean isCached(Long id) {
        return cacheMap.containsKey(id);
    }

}
