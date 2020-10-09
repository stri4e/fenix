package com.github.products.utils;

import com.github.products.entity.Product;
import com.github.products.entity.EntityStatus;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpec {

    public static Specification<Product> statusUsed() {
        return (root, query, cb) -> cb.equal(root.get("status"), EntityStatus.on);
    }

    public static Specification<Product> statusUnUsed() {
        return (root, query, cb) -> cb.equal(root.get("status"), EntityStatus.off);
    }

    public static Specification<Product> category(String category) {
        return ((root, query, cb) -> cb.equal(root.get("category").get("name"), category));
    }

}
