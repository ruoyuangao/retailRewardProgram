package com.example.retailrewardprogram.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Handles the exception thrown when a requested resource is not found.
     *
     * @param e The exception representing the resource not found error.
     * @return ResponseEntity containing the error response with appropriate HTTP status.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(Exception e) {
        // Log the error
        logger.error("Resource not found: {}", e.getMessage());
        // Return the error response with appropriate HTTP status
        return new ResponseEntity<>("Resource not found", HttpStatus.NOT_FOUND);
    }

    /**
     * Handles other types of exceptions that are not specifically handled.
     *
     * @param e The exception to be handled.
     * @return ResponseEntity containing the error response with appropriate HTTP status.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleOtherException(Exception e) {
        // Log the error
        logger.error("Internal server error: {}", e.getMessage(), e);
        // Return the error response with appropriate HTTP status
        return new ResponseEntity<>("Exception", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
