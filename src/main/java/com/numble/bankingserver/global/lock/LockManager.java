package com.numble.bankingserver.global.lock;

public interface LockManager {
    void executeWithLock(String lockName, Runnable runnable);
}
