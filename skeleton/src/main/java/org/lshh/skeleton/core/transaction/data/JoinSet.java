package org.lshh.skeleton.core.transaction.data;

import java.util.Optional;

public interface JoinSet {
    DataSet toDataSet();
    JoinSet on(String leftKey, String rightKey);

    void selectLeftColumns(String column);

    void selectRightColumns(String column);

    Optional<Integer> findIndexFromLeft(String column);

    Optional<Integer> findIndexFromRight(String column);
}
