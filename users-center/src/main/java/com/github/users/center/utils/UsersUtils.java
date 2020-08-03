package com.github.users.center.utils;

import com.github.users.center.entity.User;
import com.github.users.center.payload.ConfirmEmail;
import com.google.common.collect.Maps;
import net.sf.uadetector.OperatingSystem;
import net.sf.uadetector.ReadableDeviceCategory;
import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Map;

public class UsersUtils {

    public static final int EXPIRATION_TIME = 30;

    public static final int MAX_REFRESH_SESSION = 5;

    public static final String ROLE_USER = "ROLE_USER";

    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    private static final UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();

    public static ConfirmEmail fetchConfirmEmail(String token, User user) {
        return new ConfirmEmail(
                token,
                user.getLName(),
                user.getFName(),
                user.getEmail()
        );
    }

    public static URI createUri(String userUrl) {
        try {
            return new URI(userUrl);
        } catch (URISyntaxException ignore) {
        }
        return null;
    }

    public static Map<String, Object> information(String location, String userInfo, String firstName) {
        ReadableUserAgent agent = parser.parse(userInfo);
        ReadableDeviceCategory device = agent.getDeviceCategory();
        OperatingSystem os = agent.getOperatingSystem();
        Map<String, Object> information = Maps.newHashMap();
        information.put("firstName", firstName);
        information.put("date", new Date());
        information.put("device", device.getName());
        information.put("os_name", os.getName());
        information.put("browser_name", agent.getName());
        information.put("browser_version", agent.getVersionNumber());
        information.put("location", location);
        return information;
    }

}
