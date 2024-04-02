package org.lshh.skeleton.core.resource.resourcer.implement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lshh.skeleton.core.resource.resourcer.Resourcer;
import org.lshh.skeleton.core.resource.resourcer.ResourcerRepository;
import org.lshh.skeleton.core.resource.resourcer.dto.ResourcerCreateCommand;
import org.lshh.skeleton.core.resource.resourcer.dto.ResourcerUpdateCommand;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.lshh.skeleton.core.resource.resourcer.Resourcer.ResourcerType.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Testcontainers
@ExtendWith(MockitoExtension.class)
public class ResourcerProviderTest {
    @Container
    public GenericContainer container = new MySQLContainer(DockerImageName.parse("mysql:5.7"))
            .withDatabaseName("test")
            .withUsername("testname")
            .withPassword("password");
    @Mock
    ResourcerRepository repository;
    @InjectMocks
    ResourcerProviderImplement resourcerProvider;

    String host;
    Integer port;
    String databaseName;

    @BeforeEach
    public void setUp() {
        host = container.getHost();
        port = container.getFirstMappedPort();
        databaseName = "test";
    }

    @Test
    @DisplayName("find Exist Resourcer")
    public void find_Exist_ReturnResourcer() {
        Long resourceId = 1L;
        ResourcerCreateCommand command = ResourcerCreateCommand.of(
                "name",
                "jdbc:mysql://"+host+":"+port+"/"+databaseName,
                "description",
                RDBMS,
                "testname",
                "password",
                "com.mysql.cj.jdbc.Driver"
        );
        ResourcerContext context = ResourcerContext.of(command).setId(resourceId);
        when(repository.findById(any(Long.class))).thenReturn(Optional.of(context));

        Optional<Resourcer> resourcerOptional = resourcerProvider.find(resourceId);

        assertTrue(resourcerOptional.isPresent());
        assertEquals(resourceId, resourcerOptional.get().getId());
    }


    @Test
    @DisplayName("find All Resourcer List")
    public void testFindAll() {
        Long resourceId1 = 1L;
        ResourcerCreateCommand command1 = ResourcerCreateCommand.of(
                "h2test",
                "jdbc:h2:~/test",
                "description",
                RDBMS,
                "sa",
                "",
                "org.h2.Driver"
        );
        ResourcerContext fakeContext1 = ResourcerContext.of(command1).setId(resourceId1);
        Long resourceId2 = 2L;
        ResourcerCreateCommand command2 = ResourcerCreateCommand.of(
                "name",
                "jdbc:mysql://"+host+":"+port+"/"+databaseName,
                "description",
                RDBMS,
                "testname",
                "password",
                "com.mysql.cj.jdbc.Driver"
        );
        ResourcerContext fakeContext2 = ResourcerContext.of(command2).setId(resourceId2);
        List<ResourcerContext> resourcerContexts = Arrays.asList(fakeContext1, fakeContext2);
        when(repository.findAll()).thenReturn(resourcerContexts);

        List<Resourcer> result = resourcerProvider.findAll();

        assertTrue(result.size() == resourcerContexts.size());
    }

    @Test
    void testCreate() {
        Long resourceId = 1L;
        ResourcerCreateCommand command = ResourcerCreateCommand.of(
                "name",
                "jdbc:mysql://"+host+":"+port+"/"+databaseName,
                "description",
                RDBMS,
                "testname",
                "password",
                "com.mysql.cj.jdbc.Driver"
        );
        ResourcerContext fakeContext = ResourcerContext.of(command).setId(resourceId);
        when(repository.create(any(ResourcerContext.class))).thenReturn(fakeContext);

        Resourcer resourcer = resourcerProvider.create(command);

        assertEquals(fakeContext.getId(), resourcer.getId());
        verify(repository).create(any(ResourcerContext.class));
    }

    @Test
    void testUpdate() {
        Long resourceId = 1L;
        ResourcerUpdateCommand command = ResourcerUpdateCommand.of(
                resourceId,
                "name",
                "jdbc:mysql://"+host+":"+port+"/"+databaseName,
                "description",
                RDBMS,
                "testname",
                "password",
                "com.mysql.cj.jdbc.Driver"
        );
        ResourcerContext fakeContext = ResourcerContext.of(command);
        when(repository.update(any(ResourcerContext.class))).thenReturn(fakeContext);

        Resourcer resourcer = resourcerProvider.update(command);

        assertEquals(fakeContext.getId(), resourcer.getId());
        verify(repository).update(any(ResourcerContext.class));
    }


}