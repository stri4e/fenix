package com.github.employees.controllers.impl;

import com.github.employees.controllers.IEmployeesPaginationController;
import com.github.employees.entities.Employee;
import com.github.employees.services.IEmployeesPaginationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/pagination")
public class EmployeesPaginationController implements IEmployeesPaginationController {

    private final IEmployeesPaginationService employeesPaginationService;

    @Override
    public Flux<Employee> findByLockedIs(boolean isLocked, Pageable pageable) {
        return this.employeesPaginationService.findByLockedIs(isLocked, pageable);
    }

}
