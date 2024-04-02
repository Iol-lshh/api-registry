package org.lshh.skeleton.core.resource.resourcer.implement;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lshh.skeleton.core.resource.resourcer.JdbcResourcer;
import org.lshh.skeleton.core.resource.resourcer.dto.ResourcerCreateCommand;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.lshh.skeleton.core.resource.resourcer.Resourcer.ResourcerType.RDBMS;

@Testcontainers
@ExtendWith(MockitoExtension.class)
public class SimpleJdbcResourcerTest {

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
    @DisplayName("SimpleJdbcResourcer::initDataSource 성공(H2)")
    public void testInitDataSource_byH2() {
        Long resourceId = 1L;
        ResourcerCreateCommand command = ResourcerCreateCommand.of(
                "h2test",
                "jdbc:h2:~/test",
                "description",
                RDBMS,
                "sa",
                "",
                "org.h2.Driver"
        );
        ResourcerContext context = ResourcerContext.of(command).setId(resourceId);

        SimpleJdbcResourcer resourcer = new SimpleJdbcResourcer(context);
        resourcer.initDataSource();
        DataSource dataSource = resourcer.getDataSource();

        assertNotNull(dataSource);
        assertEquals(resourceId, context.getId());
        assertEquals(resourceId, resourcer.getId());
        assertEquals(command.getName(), context.getName());
        assertEquals(command.getName(), resourcer.getName());
        assertEquals(command.getDescription(), context.getDescription());
        assertEquals("jdbc:h2:~/test", ((HikariDataSource) dataSource).getJdbcUrl());
        assertEquals("sa", ((HikariDataSource) dataSource).getUsername());
        assertEquals("", ((HikariDataSource) dataSource).getPassword());
        assertEquals("org.h2.Driver", ((HikariDataSource) dataSource).getDriverClassName());
    }

    @Test
    @DisplayName("SimpleJdbcResourcer::of 성공(h2)")
    public void testDataSource_h2_default() {
        Long resourceId = 1L;
        ResourcerCreateCommand command = ResourcerCreateCommand.of(
                "h2test",
                "jdbc:h2:~/test",
                "description",
                RDBMS,
                "sa",
                "",
                "org.h2.Driver"
        );
        ResourcerContext context = ResourcerContext.of(command).setId(resourceId);

        JdbcResourcer resourcer = SimpleJdbcResourcer.of(context);
        DataSource dataSource = resourcer.getDataSource();

        assertNotNull(dataSource);
        assertEquals(resourceId, context.getId());
        assertEquals(resourceId, resourcer.getId());
        assertEquals(command.getName(), context.getName());
        assertEquals(command.getName(), resourcer.getName());
        assertEquals(command.getDescription(), context.getDescription());
        assertEquals("jdbc:h2:~/test", ((HikariDataSource) dataSource).getJdbcUrl());
        assertEquals("sa", ((HikariDataSource) dataSource).getUsername());
        assertEquals("", ((HikariDataSource) dataSource).getPassword());
        assertEquals("org.h2.Driver", ((HikariDataSource) dataSource).getDriverClassName());
    }

    @Test
    @DisplayName("SimpleJdbcResourcer::of 성공(mysql)")
    public void testDataSource_mysql() {
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

        JdbcResourcer resourcer = SimpleJdbcResourcer.of(context);
        DataSource dataSource = resourcer.getDataSource();

        assertNotNull(dataSource);
        assertEquals(resourceId, context.getId());
        assertEquals(resourceId, resourcer.getId());
        assertEquals(command.getName(), context.getName());
        assertEquals(command.getName(), resourcer.getName());
        assertEquals(command.getDescription(), context.getDescription());
        assertEquals(command.getUrl(), ((HikariDataSource) dataSource).getJdbcUrl());
        assertEquals(command.getUsername(), ((HikariDataSource) dataSource).getUsername());
        assertEquals(command.getPassword(), ((HikariDataSource) dataSource).getPassword());
        assertEquals(command.getAdaptorName(), ((HikariDataSource) dataSource).getDriverClassName());
    }

    @Test
    @DisplayName("SimpleJdbcResourcer::setConectionPool 성공(mysql)")
    void setConectionPool_ModifySettings_Succeed() {
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
        JdbcResourcer resourcer = SimpleJdbcResourcer.of(context);

        // Prepare
        int maxPoolSize = 5, minIdle = 2;
        long connectionTimeout = 20000, idleTimeout = 400000, maxLifeTime = 1200000;

        // Act
        resourcer.setConectionPool(maxPoolSize, minIdle, connectionTimeout, idleTimeout, maxLifeTime);
        HikariDataSource resultDS = (HikariDataSource) resourcer.getDataSource();

        // Assert
        assertEquals(maxPoolSize, resultDS.getMaximumPoolSize());
        assertEquals(minIdle, resultDS.getMinimumIdle());
        assertEquals(connectionTimeout, resultDS.getConnectionTimeout());
        assertEquals(idleTimeout, resultDS.getIdleTimeout());
        assertEquals(maxLifeTime, resultDS.getMaxLifetime());
    }
}