package org.lshh.skeleton.core.transaction.data;

import java.util.ArrayList;
import java.util.List;

public class InnerJoinSetImplement extends JoinSetImplement implements InnerJoinSet {

    public InnerJoinSetImplement(DataSet left, DataSet right) {
        super(left, right);
    }

    @Override
    public DataSet computeJoin(){
        DataSet rawResult = new DataSet(new ArrayList<>());
        for(int i = 0; i < left.data.size(); i++){
            for(int j = 0; j < right.data.size(); j++){
                int _i = i;
                int _j = j;
                this.joinColumns.forEach(indexes -> {
                    int leftIndex = indexes.get(0);
                    int rightIndex = indexes.get(1);
                    if(left.data.get(_i).get(leftIndex)
                            .equals(right.data.get(_j).get(rightIndex))
                    ){
                        List<Object> row = new ArrayList<>();
                        row.addAll(left.data.get(_i));
                        row.addAll(right.data.get(_j));
                        rawResult.data.add(row);
                    }
                });
            }
        }
        return rawResult;
    }
}
