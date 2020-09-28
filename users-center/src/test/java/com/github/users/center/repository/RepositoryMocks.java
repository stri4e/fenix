package com.github.users.center.repository;

import com.github.users.center.entity.*;
import org.assertj.core.util.Lists;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class RepositoryMocks {

    public static final Long ID = 1L;

    public static final Long ID_ANOTHER = 2L;

    public static final String EMAIL = "email@gmail.com";

    public static final String F_NAME = "Alex";

    public static final String NOT_F_NAME = "nOTAlex";

    public static final String L_NAME = "Northred";

    public static final String LOGIN = "alex";

    public static final String NOT_LOGIN = "alex1";

    public static final String PASS = "123adfr";

    public static final String NEW_PASS = "3213adfr";

    public static final String NOT_PASS = "43424dfsdfadfr";

    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    public static final String ROLE_USER = "ROLE_USER";

    public static final boolean IS_ENABLE = true;

    public static final boolean IS_DISABLE = false;

    public static final String TOKEN = "00001101-0000-1000-8000-00805F9B34FB";

    public static final String NOT_VALID_TOKEN = "11001101-0000-1000-8000-00805F9B34FB";

    public static final String MODULE = "USERS-CENTER";

    public static final String COMMAND = "CONFIRM";

    public static final String LOCALHOST_AUTH_FRONT = "http://localhost:3000/auth";

    public static final Long USER_ID = 1L;

    public static final String REFRESH_TOKEN = "00001101-0000-1000-8000-00805F9B34FB";

    public static final String FINGER_PRINT = "11001101-0000-1000-8000-00805F9B34FB";

    public static final String IP = "192.192.192.1";

    public static final Date EXPIRE_IN = new Date(4342342342L);

    public static final Date CREATE_AT = new Date(4242342342L);

    //=========================================================
    //============== USERS MOCKS ==============================
    //=========================================================

    public static User userExp() {
        User u = new User();
        u.setId(ID);
        u.setFName(F_NAME);
        u.setLName(L_NAME);
        u.setLogin(LOGIN);
        u.setEmail(EMAIL);
        u.setPass(PASS);
        u.setEnable(IS_ENABLE);
        u.setIsLocked(Boolean.FALSE);
        u.setRoles(Collections.singletonList(role()));
        return u;
    }

    public static User user() {
        User u = new User();
        u.setId(ID);
        u.setFName(F_NAME);
        u.setLName(L_NAME);
        u.setLogin(LOGIN);
        u.setEmail(EMAIL);
        u.setPass(PASS);
        u.setEnable(IS_ENABLE);
        u.setIsLocked(Boolean.FALSE);
        u.setRoles(Collections.singletonList(role()));
        return u;
    }

    public static Role role() {
        Role r = new Role(ROLE_ADMIN);
        r.setId(ID);
        return r;
    }

    //=========================================================
    //============== CONFIRM TOKEN MOCKS ======================
    //=========================================================

    public static ConfirmToken confirmTokenExp() {
        ConfirmToken c = new ConfirmToken();
        c.setId(ID);
        c.setToken(TOKEN);
        c.setUser(userExp());
        c.setClientUrl(LOCALHOST_AUTH_FRONT);
        return c;
    }

    public static ConfirmToken confirmToken() {
        ConfirmToken c = new ConfirmToken();
        c.setToken(TOKEN);
        c.setUser(userExp());
        c.setClientUrl(LOCALHOST_AUTH_FRONT);
        return c;
    }

    //=========================================================
    //============== PASS RESET TOKEN MOCKS ===================
    //=========================================================

    public static PassResetToken passResetTokenExp() {
        PassResetToken t = new PassResetToken();
        t.setId(ID);
        t.setToken(TOKEN);
        t.setNewPass(NEW_PASS);
        t.setExpiryDate(30);
        t.setUser(userExp());
        return t;
    }

    public static PassResetToken passResetToken() {
        PassResetToken t = new PassResetToken();
        t.setId(ID);
        t.setToken(TOKEN);
        t.setNewPass(NEW_PASS);
        t.setExpiryDate(30);
        t.setUser(userExp());
        return t;
    }

    //=========================================================
    //============== REFRESH SESSION MOCKS ====================
    //=========================================================

    public static RefreshSession refreshSession() {
        return new RefreshSession(
                USER_ID,
                REFRESH_TOKEN,
                FINGER_PRINT,
                IP,
                EXPIRE_IN
        );
    }

    public static RefreshSession refreshSessionExp() {
        return new RefreshSession(
                ID,
                USER_ID,
                REFRESH_TOKEN,
                FINGER_PRINT,
                IP,
                EXPIRE_IN
        );
    }

    public static List<RefreshSession> refreshSessions() {
        return Lists.newArrayList(refreshSessionExp());
    }

}
