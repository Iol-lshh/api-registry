package org.lshh.skeleton.config;

import org.lshh.skeleton.core.router.RouterManager;
import org.lshh.skeleton.core.router.RouterProvider;
import org.lshh.skeleton.core.router.RouterRepository;
import org.lshh.skeleton.core.router.implement.RouterManagerImplement;
import org.lshh.skeleton.core.router.implement.RouterProviderImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfig {
    @Bean
    public RouterProvider routerProvider(@Autowired RouterRepository repository) {
        return RouterProviderImplement.of(repository);
    }
    @Bean
    public RouterManager routerManager(@Autowired RouterProvider provider) {
        return RouterManagerImplement.of(provider);
    }
}
