package com.github.users.center.controllers;

import com.github.users.center.dto.ForgotPassDto;
import com.github.users.center.dto.UserAuthDto;
import com.github.users.center.dto.UserRegDto;
import com.github.users.center.entity.PassResetToken;
import com.github.users.center.entity.Role;
import com.github.users.center.entity.User;

import java.util.Collections;

public class ControllersMocks {

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

    public static final String ENCODE_PASS = "$2a$10$taKaBwahgn2Sm5ygceEGi.2CJOzW35FMcqmzF2HifnSDsHuHTeoXK";

    //=========================================================
    //============== USERS REG DTO MOCKS ======================
    //=========================================================

    public static UserRegDto userRegDto() {
        UserRegDto ur = new UserRegDto();
        ur.setFName(F_NAME);
        ur.setLName(L_NAME);
        ur.setLogin(LOGIN);
        ur.setEmail(EMAIL);
        ur.setPass(PASS);
        ur.setConfirmPass(PASS);
        return ur;
    }

    public static UserRegDto userRegDtoNotValid() {
        UserRegDto ur = new UserRegDto();
        ur.setLName(L_NAME);
        ur.setLogin(LOGIN);
        ur.setEmail(EMAIL);
        ur.setPass(PASS);
        ur.setConfirmPass(PASS);
        return ur;
    }

    //=========================================================
    //============== USERS REG DTO MOCKS ======================
    //=========================================================

    public static UserAuthDto userAuthDto() {
        return new UserAuthDto(
              EMAIL, PASS
        );
    }

    public static UserAuthDto userAuthDtoNotValid() {
        UserAuthDto ua = new UserAuthDto();
        ua.setUserName(LOGIN);
        ua.setPass(NOT_PASS);
        return ua;
    }

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
        u.setRoles(Collections.singletonList(role()));
        return u;
    }

    public static Role role() {
        Role r = new Role(ROLE_ADMIN);
        r.setId(ID);
        return r;
    }

    public static User userForAuth() {
        User u = new User();
        u.setId(ID);
        u.setFName(F_NAME);
        u.setLName(L_NAME);
        u.setLogin(LOGIN);
        u.setEmail(EMAIL);
        u.setPass(ENCODE_PASS);
        u.setEnable(IS_ENABLE);
        u.setRoles(Collections.singletonList(role()));
        return u;
    }

    //=========================================================
    //============== FORGOT PASS MOCKS ========================
    //=========================================================

    public static ForgotPassDto forgotPassDto() {
        return new ForgotPassDto(EMAIL, NEW_PASS, NEW_PASS);
    }

    public static PassResetToken passResetToken() {
        return new PassResetToken(user());
    }

}
