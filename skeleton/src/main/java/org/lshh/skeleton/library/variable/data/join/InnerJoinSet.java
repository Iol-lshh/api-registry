package org.lshh.skeleton.library.variable.data.join;

import org.lshh.skeleton.library.variable.data.DataSet;

public interface InnerJoinSet extends JoinSet{
    static JoinSet of(DataSet left, DataSet right) {
        return new InnerJoinSetImplement(left, right);
    }
}
