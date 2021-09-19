package com.github.employees.utils;

import com.github.employees.entities.Employee;
import com.github.employees.payload.EmployeeDetailDto;

public class TransferObj {

    public static Employee ofNewEmployee(EmployeeDetailDto data) {
        return Employee.newEmployee(
                data.getFirstName(),
                data.getLastName(),
                data.getPatronymic(),
                data.getLogin(),
                data.getEmail(),
                data.getRoles()
        );
    }

}
