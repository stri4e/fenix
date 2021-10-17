package com.github.employees.controllers;

import com.github.employees.entities.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;

public interface IEmployeesPaginationController {

    @GetMapping
    Flux<Employee> findByLockedIs(@RequestParam(name = "isLocked") boolean isLocked,
                                  Pageable pageable
    );

}
