package org.lshh.skeleton.core.resource.query.implement;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lshh.skeleton.core.resource.query.Query;
import org.lshh.skeleton.core.resource.query.QueryProvider;
import org.lshh.skeleton.core.resource.query.dto.QueryCreateCommand;
import org.lshh.skeleton.core.resource.query.dto.QueryUpdateCommand;
import org.lshh.skeleton.core.resource.resourcer.JdbcResourcer;
import org.lshh.skeleton.core.resource.resourcer.Resourcer;
import org.lshh.skeleton.core.resource.resourcer.ResourcerProvider;
import org.lshh.skeleton.core.resource.resourcer.dto.ResourcerCreateCommand;
import org.lshh.skeleton.core.resource.resourcer.dto.ResourcerUpdateCommand;
import org.lshh.skeleton.core.resource.resourcer.implement.ResourcerContext;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import javax.sql.DataSource;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.lshh.skeleton.core.resource.resourcer.Resourcer.ResourcerType.RDBMS;

@Testcontainers
@ExtendWith(MockitoExtension.class)
public class SimpleJdbcQueryTest {

    @Mock
    private QueryProvider queryProvider;


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
    void constructor(){
        ResourcerCreateCommand resourcerCommand = ResourcerCreateCommand.of(
                "name",
                "jdbc:mysql://"+host+":"+port+"/"+databaseName,
                "description",
                RDBMS,
                "testname",
                "password",
                "com.mysql.cj.jdbc.Driver"
        );
        ResourcerContext resourcerContext = ResourcerContext.of(resourcerCommand);
        Resourcer resourcer = ResourcerProvider.Resourcers.of(resourcerContext);
        QueryCreateCommand queryCommand = QueryCreateCommand.of(
                "title",
                "select 1",
                resourcer.getId()
        );
        QueryContext context = QueryContext.of(queryCommand).setId(1L);
        DataSource dataSource = resourcer.as(JdbcResourcer.class).getDataSource();

        Query query = Query.of(context, dataSource);

        assertEquals(context.getId(), query.getId());
    }

    @Test
    void query() {
        ResourcerCreateCommand resourcerCommand = ResourcerCreateCommand.of(
                "name",
                "jdbc:mysql://"+host+":"+port+"/"+databaseName,
                "description",
                RDBMS,
                "testname",
                "password",
                "com.mysql.cj.jdbc.Driver"
        );
        ResourcerContext resourcerContext = ResourcerContext.of(resourcerCommand);
        Resourcer resourcer = ResourcerProvider.Resourcers.of(resourcerContext);
        QueryCreateCommand queryCommand = QueryCreateCommand.of(
                "title",
                "select 1",
                resourcer.getId()
        );
        QueryContext context = QueryContext.of(queryCommand).setId(1L);
        DataSource dataSource = resourcer.as(JdbcResourcer.class).getDataSource();
        Query query = Query.of(context, dataSource);

        Map<String, Object> result = query.query(Map.of());

        assertEquals("[{1=1}]", result.get(context.getTitle()+context.getId()).toString());
    }

    @Test
    void query_withArgs() {
        ResourcerCreateCommand resourcerCommand = ResourcerCreateCommand.of(
                "name",
                "jdbc:mysql://"+host+":"+port+"/"+databaseName,
                "description",
                RDBMS,
                "testname",
                "password",
                "com.mysql.cj.jdbc.Driver"
        );
        ResourcerContext resourcerContext = ResourcerContext.of(resourcerCommand);
        Resourcer resourcer = ResourcerProvider.Resourcers.of(resourcerContext);
        QueryCreateCommand queryCommand = QueryCreateCommand.of(
                "title",
                "select :arg1 as arg1",
                resourcer.getId()
        );
        QueryContext context = QueryContext.of(queryCommand).setId(1L);
        DataSource dataSource = resourcer.as(JdbcResourcer.class).getDataSource();
        Query query = Query.of(context, dataSource);

        Map<String, Object> result = query.query(Map.of("arg1", "this is test"));

        assertEquals("[{arg1=this is test}]", result.get(context.getTitle()+context.getId()).toString());
    }
}