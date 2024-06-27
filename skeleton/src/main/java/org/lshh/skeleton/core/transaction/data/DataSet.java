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

    public static DataSet of(List<String> columns) {
        return new DataSet(columns);
    }

    public JoinSet leftJoin(DataSet right){
        return LeftJoinSetImplement.of(this, right);
    }
    public JoinSet innerJoin(DataSet right){
        return InnerJoinSetImplement.of(this, right);
    }
}
