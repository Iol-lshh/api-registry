package org.lshh.skeleton.library.lock;

import java.util.concurrent.locks.Lock;

public interface LockManager {
    Lock lock(String s);

    void unlock(String s);
}
