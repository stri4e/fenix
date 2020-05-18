package com.github.products.utils;

import com.github.products.entity.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpec {

    public static Specification<Product> isPublishTrue() {
        return (root, query, cb) -> cb.isTrue(root.get("publish"));
    }

    public static Specification<Product> isPublishFalse() {
        return (root, query, cb) -> cb.isFalse(root.get("publish"));
    }

    public static Specification<Product> category(String category) {
        return ((root, query, cb) ->
                cb.equal(root.get("category").get("name"), category));
    }

}
