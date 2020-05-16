package com.github.users.center.utils;

import com.github.users.center.entity.User;
import com.github.users.center.payload.ConfirmEmail;

import java.net.URI;
import java.net.URISyntaxException;

public class UsersUtils {

    public static final int EXPIRATION_TIME = 30;

    public static final int MAX_REFRESH_SESSION = 5;

    public static final String ROLE_USER = "ROLE_USER";

    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    public static ConfirmEmail fetchConfirmEmail(String token, User user) {
        ConfirmEmail ce = new ConfirmEmail();
        ce.setToken(token);
        ce.setLastName(user.getLName());
        ce.setFirstName(user.getFName());
        ce.setEmail(user.getEmail());
        return ce;
    }

    public static URI createUri(String userUrl) {
        try {
            return new URI(userUrl);
        } catch (URISyntaxException ignore) {
        }
        return null;
    }

}
