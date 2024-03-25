package org.lshh.skeleton.domain.router.implement;

import org.junit.jupiter.api.Test;
import org.lshh.skeleton.domain.router.Router;
import org.lshh.skeleton.domain.router.RouterRepository;
import org.lshh.skeleton.domain.router.command.RouterCreateCommand;
import org.lshh.skeleton.domain.router.command.RouterUpdateCommand;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class RouterProviderTest {

    @Mock
    RouterRepository routerRepository;

    @InjectMocks
    RouterProviderImplement routerProvider;

    @Test
    public void testFindByPath_findsRouter_whenPathExists(){
        MockitoAnnotations.initMocks(this);
        String path = "/test-path";
        RouterContext testContext = new RouterContext();
        
        when(routerRepository.findByPath(path)).thenReturn(Optional.of(testContext));

        Optional<Router> optionalRouter = routerProvider.findByPath(path);
        
        verify(routerRepository, times(1)).findByPath(path);
        assertTrue(optionalRouter.isPresent());
    }

    @Test
    public void testFindByPath_returnsEmptyOptional_whenPathNotExist(){
        MockitoAnnotations.initMocks(this);
        String path = "/nonexistent-path";

        when(routerRepository.findByPath(path)).thenReturn(Optional.empty());

        Optional<Router> optionalRouter = routerProvider.findByPath(path);
        
        verify(routerRepository, times(1)).findByPath(path);
        assertFalse(optionalRouter.isPresent());
    }

    @Test
    public void testFindAll_returnsAllRouters(){
        MockitoAnnotations.initMocks(this);
        RouterContext testContext1 = new RouterContext();
        RouterContext testContext2 = new RouterContext();

        when(routerRepository.findAll()).thenReturn(java.util.List.of(testContext1, testContext2));

        routerProvider.findAll();

        verify(routerRepository, times(1)).findAll();
    }

    @Test
    public void testCreate_createsRouter(){
        MockitoAnnotations.initMocks(this);
        RouterCreateCommand command = RouterCreateCommand.of("test-path", "test-method", "test-service", 1L);
        RouterContext testContext = new RouterContext();

        when(routerRepository.create(any(RouterContext.class))).thenReturn(testContext);

        routerProvider.create(command);

        verify(routerRepository, times(1)).create(any(RouterContext.class));
    }

    @Test
    public void testUpdate_updatesRouter(){
        MockitoAnnotations.initMocks(this);
        RouterContext testContext = new RouterContext();
        RouterUpdateCommand command = RouterUpdateCommand.of(1L, "test-path", "test-method", "test-service", 1L);

        when(routerRepository.update(any(RouterContext.class))).thenReturn(testContext);

        routerProvider.update(command);

        verify(routerRepository, times(1)).update(any(RouterContext.class));
    }
}