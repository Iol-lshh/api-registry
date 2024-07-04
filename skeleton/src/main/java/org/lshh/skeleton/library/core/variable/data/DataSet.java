package org.lshh.skeleton.library.core.variable.data;

import org.lshh.skeleton.library.core.variable.data.join.JoinSet;
import java.util.List;
import java.util.Optional;

public interface DataSet extends DataVariable {

    static DataSet of(List<String> columns, List<List<Object>> data) {
        return new DataSetVariable(columns, data);
    }
    static DataSet of(List<String> columns) {
        return new DataSetVariable(columns);
    }
    DataSet addRow(List<Object> row);
    DataSet addAllRows(List<List<Object>> rows);
    int size();
    List<Object> getRow(int index);
    List<List<Object>> getRows();
    int getColumnSize();
    String getColumn(int index);
    List<String> getColumns();
    Optional<Integer> findColumnIndex(String column);
    JoinSet leftJoin(DataSet right);
    JoinSet innerJoin(DataSet right);
}
