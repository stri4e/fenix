package com.github.employees.services.impl;

import com.github.employees.entities.Employee;
import com.github.employees.repository.EmployeesRepo;
import com.github.employees.services.IEmployeesService;
import com.github.employees.services.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeesService implements IEmployeesService {

    private final IRoleService roleService;

    private final EmployeesRepo employeesRepo;

    @Override
    public Mono<Employee> readById(UUID employeeId) {
        return this.employeesRepo.findById(employeeId);
    }

    @Override
    public Mono<Boolean> existByEmailOrLogin(String email, String login) {
        return this.employeesRepo.existsByEmailOrLogin(email, login);
    }

    @Override
    public Mono<Employee> create(Employee employee, Set<Long> rolesIds) {
        return this.roleService.findByIds(rolesIds).collect(Collectors.toSet())
                .flatMap(roles -> this.employeesRepo.save(employee.addRoles(roles)));
    }

    @Override
    public Mono<Void> update(Employee employee) {
        return this.employeesRepo.save(employee)
                .then();
    }

    @Override
    public Mono<Employee> readByEmailOrLogin(String email, String login) {
        return this.employeesRepo.findByEmailOrLogin(email, login);
    }

    @Override
    public Mono<Employee> readByEmail(String email) {
        return this.employeesRepo.findByEmail(email);
    }

}
