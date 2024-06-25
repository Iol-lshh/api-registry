package org.lshh.skeleton.core.transaction.data;

import java.util.ArrayList;
import java.util.List;

public class DataSet {
    List<String> columns;
    List<List<Object>> data;

    public DataSet(List<String> columns, List<List<Object>> data){
        this.columns = columns;
        this.data = data;
    }
    public DataSet(List<String> columns){
        this.columns = columns;
        this.data = new ArrayList<>();
    }

    public JoinSet leftJoin(DataSet right){
        return LeftJoinSet.of(this, right);
    }
    public JoinSet innerJoin(DataSet right){
        return InnerJoinSet.of(this, right);
    }
}
