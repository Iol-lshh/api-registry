package org.lshh.skeleton.core.transaction;

import java.util.List;
import java.util.Map;

public interface TransactionCompiled {
    List<String> process();

    Map<String, Object> heap();

    Map<String, Object> inputs();

    Map<String, Object> outputs();

    TransactionCompiled copy();
}
