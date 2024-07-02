package org.lshh.skeleton.core.transaction.data;

import java.util.ArrayList;
import java.util.List;

public class LeftJoinSetImplement extends JoinSetImplement implements LeftJoinSet {


    public LeftJoinSetImplement(DataSet left, DataSet right) {
        super(left, right);
    }

    @Override
    public DataSet computeJoinRawResult(){
        DataSet rawResult = DataSet.of(new ArrayList<>());
        for(int i = 0; i < left.size(); i++){
            List<Object> row = new ArrayList<>(left.getRow(i));
            for(int j = 0; j < right.size(); j++){
                final int _i = i;
                final int _j = j;
                boolean isJoined = this.joinColumns.stream().allMatch(pair -> {
                    int leftIndex = pair.get(0);
                    int rightIndex = pair.get(1);
                    return left.getRow(_i).get(leftIndex)
                            .equals(right.getRow(_j).get(rightIndex));
                });
                if(isJoined){
                    row.addAll(right.getRow(_j));
                }
            }
            if(row.size() == left.getColumnSize()){
                List<Object> emptyRow = new ArrayList<>();
                for(int k = 0; k < right.getColumnSize(); k++){
                    emptyRow.add(null);
                }
                row.addAll(emptyRow);
            }
            rawResult.addRow(row);
        }
        return rawResult;
    }
}
