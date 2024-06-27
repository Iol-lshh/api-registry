package org.lshh.skeleton.core.transaction.data;

public interface JoinSet {
    JoinSet on(String key, String value);

    DataSet toDataSet();
}
