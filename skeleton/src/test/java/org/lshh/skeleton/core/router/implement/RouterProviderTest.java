package org.lshh.skeleton.core.router.implement;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lshh.skeleton.core.router.Router;
import org.lshh.skeleton.core.router.RouterRepository;
import org.lshh.skeleton.core.router.dto.RouterCreateCommand;
import org.lshh.skeleton.core.router.dto.RouterUpdateCommand;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RouterProviderTest {

    @Mock
    RouterRepository routerRepository;

    @InjectMocks
    RouterProviderImplement routerProvider;

    @Test
    @DisplayName("RouterProvider:findByPath 성공")
    public void testFindByPath_findsRouter_whenPathExists(){
        String path = "/test-path";
        RouterContext testContext = new RouterContext();
        
        when(routerRepository.findByPath(path)).thenReturn(Optional.of(testContext));

        Optional<Router> optionalRouter = routerProvider.findByPath(path);
        
        verify(routerRepository, times(1)).findByPath(path);
        assertTrue(optionalRouter.isPresent());
    }

    @Test
    @DisplayName("RouterProvider:findByPath 실패")
    public void testFindByPath_returnsEmptyOptional_whenPathNotExist(){
        String path = "/nonexistent-path";

        when(routerRepository.findByPath(path)).thenReturn(Optional.empty());

        Optional<Router> optionalRouter = routerProvider.findByPath(path);
        
        verify(routerRepository, times(1)).findByPath(path);
        assertFalse(optionalRouter.isPresent());
    }

    @Test
    @DisplayName("RouterProvider:findAll 성공")
    public void testFindAll_returnsAllRouters(){
        RouterContext testContext1 = new RouterContext();
        RouterContext testContext2 = new RouterContext();

        when(routerRepository.findAll()).thenReturn(java.util.List.of(testContext1, testContext2));

        routerProvider.findAll();

        verify(routerRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("RouterProvider:create 성공")
    public void testCreate_createsRouter(){
        Long id = 1L;
        RouterCreateCommand command = RouterCreateCommand.of("test-path", "test-method", "test-service", 1L);
        RouterContext testContext = RouterContext.of(command).setId(id);

        when(routerRepository.create(any(RouterContext.class))).thenReturn(testContext.setId(id));

        Router router = routerProvider.create(command);

        verify(routerRepository, times(1)).create(any(RouterContext.class));
        assertEquals(id, router.getId());
        assertEquals(command.getName(), router.getName());
        assertEquals(testContext.getName(), router.getName());
        assertEquals(command.getPath(), router.getPath());
        assertEquals(testContext.getPath(), router.getPath());
        assertEquals(command.getDescription(), router.getDescription());
        assertEquals(testContext.getDescription(), router.getDescription());
        assertEquals(command.getTaskId(), router.getTaskId());
        assertEquals(testContext.getTaskId(), router.getTaskId());
    }

    @Test
    @DisplayName("RouterProvider:update 성공")
    public void testUpdate_updatesRouter(){
        Long id = 1L;
        RouterUpdateCommand command = RouterUpdateCommand.of(id, "test-path", "test-method", "test-service", 1L);
        RouterContext testContext = RouterContext.of(command);

        when(routerRepository.update(any(RouterContext.class))).thenReturn(testContext);

        Router router = routerProvider.update(command);

        verify(routerRepository, times(1)).update(any(RouterContext.class));
        assertEquals(id, router.getId());
        assertEquals(command.getName(), router.getName());
        assertEquals(testContext.getName(), router.getName());
        assertEquals(command.getPath(), router.getPath());
        assertEquals(testContext.getPath(), router.getPath());
        assertEquals(command.getDescription(), router.getDescription());
        assertEquals(testContext.getDescription(), router.getDescription());
        assertEquals(command.getTaskId(), router.getTaskId());
        assertEquals(testContext.getTaskId(), router.getTaskId());
    }
}