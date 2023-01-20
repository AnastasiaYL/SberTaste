package com.example.sbertaste.controller.exception;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

public class NotFoundException extends Exception {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String... messages) {
        this(ArrayUtils.isNotEmpty(messages) ? Arrays.stream(messages).reduce((a, b) -> a + b).orElse("") : "");
    }
}