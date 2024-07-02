package org.lshh.skeleton.core.transaction.lock;

import java.util.concurrent.locks.Lock;

public interface LockManager {
    Lock getLock(String key);
    void lock(String key);
    void unLock(String key);
}
