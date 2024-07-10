package org.lshh.skeleton.library.variable.data;

import org.lshh.skeleton.library.variable.Variable;
import org.lshh.skeleton.library.variable.data.join.InnerJoinSet;
import org.lshh.skeleton.library.variable.data.join.JoinSet;
import org.lshh.skeleton.library.variable.data.join.LeftJoinSet;

import java.util.*;

public class DataSetImplement implements DataSet, Variable {
    private final List<String> columns;
    private final List<List<Object>> data;
    private final List<Integer> resultIndexes;

    public DataSetImplement(List<String> columns, List<List<Object>> data){
        this.columns = columns;
        this.data = data;
        this.resultIndexes = new ArrayList<>();
        clearWhere();
    }
    public DataSetImplement(List<String> columns){
        this.columns = columns;
        this.data = new ArrayList<>();
        this.resultIndexes = new ArrayList<>();
    }
    @Override
    public DataSet clearWhere(){
        this.resultIndexes.clear();
        for(int i = 0; i < this.data.size(); i++){
            this.resultIndexes.add(i);
        }
        return this;
    }
    @Override
    public DataSet addRow(List<Object> row){
        this.data.add(row);
        clearWhere();
        return this;
    }
    @Override
    public DataSet addAllRows(List<List<Object>> rows){
        this.data.addAll(rows);
        clearWhere();
        return this;
    }
    @Override
    public int size(){
        return this.data.size();
    }
    @Override
    public List<Object> getRow(int index){
        if(index < 0 || index >= this.data.size() || this.resultIndexes.stream().noneMatch(i -> i == index)){
            throw new IndexOutOfBoundsException();
        }
        return this.data.get(index);
    }
    @Override
    public List<List<Object>> getRows(){
        return this.resultIndexes.stream().map(this.data::get).toList();
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
        return LeftJoinSet.of(this.getComputed(), right.getComputed());
    }
    @Override
    public JoinSet innerJoin(DataSet right){
        return InnerJoinSet.of(this.getComputed(), right.getComputed());
    }

    @Override
    public List<Map<String, Object>> toMapList() {
        return this.resultIndexes.stream().map(this.data::get)
                .map(row -> {
                    Map<String, Object> _map = new HashMap<>();
                    for (int i = 0; i < this.columns.size(); i++) {
                        _map.put(this.columns.get(i), row.get(i));
                    }
                    return _map;
                }).toList();
    }

    @Override
    public DataSet where(String column, Object value, String operator) {
        List<Integer> indexes = new ArrayList<>(this.resultIndexes);
        this.resultIndexes.clear();
        indexes.stream().filter(index -> {
            int columnIndex = this.columns.indexOf(column);
            Object rowValue = this.data.get(index).get(columnIndex);
            switch (operator) {
                case "=":
                    return rowValue.equals(value);
                case "!=":
                    return !rowValue.equals(value);
                case ">":
                    return (int) rowValue > (int) value;
                case "<":
                    return (int) rowValue < (int) value;
                case ">=":
                    return (int) rowValue >= (int) value;
                case "<=":
                    return (int) rowValue <= (int) value;
                default:
                    return false;
            }
        }).forEach(this.resultIndexes::add);
        return this;
    }

    @Override
    public DataSet getComputed(){
        return DataSet.of(
                this.columns,
                this.resultIndexes.stream().map(this.data::get).toList()
        );
    }
}
