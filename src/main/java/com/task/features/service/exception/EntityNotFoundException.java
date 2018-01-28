package com.task.features.service.exception;

/**
 * Thrown when entity is not found in persistence store.
 */
public class EntityNotFoundException extends RuntimeException {


    /**
     * Main constructor.
     *
     * @param message error message
     */
    public EntityNotFoundException(String message) {
        super(message);
    }
}
