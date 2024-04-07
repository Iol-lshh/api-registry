package org.lshh.skeleton.core.task.implement;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lshh.skeleton.core.resource.query.implement.QueryTaskImplement;
import org.lshh.skeleton.core.task.QueryTask;
import org.lshh.skeleton.core.task.Task;
import org.lshh.skeleton.core.task.TaskManager;
import org.lshh.skeleton.core.task.TaskProvider;
import org.lshh.skeleton.core.task.dto.TaskCreateCommand;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.lshh.skeleton.core.task.Task.TaskType.*;
import static org.mockito.Mockito.*;

/**
 * A JUnit test class for the 'find' method in the TaskManagerImplement class.
 */
@ExtendWith(MockitoExtension.class)
public class TaskManagerTest {
    @Mock
    TaskProvider provider;
    @InjectMocks
    TaskManagerImplement manager;

    @Test
    public void testFind_cacheHit() {
        Long taskId = 1L;
        Task mockTask = mock(Task.class);
        doReturn(taskId).when(mockTask).getId();
        when(provider.find(anyLong())).thenReturn(Optional.of(mockTask));
        assertFalse(manager.isCached(taskId));

        Task result = manager.find(taskId);

        assertTrue(manager.isCached(taskId));
        assertEquals(taskId, result.getId());
    }

    @Test
    public void testFind_cacheMiss() {
        Long taskId = 1L;
        Task mockTask = mock(Task.class);
        when(provider.find(taskId)).thenReturn(Optional.of(mockTask));
        assertFalse(manager.isCached(taskId));
        manager.find(taskId);
    }

    /**
     * Test the 'find' method with a start task id that doesn't exist in both cache and database.
     */
    @Test
    public void testFind_fail() {
        Long taskId = 1L;

        TaskProvider provider = Mockito.mock(TaskProvider.class);
        when(provider.find(taskId)).thenReturn(Optional.empty());

        TaskManager manager = new TaskManagerImplement(provider);

        Task result = manager.find(taskId);

        assertNull(result);
    }
}