package com.github.employees.utils;

import com.github.employees.entities.Account;
import com.github.employees.entities.Employee;
import com.github.employees.payload.AccountDto;
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

    public static AccountDto fromAccount(Account account) {
        return new AccountDto();
    }

    public static Account toAccount(AccountDto account) {
        return new Account();
    }

}
