package org.lshh.skeleton.core.router.implement;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.lshh.skeleton.core.router.Router;
import org.lshh.skeleton.core.router.RouterProvider;
import org.lshh.skeleton.core.router.command.RouterCreateCommand;
import org.lshh.skeleton.core.router.command.RouterUpdateCommand;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class RouterManagerTest {
    @Mock
    private RouterProvider provider;

    @InjectMocks
    private RouterManagerImplement routerManager;

    @Test
    @DisplayName("RouterManager:create 성공")
    public void testCreate() {
        RouterCreateCommand command = RouterCreateCommand.of("name", "/path", "description", 1L);
        Router router = RouterProvider.Routers.of(RouterContext.of(command).setId(1L));
        when(provider.create(command)).thenReturn(router);

        Router result = routerManager.create(command);

        assertNotNull(result);
        assertEquals(router, result);
        assertEquals(result.getPath(), "/path");
    }

    @Test
    @DisplayName("RouterManager:update 성공")
    public void testUpdate(){
        RouterUpdateCommand command = RouterUpdateCommand.of(1L, "name", "/path", "description", 1L);
        Router router = RouterProvider.Routers.of(RouterContext.of(command));
        when(provider.update(command)).thenReturn(router);

        Router result = routerManager.update(command);

        assertNotNull(result);
        assertEquals(router, result);
        assertEquals(result.getPath(), "/path");
    }

    @Test
    @DisplayName("RouterManager:findByPath 성공(캐시히트)")
    public void testFindByPath_whenCacheHit(){
        String path = "/test-path";
        RouterCreateCommand command = RouterCreateCommand.of("name", path, "description", 1L);
        Router router = RouterProvider.Routers.of(RouterContext.of(command).setId(1L));

        // 캐시
        when(provider.create(command)).thenReturn(router);
        routerManager.create(command);

        // 캐시 확인
        Router result = routerManager.findByPath(path);

        verify(provider, times(0)).findByPath(path);
        assertTrue(routerManager.isCached(path));
        assertNotNull(result);
    }

    @Test
    @DisplayName("RouterManager:findByPath 성공(캐시미스)")
    public void testFindByPath_whenCacheMiss(){
        String path = "/test-path";
        RouterCreateCommand command = RouterCreateCommand.of("name", path, "description", 1L);
        Router router = RouterProvider.Routers.of(RouterContext.of(command).setId(1L));

        // 캐시
        when(provider.create(command)).thenReturn(router);
        routerManager.create(command);

        // 캐시 비움
        routerManager.clearCache();
        assertFalse(routerManager.isCached(path));

        // 확인
        when(provider.findByPath(path)).thenReturn(Optional.of(router));
        Router result = routerManager.findByPath(path);

        verify(provider, times(1)).findByPath(path);
        assertNotNull(result);
    }

    @Test
    @DisplayName("RouterManager:findByPath 실패")
    public void testFindByPath_fail(){
        String path = "/test-path";

        // 확인
        when(provider.findByPath(path)).thenReturn(Optional.empty());
        Router result = routerManager.findByPath(path);

        verify(provider, times(1)).findByPath(path);
        assertNull(result);
    }

    @Test
    @DisplayName("RouterManager:findAll 성공")
    public void testFindAll(){
        RouterCreateCommand command = RouterCreateCommand.of("name", "/path", "description", 1L);
        Router router = RouterProvider.Routers.of(RouterContext.of(command).setId(1L));

        // 확인
        when(provider.findAll()).thenReturn(List.of(router));
        routerManager.findAll();

        verify(provider, times(1)).findAll();
        assertTrue(routerManager.isCached("/path"));
    }
}