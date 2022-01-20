package com.c.refactoring.lock;

import java.util.Date;
import java.util.List;

import static com.c.refactoring.lock.Constants.LOCK_TEXT;

public class UserLoginChecker {

    public static final int MAXIMUM_LOCK_PERIOD_IN_MS = 60 * 60 * 1000;

    public Lock isUserAllowedToLogin(long id, String status, boolean firstScreen, User user, List existingLocks) {
        if (existingLocks.isEmpty() || existingLocks.get(0) == null) {
            return new Lock(null, false);
        }

        Object[] object = (Object[]) existingLocks.get(0);
        String userId = (String) object[0];
        Date lockTimestamp = (Date) object[1];

        return isUserAllowedToLogin(firstScreen, user, new ExistingLock(userId, lockTimestamp));
    }

    public Lock isUserAllowedToLogin(boolean firstScreen, User user, ExistingLock existingLock) {
        String userId = existingLock.getUserId();
        if (userId == null) {
            return new Lock(null, false);
        }

        long timeElapsedSinceLock = new Date().getTime() - existingLock.getLockTimeStamp().getTime();
        boolean userIdsMatch = userId.equalsIgnoreCase(user.getUserId());

        if (timeElapsedSinceLock > MAXIMUM_LOCK_PERIOD_IN_MS && firstScreen || userIdsMatch) {
            return new Lock(null, false);
        }

        return new Lock(LOCK_TEXT.replace("@@USER@@", userId), true);
    }
}