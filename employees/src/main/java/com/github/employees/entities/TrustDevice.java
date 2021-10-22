package com.github.employees.entities;

import eu.bitwalker.useragentutils.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrustDevice {

    private String ip;

    private String deviceType;

    private String osName;

    public static TrustDevice ofTrustDevice(String ip, UserAgent agent) {
        OperatingSystem os = agent.getOperatingSystem();
        DeviceType deviceType = os.getDeviceType();
        return new TrustDevice(ip, deviceType.getName(), os.getName());
    }

}
