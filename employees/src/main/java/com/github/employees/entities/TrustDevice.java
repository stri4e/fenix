package com.github.employees.entities;

import com.github.employees.utils.DeviceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrustDevice implements Serializable {

    @Id
    private String id;

    private UUID employeeId;

    private String ip;

    private DeviceType deviceType;

    private String osName;

    private String number;

    private EntityStatus status;

    @CreatedBy
    private UUID createBy;

    @LastModifiedBy
    private UUID updateBy;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updateAt;

    public TrustDevice(UUID employeeId, String ip, DeviceType deviceType, String osName, String number) {
        this.employeeId = employeeId;
        this.ip = ip;
        this.deviceType = deviceType;
        this.osName = osName;
        this.number = number;
    }

    public TrustDevice ip(String ip) {
        this.ip = ip;
        return this;
    }

    public TrustDevice deviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
        return this;
    }

    public TrustDevice osName(String osName) {
        this.osName = osName;
        return this;
    }

    public TrustDevice number(String number) {
        this.number = number;
        return this;
    }

    public TrustDevice status(EntityStatus status) {
        this.status = status;
        return this;
    }

}
