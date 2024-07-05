package org.lshh.skeleton.library.core.variable.data;

import org.lshh.skeleton.library.core.variable.Variable;
import org.lshh.skeleton.library.core.variable.data.join.InnerJoinSet;
import org.lshh.skeleton.library.core.variable.data.join.JoinSet;
import org.lshh.skeleton.library.core.variable.data.join.LeftJoinSet;

import java.util.*;

public class DataSetImplement implements DataSet, Variable {
    private final List<String> columns;
    private final List<List<Object>> data;

    public DataSetImplement(List<String> columns, List<List<Object>> data){
        this.columns = columns;
        this.data = data;
    }
    public DataSetImplement(List<String> columns){
        this.columns = columns;
        this.data = new ArrayList<>();
    }
    @Override
    public DataSet addRow(List<Object> row){
        this.data.add(row);
        return this;
    }
    @Override
    public DataSet addAllRows(List<List<Object>> rows){
        this.data.addAll(rows);
        return this;
    }
    @Override
    public int size(){
        return this.data.size();
    }
    @Override
    public List<Object> getRow(int index){
        return this.data.get(index);
    }
    @Override
    public List<List<Object>> getRows(){
        return this.data;
    }
    @Override
    public List<String> getColumns() {
        return this.columns;
    }
    @Override
    public String getColumn(int index){
        return this.columns.get(index);
    }
    @Override
    public int getColumnSize(){
        return this.columns.size();
    }
    @Override
    public Optional<Integer> findColumnIndex(String column){
        for(int i = 0; i < this.columns.size(); i++){
            if(this.columns.get(i).equals(column)){
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }
    @Override
    public JoinSet leftJoin(DataSet right){
        return LeftJoinSet.of(this, right);
    }
    @Override
    public JoinSet innerJoin(DataSet right){
        return InnerJoinSet.of(this, right);
    }

    @Override
    public List<Map<String, Object>> toMapList() {
        return this.data.stream().map(row -> {
            Map<String, Object> _map = new HashMap<>();
            for (int i = 0; i < this.columns.size(); i++) {
                _map.put(this.columns.get(i), row.get(i));
            }
            return _map;
        }).toList();
    }
}
