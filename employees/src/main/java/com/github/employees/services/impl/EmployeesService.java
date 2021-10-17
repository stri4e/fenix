package com.github.employees.services.impl;

import com.github.employees.entities.Employee;
import com.github.employees.repository.EmployeesRepo;
import com.github.employees.services.IEmployeesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class EmployeesService implements IEmployeesService {

    private final EmployeesRepo employeesRepo;

    @Override
    public Mono<Boolean> existByEmailOrLogin(String email, String login) {
        return this.employeesRepo.existsByEmailOrLogin(email, login);
    }

    @Override
    public Mono<Employee> create(Employee employee) {
        return this.employeesRepo.save(employee);
    }

    @Override
    public Mono<Employee> readByEmailOrLogin(String email, String login) {
        return this.employeesRepo.findByEmailOrLogin(email, login);
    }

}
