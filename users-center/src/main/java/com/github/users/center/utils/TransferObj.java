package com.github.users.center.utils;

import com.github.users.center.dto.UserRegDto;
import com.github.users.center.entity.Role;
import com.github.users.center.entity.User;

import java.util.Collections;

public class TransferObj {

    public static User user(UserRegDto dto, String role) {
        return new User(
                dto.getFName(),
                dto.getLName(),
                dto.getEmail(),
                dto.getLogin(),
                dto.getPass(),
                Collections.singleton(new Role(role))
        );
    }

}
