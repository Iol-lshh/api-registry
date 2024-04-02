package org.lshh.skeleton.core.resource.resourcer.implement;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lshh.skeleton.core.resource.resourcer.Resourcer;
import org.lshh.skeleton.core.resource.resourcer.ResourcerProvider;
import org.lshh.skeleton.core.resource.resourcer.ResourcerRepository;
import org.lshh.skeleton.core.resource.resourcer.dto.ResourcerCreateCommand;
import org.lshh.skeleton.core.resource.resourcer.dto.ResourcerUpdateCommand;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.lshh.skeleton.core.resource.resourcer.Resourcer.ResourcerType.RDBMS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@Testcontainers
@SpringBootTest
public class ResourcerProviderIntegrationTest {

    @Autowired
    ResourcerProvider resourcerProvider;

    @Container
    public static GenericContainer container = new MySQLContainer(DockerImageName.parse("mysql:5.7"))
            .withDatabaseName("test")
            .withUsername("testname")
            .withPassword("password")
            .withReuse(true);

    String host = container.getHost();
    Integer port = container.getFirstMappedPort();
    String databaseName = "test";


    @Test
    @DisplayName("ResourcerProvider::find 성공")
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
        resourcerProvider.create(command);
        Optional<Resourcer> resourcerOptional = resourcerProvider.find(resourceId);

        assertTrue(resourcerOptional.isPresent());
        assertEquals(resourceId, resourcerOptional.get().getId());
    }


    @Test
    @DisplayName("ResourcerProvider::findAll 성공")
    public void testFindAll() {
        ResourcerCreateCommand command1 = ResourcerCreateCommand.of(
                "h2test",
                "jdbc:h2:~/test",
                "description",
                RDBMS,
                "sa",
                "",
                "org.h2.Driver"
        );
        ResourcerCreateCommand command2 = ResourcerCreateCommand.of(
                "name",
                "jdbc:mysql://"+host+":"+port+"/"+databaseName,
                "description",
                RDBMS,
                "testname",
                "password",
                "com.mysql.cj.jdbc.Driver"
        );
        resourcerProvider.create(command1);
        resourcerProvider.create(command2);

        List<Resourcer> result = resourcerProvider.findAll();

        assertTrue(result.size() >= 2);
    }

    @Test
    @DisplayName("ResourcerProvider::create 성공")
    void testCreate() {
        ResourcerCreateCommand command = ResourcerCreateCommand.of(
                "name",
                "jdbc:mysql://"+host+":"+port+"/"+databaseName,
                "description",
                RDBMS,
                "testname",
                "password",
                "com.mysql.cj.jdbc.Driver"
        );

        Resourcer resourcer = resourcerProvider.create(command);

        assertNotNull(resourcer.getId());
    }

    @Test
    @DisplayName("ResourcerProvider::update 성공")
    void testUpdate() {
        ResourcerUpdateCommand command = ResourcerUpdateCommand.of(
                1L,
                "name",
                "jdbc:mysql://"+host+":"+port+"/"+databaseName,
                "description",
                RDBMS,
                "testname",
                "password",
                "com.mysql.cj.jdbc.Driver"
        );
        ResourcerContext fakeContext = ResourcerContext.of(command);

        Resourcer resourcer = resourcerProvider.update(command);

        assertEquals(fakeContext.getId(), resourcer.getId());
    }
}