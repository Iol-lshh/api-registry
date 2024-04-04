package org.lshh.skeleton.core.task.implement;

import org.junit.jupiter.api.Test;
import org.lshh.skeleton.core.task.dto.TaskCreateCommand;
import org.lshh.skeleton.core.task.dto.TaskUpdateCommand;
import org.lshh.skeleton.core.task.exception.TaskContextException;

import static org.junit.jupiter.api.Assertions.*;
import static org.lshh.skeleton.core.task.Task.TaskType.QUERY;

public class TaskContextTest {

    @Test
    public void testCreateCommand(){
        TaskCreateCommand command = TaskCreateCommand.of("t1", "00", QUERY, 123L, 456L, 789L);
        TaskContext context = TaskContext.of(command);

        assertEquals("t1", context.getSortId());
        assertEquals("00", context.getParentTreeId());
        assertEquals(QUERY, context.getType());
        assertEquals(123L, context.getQueryId());
        assertEquals(456L, context.getResourcerId());
        assertEquals(789L, context.getRollbackTaskId());
    }

    @Test
    public void testUpdateCommand(){
        TaskUpdateCommand command = TaskUpdateCommand.of(1L, "t1", "00", QUERY, 123L, 456L, 789L);
        TaskContext context = TaskContext.of(command);

        assertEquals(1L, context.getId());
        assertEquals("t1", context.getSortId());
        assertEquals("00", context.getParentTreeId());
        assertEquals(QUERY, context.getType());
        assertEquals(123L, context.getQueryId());
    }

    @Test
    public void testSetSortId() {
        TaskContext context = new TaskContext();

        context.setSortId("s1");
        assertEquals("s1", context.getSortId());
        assertEquals("s1", context.getTreeId());

        context.setSortId("s2").setParentTreeId("1234");
        assertEquals("s2", context.getSortId());
        assertEquals("1234s2", context.getTreeId());

        context.setSortId("s0").setParentTreeId("1234");
        context.setSortId("s3");
        assertEquals("s3", context.getSortId());
        assertEquals("1234s3", context.getTreeId());

        assertThrows(TaskContextException.class, () -> context.setSortId(null));
        assertThrows(TaskContextException.class, () -> context.setSortId("1"));
    }

    @Test
    void testSetParentTreeId() {
        TaskContext context = new TaskContext();
        context.setSortId("t1");
        context.setParentTreeId("00");
        assertEquals("00t1", context.getTreeId());

        context.setParentTreeId("1234");
        assertEquals("1234t1", context.getTreeId());

        context.setParentTreeId(null);
        assertEquals("t1", context.getTreeId());

        assertThrows(TaskContextException.class, () -> context.setParentTreeId("1"));
    }

    @Test
    public void testSetTreeId() {
        TaskContext context = new TaskContext();
        context.setSortId("t0");
        context.setParentTreeId("1234");
        assertEquals("t0", context.getSortId());
        assertEquals("1234t0", context.getTreeId());

        //
        context.setTreeId("t1");
        assertEquals("t1", context.getSortId());
        assertEquals("t1", context.getTreeId());

        //
        context.setTreeId("1234");
        assertEquals("34", context.getSortId());
        assertEquals("1234", context.getTreeId());

        //
        assertThrows(TaskContextException.class, () -> context.setTreeId("1"));
        assertThrows(TaskContextException.class, () -> context.setTreeId(null));
    }
}