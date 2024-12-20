package com.apelisser.manager.domain.util;

import java.util.Collection;

public final class Assert {

    private Assert() {
        throw new IllegalStateException("Utility class");
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(Collection<?> collection, String message) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isTrue(boolean result, String message) {
        if (!result) {
            throw new IllegalArgumentException(message);
        }
    }

}
