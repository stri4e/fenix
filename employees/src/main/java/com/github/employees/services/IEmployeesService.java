package com.github.employees.services;

import com.github.employees.entities.Employee;
import reactor.core.publisher.Mono;

public interface IEmployeesService {

    Mono<Boolean> existByEmailOrLogin(String email, String login);

    Mono<Employee> create(Employee employee);

    Mono<Employee> readByEmailOrLogin(String email, String login);

}
