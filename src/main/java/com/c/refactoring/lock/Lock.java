package com.c.refactoring.lock;

public class Lock {

    private String lockReason;
    private boolean readAccess;

    public Lock() {
    }

    public Lock(String lockReason, boolean readAccess) {
        this.lockReason = lockReason;
        this.readAccess = readAccess;
    }

    public String getLockReason() {
        return lockReason;
    }

    public boolean isReadAccess() {
        return readAccess;
    }

    public void setLockReason(String lockReason) {
        this.lockReason = lockReason;
    }

    public void setRead(boolean readAccess) {
        this.readAccess = readAccess;
    }
}
