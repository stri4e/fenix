package com.github.users.center.utils;

import java.net.URI;
import java.net.URISyntaxException;

public class UsersUtils {

    public static final int EXPIRATION_TIME = 30;

    public static final int MAX_REFRESH_SESSION = 5;

    public static final String ROLE_USER = "ROLE_USER";

    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    public static final String ROLE_MANAGER = "ROLE_MANAGER";

    public static final String MANAGER_SCOPE = "self groups/managers";

    public static final String ADMIN_SCOPE = "self groups/managers";

    public static URI createUri(String userUrl) {
        try {
            return new URI(userUrl);
        } catch (URISyntaxException ignore) {
        }
        return null;
    }

}
