package org.lshh.skeleton.library.variable.data.join;

import org.lshh.skeleton.library.variable.data.DataSet;

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
            boolean anyMatched = false;
            for(int j = 0; j < right.size(); j++){
                boolean isJoined = isJoinedRow(this.left.getRow(i), this.right.getRow(j));
                if(isJoined){
                    List<Object> row = new ArrayList<>();
                    row.addAll(left.getRow(i));
                    row.addAll(right.getRow(j));
                    anyMatched = true;
                    rawResult.addRow(row);
                }
            }
            if(!anyMatched){
                List<Object> emptyRow = new ArrayList<>();
                emptyRow.addAll(left.getRow(i));
                for(int k = 0; k < right.getColumnSize(); k++){
                    emptyRow.add(null);
                }
                rawResult.addRow(emptyRow);
            }
        }
        return rawResult;
    }
}
