package org.lshh.skeleton.core.transaction.data;

import java.util.ArrayList;
import java.util.List;

public class LeftJoinSetImplement extends JoinSetImplement implements LeftJoinSet {

    public LeftJoinSetImplement(DataSet left, DataSet right) {
        super(left, right, new DataSet(new ArrayList<>(left.columns)));
    }

    public static JoinSet of(DataSet left, DataSet right) {
        return new LeftJoinSetImplement(left, right);
    }

    @Override
    public JoinSet on(String key, String value) {
        int leftIndex = this.left.columns.indexOf(key);
        int rightIndex = this.right.columns.indexOf(value);
        if(leftIndex == -1 || rightIndex == -1){
            throw new IllegalArgumentException("Key not found");
        }
        for(int i = 0; i < this.left.data.size(); i++){
            for(int j = 0; j < this.right.data.size(); j++){
                if(this.left.data.get(i).get(leftIndex).equals(this.right.data.get(j).get(rightIndex))){
                    joinedIndex.add(i);
                    break;
                }
            }
        }

        joinedIndex.forEach(index -> {
            List<Object> row = new ArrayList<>(this.left.data.get(index));
            boolean found = false;
            for(int i = 0; i < this.right.data.size(); i++){
                if(this.left.data.get(index).get(leftIndex).equals(this.right.data.get(i).get(rightIndex))){
                    row.addAll(this.right.data.get(i));
                    found = true;
                    break;
                }
            }
            if(!found){
                row.addAll(new ArrayList<>(this.right.columns.size()));
            }
            this.result.data.add(row);
        });
        return this;
    }
}
