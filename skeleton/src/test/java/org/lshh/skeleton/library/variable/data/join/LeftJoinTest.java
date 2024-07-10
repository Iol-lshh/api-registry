package org.lshh.skeleton.library.variable.data.join;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lshh.skeleton.library.variable.data.DataSet;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LeftJoinTest {
    @Test
    @DisplayName("Test Left Join Success")
    public void testLeftJoin(){
        DataSet left = DataSet.of(List.of("id", "name"))
                .addRow(List.of(1, "Alice"))
                .addRow(List.of(2, "Bob"))
                .addRow(List.of(3, "Charlie"));

        DataSet right = DataSet.of(List.of("id", "age"))
                .addRow(List.of(1, 20))
                .addRow(List.of(3, 30))
                .addRow(List.of(4, 40));

        JoinSet joinSet = LeftJoinSet.of(left, right)
                .on("id")
                .selectAll();

        DataSet result = joinSet.toDataSet();

        System.out.println(result.getColumns());
        System.out.println(result.getRows());
        // 4
        // [id, name, id, age]
        assertEquals(left.getColumnSize()+ right.getColumnSize(), result.getColumns().size());
        // 3
        // [[1, Alice, 1, 20], [2, Bob, null, null], [3, Charlie, 3, 30]]
        assertEquals(left.size(), result.getRows().size());
        // 4
        assertEquals(4, result.getRow(0).size());
    }
}
