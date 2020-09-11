package com.github.orders.service;

import com.github.orders.entity.Branch;

public interface IBranchService {

    Branch readById(Long id);

    Branch create(Branch branch);

    void update(Branch branch);

    void remove(Long id);

}
