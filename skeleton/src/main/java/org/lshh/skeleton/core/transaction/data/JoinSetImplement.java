package org.lshh.skeleton.core.transaction.data;

import java.util.ArrayList;
import java.util.List;

public abstract class JoinSetImplement implements JoinSet{
    protected final DataSet left;
    protected final DataSet right;
    protected final DataSet result;
    protected final List<Integer> joinedIndex = new ArrayList<>();

    public JoinSetImplement(DataSet left, DataSet right, DataSet result) {
        this.left = left;
        this.right = right;
        List<String> columns = new ArrayList<>(left.columns);
        columns.stream().filter(col -> !right.columns.contains(col))
                .forEach(columns::add);
        this.result = DataSet.of(columns);
    }

    @Override
    public abstract JoinSet on(String key, String value);

    @Override
    public DataSet toDataSet() {
        return result;
    }
}
