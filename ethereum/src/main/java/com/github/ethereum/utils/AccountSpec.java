package com.github.ethereum.utils;

import com.github.ethereum.entity.Account;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class AccountSpec {

    public static Specification<Account> byUserId(UUID id) {
        return (r, q, cb) -> cb.equal(r.get("user_id"), id);
    }

}
