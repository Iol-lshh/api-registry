package org.lshh.skeleton.library.variable.data;

import org.lshh.skeleton.library.variable.data.join.JoinSet;
import java.util.List;

import java.util.Map;
import java.util.Optional;

public interface DataSet extends DataVariable {
    static DataSet of(List<String> columns, List<List<Object>> data) {
        return new DataSetImplement(columns, data);
    }
    static DataSet of(List<String> columns) {
        return new DataSetImplement(columns);
    }
    default boolean checkType(Class<?> clazz){
        return clazz.equals(DataSet.class);
    }

    DataSet clearWhere();

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
    List<Map<String, Object>> toMapList();

    DataSet where(String column, Object value, String operator);

    DataSet getComputed();
}
