package com.github.employees.entities;

import com.github.jwt.tokens.entity.TokenInformation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "employees")
public class Employee implements Serializable, TokenInformation {

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

    private Set<Role> roles = new HashSet<>();

    @CreatedBy
    private UUID createBy;

    @LastModifiedBy
    private UUID updateBy;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updateAt;

    public Employee(UUID id,
                    String firstName,
                    String lastName,
                    String patronymic,
                    String login,
                    String email,
                    boolean isLocked) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.login = login;
        this.email = email;
        this.isLocked = isLocked;
    }

    public Employee(
            UUID id,
            String firstName,
            String lastName,
            String patronymic,
            String login,
            String email,
            boolean isLocked,
            Set<Role> roles
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
                                       String email) {
        return new Employee(
                UUID.randomUUID(),
                firstName,
                lastName,
                patronymic,
                login,
                email,
                Boolean.FALSE
        );
    }

    public Employee encodedPassword(String pass) {
        this.pass = pass;
        return this;
    }

    public Employee addRoles(Set<Role> roles) {
        this.roles = roles;
        return this;
    }

    public boolean isAuth(Predicate<String> passwordEquals) {
        return passwordEquals.test(this.pass) && !this.isLocked;
    }

    public Employee isLocked(boolean isLocked) {
        this.isLocked = isLocked;
        return this;
    }

    @Override
    public UUID fetchUuIdAsId() {
        return id;
    }

    @Override
    public String fetchId() {
        return this.id.toString();
    }

    @Override
    public List<String> fetchRoles() {
        return this.roles.stream()
                .map(Role::getRole)
                .collect(Collectors.toList());
    }

    @Override
    public String fetchName() {
        return String.format("%s %s", this.firstName, this.lastName);
    }
}
