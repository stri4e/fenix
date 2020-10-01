package com.github.ethereum.utils;

import com.github.ethereum.entity.Account;
import org.springframework.data.jpa.domain.Specification;

public class AccountSpec {

    public static Specification<Account> byUserId(Long id) {
        return (r, q, cb) -> cb.equal(r.get("user_id"), id);
    }

}
