package com.task.features.service.exception;

public class EntityNotFoundException extends RuntimeException {


    public EntityNotFoundException(String message) {
        super(message);
    }
}
