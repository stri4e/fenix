package com.github.employees.controllers.impl;

import com.github.employees.controllers.IEmployeesController;
import com.github.employees.entities.Account;
import com.github.employees.payload.EmployAuthDto;
import com.github.employees.payload.EmployeeDto;
import com.github.employees.payload.JwtAuthResponse;
import com.github.employees.payload.RegistrationResponse;
import com.github.employees.services.IAccountService;
import com.github.employees.services.IEmployeesService;
import com.github.employees.services.INotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;

import static com.github.employees.utils.TransferObj.ofNewEmployee;

@RestController
@RequiredArgsConstructor
public class EmployeesController implements IEmployeesController {

    private final IEmployeesService employeesService;

    private final INotificationService notificationService;

    private final PasswordEncoder passwordEncoder;

    private final IAccountService accountService;

    @Override
    public Mono<RegistrationResponse> registration(EmployeeDto payload) {
        return this.employeesService
                .existByEmailOrLogin(payload.getEmail(), payload.getLogin())
                .filter(Predicate.isEqual(Boolean.FALSE))
                .flatMap(isExist -> this.employeesService.save(ofNewEmployee(payload)
                                .encodedPassword(this.passwordEncoder.encode(payload.getPass()))
                        ).doOnNext(this.notificationService::registrationNotify)
                        .doOnNext(employee -> this.accountService.createDefaultAccount(Account.defaultAccount(employee)))
                        .map(employee -> new RegistrationResponse(employee.getEmail(), payload.getPass()))
                );
    }

    @Override
    public JwtAuthResponse submitAuth(String ip, String userAgent, EmployAuthDto payload) {
        return null;
    }

}
