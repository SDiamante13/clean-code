package com.c.refactoring.lock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.Test;

class UserLoginCheckerTest {
    public static final String STATUS = "NOT_USED";
    public static final int ID = 10;
    public static final boolean ON_FIRST_SCREEN = true;
    public static final String USER_ID_1 = "TEST_USER_ID_1";
    public static final String USER_ID_2 = "TEST_USER_ID_2";
    public static final boolean ON_SECOND_SCREEN = false;
    UserLoginChecker userLoginChecker = new UserLoginChecker();

    @Test
    void testisUserAllowedToLogin_DifferentUserTriesImmediatelyAfter() {
        Lock lock = isUserAllowedToLogin(ON_FIRST_SCREEN, new Object[]{USER_ID_1, new Date()}, new User(USER_ID_2));

        assertTrue(lock.isReadAccess());
        assertNotNull(lock.getLockReason());
    }

    @Test
    void testisUserAllowedToLogin_SameUserReturnsToFirstScreen() {
        Lock lock = isUserAllowedToLogin(ON_FIRST_SCREEN, new Object[]{USER_ID_1, new Date()}, new User(USER_ID_1));

        assertFalse(lock.isReadAccess());
        assertNull(lock.getLockReason());
    }

    @Test
    void testisUserAllowedToLogin_SameUserReturnsToSecondScreen() {
        Lock lock = isUserAllowedToLogin(ON_SECOND_SCREEN, new Object[]{USER_ID_1, new Date()}, new User(USER_ID_1));

        assertFalse(lock.isReadAccess());
        assertNull(lock.getLockReason());
    }

    @Test
    void testisUserAllowedToLogin_User2TriesToLoginToFirstScreen3hoursAfterUser1() {
        Lock lock = isUserAllowedToLogin(ON_FIRST_SCREEN, new Object[]{USER_ID_1, threeHoursBefore()}, new User(USER_ID_2));

        assertFalse(lock.isReadAccess());
        assertNull(lock.getLockReason());
    }

    @Test
    void testisUserAllowedToLogin_User2TriesToLoginToSecondScreen3hoursAfterUser1() {
        Lock lock = isUserAllowedToLogin(ON_SECOND_SCREEN, new Object[]{USER_ID_2, threeHoursBefore()}, new User(USER_ID_1));

        assertTrue(lock.isReadAccess());
        assertNotNull(lock.getLockReason());
    }

    private Lock isUserAllowedToLogin(boolean firstScreen, Object[] access, User user) {
        return userLoginChecker.isUserAllowedToLogin(ID, STATUS, firstScreen, user, Arrays.asList(new Object[][]{access}));
    }

    private Date threeHoursBefore() {
        Date now = new Date();
        return new Date(now.getTime() - 3 * 60 * 60 * 1000);
    }

}
