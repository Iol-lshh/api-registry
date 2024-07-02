package org.lshh.skeleton.core.transaction.data;

public interface LeftJoinSet extends JoinSet{
    static JoinSet of(DataSet left, DataSet right) {
        return new LeftJoinSetImplement(left, right);
    }
}
