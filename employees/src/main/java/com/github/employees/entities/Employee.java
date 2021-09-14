package com.github.employees.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {

    private static final long serialVersionUID = -4782435250676883500L;

    private UUID id;

    private String firstName;

    private String lastName;

    private String patronymic;

    private String login;

    private String email;

    private String pass;

    private boolean isEnable;

    private boolean isLocked;

    private Set<Role> roles;

    @CreatedDate
    @Column(value = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(value = "updated_at")
    private LocalDateTime updateAt;

    public boolean isAuth(Predicate<String> passwordEquals) {
        return passwordEquals.test(this.pass) && this.isEnable && !this.isLocked;
    }

}
