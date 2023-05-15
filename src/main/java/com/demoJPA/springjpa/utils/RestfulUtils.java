package com.demoJPA.springjpa.utils;

import org.apache.commons.lang3.StringUtils;

public class RestfulUtils {
    public static void checkMissingField(Object obj, String field) {
        if ((obj instanceof String && StringUtils.isBlank((String) obj)) || obj == null) {
            throw new IllegalStateException("Missing require field: " + field);
        }
    }
}
