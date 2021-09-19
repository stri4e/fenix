package com.github.employees.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class RefreshSession implements Serializable {

    private static final long serialVersionUID = -8022389211207894683L;

    @Id
    private String id;

    private UUID userId;

    private String refreshToken;

    private String fingerprint;

    private String ip;

    private Date expireIn;

    private EntityStatus status = EntityStatus.on;

    @CreatedBy
    private UUID createBy;

    @LastModifiedDate
    private UUID updateBy;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updateAt;

    public RefreshSession(UUID userId, String refreshToken, String fingerprint, String ip, Date expireIn) {
        this.userId = userId;
        this.refreshToken = refreshToken;
        this.fingerprint = fingerprint;
        this.ip = ip;
        this.expireIn = expireIn;
    }

    public boolean isExpired() {
        return new Date().after(this.expireIn);
    }

    public long expireIn() {
        return this.expireIn.getTime();
    }

}
