package org.lshh.skeleton.library.core.variable.data.join;

import org.lshh.skeleton.library.core.variable.data.DataSet;

import java.util.ArrayList;
import java.util.List;

public class InnerJoinSetImplement extends JoinSetImplement implements InnerJoinSet {

    public InnerJoinSetImplement(DataSet left, DataSet right) {
        super(left, right);
    }

    @Override
    public DataSet computeJoinRawResult(){
        DataSet rawResult = DataSet.of(new ArrayList<>());
        for(int i = 0; i < left.size(); i++){
            for(int j = 0; j < right.size(); j++){
                boolean isJoined = isJoinedRow(this.left.getRow(i), this.right.getRow(j));
                if(isJoined){
                    List<Object> row = new ArrayList<>();
                    row.addAll(left.getRow(i));
                    row.addAll(right.getRow(j));
                    rawResult.addRow(row);
                }
            }
        }
        return rawResult;
    }
}
