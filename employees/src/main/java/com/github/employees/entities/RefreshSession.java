package com.github.employees.entities;

import eu.bitwalker.useragentutils.*;
import eu.bitwalker.useragentutils.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class RefreshSession implements Serializable {

    private static final long serialVersionUID = -8022389211207894683L;

    @Id
    private String id;

    private UUID employeeId;

    private String fingerprint;

    private String ip;

    private String deviceName;

    private String osName;

    private String browserName;

    private String browserVersion;

    private Date expireIn;

    private EntityStatus status = EntityStatus.on;

    @CreatedBy
    private UUID createBy;

    @LastModifiedBy
    private UUID updateBy;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updateAt;

    public RefreshSession(UUID employeeId, String fingerprint, String ip, String deviceName, String osName, String browserName, String browserVersion, Date expireIn) {
        this.employeeId = employeeId;
        this.fingerprint = fingerprint;
        this.ip = ip;
        this.deviceName = deviceName;
        this.osName = osName;
        this.browserName = browserName;
        this.browserVersion = browserVersion;
        this.expireIn = expireIn;
    }

    public static RefreshSession create(UUID employeeId, String fingerprint, String ip, Date expireIn, UserAgent agent) {
        Browser browser = agent.getBrowser();
        Version version = agent.getBrowserVersion();
        OperatingSystem os = agent.getOperatingSystem();
        DeviceType deviceType = os.getDeviceType();
        return new RefreshSession(employeeId, fingerprint, ip, deviceType.getName(), os.getName(), browser.getName(), version.getVersion(), expireIn);
    }

    public boolean isArgsEq(String fingerprint, String ip, UserAgent agent) {
        Browser browser = agent.getBrowser();
        Version version = agent.getBrowserVersion();
        OperatingSystem os = agent.getOperatingSystem();
        DeviceType deviceType = os.getDeviceType();
        return this.fingerprint.equals(fingerprint)
                && this.ip.equals(ip)
                && this.deviceName.equals(deviceType.getName())
                && this.osName.equals(os.getName())
                && this.browserName.equals(browser.getName())
                && this.browserVersion.equals(version.getVersion());
    }

    public boolean isExpired() {
        return new Date().after(this.expireIn);
    }

    public long expireIn() {
        return this.expireIn.getTime();
    }

    public RefreshSession statusOff() {
        this.status = EntityStatus.off;
        return this;
    }

}
