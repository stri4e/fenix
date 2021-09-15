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
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "employees")
public class Employee implements Serializable {

    private static final long serialVersionUID = -4782435250676883500L;

    @Id
    private UUID id;

    private String firstName;

    private String lastName;

    private String patronymic;

    private String login;

    private String email;

    private String pass;

    private boolean isLocked;

    private Set<String> roles;

    @CreatedBy
    private UUID createBy;

    @LastModifiedDate
    private UUID updateBy;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updateAt;

    public boolean isAuth(Predicate<String> passwordEquals) {
        return passwordEquals.test(this.pass) && !this.isLocked;
    }

}
