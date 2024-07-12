package org.lshh.skeleton.library.transaction.agent;

import org.lshh.skeleton.library.lock.LockManager;
import org.lshh.skeleton.library.resource.query.QueryManager;
import org.lshh.skeleton.library.transaction.Transaction;
import org.lshh.skeleton.library.transaction.TransactionManager;

public interface TransactionAgent {

    static TransactionAgent of(TransactionManager transactionManager, QueryManager queryManager, LockManager lockManager) {
        return new TransactionAgentImplement(transactionManager, queryManager, lockManager);
    }

    int run(Transaction transaction);

    // 가져오기 => 할당
        // 가져오기 => tmp
        // tmp => variable/input/output
    void switchStep(Transaction transaction, String[] step);

    void switchQueryCommand(Transaction transaction, String[] step);

    void switchJoinSetCommand(Transaction transaction, String[] step);

    void switchDataSetCommand(Transaction transaction, String[] step);

    void switchThisCommand(Transaction transaction, String[] step);

    void switchChildCommand(Transaction transaction, String[] step);
}
