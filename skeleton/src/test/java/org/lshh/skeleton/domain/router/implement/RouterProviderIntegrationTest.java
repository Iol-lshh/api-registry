package org.lshh.skeleton.domain.router.implement;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lshh.skeleton.domain.router.Router;
import org.lshh.skeleton.domain.router.RouterProvider;
import org.lshh.skeleton.domain.router.command.RouterCreateCommand;
import org.lshh.skeleton.domain.router.command.RouterUpdateCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RouterProviderIntegrationTest {

    @Autowired
    RouterProvider routerProvider;

    @Test
    @DisplayName("RouterProvider:findByPath 성공")
    public void testFindByPath_findsRouter_whenPathExists(){
        String path = "/test-path";
        RouterCreateCommand command = RouterCreateCommand.of("test-path", path, "test-service", 1L);
        routerProvider.create(command);

        Optional<Router> optionalRouter = routerProvider.findByPath(path);
        
        assertTrue(optionalRouter.isPresent());
    }

    @Test
    @DisplayName("RouterProvider:findAll 성공")
    public void testFindAll_returnsAllRouters(){
        RouterCreateCommand command1 = RouterCreateCommand.of("test-path1", "/test-path1", "test-service1", 1L);
        RouterCreateCommand command2 = RouterCreateCommand.of("test-path2", "/test-path2", "test-service2", 2L);
        routerProvider.create(command1);
        routerProvider.create(command2);

        assertEquals(2, routerProvider.findAll().size());
    }

    @Test
    @DisplayName("RouterProvider:update 성공")
    public void testUpdate(){
        // 초기화
        String stubName = "test-path";
        String stubPath = "/test-path";
        String stubDescription = "test-service";
        Long stubServiceId = 1L;
        RouterCreateCommand createCommand = RouterCreateCommand.of(stubName, stubPath, stubDescription, stubServiceId);
        Router router = routerProvider.create(createCommand);

        // 업데이트
        String updatedPath = "/updated-path";
        RouterUpdateCommand updateCommand = RouterUpdateCommand.of(router.getId(), stubName, updatedPath, stubDescription, stubServiceId);
        routerProvider.update(updateCommand);

        // 확인
        Optional<Router> optionalRouter = routerProvider.findByPath(updatedPath);
        assertTrue(optionalRouter.isPresent());
        assertEquals(router.getId(), optionalRouter.get().getId());
    }

}