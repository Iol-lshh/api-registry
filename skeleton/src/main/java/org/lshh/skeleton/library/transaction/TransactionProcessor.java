package org.lshh.skeleton.library.transaction;

import org.lshh.skeleton.library.lock.LockManager;
import org.lshh.skeleton.library.resource.query.QueryManager;
import org.lshh.skeleton.library.resource.resourcer.ResourcerManager;
import org.lshh.skeleton.library.router.RouterManager;

public interface TransactionProcessor {

    static TransactionProcessor of(RouterManager routerManager, TransactionManager transactionManager, ResourcerManager resourcerManager, QueryManager queryManager, LockManager lockManager) {
        return new TransactionProcessorImplement(routerManager, transactionManager, resourcerManager, queryManager, lockManager);
    }

    int runProcess(Transaction transaction);

    // 가져오기 => 할당
        // 가져오기 => tmp
        // tmp => variable/input/output
    void switchStep(Transaction transaction, String[] step);
}
