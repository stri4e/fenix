package com.github.users.center.utils;

import com.github.users.center.dto.UserRegDto;
import com.github.users.center.entity.Role;
import com.github.users.center.entity.User;

import java.util.Collections;

public class TransferObj {

    public static User user(UserRegDto dto, String role) {
        User u = new User();
        u.setFName(dto.getFName());
        u.setLName(dto.getLName());
        u.setEmail(dto.getEmail());
        u.setLogin(dto.getLogin());
        u.setPass(dto.getPass());
        u.setRoles(Collections.singleton(new Role(role)));
        return u;
    }


}
