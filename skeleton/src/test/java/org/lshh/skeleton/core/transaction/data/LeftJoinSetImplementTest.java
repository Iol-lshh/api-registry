package org.lshh.skeleton.core.transaction.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class LeftJoinSetImplementTest {

    @Test
    public void testComputeJoinRawResult() {
        // Prepare data sets for test
        DataSet dataSet1 = DataSet.of(Arrays.asList("id", "name"));
        dataSet1.addRow(Arrays.asList(1, "John Doe"));
        dataSet1.addRow(Arrays.asList(2, "Jane Doe"));

        DataSet dataSet2 = DataSet.of(Arrays.asList("id", "email"));
        dataSet2.addRow(Arrays.asList(1, "john.doe@email.com"));
        dataSet2.addRow(Arrays.asList(3, "mary.jane@email.com"));

        LeftJoinSetImplement leftJoinSet = new LeftJoinSetImplement(dataSet1, dataSet2);
        leftJoinSet.on("id").selectAll();

        // Execute the method under test
        DataSet rawResult = leftJoinSet.computeJoinRawResult();

        // Verify the results are correct
        System.out.println(rawResult.getRows());
        // [[1, John Doe, 1, john.doe@email.com], [2, Jane Doe, null, null]]
        assertEquals(2, rawResult.size());
        assertEquals(4, rawResult.getRow(0).size());
    }

    @Test
    public void testComputeJoinRawResultWhenNoMatches() {
        // Prepare data sets for test
        DataSet dataSet1 = DataSet.of(Arrays.asList("id", "name"));
        dataSet1.addRow(Arrays.asList(1, "John Doe"));
        dataSet1.addRow(Arrays.asList(2, "Jane Doe"));

        DataSet dataSet2 = DataSet.of(Arrays.asList("id", "email"));
        dataSet2.addRow(Arrays.asList(3, "mary.jane@email.com"));

        LeftJoinSetImplement leftJoinSet = new LeftJoinSetImplement(dataSet1, dataSet2);
        leftJoinSet.on("id").selectAll();
        // Execute the method under test
        DataSet rawResult = leftJoinSet.computeJoinRawResult();

        // Verify the results are correct
        System.out.println(rawResult.getRows());
        // [[1, John Doe, null, null], [2, Jane Doe, null, null]]
        assertEquals(2, rawResult.size());
    }

}