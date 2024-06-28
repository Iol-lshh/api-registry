package org.lshh.skeleton.core.transaction.data;

import java.util.ArrayList;
import java.util.List;

public class LeftJoinSetImplement extends JoinSetImplement implements LeftJoinSet {


    public LeftJoinSetImplement(DataSet left, DataSet right) {
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

                    List<Object> row = new ArrayList<>();
                    row.addAll(left.data.get(_i));

                    if(left.data.get(_i).get(leftIndex)
                            .equals(right.data.get(_j).get(rightIndex))
                    ){
                        row.addAll(right.data.get(_j));
                    }else{
                        row.addAll(new ArrayList<>(right.columns.size()));
                    }
                    rawResult.data.add(row);
                });
            }
        }
        return rawResult;
    }
}
