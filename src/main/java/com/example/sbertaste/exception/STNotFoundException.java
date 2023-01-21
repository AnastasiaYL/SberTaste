package com.example.sbertaste.exception;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

public class STNotFoundException extends Exception {
    public STNotFoundException(String message) {
        super(message);
    }

    public STNotFoundException(String... messages) {
        this(ArrayUtils.isNotEmpty(messages) ? Arrays.stream(messages).reduce((a, b) -> a + b).orElse("") : "");
    }
}