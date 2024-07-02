package org.lshh.skeleton.core.transaction.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataSet {
    private List<String> columns;
    private List<List<Object>> data;

    public DataSet(List<String> columns, List<List<Object>> data){
        this.columns = columns;
        this.data = data;
    }
    public DataSet(List<String> columns){
        this.columns = columns;
        this.data = new ArrayList<>();
    }

    public static DataSet of(List<String> columns, List<List<Object>> data) {
        return new DataSet(columns, data);
    }
    public static DataSet of(List<String> columns) {
        return new DataSet(columns);
    }
    public DataSet addRow(List<Object> row){
        this.data.add(row);
        return this;
    }
    public DataSet addAllRows(List<List<Object>> rows){
        this.data.addAll(rows);
        return this;
    }
    public int size(){
        return this.data.size();
    }
    public List<List<Object>> getRows(){
        return this.data;
    }
    public List<Object> getRow(int index){
        return this.data.get(index);
    }
    public List<String> getColumns() {
        return this.columns;
    }
    public String getColumn(int index){
        return this.columns.get(index);
    }
    public int getColumnSize(){
        return this.columns.size();
    }
    public Optional<Integer> findColumnIndex(String column){
        for(int i = 0; i < this.columns.size(); i++){
            if(this.columns.get(i).equals(column)){
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }

    public JoinSet leftJoin(DataSet right){
        return LeftJoinSet.of(this, right);
    }
    public JoinSet innerJoin(DataSet right){
        return InnerJoinSet.of(this, right);
    }


}
