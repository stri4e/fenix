package com.github.employees.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefreshSession implements Serializable {

    private static final long serialVersionUID = -8022389211207894683L;

    private Long id;

    private UUID userId;

    private String refreshToken;

    private String fingerprint;

    private String ip;

    private Date expireIn;

    private EntityStatus status = EntityStatus.on;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    public boolean isExpired() {
        return new Date().after(this.expireIn);
    }

    public long expireIn() {
        return this.expireIn.getTime();
    }

}
