package com.github.products.exceptions;

import java.util.HashMap;
import java.util.Map;

public enum TypeMessage {

    invalidPageable,

    invalidPageableOrCategory,

    invalidPayload,

    invalidComment;

    private static final Map<TypeMessage, String> MESSAGE = new HashMap<>();

    static {
        MESSAGE.put(invalidPageable, "Invalid pageable params.");
        MESSAGE.put(invalidPageableOrCategory, "Invalid pageable or category params.");
        MESSAGE.put(invalidComment, "Invalid comment data.");
        MESSAGE.put(invalidPayload, "Invalid payload.");
    }

    public static String getMessage(TypeMessage type) {
        return MESSAGE.getOrDefault(type, "");
    }

}
