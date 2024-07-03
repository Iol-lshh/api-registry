package org.lshh.skeleton.library.core.variable.data.join;

import org.lshh.skeleton.library.core.variable.data.DataSet;

public interface LeftJoinSet extends JoinSet{
    static JoinSet of(DataSet left, DataSet right) {
        return new LeftJoinSetImplement(left, right);
    }
}
