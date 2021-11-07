package com.github.employees.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDetailDto implements Serializable {

    private static final long serialVersionUID = -4782435250676883500L;

    private String firstName;

    private String lastName;

    private String patronymic;

    private String login;

    private String email;

    private String pass;

    private Set<Long> roles;

}
