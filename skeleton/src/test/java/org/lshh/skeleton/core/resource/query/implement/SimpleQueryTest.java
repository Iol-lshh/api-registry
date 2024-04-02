package org.lshh.skeleton.core.resource.query.implement;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lshh.skeleton.core.resource.argument.ArgumentsMap;
import org.lshh.skeleton.core.resource.query.Query;
import org.lshh.skeleton.core.resource.query.QueryProvider;
import org.lshh.skeleton.core.resource.query.dto.QueryCreateCommand;
import org.lshh.skeleton.core.resource.query.dto.QueryUpdateCommand;
import org.lshh.skeleton.core.resource.query.implement.SimpleQuery;
import org.lshh.skeleton.core.resource.resourcer.JdbcResourcer;
import org.lshh.skeleton.core.resource.resourcer.Resourcer;
import org.lshh.skeleton.core.resource.resourcer.ResourcerProvider;
import org.lshh.skeleton.core.resource.resourcer.dto.ResourcerUpdateCommand;
import org.lshh.skeleton.core.resource.resourcer.implement.ResourcerContext;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.lshh.skeleton.core.resource.resourcer.Resourcer.ResourcerType.RDBMS;
import static org.mockito.Mockito.*;

@Testcontainers
@ExtendWith(MockitoExtension.class)
public class SimpleQueryTest {

    @Container
    public static GenericContainer container = new MySQLContainer(DockerImageName.parse("mysql:5.7"))
            .withDatabaseName("test")
            .withUsername("testname")
            .withPassword("password")
            .withReuse(true);

    @Test
    void testQuery() {
        ResourcerUpdateCommand resourcerCommand = ResourcerUpdateCommand.of(
                1L,
                "resourcer",
                "resourcer",
                "resourcer",
                RDBMS,
                "resourcer",
                "resourcer",
                "resourcer"
        );
        ResourcerContext resourcerContext = ResourcerContext.of(resourcerCommand);
        // todo
        JdbcResourcer resourcer = (JdbcResourcer) ResourcerProvider.Resourcers.of(resourcerContext);
        QueryUpdateCommand queryCommand = QueryUpdateCommand.of(
                1L,
                "title",
                "select 1",
                1L
        );
        DataSource dataSource = resourcer.getDataSource();
        QueryContext context = QueryContext.of(queryCommand);
        Query query = Query.of(context, dataSource);

        // 1. 생성 테스트
        // 2. 쿼리 테스트
//        // Initialize the object to be tested.
//        SimpleQuery simpleQuery = new SimpleQuery(context, dataSource);
//        simpleQuery.JDBC_TEMPLATE = jdbcTemplate;
//        Map<String, Object> args = new HashMap<>();
//        args.put("key", "value");
//
//        // Execute the method to be tested.
//        ArgumentsMap<String, Object> result = simpleQuery.query(args);
//
//        // Verify the results.
//        assertNotNull(result);
//        assertTrue(result.containsKey("result"));
//        assertEquals(Collections.emptyList(), result.get("result"));
    }
}