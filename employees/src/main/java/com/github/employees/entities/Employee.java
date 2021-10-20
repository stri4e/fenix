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

    public Employee(
            UUID id,
            String firstName,
            String lastName,
            String patronymic,
            String login,
            String email,
            boolean isLocked,
            Set<String> roles
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.login = login;
        this.email = email;
        this.isLocked = isLocked;
        this.roles = roles;
    }

    public static Employee newEmployee(String firstName,
                                       String lastName,
                                       String patronymic,
                                       String login,
                                       String email,
                                       Set<String> roles) {
        return new Employee(
                UUID.randomUUID(),
                firstName,
                lastName,
                patronymic,
                login,
                email,
                Boolean.FALSE,
                roles
        );
    }

    public Employee encodedPassword(String pass) {
        this.pass = pass;
        return this;
    }

    public boolean isAuth(Predicate<String> passwordEquals) {
        return passwordEquals.test(this.pass) && !this.isLocked;
    }

    public Employee isLockedTrue() {
        this.isLocked = Boolean.TRUE;
        return this;
    }

}
