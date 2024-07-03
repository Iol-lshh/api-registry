package org.lshh.skeleton.config;

import org.lshh.skeleton.library.resource.resourcer.ResourcerManager;
import org.lshh.skeleton.library.resource.resourcer.ResourcerProvider;
import org.lshh.skeleton.library.resource.resourcer.ResourcerRepository;
import org.lshh.skeleton.library.resource.resourcer.implement.ResourcerManagerImplement;
import org.lshh.skeleton.library.resource.resourcer.implement.ResourcerProviderImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResourcerConfig {
    @Bean
    public ResourcerProvider resourcerProvider(@Autowired ResourcerRepository repository) {
        return ResourcerProviderImplement.of(repository);
    }
    @Bean
    public ResourcerManager resourcerManager(@Autowired ResourcerProvider provider) {
        return ResourcerManagerImplement.of(provider);
    }
}
