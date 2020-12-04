package com.github.admins.controllers.impl;

import com.github.admins.dto.LockedDto;
import com.github.admins.dto.UserRegDto;

public class UsersControllerMocks {

    public static final String EMAIL = "email@gmail.com";

    public static final String F_NAME = "Alex";

    public static final String L_NAME = "Northred";

    public static final String LOGIN = "alex";

    public static final String PASS = "123adfr";

    public static UserRegDto requestReg() {
        UserRegDto ur = new UserRegDto();
        ur.setFName(F_NAME);
        ur.setLName(L_NAME);
        ur.setLogin(LOGIN);
        ur.setEmail(EMAIL);
        ur.setPass(PASS);
        ur.setConfirmPass(PASS);
        return ur;
    }

    public static LockedDto requestLocked() {
        return new LockedDto(EMAIL, Boolean.FALSE);
    }

}
