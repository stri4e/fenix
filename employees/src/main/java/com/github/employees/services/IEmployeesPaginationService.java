package com.github.employees.services;

import com.github.employees.entities.Employee;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;

public interface IEmployeesPaginationService {

    Flux<Employee> findByLockedIs(boolean isLocked, Pageable pageable);

}
