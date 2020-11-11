package com.github.users.center.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;
import eu.bitwalker.useragentutils.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EmailNotification {

    @NotBlank
    @JsonProperty(value = "email")
    private String email;

    @NotNull
    @JsonProperty(value = "information")
    private Map<String, Object> information;

    public static EmailNotification
    loginNotify(String email, String location, String userInfo, String firstName) {
        return new EmailNotification(email, information(location, userInfo, firstName));
    }

    private static Map<String, Object>
    information(String location, String userInfo, String firstName) {
        UserAgent agent = UserAgent.parseUserAgentString(userInfo);
        Browser browser = agent.getBrowser();
        Version version = agent.getBrowserVersion();
        OperatingSystem os = agent.getOperatingSystem();
        DeviceType deviceType = os.getDeviceType();
        Map<String, Object> information = Maps.newHashMap();
        information.put("firstName", firstName);
        information.put("date", new Date());
        information.put("device", deviceType.getName());
        information.put("os_name", os.getName());
        information.put("browser_name", browser.getName());
        information.put("browser_version", version.getVersion());
        information.put("location", location);
        return information;
    }

    public static EmailNotification
    userChangeNotify(String email, String fName, String lName,
                     String clientUrl, String path, String token) {
        var url = UriComponentsBuilder.newInstance()
                .uri(URI.create(clientUrl))
                .path(path)
                .path(token)
                .build().toString();
        Map<String, Object> information = new HashMap<>();
        information.put("firstName", fName);
        information.put("lastName", lName);
        information.put("redirectUrl", url);
        return new EmailNotification(email, information);
    }

}
