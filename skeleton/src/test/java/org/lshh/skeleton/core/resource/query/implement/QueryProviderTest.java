package org.lshh.skeleton.core.resource.query.implement;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lshh.skeleton.core.resource.query.Query;
import org.lshh.skeleton.core.resource.query.QueryRepository;
import org.lshh.skeleton.core.resource.query.dto.QueryCreateCommand;
import org.lshh.skeleton.core.resource.query.dto.QueryUpdateCommand;
import org.lshh.skeleton.core.resource.query.exception.ResourceQueryException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.sql.DataSource;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QueryProviderTest {

    @Mock
    private QueryRepository queryRepository;

    @InjectMocks
    private QueryProviderImplement queryProvider;

    @Test
    void testFind_exists() {
        // arrange
        Long id = 1L;
        QueryCreateCommand command = QueryCreateCommand.of("title", "body", 1L);
        QueryContext stubContext = QueryContext.of(command);
        when(queryRepository.findById(id)).thenReturn(Optional.of(stubContext.setId(id)));
        DataSource dataSource = mock(DataSource.class);

        // act
        Optional<Query> result = queryProvider.find(id, dataSource);

        // assert
        assertTrue(result.isPresent(), "Query should be present when it exists in the repository");
    }

    @Test
    void testFind_notExists() {
        // arrange
        Long id = 1L;
        when(queryRepository.findById(id)).thenReturn(Optional.empty());
        DataSource dataSource = mock(DataSource.class);

        // assert
        assertThrows(ResourceQueryException.class, 
            () -> queryProvider.find(id, dataSource),
            "A ResourceQueryException should be thrown when no query with the given ID exists in the repository"
        );
    }

    @Test
    void create() {
        Long id = 1L;
        QueryCreateCommand command = QueryCreateCommand.of("title", "body", 1L);
        DataSource mockDataSource = mock(DataSource.class);
        QueryContext stubQueryContext = QueryContext.of(command);
        when(queryRepository.create(any(QueryContext.class))).thenReturn(stubQueryContext.setId(id));

        Query result = queryProvider.create(command, mockDataSource);

        assertEquals(result.getId(), id);
        assertEquals(result.getId(), stubQueryContext.getId());
        assertEquals(result.getTitle(), command.getTitle());
        assertEquals(result.getTitle(), stubQueryContext.getTitle());
        assertEquals(result.getBody(), command.getBody());
        assertEquals(result.getBody(), stubQueryContext.getBody());
        assertEquals(result.getResourcerId(), command.getResourcerId());
        assertEquals(result.getResourcerId(), stubQueryContext.getResourcerId());
    }

    @Test
    void update() {
        Long id = 1L;
        QueryUpdateCommand command = QueryUpdateCommand.of(id, "title", "body", 1L);
        DataSource mockDataSource = mock(DataSource.class);
        QueryContext stubQueryContext = QueryContext.of(command);
        when(queryRepository.update(any(QueryContext.class))).thenReturn(stubQueryContext);

        Query result = queryProvider.update(command, mockDataSource);

        assertEquals(result.getId(), id);
        assertEquals(result.getId(), stubQueryContext.getId());
        assertEquals(result.getTitle(), command.getTitle());
        assertEquals(result.getTitle(), stubQueryContext.getTitle());
        assertEquals(result.getBody(), command.getBody());
        assertEquals(result.getBody(), stubQueryContext.getBody());
        assertEquals(result.getResourcerId(), command.getResourcerId());
        assertEquals(result.getResourcerId(), stubQueryContext.getResourcerId());
    }
}