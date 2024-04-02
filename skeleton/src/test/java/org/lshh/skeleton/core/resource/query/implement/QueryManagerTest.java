package org.lshh.skeleton.core.resource.query.implement;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lshh.skeleton.core.resource.query.dto.QueryCreateCommand;
import org.lshh.skeleton.core.resource.query.dto.QueryUpdateCommand;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.lshh.skeleton.core.resource.query.Query;
import org.lshh.skeleton.core.resource.query.QueryProvider;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.sql.DataSource;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class QueryManagerTest {
    @Mock
    QueryProvider provider;
    @InjectMocks
    QueryManagerImplement queryManager;

    @Test
    public void find_isCached() {
        Long queryId = 1L;
        QueryCreateCommand command = QueryCreateCommand.of("title", "body", 1L);
        QueryContext context = QueryContext.of(command).setId(queryId);
        DataSource mockDataSource = Mockito.mock(DataSource.class);
        Query stubQuery = Query.of(context, mockDataSource);
        when(provider.create(command, mockDataSource)).thenReturn(stubQuery);
        queryManager.create(command, mockDataSource);

        Query query = queryManager.find(queryId, mockDataSource);

        assertEquals(stubQuery.getId(), query.getId());
        verify(provider, times(0)).find(queryId, mockDataSource);
    }

    // This test case verifies that if a Query is not in the cache but can be retrieved from provider, the find method of QueryManagerImplement returns the Query and also adds it to cache.
    @Test
    public void find_notCached() {
        Long queryId = 1L;
        QueryCreateCommand command = QueryCreateCommand.of("title", "body", 1L);
        QueryContext context = QueryContext.of(command).setId(queryId);
        DataSource mockDataSource = Mockito.mock(DataSource.class);
        Query stubQuery = Query.of(context, mockDataSource);
        when(provider.find(queryId, mockDataSource)).thenReturn(Optional.of(stubQuery));
        when(provider.create(command, mockDataSource)).thenReturn(stubQuery);
        queryManager.create(command, mockDataSource);
        queryManager.clearCache();

        Query query = queryManager.find(queryId, mockDataSource);

        assertEquals(stubQuery.getId(), query.getId());
        verify(provider, times(1)).find(queryId, mockDataSource);
    }

    // This test case verifies that if a Query is neither in cache nor in provider, the find method of QueryManagerImplement returns null.
    @Test
    public void find_notAvailable() {
        Long queryId = 1L;
        DataSource mockDataSource = Mockito.mock(DataSource.class);
        when(provider.find(queryId, mockDataSource)).thenReturn(Optional.empty());

        Query query = queryManager.find(queryId, mockDataSource);

        assertSame(null, query);
    }

    @Test
    void create() {
        Long queryId = 1L;
        QueryCreateCommand command = QueryCreateCommand.of("title", "body", 1L);
        QueryContext context = QueryContext.of(command).setId(queryId);
        DataSource mockDataSource = Mockito.mock(DataSource.class);
        Query stubQuery = Query.of(context, mockDataSource);
        when(provider.create(command, mockDataSource)).thenReturn(stubQuery);

        Query createdQuery = queryManager.create(command, mockDataSource);

        verify(provider).create(command, mockDataSource);
        assertEquals(createdQuery.getId(), queryId);
        assertEquals(createdQuery.getTitle(), command.getTitle());
        assertEquals(createdQuery.getBody(), command.getBody());
        assertEquals(createdQuery.getResourcerId(), command.getResourcerId());
    }

    @Test
    void update(){
        Long queryId = 1L;
        QueryUpdateCommand command = QueryUpdateCommand.of(queryId, "title", "body", 1L);
        QueryContext context = QueryContext.of(command);
        DataSource mockDataSource = Mockito.mock(DataSource.class);
        Query stubQuery = Query.of(context, mockDataSource);
        when(provider.update(command, mockDataSource)).thenReturn(stubQuery);
        queryManager.update(command, mockDataSource);

        Query updatedQuery = queryManager.find(queryId, mockDataSource);

        assertEquals(updatedQuery.getId(), queryId);
        assertEquals(updatedQuery.getTitle(), command.getTitle());
        assertEquals(updatedQuery.getBody(), command.getBody());
        assertEquals(updatedQuery.getResourcerId(), command.getResourcerId());
    }

    @Test
    public void testClearCache() {
        Long queryId = 1L;
        QueryCreateCommand command = QueryCreateCommand.of("title", "body", 1L);
        QueryContext context = QueryContext.of(command);
        DataSource mockDataSource = Mockito.mock(DataSource.class);
        Query stubQuery = Query.of(context.setId(queryId), mockDataSource);
        when(provider.create(any(QueryCreateCommand.class), any(DataSource.class))).thenReturn(stubQuery);
        queryManager.create(command, mockDataSource);
        assertTrue(queryManager.isCached(queryId));

        queryManager.clearCache();

        assertFalse(queryManager.isCached(queryId));
    }
}