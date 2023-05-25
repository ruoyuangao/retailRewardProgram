package com.example.retailrewardprogram.exception;

/**
 * Custom exception to be thrown when a requested resource is not found.
 */
public class ResourceNotFoundException extends RuntimeException{
    /**
     * Constructs a new ResourceNotFoundException with the specified error message.
     *
     * @param message The error message describing the resource not found error.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}