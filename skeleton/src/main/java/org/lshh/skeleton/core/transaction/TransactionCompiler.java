package org.lshh.skeleton.core.transaction;

import org.lshh.skeleton.core.transaction.implement.TransactionContext;

import java.util.List;

public interface TransactionCompiler {
    List<String> parse(TransactionContext context);
}
