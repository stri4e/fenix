package com.github.employees.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "reset_pass_token")
public class ResetPassToken implements Serializable {

    private static final long serialVersionUID = -2891754208417175650L;

    @Id
    private String id;

    private String token;

    private Date expireTime;

    private String newPass;

    private UUID employeeId;

    @CreatedBy
    private UUID createBy;

    @LastModifiedBy
    private UUID updateBy;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updateAt;

    public ResetPassToken(UUID employeeId) {
        this.employeeId = employeeId;
        this.token = UUID.randomUUID().toString();
    }

    public void setExpiryDate(int minute) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, minute);
        this.expireTime = now.getTime();
    }

    public boolean isExpired() {
        return new Date().after(this.expireTime);
    }

    public static ResetPassToken build() {
        return new ResetPassToken();
    }

    public ResetPassToken user(UUID employeeId) {
        this.employeeId = employeeId;
        this.token = UUID.randomUUID().toString();
        return this;
    }

    public ResetPassToken newPass(String newPass) {
        this.newPass = newPass;
        return this;
    }

    public ResetPassToken expiryDate(int minute) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, minute);
        this.expireTime = now.getTime();
        return this;
    }

}
