package com.github.products.utils;

import org.apache.commons.lang.StringUtils;

import java.util.Objects;
import java.util.function.Supplier;

public class NullSafety {

    private NullSafety() {}

    public static String requiredNotBlank(String value, Supplier<RuntimeException> exception) {
        if (StringUtils.isBlank(value)) {
            throw exception.get();
        }
        return value;
    }

    public static <T> T requiredNotNull(T value, Supplier<RuntimeException> exception) {
        if (Objects.isNull(value)) {
            throw exception.get();
        }
        return value;
    }

    public static void throwIfNull(Object value, Supplier<RuntimeException> exception) {
        if (Objects.isNull(value)) {
            throw exception.get();
        }
    }

}
