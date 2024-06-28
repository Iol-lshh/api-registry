package org.lshh.skeleton.core.transaction.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class JoinSetImplement implements JoinSet{
    protected final DataSet left;
    protected final DataSet right;
    protected final List<List<Integer>> selectColumns;
    protected final List<List<Integer>> joinColumns = new ArrayList<>();


    public JoinSetImplement(DataSet left, DataSet right) {
        this.left = left;
        this.right = right;
        this.selectColumns = new ArrayList<>();
        List<Integer> leftSelectColumns = new ArrayList<>();
        this.selectColumns.add(leftSelectColumns);
        List<Integer> rightSelectColumns = new ArrayList<>();
        this.selectColumns.add(rightSelectColumns);
    }

    @Override
    public JoinSet on(String leftKey, String rightKey){
        Integer leftIndex = findIndexFromLeft(leftKey)
                .orElseThrow(() -> new IllegalArgumentException("Key not found"));
        Integer rightIndex = findIndexFromRight(rightKey)
                .orElseThrow(() -> new IllegalArgumentException("Key not found"));
        List<Integer> joinColumn = new ArrayList<>();
        joinColumn.add(leftIndex);
        joinColumn.add(rightIndex);
        this.joinColumns.add(joinColumn);
        return this;
    }

    @Override
    public DataSet toDataSet() {
        DataSet rawResult = computeJoin();
        List<String> columns = new ArrayList<>();
        selectColumns.get(0).forEach(index -> columns.add(left.columns.get(index)));
        selectColumns.get(1).forEach(index -> columns.add(right.columns.get(index)));

        DataSet dataSet = DataSet.of(columns);
        for(List<Object> row : rawResult.data){
            List<Object> newRow = new ArrayList<>();
            selectColumns.get(0).forEach(index -> newRow.add(row.get(index)));
            selectColumns.get(1).forEach(index -> newRow.add(row.get(this.selectColumns.get(0).size() + index)));
            dataSet.data.add(newRow);
        }
        return dataSet;
    }

    @Override
    public void selectLeftColumns(String column){
        int index = findIndexFromLeft(column)
                .orElseThrow(() -> new IllegalArgumentException("Left Key not found"));
        this.selectColumns.get(0).add(index);
    }

    @Override
    public void selectRightColumns(String column){
        int index = findIndexFromRight(column)
                .orElseThrow(() -> new IllegalArgumentException("Right Key not found"));
        this.selectColumns.get(1).add(index);
    }


    @Override
    public Optional<Integer> findIndexFromLeft(String column){
        int index = this.left.columns.indexOf(column);
        if(index == -1){
            return Optional.empty();
        }
        return Optional.of(index);
    }
    @Override
    public Optional<Integer> findIndexFromRight(String column){
        int index = this.right.columns.indexOf(column);
        if(index == -1){
            return Optional.empty();
        }
        return Optional.of(index);
    }

    public abstract DataSet computeJoin();
}
