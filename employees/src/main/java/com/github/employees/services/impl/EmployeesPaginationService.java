package com.github.employees.services.impl;

import com.github.employees.entities.Employee;
import com.github.employees.repository.EmployeesRepo;
import com.github.employees.services.IEmployeesPaginationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class EmployeesPaginationService implements IEmployeesPaginationService {

    private final EmployeesRepo employeesRepo;

    @Override
    public Flux<Employee> findByLockedIs(boolean isLocked, Pageable pageable) {
        return this.employeesRepo.findByLockedIs(isLocked, pageable);
    }

}
