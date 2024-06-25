package org.lshh.skeleton.core.transaction.data;

import java.util.ArrayList;
import java.util.List;

public class LeftJoinSet implements JoinSet{
    private final DataSet left;
    private final DataSet right;
    private final DataSet result;
    private final List<Integer> joinedIndex = new ArrayList<>();

    public LeftJoinSet(DataSet left, DataSet right) {
        this.left = left;
        this.right = right;
        this.result = new DataSet(new ArrayList<>(left.columns));
    }

    public static JoinSet of(DataSet left, DataSet right) {
        return new LeftJoinSet(left, right);
    }

    @Override
    public JoinSet on(String key, String value) {
        int leftIndex = left.columns.indexOf(key);
        int rightIndex = right.columns.indexOf(value);
        if(leftIndex == -1 || rightIndex == -1){
            throw new IllegalArgumentException("Key not found");
        }
        for(int i = 0; i < left.data.size(); i++){
            for(int j = 0; j < right.data.size(); j++){
                if(left.data.get(i).get(leftIndex).equals(right.data.get(j).get(rightIndex))){
                    joinedIndex.add(i);
                    break;
                }
            }
        }

        joinedIndex.forEach(index -> {
            List<Object> row = new ArrayList<>(left.data.get(index));
            boolean found = false;
            for(int i = 0; i < right.data.size(); i++){
                if(left.data.get(index).get(leftIndex).equals(right.data.get(i).get(rightIndex))){
                    row.addAll(right.data.get(i));
                    found = true;
                    break;
                }
            }
            if(!found){
                row.addAll(new ArrayList<>(right.columns.size()));
            }
            result.data.add(row);
        });
        return this;
    }

    public DataSet getResult() {
        return result;
    }
}
