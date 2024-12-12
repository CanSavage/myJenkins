package org.example;

import java.util.concurrent.locks.ReentrantLock;
import java.util.HashMap;
import java.util.Map;

public class RepositoryLockManager {
    private static final Map<String, ReentrantLock> repoLocks = new HashMap<>();

    public static synchronized ReentrantLock getLock(String repoPath) {
        return repoLocks.computeIfAbsent(repoPath, key -> new ReentrantLock());
    }
}
