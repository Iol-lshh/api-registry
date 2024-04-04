package org.lshh.skeleton.sample.config;

import org.lshh.skeleton.core.resource.query.QueryManager;
import org.lshh.skeleton.core.resource.query.QueryProvider;
import org.lshh.skeleton.core.resource.query.QueryRepository;
import org.lshh.skeleton.core.resource.query.implement.QueryManagerImplement;
import org.lshh.skeleton.core.resource.query.implement.QueryProviderImplement;
import org.lshh.skeleton.core.resource.resourcer.ResourcerManager;
import org.lshh.skeleton.core.resource.resourcer.ResourcerProvider;
import org.lshh.skeleton.core.resource.resourcer.ResourcerRepository;
import org.lshh.skeleton.core.resource.resourcer.implement.ResourcerManagerImplement;
import org.lshh.skeleton.core.resource.resourcer.implement.ResourcerProviderImplement;
import org.lshh.skeleton.core.router.RouterManager;
import org.lshh.skeleton.core.router.RouterProvider;
import org.lshh.skeleton.core.router.RouterRepository;
import org.lshh.skeleton.core.router.implement.RouterManagerImplement;
import org.lshh.skeleton.core.router.implement.RouterProviderImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiRegistryConfig {
    @Bean
    public RouterProvider routerProvider(@Autowired RouterRepository repository) {
        return RouterProviderImplement.of(repository);
    }
    @Bean
    public RouterManager routerManager(@Autowired RouterProvider provider) {
        return RouterManagerImplement.of(provider);
    }
    @Bean
    public ResourcerProvider resourcerProvider(@Autowired ResourcerRepository repository) {
        return ResourcerProviderImplement.of(repository);
    }
    @Bean
    public ResourcerManager resourcerManager(@Autowired ResourcerProvider provider) {
        return ResourcerManagerImplement.of(provider);
    }
    @Bean
    public QueryProvider queryProvider(@Autowired QueryRepository repository) {
        return QueryProviderImplement.of(repository);
    }

    @Bean
    public QueryManager queryManager(@Autowired QueryProvider provider) {
        return QueryManagerImplement.of(provider);
    }

    // todo - task와 port도 Bean을 만들어야한다.

}
