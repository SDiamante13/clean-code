package com.c.refactoring.lock;

import java.util.Date;
import java.util.Objects;

public class ExistingLock {
    private final String userId;
    private final Date lockTimeStamp;

    public ExistingLock(String userId, Date lockTimeStamp) {
        this.userId = userId;
        this.lockTimeStamp = lockTimeStamp;
    }

    public String getUserId() {
        return userId;
    }

    public Date getLockTimeStamp() {
        return lockTimeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExistingLock existingLock = (ExistingLock) o;
        return Objects.equals(userId, existingLock.userId) && Objects.equals(lockTimeStamp, existingLock.lockTimeStamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, lockTimeStamp);
    }

    @Override
    public String toString() {
        return "Access{" +
                "userId='" + userId + '\'' +
                ", lockTimeStamp=" + lockTimeStamp +
                '}';
    }
}
