package org.lshh.skeleton.library.variable.data.join;

import org.lshh.skeleton.library.variable.data.DataSet;

public interface LeftJoinSet extends JoinSet{
    static JoinSet of(DataSet left, DataSet right) {
        return new LeftJoinSetImplement(left, right);
    }
}
