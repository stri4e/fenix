package com.github.employees.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetPassToken implements Serializable {

    private static final long serialVersionUID = -2891754208417175650L;

    private Long id;

    private String token;

    private Date expireTime;

    private String newPass;

    private Employee employee;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    public ResetPassToken(Employee employee) {
        this.employee = employee;
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

    public ResetPassToken user(Employee employee) {
        this.employee = employee;
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
