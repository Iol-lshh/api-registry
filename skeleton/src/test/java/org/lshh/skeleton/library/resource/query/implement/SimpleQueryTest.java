package org.lshh.skeleton.library.resource.query.implement;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lshh.skeleton.library.variable.data.DataSet;

import javax.sql.DataSource;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SimpleQueryTest {

    static class FakeJdbcTemplate implements JdbcTemplateWrapper {
        @Override
        public List<Map<String, Object>> queryForList(String queryBody, Map<String, Object> args) {
            return null;
        }

        @Override
        public boolean isReady() {
            return true;
        }
    }

    @Test
    @DisplayName("Test Query Success")
    public void testQuerySuccess() {

        // Mock dependencies
        QueryContext queryContext = mock(QueryContext.class);
        when(queryContext.getId()).thenReturn(1L);
        when(queryContext.getBody()).thenReturn("query");

        DataSource dataSource = mock(DataSource.class);
        JdbcTemplateWrapper jdbcTemplate = mock(FakeJdbcTemplate.class);

        // Mock return
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("name", "John Doe");
        resultMap.put("age", 30);
        List<Map<String, Object>> mockList = new ArrayList<>();
        mockList.add(resultMap);
        when(jdbcTemplate.queryForList(anyString(), anyMap())).thenReturn(mockList);

        // Create class
        SimpleQuery simpleQuery = new SimpleQuery(queryContext, jdbcTemplate);
        simpleQuery = spy(simpleQuery);

        // Call Method
        simpleQuery.setInput("parameter", DataSet.of(List.of("name", "age"), List.of(List.of("John Doe", 30))));
        DataSet dataSet = (DataSet) simpleQuery.query().getOutput();

        // Check
        assertNotNull(dataSet);

        // Check Columns
        assertEquals(2, dataSet.getColumnSize());
        assertEquals("name", dataSet.getColumns().get(0));
        assertEquals("age", dataSet.getColumns().get(1));

        // Check Rows
        assertEquals(1, dataSet.size());
        assertEquals("John Doe", dataSet.getRow(0).get(0));
        assertEquals(30, dataSet.getRow(0).get(1));
    }
}