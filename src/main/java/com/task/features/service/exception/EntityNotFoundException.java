package com.task.features.service.exception;

/**
 * Thrown when entity is not found in persistence store.
 */
public class EntityNotFoundException extends RuntimeException {


    public EntityNotFoundException(String message) {
        super(message);
    }
}
