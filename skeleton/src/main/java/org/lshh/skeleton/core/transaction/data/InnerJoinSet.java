package org.lshh.skeleton.core.transaction.data;

import java.util.ArrayList;
import java.util.List;

public class InnerJoinSet implements JoinSet{
    private final DataSet left;
    private final DataSet right;
    private final DataSet result;
    private final List<Integer> joinedIndex = new ArrayList<>();

    public InnerJoinSet(DataSet left, DataSet right) {
        this.left = left;
        this.right = right;
        List<String> columns = new ArrayList<>(left.columns);
        columns.stream().filter(col -> !right.columns.contains(col)).forEach(right.columns::add);
        this.result = new DataSet(columns);
    }

    public static JoinSet of(DataSet left, DataSet right) {
        return new InnerJoinSet(left, right);
    }

    @Override
    public JoinSet on(String leftKey, String rightKey){
        int leftIndex = left.columns.indexOf(leftKey);
        int rightIndex = right.columns.indexOf(rightKey);
        if(leftIndex == -1 || rightIndex == -1){
            throw new IllegalArgumentException("Key not found");
        }
        for(int i = 0; i < left.data.size(); i++){
            for(int j = 0; j < right.data.size(); j++){
                if(left.data.get(i).get(leftIndex).equals(right.data.get(j).get(rightIndex))){
                    joinedIndex.add(i);
                }
            }
        }

        joinedIndex.forEach(index -> {
            List<Object> row = new ArrayList<>(left.data.get(index));
            for(int i = 0; i < right.data.size(); i++){
                if(left.data.get(index).get(leftIndex).equals(right.data.get(i).get(rightIndex))){
                    row.addAll(right.data.get(i));
                }
            }
            result.data.add(row);
        });

        return this;
    }

    @Override
    public DataSet getResult() {
        return result;
    }
}
