package org.lshh.skeleton.core.resource.resourcer.implement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lshh.skeleton.core.resource.resourcer.Resourcer;
import org.lshh.skeleton.core.resource.resourcer.ResourcerRepository;
import org.lshh.skeleton.core.resource.resourcer.dto.ResourcerCreateCommand;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.lshh.skeleton.core.resource.resourcer.Resourcer.ResourcerType.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Test class for `ResourcerProviderImplement`.
 * This Class tests the method `find` of `ResourcerProviderImplement`.
 */
@Testcontainers
@ExtendWith(MockitoExtension.class)
public class ResourcerProviderTest {

    @Mock
    ResourcerRepository repository;

    @InjectMocks
    ResourcerProviderImplement resourcerProvider;

    @Container
    public GenericContainer container = new GenericContainer(DockerImageName.parse("oscarfonts/h2"))
            .withEnv("username", "username")
            .withEnv("password", "password")
            .withExposedPorts(1521);
            // /test 디비를 생성해야하는데..

    String address;
    Integer port;

    @BeforeEach
    public void setUp() {
        address = container.getHost();
        port = container.getMappedPort(1521);
    }

    @Test
    public void find_Exist_ReturnResourcer() {
        Long resourceId = 1L;
        ResourcerCreateCommand command = ResourcerCreateCommand.of(
                "name",
                "jdbc:h2:tcp://"+address+":"+port+"/test",
                "description",
                RDBMS,
                "username",
                "password",
                "org.h2.Driver"
        );
        ResourcerContext context = ResourcerContext.of(command).setId(resourceId);
        when(repository.findById(any(Long.class))).thenReturn(Optional.of(context));

        // 히카리 연결 및 데이터 소스를 생성
        Optional<Resourcer> resourcerOptional = resourcerProvider.find(resourceId);

        assertTrue(resourcerOptional.isPresent());
        assertEquals(resourceId, resourcerOptional.get().getId());
    }
}