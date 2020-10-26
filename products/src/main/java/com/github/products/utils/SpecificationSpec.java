package com.github.products.utils;

import org.springframework.data.jpa.domain.Specification;

public class SpecificationSpec {

    public static Specification<com.github.products.entity.Specification> selectBy(String name, String description) {
        return ((root, query, cb) -> {
            query.distinct(Boolean.TRUE);
            return cb.and(cb.equal(root.get("name"), name),
                    cb.like(root.get("description"), "%" + description + "%"));
        });
    }

}
