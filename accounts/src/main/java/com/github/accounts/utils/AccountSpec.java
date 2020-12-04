package com.github.accounts.utils;

import com.github.accounts.entity.Account;
import org.springframework.data.jpa.domain.Specification;

public class AccountSpec {

    public static Specification<Account> byEmailAndPhone(String email, String phone) {
        return ((root, query, cb) -> cb.and(
                cb.equal(root.get("contact").get("email"), email),
                cb.equal(root.get("contact").get("phone"), phone))
        );
    }

}
