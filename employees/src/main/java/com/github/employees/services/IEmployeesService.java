package com.github.employees.services;

import com.github.employees.entities.Employee;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface IEmployeesService {

    Mono<Employee> readById(UUID employeeId);

    Mono<Boolean> existByEmailOrLogin(String email, String login);

    Mono<Employee> create(Employee employee);

    Mono<Void> update(Employee employee);

    Mono<Employee> readByEmailOrLogin(String email, String login);

}
