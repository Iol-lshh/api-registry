package org.lshh.skeleton.core.task.implement;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lshh.skeleton.core.resource.query.Query;
import org.lshh.skeleton.core.resource.query.implement.SimpleQueryTask;
import org.lshh.skeleton.core.resource.resourcer.JdbcResourcer;
import org.lshh.skeleton.core.resource.resourcer.ResourcerManager;
import org.lshh.skeleton.core.resource.query.QueryManager;
import org.lshh.skeleton.core.task.*;
import org.lshh.skeleton.core.task.dto.TaskCreateCommand;
import org.lshh.skeleton.core.task.dto.TaskUpdateCommand;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.sql.DataSource;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.lshh.skeleton.core.task.Task.TaskType.PIPELINE;
import static org.lshh.skeleton.core.task.Task.TaskType.QUERY;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskProviderTest {

    @Mock
    TaskRepository repository;

    @Mock
    ResourcerManager resourcerManager;
    @Mock
    QueryManager queryManager;

    @InjectMocks
    TaskProviderImplement mockTaskProvider;

    @Spy
    TaskProvider spyTaskProvider = TaskProviderImplement.of(repository, resourcerManager, queryManager);


    @Test
    public void findContext() {
        // Prepare test data
        Long testContextId = 123L;
        TaskCreateCommand command = TaskCreateCommand.of("t1", "00", QUERY, 123L, 456L, 789L);
        TaskContext stubTaskContext = TaskContext.of(command).setId(testContextId);
        when(repository.findContext(anyLong())).thenReturn(Optional.of(stubTaskContext));

        // Execution
        Optional<TaskContext> resultOptional = mockTaskProvider.findContext(testContextId);

        // Verification
        assertTrue(resultOptional.isPresent());
        TaskContext result = resultOptional.get();
        assertEquals(stubTaskContext.getId(), result.getId());

        verify(repository, times(1)).findContext(testContextId);
    }

    @Test
    void findChildContextList() {
        TaskContext mockContext = mock(TaskContext.class);
        when(repository.findByStartId(anyLong())).thenReturn(Arrays.asList(mockContext));

        List<TaskContext> result = mockTaskProvider.findChildContextList(1L);

        assertEquals(1, result.size());
        assertEquals(mockContext, result.getFirst());
    }

    @Test
    public void findContextByTreeId() {
        when(repository.findByTreeId(anyString())).thenReturn(Optional.of(new TaskContext()));

        Optional<TaskContext> found = mockTaskProvider.findContextByTreeId("testId");

        assertTrue(found.isPresent());
    }

    @Test
    public void generateQueryTask() {
        Long taskId = 123L;
        TaskCreateCommand command = TaskCreateCommand.of("t1", "00", QUERY, 123L, 456L, 789L);
        TaskContext taskContext = TaskContext.of(command).setId(taskId);
        Query mockQuery = mock(Query.class);
        JdbcResourcer mockResourcer = mock(JdbcResourcer.class);
        DataSource mockDataSource = mock(DataSource.class);
        when(resourcerManager.find(anyLong())).thenReturn(mockResourcer);
        when(mockResourcer.getDataSource()).thenReturn(mockDataSource);
        when(mockResourcer.as(any())).thenReturn(mockResourcer);
        when(queryManager.find(anyLong(), any(DataSource.class))).thenReturn(mockQuery);

        QueryTask result = mockTaskProvider.generateQueryTask(taskContext);

        assertEquals(SimpleQueryTask.class, result.getClass());
    }

    @Test
    public void filterChildren() {
        TaskCreateCommand rootCommand = TaskCreateCommand.of("r1", "", PIPELINE, 1001L);
        TaskContext rootContext = TaskContext.of(rootCommand).setId(1L);
        // child1
        TaskCreateCommand childCommand1 = TaskCreateCommand.of("q1", "r1", QUERY, 1L, 1L, 1011L);
        TaskContext childContext1 = TaskContext.of(childCommand1).setId(11L);
        QueryTask childQueryTask1 = SimpleQueryTask.of(childContext1, mock(Query.class), Map.of());
        // child2
        TaskCreateCommand childCommand2 = TaskCreateCommand.of("q2", "r1", QUERY, 2L, 1L, 1012L);
        TaskContext childContext2 = TaskContext.of(childCommand2).setId(12L);
        QueryTask childQueryTask2 = SimpleQueryTask.of(childContext2, mock(Query.class), Map.of());
        // list
        List<TaskContext> childContexts = List.of(childContext1, childContext2);

        List<TaskContext> actual = mockTaskProvider.filterChildren(rootContext, childContexts);

        assertEquals(childContexts.size(), actual.size());
    }



    @Test
    public void generatePipelineTask() {
        TaskCreateCommand rootCommand = TaskCreateCommand.of("r1", "", PIPELINE, 1001L);
        TaskContext rootContext = TaskContext.of(rootCommand).setId(1L);
        // child1
        TaskCreateCommand childCommand1 = TaskCreateCommand.of("q1", "r1", QUERY, 1L, 1L, 1011L);
        TaskContext childContext1 = TaskContext.of(childCommand1).setId(11L);
        QueryTask childQueryTask1 = SimpleQueryTask.of(childContext1, mock(Query.class), Map.of());
        doReturn(childQueryTask1).when(spyTaskProvider).generateQueryTask(eq(childContext1));
        // child2
        TaskCreateCommand childCommand2 = TaskCreateCommand.of("q2", "r1", QUERY, 2L, 1L, 1012L);
        TaskContext childContext2 = TaskContext.of(childCommand2).setId(12L);
        QueryTask childQueryTask2 = SimpleQueryTask.of(childContext2, mock(Query.class), Map.of());
        doReturn(childQueryTask2).when(spyTaskProvider).generateQueryTask(eq(childContext2));
        // list
        List<TaskContext> childContexts = List.of(childContext1, childContext2);

        PipelineTask pipelineTask = spyTaskProvider.generatePipelineTask(rootContext, childContexts);

        assertNotNull(pipelineTask);
        assertEquals(2, pipelineTask.getSubTasks().size());
    }

    @Test
    public void create() {
        Long id = 1L;
        TaskCreateCommand command = TaskCreateCommand.of("q1", "r1", QUERY, 1L, 1L, 1011L);
        TaskContext stubContext = TaskContext.of(command);
        when(repository.create(any(TaskContext.class))).thenReturn(stubContext.setId(id));

        TaskContext resultContext = mockTaskProvider.create(command);

        verify(repository).create(any(TaskContext.class));
        assertEquals(resultContext.getId(), id);
    }

    @Test
    public void update() {
        Long id = 1L;
        TaskUpdateCommand command = TaskUpdateCommand.of(id,"q1", "r1", QUERY, 1L, 1L, 1011L);
        TaskContext stubContext = TaskContext.of(command);
        when(repository.update(any(TaskContext.class))).thenReturn(stubContext);

        TaskContext resultContext = mockTaskProvider.update(command);

        verify(repository).update(any(TaskContext.class));
        assertEquals(resultContext.getId(), id);
    }


    ///////////////////////

    @Test
    @DisplayName(value = "find success - Mock test with Spy") // todo 실제 스텁 테스트는 통테로..
    public void find() {
        // Prepare test data
        Long testContextId = 123L;
        TaskCreateCommand command = TaskCreateCommand.of("t1", "00", QUERY, 123L, 456L, 789L);
        TaskContext stubTaskContext = TaskContext.of(command).setId(testContextId);
        Task mockTask = mock(Task.class);
        doReturn(Optional.of(stubTaskContext)).when(spyTaskProvider).findContext(anyLong());
        doReturn(List.of()).when(spyTaskProvider).findChildContextList(anyLong());
        doReturn(mockTask).when(spyTaskProvider).generate(any(), any());    // 이럼 의미가 있을까..? 행위 검증 의미..?

        // Execution
        Optional<Task> resultOptional = spyTaskProvider.find(testContextId);

        // Verification
        assertTrue(resultOptional.isPresent());
        verify(spyTaskProvider, times(1)).findContext(testContextId);
        verify(spyTaskProvider, times(1)).findChildContextList(testContextId);
        verify(spyTaskProvider, times(1)).generate(any(), any());
    }

    @Test
    @DisplayName(value = "findByTreeId success - Mock test with Spy")
    void findByTreeId() {
        String rootTreeId = "00";
        TaskCreateCommand rootCommand = TaskCreateCommand.of(rootTreeId, "", PIPELINE, 789L);
        TaskContext rootStubTaskContext = TaskContext.of(rootCommand).setId(1L);

        Long subContextId = 123L;
        TaskCreateCommand subCommand = TaskCreateCommand.of("t1", "00", QUERY, 123L, 456L, 789L);
        TaskContext stubTaskContext = TaskContext.of(subCommand).setId(subContextId);
        doReturn(Optional.of(rootStubTaskContext)).when(spyTaskProvider).findContextByTreeId(rootTreeId);
        doReturn(List.of(rootStubTaskContext, stubTaskContext)).when(spyTaskProvider).findChildContextList(anyLong());
        doReturn(mock(QueryTask.class)).when(spyTaskProvider).generateQueryTask(any());

        Optional<Task> task = spyTaskProvider.findByTreeId(rootTreeId);

        assertTrue(task.isPresent());
        verify(spyTaskProvider, times(1)).findContextByTreeId(rootTreeId);
        verify(spyTaskProvider, times(1)).findChildContextList(rootStubTaskContext.getId());
        verify(spyTaskProvider, times(2)).generate(any(), any());
    }

    @Test
    void testFindAll() {
        String rootTreeId = "00";
        TaskCreateCommand rootCommand = TaskCreateCommand.of(rootTreeId, "", PIPELINE, 789L);
        TaskContext rootStubTaskContext = TaskContext.of(rootCommand).setId(1L);
        Long subContextId = 123L;
        String subTreeId = "00t1";
        TaskCreateCommand subCommand = TaskCreateCommand.of("t1", "00", QUERY, 123L, 456L, 789L);
        TaskContext stubTaskContext = TaskContext.of(subCommand).setId(subContextId);
        doReturn(List.of(rootStubTaskContext)).when(spyTaskProvider).findAllRouteContext();
        doReturn(List.of(rootStubTaskContext, stubTaskContext)).when(spyTaskProvider).findChildContextList(anyLong());
        doReturn(mock(QueryTask.class)).when(spyTaskProvider).generateQueryTask(any());

        // Running test
        List<Task> taskList = spyTaskProvider.findAll();

        // Checking result
        assertNotNull(taskList);
        assertEquals(1, taskList.size());
    }
}