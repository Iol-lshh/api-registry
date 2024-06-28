package org.lshh.skeleton.core.transaction.data;

public interface InnerJoinSet extends JoinSet{
    static JoinSet of(DataSet left, DataSet right) {
        return new InnerJoinSetImplement(left, right);
    }
}
